package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.*;
import com.yuegou.entity.*;
import com.yuegou.service.SkuService;
import com.yuegou.service.SpuService;
import com.yuegou.utils.FileUtil;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Spu)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Service("spuService")
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuDao spuDao;
    @Autowired
    private SpuAttributeValueDao spuAttributeValueDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private RelationDao relationDao;
    @Autowired
    private SkuService skuService;
    @Autowired
    private AttributeDao attributeDao;

    @Value("${utils.imagessavepath}")
    private String path;


    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Spu spu) {
        return spuDao.insert(spu);
    }

    /**
     * 通过主键删除数据
     *
     * @param spuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long spuId) {
        if (!spuDao.deleteById(spuId)) throw new CURDException(Code.DELETE_ERR, "spu删除失败");
        List<SpuAttributeValue> spuAttributeValues = spuAttributeValueDao.queryBySpuId(spuId);
        int size = spuAttributeValues.size();
        for (SpuAttributeValue spuAttributeValue : spuAttributeValues) {
            size -= spuAttributeValueDao.deleteById(spuAttributeValue.getSpuAttrId());
        }
        //删除关系表的关系。
        Relation relation = relationDao.queryBySpuId(spuId);
        if (relation == null) throw new CURDException(Code.DELETE_ERR, "找不到对应的关系");
        if (!relationDao.deleteById(relation.getRelationId())) throw new CURDException(Code.DELETE_ERR, "删除关系失败");
        ;
        //删除所有有关系的sku
        List<Sku> skus = skuService.queryBySpuId(spuId);
        for (Sku sku : skus) {
            if (!skuService.deleteById(sku.getSkuId())) throw new CURDException(Code.DELETE_ERR, "sku删除失败");
            ;
        }
        if (!attributeDao.deleteBySpuId(spuId)) throw new CURDException(Code.DELETE_ERR, "attribute删除失败");
        return size == 0;
    }

    @Override
    public boolean update(Spu spu) {
        if (spuDao.queryBySpuId(spu.getSpuId()) == null)
            throw new CURDException(Code.SELECT_ERR, "没有找到id为" + spu.getSpuId() + " 的商品");
        if (!spuDao.update(spu)) throw new CURDException(Code.UPDATE_ERR, "spu更新失败");
        return true;
    }

    public List<Spu> queryAll(Integer size, Integer offset) {
        return spuDao.queryAll(size, offset);
    }

    @Override
    public Spu queryBySpuId(Long spuId) {
        Spu spu = spuDao.queryBySpuId(spuId);
        SpuImages spuImages = spu.getSpuImages();
        if (spuImages != null) {
            if (spuImages.getIndexImgPath() != null)
                spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
            if (spuImages.getImgPath() != null)
                spuImages.setImgPathBase64(FileUtil.fileToByte(path + spuImages.getImgPath()));
        }
        List<Sku> skuList = spu.getSkuList();
        if (skuList.size() != 0) {
            for (Sku sku : skuList) {
                List<SkuImages> skuImagesList = sku.getSkuImagesList();
                if (skuImagesList.size() == 0) {
                    skuImagesList.add(new SkuImages());
                    skuImagesList.add(new SkuImages());
                    skuImagesList.add(new SkuImages());
                    skuImagesList.add(new SkuImages());
                    skuImagesList.add(new SkuImages());
                } else {
                    for (SkuImages skuImages : skuImagesList) {
                        skuImages.setImgPath(FileUtil.fileToByte(path + skuImages.getImgPath()));
                    }
                }
            }
        }
        return spu;
    }

    public boolean saveSpuAndAttributeValues(SpuAndAttributeValues spuAndAttributeValues, String token) {
        Spu spu = spuAndAttributeValues.getSpu();
        List<SpuAttributeValue> spuAttributeValueList = spuAndAttributeValues.getSpuAttributeValueList();
        Date isTime = new Date();
        spu.setCreateTime(isTime);
        if (!spuDao.insert(spu)) throw new CURDException(Code.SAVE_ERR, "spu存储失败");

        Long spuId = spu.getSpuId();
        Claims claims = jwtUtil.parseToken(token);
        //通过名字获取用户，通过用户获取店铺
        User userName = userDao.getUserName((String) claims.get("userName"));
        Store store = storeDao.queryByUserId(userName.getUserId());
        //relation创建这个对象与店铺建立关系
        Relation relation = new Relation(null, store.getStoreId(), spuId);
        if (!relationDao.insert(relation)) throw new CURDException(Code.SAVE_ERR, "商品和店铺关系建立失败");

        return true;
    }

    @Override
    public boolean intersectSpuAndAttribute(SpuAndAttributeAllList spuAndAttributeAllList) {
        //处理spu，添加创建时间，获取店铺，建立关联
        Spu spu = spuAndAttributeAllList.getSpu();
        spu.setCreateTime(new Date());
        if (!spuDao.insert(spu)) throw new CURDException(Code.SAVE_ERR, "spu存储失败");
        Store store = storeDao.queryById(spu.getStoreId());
        Relation relation = new Relation(null, store.getStoreId(), spu.getSpuId());
        if (!relationDao.insert(relation)) throw new CURDException(Code.SAVE_ERR, "商品和店铺关系建立失败");
        //这里的数组存放了attribute的key，type，和spuAttribute的value
        List<SpuAttAndAttVal> spuAttAndAttValList = spuAndAttributeAllList.getSpuAttAndAttValList();
        for (int i = 0; i < spuAttAndAttValList.size(); i++) {
            //创建attribute对象
            Attribute attribute = new Attribute();
            attribute.setSpuId(spu.getSpuId());
            attribute.setCreateTime(new Date());
            attribute.setAttributeType(spuAttAndAttValList.get(i).getAttributeType());
            attribute.setAttributeSort((long) (i + 1));
            attribute.setAttributeName(spuAttAndAttValList.get(i).getAttributeName());
            if (spuAttAndAttValList.get(i).getAttributeType().equals(1)) {
                attribute.setAttributeOptions(spuAttAndAttValList.get(i).getAttributeValue());
                if (!attributeDao.insert(attribute)) throw new CURDException(Code.SAVE_ERR, "创建Attribute失败");
                continue;
            }
            if (!attributeDao.insert(attribute)) throw new CURDException(Code.SAVE_ERR, "创建Attribute失败");
            //创建spu_attribute_value对象
            SpuAttributeValue spuAttributeValue = new SpuAttributeValue();
            spuAttributeValue.setSpuId(spu.getSpuId());
            spuAttributeValue.setSpuAttrId((long) i + 1);
            spuAttributeValue.setAttributeValue(spuAttAndAttValList.get(i).getAttributeValue());
            if (!spuAttributeValueDao.insert(spuAttributeValue))
                throw new CURDException(Code.SAVE_ERR, "创建SpuAttributeValue失败");
        }
        return true;
    }

    @Override
    public List<Spu> queryIndexPageList(Integer size, Integer offset) {
        List<Spu> spuList = spuDao.queryIndexPageList(size, offset);
        for (Spu spu : spuList) {
            SpuImages spuImages = spu.getSpuImages();
            spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
        }
        return spuList;
    }

    @Override
    public List<Spu> querySearchSpu(SpuSearchEntity spuSearchEntity) {
        List<Spu> spuList = spuDao.querySearchSpu(spuSearchEntity);
        for (Spu spu : spuList) {
            SpuImages spuImages = spu.getSpuImages();
            spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
        }
        return spuList;
    }

    @Override
    public List<Spu> queryByStoreId(Integer size, Integer offset, Long storeId,String search,Integer power) {
        List<Spu> spuList = spuDao.queryByStoreIdAllSpu(size, offset, storeId, search, power);
        for (Spu spu : spuList) {
            SpuImages spuImages = spu.getSpuImages();
            if (spuImages == null) continue;
            if (spuImages.getIndexImgPath() != null)
                spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
            if (spuImages.getImgPath() != null)
                spuImages.setImgPathBase64(FileUtil.fileToByte(path + spuImages.getImgPath()));
        }
        return spuList;
    }

}

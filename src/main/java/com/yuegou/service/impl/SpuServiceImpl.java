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
        return size == 0;
    }

    @Override
    public boolean update(SpuAndAttributeValues spuAndAttributeValues) {
        Spu spu = spuAndAttributeValues.getSpu();
        if (spuDao.queryBySpuId(spu.getSpuId()) == null)
            throw new CURDException(Code.SELECT_ERR, "没有找到id为" + spu.getSpuId() + " 的商品");
        if (!spuDao.update(spu)) throw new CURDException(Code.UPDATE_ERR, "spu更新失败");
        List<SpuAttributeValue> spuAttributeValueList = spuAndAttributeValues.getSpuAttributeValueList();
        for (SpuAttributeValue spuAttributeValue : spuAttributeValueList) {
            if (!spuAttributeValueDao.update(spuAttributeValue))
                throw new CURDException(Code.UPDATE_ERR, "spuAttributeValue更新失败");
        }
        return true;
    }

    public List<Spu> queryAll(Integer size, Integer offset) {
        return spuDao.queryAll(size, offset);
    }

    @Override
    public Spu queryBySpuId(Long spuId) {
        Spu spu = spuDao.queryBySpuId(spuId);
        SpuImages spuImages = spu.getSpuImages();
        spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
        List<Sku> skuList = spu.getSkuList();
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

}

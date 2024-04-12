package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.*;
import com.yuegou.entity.*;
import com.yuegou.service.SkuService;
import com.yuegou.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Sku)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private SpuDao spuDao;
    @Autowired
    private AttributeDao attributeDao;
    @Autowired
    private SkuAttributeValueDao skuAttributeValueDao;
    @Autowired
    private DetailDao detailDao;
    @Autowired
    private GoodEvaluateDao goodEvaluateDao;

    @Value("${utils.imagessavepath}")
    private String path;


    /**
     * 通过ID查询单条数据
     *
     * @param skuId 主键
     * @return 实例对象
     */
    @Override
    public Sku queryById(Long skuId) {
        return this.skuDao.queryById(skuId);
    }

    @Override
    public List<Sku> queryBySpuId(Long spuId) {
        List<Sku> skus = skuDao.queryBySpuId(spuId);
        for (Sku sku : skus) {
            List<SkuImages> skuImagesList = sku.getSkuImagesList();
            if (skuImagesList == null) continue;
            for (SkuImages skuImages : skuImagesList) {
                if (skuImages == null) continue;
                skuImages.setImgPath(FileUtil.fileToByte(path + skuImages.getImgPath()));
            }
        }
        return skuDao.queryBySpuId(spuId);
    }

    @Override
    public List<Sku> queryAll(Integer size, Integer offset) {
        return skuDao.queryAll(size,offset);
    }

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Sku sku) {
        return skuDao.insert(sku);
    }

    @Override
    public boolean insertSkuAndAttributeValues(SkuAndAttributeValues skuAndAttributeValues) {
        Sku sku = skuAndAttributeValues.getSku();
        List<SkuAttributeValue> skuAttributeValues = skuAndAttributeValues.getSkuAttributeValues();
        if (!skuDao.insert(sku)) throw new CURDException(Code.SAVE_ERR,"sku保存失败");
        List<Attribute> attributes = attributeDao.queryBySpuId(sku.getSpuId());
        List<Attribute> attributesTypeIsOne = attributes.stream().filter(item -> item.getAttributeType().equals(1)).collect(Collectors.toList());
        for (int i = 0; i < attributesTypeIsOne.size(); i++) {
            skuAttributeValues.get(i).setSkuId(sku.getSkuId());
            skuAttributeValues.get(i).setAttributeId(attributesTypeIsOne.get(i).getAttributeId());
            if (!skuAttributeValueDao.insert(skuAttributeValues.get(i))) throw new CURDException(Code.SAVE_ERR,"skuAttributeValue存储失败");
        }
        return true;
    }

    @Override
    public boolean update(SkuAndAttributeValues skuAndAttributeValues) {
        Sku sku = skuAndAttributeValues.getSku();
        List<SkuAttributeValue> skuAttributeValues = skuAndAttributeValues.getSkuAttributeValues();
        if (!skuDao.update(sku)) throw new CURDException(Code.UPDATE_ERR,"sku修改失败");
        for (SkuAttributeValue skuAttributeValue : skuAttributeValues) {
            if (!skuAttributeValueDao.update(skuAttributeValue)) throw new CURDException(Code.UPDATE_ERR,"skuAttributeValue修改失败");
        }
        return true;
    }

    @Override
    public boolean deleteById(Long skuId) {
        if(!skuDao.deleteById(skuId)) throw new CURDException(Code.DELETE_ERR,"sku删除失败，或没有找到这个商品实体");
        List<SkuAttributeValue> skuAttributeValues = skuAttributeValueDao.queryBySkuId(skuId);
        int sum = skuAttributeValues.size();
        for (SkuAttributeValue skuAttributeValue : skuAttributeValues) {
            if (!skuAttributeValueDao.deleteById(skuAttributeValue.getSkuAttrId())) throw new CURDException(Code.DELETE_ERR,"skuAttributeValue删除失败");
            sum--;
        }
        List<Detail> details = detailDao.queryBySkuId(skuId);
        if (details.size()!=0){
            if (!detailDao.deleteBySkuId(skuId)) throw new CURDException(Code.DELETE_ERR,"订单详情删除失败");
        }
        List<GoodEvaluate> goodEvaluateList = goodEvaluateDao.queryBySkuId(skuId);
        if (goodEvaluateList.size()!=0){
            if (!goodEvaluateDao.deleteBySkuId(skuId)) throw new CURDException(Code.DELETE_ERR,"相关评论删除失败");
        }
        return sum == 0;
    }

}

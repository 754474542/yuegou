package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.AttributeDao;
import com.yuegou.dao.SkuAttributeValueDao;
import com.yuegou.dao.SpuDao;
import com.yuegou.entity.*;
import com.yuegou.dao.SkuDao;
import com.yuegou.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
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
        //获取spu
        Spu spu = spuDao.queryBySpuId(sku.getSpuId());
        //通过spu的id获取对应的属性
        List<Attribute> attributes = attributeDao.queryByCateId(spu.getCategoryId());
        //用stream流把type=1的全取出来，就是sku要设置的属性
        List<Attribute> attributesTypeIsOne = attributes.stream().filter(item -> item.getAttributeType().equals(1)).collect(Collectors.toList());
        if (!skuDao.insert(sku)) throw new CURDException(Code.SAVE_ERR,"sku保存失败");
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
        if(!skuAttributeValueDao.deleteById(skuId)) throw new CURDException(Code.DELETE_ERR,"skuAttributeValue删除失败，或没有找到这个商品实体");
        List<SkuAttributeValue> skuAttributeValues = skuAttributeValueDao.queryBySkuId(skuId);
        int sum = skuAttributeValues.size();
        for (SkuAttributeValue skuAttributeValue : skuAttributeValues) {
            if (!skuAttributeValueDao.deleteById(skuAttributeValue.getSkuAttrId())) throw new CURDException(Code.DELETE_ERR,"skuAttributeValue删除失败");
            sum--;
        }
        return sum == 0;
    }

}

package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.AttributeDao;
import com.yuegou.dao.SpuAttributeValueDao;
import com.yuegou.entity.Attribute;
import com.yuegou.entity.Spu;
import com.yuegou.dao.SpuDao;
import com.yuegou.entity.SpuAndAttributeValues;
import com.yuegou.entity.SpuAttributeValue;
import com.yuegou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AttributeDao attributeDao;

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
        if (!spuDao.deleteById(spuId)) throw new CURDException(Code.DELETE_ERR,"spu删除失败");
        List<SpuAttributeValue> spuAttributeValues = spuAttributeValueDao.queryBySpuId(spuId);
        int size = spuAttributeValues.size();
        for (SpuAttributeValue spuAttributeValue : spuAttributeValues) {
            size -= spuAttributeValueDao.deleteById(spuAttributeValue.getSpuAttrId());
        }
        //后续还要加上删除所有有关系的sku
        return size == 0;
    }

    @Override
    public boolean update(SpuAndAttributeValues spuAndAttributeValues) {
        Spu spu = spuAndAttributeValues.getSpu();
        if (spuDao.queryBySpuId(spu.getSpuId()) == null) throw new CURDException(Code.SELECT_ERR,"没有找到id为" + spu.getSpuId() + " 的商品");
        if (!spuDao.update(spu)) throw new CURDException(Code.UPDATE_ERR,"spu更新失败");
        List<SpuAttributeValue> spuAttributeValueList = spuAndAttributeValues.getSpuAttributeValueList();
        for (SpuAttributeValue spuAttributeValue : spuAttributeValueList) {
            System.out.println(spuAttributeValue);
            if(!spuAttributeValueDao.update(spuAttributeValue)) throw new CURDException(Code.UPDATE_ERR,"spuAttributeValue更新失败");
        }
        return true;
    }

    public List<Spu> queryAll() {
        return spuDao.queryAll();
    }

    @Override
    public Spu queryBySpuId(Long spuId) {
        return spuDao.queryBySpuId(spuId);
    }

    public boolean saveSpuAndAttributeValues(SpuAndAttributeValues spuAndAttributeValues) {
        Spu spu = spuAndAttributeValues.getSpu();
        List<SpuAttributeValue> spuAttributeValueList = spuAndAttributeValues.getSpuAttributeValueList();
        Date isTime = new Date();
        spu.setCreateTime(isTime);
        if (!spuDao.insert(spu)) throw new CURDException(Code.SAVE_ERR, "spu存储失败");
        List<Attribute> attributes = attributeDao.queryByCateId(spu.getCategoryId());
        Long spuId = spu.getSpuId();

        List<Attribute> collect = attributes.stream().filter((item1) -> {
            return item1.getAttributeType().equals(0);
        }).collect(Collectors.toList());

        for (int i = 0; i < collect.size(); i++) {
            SpuAttributeValue spuAttributeValue = spuAttributeValueList.get(i);
            spuAttributeValue.setSpuId(spuId);
            spuAttributeValue.setAttributeId(collect.get(i).getAttributeId());
            if (!spuAttributeValueDao.insert(spuAttributeValue)) throw new CURDException(Code.SAVE_ERR, "spu属性存储失败");
        }
        return true;
    }

}

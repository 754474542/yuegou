package com.yuegou.service.impl;

import com.yuegou.entity.Attribute;
import com.yuegou.dao.AttributeDao;
import com.yuegou.service.AttributeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Attribute)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    @Resource
    private AttributeDao attributeDao;


    @Override
    public List<Attribute> queryBySpuId(Long spuId) {
        return attributeDao.queryBySpuId(spuId);
    }

    @Override
    public boolean insert(Attribute attribute) {
        return attributeDao.insert(attribute);
    }

    @Override
    public boolean update(Attribute attribute) {
        return attributeDao.update(attribute);
    }

    @Override
    public boolean deleteBySpuId(Long spuId) {
        return attributeDao.deleteBySpuId(spuId);
    }
}

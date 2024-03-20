package com.yuegou.service.impl;

import com.yuegou.entity.Spu;
import com.yuegou.dao.SpuDao;
import com.yuegou.service.SpuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Spu)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Service("spuService")
public class SpuServiceImpl implements SpuService {
    @Resource
    private SpuDao spuDao;

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    @Override
    public Spu insert(Spu spu) {
        this.spuDao.insert(spu);
        return spu;
    }

    /**
     * 通过主键删除数据
     *
     * @param spuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long spuId) {
        return this.spuDao.deleteById(spuId) > 0;
    }
}

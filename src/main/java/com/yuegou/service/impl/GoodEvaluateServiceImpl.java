package com.yuegou.service.impl;

import com.yuegou.entity.GoodEvaluate;
import com.yuegou.dao.GoodEvaluateDao;
import com.yuegou.service.GoodEvaluateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (GoodEvaluate)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Service("goodEvaluateService")
public class GoodEvaluateServiceImpl implements GoodEvaluateService {
    @Resource
    private GoodEvaluateDao goodEvaluateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param evaluateId 主键
     * @return 实例对象
     */
    @Override
    public GoodEvaluate queryById(Long evaluateId) {
        return this.goodEvaluateDao.queryById(evaluateId);
    }

    /**
     * 新增数据
     *
     * @param goodEvaluate 实例对象
     * @return 实例对象
     */
    @Override
    public GoodEvaluate insert(GoodEvaluate goodEvaluate) {
        this.goodEvaluateDao.insert(goodEvaluate);
        return goodEvaluate;
    }

    /**
     * 修改数据
     *
     * @param goodEvaluate 实例对象
     * @return 实例对象
     */
    @Override
    public GoodEvaluate update(GoodEvaluate goodEvaluate) {
        this.goodEvaluateDao.update(goodEvaluate);
        return this.queryById(goodEvaluate.getEvaluateId());
    }

    /**
     * 通过主键删除数据
     *
     * @param evaluateId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long evaluateId) {
        return this.goodEvaluateDao.deleteById(evaluateId) > 0;
    }
}

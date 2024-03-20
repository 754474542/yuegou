package com.yuegou.service;

import com.yuegou.entity.GoodEvaluate;
import org.springframework.transaction.annotation.Transactional;

/**
 * (GoodEvaluate)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Transactional
public interface GoodEvaluateService {

    /**
     * 通过ID查询单条数据
     *
     * @param evaluateId 主键
     * @return 实例对象
     */
    GoodEvaluate queryById(Long evaluateId);

    /**
     * 新增数据
     *
     * @param goodEvaluate 实例对象
     * @return 实例对象
     */
    GoodEvaluate insert(GoodEvaluate goodEvaluate);

    /**
     * 修改数据
     *
     * @param goodEvaluate 实例对象
     * @return 实例对象
     */
    GoodEvaluate update(GoodEvaluate goodEvaluate);

    /**
     * 通过主键删除数据
     *
     * @param evaluateId 主键
     * @return 是否成功
     */
    boolean deleteById(Long evaluateId);

}

package com.yuegou.service;

import com.yuegou.entity.Ban;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Ban)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Transactional
public interface BanService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Ban queryById(Long userId);

    List<Ban> queryAll();

    /**
     * 新增数据
     *
     * @param ban 实例对象
     * @return 实例对象
     */
    Ban insert(Ban ban);

    /**
     * 修改数据
     *
     * @param ban 实例对象
     * @return 实例对象
     */
    Ban update(Ban ban);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

}

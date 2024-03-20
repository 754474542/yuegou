package com.yuegou.service.impl;

import com.yuegou.entity.Ban;
import com.yuegou.dao.BanDao;
import com.yuegou.service.BanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Ban)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Service("banService")
public class BanServiceImpl implements BanService {
    @Resource
    private BanDao banDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public Ban queryById(Long userId) {
        return this.banDao.queryById(userId);
    }

    @Override
    public List<Ban> queryAll() {
        return banDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param ban 实例对象
     * @return 实例对象
     */
    @Override
    public Ban insert(Ban ban) {
        this.banDao.insert(ban);
        return ban;
    }

    /**
     * 修改数据
     *
     * @param ban 实例对象
     * @return 实例对象
     */
    @Override
    public Ban update(Ban ban) {
        this.banDao.update(ban);
        return this.queryById(ban.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.banDao.deleteById(userId) > 0;
    }
}

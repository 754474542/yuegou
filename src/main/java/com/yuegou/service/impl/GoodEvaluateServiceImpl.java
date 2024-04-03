package com.yuegou.service.impl;

import com.yuegou.entity.GoodEvaluate;
import com.yuegou.dao.GoodEvaluateDao;
import com.yuegou.entity.User;
import com.yuegou.service.GoodEvaluateService;
import com.yuegou.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * (GoodEvaluate)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Service("goodEvaluateService")
public class GoodEvaluateServiceImpl implements GoodEvaluateService {

    @Autowired
    private GoodEvaluateDao goodEvaluateDao;
    @Value("${utils.imagessavepath}")
    private String path;

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
    public boolean insert(GoodEvaluate goodEvaluate) {
        goodEvaluate.setCreateTime(new Date());
        return goodEvaluateDao.insert(goodEvaluate);
    }

    /**
     * 修改数据
     *
     * @param goodEvaluate 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(GoodEvaluate goodEvaluate) {
        return goodEvaluateDao.update(goodEvaluate);
    }

    /**
     * 通过主键删除数据
     *
     * @param evaluateId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long evaluateId) {
        return goodEvaluateDao.deleteById(evaluateId);
    }

    @Override
    public List<GoodEvaluate> queryBySkuId(Long skuId) {
        return goodEvaluateDao.queryBySkuId(skuId);
    }

    @Override
    public List<GoodEvaluate> queryBySpuId(Long spuId) {
        List<GoodEvaluate> goodEvaluateList = goodEvaluateDao.queryBySpuId(spuId);
        for (GoodEvaluate goodEvaluate : goodEvaluateList) {
            User user = goodEvaluate.getUser();
                String userHead = user.getUserHead();
                if (userHead != null && !userHead.trim().equals("") && userHead.length() <= 200) user.setUserHead(FileUtil.fileToByte(path + userHead));
        }
        return goodEvaluateList;
    }

    @Override
    public List<GoodEvaluate> queryBySpuIdLimit(Integer size, Integer offset, Long spuId) {
        List<GoodEvaluate> goodEvaluateList = goodEvaluateDao.queryBySpuIdLimit(size, offset, spuId);
        for (GoodEvaluate goodEvaluate : goodEvaluateList) {
            if (goodEvaluate == null){
                System.out.println("空");
            }else {
                User user = goodEvaluate.getUser();
                String userHead = user.getUserHead();
                if (userHead != null && !userHead.trim().equals("") && userHead.length() <= 200) user.setUserHead(FileUtil.fileToByte(path + userHead));
            }
        }
        return goodEvaluateList;
    }

    @Override
    public List<GoodEvaluate> queryAll(Integer size, Integer offset) {
        return goodEvaluateDao.queryAll(size,offset);
    }

    @Override
    public List<GoodEvaluate> queryByUserId(Long userId) {
        return goodEvaluateDao.queryByUserId(userId);
    }
}

package com.yuegou.service;

import com.yuegou.entity.Detail;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Detail)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Transactional
public interface DetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    Detail queryById(Long detailId);

    List<Detail> queryByOrderId(Long orderId);

    Detail queryBySkuIdAndSpuId(Detail detail);

    List<Detail> queryByStoreId(Integer size,
                                     Integer offset,
                                     Long storeId,
                                     Integer detailStatus);

    /**
     * 新增数据
     *
     * @param detail 实例对象
     * @return 实例对象
     */
    boolean insert(Detail detail);

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 是否成功
     */
    boolean deleteById(Long detailId);

    boolean update(Detail detail);

}

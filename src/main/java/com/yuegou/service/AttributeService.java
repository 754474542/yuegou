package com.yuegou.service;

import com.yuegou.entity.Attribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Attribute)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:32
 */
@Transactional
public interface AttributeService {

    List<Attribute> queryBySpuId(Long spuId);

    boolean insert(Attribute attribute);

    boolean update(Attribute attribute);

    boolean deleteBySpuId(Long spuId);

}

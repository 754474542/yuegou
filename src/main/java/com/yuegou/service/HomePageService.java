package com.yuegou.service;

import com.yuegou.entity.SpuImages;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HomePageService {

    List<SpuImages> carouseImages();

}

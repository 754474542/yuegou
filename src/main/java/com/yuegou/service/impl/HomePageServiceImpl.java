package com.yuegou.service.impl;

import com.yuegou.entity.SpuImages;
import com.yuegou.service.HomePageService;
import com.yuegou.service.timetacks.ProjectTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private ProjectTasks projectTasks;

    @Override
    public List<SpuImages> carouseImages() {
        return projectTasks.getBannerList();
    }

}

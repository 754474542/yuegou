package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.SpuImages;
import com.yuegou.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/carouselImgs")
    public Result carouseImages(){
        List<SpuImages> spuImages = homePageService.carouseImages();
        return new Result(spuImages != null ? Code.SAVE_OK : Code.SAVE_ERR,spuImages,spuImages != null ? "OK" : "ERROR");
    }

}

package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.ImageDeleteEntity;
import com.yuegou.entity.QainImageEntity;
import com.yuegou.entity.SpuImages;
import com.yuegou.service.ImageDownloadService;
import com.yuegou.service.timetacks.ProjectTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageDownloadController {

    @Autowired
    private ImageDownloadService imageDownloadService;

    @PostMapping("/user")
    public Result userHeadFileUp(@RequestParam("file") MultipartFile file , @RequestHeader("token") String token){
        boolean flag = imageDownloadService.userHeadFileUp(file, token);
        return new Result(flag ? Code.IMG_UP_OK : Code.IMG_UP_ERR,flag ? "头像修改成功" : "头像上传失败");
    }

    @PostMapping("/store")
    public Result skuStoreImgFileUp(@RequestParam("files") List<MultipartFile> files,@RequestParam(value = "imgIds",defaultValue = "0")List<Long> imgIds,@RequestParam(value = "skuId", defaultValue = "0")Long skuId, @RequestHeader("token") String token){
        boolean flag = imageDownloadService.storeImgFileUp(files, imgIds, skuId, token);
        return new Result(flag ? Code.IMG_UP_OK : Code.IMG_UP_ERR,flag ? "商品图片上传完毕" : "商品图片上传失败");
    }

    @PostMapping("/deleteStore")
    public Result skuStoreImgDelete(@RequestBody ImageDeleteEntity imageDeleteEntity, @RequestHeader String token){
        boolean flag = imageDownloadService.storeImgDelete(imageDeleteEntity, token);
        return new Result(flag ? Code.IMG_UP_OK : Code.IMG_UP_ERR,flag ? "商品删除完毕" : "商品删除失败");
    }

    @PostMapping("/carousel")
    public Result spuImgFileUp(@RequestParam("file") MultipartFile file ,@RequestParam(value = "spuId",defaultValue = "0")Long spuId,  @RequestHeader("token") String token){
        boolean flag = imageDownloadService.spuImgFileUp(file,spuId,token);
        return new Result(flag ? Code.IMG_UP_OK : Code.IMG_UP_ERR,flag ? "轮播图上传完毕，感谢您的参加" : "轮播图上传失败");
    }

    @PostMapping("/deleteCarousel/{imgId}")
    public Result spuImageDelete(@PathVariable Long imgId, @RequestHeader String token){
        boolean flag = imageDownloadService.spuImgFileDelete(imgId, token);
        return new Result(flag ? Code.IMG_UP_OK : Code.IMG_UP_ERR,flag ? "商品删除完毕" : "商品删除失败");
    }

    @PostMapping("/gainImage")
    public Result queryOneImage(@RequestBody QainImageEntity imagePath){
        String base = imageDownloadService.queryOneImage(imagePath);
        return new Result(Code.SELECT_OK, base,"图片读取成功");
    }

    @GetMapping("/queryBannerList")
    public Result queryBannerList(){
        List<SpuImages> bannerList = imageDownloadService.queryBannerList();
        return new Result(bannerList != null ? Code.SELECT_OK : Code.SELECT_ERR, bannerList,bannerList != null ? "轮播图获取成功" : "轮播图获取失败" );
    }

}

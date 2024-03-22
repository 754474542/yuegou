package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.controller.pretreatment.exceptionhandle.FileFailedException;
import com.yuegou.controller.pretreatment.exceptionhandle.NullValueException;
import com.yuegou.dao.*;
import com.yuegou.entity.*;
import com.yuegou.service.ImageDownloadService;
import com.yuegou.service.SkuImagesService;
import com.yuegou.service.SkuService;
import com.yuegou.service.UserService;
import com.yuegou.utils.FileUtil;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ImageDownloadServiceImpl implements ImageDownloadService {

    @Autowired
    private Logger logger;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SkuImagesDao skuImagesDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private SpuImagesDao spuImagesDao;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean userHeadFileUp(MultipartFile file, String token) {
        long maxSize = 3145728L;
        if (file.getSize() > maxSize) throw new FileFailedException(Code.FILEUP_ERR, "文件大小过大，请重新选择小于3MB的文件");
        String msg = "头像上传成功";
        if (file.isEmpty()) throw new NullValueException(Code.NULLVALUE_ERR, "上传文件失败，请重新上传");
        Claims claims = jwtUtil.parseToken(token);
        //生成保存url
        User user = userDao.getUserName((String) claims.get("userName"));
        String url = "images/" + "head" + user.getUserName() + System.currentTimeMillis() + ".jpg";
        String userHead = user.getUserHead();
        //判断有没有头像，如果有删除掉
        if (userHead != null) {
            File oldHead = new File(userHead);
            if (oldHead.isFile()) {
                boolean delete = oldHead.delete();
                if (delete) logger.info(" 删除了" + userHead);
                msg = "头像修改成功";
            }
        }
        //进行头像上传
        if (!FileUtil.saveFile(file, url)) throw new FileFailedException(Code.FILEDAILED_ERR, "文件上传出现异常");
        user.setUserHead(url);
        //更新用户信息
        if (!userDao.update(user)) throw new FileFailedException(Code.FILEDAILED_ERR, "文件上传出现问题");
        logger.info(user.getUserName() + msg);
        return true;
    }

    @Override
    public boolean storeImgFileUp(List<MultipartFile> files, List<Long> imgIds, Long skuId, String token) {
        if (skuId == 0) throw new FileFailedException(Code.FILEUP_ERR, "skuId不能为空");
        if (files.size() > 10) throw new FileFailedException(Code.FILEUP_ERR, "上传的图片数量不能大于10张");
        if (skuDao.queryById(skuId) == null) throw new CURDException(Code.SELECT_ERR, "没有找到对应的sku商品");
        //遍历图片大小
        for (MultipartFile file : files) {
            long maxSize = 3145728L;
            if (file.getSize() > maxSize) throw new FileFailedException(Code.FILEUP_ERR, "文件过大，请重新选择小于3MB的文件");
        }
        Claims claims = jwtUtil.parseToken(token);
        long timeStamp = System.currentTimeMillis();
        String paths;
        //imgIds等于0，代表使用的是默认值0，全部是第一次上传的文件。
        if (!(imgIds.get(0) == 0)) {
            //使用迭代器把前几个要修改的文件先修改然后在删除,剩下的就都是创建文件的操作了
            Iterator<MultipartFile> iterator = files.iterator();
            for (int i = 0; i < imgIds.size(); i++) {
                MultipartFile nextFile = iterator.next();
                paths = "images/" + "skuId_" + skuId + nextFile + timeStamp + ".jpg";
                SkuImages skuImages = skuImagesDao.queryById(imgIds.get(i));
                if (!FileUtil.deleteFile(skuImages.getImgPath()))
                    throw new FileFailedException(Code.FILEDAILED_ERR, "文件删除失败");
                skuImages.setImgPath(paths);
                if (!skuImagesDao.updateByImageIdAndSkuId(skuImages))
                    throw new CURDException(Code.SAVE_ERR, "图片路径存储异常");
                if (!FileUtil.saveFile(nextFile, paths)) throw new FileFailedException(Code.FILEUP_ERR, "图片存储出现异常");
                iterator.remove();
            }
        }
        //执行创建文件的操作
        for (int i = 0; i < files.size(); i++) {
            paths = "images/" + "skuId_" + skuId + files.get(i) + timeStamp + ".jpg";
            if (!FileUtil.saveFile(files.get(i), paths)) throw new FileFailedException(Code.FILEUP_ERR, "图片存储出现异常");
            if (!skuImagesDao.save(new SkuImages(null, skuId, paths)))
                throw new CURDException(Code.SAVE_ERR, "图片路径存储异常");
        }
        if ((imgIds.get(0) == 0)) {
            logger.info(claims.get("userName") + "上传了 " + files.size() + " 张商品图片");
        } else {
            logger.info(claims.get("userName") + "上传了 " + files.size() + " 张商品图片" + " 修改了 " + imgIds.size() + " 张商品图片");
        }
        return true;
    }

    @Override
    public boolean storeImgDelete(ImageDeleteEntity imageDeleteEntity, String token) {
        Long skuId = imageDeleteEntity.getSkuId();
        List<Long> imgIds = imageDeleteEntity.getImgIds();
        Sku sku = null;
        if ((sku = skuDao.queryById(skuId)) == null) throw new CURDException(Code.SELECT_ERR, "没有找到对应的sku商品");
        List<SkuImages> skuImagesList = sku.getSkuImagesList();
        List<SkuImages> deleteList = new ArrayList<>();
        for (SkuImages skuImages : skuImagesList) {
            for (Long imgId : imgIds) {
                if (skuImages.getImgId().equals(imgId)) deleteList.add(skuImages);
            }
        }
        for (SkuImages skuImages : deleteList) {
            if (!skuImagesDao.delete(skuImages.getImgId())) throw new CURDException(Code.DELETE_ERR, "店铺图片删除失败");
            if (!FileUtil.deleteFile(skuImages.getImgPath()))
                throw new FileFailedException(Code.FILEDAILED_ERR, "店铺图片删除失败");
        }
        return true;
    }

    @Override
    public boolean spuImgFileUp(MultipartFile file, Long spuId, String token) {
        long maxSize = 5242880L;
        if (spuId == 0) throw new FileFailedException(Code.FILEDAILED_ERR,"spuId不能为空");
        if (file.getSize() > maxSize) throw new FileFailedException(Code.FILEUP_ERR, "文件大小过大，请重新选择小于5MB的文件");
        Claims claims = jwtUtil.parseToken(token);
        String userName = (String) claims.get("userName");
        long isTime = System.currentTimeMillis();
        String path = "images/" + "spu" + userName + isTime + ".jpg";
        SpuImages spuImages = spuImagesDao.queryBySpuId(spuId);
        if (spuImages == null) {
            SpuImages newSpuImages = new SpuImages(null, spuId, path);
            if (!spuImagesDao.insert(newSpuImages)) throw new CURDException(Code.SAVE_ERR, "店铺图片添加失败");
        }else {
            if (!FileUtil.deleteFile(spuImages.getImgPath())) throw new FileFailedException(Code.FILEDAILED_ERR, "店铺图片删除失败");;
            spuImages.setImgPath(path);
            if (!spuImagesDao.updateByImageIdAndSpuId(spuImages))throw new CURDException(Code.SAVE_ERR, "店铺图片修改失败");
        }
        if (!FileUtil.saveFile(file, path)) throw new FileFailedException(Code.FILEUP_ERR, "店铺图片创建失败");
        return true;
    }

    @Override
    public boolean spuImgFileDelete(Long imgId, String token) {
        SpuImages spuImages = spuImagesDao.queryByImgId(imgId);
        if (spuImages == null) throw new CURDException(Code.DELETE_ERR,"找不到imgId为" + imgId + " 的图片");
        String imgPath = spuImages.getImgPath();
        if (!FileUtil.deleteFile(imgPath)) throw new FileFailedException(Code.FILEDAILED_ERR,"找不到imgId为" + imgId + " 的图片");
        if (!spuImagesDao.delete(imgId)) throw new CURDException(Code.DELETE_ERR, "店铺图片删除失败");
        return true;
    }


}

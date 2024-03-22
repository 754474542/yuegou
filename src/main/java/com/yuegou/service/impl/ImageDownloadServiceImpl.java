package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.controller.pretreatment.exceptionhandle.FileFailedException;
import com.yuegou.controller.pretreatment.exceptionhandle.NullValueException;
import com.yuegou.dao.SkuDao;
import com.yuegou.dao.SkuImagesDao;
import com.yuegou.dao.UserDao;
import com.yuegou.entity.SkuImages;
import com.yuegou.entity.User;
import com.yuegou.service.ImageDownloadService;
import com.yuegou.service.SkuImagesService;
import com.yuegou.service.SkuService;
import com.yuegou.service.UserService;
import com.yuegou.utils.FileUtil;
import com.yuegou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private JwtUtil jwtUtil;

    @Override
    public boolean userHeadFileUp(MultipartFile file,String token) {
        long maxSize = 3145728L;
        if (file.getSize() > maxSize) throw new FileFailedException(Code.FILEUP_ERR,"文件大小过大，请重新选择文件");
        String msg = "头像上传成功";
        if (file.isEmpty()) throw new NullValueException(Code.NULLVALUE_ERR,"上传文件失败，请重新上传");
        Claims claims = jwtUtil.parseToken(token);
        //生成保存url
        String url = "images/" + claims.get("userName") + "head" + System.currentTimeMillis() + ".jpg";
        User user = userDao.getUserName(new User((String) claims.get("userName")));
        String userHead = user.getUserHead();
        //判断有没有头像，如果有删除掉
        if (userHead != null){
            File oldHead = new File(userHead);
            if (oldHead.isFile()){
                boolean delete = oldHead.delete();
                if (delete) logger.info(" 删除了" + userHead);
                msg = "头像修改成功";
            }
        }
        //进行头像上传
        if (!FileUtil.saveFile(file,url)) throw new FileFailedException(Code.FILEDAILED_ERR,"文件上传出现异常");
        user.setUserHead(url);
        //更新用户信息
        if (!userDao.update(user)) throw new FileFailedException(Code.FILEDAILED_ERR,"文件上传出现问题");
        logger.info(user.getUserName() + msg);
        return true;
    }

    @Override
    public boolean storeImgFileUp(List<MultipartFile> files, List<Long> imgIds, Long skuId, String token) {
        if (skuDao.queryById(skuId) == null) throw new CURDException(Code.SELECT_ERR,"没有找到对应的sku商品");
        //遍历图片大小
        for (MultipartFile file : files) {
            long maxSize = 3145728L;
            if (file.getSize() > maxSize) throw new FileFailedException(Code.FILEUP_ERR,"文件过大，请重新选择文件");
        }
        if (files.size() != imgIds.size()) throw new FileFailedException(Code.FILEUP_ERR,"请确保前端发送过来的图片id数量和文件数量相等");
        Claims claims = jwtUtil.parseToken(token);
        //获取当前时间戳
        String paths;
        long timeStamp = System.currentTimeMillis();
        //图片新增/修改
        for (int i = 0; i < files.size(); i++) {
            paths = "images/" + claims.get("userName") + (i+1) + "store" +  timeStamp + ".jpg";
            SkuImages skuImages = skuImagesDao.queryByImgIdAndSkuId(new SkuImages(imgIds.get(i), skuId, null));
            //如果这里等于空代表数据库里没有这条数据，要新增数据。
            if (skuImages == null ){
                logger.info("新增了图片");
                if(!skuImagesDao.save(new SkuImages(null,skuId,paths))) throw new CURDException(Code.SAVE_ERR, "用户数据保存异常");
                if(!FileUtil.saveFile(files.get(i),paths)) throw new FileFailedException(Code.FILEUP_ERR,"文件上传异常");
                continue;
            }
            //这里是数据库找到了相应的数据，进行数据删除和更新
            logger.info("修改了图片" + imgIds.get(i));
            if (!new File(skuImages.getImgPath()).delete()) throw new FileFailedException(Code.FILEDAILED_ERR,"文件删除失败");
            skuImages.setImgPath(paths);
            if(!skuImagesDao.updateByImageIdAndSkuId(new SkuImages(skuImages.getImgId(),skuId,paths))) throw new CURDException(Code.SAVE_ERR, "用户数据保存异常");
            if(!FileUtil.saveFile(files.get(i),paths)) throw new FileFailedException(Code.FILEUP_ERR,"文件上传异常");
        }
        return true;
    }

}

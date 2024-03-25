package com.yuegou.service.timetacks;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.BanDao;
import com.yuegou.dao.SpuImagesDao;
import com.yuegou.dao.UserDao;
import com.yuegou.entity.Ban;
import com.yuegou.entity.SpuImages;
import com.yuegou.entity.User;
import com.yuegou.service.BanService;
import com.yuegou.utils.ViewUtile;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ProjectTasks {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BanDao banDao;
    @Autowired
    private Logger logger;
    @Autowired
    private ViewUtile viewUtile;

    private List<SpuImages> bannerList = new ArrayList<>();

    public List<SpuImages> getBannerList() {
        return bannerList;
    }

    @Scheduled(cron = "${utils.projecttasks.loginEmptCleanTime}")
//    @Scheduled(fixedDelay = 1000)
    private void loginEmptClean(){
        logger.info("执行登录检测");
        List<User> emptTimeUser = userDao.getEmptTimeUser();
        for (User user : emptTimeUser) {
            Date emptCreate = user.getEmptCreate();
            LocalDateTime dt = emptCreate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            Duration limit = Duration.ofMinutes(10);
            if (LocalDateTime.now().isAfter(dt.plus(limit))){
                user.setUserEmpt(0);
                userDao.setEmpt(user);
                logger.info(user.getUserName() + " 被解除登录限制");
            }
        }
    }

    @Scheduled(cron = "${utils.projecttasks.detectionUnBanTime}")
    private void detectionUnBan(){
        logger.info("执行封号检测");
        List<Ban> bans = banDao.queryAll();
        for (Ban ban : bans) {
            LocalDateTime banUntime = ban.getBanUntime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime date = LocalDateTime.now();
            if (date.isAfter(banUntime)){
                User byId = userDao.getById(ban.getUserId());
                boolean b = banDao.deleteById(ban.getUserId());
                if (b){
                    logger.info("用户：" + byId.getUserName() + "解封");
                }else {
                    logger.error("用户" + byId.getUserName() + "解封失败，请联系管理员");
                }
            }
        }
    }

    @Scheduled(cron = "${utils.projecttasks.bannerCarousel}")
    private void bannerCarousel(){
        logger.info("执行轮播图更换");
        if ((bannerList=viewUtile.getSpuImages()) == null) throw new CURDException(Code.UPDATE_ERR,"后端设置轮播图失败，请联系管理员");
    }

    @PostConstruct
    private void ServiceStart(){
        logger.info("固定当日轮播图列表");
        if ((bannerList=viewUtile.getSpuImages()) == null) throw new CURDException(Code.UPDATE_ERR,"后端设置轮播图失败，请联系管理员");
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    private void demo(){
        logger.info("还请仔细查看代码");
    }

}

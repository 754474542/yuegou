package com.yuegou.service.timetacks;

import com.yuegou.dao.UserDao;
import com.yuegou.entity.Ban;
import com.yuegou.entity.User;
import com.yuegou.service.BanService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class ProjectTasks {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BanService banService;
    @Autowired
    private Logger logger;

    //每十分钟检测，满足条件解除登录限制
    @Scheduled(cron = "0 0/10 * * * *")
    private void loginEmptClean(){
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

    //每天凌晨5分执行
    @Scheduled(cron = "0 5 0 * * *")
    private void detectionUnBan(){
        List<Ban> bans = banService.queryAll();
        for (Ban ban : bans) {
            LocalDateTime banUntime = ban.getBanUntime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime date = LocalDateTime.now();
            if (date.isAfter(banUntime)){
                User byId = userDao.getById(ban.getUserId());
                boolean b = banService.deleteById(ban.getUserId());
                if (b){
                    logger.info("用户：" + byId.getUserName() + "解封");
                }else {
                    logger.error("用户" + byId.getUserName() + "解封失败，请联系管理员");
                }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    private void demo(){
        logger.info("还请仔细查看代码");
    }

}

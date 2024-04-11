package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.*;
import com.yuegou.entity.*;
import com.yuegou.service.OrderService;
import com.yuegou.utils.FileUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private DetailDao detailDao;
    @Autowired
    private SpuDao spuDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private Logger logger;

    @Value("${utils.imagessavepath}")
    private String path;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Long orderId) {
        return this.orderDao.queryById(orderId);
    }

    @Override
    public List<Order> queryByUserId(Integer size,Integer offset,Long userId,Integer orderStatus) {
        List<Order> orders = orderDao.queryByUserId(size,offset,userId,orderStatus);
        for (Order order : orders) {
            List<Detail> detailList = order.getDetailList();
            for (Detail detail : detailList) {
                Spu spu = detail.getSpu();
                SpuImages spuImages = spu.getSpuImages();
                if (spuImages.getIndexImgPath() != null){
                    if (spuImages.getIndexImgPath() != null)spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
                }
            }
        }
        return orders;
    }

    @Override
    public List<Order> queryAll(Integer size,Integer offset) {
        return orderDao.queryAll(size,offset);
    }

    @Override
    public boolean insert(OrderAndDetail orderAndDetail) {
        Date isTime = new Date();
        Order order = orderAndDetail.getOrder();
        order.setOrderStatus(0);
        order.setCreateTime(isTime);
        if (!orderDao.insert(order)) throw new CURDException(Code.SAVE_ERR,"订单保存失败");
        List<Detail> detailList = orderAndDetail.getDetailList();
        for (Detail detail : detailList) {
            if (detail.getCartId() != null) if (!cartDao.deleteById(detail.getCartId())) throw new CURDException(Code.DELETE_ERR,"购物车商品删除失败");
            detail.setOrderId(order.getOrderId());
            double money = calPrice(detail.getSkuId(), detail.getNumber(),null);
            detail.setPrice(money);
            detail.setDetailStatus(0);
            if (!detailDao.insert(detail)) throw new CURDException(Code.SAVE_ERR,"订单详细保存失败");
        }
        return true;
    }


    @Override
    public boolean update(OrderAndDetail orderAndDetail) {
        Order order = orderAndDetail.getOrder();
        List<Detail> detailList = orderAndDetail.getDetailList();
        if (!orderDao.update(order)) throw new CURDException(Code.UPDATE_ERR,"订单更新失败");
        for (Detail detail : detailList) {
            double money = calPrice(detail.getSkuId(), detail.getNumber(),detail.getDetailId());
            detail.setPrice(money);
            if (!detailDao.update(detail)) throw new CURDException(Code.UPDATE_ERR,"订单详情更新失败");
        }
        return true;
    }

    @Override
    public boolean deleteById(Long orderId) {
        Order order = orderDao.queryById(orderId);
        List<Detail> detailList = order.getDetailList();
        if (!orderDao.deleteById(order.getOrderId())) throw new CURDException(Code.DELETE_ERR,"删除订单失败");
        for (Detail detail : detailList) {
            if (!detailDao.deleteById(detail.getDetailId())) throw new CURDException(Code.DELETE_ERR,"删除订单详情失败");
        }
        return true;
    }

    @Override
    public double calPrice(Long skuId,Integer number,Long detailId){
        Sku sku = skuDao.queryById(skuId);
        Spu spu = spuDao.queryBySpuId(sku.getSpuId());
        if (((Integer.parseInt(sku.getSkuFund()) - number)) < 0) throw new CURDException(Code.SAVE_ERR,"库存不足!");
        //判断，如果是修改订单的话，就要把之前买的商品回滚一下在继续计算
        if (detailId != null){
            Detail detail = detailDao.queryById(detailId);
            Sku oldSku = skuDao.queryById(detail.getSkuId());
            int isSkuFund = Integer.parseInt(oldSku.getSkuFund()) + detail.getNumber();
            oldSku.setSkuFund(Integer.toString(isSkuFund));
            if (!skuDao.update(oldSku)) throw new CURDException(Code.UPDATE_ERR,"sku回滚操作失败!");
            logger.info("事务回滚- 商品： " + oldSku.getSkuId() + " 库存增加 " +  detail.getNumber() + "现有库存: " + isSkuFund);
        }

        int skuFund = Integer.parseInt(sku.getSkuFund()) - number;
        logger.info("sku:" + sku.getSkuId() + " 被卖出 " + number + " 份" +"原有库存: " + sku.getSkuFund() + " 剩余库存: " + skuFund);
        sku.setSkuFund(Integer.toString(skuFund));
        if (!skuDao.update(sku)) throw new CURDException(Code.UPDATE_ERR,"sku库存操作失败!");

        BigDecimal skuPrice = BigDecimal.valueOf(sku.getSkuPrice());
        BigDecimal spuDiscount = BigDecimal.valueOf(spu.getDiscount()).divide(new BigDecimal(100));
        BigDecimal priceNumber = BigDecimal.valueOf(number);
        // 使用BigDecimal保留后两位小数，并进行精确运算并设置舍入模式为ROUND_DOWN，隔断算法
        return skuPrice.multiply(spuDiscount).multiply(priceNumber).setScale(2, RoundingMode.DOWN).doubleValue();
    }

}

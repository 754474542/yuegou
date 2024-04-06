package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.SkuDao;
import com.yuegou.entity.Detail;
import com.yuegou.dao.DetailDao;
import com.yuegou.entity.Sku;
import com.yuegou.entity.Spu;
import com.yuegou.entity.SpuImages;
import com.yuegou.service.DetailService;
import com.yuegou.utils.FileUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Detail)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Service("detailService")
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailDao detailDao;
    @Autowired
    private Logger logger;
    @Autowired
    private SkuDao skuDao;
    @Value("${utils.imagessavepath}")
    private String path;

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    @Override
    public Detail queryById(Long detailId) {
        return detailDao.queryById(detailId);
    }

    @Override
    public List<Detail> queryByOrderId(Long orderId) {
        return detailDao.queryByOrderId(orderId);
    }

    @Override
    public Detail queryBySkuIdAndSpuId(Detail detail) {
        return detailDao.queryBySkuIdAndSpuId(detail);
    }

    @Override
    public List<Detail> queryByStoreId(Integer size, Integer offset, Long storeId, Integer detailStatus) {
        List<Detail> details = detailDao.queryByStoreId(size, offset, storeId, detailStatus);
        for (Detail detail : details) {
            Spu spu = detail.getSpu();
            SpuImages spuImages = spu.getSpuImages();
            if (spuImages.getIndexImgPath() != null) spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
        }
        return details;
    }


    /**
     * 新增数据
     *
     * @param detail 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Detail detail) {
        detail.setDetailStatus(0);
        return detailDao.insert(detail);
    }

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long detailId) {
        return detailDao.deleteById(detailId);
    }

    @Override
    public boolean update(Detail detail) {
        if (detail.getDetailStatus() == 4){
            logger.info("执行退款，进行商品数量回滚");
            Sku sku = skuDao.queryById(detail.getSkuId());
            sku.setSkuFund(String.valueOf(Integer.parseInt(sku.getSkuFund()) + detail.getNumber()));
            if (!skuDao.update(sku)) throw new CURDException(Code.UPDATE_ERR,"商品数量回滚失败");
        }
        return detailDao.update(detail);
    }
}

package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.*;
import com.yuegou.service.SpuService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/spus")
public class SpuController {

    @Autowired
    private SpuService spuService;

    //这里一次性接收Spu,Attribute,因为他们之间互相有关联，所以放在一起存储。
    @PostMapping
    public Result saveSpuAndAttributeValues(@RequestBody SpuAndAttributeValues spuAndAttributeValues, @RequestHeader String token){
        boolean flag = spuService.saveSpuAndAttributeValues(spuAndAttributeValues,token);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    //这里也是接收spu，attribute，由于前端更改，上面接口过时，可以用，但不推荐。
    @PostMapping("/intersectSpuAndAttribute")
    public Result intersetSpu(@RequestBody SpuAndAttributeAllList spuAndAttributeAllList){
        boolean flag = spuService.intersectSpuAndAttribute(spuAndAttributeAllList);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = spuService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody Spu spu){
        boolean flag = spuService.update(spu);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @GetMapping
    public Result queryAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<Spu> spus = spuService.queryAll(size,offset);
        return new Result(spus != null ? Code.SELECT_OK : Code.SELECT_ERR, spus, spus != null ? "OK" : "Error");
    }

    @GetMapping("/{id}")
    public Result queryBySpuId(@PathVariable Long id){
        Spu spu = spuService.queryBySpuId(id);
        List<Attribute> attributeList = spu.getAttributeList();
        List<SpuAttributeValue> spuAttributeValueList = spu.getSpuAttributeValueList();
        return new Result(spu != null ? Code.SELECT_OK : Code.SELECT_ERR, spu, spu != null ? "OK" : "Error");
    }

    @GetMapping("/indexSpuList")
    public Result queryIndexPageList(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<Spu> spus = spuService.queryIndexPageList(size,offset);
        return new Result(spus != null ? Code.SELECT_OK : Code.SELECT_ERR, spus, spus != null ? "OK" : "Error");
    }

    @PostMapping("/searchSpu")
    public Result querySearchSpu(@RequestBody SpuSearchEntity spuSearchEntity){
        spuSearchEntity.setOffset(PaginationUtil.calculateOffset(spuSearchEntity.getPage(),spuSearchEntity.getSize()));
        List<Spu> spus = spuService.querySearchSpu(spuSearchEntity);
        return new Result(spus != null ? Code.SELECT_OK : Code.SELECT_ERR, spus, spus != null ? "OK" : "Error");
    }

    @GetMapping("/byStoreId")
    public Result queryByStoreId(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page,
                                 @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size,
                                 @RequestParam Long storeId,
                                 @RequestParam String search,
                                 @RequestParam Integer power){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<Spu> spus = spuService.queryByStoreId(size,offset,storeId,search,power);
        return new Result(spus != null ? Code.SELECT_OK : Code.SELECT_ERR, spus, spus != null ? "OK" : "Error");
    }

}

package com.yuegou.utils;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.OffSetException;

public class PaginationUtil {

    public static int calculateOffset(int page, int size){
        if (page < 1 || size <= 0) throw new OffSetException(Code.OFFSET_ERR,"偏移量计算错误");
        return (page -1) * size;
    }

}

package com.vilce.springboot_vue.utils;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: operate
 * @Package: com.vilce.springboot_vue
 * @Author: 雷才哲
 * @Date: 2021/4/22 下午1:27
 * @Version: 1.0
 */
public class PageUtil {
    /**
     * 开始分页
     *
     * @param list
     * @param pageIndex 页码
     * @param pageSize  每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageIndex, Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount = 0;
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }
        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;
        if (pageIndex != pageCount) {
            fromIndex = (pageIndex - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageIndex - 1) * pageSize;
            toIndex = count;
        }
        List pageList = list.subList(fromIndex, toIndex);
        return pageList;
    }
}

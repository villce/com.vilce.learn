package com.vilce.common.utils;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.ObjectSizeUtil
 * @Author: 雷才哲
 * @Date: 2020/11/27 15:16
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class ObjectSizeUtil {
    /**
     * @Description 计算对象占用内存大小，单位：字节
     * @Version  1.0
     */
    public static long getObjectSize(Object o){
        if(null == o){
            return 0;
        }
        byte[] obyte = JSONUtils.toByteArray(o);
        return obyte.length;
    }
    /**
     * @Description 计算对象占用内存大小，数值后带单位：GB, MB, KB or bytes
     * @Version  1.0
     */
    public static String getObjectSizeUnit(Object o){
        return FileUtils.byteCountToDisplaySize(getObjectSize(o));
    }
}

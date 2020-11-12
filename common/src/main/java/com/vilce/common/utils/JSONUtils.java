package com.vilce.common.utils;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.vilce.common.model.log.utils.LoggerUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description: 重新封装JSON转换工具
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.JSONUtils
 * @Author: 雷才哲
 * @Date: 2019/12/5 14:59
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class JSONUtils {

    private static ObjectMapper om = new ObjectMapper();

    static {
        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        om.setSerializationInclusion(Include.ALWAYS);
        //取消默认转换timestamps
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        // 设置Date类型的序列化及反序列化格式
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略空Bean转json的错误
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 注册一个时间序列化及反序列化的处理模块，用于解决jdk8中localDateTime等的序列化问题
        om.registerModule(new JavaTimeModule());
    }

    /**
     * 对象转json字符串，支持List、Map、Collection、字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json = null;
        if (ObjectUtils.isNotEmpty(obj)) {
            try {
                json = om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                LoggerUtils.error(JSONUtils.class, e.toString());
            }
        }
        return json;
    }

    /**
     * 带格式化，对象转换为json字符串,支持List、Map、Collection、字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJsonPretty(T obj){
        String json = null;
        if (ObjectUtils.isNotEmpty(obj)){
            try {
                json = om.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                LoggerUtils.error(JSONUtils.class,e.toString());
            }
        }
        return json;
    }

    /**
     * json字符串转换为java对象,支持List、Map、Collection、字符串
     *
     * @param json 源json串
     * @param <T> 泛型
     */
    public static <T> T toJavaBean(String json, Class<T> responseType) {
        try {
            return om.readValue(json, responseType);
        } catch (JsonParseException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
            return null;
        } catch (JsonMappingException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
            return null;
        } catch (IOException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
            return null;
        }
    }

    /**
     * @Description json字符串转换为java对象,支持List、Map、Collection、字符串
     * @Version  1.0
     */
    public static <T> T toJavaBean(File file, Class<T> responseType){
        try {
            return om.readValue(file, responseType);
        } catch (JsonMappingException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
            return null;
        } catch (JsonParseException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
            return null;
        } catch (IOException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
            return null;
        }
    }

    /**
     * 示例1：
     *         List<Map<Integer, String>> list = Lists.newArrayList();
     *         Map<Integer, String> map = Maps.newHashMap();
     *         map.put(12, "gg");
     *         map.put(34, "sd");
     *         list.add(map);
     *         List<Map<Integer, String>> list1 = toJavaBean(JSONUtils.toJSONString(list), ArrayList.class, HashMap.class);
     * 示例2：
     *         Map<Integer, String> map = Maps.newHashMap();
     *         map.put(12, "gg");
     *         map.put(34, "sd");
     *         Map<Integer, String> map1 = toJavaBean(JSONUtils.toJSONString(map), HashMap.class, Integer.class, String.class);
     * @param jsonString JSON字符串
     * @param parametrized 数据类型最外层class或者泛型实际的class, 如List<Map<String, Integer>>的List.class 或者Map<String, Integer>中的Map.class
     * @param parameterClasses 参数内部类型，如List<Map<String, Object>中的Map.class 或者Map<String, Integer>中的String.class、Integer.class
     * @param <T>
     * @return
     */
    public static <T> T toJavaBean(String jsonString, Class<?> parametrized, Class<?>... parameterClasses){
        try{
            JavaType javaType = javaType(parametrized, parameterClasses);
            return om.readValue(jsonString, javaType);
        } catch (JsonParseException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        } catch (JsonMappingException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        } catch (IOException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        }
        return null;
    }
    /**
     *  示例1：
     *  Map<Integer, String> map = Maps.newHashMap();
     *  JavaType javaType = javaType(Map.class, Integer.class, String.class);
     *  示例2：
     *   List<Map<Integer, String>> list = Lists.newArrayList();
     *   Map<Integer, String> map = Maps.newHashMap();
     *   map.put(12, "gg");
     *   map.put(34, "sd");
     *   list.add(map);
     *   JavaType javaType = javaType(List.class, Map.class);
     * @param parametrized 实际的数据类型，即最外层数据类型List
     * @param parameterClasses 内部参数类型，即Set.class Bean.class
     * @return
     */
    public static JavaType javaType(Class<?> parametrized, Class<?>... parameterClasses){
        return om.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }
    /**
     * @Description 将对象写入文件
     * @Version  1.0
     */
    public static boolean writeToFile(File file, Object o){
        try{
            om.writeValue(file, o);
            return true;
        } catch (JsonMappingException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        } catch (JsonGenerationException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        } catch (IOException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        }
        return false;
    }
    /**
     * @Description 格式化，将对象写入文件
     * @Version  1.0
     */
    public static boolean writeToFilePretty(File file, Object o){
        try{
            om.writerWithDefaultPrettyPrinter().writeValue(file, o);
            return true;
        } catch (JsonMappingException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        } catch (JsonGenerationException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        } catch (IOException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        }
        return false;
    }

    /**
     * 将对象转换为字节数组
     * @param value
     * @return
     */
    public static byte[] toByteArray(Object value){
        if(value == null){
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        try{
            return om.writeValueAsBytes(value);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            LoggerUtils.error(JSONUtils.class, e.toString());
        }
        return ArrayUtils.EMPTY_BYTE_ARRAY;
    }
}

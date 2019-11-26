package com.vilce.annotation.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.model.vo.MyResult
 * @Author: 雷才哲
 * @Date: 2019/11/25 16:07
 * @Version: 1.0
 */
@Data
public class MyResult {
    private Integer status;
    private String msg;
    private List<Object> data;

    public MyResult(Integer status, String msg, List<Object> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static MyResult OK(String msg, List<Object> data) {
        return new MyResult(200, msg, data);
    }

    public static MyResult Error(Integer status, String msg) {
        return new MyResult(status, msg, null);
    }
}

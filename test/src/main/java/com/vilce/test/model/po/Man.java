package com.vilce.test.model.po;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.model.po.Man
 * @Author: 雷才哲
 * @Date: 2020/7/17 17:58
 * @Version: 1.0
 */
public class Man extends User {
    private int flag;
    private Man() {
        super("lcz","123");
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

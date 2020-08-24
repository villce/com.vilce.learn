package com.vilce.test.model.po;

import javafx.scene.input.Mnemonic;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.model.po.Man
 * @Author: 雷才哲
 * @Date: 2020/7/17 17:58
 * @Version: 1.0
 */
@Data
public class Man extends User {
    private int flag;
    private Man() {
        super("lcz");
    }
}

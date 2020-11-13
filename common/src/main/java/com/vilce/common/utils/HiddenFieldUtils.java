package com.vilce.common.utils;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 字段隐藏工具类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.HiddenFieldUtils
 * @Author: 雷才哲
 * @Date: 2020/11/13 14:05
 * @Version: 1.0
 */
public class HiddenFieldUtils {
    /**
     * @Description 隐藏手机号码中间四位
     * @Version  1.0
     */
    public static String hiddenAccountId(String accountId){
        if(StringUtils.isEmpty(accountId)){
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "账号不可以为空");
        }
        return RegExUtils.replacePattern(accountId,"(\\d{1})\\d{6}(\\d{1})", "$1******$2");
    }
    /**
     * @Description 隐藏手机号码中间四位
     * @Version  1.0
     */
    public static String hiddenPhoneNum(String phone){
        if(StringUtils.isEmpty(phone)){
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "手机号码不可以为空");
        }
        //$1 $2 表示正则表达式里面的第一个和第二个，也就是括号里面的内容
        return RegExUtils.replacePattern(phone,"(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 隐藏身份证号码
     * @param cardNum
     * @return
     */
    public static String hiddenIdCardNum(String cardNum){
        if(StringUtils.isEmpty(cardNum)){
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "身份证号码不可以为空");
        }
        if(cardNum.length() != 15 && cardNum.length() != 18){
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "身份证号码位数必须为15位或者18位");
        }
        if(cardNum.length() == 18){
            return RegExUtils.replacePattern(cardNum, "(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
        }
        return RegExUtils.replacePattern(cardNum, "(\\d{4})\\d{7}(\\d{4})", "$1*******$2");
    }
}

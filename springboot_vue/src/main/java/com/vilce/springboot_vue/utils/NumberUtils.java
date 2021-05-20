package com.vilce.springboot_vue.utils;

import com.vilce.common.model.log.utils.LoggerUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @description: 数值处理工具类
 * @projectName: com.eastmoney.emis.activity
 * @package: com.eastmoney.emis.activity.utils.EmisNumberUtils
 * @author: 韩庆瑞
 * @date: 2019/9/10 4:25 PM
 * @version: 1.0
 */
public class NumberUtils extends org.springframework.util.NumberUtils {
    private static final double ONE_HUNDRED = 100.0;
    private static final double ONE_THOUSAND = 1000.0;
    private static final double TEN_THOUSAND = 10000.0;
    private static final double ONE_HUNDRED_MILLION = 100000000.0;
    private static final String TEN_THOUSAND_UNIT = "万";
    private static final String ONE_HUNDRED_MILLION_UNIT = "亿";

    /**
     * 将数字转换成以万为单位或者以亿为单位，因为在前端数字太大显示有问题
     */
    public static String amountConversion(String amount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            String result = null;
            if (Double.parseDouble(amount) > ONE_HUNDRED_MILLION) {
                result = StringUtils.join(df.format(Double.parseDouble(amount) / ONE_HUNDRED_MILLION), ONE_HUNDRED_MILLION_UNIT);
            } else if (Double.parseDouble(amount) > TEN_THOUSAND) {
                result = StringUtils.join(df.format(Double.parseDouble(amount) / TEN_THOUSAND), TEN_THOUSAND_UNIT);
            } else {
                result = df.format(Double.parseDouble(amount));
            }
            return result;
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "转换为亿或万失败！"));
            return null;
        }
    }

    /**
     * 将数字转换成以万为单位，保留两位小数且去末位0
     */
    public static String conversionToTDel(String amount) {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            String result = null;
            if (Double.parseDouble(amount) > TEN_THOUSAND) {
                result = StringUtils.join(df.format(Double.parseDouble(amount) / TEN_THOUSAND), TEN_THOUSAND_UNIT);
            } else {
                result = df.format(Double.parseDouble(amount));
            }
            return result;
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "转换为万失败！"));
            return null;
        }
    }

    /**
     * 将数字转换成以亿为单位，保留4位小数
     */
    public static String conversionToB(String amount) {
        DecimalFormat df = new DecimalFormat("#0.0000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            return StringUtils.join(df.format(Double.parseDouble(amount) / ONE_HUNDRED_MILLION), '亿');
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "转换为亿失败！"));
            return null;
        }
    }

    /**
     * 对数据四舍五入取整数
     *
     * @param amount
     * @return
     */
    public static String rounding(String amount) {
        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            return df.format(Double.parseDouble(amount));
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "取整失败！"));
            return null;
        }
    }

    /**
     * 对数据四舍五入保留两位小数
     *
     * @param amount
     * @return
     */
    public static String retain2Decimals(String amount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            return df.format(Double.parseDouble(amount));
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "保留两位小数失败！"));
            return null;
        }
    }

    /**
     * 对数据四舍五入保留两位小数，并末位去0
     *
     * @param amount
     * @return
     */
    public static String retain2DecimalsDel(String amount) {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            return df.format(Double.parseDouble(amount));
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "保留两位小数失败！"));
            return null;
        }
    }

    /**
     * 对数据四舍五入保留三位小数
     *
     * @param amount
     * @return
     */
    public static String retain3Decimals(String amount) {
        DecimalFormat df = new DecimalFormat("#0.000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        try {
            return df.format(Double.parseDouble(amount));
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据：", amount, "保留三位小数失败！"));
            return null;
        }
    }

    /**
     * 转换为百分比
     *
     * @param num
     * @return
     */
    public static String getPercent(String num) {
        String result = null;
        if (!ObjectUtils.isEmpty(num)) {
            String amount = String.valueOf(Double.parseDouble(num) * 100);
            result = retain2DecimalsDel(amount);
        }
        return result;
    }

    /**
     * 将数据转换为百分比,四舍五入
     *
     * @param number 数据
     * @param scale  精度（保留小数位）
     * @return 0.12567->12.57%
     */
    public static String getPercent(String number, int scale) {
        if (StringUtils.isEmpty(number)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("0");
        for (int i = 0; i < scale; i++) {
            if (i == 0) {
                sb.append(".");
            }
            sb.append("0");
        }
        sb.append("%");

        DecimalFormat format = new DecimalFormat(sb.toString());
        return format.format(Double.valueOf(number));
    }

    /**
     * 四舍五入，保留指定位数的小数
     *
     * @param number 数据
     * @param scale  小数位
     * @return
     */
    public static String rounding(String number, int scale) {
        try {
            if (scale < 0) {
                scale = 0;
            }
            return new BigDecimal(number).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据【", number, "】保留【", scale, "】位小数异常，", e));
            return null;
        }
    }

    public static BigDecimal rounding(double number, int scale) {
        try {
            if (scale < 0) {
                scale = 0;
            }
            return new BigDecimal(number).setScale(scale, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            LoggerUtils.error(NumberUtils.class, StringUtils.join("数据【", number, "】保留【", scale, "】位小数异常，", e));
            return new BigDecimal(0);
        }
    }
}

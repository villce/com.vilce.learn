package com.vilce.consul.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.model.po.WageOrderRes
 * @Author: 雷才哲
 * @Date: 2019/12/19 20:09
 * @Version: 1.0
 */
@Data
@ApiModel(value = "工资单返参")
public class WageOrderRes {
    @ApiModelProperty(value = "系统物理主键")
    private int id;
    @ApiModelProperty(value = "用户id")
    private int uid;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "工号")
    private String code;
    @ApiModelProperty(value = "单号时间")
    private String order_time;
    @ApiModelProperty(value = "基本工资")
    private Double basic_wage;
    @ApiModelProperty(value = "岗位工资")
    private Double job_wage;
    @ApiModelProperty(value = "基准绩效")
    private Double benchmark_performance;
    @ApiModelProperty(value = "标准工资")
    private Double standard_salary;
    @ApiModelProperty(value = "固定津贴")
    private Double fixed_allowance;
    @ApiModelProperty(value = "技术专家岗位津贴")
    private Double technical_expert_allowance;
    @ApiModelProperty(value = "职级补贴")
    private Double rank_allowance;
    @ApiModelProperty(value = "固定奖金")
    private Double fixed_bonus;
    @ApiModelProperty(value = "浮动奖金")
    private Double floating_bonus;
    @ApiModelProperty(value = "工资共计")
    private Double total_wages;
    @ApiModelProperty(value = "考试津贴")
    private Double examination_allowance;
    @ApiModelProperty(value = "入/离/事假后工资")
    private Double on_off_after_leave_pay;
    @ApiModelProperty(value = "绩效调整")
    private Double performance_adjustment;
    @ApiModelProperty(value = "浮动津贴")
    private Double floating_allowance;
    @ApiModelProperty(value = "日常加班")
    private Double daily_overtime;
    @ApiModelProperty(value = "病假扣除")
    private Double sick_leave_deduction;
    @ApiModelProperty(value = "迟到/旷工扣款")
    private Double late_absentee_deductions;
    @ApiModelProperty(value = "其他增减")
    private Double other_increase_or_decrease;
    @ApiModelProperty(value = "产假扣款")
    private Double maternity_leave_deductions;
    @ApiModelProperty(value = "浮动奖金2")
    private Double floating_bonus2;
    @ApiModelProperty(value = "推荐奖")
    private Double recommendation_award;
    @ApiModelProperty(value = "产品体验/积分奖金")
    private Double product_experience_bonus;
    @ApiModelProperty(value = "应发工资")
    private Double payable;
    @ApiModelProperty(value = "社保")
    private Double social_security;
    @ApiModelProperty(value = "公积金")
    private Double provident_fund;
    @ApiModelProperty(value = "险金后工资")
    private Double post_insurance_pay;
    @ApiModelProperty(value = "子女教育")
    private Double education_for_children;
    @ApiModelProperty(value = "住房贷款")
    private Double home_loan;
    @ApiModelProperty(value = "住房租金")
    private Double housing_rent;
    @ApiModelProperty(value = "赡养老人")
    private Double support_for_the_elderly;
    @ApiModelProperty(value = "继续教育")
    private Double continuing_education;
    @ApiModelProperty(value = "递延保险")
    private Double deferred_insurance;
    @ApiModelProperty(value = "本月个税")
    private Double tax;
    @ApiModelProperty(value = "实发工资")
    private Double paid_wages;
    @ApiModelProperty(value = "图片地址")
    private String url;
}

package com.vilce.springboot_vue.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.WageOrder
 * @Author: 雷才哲
 * @Date: 2019/12/19 17:59
 * @Version: 1.0
 */
@Data
public class WageOrder {
    private Long eid;
    private String code;
    private String name;
    private Date order_time;
    private Double basic_wage;
    private Double job_wage;
    private Double benchmark_performance;
    private Double standard_salary;
    private Double fixed_allowance;
    private Double technical_expert_allowance;
    private Double rank_allowance;
    private Double fixed_bonus;
    private Double floating_bonus;
    private Double total_wages;
    private Double examination_allowance;
    private Double on_off_after_leave_pay;
    private Double performance_adjustment;
    private Double floating_allowance;
    private Double daily_overtime;
    private Double sick_leave_deduction;
    private Double late_absentee_deductions;
    private Double other_increase_or_decrease;
    private Double maternity_leave_deductions;
    private Double floating_bonus2;
    private Double recommendation_award;
    private Double product_experience_bonus;
    private Double payable;
    private Double social_security;
    private Double provident_fund;
    private Double post_insurance_pay;
    private Double education_for_children;
    private Double home_loan;
    private Double housing_rent;
    private Double support_for_the_elderly;
    private Double continuing_education;
    private Double deferred_insurance;
    private Double tax;
    private Double paid_wages;
}

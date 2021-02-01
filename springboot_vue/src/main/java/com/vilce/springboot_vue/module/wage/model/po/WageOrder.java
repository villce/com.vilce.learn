package com.vilce.springboot_vue.module.wage.model.po;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.wage.model.po.WageOrder
 * @Author: 雷才哲
 * @Date: 2019/12/19 17:59
 * @Version: 1.0
 */
public class WageOrder {
    @ApiModelProperty(value = "系统物理主键")
    private int id;
    @ApiModelProperty(value = "用户id")
    private int uid;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "工号")
    private String code;
    @ApiModelProperty(value = "单号时间")
    private Date order_time;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Double getBasic_wage() {
        return basic_wage;
    }

    public void setBasic_wage(Double basic_wage) {
        this.basic_wage = basic_wage;
    }

    public Double getJob_wage() {
        return job_wage;
    }

    public void setJob_wage(Double job_wage) {
        this.job_wage = job_wage;
    }

    public Double getBenchmark_performance() {
        return benchmark_performance;
    }

    public void setBenchmark_performance(Double benchmark_performance) {
        this.benchmark_performance = benchmark_performance;
    }

    public Double getStandard_salary() {
        return standard_salary;
    }

    public void setStandard_salary(Double standard_salary) {
        this.standard_salary = standard_salary;
    }

    public Double getFixed_allowance() {
        return fixed_allowance;
    }

    public void setFixed_allowance(Double fixed_allowance) {
        this.fixed_allowance = fixed_allowance;
    }

    public Double getTechnical_expert_allowance() {
        return technical_expert_allowance;
    }

    public void setTechnical_expert_allowance(Double technical_expert_allowance) {
        this.technical_expert_allowance = technical_expert_allowance;
    }

    public Double getRank_allowance() {
        return rank_allowance;
    }

    public void setRank_allowance(Double rank_allowance) {
        this.rank_allowance = rank_allowance;
    }

    public Double getFixed_bonus() {
        return fixed_bonus;
    }

    public void setFixed_bonus(Double fixed_bonus) {
        this.fixed_bonus = fixed_bonus;
    }

    public Double getFloating_bonus() {
        return floating_bonus;
    }

    public void setFloating_bonus(Double floating_bonus) {
        this.floating_bonus = floating_bonus;
    }

    public Double getTotal_wages() {
        return total_wages;
    }

    public void setTotal_wages(Double total_wages) {
        this.total_wages = total_wages;
    }

    public Double getExamination_allowance() {
        return examination_allowance;
    }

    public void setExamination_allowance(Double examination_allowance) {
        this.examination_allowance = examination_allowance;
    }

    public Double getOn_off_after_leave_pay() {
        return on_off_after_leave_pay;
    }

    public void setOn_off_after_leave_pay(Double on_off_after_leave_pay) {
        this.on_off_after_leave_pay = on_off_after_leave_pay;
    }

    public Double getPerformance_adjustment() {
        return performance_adjustment;
    }

    public void setPerformance_adjustment(Double performance_adjustment) {
        this.performance_adjustment = performance_adjustment;
    }

    public Double getFloating_allowance() {
        return floating_allowance;
    }

    public void setFloating_allowance(Double floating_allowance) {
        this.floating_allowance = floating_allowance;
    }

    public Double getDaily_overtime() {
        return daily_overtime;
    }

    public void setDaily_overtime(Double daily_overtime) {
        this.daily_overtime = daily_overtime;
    }

    public Double getSick_leave_deduction() {
        return sick_leave_deduction;
    }

    public void setSick_leave_deduction(Double sick_leave_deduction) {
        this.sick_leave_deduction = sick_leave_deduction;
    }

    public Double getLate_absentee_deductions() {
        return late_absentee_deductions;
    }

    public void setLate_absentee_deductions(Double late_absentee_deductions) {
        this.late_absentee_deductions = late_absentee_deductions;
    }

    public Double getOther_increase_or_decrease() {
        return other_increase_or_decrease;
    }

    public void setOther_increase_or_decrease(Double other_increase_or_decrease) {
        this.other_increase_or_decrease = other_increase_or_decrease;
    }

    public Double getMaternity_leave_deductions() {
        return maternity_leave_deductions;
    }

    public void setMaternity_leave_deductions(Double maternity_leave_deductions) {
        this.maternity_leave_deductions = maternity_leave_deductions;
    }

    public Double getFloating_bonus2() {
        return floating_bonus2;
    }

    public void setFloating_bonus2(Double floating_bonus2) {
        this.floating_bonus2 = floating_bonus2;
    }

    public Double getRecommendation_award() {
        return recommendation_award;
    }

    public void setRecommendation_award(Double recommendation_award) {
        this.recommendation_award = recommendation_award;
    }

    public Double getProduct_experience_bonus() {
        return product_experience_bonus;
    }

    public void setProduct_experience_bonus(Double product_experience_bonus) {
        this.product_experience_bonus = product_experience_bonus;
    }

    public Double getPayable() {
        return payable;
    }

    public void setPayable(Double payable) {
        this.payable = payable;
    }

    public Double getSocial_security() {
        return social_security;
    }

    public void setSocial_security(Double social_security) {
        this.social_security = social_security;
    }

    public Double getProvident_fund() {
        return provident_fund;
    }

    public void setProvident_fund(Double provident_fund) {
        this.provident_fund = provident_fund;
    }

    public Double getPost_insurance_pay() {
        return post_insurance_pay;
    }

    public void setPost_insurance_pay(Double post_insurance_pay) {
        this.post_insurance_pay = post_insurance_pay;
    }

    public Double getEducation_for_children() {
        return education_for_children;
    }

    public void setEducation_for_children(Double education_for_children) {
        this.education_for_children = education_for_children;
    }

    public Double getHome_loan() {
        return home_loan;
    }

    public void setHome_loan(Double home_loan) {
        this.home_loan = home_loan;
    }

    public Double getHousing_rent() {
        return housing_rent;
    }

    public void setHousing_rent(Double housing_rent) {
        this.housing_rent = housing_rent;
    }

    public Double getSupport_for_the_elderly() {
        return support_for_the_elderly;
    }

    public void setSupport_for_the_elderly(Double support_for_the_elderly) {
        this.support_for_the_elderly = support_for_the_elderly;
    }

    public Double getContinuing_education() {
        return continuing_education;
    }

    public void setContinuing_education(Double continuing_education) {
        this.continuing_education = continuing_education;
    }

    public Double getDeferred_insurance() {
        return deferred_insurance;
    }

    public void setDeferred_insurance(Double deferred_insurance) {
        this.deferred_insurance = deferred_insurance;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getPaid_wages() {
        return paid_wages;
    }

    public void setPaid_wages(Double paid_wages) {
        this.paid_wages = paid_wages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

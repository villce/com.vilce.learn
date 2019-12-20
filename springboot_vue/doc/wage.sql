CREATE TABLE vilce.wage_order
(
    code                       varchar2(255) NOT NULL,
    name                       varchar2(255) NOT NULL,
    order_time                 date          NOT NULL,
    basic_wage                 number(10, 2) NOT NULL,
    job_wage                   number(10, 2) NOT NULL,
    benchmark_performance      number(10, 2) NOT NULL,
    standard_salary            number(10, 2) NOT NULL,
    fixed_allowance            number(10, 2) DEFAULT NULL,
    technical_expert_allowance number(10, 2) DEFAULT NULL,
    rank_allowance             number(10, 2) DEFAULT NULL,
    fixed_bonus                number(10, 2) DEFAULT NULL,
    floating_bonus             number(10, 2) DEFAULT NULL,
    total_wages                number(10, 2) NOT NULL,
    examination_allowance      number(10, 2) DEFAULT NULL,
    on_off_after_leave_pay     number(10, 2) NOT NULL,
    performance_adjustment     number(10, 2) DEFAULT NULL,
    floating_allowance         number(10, 2) DEFAULT NULL,
    daily_overtime             number(10, 2) DEFAULT NULL,
    sick_leave_deduction       number(10, 2) DEFAULT NULL,
    late_absentee_deductions   number(10, 2) DEFAULT NULL,
    other_increase_or_decrease number(10, 2) DEFAULT NULL,
    maternity_leave_deductions number(10, 2) DEFAULT NULL,
    floating_bonus2            number(10, 2) DEFAULT NULL,
    recommendation_award       number(10, 2) DEFAULT NULL,
    product_experience_bonus   number(10, 2) DEFAULT NULL,
    payable                    number(10, 2) NOT NULL,
    social_security            number(10, 2) NOT NULL,
    provident_fund             number(10, 2) NOT NULL,
    post_insurance_pay         number(10, 2) NOT NULL,
    education_for_children     number(10, 2) DEFAULT NULL,
    home_loan                  number(10, 2) DEFAULT NULL,
    housing_rent               number(10, 2) DEFAULT NULL,
    support_for_the_elderly    number(10, 2) DEFAULT NULL,
    continuing_education       number(10, 2) DEFAULT NULL,
    deferred_insurance         number(10, 2) DEFAULT NULL,
    tax                        number(10, 2) NOT NULL,
    paid_wages                 number(10, 2) NOT NULL
);
--注释脚本
comment on table vilce.wage_order is '工资单';
comment on column vilce.wage_order.code is '工号';
comment on column vilce.wage_order.name is '姓名';
comment on column vilce.wage_order.order_time is '单号时间';
comment on column vilce.wage_order.basic_wage is '基本工资';
comment on column vilce.wage_order.job_wage is '岗位工资';
comment on column vilce.wage_order.benchmark_performance is '基准绩效';
comment on column vilce.wage_order.standard_salary is '标准工资';
comment on column vilce.wage_order.fixed_allowance is '固定津贴';
comment on column vilce.wage_order.technical_expert_allowance is '技术专家岗位津贴';
comment on column vilce.wage_order.rank_allowance is '职级补贴';
comment on column vilce.wage_order.fixed_bonus is '固定奖金';
comment on column vilce.wage_order.floating_bonus is '浮动奖金';
comment on column vilce.wage_order.total_wages is '工资共计';
comment on column vilce.wage_order.examination_allowance is '考试津贴';
comment on column vilce.wage_order.on_off_after_leave_pay is '入/离/事假后工资';
comment on column vilce.wage_order.performance_adjustment is '绩效调整';
comment on column vilce.wage_order.floating_allowance is '浮动津贴';
comment on column vilce.wage_order.daily_overtime is '日常加班';
comment on column vilce.wage_order.sick_leave_deduction is '病假扣除';
comment on column vilce.wage_order.late_absentee_deductions is '迟到/旷工扣款';
comment on column vilce.wage_order.other_increase_or_decrease is '其他增减';
comment on column vilce.wage_order.maternity_leave_deductions is '产假扣款';
comment on column vilce.wage_order.floating_bonus2 is '浮动奖金2';
comment on column vilce.wage_order.recommendation_award is '推荐奖';
comment on column vilce.wage_order.product_experience_bonus is '产品体验/积分奖金';
comment on column vilce.wage_order.payable is '应发工资';
comment on column vilce.wage_order.social_security is '社保';
comment on column vilce.wage_order.provident_fund is '公积金';
comment on column vilce.wage_order.post_insurance_pay is '险金后工资';
comment on column vilce.wage_order.education_for_children is '子女教育';
comment on column vilce.wage_order.home_loan is '住房贷款';
comment on column vilce.wage_order.housing_rent is '住房租金';
comment on column vilce.wage_order.support_for_the_elderly is '赡养老人';
comment on column vilce.wage_order.continuing_education is '继续教育';
comment on column vilce.wage_order.deferred_insurance is '递延保险';
comment on column vilce.wage_order.tax is '本月个税';
comment on column vilce.wage_order.paid_wages is '实发工资';

insert into vilce.wage_order(code, name, order_time, basic_wage, job_wage, benchmark_performance, standard_salary,
                             total_wages, on_off_after_leave_pay, floating_bonus2, payable, social_security,
                             provident_fund, post_insurance_pay, housing_rent, tax, paid_wages)
values ('190665','lcz',to_date('2019-8-15','yyyy-mm-dd'),2500,8700,3800,15000,15000,15000,500,15500,1575,1050,12875.00,3000,191.25,12683.75);
<template>
  <el-container style="height: 500px; border: 1px solid #B3C0D1">
<!--    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">-->
<!--&lt;!&ndash;      <el-menu :default-openeds="['1', '3']">&ndash;&gt;-->
<!--      <el-menu>-->
<!--        <el-submenu index="1">-->
<!--          <template slot="title"><i class="el-icon-message"></i>导航一</template>-->
<!--          <el-menu-item-group>-->
<!--            <template slot="title">分组一</template>-->
<!--            <el-menu-item index="1-1">选项1</el-menu-item>-->
<!--            <el-menu-item index="1-2">选项2</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-menu-item-group title="分组2">-->
<!--            <el-menu-item index="1-3">选项3</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-submenu index="1-4">-->
<!--            <template slot="title">选项4</template>-->
<!--            <el-menu-item index="1-4-1">选项4-1</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--        <el-submenu index="2">-->
<!--          <template slot="title"><i class="el-icon-menu"></i>导航二</template>-->
<!--          <el-menu-item-group>-->
<!--            <template slot="title">分组一</template>-->
<!--            <el-menu-item index="2-1">选项1</el-menu-item>-->
<!--            <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-menu-item-group title="分组2">-->
<!--            <el-menu-item index="2-3">选项3</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-submenu index="2-4">-->
<!--            <template slot="title">选项4</template>-->
<!--            <el-menu-item index="2-4-1">选项4-1</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--        <el-submenu index="3">-->
<!--          <template slot="title"><i class="el-icon-setting"></i>导航三</template>-->
<!--          <el-menu-item-group>-->
<!--            <template slot="title">分组一</template>-->
<!--            <el-menu-item index="3-1">选项1</el-menu-item>-->
<!--            <el-menu-item index="3-2">选项2</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-menu-item-group title="分组2">-->
<!--            <el-menu-item index="3-3">选项3</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-submenu index="3-4">-->
<!--            <template slot="title">选项4</template>-->
<!--            <el-menu-item index="3-4-1">选项4-1</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--      </el-menu>-->
<!--    </el-aside>-->

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item @click.native="addWageOrder()">新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>vilce</span>
      </el-header>
      <el-main>
        <wageOrder class="wageOrder-area" ref="wageOrderArea"></wageOrder>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import WageOrder from './WageOrder'
export default {
  name: 'WageIndex',
  components: {WageOrder},
  methods: {
    addWageOrder () {
      console.info('点击成功！')
      this.$refs.edit.form = {
        id: WageOrder.id,
        uid: WageOrder.uid,
        username: WageOrder.username,
        code: WageOrder.code,
        order_time: WageOrder.order_time,
        basic_wage: WageOrder.basic_wage,
        job_wage: WageOrder.job_wage,
        benchmark_performance: WageOrder.benchmark_performance,
        standard_salary: WageOrder.standard_salary,
        fixed_allowance: WageOrder.fixed_allowance,
        technical_expert_allowance: WageOrder.technical_expert_allowance,
        rank_allowance: WageOrder.rank_allowance,
        fixed_bonus: WageOrder.fixed_bonus,
        floating_bonus: WageOrder.floating_bonus,
        total_wages: WageOrder.total_wages,
        examination_allowance: WageOrder.examination_allowance,
        on_off_after_leave_pay: WageOrder.on_off_after_leave_pay,
        performance_adjustment: WageOrder.performance_adjustment,
        floating_allowance: WageOrder.floating_allowance,
        daily_overtime: WageOrder.daily_overtime,
        sick_leave_deduction: WageOrder.sick_leave_deduction,
        late_absentee_deductions: WageOrder.late_absentee_deductions,
        other_increase_or_decrease: WageOrder.other_increase_or_decrease,
        maternity_leave_deductions: WageOrder.maternity_leave_deductions,
        floating_bonus2: WageOrder.floating_bonus2,
        recommendation_award: WageOrder.recommendation_award,
        product_experience_bonus: WageOrder.product_experience_bonus,
        payable: WageOrder.payable,
        social_security: WageOrder.social_security,
        provident_fund: WageOrder.provident_fund,
        post_insurance_pay: WageOrder.post_insurance_pay,
        education_for_children: WageOrder.education_for_children,
        home_loan: WageOrder.home_loan,
        housing_rent: WageOrder.housing_rent,
        support_for_the_elderly: WageOrder.support_for_the_elderly,
        continuing_education: WageOrder.continuing_education,
        deferred_insurance: WageOrder.deferred_insurance,
        tax: WageOrder.tax,
        paid_wages: WageOrder.paid_wages
      }
    }
  }
}
</script>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-aside {
    color: #222222;
  }
  .wageOrder-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>

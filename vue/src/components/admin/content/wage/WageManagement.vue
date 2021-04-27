<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>工资管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <wage-edit @onSubmit="loadWageOrders()" ref="edit"></wage-edit>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="wageOrder"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-image
              style="margin:18px 0 0 30px;width:100px;height: 100px"
              :src="props.row.url"
              fit="url">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="order_time"
          label="日期"
          fit>
        </el-table-column>
        <el-table-column
          prop="username"
          label="姓名"
          width="100">
        </el-table-column>
        <el-table-column
          prop="payable"
          label="应发工资"
          fit>
        </el-table-column>
        <el-table-column
          prop="social_security"
          label="社保"
          width="120">
        </el-table-column>
        <el-table-column
          prop="provident_fund"
          label="公积金"
          fit>
        </el-table-column>
        <el-table-column
          prop="tax"
          label="本月个税"
          fit>
        </el-table-column>
        <el-table-column
          prop="paid_wages"
          label="实发工资"
          fit>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editWage(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteWage(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button>取消选择</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import WageEdit from './WageEdit'
  export default {
    name: 'WageManagement',
    components: {WageEdit},
    data () {
      return {
        wageOrder: []
      }
    },
    mounted: function () {
      this.loadWageOrders()
    },
    computed: {
      tableHeight () {
        return window.innerHeight - 320
      }
    },
    methods: {
      loadWageOrders () {
        var _this = this
        this.$axios.get('/wage/getAllWageOrder').then(resp => {
          if (resp && resp.status === 200) {
            if (resp.data.status === 0) {
              _this.wageOrder = resp.data.data
              console.info(_this.wageOrder)
            }
          }
        })
      },
      editWage (row) {
        this.$refs.edit.dialogFormVisible = true
        this.$refs.edit.form = {
          id: row.id,
          uid: row.uid,
          username: row.username,
          code: row.code,
          order_time: row.order_time,
          basic_wage: row.basic_wage,
          job_wage: row.job_wage,
          benchmark_performance: row.benchmark_performance,
          standard_salary: row.standard_salary,
          fixed_allowance: row.fixed_allowance,
          technical_expert_allowance: row.technical_expert_allowance,
          rank_allowance: row.rank_allowance,
          fixed_bonus: row.fixed_bonus,
          floating_bonus: row.floating_bonus,
          total_wages: row.total_wages,
          examination_allowance: row.examination_allowance,
          on_off_after_leave_pay: row.on_off_after_leave_pay,
          performance_adjustment: row.performance_adjustment,
          floating_allowance: row.floating_allowance,
          daily_overtime: row.daily_overtime,
          sick_leave_deduction: row.sick_leave_deduction,
          late_absentee_deductions: row.late_absentee_deductions,
          other_increase_or_decrease: row.other_increase_or_decrease,
          maternity_leave_deductions: row.maternity_leave_deductions,
          floating_bonus2: row.floating_bonus2,
          recommendation_award: row.recommendation_award,
          product_experience_bonus: row.product_experience_bonus,
          payable: row.payable,
          social_security: row.social_security,
          provident_fund: row.provident_fund,
          post_insurance_pay: row.post_insurance_pay,
          education_for_children: row.education_for_children,
          home_loan: row.home_loan,
          housing_rent: row.housing_rent,
          support_for_the_elderly: row.support_for_the_elderly,
          continuing_education: row.continuing_education,
          deferred_insurance: row.deferred_insurance,
          tax: row.tax,
          paid_wages: row.paid_wages,
          url: row.url
        }
      },
      deleteWage (id) {
        console.info(id)
        this.$confirm('此操作将永久删除该工资条, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .get('/wage/deleteWageOrder?id='+ id).then(resp => {
              if (resp && resp.data.status === 0) {
                this.$message({
                  type: 'info',
                  message: resp.data.message
                })
                this.loadWageOrders()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
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

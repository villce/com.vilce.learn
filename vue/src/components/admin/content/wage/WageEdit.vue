<template>
  <div style="text-align: left">
    <el-button class="add-button" type="success" @click="dialogFormVisible = true">添加工资条</el-button>
    <el-dialog
      title="添加/修改工资单"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form v-model="form" style="text-align: left" ref="dataForm">
        <el-form-item label="日期" :label-width="formLabelWidth" prop="order_time">
          <el-input v-model="form.order_time" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户id" :label-width="formLabelWidth" prop="uid">
          <el-input v-model="form.uid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工号" :label-width="formLabelWidth" prop="code">
          <el-input v-model="form.code" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="基本工资" :label-width="formLabelWidth" prop="basic_wage">
          <el-input v-model="form.basic_wage" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="岗位工资" :label-width="formLabelWidth" prop="job_wage">
          <el-input v-model="form.job_wage" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="基准绩效" :label-width="formLabelWidth" prop="benchmark_performance">
          <el-input v-model="form.benchmark_performance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="标准工资" :label-width="formLabelWidth" prop="standard_salary">
          <el-input v-model="form.standard_salary" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="固定津贴" :label-width="formLabelWidth" prop="fixed_allowance">
          <el-input v-model="form.fixed_allowance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="技术专家岗位津贴" :label-width="formLabelWidth" prop="technical_expert_allowance">
          <el-input v-model="form.technical_expert_allowance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="职级补贴" :label-width="formLabelWidth" prop="rank_allowance">
          <el-input v-model="form.rank_allowance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="固定奖金" :label-width="formLabelWidth" prop="fixed_bonus">
          <el-input v-model="form.fixed_bonus" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="浮动奖金" :label-width="formLabelWidth" prop="floating_bonus">
          <el-input v-model="form.floating_bonus" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工资共计" :label-width="formLabelWidth" prop="total_wages">
          <el-input v-model="form.total_wages" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="考试津贴" :label-width="formLabelWidth" prop="examination_allowance">
          <el-input v-model="form.examination_allowance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="入/离/事假后工资" :label-width="formLabelWidth" prop="on_off_after_leave_pay">
          <el-input v-model="form.on_off_after_leave_pay" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="绩效调整" :label-width="formLabelWidth" prop="performance_adjustment">
          <el-input v-model="form.performance_adjustment" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="浮动津贴" :label-width="formLabelWidth" prop="floating_allowance">
          <el-input v-model="form.floating_allowance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="日常加班" :label-width="formLabelWidth" prop="daily_overtime">
          <el-input v-model="form.daily_overtime" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="病假扣除" :label-width="formLabelWidth" prop="sick_leave_deduction">
          <el-input v-model="form.sick_leave_deduction" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="迟到/旷工扣款" :label-width="formLabelWidth" prop="late_absentee_deductions">
          <el-input v-model="form.late_absentee_deductions" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="其他增减" :label-width="formLabelWidth" prop="other_increase_or_decrease">
          <el-input v-model="form.other_increase_or_decrease" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="产假扣款" :label-width="formLabelWidth" prop="maternity_leave_deductions">
          <el-input v-model="form.maternity_leave_deductions" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="浮动奖金2" :label-width="formLabelWidth" prop="floating_bonus2">
          <el-input v-model="form.floating_bonus2" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="推荐奖" :label-width="formLabelWidth" prop="recommendation_award">
          <el-input v-model="form.recommendation_award"></el-input>
        </el-form-item>
        <el-form-item label="产品体验/积分奖金" :label-width="formLabelWidth" prop="product_experience_bonus">
          <el-input v-model="form.product_experience_bonus" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="应发工资" :label-width="formLabelWidth" prop="payable">
          <el-input v-model="form.payable" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="社保" :label-width="formLabelWidth" prop="social_security">
          <el-input v-model="form.social_security" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="公积金" :label-width="formLabelWidth" prop="provident_fund">
          <el-input v-model="form.provident_fund" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="险金后工资" :label-width="formLabelWidth" prop="post_insurance_pay">
          <el-input v-model="form.post_insurance_pay" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="子女教育" :label-width="formLabelWidth" prop="education_for_children">
          <el-input v-model="form.education_for_children" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="住房贷款" :label-width="formLabelWidth" prop="home_loan">
          <el-input v-model="form.home_loan" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="住房租金" :label-width="formLabelWidth" prop="housing_rent">
          <el-input v-model="form.housing_rent" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="赡养老人" :label-width="formLabelWidth" prop="support_for_the_elderly">
          <el-input v-model="form.support_for_the_elderly" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="继续教育" :label-width="formLabelWidth" prop="continuing_education">
          <el-input v-model="form.continuing_education" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="递延保险" :label-width="formLabelWidth" prop="deferred_insurance">
          <el-input v-model="form.deferred_insurance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="本月个税" :label-width="formLabelWidth" prop="tax">
          <el-input v-model="form.tax" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实发工资" :label-width="formLabelWidth" prop="paid_wages">
          <el-input v-model="form.paid_wages" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图片" :label-width="formLabelWidth" prop="url">
          <el-input v-model="form.url" autocomplete="off" placeholder="图片 URL"></el-input>
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import ImgUpload from '../ImgUpload'
  export default {
    name: 'WageEdit',
    components: {ImgUpload},
    data() {
      return {
        dialogFormVisible: false,
        form: {
          id: '',
          uid: '',
          username: '',
          code: '',
          order_time: '',
          basic_wage: '',
          job_wage: '',
          benchmark_performance: '',
          standard_salary: '',
          fixed_allowance: '',
          technical_expert_allowance: '',
          rank_allowance: '',
          fixed_bonus: '',
          floating_bonus: '',
          total_wages: '',
          examination_allowance: '',
          on_off_after_leave_pay: '',
          performance_adjustment: '',
          floating_allowance: '',
          daily_overtime: '',
          sick_leave_deduction: '',
          late_absentee_deductions: '',
          other_increase_or_decrease: '',
          maternity_leave_deductions: '',
          floating_bonus2: '',
          recommendation_award: '',
          product_experience_bonus: '',
          payable: '',
          social_security: '',
          provident_fund: '',
          post_insurance_pay: '',
          education_for_children: '',
          home_loan: '',
          housing_rent: '',
          support_for_the_elderly: '',
          continuing_education: '',
          deferred_insurance: '',
          tax: '',
          paid_wages: '',
          url: '',
        },
        formLabelWidth: '120px'
      }
    },
    methods: {
      clear() {
        this.$refs.imgUpload.clear()
        this.form = {
          id: '',
          uid: '',
          username: '',
          code: '',
          order_time: '',
          basic_wage: '',
          job_wage: '',
          benchmark_performance: '',
          standard_salary: '',
          fixed_allowance: '',
          technical_expert_allowance: '',
          rank_allowance: '',
          fixed_bonus: '',
          floating_bonus: '',
          total_wages: '',
          examination_allowance: '',
          on_off_after_leave_pay: '',
          performance_adjustment: '',
          floating_allowance: '',
          daily_overtime: '',
          sick_leave_deduction: '',
          late_absentee_deductions: '',
          other_increase_or_decrease: '',
          maternity_leave_deductions: '',
          floating_bonus2: '',
          recommendation_award: '',
          product_experience_bonus: '',
          payable: '',
          social_security: '',
          provident_fund: '',
          post_insurance_pay: '',
          education_for_children: '',
          home_loan: '',
          housing_rent: '',
          support_for_the_elderly: '',
          continuing_education: '',
          deferred_insurance: '',
          tax: '',
          paid_wages: '',
          url: '',
        }
      },
      onSubmit() {
        this.$axios
          .post('/wage/addOrUpdateWageOrder', {
            id: this.form.id,
            uid: this.form.uid,
            username: this.form.username,
            code: this.form.code,
            order_time: this.form.order_time,
            basic_wage: this.form.basic_wage,
            job_wage: this.form.job_wage,
            benchmark_performance: this.form.benchmark_performance,
            standard_salary: this.form.standard_salary,
            fixed_allowance: this.form.fixed_allowance,
            technical_expert_allowance: this.form.technical_expert_allowance,
            rank_allowance: this.form.rank_allowance,
            fixed_bonus: this.form.fixed_bonus,
            floating_bonus: this.form.floating_bonus,
            total_wages: this.form.total_wages,
            examination_allowance: this.form.examination_allowance,
            on_off_after_leave_pay: this.form.on_off_after_leave_pay,
            performance_adjustment: this.form.performance_adjustment,
            floating_allowance: this.form.floating_allowance,
            daily_overtime: this.form.daily_overtime,
            sick_leave_deduction: this.form.sick_leave_deduction,
            late_absentee_deductions: this.form.late_absentee_deductions,
            other_increase_or_decrease: this.form.other_increase_or_decrease,
            maternity_leave_deductions: this.form.maternity_leave_deductions,
            floating_bonus2: this.form.floating_bonus2,
            recommendation_award: this.form.recommendation_award,
            product_experience_bonus: this.form.product_experience_bonus,
            payable: this.form.payable,
            social_security: this.form.social_security,
            provident_fund: this.form.provident_fund,
            post_insurance_pay: this.form.post_insurance_pay,
            education_for_children: this.form.education_for_children,
            home_loan: this.form.home_loan,
            housing_rent: this.form.housing_rent,
            support_for_the_elderly: this.form.support_for_the_elderly,
            continuing_education: this.form.continuing_education,
            deferred_insurance: this.form.deferred_insurance,
            tax: this.form.tax,
            paid_wages: this.form.paid_wages,
            url: this.form.url
          }).then(resp => {
          if (resp && resp.data.status === 0) {
            this.dialogFormVisible = false
            this.$emit('onSubmit')
          }
          this.$message({
            type: 'info',
            message: resp.data.message
          })
        })
      },
      uploadImg() {
        this.form.url = this.$refs.imgUpload.url
      }
    }
  }
</script>

<style scoped>
  .add-button {
    margin: 18px 0 0 10px;
  }
</style>

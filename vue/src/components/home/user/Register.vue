<template>
  <body id="paper">
  <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">用户注册</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password2"
                auto-complete="off" placeholder="再次确认密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
export default{
  data () {
    return {
      rules: {
        username: [
          {required: true, message: '用户名不能为空', trigger: 'blur'},
          { type: 'string', max: 20, message: '长度不能超过20个字符！', trigger: 'blur' }
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          { type: 'string', min: 6, message: '长度不能小于6！', trigger: 'blur' }
        ]
      },
      checked: true,
      loginForm: {
        username: '',
        password: '',
        password2: ''
      },
      loading: false
    }
  },
  methods: {
    register () {
      var _this = this;
      if (this.loginForm.password === this.loginForm.password2) {
        this.$axios
          .post('/login/register', {
            username: this.loginForm.username,
            password: this.loginForm.password,
            name: this.loginForm.name,
            phone: this.loginForm.phone,
            email: this.loginForm.email
          })
          .then(resp => {
            if (resp.data.status === 0) {
              this.$alert('注册成功', '提示', {
                confirmButtonText: '确定'
              })
              _this.$router.replace('/login')
            } else {
              this.$alert(resp.data.message, '提示', {
                confirmButtonText: '确定'
              })
            }
          })
          .catch(failResponse => {})
      } else {
        this.loginForm.password = null;
        this.loginForm.password2 = null;
        this.$alert('密码输入不一致，请重新输入', '提示', {
          confirmButtonText: '确定'
        })
      }
    }
  }
}
</script>
<style>
  #paper {
    background:url("../../../assets/img/hzw/name/3.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>

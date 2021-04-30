<template>
  <el-card class="admin-header">
    <a href="/index">
      <img :src="userIcon" alt="" width="55px" style="float: left;margin-top: -5px;">
    </a>
    <span style="font-size: 32px;font-weight: bold;position:absolute;left: 100px">{{username}}</span>
    <i class="el-icon-switch-button" v-on:click="logout" style="font-size: 40px;float: right"></i>
  </el-card>
</template>

<script>
  import {createRouter} from '../../router'
  import {currentUser, logout} from "../../api/user/login";

  export default {
    name: 'Header',
    data() {
      return {
        userIcon: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        username: ''
      }
    },
    mounted() {
      this.currentUser();
    },
    methods: {
      currentUser() {
        currentUser().then(resp => {
          if (resp.status === 0) {
            if (resp.data !== null) {
              this.userIcon = resp.data.icon;
            }
          }
        })
      },
      logout () {
        logout().then(resp => {
          if (resp.status === 0) {
            this.$store.commit('logout');
            this.$router.replace('/index');
            // 清空路由，防止路由重复加载
            const newRouter = createRouter();
            this.$router.matcher = newRouter.matcher
          }
        }).catch(failResponse => {})
      }
    }
  }
</script>

<style scoped>
  .admin-header {
    height: 80px;
    opacity: 0.85;
    line-height: 40px;
    min-width: 900px;
  }
  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }
</style>

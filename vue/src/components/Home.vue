<template>
  <el-container class="home">
    <el-header style="text-align: center;height: 100px; margin-top: 100px">
      <div class="block">
        <el-avatar :size="70" :src="circleUrl" @click.native="handleAdmin"></el-avatar>
      </div>
    </el-header>
    <el-main>
      <el-row :gutter="10" type="flex" justify="center">
        <el-col :span="4" class="center-card">
          <el-card shadow="always" @click.native="handleJotter">
<!--            <img src="../assets/img/hzw/name/2.jpg" class="image">-->
            <div style="padding: 10px;font-size: 30px">
              <i class="el-icon-s-platform"></i>
              <span>博客</span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="4" class="center-card">
          <el-card shadow="always" @click.native="handleTool">
<!--            <img src="../assets/img/hzw/name/4.jpg" class="image">-->
            <div style="padding: 10px;;font-size: 30px">
              <i class="el-icon-s-cooperation"></i>
              <span>工具</span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="4" class="center-card">
          <el-card shadow="always" @click.native="handleSecret">
<!--            <img src="../assets/img/hzw/name/5.jpg" class="image">-->
            <div style="padding: 10px;;font-size: 30px">
              <i class="el-icon-camera-solid"></i>
              <span>秘密花园</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
  import {currentUser} from "../api/user/login";

  export default {
    data() {
      return {
        circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      }
    },
    mounted() {
      this.currentUser();
    },
    methods: {
      currentUser() {
        const username = this.$store.state.username;
        currentUser(username).then(resp => {
          if (resp.status === 0) {
            if (resp.data !== null) {
              this.circleUrl = resp.data.icon;
            }
          }
        })
      },
      handleJotter() {
        this.$router.replace({path: '/jotter'});
      },
      handleTool() {
        this.$router.replace({path: '/tool'});
      },
      handleAdmin() {
        this.$router.replace({path: '/admin'});
      },
      handleSecret() {
        this.$router.replace({path: '/secret'});
      }
    }
  }
</script>

<style scoped>
  .home {
    background-color: #283443;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }

  .image {
    width: 100%;
    display: block;
  }

  .center-card {
    font-weight: bold;
    font-size: large;
  }
</style>

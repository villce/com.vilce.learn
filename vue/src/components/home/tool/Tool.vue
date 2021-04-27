<template>
  <el-container id="admin-body">
    <el-header style="z-index: 1;height: 80px;margin-left: -20px">
      <Header style="position: absolute;width: 98%;"></Header>
    </el-header>
    <!--<el-row style="height: 100%;">-->
    <el-container>
      <el-aside style="height: 112%;margin-top: -80px;width: 250px">
        <el-menu
          :default-active="this.$route.path"
          class="el-menu-admin"
          router
          mode="vertical"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :collapse="isCollapse">
          <div style="height: 80px;"></div>
          <el-menu-item style="text-align: left" key="/tool/markTool" index="/tool/markTool">
            <i class="el-icon-s-cooperation"></i>
            <span slot="title" style="font-size: 17px;">水印工具</span>
          </el-menu-item>
          <el-menu-item style="text-align: left" key="/tool/certTool" index="/tool/certTool">
            <i class="el-icon-s-cooperation"></i>
            <span slot="title" style="font-size: 17px;">证件照工具</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import Header from '../../admin/Header';

  export default {
    name: "Tool",
    components: {Header},
    data() {
      return {
        isCollapse: false,
        userIcon: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      }
    },
    mounted() {
      this.currentUser();
    },
    methods: {
      currentUser() {
        var _this = this;
        this.$axios.get('/login/currentUser').then(resp => {
          if (resp && resp.data.status === 0) {
            _this.userIcon = resp.data.data.icon;
            _this.username = resp.data.data.username;
          }
        })
      }
    }
  }
</script>

<style scoped>
  #admin-body {
    height: 95vh;
  }

  .el-menu-admin {
    border-radius: 5px;
    height: 100%;
  }
</style>

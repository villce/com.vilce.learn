<template>
  <el-container>
      <el-header style="height: 80px;">
        <div style="float: left">
          <a href="/">
            <el-avatar :size="70" :src="userIcon" width="55px" style="float: left;margin-top: 15px"></el-avatar>
          </a>
        </div>
        <div style="text-align: center;margin-top: 30px">
          <span style="font-size: 30px;margin-left: -50px">
            {{modulesTitle}}
          </span>
        </div>
        <div style="float: right;margin-top: -70px;margin-right: -30px">
          <Heart></Heart>
        </div>
      </el-header>
      <el-main style="">
        <div class="content">
          <el-carousel :interval="5000" arrow="always" :height="'540px'">
            <el-carousel-item v-for="url in imageList" :key="url">
              <el-row :gutter="12">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="height:540px;">
                  <el-image
                    :src="url"
                    alt=""
                    :fit="'contain'"
                    style="height:540px;"></el-image>
                </el-col>
              </el-row>
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-main>
      <el-footer>
        <el-card>
          <div class="my_timeline_prev">
            <img
                    style="zoom: 20%;margin-top: 150px;"
                    :src="leftUrl"
                    @click="moveLeft"
                    class="my_timeline_node"/>
          </div>
          <div class="my_timeline_next">
            <img
                    style="zoom: 20%;margin-top: 150px;"
                    :src="rightUrl"
                    @click="moveRight"
                    class="my_timeline_node"/>
          </div>
          <div class="ul_box">
            <ul class="my_timeline" ref="mytimeline">
              <li class="my_timeline_item" v-for="(item,index) in modulesList" :key="index">
                <!--圈圈节点-->
                <div class="my_timeline_node"
                     :style=" {backgroundColor: '#e4e7ed', width: '28px', height: '28px'}"
                     @click="changeActive(index)"
                     :class="{active: index == timeIndex}"></div>
                <!--线-->
                <div
                        :style="{width: index === 4 ? '0%':'100%'}"
                        class="my_timeline_item_line">
                </div>
                <!--标注-->
                <div class="my_timeline_item_content" :style="{color: '#999', fontSize: '18px'}">
                  {{item.modulesDate}}
                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-footer>
    </el-container>
</template>

<script>
  import Heart from "@/views/secret/Heart";
  import {currentUser} from "@/api/common/login";
  import {getNewModules, timeLineGetModules} from "@/api/secret/secret";

  export default {
    name: "Secret",
    components: {Heart},
    data() {
      return {
        userIcon: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        leftUrl: 'https://cdn.jsdelivr.net/gh/villce/img/2021-02/left2.png',
        rightUrl: 'https://cdn.jsdelivr.net/gh/villce/img/2021-02/right2.png',
        timeIndex: 0,
        imageList: [],
        modulesList: [],
        modules: {},
        modulesIndex: 1,
        pageSize: 5,
        num: 0,
        days: 0,
        modulesTitle: ''
      }
    },
    mounted() {
      this.currentUser();
      this.loadModules();
    },
    methods: {
      currentUser() {
        const username = this.$store.state.username;
        currentUser(username).then(resp => {
          if (resp.status === 0) {
            if (resp.data !== null) {
              this.userIcon = resp.data.icon;
            }
          }
        })
      },
      changeActive(index) {
        this.timeIndex = index;
        this.modulesTitle = this.modulesList[index].modulesTitle;
        this.imageList = this.modulesList[index].imgUrlList;
      },
      moveLeft() {
        // 向左移动4个单位获取模块信息
        if (this.modulesIndex * this.pageSize < this.num) {
          var pageIndex = this.modulesIndex + 1;
          timeLineGetModules(pageIndex, this.pageSize).then(resp => {
            if (resp.status === 0) {
              this.modulesList = resp.data.modulesList;
              this.modulesIndex = pageIndex;
              this.modulesTitle = this.modulesList[this.timeIndex].modulesTitle;
              this.imageList = this.modulesList[this.timeIndex].imgUrlList;
            }
          })
        }
      },
      moveRight() {
        // 向左移动4个单位获取模块信息
        if (this.modulesIndex > 1) {
          var pageIndex = this.modulesIndex - 1;
          timeLineGetModules(pageIndex, this.pageSize).then(resp => {
            if (resp.status === 0) {
              this.modulesList = resp.data.modulesList;
              this.modulesIndex = pageIndex;
              this.modulesTitle = this.modulesList[this.timeIndex].modulesTitle;
              this.imageList = this.modulesList[this.timeIndex].imgUrlList;
            }
          })
        }
      },
      loadModules() {
        getNewModules(this.pageSize).then(resp => {
          if (resp.status === 0) {
            this.modulesList = resp.data.modulesList;
            this.num = resp.data.num;
            this.modulesIndex = 5;
            this.modulesTitle = this.modulesList[this.timeIndex].modulesTitle;
            this.imageList = this.modulesList[this.timeIndex].imgUrlList;
          }
        })
      }
    }
  }
</script>

<style scoped>
  .my_timeline_prev, .my_timeline_next {
    /*float: left;*/
    display: inline-block;
    background-color: #fff;
    cursor: pointer;
  }

  .my_timeline_prev {
    float: left;
  }

  .my_timeline_next {
    float: right;
  }

  .ul_box {
    height: 60px;
    text-align: center;
    margin-left: 200px;
  }

  .my_timeline_item {
    display: inline-block;
    width: 220px;
  }

  .my_timeline_node {
    box-sizing: border-box;
    border-radius: 50%;
    cursor: pointer;
  }

  .my_timeline_node.active {
    background-color: #fff !important;
    border: 6px solid #f68720;
  }

  .my_timeline_item_line {
    width: 100%;
    height: 10px;
    margin: -14px 0 0 28px;
    border-top: 2px solid #E4E7ED;
    /*border-left: none;*/
  }

  .my_timeline_item_content {
    margin: 20px 10px 0 -170px;
  }

  .el-carousel__item h3 {
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
  }
</style>

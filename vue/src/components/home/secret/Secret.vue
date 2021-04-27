<template>
  <!--时间线-->
  <div class="timeLine">
    <el-container>
      <el-header
        style="height: 80px;">
        <el-row :gutter="20">
          <el-col :span="4">
            <a href="/index">
              <el-avatar :size="70" :src="userIcon" width="55px" style="float: left;margin-top: 15px"></el-avatar>
            </a>
          </el-col>
          <el-col :span="16">
            <div style="font-size: 30px;margin-top: 25px;">
              {{modulesList[timeIndex].modulesTitle}}
            </div>
          </el-col>
          <el-col :span="4">
            <el-row>
              <el-col :span="6" style="font-size: 18px;margin-top: 20px">
                <span>LCZ</span>
              </el-col>
              <el-col :span="6">
                <Heart style="zoom: 20%"></Heart>
              </el-col>
              <el-col :span="6" style="font-size: 18px;margin-top: 20px">
                <span>CSM</span>
              </el-col>
            </el-row>
            <el-row style="float: left;font-size: 20px;margin-top: -5px;margin-left: 65px">
              <span>{{days}}天</span>
            </el-row>
          </el-col>
        </el-row>
      </el-header>
      <el-main style="">
        <div class="content">
          <el-carousel :interval="5000" arrow="always" :height="'540px'">
            <el-carousel-item v-for="url in modulesList[timeIndex].imgUrlList" :key="url">
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
      <el-footer style="margin-top: 50px">
        <el-card style="height: 130px">
          <div class="my_timeline_prev">
            <img
              style="zoom: 20%;margin-top: 150px;"
              src="../../../assets/img/icon/left2.png"
              @click="moveLeft"
              class="my_timeline_node"/>
          </div>
          <div class="my_timeline_next">
            <img
              style="zoom: 20%;margin-top: 150px;"
              src="../../../assets/img/icon/right2.png"
              @click="moveRight"
              class="my_timeline_node"/>
          </div>
          <div class="ul_box">
            <ul class="my_timeline" ref="mytimeline" style="margin-left: 100px;">
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
  </div>
</template>

<script>
  import Heart from "./Heart";

  export default {
    name: "Secret",
    components: {Heart},
    data() {
      return {
        userIcon: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        timeIndex: 0,
        modulesList: [],
        modules: {},
        modulesIndex: 1,
        pageSize: 5,
        num: 0,
        days: 0
      }
    },
    mounted() {
      this.currentUser();
      this.dateDiffer();
      this.loadModules();
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
      },
      changeActive(index) {
        this.timeIndex = index;
      },
      moveLeft() {
        // 向左移动4个单位获取模块信息
        if (this.modulesIndex * this.pageSize < this.num) {
          var pageIndex = this.modulesIndex + 1;
          this.$axios.get("/secret/timeLineGetModules/" + pageIndex + "/" + this.pageSize).then(resp => {
            if (resp && resp.data.status === 0) {
              this.modulesList = resp.data.data.modulesList;
              this.modulesIndex = pageIndex;
            }
          })
        }
      },
      moveRight() {
        // 向左移动4个单位获取模块信息
        if (this.modulesIndex > 1) {
          var pageIndex = this.modulesIndex - 1;
          this.$axios.get("/secret/timeLineGetModules/" + pageIndex + "/" + this.pageSize).then(resp => {
            if (resp && resp.data.status === 0) {
              this.modulesList = resp.data.data.modulesList;
              this.modulesIndex = pageIndex;
            }
          })
        }
      },
      loadModules() {
        this.$axios.get("/secret/getNewModules/" + this.pageSize).then(resp => {
          if (resp && resp.data.status === 0) {
            this.modulesList = resp.data.data.modulesList;
            this.num = resp.data.data.num;
            this.modulesIndex = 5;
          }
        })
      },
      dateDiffer() {
        var date_start = "2019-03-01"
        //date1结束时间
        let date1 = new Date(date_start);
        //date2当前时间
        let date2 = new Date();
        date1 = new Date(date1.getFullYear(), date1.getMonth(), date1.getDate());
        date2 = new Date(date2.getFullYear(), date2.getMonth(), date2.getDate());
        const diff = date2.getTime() - date1.getTime(); //目标时间减去当前时间
        const diffDate = diff / (24 * 60 * 60 * 1000);  //计算当前时间与结束时间之间相差天数
        this.days = diffDate;
      }
    }
  }
</script>

<style scoped>
  .my_timeline_prev, .my_timeline_next {
    float: left;
    display: inline-block;
    background-color: #fff;
    cursor: pointer;
  }

  .my_timeline_prev {
    width: 200px;
    float: left;
  }

  .my_timeline_next {
    float: right;
    width: 200px;
  }

  .ul_box {
    height: 60px;
    float: left;
    margin-top: -80px;
    margin-left: 130px;
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
    margin: 10px 0 0 -200px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

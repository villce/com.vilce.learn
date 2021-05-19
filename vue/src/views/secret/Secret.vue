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
            {{modules.modulesTitle}}
          </span>
            </div>
            <div style="float: right;margin-top: -70px;margin-right: -30px">
                <Heart></Heart>
            </div>
        </el-header>
        <el-main style="">
            <div class="content">
                <el-carousel :interval="5000" arrow="always" :height="'540px'">
                    <el-carousel-item v-for="url in modules.imgUrlList" :key="url">
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
            <div style="text-align: center;margin-top: 30px;font-size: 30px">
                {{modules.modulesDate}}
            </div>
        </el-footer>
    </el-container>
</template>

<script>
    import Heart from "@/views/secret/Heart";
    import {currentUser} from "@/api/common/login";
    import {getNewModules, timeLineGetModules, getModules} from "@/api/secret/secret";

    export default {
        name: "Secret",
        components: {Heart},
        data() {
            return {
                userIcon: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
                leftUrl: 'https://cdn.jsdelivr.net/gh/villce/img/2021-02/left2.png',
                rightUrl: 'https://cdn.jsdelivr.net/gh/villce/img/2021-02/right2.png',
                modules: {},
                pageSize: 1,
                modulesNum: 0
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
            moveLeft() {
                // 向左移动4个单位获取模块信息
                if (this.modules.index < this.modulesNum - 1) {
                    timeLineGetModules(this.modules.index, 1).then(resp => {
                        if (resp.status === 0) {
                            this.modules = resp.data;
                        }
                    })
                }
            },
            moveRight() {
                // 向左移动4个单位获取模块信息
                if (this.modules.index > 0) {
                    timeLineGetModules(this.modules.index, -1).then(resp => {
                        if (resp.status === 0) {
                            this.modules = resp.data;
                        }
                    })
                }
            },
            loadModules() {
                getModules(1, 1).then(resp => {
                    if (resp.status === 0) {
                        this.modulesNum = resp.data.num;
                    }
                })
                getNewModules().then(resp => {
                    if (resp.status === 0) {
                        this.modules = resp.data;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .my_timeline_prev, .my_timeline_next {
        /*float: left;*/
        float: contour;
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

    .my_timeline_node {
        box-sizing: border-box;
        border-radius: 50%;
        cursor: pointer;
        margin-bottom: 50px;
    }

    .my_timeline_node.active {
        background-color: #fff !important;
        border: 6px solid #f68720;
    }

    .el-carousel__item h3 {
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
    }
</style>

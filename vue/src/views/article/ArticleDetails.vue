<template>
  <div style="height:100%;width:100%">
    <el-container>
      <el-aside style="width: 250px;margin-left: 100px">
        <el-card shadow="always" style="text-align: center">
          <el-row slot="header" class="clearfix">
            <span style="font-size: 30px">
              <strong>vilce</strong>
            </span>
          </el-row>
          <el-row type="flex" justify="center">
            <a class="block" href="/login">
              <el-avatar :size="70" :src="circleUrl" style="margin-bottom: 10px"></el-avatar>
            </a>
          </el-row>
          <el-row style="font-size: 15px;margin-left: 40px">
            <el-col :span="30">
              <router-link class="article-link" :to="{path:'/article'}">
                <el-row>博客</el-row>
                <el-row>{{articleStatistic.articleNum}}</el-row>
              </router-link>
            </el-col>
            <el-col :span="30">
              <el-divider direction="vertical"></el-divider>
            </el-col>
            <el-col :span="30">
              <router-link class="article-link" :to="{path:'/article'}">
                <el-row>分类</el-row>
                <el-row>{{articleStatistic.typeNum}}</el-row>
              </router-link>
            </el-col>
            <el-col :span="30">
              <el-divider direction="vertical"></el-divider>
            </el-col>
            <el-col :span="30">
              <router-link class="article-link" :to="{path:'/article'}">
                <el-row>标签</el-row>
                <el-row>{{articleStatistic.labelNum}}</el-row>
              </router-link>
            </el-col>
          </el-row>
          <br>
          <el-row style="font-size: 15px">
            <el-col :span="10">
              <router-link :to="{path:'/tool'}" class="article-link" style="margin-left: 50px">
                <span>工具</span>
              </router-link>
            </el-col>
            <el-col :span="10">
              <router-link :to="{path:'/secret'}" class="article-link">
                <span>私密</span>
              </router-link>
            </el-col>
          </el-row>
        </el-card>
        <br>
        <el-card>
          <div class="py-4 links">
            <h3 class="pl-3 pb-3">目录</h3>
            <ul>
              <li
                style="text-align: left"
                v-for="(nav, index) in navList"
                :key="index"
                :class="{ on: activeIndex === index }"
                @click="currentClick(index)">
                <a href="javascript:;" @click="pageJump(nav.index)">{{nav.title }}</a>
                <div
                  v-if="nav.children.length > 0"
                  class="menu-children-list">
                  <ul class="nav-list">
                    <li
                      v-for="(item, idx) in nav.children"
                      :key="idx"
                      :class="{ on: childrenActiveIndex === idx }"
                      @click.stop="childrenCurrentClick(idx)">
                      <a href="javascript:;" @click="pageJump(item.index)">{{item.title }}</a>
                    </li>
                  </ul>
                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-aside>
      <el-main style="margin-top: -20px;margin-left: 50px">
        <el-card style="width:90%;text-align: left">
          <div class="body">
            <div
              class="content markdown-body"
              ref="helpDocs"
              v-html="compiledMarkdown"></div>
          </div>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import marked from "marked";
  import {currentUser} from "@/api/common/login";
  import {getOneArticle, statistics} from "@/api/article/article";

  let rendererMD = new marked.Renderer();
  marked.setOptions({
    renderer: rendererMD,
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: false,
    smartLists: true,
    smartypants: false,
  });
  export default {
    props: ["id"],
    data() {
      return {
        circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        articleStatistic: {},
        article: [],
        html: "",//文章内容
        navList: [],
        activeIndex: 0,
        docsFirstLevels: [],
        docsSecondLevels: [],
        childrenActiveIndex: 0,
      };
    },
    mounted() {
      this.currentUser();
      this.countArticles();
      this.getArticleDetail();
    },
    methods: {
      currentUser() {
        const username = this.$store.state.user.name
        currentUser(username).then(resp => {
          if (resp.status === 0) {
            if (resp.data !== null) {
              this.circleUrl = resp.data.icon;
            }
          }
        })
      },
      countArticles() {
        statistics().then(resp => {
          if (resp.status === 0) {
            this.articleStatistic = resp.data;
            this.total = this.articleStatistic.articleNum;
          }
        })
      },
      async getArticleDetail() {
        try {
          getOneArticle(this.$route.query.id).then(resp => {
            if (resp.status === 0) {
              this.article = resp.data;
              this.html = this.article.contentMd;
              document.getElementsByTagName(
                "title"
              )[0].text = this.article.title;
              //文章内容获取后渲染目录，避免目录无法及时获取内容
              this.navList = this.handleNavTree();
              this.getDocsFirstLevels(0);
            }
          });
        } catch (e) {
          global.console.log("文章获取异常");
        }
      },
      childrenCurrentClick(index) {
        this.childrenActiveIndex = index;
      },
      getDocsFirstLevels(times) {
        // 解决图片加载会影响高度问题
        setTimeout(() => {
          let firstLevels = [];
          Array.from(document.querySelectorAll("h3"), (element) => {
            firstLevels.push(element.offsetTop - 60);
          });
          this.docsFirstLevels = firstLevels;

          if (times < 8) {
            this.getDocsFirstLevels(times + 1);
          }
        }, 500);
      },
      getDocsSecondLevels(parentActiveIndex) {
        let idx = parentActiveIndex;
        let secondLevels = [];
        let navChildren = this.navList[idx].children;

        if (navChildren.length > 0) {
          secondLevels = navChildren.map((item) => {
            return this.$el.querySelector(`#data-${item.index}`).offsetTop - 60;
          });
          this.docsSecondLevels = secondLevels;
        }
      },
      getLevelActiveIndex(scrollTop, docsLevels) {
        let currentIdx = null;
        let nowActive = docsLevels.some((currentValue, index) => {
          if (currentValue >= scrollTop) {
            currentIdx = index;
            return true;
          }
        });

        currentIdx = currentIdx - 1;

        if (nowActive && currentIdx === -1) {
          currentIdx = 0;
        } else if (!nowActive && currentIdx === -1) {
          currentIdx = docsLevels.length - 1;
        }
        return currentIdx;
      },
      pageJump(id) {
        this.titleClickScroll = true;
        this.$refs.helpDocs.scrollTop = this.$el.querySelector(`#data-${id}`).offsetTop - 40;
        //这里我与原作者的不太一样，发现原作者的scrollTop一直为0，所以使用了Vuetify自带的goTo事件
        // this.$vuetify.goTo(this.$el.querySelector(`#data-${id}`).offsetTop - 40);
        setTimeout(() => (this.titleClickScroll = false), 100);
      },
      currentClick(index) {
        this.activeIndex = index;
        this.getDocsSecondLevels(index);
      },
      getTitle(content) {
        let nav = [];
        let tempArr = [];
        content.replace(/(#+)[^#][^\n]*?(?:\n)/g, function (match, m1) {
          let title = match.replace("\n", "");
          let level = m1.length;
          tempArr.push({
            title: title.replace(/^#+/, "").replace(/\([^)]*?\)/, ""),
            level: level,
            children: [],
          });
        });
        // 只处理二级到四级标题，以及添加与id对应的index值，这里还是有点bug,因为某些code里面的注释可能有多个井号
        nav = tempArr.filter((item) => item.level >= 1 && item.level <= 6);
        let index = 0;
        return (nav = nav.map((item) => {
          item.index = index++;
          return item;
        }));
      },
      // 将除一级标题外数据处理成树结构
      handleNavTree() {
        let navs = this.getTitle(this.content);
        let navLevel = [1, 2, 3, 4, 5, 6];
        let retNavs = [];
        let toAppendNavList;
        navLevel.forEach((level) => {
          // 遍历一级二级标题，将同一级的标题组成新数组
          toAppendNavList = this.find(navs, {
            level: level,
          });
          if (retNavs.length === 0) {
            // 处理一级标题
            retNavs = retNavs.concat(toAppendNavList);
          } else {
            // 处理二级标题，并将二级标题添加到对应的父级标题的children中
            toAppendNavList.forEach((item) => {
              item = Object.assign(item);
              let parentNavIndex = this.getParentIndex(navs, item.index);
              return this.appendToParentNav(retNavs, parentNavIndex, item);
            });
          }
        });
        return retNavs;
      },
      find(arr, condition) {
        return arr.filter((item) => {
          for (let key in condition) {
            if (condition.hasOwnProperty(key) && condition[key] !== item[key]) {
              return false;
            }
          }
          return true;
        });
      },
      getParentIndex(nav, endIndex) {
        for (var i = endIndex - 1; i >= 0; i--) {
          if (nav[endIndex].level > nav[i].level) {
            return nav[i].index;
          }
        }
      },
      appendToParentNav(nav, parentIndex, newNav) {
        let index = this.findIndex(nav, {
          index: parentIndex,
        });
        nav[index].children = nav[index].children.concat(newNav);
      },
      findIndex(arr, condition) {
        let ret = -1;
        arr.forEach((item, index) => {
          for (var key in condition) {
            if (condition.hasOwnProperty(key) && condition[key] !== item[key]) {
              return false;
            }
          }
          ret = index;
        });
        return ret;
      },
    },
    computed: {
      content() {
        return this.html;
      },
      //此函数将markdown内容进一步的转换
      compiledMarkdown: function () {
        let index = 0;
        rendererMD.heading = function (text, level) {
          //我比较习惯三级和四级目录，这里看你喜欢
          if (level <= 4) {
            return `<h${level} id="data-${index++}">${text}</h${level}>`;
          } else {
            return `<h${level}>${text}</h${level}>`;
          }
        };
        return marked(this.content);
      },
    },
  };
</script>

<style scoped>
  @import '../../styles/markdown.css';

  .article-link {
    text-decoration: none;
    color: #606266;
  }
</style>

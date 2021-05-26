<template>
  <div style="height:100%;width:100%">
    <el-container>
      <el-main>
        <el-card style="width:95%;text-align: left">
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
  import {getOneArticle} from "@/api/article/article";

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
      this.getArticleDetail();
    },
    methods: {
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
  @import '../../../../styles/markdown.css';

  .article-link {
    text-decoration: none;
    color: #606266;
  }
</style>

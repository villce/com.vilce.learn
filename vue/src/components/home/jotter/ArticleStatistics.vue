<template>
  <el-card class="articles-title" shadow="always" id="statistics">
    <el-row slot="header" class="clearfix">
      <span>vilce</span>
    </el-row>
    <el-row type="flex" justify="center">
      <a class="block" href="/index">
        <el-avatar :size="70" :src="circleUrl" style="margin-bottom: 5px"></el-avatar>
      </a>
    </el-row>
    <el-row class="index" style="text-align: center">
      <el-link>博客<br>{{articleStatistic.articleNum}}</el-link>
      <el-divider direction="vertical"></el-divider>
      <el-link>分类<br>{{articleStatistic.typeNum}}</el-link>
      <el-divider direction="vertical"></el-divider>
      <el-link>标签<br>{{articleStatistic.labelNum}}</el-link>
    </el-row>
  </el-card>
</template>

<script>
  export default {
    name: "ArticleStatistics",
    data() {
      return {
        circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        articleStatistic: {},
        articles: [],
        total: 1,
      }
    },
    mounted() {
      this.currentUser();
      this.countArticles();
      this.loadArticles();
    },
    methods: {
      currentUser() {
        this.$axios.get('/login/currentUser').then(resp => {
          if (resp && resp.data.status === 0) {
            this.circleUrl = resp.data.data.icon;
          }
        })
      },
      countArticles() {
        this.$axios.get('/article/statistics').then(resp => {
          if (resp && resp.data.status === 0) {
            this.articleStatistic = resp.data.data;
            this.total = this.articleStatistic.articleNum;
          }
        })
      },
      loadArticles() {
        this.$axios.get('/article/listArticles/1/' + this.pageSize).then(resp => {
          if (resp && resp.data.status === 0) {
            this.articles = resp.data.data
          }
        })
      }
    }
  }

</script>

<style scoped>
  .text {
    font-size: 14px;
    margin-bottom: 10px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .el-card__header {
    background-color: #1F1F1F;
    font-size: 28px;
    color: #eaeaea;
  }
</style>

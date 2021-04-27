<template>
  <div style="height:100%;width:100%">
    <el-row :gutter="5" type="flex" justify="center">
      <el-col :span="3">
        <article-statistics ref="statistics">
        </article-statistics>
        <br>
        <el-card class="articles-title" shadow="always">
          <div class="el-card__body mid">
            <el-row class="text" style="text-align: left;">
              <el-col v-for="(articleType,i) in articleStatistic.articleTypes" :key="i" :index="(i).toString()"
                      style="text-align: left;padding-bottom: 5px">
                <el-link @click="getArticleByType(articleType.type)">
                  <i class="el-icon-star-on"></i>
                  {{articleType.type}} ({{articleType.num}})
                </el-link>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <br>
        <el-card class="articles-title" shadow="always">
          <div class="el-card__body mid">
            <el-row class="text" style="text-align: left;">
              <el-col id="span_id" v-for="(articleLabel,i) in articleStatistic.articleLabels" :key="i"
                      :index="(i).toString()"
                      style="text-align: left;padding-left: 10px;padding-bottom: 5px">
                <el-link @click="getArticleByLabel(articleLabel.label)" :style="{fontSize: 2*(10-i) +'px'}">
                  {{articleLabel.label}}
                </el-link>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
      <el-col :span="3" class="articles-area">
        <el-card v-for="article in articles" :key="article.id" style="width:85%;height: 250px;">
          <router-link class="article-link" :to="{path:'jotter/article',query:{id: article.id}}">
                <span style="font-size: 25px;text-align: center">
                  <strong>{{article.title}}</strong>
                </span>
          </router-link>
          <el-divider content-position="center">
            <i class="el-icon-edit"></i>
            <span> {{article.publishDate}}</span>
            <el-divider direction="vertical"></el-divider>
            <i class="el-icon-folder"></i>
            <span> {{article.type}}</span>
          </el-divider>
          <div class="markdown-body" style="text-align: left">
            <div v-html="article.introduction"></div>
          </div>
        </el-card>
        <br>
        <el-pagination
          background
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :current-page.sync="currentPage"
          :total="total">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import ArticleStatistics from './ArticleStatistics'
  export default {
    name: 'Articles',
    components: {ArticleStatistics},
    data() {
      return {
        articleStatistic: {},
        articles: [],
        pageSize: 2,
        total: 1,
        currentPage: 1
      }
    },
    mounted() {
      this.$refs.statistics;
      this.articleStatistic = this.$refs.statistics.articleStatistic;
      this.articles = this.$refs.statistics.articles;
      this.total = this.$refs.statistics.total;
    },
    methods: {
      handleCurrentChange(page) {
        var _this = this;
        this.$axios.get('/article/listArticles/' + page + '/' + this.pageSize).then(resp => {
          if (resp && resp.data.status === 0) {
            _this.articles = resp.data.data
          }
        })
      },
      getArticleByType(type) {
        var _this = this;
        this.$axios.get('/article/getArticleByType/?type=' + type).then(resp => {
          if (resp && resp.data.status === 0) {
            _this.articles = resp.data.data;
            this.total = _this.articles.length;
          }
        })
      },
      getArticleByLabel(label) {
        var _this = this;
        this.$axios.get('/article/getArticleByLabel/?label=' + label).then(resp => {
          if (resp && resp.data.status === 0) {
            _this.articles = resp.data.data;
            this.total = _this.articles.length;
          }
        })
      }
    }
  }
</script>

<style>
  @import "../../../styles/markdown.css";
  .articles-title {
    width: 200px;
  }

  .articles-area {
    width: 1000px;
  }

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  .article-link:hover {
    color: #409EFF;
  }
</style>

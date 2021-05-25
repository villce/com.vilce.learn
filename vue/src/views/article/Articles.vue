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
            <a class="block" href="/user">
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
          <el-row style="font-size: 15px;text-align: center">
              <router-link :to="{path:'/secret'}" class="article-link">
                <span>私密</span>
              </router-link>
          </el-row>
        </el-card>
        <br>
        <el-card shadow="always">
          <div class="el-card__body mid">
            <el-row class="text" style="text-align: left;">
              <el-col v-for="(articleType,i) in articleStatistic.articleTypes" :key="i" :index="(i).toString()"
                      style="text-align: left;padding-bottom: 5px">
                <el-link @click="getArticleByType(1, articleType.type)">
                  <i class="el-icon-star-on"></i>
                  {{articleType.type}} ({{articleType.num}})
                </el-link>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <br>
        <el-card shadow="always">
          <div class="el-card__body mid">
            <el-row class="text" style="text-align: left;">
              <el-col id="span_id" v-for="(articleLabel,i) in articleStatistic.articleLabels" :key="i"
                      :index="(i).toString()"
                      style="text-align: left;padding-left: 10px;padding-bottom: 5px">
                <el-link @click="getArticleByLabel(1, articleLabel.label)" :style="{fontSize: 2*(10-i) +'px'}">
                  {{articleLabel.label}}
                </el-link>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-aside>
      <el-main style="margin-top: -40px;margin-left: 50px">
        <el-card v-for="article in articles" :key="article.id" style="width:90%;height: 250px;margin-top: 20px;text-align: center">
          <router-link class="article-link" :to="{path:'article/details',query:{id: article.id}}">
            <span style="font-size: 25px">
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
          <br>
        </el-card>
        <br>
        <el-pagination
          style="width: 90%"
          background
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :current-page.sync="currentPage"
          :total="total">
        </el-pagination>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import { getArticleByLabel, getArticleByType, listArticles, statistics } from "@/api/article/article";
  import { currentUser } from "@/api/common/login";

  export default {
    name: 'Articles',
    data() {
      return {
        circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        articleStatistic: {},
        articles: [],
        pageSize: 4,
        total: 1,
        currentPage: 1,
        mark: 1,
        type: '',
        label: '',
      }
    },
    mounted() {
      this.currentUser();
      this.countArticles();
      this.getArticle(1);
    },
    methods: {
      currentUser() {
        currentUser().then(resp => {
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
          }
        })
      },
      getArticle(page) {
        listArticles(page, this.pageSize).then(resp => {
          if (resp.status === 0) {
            this.articles = resp.data.articleResList;
            this.total = resp.data.articleNum;
            this.mark = 1;
            this.currentPage = page;
          }
        })
      },
      handleCurrentChange(page) {
        switch (this.mark) {
          case 1:
            this.getArticle(page);
            break;
          case 2:
            this.getArticleByType(page, this.type);
            break;
          case 3:
            this.getArticleByLabel(page, this.label);
            break;
        }
      },
      getArticleByType(page, type) {
        getArticleByType(page, this.pageSize, type).then(resp => {
          if (resp.status === 0) {
            this.articles = resp.data.articleResList;
            this.total = resp.data.articleNum;
            this.mark = 2;
            this.type = type;
            this.currentPage = page;
          }
        })
      },
      getArticleByLabel(page, label) {
        getArticleByLabel(page, this.pageSize, label).then(resp => {
          if (resp.status === 0) {
            this.articles = resp.data.articleResList;
            this.total = resp.data.articleNum;
            this.mark = 3;
            this.label = label;
            this.currentPage = page;
          }
        })
      }
    }
  }
</script>

<style scoped>
  @import '../../styles/markdown.css';

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  .article-link:hover {
    color: #409EFF;
  }

  .clearfix {
    font-size: 20px;
  }
</style>

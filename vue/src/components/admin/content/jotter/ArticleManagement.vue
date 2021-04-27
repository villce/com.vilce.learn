<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>文章管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-link
      href="/admin/content/articleEditor"
      :underline="false"
      target="_blank"
      style="margin: 18px 2%"
      class="add-link">
      <el-button type="success">写文章</el-button>
    </el-link>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="articles"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="title"
          label="题目"
          fit>
        </el-table-column>
        <el-table-column
          prop="publishDate"
          label="发布日期"
          width="200">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="viewArticle(scope.row.id)"
              type="text"
              size="small">
              查看
            </el-button>
            <el-button
              @click.native.prevent="editArticle(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteArticle(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :current-page.sync="currentPage"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ArticleManagement',
  data () {
    return {
      articleStatistic: [],
      articles: [],
      pageSize: 2,
      total: 1,
      currentPage: 1
    }
  },
  mounted () {
    this.countArticles();
    this.loadArticles();
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    countArticles() {
      var _this = this;
      this.$axios.get('/article/statistics').then(resp => {
        if (resp && resp.data.status === 0) {
          _this.articleStatistic = resp.data.data;
          _this.total = _this.articleStatistic.articleNum;
          console.info(_this.total)
        }
      })
    },
    loadArticles () {
      var _this = this
      this.$axios.get('/article/listArticles/1/' + this.pageSize ).then(resp => {
        if (resp && resp.data.status === 0) {
          _this.articles = resp.data.data;
        }
      })
    },
    handleCurrentChange(page) {
      var _this = this;
      this.$axios.get('/article/listArticles/' + page + '/' + this.pageSize).then(resp => {
        if (resp && resp.data.status === 0) {
          _this.articles = resp.data.data
        }
      })
    },
    viewArticle (id) {
      let articleUrl = this.$router.resolve(
        {
          path: '../../home/jotter/article',
          query: {
            id: id
          }
        }
      )
      window.open(articleUrl.href, '_blank')
    },
    editArticle (article) {
      this.$router.push(
        {
          name: 'ArticleEditor',
          params: {
            article: article
          }
        }
      )
    },
    deleteArticle (id) {
      this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
          this.$axios
            .delete('/article/deleteArticle?id=' + id).then(resp => {
            if (resp && resp.data.status === 0) {
              this.$message({
                type: 'info',
                message: resp.data.message
              })
              this.loadArticles()
              _this.$router.replace('/admin/dashboard')
            }
          })
        }
      ).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
  .add-link {
    margin: 18px 0 15px 10px;
    float: left;
  }
</style>

<template>
  <div>
    <el-link
      href="/content/article/articleEditor"
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
          align="center"
          width="180">
          <template slot-scope="scope">
            <router-link :to="{path:'/content/article/details',query:{id: scope.row.id}}">
              <el-button type="text" size="small">
                查看
              </el-button>
            </router-link>
            <router-link :to="{path:'/content/article/editor',query:{article: scope.row}}">
              <el-button
                      @click.native.prevent="editArticle(scope.row)"
                      type="text"
                      size="small">
                编辑
              </el-button>
            </router-link>
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
  import { listArticles, deleteArticle} from "@/api/article/article";

  export default {
  name: 'ArticleManagement',
  data () {
    return {
      articles: [],
      pageSize: 2,
      total: 1,
      currentPage: 1
    }
  },
  mounted () {
    this.loadArticles(1);
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    loadArticles (page) {
      listArticles(page, this.pageSize).then(resp => {
        if (resp.status === 0) {
          this.articles = resp.data.articleResList;
          this.total = resp.data.articleNum;
        }
      })
    },
    handleCurrentChange(page) {
      this.loadArticles(page);
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
        deleteArticle(id).then(resp => {
            if (resp.status === 0) {
              this.$message({
                type: 'info',
                message: resp.message
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

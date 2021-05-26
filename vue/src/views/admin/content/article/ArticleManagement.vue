<template>
  <el-container>
    <el-header>
      <router-link :to="{path:'/content/article/editor'}">
        <el-button type="success" style="margin-top: 20px">写文章</el-button>
      </router-link>
    </el-header>
    <el-main>
      <el-card>
        <el-table
                :data="articles"
                stripe
                style="width: 100%"
                :max-height="tableHeight">
          <el-table-column align="center" label="序号" type="index" fixed></el-table-column>
          <el-table-column
                  prop="title"
                  label="题目"
                  fit>
          </el-table-column>
          <el-table-column
                  prop="publishDate"
                  label="发布日期"
                  fit>
          </el-table-column>
          <el-table-column
                  fixed="right"
                  label="操作"
                  align="center"
                  width="180">
            <template slot-scope="scope">
              <el-tooltip effect="dark" content="查看" placement="bottom">
                <el-button
                        size="mini"
                        type="primary"
                        icon="el-icon-s-claim"
                        circle
                        @click.native.prevent="articleDetails(scope.row.id)">
                </el-button>
              </el-tooltip>
              <el-tooltip effect="dark" content="编辑" placement="bottom">
                <el-button
                        size="mini"
                        type="info"
                        icon="el-icon-edit"
                        circle
                        @click.native.prevent="editArticle(scope.row.id)">
                </el-button>
              </el-tooltip>
              <el-tooltip effect="dark" content="移除" placement="bottom">
                <el-button
                        size="mini"
                        type="danger"
                        icon="el-icon-delete-solid"
                        circle
                        @click.native.prevent="deleteArticle(scope.row.id)">
                </el-button>
              </el-tooltip>
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
    </el-main>
  </el-container>
</template>

<script>
  import { listArticles, deleteArticle} from "@/api/article/article";

  export default {
  name: 'ArticleManagement',
  data () {
    return {
      articles: [],
      pageSize: 10,
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
    async loadArticles (page) {
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
    editArticle(id) {
      this.$router.replace({ path: '/content/article/editor', query: {id: id} });
    },
    articleDetails(id) {
      this.$router.replace({ path: '/content/article/details', query: {id: id} });
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
                type: 'success',
                message: '文章删除成功'
              })
              this.loadArticles(1)
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

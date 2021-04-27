<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>秘密花园管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row style="margin: 18px 2%;font-size: 18px;text-align: left">
      <el-button type="success" @click="addModules">添加模块</el-button>
    </el-row>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="modules"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="modulesTitle"
          label="题目"
          fit>
        </el-table-column>
        <el-table-column
          prop="modulesDate"
          label="日期"
          fit>
        </el-table-column>
        <el-table-column
          prop="imgUrl"
          label="预览图片"
          fit>
          <template slot-scope="props">
            <el-image
              style="width:100px;height: 70px"
              :src="props.row.imgUrlList[0]"
              fit="url">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editModules(scope.row.id)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteModules(scope.row.id)"
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
      name: "SecretManagement",
      data () {
        return {
          modulesStatistic: [],
          modules: [],
          pageSize: 4,
          total: 1,
          currentPage: 1
        }
      },
      mounted () {
        this.loadModules();
      },
      computed: {
        tableHeight () {
          return window.innerHeight - 320
        }
      },
      methods: {
        loadModules () {
          var _this = this
          this.$axios.get('/secret/getModules/1/' + this.pageSize ).then(resp => {
            if (resp && resp.data.status === 0) {
              _this.modules = resp.data.data.modulesList;
              _this.total = resp.data.data.num;
            }
          })
        },
        handleCurrentChange(page) {
          var _this = this;
          this.$axios.get('/secret/getModules/' + page + '/' + this.pageSize).then(resp => {
            if (resp && resp.data.status === 0) {
              _this.modules = resp.data.data.modulesList;
              _this.total = resp.data.data.num;
            }
          })
        },
        addModules() {
          this.$router.push(
            {
              name: 'SecretModulesEditor'
            }
          )
        },
        editModules (id) {
          this.$axios.get('/secret/findModules/' + id).then(resp => {
            if (resp && resp.data.status === 0) {
              this.$router.push(
                {
                  name: 'SecretModulesEditor',
                  params: {
                    modules: resp.data.data
                  }
                }
              )
            }
          })
        },
        deleteModules (id) {
          this.$confirm('此操作将永久删除该模块, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
              this.$axios
                .get('/secret/deleteModules/' + id).then(resp => {
                if (resp && resp.data.status === 0) {
                  this.$message({
                    type: 'info',
                    message: resp.data.message
                  })
                  this.loadModules()
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

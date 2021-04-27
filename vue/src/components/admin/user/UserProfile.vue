<template>
  <div>
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedUser" style="text-align: left" ref="dataForm">
        <el-form-item label="用户名" label-width="120px" prop="username">
          <el-input v-model="selectedUser.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="120px" prop="password">
          <el-button type="warning" @click="resetPassword(selectedUser.username)">重置密码</el-button>
        </el-form-item>
        <el-form-item label="用户头像" label-width="120px" prop="name">
          <el-input v-model="selectedUser.icon" autocomplete="off"></el-input>
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-form-item>
        <el-form-item label="角色分配" label-width="120px" prop="roles">
          <el-checkbox-group v-model="selectedRolesIds">
            <el-checkbox v-for="(role,i) in roles" :key="i" :label="role.id">{{role.name_zh}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedUser)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <bulk-registration @onSubmit="listUsers()"></bulk-registration>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="users"
        stripe
        :default-sort="{prop: 'id', order: 'ascending'}"
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          label="id"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          fit>
        </el-table-column>
        <el-table-column
          prop="icon"
          label="用户头像"
          fit>
          <template slot-scope="scope">
            <el-image
              style="width: 50px;height: 50px"
              :src="scope.row.icon">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
          label="状态"
          sortable
          width="100">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editUser(scope.row.id)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button>取消选择</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import BulkRegistration from './BulkRegistration'
  import ImgUpload from '../content/ImgUpload'
  export default {
    name: 'UserProfile',
    components: {BulkRegistration, ImgUpload},
    data() {
      return {
        users: [],
        roles: [],
        dialogFormVisible: false,
        selectedUser: [],
        selectedRolesIds: []
      }
    },
    mounted() {
      this.listUsers()
      this.listRoles()
    },
    computed: {
      tableHeight() {
        return window.innerHeight - 320
      }
    },
    methods: {
      listUsers() {
        var _this = this
        this.$axios.get('/user/getAllUsers').then(resp => {
          if (resp && resp.data.status === 0) {
            _this.users = resp.data.data
          }
        })
      },
      listRoles() {
        var _this = this
        this.$axios.get('/role/listAllRolesInfo').then(resp => {
          console.info("listAllRolesInfo")
          if (resp && resp.data.status === 0) {
            _this.roles = resp.data.data
          }
        })
      },
      commitStatusChange(value, user) {
        if (user.username !== 'admin') {
          this.$axios.put('/user/updateUserStatus', {
            enabled: value,
            username: user.username
          }).then(resp => {
            if (resp && resp.data.status === 0) {
              if (value) {
                this.$message('用户 [' + user.username + '] 已启用')
              } else {
                this.$message('用户 [' + user.username + '] 已禁用')
              }
            }
          })
        } else {
          user.enabled = true
          this.$alert('不能禁用管理员账户')
        }
      },
      onSubmit(user) {
        let _this = this
        // 根据视图绑定的角色 id 向后端传送角色信息
        let roles = []
        for (let i = 0; i < _this.selectedRolesIds.length; i++) {
          for (let j = 0; j < _this.roles.length; j++) {
            if (_this.selectedRolesIds[i] === _this.roles[j].id) {
              roles.push(_this.roles[j])
            }
          }
        }
        // 根据视图绑定的角色 id 向后端传送角色信息
        this.$axios.put('/user/editUser', {
          id: user.id,
          username: user.username,
          icon: user.icon,
          roles: roles
        }).then(resp => {
          if (resp && resp.data.status === 0) {
            this.$alert('用户信息修改成功')
            this.dialogFormVisible = false
            // 修改角色后重新请求用户信息，实现视图更新
            this.listUsers()
          } else {
            this.$alert(resp.data.message)
          }
        })
      },
      editUser(userId) {
        this.$axios.get('/user/findUser/' + userId).then(resp => {
          if (resp && resp.data.status === 0) {
            this.dialogFormVisible = true
            this.selectedUser = resp.data.data
            let roleIds = []
            for (let i = 0; i < this.selectedUser.roles.length; i++) {
              roleIds.push(this.selectedUser.roles[i].id)
            }
            this.selectedRolesIds = roleIds
          }
        })
      },
      resetPassword(username) {
        this.$axios.put('/user/resetPassword', {
          username: username
        }).then(resp => {
          if (resp && resp.data.status === 0) {
            this.$alert('密码已重置为 123')
          }
        })
      },
      uploadImg() {
        this.selectedUser.icon = this.$refs.imgUpload.url
      }
    }
  }
</script>

<style scoped>

</style>

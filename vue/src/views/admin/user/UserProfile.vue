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
    <bulk-registration @onSubmit="listUsers()" style="margin: 18px"></bulk-registration>
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
  import BulkRegistration from '@/views/admin/user/BulkRegistration'
  import ImgUpload from '@/views/admin/content/ImgUpload'
  import {editUser, findUserById, getAllUsers, resetPassword, updateUserStatus} from "@/api/common/user";
  import {listAllRolesInfo} from "@/api/common/role";
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
        getAllUsers().then(resp => {
          if (resp.status === 0) {
            this.users = resp.data
          }
        })
      },
      listRoles() {
        listAllRolesInfo().then(resp => {
          if (resp.status === 0) {
            this.roles = resp.data
          }
        })
      },
      commitStatusChange(value, user) {
        if (user.username !== 'admin') {
          const userReq = {
            'enabled': value,
            'username': user.username
          }
          updateUserStatus(userReq).then(resp => {
            if (resp.status === 0) {
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
        // 根据视图绑定的角色 id 向后端传送角色信息
        let roles = []
        for (let i = 0; i < this.selectedRolesIds.length; i++) {
          for (let j = 0; j < this.roles.length; j++) {
            if (this.selectedRolesIds[i] === this.roles[j].id) {
              roles.push(this.roles[j])
            }
          }
        }
        const userReq = {
          'id': user.id,
          'username': user.username,
          'icon': user.icon,
          'roles': roles
        }
        editUser(userReq).then(resp => {
          if (resp.status === 0) {
            this.$alert('用户信息修改成功')
            this.dialogFormVisible = false
            // 修改角色后重新请求用户信息，实现视图更新
            this.listUsers()
          } else {
            this.$alert(resp.message)
          }
        })
      },
      editUser(userId) {
        findUserById(userId).then(resp => {
          if (resp.status === 0) {
            this.dialogFormVisible = true
            this.selectedUser = resp.data
            let roleIds = []
            for (let i = 0; i < this.selectedUser.roles.length; i++) {
              roleIds.push(this.selectedUser.roles[i].id)
            }
            this.selectedRolesIds = roleIds
          }
        })
      },
      resetPassword(username) {
        const userReq = {
          'username': username
        }
        resetPassword(userReq).then(resp => {
          if (resp.status === 0) {
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

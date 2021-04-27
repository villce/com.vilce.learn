<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard'}">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/secret'}">内容管理</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/secret'}">秘密花园管理</el-breadcrumb-item>
        <el-breadcrumb-item>模块编辑</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card style="margin: 18px 2%;width: 95%;height: 80%">
      <el-row>
        <el-input
          v-model="modules.modulesTitle"
          style="margin: 10px 0px;font-size: 18px;"
          placeholder="请输入标题"></el-input>
      </el-row>
      <el-row>
        <el-date-picker
          v-model="modules.modulesDate"
          style="margin: 10px 0px;font-size: 18px;"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间">
        </el-date-picker>
      </el-row>
      <el-row style="text-align: left">
        <el-upload
          id="selectfiles"
          :file-list="imageList"
          list-type="picture-card"
          action="http://localhost:8006/api/secret/coversUpload"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
          :on-success="handlerSuccess"
          multiple
        >
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt>
        </el-dialog>
      </el-row>
      <el-row style="margin: 10px 0px;font-size: 18px;">
        <el-button type="info" @click="cancelEdit">取消</el-button>
        <el-button type="success" @click="saveModules(modules)">确认</el-button>
      </el-row>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "SecretModulesEditor",
    data() {
      return {
        modules: {},
        imgUrlList: [],
        imageList: [],
        deleteUrl: '',
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false
      }
    },
    mounted() {
      if (this.$route.params.modules) {
        this.modules = this.$route.params.modules;
        this.imgUrlList = this.modules.imgUrlList;
        for (var i = 0; i < this.modules.imgUrlList.length; i++) {
          var image = {
            'name': i,
            'url': this.modules.imgUrlList[i]
          };
          this.imageList.push(image);
        }
      }
    },
    methods: {
      handleRemove(file) {
        var url = file.url;
        this.imgUrlList = this.imgUrlList.filter(function (item) {
          return item != url;
        });
        this.$message.warning('移除成功');
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
      },
      handlerSuccess(response) {
        this.imgUrlList.push(response.data);
      },
      saveModules(modules) {
        this.$axios.post("/secret/editModules", {
          id: modules.id,
          modulesDate: modules.modulesDate,
          modulesTitle: modules.modulesTitle,
          imgUrlList: this.imgUrlList
        }).then(resp => {
          if (resp && resp.data.status === 0) {
            this.$message({
              type: 'info',
              message: resp.data.message
            })
            this.$router.push(
              {
                name: 'SecretManagement',
              }
            )
          }
        })
      },
      cancelEdit() {
        this.$router.push(
          {
            name: 'SecretManagement'
          }
        )
      }
    }
  }
</script>

<style scoped>
</style>

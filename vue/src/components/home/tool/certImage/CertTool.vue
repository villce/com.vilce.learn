<template>
  <el-container>
    <el-header>
      <el-row style="float: right">
        <el-button type="primary" :disabled="changeBgDisabled" @click="changBg">生成证件照</el-button>
        <el-button type="success" :disabled="downloadDisabled" @click="downloadImg">下载</el-button>
      </el-row>
    </el-header>
    <el-container>
      <el-aside style="width: 400px">
        <el-form>
          <el-form-item label="源图片" prop="sourceImg">
            <el-upload
              :action="coversUploadUrl"
              list-type="picture-card"
              :limit="1"
              :on-change="handleChange"
              :on-remove="handleRemove"
              :class="{hide:hideUploadCard}">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-collapse>
              <el-collapse-item title="证件类型" name="1">
                <el-radio
                  v-model="imageSize" label="1"
                  style="width: 100px;height: 40px"
                  border>
                  <span style="color: #000000">原尺寸</span>
                </el-radio>
                <el-radio
                  v-model="imageSize" label="2"
                  style="width: 100px;height: 40px"
                  border>
                  <span style="color: #000000">一寸</span>
                </el-radio>
                <el-radio
                  v-model="imageSize" label="3"
                  style="width: 100px;height: 40px"
                  border>
                  <span style="color: #000000">两寸</span>
                </el-radio>
              </el-collapse-item>
              <el-collapse-item title="换背景" name="2">
                <el-radio
                  v-model="imageBgColor" label="1"
                  style="width: 100px;height:40px;background-color: #ffffff"
                  border>
                  <span style="color: #000000">白色</span>
                </el-radio>
                <el-radio
                  v-model="imageBgColor" label="2"
                  style="width: 100px;height:40px;background-color: #FF0000"
                  border>
                  <span style="color: #000000">红色</span>
                </el-radio>
                <el-radio
                  v-model="imageBgColor" label="3"
                  style="width: 100px;height:40px;background-color: #438EDB"
                  border>
                  <span style="color: #000000">蓝色</span>
                </el-radio>
              </el-collapse-item>
            </el-collapse>
          </el-form-item>
        </el-form>
      </el-aside>
      <el-main>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="height:540px;">
          <el-image
            :src="certImg"
            alt=""
            fit="contain"
            style="width:700px;height:540px;"></el-image>
        </el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import { getBasicUrl, downloadImage } from "../../../../utils/basic";

  export default {
    name: "CertTool",
    data() {
      return {
        certImg: '',
        coversUploadUrl: '',
        changeBgDisabled: true,
        downloadDisabled: true,
        hideUploadCard: false,
        sourceFile: null,
        width: 0,
        height: 0,
        color: '',
        imageSize: '1',
        imageBgColor: '1',
      }
    },
    mounted() {
      this.coversUploadUrl = getBasicUrl() + '/image/toolUpload';
    },
    methods: {
      handleRemove(file, fileList) {
        this.hideUploadCard = fileList.length >= 1;
        this.sourceFile = null;
        this.certImg = null;
        this.changeBgDisabled = true;
      },
      handleChange(file, fileList) {
        this.hideUploadCard = fileList.length >= 1;
        this.sourceFile = file.raw;
        if (this.hideUploadCard) {
          this.changeBgDisabled = false;
        }
      },
      changBg() {
        switch (this.imageSize) {
          case "1":
            this.width = 0;
            this.height = 0;
            break;
          case "2":
            this.width = 295;
            this.height = 412;
            break;
          case "3":
            this.width = 390;
            this.height = 567;
            break;
        }
        switch (this.imageBgColor) {
          case "1":
            this.color = '#FFFFFF';
            break;
          case "2":
            this.color = '#FF0000';
            break;
          case "3":
            this.color = '#438EDB';
            break;
        }
        var formData = new FormData();
        formData.append("sourceFile", this.sourceFile);
        formData.append('width', this.width);
        formData.append('height', this.height);
        formData.append('color', this.color);
        this.$axios.post('/image/changBg', formData).then(resp => {
          if (resp && resp.data.status === 0) {
            this.$message({
              type: 'info',
              message: resp.data.message
            })
            //这里的data数据是后台返回来的，byte是params中的键值
            this.certImg = 'data:image/png;base64,' + resp.data.data;
            this.$emit('onSubmit')
            this.downloadDisabled = false
          }
        })
      },
      downloadImg() {
        downloadImage(this.certImg);
      }
    }
  }
</script>

<style>
  .hide .el-upload--picture-card {
    display: none;
  }
</style>

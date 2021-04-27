<template>
  <el-upload
    class="img-upload"
    ref="upload"
    action="http://localhost:8006/api/image/coversUpload"
    :on-remove="handleRemove"
    :on-success="handleSuccess"
    list-type="picture"
    :file-list="fileList"
    :before-upload = "beforeAvatarUpload">
    <el-button size="small" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2M</div>
  </el-upload>
</template>

<script>
export default {
  name: 'ImgUpload',
  data () {
    return {
      fileList: [],
      url: ''
    }
  },
  methods: {
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isPG = (isJPG || isPNG);                                      //限制图片格式为jpg / png
      const isLt2M = file.size / 1024 / 1024 < 2;                         //限制图片大小
      if (!isPG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isPG && isLt2M
    },
    handleRemove(file, fileList) {
      var _this = this;
      this.$axios.get('/image/deleteImage?imageUrl=' + this.url).then(resp => {
        if (resp && resp.data.status === 0) {
          var result = resp.data.data;
          if (result) {
            this.$message.warning('移除成功')
          }
        }
      });
    },
    handleSuccess (response) {
      this.url = response.data;
      this.$emit('onUpload');
      this.$message.warning('上传成功')
    },
  }
}
</script>

<style scoped>
</style>

<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <div style="text-align: left">
        <el-form v-model="form" style="text-align: left" ref="dataForm">
          <el-form-item label="源图片" :label-width="formLabelWidth" prop="sourceImg">
            <el-upload
              action="https://www.mocky.io/v2/5185415ba171ea3a00704eed/posts/"
              list-type="picture-card"
              :limit="1"
              :on-change="handleChange"
              :on-remove="handleRemove"
              :class="{hide:hideUploadCard}">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="铺满水印" :label-width="formLabelWidth" prop="paved">
            <el-switch
              v-model="form.paved"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="changePaved">
            </el-switch>
          </el-form-item>
          <el-form-item label="文字颜色" :label-width="formLabelWidth" prop="color">
            <el-color-picker v-model="form.color" :predefine="predefineColors"></el-color-picker>
          </el-form-item>
          <el-form-item label="文字大小" :label-width="formLabelWidth" prop="wordSize">
            <el-slider
              :min="0"
              :max="100"
              v-model="form.wordSize"
              show-input>
            </el-slider>
          </el-form-item>
          <el-form-item label="水印旋转角度" :label-width="formLabelWidth" prop="degree">
            <el-slider
              :min="-90"
              :max="90"
              v-model="form.degree"
              show-input>
            </el-slider>
          </el-form-item>
          <el-form-item label="水印X轴偏移量" v-show="changeX_visible" :label-width="formLabelWidth" prop="changeX">
            <el-slider
              :min="-1020"
              :max="1020"
              v-model="form.changeX"
              show-input>
            </el-slider>
          </el-form-item>
          <el-form-item label="水印Y轴偏移量" v-show="changeY_visible" :label-width="formLabelWidth" prop="changeY">
            <el-slider
              :min="-510"
              :max="510"
              v-model="form.changeY"
              show-input>
            </el-slider>
          </el-form-item>
          <el-form-item label="水印文字" :label-width="formLabelWidth" prop="word">
            <el-input
              type="textarea"
              :rows="2"
              maxlength="20"
              show-word-limit
              placeholder="请输入水印文字"
              v-model="form.word">
            </el-input>
          </el-form-item>
        </el-form>
        <el-button class="add-button" type="success" @click="markImg">为图片生成文字水印</el-button>
        <el-button class="download-button" type="warning" :disabled="downloadStatus" @click="downloadMarkImg">下载水印图片
        </el-button>
      </div>
    </el-col>
    <el-col :span="6">
      <div style="text-align: left">
        <span class="demonstration">水印图片</span>
        <el-image :src="markImgSrc" style="width: 1020px; height: 510px"></el-image>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: 'ImgWaterMark',
    data() {
      return {
        hideUploadCard: false,
        markImgSrc: 'http://120.55.169.142:8006/image/file/0.png',
        changeX_visible: false,
        changeY_visible: false,
        downloadStatus: true,
        form: {
          word: '',
          wordSize: 50,
          color: '#000000',
          degree: 0,
          paved: true,
          changeX: 0,
          changeY: 0
        },
        sourceFile: null,
        predefineColors: [
          '#ff4500',
          '#ff8c00',
          '#ffd700',
          '#90ee90',
          '#00ced1',
          '#1e90ff',
          '#c71585',
          '#000000',
          '#999',
          '#FFFFFF'
        ],
        formLabelWidth: '120px'
      }
    },
    methods: {
      changePaved() {
        if (this.form.paved) {
          this.changeX_visible = false;
          this.changeY_visible = false;
        } else {
          this.changeX_visible = true;
          this.changeY_visible = true;
        }
      },
      handleRemove(file, fileList) {
        this.hideUploadCard = fileList.length >= 1;
        this.markImgSrc = 'http://120.55.169.142:8006/image/file/0.png';
      },
      handleChange(file, fileList) {
        this.hideUploadCard = fileList.length >= 1;
        this.sourceFile = fileList[0].raw;
      },
      markImg() {
        var formData = new FormData();
        formData.append("sourceFile", this.sourceFile);
        formData.append('word', this.form.word);
        formData.append('wordSize', this.form.wordSize);
        formData.append('color', this.form.color);
        formData.append('degree', this.form.degree);
        formData.append('paved', this.form.paved);
        formData.append('changeX', this.form.changeX);
        formData.append('changeY', this.form.changeY);
        this.$axios
          .post('/mark/wordToImage', formData).then(resp => {
          if (resp && resp.data.status === 0) {
            this.$message({
              type: 'info',
              message: resp.data.message
            })
            //这里的data数据是后台返回来的，byte是params中的键值
            this.markImgSrc = 'data:image/png;base64,' + resp.data.data;
            this.$emit('onSubmit')
            this.downloadStatus = false
          }
        })
      },
      downloadMarkImg() {
        //实例化一个img对象
        const img = new Image();
        //设置img的图片路径
        img.src = this.markImgSrc;
        //设置跨域解决
        img.setAttribute('crossOrigin', 'Anonymous');
        //img加载完后处理
        img.onload = function () {
          //创建一个canvas对象
          const canvas = document.createElement('canvas')
          //把图片的宽度设为canves的宽度
          canvas.width = img.width
          //把图片的高度设为canves的高度
          canvas.height = img.height
          //创建一个2d画布
          const ctx = canvas.getContext('2d')
          // 将img中的内容画到画布上
          ctx.drawImage(img, 0, 0, canvas.width, canvas.height)
          // 将画布内容转换为base64
          let base64 = canvas.toDataURL()
          // 创建a链接
          const a = document.createElement('a')
          a.href = base64
          a.download = 'textMark'
          // 触发a链接点击事件，浏览器开始下载文件
          a.click()
        }
      }
    }
  }
</script>

<style>
  .hide .el-upload--picture-card {
    display: none;
  }
</style>

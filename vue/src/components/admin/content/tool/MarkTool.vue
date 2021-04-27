<template>
  <el-tabs el-tabs type="border-card">
    <el-tab-pane v-show="true">
      <span slot="label"><i class="el-icon-edit-outline"></i> 透明水印图片</span>
      <NewWaterMark></NewWaterMark>
    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="el-icon-picture-outline"></i> 图片添加文字水印</span>
      <ImgWaterMark></ImgWaterMark>
    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="el-icon-picture"></i> 图片添加图片水印</span>
      <ImgMarkImg></ImgMarkImg>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
  import NewWaterMark from './NewWaterMark'
  import ImgWaterMark from './ImgWaterMark'
  import ImgMarkImg from './ImgMarkImg'
  export default {
    name: 'EditForm',
    components: {NewWaterMark, ImgWaterMark, ImgMarkImg},
  }
</script>

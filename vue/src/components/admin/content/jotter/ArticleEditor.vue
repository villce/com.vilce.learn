<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard'}">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/article'}">内容管理</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/article'}">文章管理</el-breadcrumb-item>
        <el-breadcrumb-item>编辑器</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row>
      <el-input
        v-model="article.title"
        style="margin: 10px 0px;font-size: 18px;"
        placeholder="请输入标题"></el-input>
    </el-row>
    <el-row style="height: calc(100vh - 140px); text-align: left">
      <mavon-editor
        v-model="article.contentMd"
        style="height: 100%;"
        ref=md
        @save="saveArticles"
        fontSize="16px">
        <button type="button" class="op-icon el-icon-document" :title="'标签/分类'" slot="left-toolbar-after"
                @click="dialogVisible = true"></button>
      </mavon-editor>
      <el-dialog
        :visible.sync="dialogVisible"
        width="30%">
        <el-divider content-position="left">文章标签</el-divider>
        <el-tag
          v-model="article.label"
          :key="tag"
          v-for="tag in dynamicTags"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)">
          {{tag}}
        </el-tag>
        <el-input
          class="input-new-tag"
          v-if="inputVisible"
          v-model="inputValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 添加新标签</el-button>
        <el-divider content-position="left">文章类型</el-divider>
        <el-select v-model="article.type" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: 'ArticleEditor',
    data() {
      return {
        dynamicTags: [],
        inputVisible: false,
        inputValue: '',
        article: {},
        dialogVisible: false,
        options: [{
          value: '笔记',
          label: '笔记'
        }, {
          value: '文章',
          label: '文章'
        }],
        value: ''
      }
    },
    mounted() {
      if (this.$route.params.article) {
        this.article = this.$route.params.article;
        this.dynamicTags = this.article.label;
      }
    },
    methods: {
      handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },
      showInput() {
        this.inputVisible = true;
        this.$nextTick(_this => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      saveArticles(value, render) {
        // value 是 md，render 是 html
        this.$confirm('是否保存并发布文章?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.info(this.dynamicTags);
          console.info(this.article);
          this.$axios.post('/article/saveArticle', {
              id: this.article.id,
              article_type: this.article.type,
              article_label: this.dynamicTags,
              article_title: this.article.title,
              article_content_md: this.article.contentMd,
              article_content_html: render,
              article_date: new Date()
            }).then(resp => {
              if (resp && resp.data.status === 0) {
                this.$message({
                  type: 'info',
                  message: resp.data.message
                })
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发布'
          })
        })
      }
    }
  }
</script>

<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>

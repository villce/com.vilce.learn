<template>
    <div style="height:100%;width:100%">
        <el-container>
            <el-header>
                <el-input
                        v-model="article.title"
                        style="font-size: 18px;margin-top: 20px"
                        placeholder="请输入标题"></el-input>
            </el-header>
            <el-main style="height: calc(100vh - 140px); text-align: left">
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
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import {getOneArticle, saveArticle} from "@/api/article/article";

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
            if (this.$route.query.id) {
                this.getArticleDetail();
            }
        },
        methods: {
            async getArticleDetail() {
                try {
                    getOneArticle(this.$route.query.id).then(resp => {
                        if (resp.status === 0) {
                            this.article = resp.data;
                            this.dynamicTags = this.article.label;
                        }
                    });
                } catch (e) {
                    global.console.log("文章获取异常");
                }
            },
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
                        const articleReq = {
                            'id': this.article.id,
                            'article_type': this.article.type,
                            'article_label': this.dynamicTags,
                            'article_title': this.article.title,
                            'article_content_md': this.article.contentMd,
                            'article_content_html': render,
                            'article_date': new Date()
                        }
                        saveArticle(articleReq).then(resp => {
                            if (resp.status === 0) {
                                this.$message({
                                    type: 'success',
                                    message: '文章发布成功！'
                                })
                            }
                            this.$router.replace({
                                path: '/content/article'
                            })
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

<style scoped>
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

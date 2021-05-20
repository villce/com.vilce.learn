<template>
    <el-container>
        <el-header>
            <el-button type="success" @click="addModule" style="margin-top: 20px">添加模块</el-button>
        </el-header>
        <el-main>
            <el-card>
                <el-table
                        :data="moduleList"
                        stripe
                        style="width: 100%">
                    <el-table-column label="序号" type="index" fixed width="55"></el-table-column>
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
                            width="100">
                        <template slot-scope="scope">
                            <el-button
                                    @click.native.prevent="updateModule(scope.row)"
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
        </el-main>
        <el-dialog title="编辑图片模块" :visible.sync="editSecretVisible" @close="clear">
            <el-form v-model="module" style="text-align: left">
                <el-form-item label="模块标题" prop="modulesTitle" label-width="70px" style="width: 320px">
                    <el-input v-model="module.modulesTitle" placeholder="请输入标题"></el-input>
                </el-form-item>
                <el-form-item label="模块日期" prop="modulesDate" label-width="70px">
                    <el-date-picker
                            v-model="module.modulesDate"
                            style="margin: 10px 0px;width: 250px"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="模块图片" prop="imageFileList" label-width="70px">
                    <el-upload
                            id="selectfiles"
                            :file-list="imageFileList"
                            list-type="picture-card"
                            :action="coversImageUrl"
                            :on-remove="handleRemove"
                            :on-success="handlerSuccess"
                            multiple
                    >
                        <i class="el-icon-plus"></i>
                    </el-upload>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="info" @click="clear">取消</el-button>
                <el-button type="success" @click="editModule">确认</el-button>
            </div>
        </el-dialog>
    </el-container>
</template>

<script>
    import {deleteModules, editModules, getModules} from "@/api/secret/secret";
    import {getServer, vilceServerConfig} from "@/utils/request";

    export default {
        name: "SecretManagement",
        data() {
            return {
                modulesStatistic: [],
                moduleList: [],
                pageSize: 4,
                total: 1,
                currentPage: 1,
                coversImageUrl: '',
                module: {
                    'id': 0,
                    'modulesTitle': '',
                    'modulesDate': '',
                    'imgUrlList': []
                },
                imageList: new Set(),
                imageFileList: [],
                editSecretVisible: false
            }
        },
        mounted() {
            this.loadModules(1);
            this.coversImageUrl = getServer(vilceServerConfig) + '/secret/coversImage';
        },
        methods: {
            loadModules(page) {
                getModules(page, this.pageSize).then(resp => {
                    if (resp.status === 0) {
                        this.moduleList = resp.data.modulesList;
                        this.total = resp.data.num;
                    }
                })
            },
            handleCurrentChange(page) {
                this.currentPage = page
                this.loadModules(page)
            },
            addModule() {
                this.editSecretVisible = true
            },
            handleRemove(file) {
                console.info(file)
                this.imageList.delete(file.url);
                this.$message.warning('移除成功');
            },
            handlerSuccess(response) {
                this.imageList.add(response.data);
            },
            editModule() {
                const moduleReq = {
                    'id': this.module.id,
                    'modulesDate': this.module.modulesDate,
                    'modulesTitle': this.module.modulesTitle,
                    'imgList': Array.from(this.imageList)
                }
                console.info(moduleReq)
                editModules(moduleReq).then(resp => {
                    if (resp.status === 0) {
                        this.editSecretVisible = false;
                        this.clear();
                        this.loadModules(this.currentPage);
                        this.$message({
                            showClose: true,
                            type: 'success',
                            message: '编辑成功！'
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            type: 'error',
                            message: '编辑失败！'
                        })
                    }
                })
            },
            clear() {
                this.editSecretVisible = false;
                this.module = {};
                this.imageList = new Set();
                this.imageFileList = [];
            },
            updateModule(module) {
                module.imgUrlList.map(item => {
                    this.imageFileList.push({
                        name: item,
                        url: item
                    });
                    this.imageList.add(item);
                });
                console.info(module)
                this.module = module;
                this.editSecretVisible = true;
            },
            deleteModules(id) {
                this.$confirm('此操作将永久删除该模块, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                        deleteModules(id).then(resp => {
                            if (resp.status === 0) {
                                this.loadModules(this.currentPage);
                                this.$message({
                                    type: 'success',
                                    message: '成功删除'
                                })
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
</style>

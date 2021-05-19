<template>
    <div>
        <el-dialog title="编辑图片模块" :visible.sync="editSecretVisible" @close="clear">
            <el-form v-model="module" style="text-align: left">
                <el-form-item label="模块标题" prop="modulesTitle" label-width="70px" style="width: 320px">
                    <el-input v-model="module.modulesTitle" placeholder="请输入标题"></el-input>
                </el-form-item>
                <el-form-item label="模块日期" prop="modulesDate" label-width="70px">
                    <el-date-picker
                            v-model="module.modulesDate"
                            style="margin: 10px 0px;font-size: 18px;width: 250px"
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
                <el-button type="info" @click="editSecretVisible = false">取消</el-button>
                <el-button type="success" @click="editModules()">确认</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {editModules} from "@/api/secret/secret";
    import {getServer, vilceServerConfig} from "@/utils/request";

    export default {
        name: "SecretModulesEditor",
        data() {
            return {
                coversImageUrl: '',
                module: {},
                imageList: new Set(),
                imageFileList: [],
                editSecretVisible: false
            }
        },
        mounted() {
            this.coversImageUrl = getServer(vilceServerConfig) + '/secret/coversImage';
        },
        methods: {
            handleRemove() {
                this.$message.warning('移除成功');
            },
            handlerSuccess(response) {
                this.imageList.add(response.data);
            },
            editModules() {
                console.info(this.imageList)
                const moduleReq = {
                    'id': this.module.id,
                    'modulesDate': this.module.modulesDate,
                    'modulesTitle': this.module.modulesTitle,
                    'imgList': this.imageList
                }
                editModules(moduleReq).then(resp => {
                    if (resp.status === 0) {
                        this.editSecretVisible = false;
                        this.clear();
                        this.$message({
                            showClose: true,
                            type: 'success',
                            message: '更新成功！'
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            type: 'error',
                            message: '更新失败！'
                        })
                    }
                })
            },
            clear() {
                this.module = {};
                this.imageList = new Set();
                this.imageFileList = [];
            }
        }
    }
</script>

<style scoped>
</style>

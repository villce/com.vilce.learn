<template>
    <el-container>
        <el-aside style="width: 400px;text-align: center;">
            <el-form style="margin-top: 20px;margin-left: 20px">
                <el-form-item>
                    <el-collapse>
                        <el-collapse-item title="图片长宽压缩比" name="1">
                            <el-slider
                                    style="margin-left: 20px"
                                    :min="0"
                                    :max="100"
                                    v-model="scale"
                                    show-input>
                            </el-slider>
                        </el-collapse-item>
                        <el-collapse-item title="图片质量压缩比" name="2">
                            <el-slider
                                    style="margin-left: 20px"
                                    :min="0"
                                    :max="100"
                                    v-model="quality"
                                    show-input>
                            </el-slider>
                        </el-collapse-item>
                    </el-collapse>
                </el-form-item>
                <el-form-item>
                    <el-upload
                            drag
                            :action="coversUploadUrl"
                            :on-success="uploadSuccess"
                            :on-remove="removeImage"
                            multiple>
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
                    </el-upload>
                </el-form-item>
            </el-form>
        </el-aside>
        <el-main>
            <el-card style="float: left">
                <el-button type="primary" style="margin: 0 auto" @click="compressImage">压缩</el-button>
                <el-table
                        :data="sourceImageList"
                        @select="selectImage"
                        style="width: 100%">
                    <el-table-column
                            align="center"
                            type="selection"
                            width="50px">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="原图片"
                            width="170px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.sourceImageName }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="原图片大小"
                            width="150px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.sourceImageSize }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="压缩图片大小"
                            width="150px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.compressImageSize }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作"
                            fit>
                        <template slot-scope="scope">
                            <el-button
                                    @click.native.prevent="downloadCompress(scope.row)"
                                    type="text"
                                    size="mini">
                                下载
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-card>
        </el-main>
    </el-container>
</template>

<script>
    import {getServer, vilceServerConfig} from "@/utils/request";
    import {compress} from "@/api/tool/image";
    import {downloadByUrl} from "@/utils/download";

    export default {
        name: "CompressImg",
        data() {
            return {
                coversUploadUrl: '',
                sourceImageList: [],
                scale: 100,
                quality: 75,
                imageSelect: [],
            }
        },
        mounted() {
            this.coversUploadUrl = getServer(vilceServerConfig) + '/image/imageUpload';
        },
        methods: {
            uploadSuccess(response) {
                this.sourceImageList.push(response.data)
            },
            removeImage(file) {
                var newArr=[];
                for (var i=0;i < this.sourceImageList.length; i++) {
                    if (this.sourceImageList[i].sourceImageName != file.name) {
                        newArr.push(this.sourceImageList[i]);
                    }
                }
                this.sourceImageList = newArr;
            },
            selectImage(selection) {
                this.imageSelect = selection;
            },
            compressImage() {
                if (this.imageSelect.length > 0) {
                    const compressReq = {
                        'scale': this.scale,
                        'quality': this.quality,
                        'imageList': this.imageSelect
                    }
                    compress(compressReq).then(response => {
                        if (response.status === 0) {
                            response.data.map(image => {
                                var newArr=[];
                                for (var i=0;i < this.sourceImageList.length; i++) {
                                    if (this.sourceImageList[i].sourceImageName === image.sourceImageName) {
                                        newArr.push(image);
                                    } else {
                                        newArr.push(this.sourceImageList[i]);
                                    }
                                }
                                this.sourceImageList = newArr;
                            })
                            this.imageSelect = [];
                            this.$message({
                                showClose: true,
                                message: '压缩完成！',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                showClose: true,
                                message: '压缩失败！',
                                type: 'error'
                            });
                        }
                    })
                }
            },
            downloadCompress(compressImage) {
                if (compressImage.compressImageUrl != null) {
                    var url = compressImage.compressImageUrl;
                    var name = compressImage.sourceImageName;
                    console.info(url);
                    downloadByUrl(url, name);
                }
            }
        }
    }
</script>

<style scoped>

</style>

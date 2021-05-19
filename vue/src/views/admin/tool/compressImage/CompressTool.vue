<template>
    <el-container>
        <el-main>
            <el-upload
                    style="text-align: center"
                    drag
                    :action="coversUploadUrl"
                    multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
            </el-upload>
        </el-main>
    </el-container>
</template>

<script>
    import {getServer, vilceServerConfig} from "@/utils/request";

    export default {
        name: "CompressImg",
        data() {
            return {
                coversUploadUrl: '',
            }
        },
        mounted() {
            this.coversUploadUrl = getServer(vilceServerConfig) + '/image/toolUpload';
        },
    }
</script>

<style scoped>

</style>

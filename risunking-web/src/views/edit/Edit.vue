<template>
<div>
    <div class="title-container">
        <el-input class="title-style" placeholder="请输入文章资源编号" v-model="articleId">
            <template slot="prepend">ID：</template>
        </el-input>
        <el-input class="title-style" placeholder="请输入标题" v-model="articleTitle">
            <template slot="prepend">标题：</template>
        </el-input>
        <el-input class="title-style-type" placeholder="请输入类型编码" v-model="articleType">
            <template slot="prepend">类型：</template>
        </el-input>
        <el-button class="btn-style" type="primary" @click="getArticle" plain>查询</el-button>
        <el-button class="btn-style" type="primary" @click="resetArticle" plain>重置</el-button>
        <el-button class="btn-style" type="primary" @click="saveArticle" plain>保存</el-button>
    </div>
    <div class="desc-cover-container">
        <el-upload
            class="cover-uploader"
            :action="uploadUrl"
            :show-file-list="false"
            :on-success="handleCoverSuccess">
            <img v-if="cover" :src="coverImg" class="cover-style">
            <i v-else class="el-icon-plus cover-uploader-icon"></i>
        </el-upload>
        <el-input
            class="desc-textarea"
            type="textarea"
            placeholder="请输入摘要"
            v-model="articleDesc"
            maxlength="200"
            show-word-limit>
        </el-input>        
    </div>
    
    <el-upload
        class="img-uploader"
        :action="uploadUrl"
        :show-file-list="false"
        :on-success="handleImgUploadSuccess">
        <i class="quill-uploader"></i>
    </el-upload>
    <quill-editor  
        ref="myQuillEditor"
        v-model="content"
        :options="editorOption" 
        @blur="onEditorBlur($event)" 
        @focus="onEditorFocus($event)"
        @change="onEditorChange($event)">
    </quill-editor>
    <div style="width:100%;height:10px;margin-top:60px;"></div>
</div>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
// 工具栏配置
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],
    
      [{'header': 1}, {'header': 2}],                   // custom button values
      [{'list': 'ordered'}, {'list': 'bullet'}],
      [{'script': 'sub'}, {'script': 'super'}],         // superscript/subscript
      //[{'indent': '-1'}, {'indent': '+1'}],           // outdent/indent
      [{'direction': 'rtl'}],                           // text direction
    
      [{'size': ['small', 'normal', 'large', 'huge']}],    // custom dropdown
      [{'header': [1, 2, 3, 4, 5, 6, 'normal']}],

      [{'color': []}, {'background': []}],              // dropdown with defaults from theme
      [{'font': []}],
      [{'align': []}],
      ['link', 'image', 'video'],
      //['clean']                                       // remove formatting button
    ]
export default {
    data:function(){
        return{
            uploadUrl:this.$store.state.baseUrl + this.$store.state.uploadUrl,
            articleId:'',
            resourcePath:'',
            articleTitle:'',
            articleType:'00',
            articleDesc:'',
            content:'',
            cover:'',
            coverImg:'',
            editorOption:{
                modules: {
                    toolbar: {
                        container: toolbarOptions,
                        handlers: {
                            'image': function (value) {
                                if (value) {
                                    document.querySelector('.quill-uploader').click()
                                } else {
                                    this.quill.format('image', false);
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    methods:{
        getParams(){
            if(this.$route.query){
                this.resourcePath = this.$route.query.resourcePath||'';
                this.articleTitle = this.$route.query.articleTitle||'';
                this.articleDesc = this.$route.query.articleDesc||'';
                this.articleType = this.$route.query.articleType||'00';
                this.cover = this.$route.query.cover||'';
                this.coverImg = this.$store.state.baseUrl+this.$store.state.downloadUrl+"?fileName="+this.cover+'&tsp='+this.$utils.getTsp();
                this.articleId = this.$route.query.articleId||'';
            }
        },
        // 准备编辑器
        onEditorReady(editor) { 
        },
        //失去焦点事件
        onEditorBlur(editor){},
         //获得焦点事件
        onEditorFocus(editor){
            //editor.enable(false);
        },
        //编辑器文本发生变化
        onEditorChange({editor,html,text}){},
        // 获取文章内容
        getArticle(){
            var params = {
                'id':this.articleId
            }
            this.$jsonPost('web/resource/articles/detailById',params)
            .then((response)=>{
                var contentStr = response.result.content.replace(/tsp=(.\d*)"/g,'tsp='+this.$utils.getTsp()+'"');
                this.content = contentStr;
                this.resourcePath = response.result.path||'';
                this.articleTitle = response.result.title||'';
                this.articleDesc = response.result.desc||'';
                this.articleType = response.result.type||'00';
                this.cover = response.result.cover||'';
                this.coverImg = this.$store.state.baseUrl+this.$store.state.downloadUrl+"?fileName="+this.cover+'&tsp='+this.$utils.getTsp();
                this.articleId = response.result.id||'';
            });
        },
        // 重置文章内容
        resetArticle(){
            if(this.articleId === ''){
                this.content = '';
            }else{
                this.getArticle();
            }
        },
        // 保存文章内容
        saveArticle(){
            if(this.articleId === ''){
                var params = {
                    'title':this.articleTitle,
                    'type':this.articleType,
                    'path':'',
                    'desc':this.articleDesc,
                    'content':this.content,
                    'url':'',
                    'cover':this.cover
                }
                this.$jsonPost('web/resource/articles/add',params)
                .then((response)=>{
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    });
                });
            }else{
                var params = {
                    'title':this.articleTitle,
                    'path':this.resourcePath,
                    'desc':this.articleDesc,
                    'content':this.content,
                    'url':'',
                    'id':this.articleId,
                    'cover':this.cover
                }
                this.$jsonPost('web/resource/articles/update',params)
                .then((response)=>{
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    });
                });
            } 
        },
        handleCoverSuccess(res){
            if (res) {
                this.cover = res;
                this.coverImg = this.$store.state.baseUrl + this.$store.state.downloadUrl + '?fileName='+res+'&tsp='+this.$utils.getTsp()
            }else {
                // 提示信息，需引入Message
                Message.error('封面上传失败')
            }
        },
        // 处理图片上传
        handleImgUploadSuccess(res){
            // 获取富文本组件实例
            let quill = this.$refs.myQuillEditor.quill
            // 如果上传成功
            if (res) {
                // 获取光标所在位置
                let length = quill.getSelection().index;
                // 插入图片，res为服务器返回的图片链接地址
                let imgPath = this.$store.state.baseUrl + this.$store.state.downloadUrl + '?fileName='+res+'&tsp='+this.$utils.getTsp()
                console.log(imgPath);
                quill.insertEmbed(length, 'image', imgPath)
                // 调整光标到最后
                quill.setSelection(length + 1)
            } else {
                // 提示信息，需引入Message
                Message.error('图片插入失败')
            }
        }
    },
    created(){
        this.getParams();
        if(this.resourcePath !== ''){
            this.getArticle();
        }
    }
}
</script>

<style>
.title-container{
    display: flex;
    width:100%; 
    flex-direction:row;
    margin-bottom: 10px;
    justify-content:center;
}
.title-style-type{
    width: 12%;
    margin-right: 2%;
}
.title-style{
    width: 23%;
    margin-right: 2%;
}
.btn-style{
    width: 8%;
}
.img-uploader{
    display: none;
}
.desc-cover-container{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 90%;
    margin-left: 5%;
    margin-bottom: 10px;
}
.desc-textarea{
    width: 90%;
}
.cover-uploader{
    border: 1px dashed #409EFF;
    border-radius: 4px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.cover-uploader-icon {
    color: #8c939d;
    width: 80px;
    height: 54px;
    line-height: 54px;
    text-align: center;
}
.cover-style {
    width: 80px;
    height: 54px;
    display: block;
}
.quill-editor {
    height: 500px;
    width: 90%;
    margin-left: 5%;
}
.el-tabs{
    display: none;
}
</style>
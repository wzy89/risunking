<template>
<div>
    <h2>{{articleTitle}}</h2>
    <div class="ql-container ql-snow ql-class-container">    
        <div class="ql-editor">
            <div v-html="content">
            </div>
        </div>
    </div>
</div>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
// 工具栏配置
const toolbarOptions = []
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
                        handlers: {}
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
        onEditorReady() { 
            let quill = this.$refs.myQuillEditor.quill
            quill.enable(false);
        },
        //失去焦点事件
        onEditorBlur(){
            let quill = this.$refs.myQuillEditor.quill
            quill.enable(false);
        },
         //获得焦点事件
        onEditorFocus(){
            let quill = this.$refs.myQuillEditor.quill
            quill.enable(false);
        },
        //编辑器文本发生变化
        onEditorChange(){
            let quill = this.$refs.myQuillEditor.quill
            quill.enable(false);
        },
        // 获取文章内容
        getArticle(){
            var params = {
                'path':this.resourcePath
            }
            this.$jsonPost('web/resource/articles/detail',params)
            .then((response)=>{
                var contentStr = response.result.content.replace(/tsp=(.\d*)"/g,'tsp='+this.$utils.getTsp()+'"');
                this.content = contentStr;
            });
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
.el-tabs{
    display: none;
}
.ql-class-container {
    width: 100%;
    border: 0;
    border-color: white;
    height: 100%;
}
.ql-container.ql-snow{
    border: 0;
    border-color: white;
}
.ql-editor{
    border-color: white;
    width: 70%;
    margin-left: 15%;
}
</style>
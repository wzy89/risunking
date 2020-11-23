<template>
    <div class="infinite-list-wrapper" style="overflow:auto;height:100%;">
        <ul v-infinite-scroll="loadMoreArts" :infinite-scroll-disabled="canNotLoadMore">
            <div class="code-cell-container" v-for="item in codeList" :key="item.id">
                <codeCell class="code-cell" :randerData="item" :clickCell="clickCell" />
            </div>
        </ul>
        <p v-if="loading" style="color:#666;font-size:13px;text-align:center;">加载中...</p>
        <p v-if="noMore" style="color:#666;font-size:13px;text-align:center;">没有更多数据</p>
    </div>
</template>

<script>
import codeCell from "@/components/cell/code_read_cell.vue";
export default {
    components: {
        codeCell
    },
    data() {
        return {
            codeList: [],
            page:1,
            totalCount:0,
            loading:false
        };
    },
    created() {
        this.getList();
    },
    computed: {
      noMore () {
        return this.codeList.length >= this.totalCount;
      },
      canNotLoadMore () {
        return this.loading || this.noMore;
      }
    },
    methods: {
        clickCell(item){
            console.log(item);
            const { href } = this.$router.resolve({
                name: "Detail",
                query: { 
                    articleType: item.type,
                    resourcePath: item.path,
                    articleTitle: item.title,
                    articleDesc: item.desc,
                    cover: item.cover,
                    articleId: item.id
                }
            });
            window.open(href, '_blank');
        },
        /** 获取数据 */
        getList() {
            this.page = 1;
            this.codeList = [];
            this.totalCount = 0;
            var params = {
                'page':this.page,
                'size':10,
                'type':'00'
            }
            this.$jsonPost('web/resource/articles/list',params)
            .then((response)=>{
                this.totalCount = response.count;
                response.result.forEach((item)=>{
                    if(item.cover){
                        item.coverImg = this.$store.state.baseUrl + this.$store.state.downloadUrl + '?fileName='+item.cover+'&tsp='+this.$utils.getTsp();
                    }
                    if(item.tags){
                        item.tagList = item.tags.split(',');
                    }
                    if(item.desc.length>100){
                        item.marks = item.desc.substring(0,99)+"...";
                    }else{
                        item.marks = item.desc;
                    }
                    this.codeList.push(item);
                })
            });
        },
        /** 展示文章列表 更多*/
        loadMoreArts(){
            this.loading = true;
            this.page ++;
            var params = {
                'page':this.page,
                'size':10,
                'type':'00'
            }
            this.$jsonPost('web/resource/articles/list',params)
            .then((response)=>{
                this.loading = false;
                response.result.forEach((item)=>{
                    if(item.cover){
                        item.coverImg = this.$store.state.baseUrl + this.$store.state.downloadUrl + '?fileName='+item.cover+'&tsp='+this.$utils.getTsp();
                    }
                    if(item.tags){
                        item.tagList = item.tags.split(',');
                    }
                    if(item.desc.length>100){
                        item.marks = item.desc.substring(0,99)+"...";
                    }else{
                        item.marks = item.desc;
                    }
                    this.codeList.push(item);
                });
                this.totalCount = response.count;
            });
        }
    }
}
</script>>


<style scoped>
.code-cell-container{
    display: flex;
    justify-content:center;
}
.code-cell{
    width:60%;
}
</style>>
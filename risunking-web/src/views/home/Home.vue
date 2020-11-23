<template>
    <div class="home-main-swapper">
        <div class="home-main-container">
            <div class="home-card-container" v-for="item in topData" :key="item.id">
                <homeCard :randerData="item" :clickCard="clickCard" />
            </div>                  
        </div>
    </div>
</template>

<script>
import homeCard from "@/components/card/home_card.vue";
export default {
    components: {
        homeCard
    },
    data() {
        return {
            topData: []
        };
    },
    created() {
        this.getTops();
    },
    methods: {
        clickCard(item){
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
        getTops() {
            var params = {
                'size':16
            }
            this.$jsonPost('web/resource/articles/tops',params)
            .then((response)=>{
                response.result.forEach((item)=>{
                    if(item.cover){
                        item.coverImg = this.$store.state.baseUrl + this.$store.state.downloadUrl + '?fileName='+item.cover+'&tsp='+this.$utils.getTsp();
                    }
                    if(item.desc.length>85){
                        item.marks = item.desc.substring(0,84)+"...";
                    }else{
                        item.marks = item.desc;
                    }
                    this.topData.push(item);
                })
            });
        }
    }
}
</script>

<style scoped>
.home-main-swapper{
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: scroll;
}
.home-main-container{
    display: flex;
    flex-wrap: wrap;
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;
    padding-left: 50px;
    padding-right: 50px;
}
.home-card-container{
    display: flex;
    justify-content:center;
    width: 25%;
    height: 35vw;
}
</style>
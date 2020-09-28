<template>
    <div class="home-main-container">
        <div class="home-card-container" v-for="item in topData" :key="item.id">
            <homeCard :randerData="item" :clickCard="clickCard" />
        </div>
    </div>
</template>

<script>
import homeCard from "@/components/card/home_card.vue";
import { Loading } from 'element-ui';
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
        },
        /** 获取数据 */
        getTops() {
            Loading.service({ fullscreen: true });
            for(var i=0; i<8; i++){
                var data = {
                    id:i, 
                    imgPath:'http://www.risunking.com/web/storage/downloadFile?fileName=8eca1c38-5488-464f-9514-b2e0ec606862.png&tsp='+this.$utils.getTsp(),
                    name:'测试数据不算数的',
                    tags:'啊啊啊｜嗷嗷｜呵呵',
                    remarks:'测试数据不好看没关系，测试数据，不好看没关系;测试数据不好看没关系，测试数据，不好看没关系,测试数据不好看没关系，测试数据，不好看没关系.'
                }
                this.topData.push(data);
            }
            Loading.service({ fullscreen: true }).close();
        }
    }
}
</script>


<style scoped>
.home-main-container{
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;
    padding-left: 50px;
    padding-right: 50px;
    height: 100%;
}
.home-card-container{
    display: flex;
    justify-content:center;
    width: 25%;
    height: 35vw;
}
</style>
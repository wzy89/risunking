<template>
    <div class="home-read-container">
        <div class="read-cell-container" v-for="item in readList" :key="item.id">
            <readCell class="read-cell" :randerData="item" :clickCell="clickCell" />
        </div>
    </div>
</template>

<script>
import readCell from "@/components/cell/code_read_cell.vue";
import { Loading } from 'element-ui';
export default {
    components: {
        readCell
    },
    data() {
        return {
            readList: []
        };
    },
    created() {
        this.getTops();
    },
    methods: {
        clickCell(item){
            console.log(item);
        },
        /** 获取数据 */
        getTops() {
            Loading.service({ fullscreen: true });
            for(var i=0; i<10; i++){
                var data = {
                    id:i, 
                    imgPath:i%2==0?'http://www.risunking.com/web/storage/downloadFile?fileName=8eca1c38-5488-464f-9514-b2e0ec606862.png&tsp='+this.$utils.getTsp():'',
                    name:'测试数据不算数的',
                    tags:['啊啊啊','嗷嗷','呵呵'],
                    remarks:'测试数据不好看没关系，测试数据，不好看没关系;测试数据不好看没关系，测试数据.测试数据不好看没关系，测试数据，不好看没关系测试数据不好看没关系，测试数据，不好看没关系',
                    thumbUps:'0'
                }
                this.readList.push(data);
            }
            Loading.service({ fullscreen: true }).close();
        }
    }
}
</script>>


<style scoped>
.home-read-container{
    display: flex;
    flex-direction: column;
    justify-content:center;
}
.read-cell-container{
    display: flex;
    justify-content:center;
}
.read-cell{
    width:60%;
}
</style>>
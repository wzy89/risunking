<template>
    <div class="home-code-container">
        <div class="code-cell-container" v-for="item in codeList" :key="item.id">
            <codeCell class="code-cell" :randerData="item" :codeCell="clickCell" />
        </div>
    </div>
</template>

<script>
import codeCell from "@/components/cell/code_read_cell.vue";
import { Loading } from 'element-ui';
export default {
    components: {
        codeCell
    },
    data() {
        return {
            codeList: []
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
                this.codeList.push(data);
            }
            Loading.service({ fullscreen: true }).close();
        }
    }
}
</script>>


<style scoped>
.home-code-container{
    display: flex;
    flex-direction: column;
    justify-content:center;
}
.code-cell-container{
    display: flex;
    justify-content:center;
}
.code-cell{
    width:60%;
}
</style>>
<template>
    <el-card class="home-card" shadow="hover" @click.native="handleClickCard" :body-style="{'display':'flex','justify-content':'space-between','align-items':'start','flex-direction':'column','padding':'10px'}">
        <div class="home-card-image">
            <img :src="randerData.imgPath" style="cursor: pointer; width: 100%; height: 100%; display: block;" >
        </div>
        <div class="home-card-name">
            {{randerData.name===undefined||randerData.name===null||randerData.name===''?'无标题':randerData.name}}
        </div>
        <div class="home-card-tags">
            {{randerData.tags===undefined||randerData.tags===null||randerData.tags===''?'无标签':randerData.tags}}
        </div>
        <div class="home-card-remarks">
            {{randerData.remarks===undefined||randerData.remarks===null||randerData.remarks===''?'无简介':randerData.remarks}}
        </div>
    </el-card>
</template>

<script>
export default {
  props: {
    /** 数据源 */
    randerData: {
      type: Object,
      default: () => {
        return {
            id:'0',
            imgPath:'',
            name:'',
            tags:'',
            remarks:''
        };
      }
    },
    /** 点击卡片动作 */
    clickCard: {
      type: Function,
      default() {}
    }
  },
  /** 初始化数据 */
  data() {
    return {}
  },
  /** 创建后处理 */
  created() {},
  methods: {
    handleClickCard() {
      console.info("handleClickCard");
      const randerData = this.deepCopyJsonData(this.randerData, this.type);
      this.clickCard(randerData);
    },
    // 复制数据
    deepCopyJsonData(json) {
      return JSON.parse(JSON.stringify(json));
    }
  }
};
</script>

<style scoped>
.home-card {
    width: 88%;
    height: 90%;
    display: flex;
    justify-content: center;
    color: #666;
}
.home-card-image {
    width: 100%;
    height: 40%;
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    background: #badeff;
}
.home-card-name{
    height: 10%;
    width: 100%;
    padding-top: 20px;
    font-size: 18px;
    text-align: center;
    color: #333;
    font-weight: 500;
    line-height: 1.5;
}
.home-card-tags{
    font-size: 14px;
    height: 10%;
    width: 100%;
    color: #666;
    text-align: center;
}
.home-card-remarks{
    font-size: 13px;
    height: 35%;
    width: 100%;
    text-align: left;
    line-height: 2;
    line-height: 24px;
    color: #999;
}
</style>

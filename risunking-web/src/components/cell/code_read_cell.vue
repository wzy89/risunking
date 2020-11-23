<template>
    <div class="code-cell-container"  @click="handleClickCell">
        <!-- 标题、摘要、点赞、标签 等 -->
        <div class="code-cell-detail-container">
            <div class="code-cell-title">{{randerData.title}}</div>
            <div class="code-cell-remarks">{{randerData.marks}}</div>
            <div class="code-cell-tags">
                <el-tag class="el-icon-pear code-cell-tag">{{randerData.readNum}}</el-tag>
                <el-tag class="code-cell-tag" type="danger" :key="i" v-for="(item,i) in randerData.tagList">{{item}}</el-tag>
            </div>
        </div>
        <!-- 图片 -->
        <div class="code-cell-img" v-show="!(randerData.coverImg==null || randerData.coverImg=='')">
            <img :src="randerData.coverImg" style="width: 150px; height: 100px; display: block;border-radius:3px;">
        </div>
    </div>
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
            coverImg:'',
            title:'',
            tagList:[],
            marks:'',
            readNum:'0'
        };
      }
    },
    /** 点击卡片动作 */
    clickCell: {
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
    handleClickCell() {
      console.info("handleClickCard");
      const randerData = this.deepCopyJsonData(this.randerData, this.type);
      this.clickCell(randerData);
    },
    // 复制数据
    deepCopyJsonData(json) {
      return JSON.parse(JSON.stringify(json));
    }
  }
};
</script>

<style scoped>
.code-cell-container{
    display: flex;
    float: left;
    justify-content:space-between;
    align-items:center;
    border-bottom:1px solid #f0f0f0;
    cursor: pointer;
}
.code-cell-detail-container{
    display: flex;
    justify-content: left;
    flex-direction: column;
}
.code-cell-title{
    text-align: left;
    font-size: 18px;
    font-weight: bold;
    margin-top: 10px;
    font-weight: 700;
    line-height: 1.5;
}
.code-cell-remarks{
    text-align: left;
    font-size: 13px;
    line-height: 24px;
    margin-top: 10px;
    color: #999;
}
.code-cell-tags{
    display: flex;
    justify-content: left;
    flex-direction: row;
}
.code-cell-tag{
    margin-top: 10px;
    margin-right: 10px;
    margin-bottom: 15px;
    height: 30px;
}
.code-cell-img{
    padding-left: 5px;
    width: 150px;
    height: 100px;
}
</style>

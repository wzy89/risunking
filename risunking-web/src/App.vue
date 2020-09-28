<template>
    <div id="app">
    <!-- <img src="./assets/logo.png"> -->
      <el-tabs :tab-position="tabPosition" @tab-click='tabClick' v-model="currentSelectedIndex">
        <el-tab-pane :key="item.id" v-for="item in tabItems" :label="item.name" :name="item.path">
          <router-link  :to="item.path" ></router-link>
        </el-tab-pane>
      </el-tabs>
      <router-view/>
    </div>
</template>

<script>
// import canvas from "@/components/canvas/canvas.js";
export default {
  name: 'App',
  data() {
    return {
      tabPosition: 'top',
      currentSelectedIndex:this.$store.state.activeIndex,
      tabItems:[
        {id:1,path:'/',name:' 主页 '},
        {id:2,path:'/code',name:' 编码 '},
        {id:3,path:'/read',name:' 阅读 '},
        // {id:4,path:'/game',name:' 娱乐 '},
        {id:4,path:'/about',name:' 发个呆 '}
      ]
    };
  },
  methods:{
    tabClick(tab,event) {
      this.$router.push({path: this.currentSelectedIndex});
      this.$store.commit('setActiveIndex', this.currentSelectedIndex);
    }
  },
  
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 25px;
}
/** tab居中对齐 */
.el-tabs__nav-wrap
.el-tabs__nav-scroll
.el-tabs__nav {
    width: 100%;
    display: flex;
    justify-content: center;
}
/** tab隐藏滚动导航条 */
.el-tabs__active-bar{
  display: none;
}
/** tab隐藏滚动导航指示块 */
.el-tabs__nav-wrap::after{
  display: none;
}
/** tab设置标题字体大小 */
.el-tabs__item{
  font-size: 18px;
}

</style>

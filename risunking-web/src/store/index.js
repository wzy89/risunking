import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);

const state = {
    activeIndex: sessionStorage.getItem('activeIndex')||'/',
    baseUrl: "http://localhost:9999",//"http://www.risunking.com",
    downloadUrl: "/web/storage/downloadFile",
    uploadUrl:"/web/storage/uploadFile"
}
const actions = {}
const mutations = {
    setActiveIndex(state, value){
        state.activeIndex = value;
        /** 保存数据到session中 */
        sessionStorage.setItem('activeIndex',value);
    }
}
const store = new Vuex.Store({
    actions,
    state,
    mutations
})
export default store;
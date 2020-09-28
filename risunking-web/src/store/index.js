import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);
const state = {
    activeIndex: sessionStorage.getItem('activeIndex')||'/'
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
import Vue from 'vue'
import Router from 'vue-router'

const Err404 = resolve => require(['../views/error/404'], resolve)
const Home = resolve => require(['../views/home/home'], resolve)

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    { path: '/404', component: Err404, hidden: true },
    { path: '*', redirect: '/404', hidden: true }
  ]
})

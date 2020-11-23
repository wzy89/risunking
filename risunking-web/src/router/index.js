import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/views/home/Home'
import Code from '@/views/code/Code'
import Read from '@/views/read/Read'
import Game from '@/views/game/Game'
import About from '@/views/about/About'
import Edit from '@/views/edit/Edit'
import Detail from '@/views/detail/Detail'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/code',
      name: 'Code',
      component: Code
    },
    {
      path: '/read',
      name: 'Read',
      component: Read
    },
    {
      path: '/game',
      name: 'Game',
      component: Game
    },
    {
      path: '/about',
      name: 'About',
      component: About
    },
    {
      path: '/edit-wzy',
      name: 'Edit',
      component: Edit
    },
    {
      path: '/detail',
      name: 'Detail',
      component: Detail
    }
  ]
})

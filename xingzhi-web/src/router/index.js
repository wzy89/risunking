import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/home/home'
import QuillEditor from '@/views/edit/quill_editor'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/edit',
      name: 'quill_editor',
      component: QuillEditor
    }
  ]
})

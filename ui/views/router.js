import Vue from 'vue'
import Router from 'vue-router'


//const Skills = () => import('./components/Skills.vue')
//const About = () => import('./components/About.vue')

import Skills from './components/Skills.vue'
import About from './components/About.vue'

Vue.use(Router)

export function createRouter(){
  return new Router({
    mode: 'history',
    routes: [
      {
        path: '/',
        name: 'skills',
        component: Skills
      },
      {
        path: '/about',
        name: 'about',
        component: About
      }
    ]
  })  
}
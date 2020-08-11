import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import LoginView from '@/views/accounts/LoginView.vue'
import SignupView from '../views/accounts/SignupView.vue'
import Profile from '../views/accounts/Profile.vue'

import CreateView from '../views/articles/CreateView.vue'
import DetailView from '../views/articles/DetailView.vue'
import ListView from '../views/articles/ListView.vue'
import AlertView from '../views/alerts/AlertView.vue'

import SearchView from '../views/search/SearchView.vue'
import SearchDetailView from '../views/search/SearchDetail.vue'


Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/accounts/login',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/accounts/signup',
    name: 'Signup',
    component: SignupView,
  },
  {
    path: '/accounts/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/articles/create',
    name: 'Create',
    component: CreateView,
  },
  {
    path: '/articles/detail',
    name: 'Detail',
    component: DetailView,
  },
  {
    path: '/alerts/Alert',
    name: 'Alert',
    component: AlertView,
  },
  {
    path: '/articles',
    name: 'List',
    component: ListView,
  },
  {
    path: '/search/:selected/:checkin/:checkout/:people',
    name: 'Search',
    component: SearchView,
  },
  {
    path: '/search/detail',
    name: 'SearchDetail',
    component: SearchDetailView,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

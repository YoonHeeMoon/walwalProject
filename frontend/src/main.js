import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueCookies from 'vue-cookies'
import BootstrapVue from 'bootstrap-vue'
import Vuetify from 'vuetify'
import { library } from '@fortawesome/fontawesome-svg-core'

import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import 'vuetify/dist/vuetify.min.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// 구글로그인 등록
import GAuth from 'vue-google-oauth2'

Vue.use(GAuth, {clientId: '376396557114-psh4oj648fk3tnbbc1ejffu91choem6r.apps.googleusercontent.com',
scope: 'profile email https://www.googleapis.com/auth/plus.login'})

//카카오 로그인
window.Kakao.init("669fbe6453e6ef033819ded3c7e15941");
library.add(fas)
library.add(far)

Vue.component('font-awesome-icon', FontAwesomeIcon)


Vue.config.productionTip = false

Vue.use(Vuetify)
Vue.use(BootstrapVue)
Vue.use(VueCookies)


Vue.config.productionTip = false


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

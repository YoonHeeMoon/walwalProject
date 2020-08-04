<template>
  <div id="app">
    <div id="nav" class="navbar" :class="{ 'change-navbar': !changeNavbar }">
      <b-navbar toggleable="lg" type="dark" class="nav container" :class="{ 'change-navbar': !changeNavbar }">
        <div>
          <b-navbar-brand><b-link to="/"><img src="./assets/logo.png" alt="logo" class="logo"></b-link></b-navbar-brand>
          <b-navbar-brand><b-link to="/"><img src="./assets/walwal.png" alt="walwal" class="walwal"></b-link></b-navbar-brand>
        </div>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-nav-item class="mx-2" ><b-link to="/articles/Alert/">공지사항</b-link></b-nav-item>
            <b-nav-item class="mx-2" ><b-link to="/articles/">게시판</b-link></b-nav-item>
            <b-nav-item class="mx-2" v-if="!isLoggedIn">
              <b-link id="show-btn" @click="$bvModal.show('bv-modal-example')">로그인</b-link>
              <b-modal id="bv-modal-example" hide-footer class="login-modal">
                <template v-slot:modal-title>
                  Login
                </template>
                <div class="d-block text-center">
                  <LoginVue />
                </div>
              </b-modal>
            </b-nav-item>

            <b-nav-item-dropdown right v-if="isLoggedIn">
              <!-- Using 'button-content' slot -->
              <template v-slot:button-content>
                <em>User</em>
              </template>
              <b-dropdown-item href="#">Profile</b-dropdown-item>
              <b-dropdown-item href="#"><b-link v-if="isLoggedIn" to="/accounts/logout" @click.native="logout" >로그아웃</b-link></b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <router-view @submit-login-data="login" @submit-signup-data="signup"/>
  </div>
</template>

<script>
import axios from 'axios'

import LoginVue from './views/accounts/LoginView.vue'
const SERVER_URL = ''

export default {
  name: 'App',
  components: { LoginVue },
  data() {
    return {
      isLoggedIn: false,
      errorMessages: null,
      changeNavbar: true,
      lastScrollPosition: 0,
    }
  },

  mounted() {
    window.addEventListener('scroll', this.onScroll)
  },
  beforeDestroy () {
    window.removeEventListener('scroll', this.onScroll)
  },
  
  methods: {
    setCookie(token) {
      this.$cookies.set('auth-token', token)
      this.isLoggedIn = true
    },

    signup(signupData) {
       axios.post(SERVER_URL + '', signupData)
        .then(res => {
          this.setCookie(res.data.key)
          this.$router.push({ name: 'Home' })
        })
        .catch(err => this.errorMessages = err.response.data)
    },

    login(loginData) {
      axios.post(SERVER_URL + '', loginData)
        .then(res => {
          this.setCookie(res.data.key)
          this.$router.push({ name: 'Home' })
        })
        .catch(err => this.errorMessages = err.response.data)
    },

    logout() {
      const config = {
        headers: {
          'Authorization': `Token ${this.$cookies.get('auth-token')}`
        }
      }

      axios.post(SERVER_URL + '', null, config)
        .catch(err => console.log(err.response))
        .finally(() => {
          this.$cookies.remove('auth-token')
          this.isLoggedIn = false
          this.$router.push({ name: 'Home' })
        })
    },

    onScroll () {
      const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop
      if (currentScrollPosition < 0) {
      return
    }
    if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 0) {
      return
    }
      this.changeNavbar = currentScrollPosition < this.lastScrollPosition
      this.lastScrollPosition = currentScrollPosition
    }
  
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

#nav {
  padding: 0;
  width: 100%;
  position: sticky;
  top: 0;
  z-index :999;
}

.navbar {
  transition: background-color 0.5s ease;
  background-color: #F1C40F;
}

.navbar.change-navbar {
  background-color: white;
  box-shadow: 0px 1px #999;
}

.navbar a {
  font-weight: bold;
  color: white;
}

.navbar a:hover {
  color: white;
  text-decoration: none;
}

.navbar.change-navbar a {
  font-weight: bold;
  color: black;
}

.navbar.change-navbar a:hover {
  color: black;
  text-decoration: none;
}

.login-modal {
  z-index: 9999;
}

.logo {
  width: 70px;
}
.walwal {
  height: 30px;
}

#nav a.router-link-exact-active {
    color: white;
}

.modal-open {
  padding-right: 0 !important;
}
</style>

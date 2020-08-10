<template>
  <b-container>
      <b-row class="my-1">
        <b-col sm="3">
          <label for="name">이름 </label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="signupData.name" type="text"></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="3">
          <label for="nickname">닉네임</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="signupData.nickname" type="text"></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="3">
          <label for="pwd1">비밀번호</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="signupData.pwd1" v-validate="'required|min:6|max:35'" type="password"></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="3">
          <label for="pwd2">비밀번호 확인</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="signupData.pwd2" v-validate="'required|confirmed:password'" type="password"></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="3">
          <label for="email">이메일</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="signupData.email" type="email"></b-form-input>
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="3">
          <label for="birth">생년월일</label>
        </b-col>
        <b-col sm="9">
          <b-form-input v-model="signupData.birth" type="date"></b-form-input>
        </b-col>
      </b-row>
      <div>
          <b-button @click="signup" style="background-color: #F1C40F; border-color:#F1C40F;">회원가입</b-button>
      </div>
  </b-container>
</template>

<script>
export default {
    name: 'SignupView',
    data() {
        return {
            signupData: {
                name: null,
                nickname: null,
                password1: null,
                password2: null,
                email: null,
                birth: null,
            }
        }
    },
    methods: {
        signup() {
            // console.log('1', this.signupData)
            this.$emit('submit-signup-data', this.signupData)
        }
    }
}
</script>

<style>

</style>
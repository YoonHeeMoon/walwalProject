<template>
  <div class="container" style="width: 800px">
    <h1 class="pt-3">글 작성</h1>
    <div class="pt-5">
      <b-input-group>
      <template v-slot:prepend>
      <b-input-group-text class="pr-5" style="background-color:white;">제목</b-input-group-text>
    </template>
    <b-form-input placeholder="제목을 입력해주세요."></b-form-input>
    </b-input-group>
    </div>
    <div class="pt-3">
      <b-input-group>
      <template v-slot:prepend>
      <b-input-group-text class="pr-5" style="background-color:white;">내용</b-input-group-text>
    </template>
    
        <b-form-textarea
          v-model="articleData.content"
          id="content"
         rows="10"
         max-rows="18"
           placeholder="내용을 입력해주세요."
        ></b-form-textarea>
        </b-input-group>
    </div>
    <hr>
    <div>
      <b-button @click="createArticle">작성</b-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const SERVER_URL = ''

export default {
  name: "CreateView",
  data() {
    return {
      articleData: {
        title: null,
        content: null,
        created_at: new Date(),
      }
    };
  },
  methods: {
    createArticle() {
      const config = {
        headers: {
          Authorization: `Token ${this.$cookies.get('auth-token')}`
        }
      }
      axios.post(SERVER_URL + '', this.articleData, config)
        .then(res => { 
          console.log(res.data) 
          this.$router.push({ name: 'List' })
        })
        .catch(err => console.log(err.response.data))
    },
  },
};
</script>

<style>
</style>
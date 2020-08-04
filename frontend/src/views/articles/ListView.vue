<template>
  <div class="container">
    <h1 class="py-3">게시판</h1>
    <table class="table">
    <thead>
    <tr>
      <th scope="col">Num</th>
      <th scope="col">작성자</th>
      <th scope="col">제목</th>
      <th scope="col">조회수</th>
      <th scope="col">좋아요</th>
      <th scope="col">댓글수</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="article in articles" :key="`article_${article.id}`">
      <th scope="row">{{article.id}}</th>
      <td>{{article.user}}}</td>
      <td>{{article.title}}</td>
      <td>{{article.hit}}</td>
      <td>{{article.like}}</td>
      <td>{{article.comment}}</td>
    </tr>
  </tbody>
  </table>
  </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "";

export default {
  name: "ListView",
  data() {
    return {
      articles: []
    };
  },
  methods: {
    fetchArticles() {
      axios.get(SERVER_URL + "")
        .then(res => this.articles = res.data)
        .catch(err => console.error(err))
    },
  },
  created() {
    this.fetchArticles()
  }
};
</script>

<style>
</style>
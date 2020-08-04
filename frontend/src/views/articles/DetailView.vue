<template>
  <div>
    <b-card>
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">{{contentId}}</div>
          <div class="content-detail-content-info-left-subject">{{title}}</div>
        </div>
        <div class="content-detail-content-info-right">
          <div class="content-detail-content-info-right-user">글쓴이: {{user}}</div>
          <div class="content-detail-content-info-right-created">등록일: {{created}}</div>
        </div>
      </div>
      <div class="content-detail-content">{{context}}</div>
      <div class="content-detail-button">
        <b-button variant="primary" @click="updateData">수정</b-button>&nbsp;
        <b-button variant="success" @click="deleteData">삭제</b-button>
      </div>
      <div class="content-detail-comment">
        <CommentList :contentId="contentId"></CommentList>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "";

export default {
  name: "DetailView",
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
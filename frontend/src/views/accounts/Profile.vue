<template>
  <div class="container" style="padding-top: 100px">
    <header class="jumbotron">
      <h3>user name : {{content.username}}</h3>
      <h3>email : {{content.email}}</h3>
    </header>
  </div>
</template>

<script>
export default {
  name: 'profile',
  data() {
    return {
      content: ''
    }
  },
  mounted() {

  }
}
</script>
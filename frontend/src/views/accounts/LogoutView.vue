<template>
  <div></div>
</template>

<script>
export default {
    name: 'LogoutView',
    methods: {
        ...mapActions(['logout'])
    },
    created() {
        this.logout()
    }
}
</script>

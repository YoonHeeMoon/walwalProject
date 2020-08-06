<template>
  <div class ="wrap-search container ">
    <div style="text-align:left;">
    <h2>{{selected}}의 숙소</h2>
    <p class="m-1">체크인: {{checkin}}</p>
    <p class="m-1">체크아웃: {{checkout}}</p>
    </div>
    <b-card-group deck class="row">
      <div v-for="search in searches" :key="search" class="pt-5 px-0 col-lg-3 col-sm-6 col-md-4 content">
        <b-link id="show-btn" @click="$bvModal.show('bv-modal-example')">
          <b-card :title="search.s_name" :img-src="search.s_img" img-alt="Image" img-top >
            <b-card-text>
              &#8361; {{search.s_price}}
            </b-card-text>
          </b-card>
        </b-link>
        <b-modal id="bv-modal-example" size="lg" hide-footer class="Detail-modal">
          <template v-slot:modal-title>
            숙소 상세보기
          </template>
          <div class="d-block text-center">
            <SearchDetailVue/>
          </div>
        </b-modal>
      </div>
    </b-card-group>
  </div>

</template>

<style scoped>
</style>

<script>
import axios from "axios";
import SearchDetailVue from '@/views/search/SearchDetail.vue'


const SERVER_URL = "http://localhost:8080";

export default {
  name: "SearchView",
  components: { SearchDetailVue },
  computed:{
  },
  data() {
    return {
      selected: this.$route.params.selected,
      checkin: this.$route.params.checkin,
      checkout: this.$route.params.checkout,
      people: this.$route.params.people,
      searches: []
    };
  },
  methods: {
    fetchSearches() {
      axios.get(SERVER_URL + "/search", {
        params:{
          selected:this.selected,checkin:this.checkin,checkout:this.checkout,people:this.people
      }
      }
      
      )
        .then(res => this.searches = res.data)
        .catch(err => console.error(err))
    },
  },
  created() {
    this.fetchSearches()

  }
};
</script>
<style scoped>
  .card-img-top {
    border-radius: 10px 10px 0 0;
    background-size: cover;
    height: 150px;
  }

  .card {
    border: none;
    box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    border-radius: 10px;
  }

  .card:hover {
    opacity: 0.5;
  }

  a {
    color: black;
    }

  a:hover {
    color: black;
    text-decoration: none;
    }
  
  .card-title {
    font-size: 20px;
    font-weight: bold;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>
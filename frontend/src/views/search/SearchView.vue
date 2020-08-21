<template>
  <div class ="wrap-search container search" style="font-family: 'Nanum Gothic Coding', monospace;">
    <div style="text-align:left;">
    <h2><font-awesome-icon icon="paw"/>{{selected}}의 숙소<font-awesome-icon icon="paw"/></h2>
    <p class="m-1">체크인: {{checkin}}</p>
    <p class="m-1">체크아웃: {{checkout}}</p>
    </div>
    <hr>
    <div v-if="!searches.length">
      <img src=../../assets/runningCogi.gif>
    </div>
    <b-card-group deck class="row">
      <div v-for="search in searches" :key="search" class="pt-5 px-0 col-lg-4 col-sm-6 content">
        <b-link id="show-btn" v-b-modal.modal-scrollable @click="getModalDatas(search.s_link,search.s_type)"> 
          <b-card :title="search.s_name" :img-src="search.s_img" img-alt="Image" img-top >
            <b-card-text> 
              {{search.s_info}}
            </b-card-text>
            <b-card-text>
              &#8361; {{search.s_price}}
            </b-card-text>
          </b-card>
        </b-link>
      </div>
    </b-card-group>
        <b-modal id="modal-scrollable" size="lg" scrollable title="숙소 상세보기">
          <div class="d-block text-center">
            <div v-if="detailsearch ===''">
              <img src=../../assets/runningCogi.gif style="width:100%;">
            </div>
            <div v-else>
              <h2><font-awesome-icon icon="paw"/>PHOTO<font-awesome-icon icon="paw"/></h2>
              <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators>
                <b-carousel-slide class="Slide" :img-src="detailsearch.d_img1"></b-carousel-slide>
                <b-carousel-slide class="Slide" :img-src="detailsearch.d_img2"></b-carousel-slide>
                <b-carousel-slide class="Slide" :img-src="detailsearch.d_img3"></b-carousel-slide>
              </b-carousel>
              <hr>
              <h2><font-awesome-icon icon="paw"/>주변시설<font-awesome-icon icon="paw"/></h2>
              <br>
                <kakaoVue v-bind:val="detailsearch.d_dong">
                </kakaoVue>
            </div>
          </div>
          <template v-slot:modal-footer>
            <div class="w-100">
              <b-button
                variant="outline-warning"
                size="sm"
                class="float-right"
                @click="gotopage()"
                
              >
                숙소 이동하기
              </b-button>
            </div>
          </template>
        </b-modal>
  </div>

</template>

<style scoped>
</style>

<script>
import axios from "axios";
import kakaoVue from '@/views/map/KakaoMapView.vue'

const SERVER_URL = "http://localhost:8080";

export default {
  name: "SearchView",
  components: {
     kakaoVue
     },
  computed:{
    },
  data() {
    return {
      selected: this.$route.params.selected,
      checkin: this.$route.params.checkin,
      checkout: this.$route.params.checkout,
      people: this.$route.params.people,
      link: "",
      type:"",
      searches: [],
      detailsearch: ""
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
    getModalDatas(link,type){
        console.log(link);
        this.link = link;
        this.type = type;
        this.detailsearch="";
        axios.get(SERVER_URL + "/detailsearch", {
        params:{
          link:this.link,
          type:this.type
        }
      }
      )
        .then(res => this.detailsearch = res.data,
          console.log(this.detailsearch.d_dong),
          
        )
        .catch(err => console.error(err));

        this.$bvModal.show('bv-modal-search');
        console.log(this.detailsearch.d_dong);
    },
    gotopage(){
      window.open(this.link);
    },
    gotoimg(){
      window.open(this.link);
    },
  },
  created() {
    this.fetchSearches()

  }
};
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&display=swap');

.search {
  padding-top: 100px;
}

.card-img-top {
  border-radius: 10px 10px 0 0;
  background-size: cover;
  height: 250px;
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

.Slide {
  width:100%;
  max-height: 400px !important;
}

</style>
<template>
  <div class="bg-image">
    <div class="home container">
      <b-input-group class="d-flex flex-row justify-content-between pt-5" >
        <b-form-select v-model="selected" :options="options" ></b-form-select>
        <b-form-datepicker v-model= "checkin" id="datepicker-invalid-checkIn" :min="min" :max="max" locale="ko" class="" placeholder="체크인" ></b-form-datepicker>
        <b-form-datepicker v-model= "checkout"  id="datepicker-invalid-checkOut" :min="min" :max="max" locale="ko" class="" placeholder="체크아웃"></b-form-datepicker>
        <b-form-spinbutton id="demo-sb" v-model="people" min="1" max="100" placeholder="인원 수"></b-form-spinbutton>
        <b-input-group-append>
          <b-button v-on:click= "submit" size="sm" text="Button" style="color:gray; height:38px; background-color: white; border-color: lightgray;"><font-awesome-icon icon="search"/></b-button>
        </b-input-group-append>
      </b-input-group>
      <div class="d-flex">
        <div class="text-group">
          <h1 class="home-title">함께하는 여행 <br> 그 이상의 즐거움 </h1>
          <h5 class="home-subtitle">반려동물과 함께하는 여행! <br> WALWAL에서 두배로 즐기세요!</h5>
          <div class="d-flex justify-content-start pl-3">
            <b-button v-b-toggle.company>WALWAL이란?</b-button>
            <b-sidebar id="company" title="WALWAL" right shadow width="400px">
            <div class="px-3 py-2">
              <p>
                WALWAL 소개
              </p>
               <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators>
                <b-carousel-slide img-src="https://images.pexels.com/photos/1448055/pexels-photo-1448055.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=600&w=600"></b-carousel-slide>
                <b-carousel-slide img-src="https://images.pexels.com/photos/3671283/pexels-photo-3671283.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=600&w=600"></b-carousel-slide>
                <b-carousel-slide img-src="https://images.pexels.com/photos/2402799/pexels-photo-2402799.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=600&w=600"></b-carousel-slide>
                <b-carousel-slide img-src="https://images.pexels.com/photos/2739505/pexels-photo-2739505.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=600&w=600"></b-carousel-slide>
              </b-carousel>
            </div>
    </b-sidebar>
          </div>
        </div>
      </div>
      <div class="px-5">
        <h1>공지사항</h1>
            <b-link class="d-flex justify-content-end" to="/articles/Alert/">더보기</b-link>
            <b-table :items="items" :fields="fields">
            </b-table>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  },
  data() {
    const now = new Date()
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
    const minDate = new Date(today)
      minDate.setMonth(minDate.getMonth())
      minDate.setDate(now.getDate())
    const maxDate = new Date(today)
      maxDate.setMonth(maxDate.getMonth() + 2)
      maxDate.setDate(15)
    return {
      selected: null,
      items: [
          { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
          { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
          { age: 89, first_name: 'Geneva', last_name: 'Wilson' }
        ],
      options: [
        { value: null, text: '지역 선택' },
        { value: 'seoul', text: '서울' },
        { value: 'incheon', text: '인천' },
        { value: 'daejeon', text: '대전' },
        { value: 'daegu', text: '대구' },
        { value: 'Gwangju', text: '광주' },
        { value: 'Busan', text: '부산' },
        { value: 'Ulsan', text: '울산' },
        { value: 'Jeju', text: '제주' },
        { value: 'gyeong-gi', text: '경기' },
        { value: 'Gangwon-do', text: '강원' },
        { value: 'chungcheongnamdo', text: '충북' },
        { value: 'Chung-cheongbukdo', text: '충남' },
        { value: 'Jeollabukdo', text: '전북' },
        { value: 'Jeollanam-do', text: '전남' },
        { value: 'Gyeongsangbuk-do', text: '경북' },
        { value: 'Gyeongsangnam-do', text: '경남' },
      ],
      checkin: null,
      checkout: null,
      people: null,
      min: minDate,
      max: maxDate
    }
  },
  methods: {
        submit(){
        console.log(this.selected);
        console.log(this.checkin);
        console.log(this.checkout);
        console.log(this.people);
        location.href="/search/" + this.selected + "/" + this.checkin + "/" + this.checkout + "/" + this.people;
        
    },
  }
  }
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&display=swap');
.bg-image {
  background-image: url("../assets/main3.jpg");
  background-size: 100% 650px;
  display: flex;
  flex-wrap: wrap;
}

.home {
  padding-top: 100px;
}

.card {
  border: none;
  box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  border-radius: 10px;
}

.text-group{
  padding: 100px 0px 200px 0;
}

.home-title {
  color: white;
  padding-left: 10px;
  font-family: 'Nanum Gothic Coding', monospace;
  text-align: left;
}

.home-subtitle {
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 10px;
  color: lightgray;
  text-align: left;
  font-family: 'Nanum Gothic Coding', monospace;
}
</style>
<template>
  <div class="bg-image">
    <div class="home container">
      <b-input-group class="d-flex flex-row justify-content-between pt-5" >
        <b-form-select v-model="selected" :options="options"></b-form-select>
        <b-form-datepicker v-model= "checkin" id="datepicker-invalid-checkIn" :min="min" :max="max" locale="ko" class="mb-2" placeholder="체크인" ></b-form-datepicker>
        <b-form-datepicker v-model= "checkout"  id="datepicker-invalid-checkOut" :min="min" :max="max" locale="ko" class="mb-2" placeholder="체크아웃"></b-form-datepicker>
        <b-form-spinbutton id="demo-sb" v-model="people" min="1" max="100" placeholder="인원 수"></b-form-spinbutton>
        <b-input-group-append>
          <b-button v-on:click= "submit" size="sm" text="Button" style="color:gray; height:38px; background-color: white; border-color: lightgray;"><font-awesome-icon icon="search"/></b-button>
        </b-input-group-append>
      </b-input-group>
      <div class="d-flex justify-content-center">
        <div class="text-group">
          <h1 class="home-title">함께하는 여행.. 그 이상의 즐거움 </h1>
          <h5 class="home-subtitle">반려동물과 함께하는 여행! WALWAL에서 두배로 즐기세요</h5>
        </div>
      </div>
      <b-card-group deck class="p-5">
        <b-card>
          <img src="../assets/Savemoney.jpg" img-top style="width:100%; border-radius:20px;">
            <b-card-text>
              <h3 class="pt-3">최저가 검색</h3>
              <p class="m-0">저렴한 펜션이 최고! 최저가로 펜션을 즐기세요!</p>
            </b-card-text>
        </b-card>
        <b-card>
          <img src="../assets/travel.jpg" img-top style="width:100%; border-radius:20px;">
            <b-card-text>
              <h3 class="pt-3">BEST</h3>
              <p class="m-0">여행자들이 뽑은 BEST펜션! 함께 알아봐요!</p>
            </b-card-text>
        </b-card>
      </b-card-group>
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
    }
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
  padding: 150px 0px 200px 0;
}

.home-title {
  color: white;
  font-family: 'Nanum Gothic Coding', monospace;
  text-align: center;
}

.home-subtitle {
  padding-top: 30px;
  color: lightgray;
  text-align: center;
  font-family: 'Nanum Gothic Coding', monospace;
}
</style>
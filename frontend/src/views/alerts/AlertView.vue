<template>
  <div class="container" style="padding: 120px 100px 0px 100px">
    <b-table :items="items" :fields="fields" responsive="sm">
      <template v-slot:cell(Detail)="row">
        <b-button pill size="sm" variant="outline-warning" @click="row.toggleDetails" class="mr-2">
          {{ row.detailsShowing ? 'Hide' : 'Show'}} 
        </b-button>
      </template>

      <template v-slot:row-details="row">
        <b-card>

          <b-row class="mb-2">
            <b-col>{{ row.item.isActive }}</b-col>
          </b-row>

          <b-button pill size="sm" variant="outline-warning" @click="row.toggleDetails">Hide</b-button>
        </b-card>
      </template>
    </b-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        fields: ['No', '제목','Detail'],
        items: [
          { isActive: '안녕하세요 WalWal입니다. 최근 장마 발생때문에 많은 지역에서 피해가 속출하고 있습니다. 여행 시 이점 꼭 확인하시고 조심하시길 바랍니다. 감사합니다.', No:9, 제목: '홍수 관련 공지'},
          { isActive: '안녕하세요 WalWal입니다. 접속 오류 문제를 해결했습니다. 서비스 이용에 불편을 드려 죄송합니다. 앞으로 고객님의 편의를 위해 노력하는 WalWal이 되겠습니다. 감사합니다.', No:8, 제목: '오류 해결 공지'},
          { isActive: '안녕하세요 WalWal입니다. 지난 7일 저녁 9시부터 생긴 접속 오류 관련 공지올립니다. 서비스 이용에 불편을 드려 죄송합니다. 신속하게 조치하겠습니다.', No:7, 제목: '서비스 오류 안내'},
          { isActive: '안녕하세요 WalWal입니다. 무더위와 코로나 때문에 힘들시죠? 따라서 8월 이벤트 당첨자를 발표합니다. abc@naver.com(홍xx)', No:6, 제목: '8월 이벤트 당첨'},
          { isActive: '안녕하세요 WalWal입니다. 검색 후 에어비앤비의 숙소 정보를 확인하세요', No:5, 제목: 'WalWal은 에어비앤비의 정보를 제공합니다.'},
          { isActive: '안녕하세요 WalWal입니다. 검색 후 호텔스닷컴의 숙소 정보를 확인하세요', No:4, 제목: 'WalWal은 호텔스닷컴의 정보를 제공합니다.'},
          { isActive: '안녕하세요 WalWal입니다. 반려견 유기 및 학대 금지!', No:3, 제목: '반려견은 사랑입니다.'},
          { isActive: '안녕하세요 WalWal입니다. WalWal에서 사랑하는 반려견과 함께 여행을 즐겨요', No:2, 제목: '혼자있으면 외로워요.'},
          { isActive: `안녕하세요 WalWal입니다.
          코로나 때문에 모두들 힘든 시기입니다.
          여행에 있어 마스크는 필수!
           모두들 힘든시기 코로나 극복해 나갑시다.`, No:1, 제목: '코로나 관련 공지'},
        ]
      }
    }
  }
</script>
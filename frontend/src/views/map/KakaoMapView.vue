<template>
    <div>
        <div id="map"></div>
            <div v-if="this.loca.lng ==='default'">
            </div>
            <div v-else>
                {{findCenter(this.map)}}
                {{this.loca.lng="default"}}
            </div>
            
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        props:['val'],
        data() {
            return {
                loca:{
                    lng:"default"
                },
                map:null,
                centerL:null,


            }
        },
        mounted() {
            console.log(this.loca);
            axios.get('https://maps.googleapis.com/maps/api/geocode/json?address='+this.val+'&key=AIzaSyA0l538JpdPEwHpgl4PfROrV54pFK5IMlA')
            .then(result =>
            console.log(this.loca=result.data.results[0].geometry.location))
            .catch(err => console.error(err));
                
            window.kakao && window.kakao.maps
                 ? this.initMap()
                 : this.addScript();
        },
        methods: {
            initMap() {
                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(37.501275, 127.039613),
                    level: 5
                };
                var map = new kakao
                    .maps
                    .Map(container, options); 
                this.map=map;
            }, //
            findCenter(){
                var moveLatLon = new kakao.maps.LatLng(this.loca.lat, this.loca.lng);
                this.map.setCenter(moveLatLon);
                this.centerL=moveLatLon;
                var imageSrcCenter = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',// 센터 마커 이미지
                imageSize = new kakao.maps.Size(64, 68), // 마커이미지의 크기입니다
                imageOption = {offset: new kakao.maps.Point(32, 68)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                var centerMarkerImg = new kakao.maps.MarkerImage(imageSrcCenter, imageSize, imageOption);

                var centerMarker = new kakao.maps.Marker({
                    map: this.map,
                    position: moveLatLon,
                    image: centerMarkerImg // 마커이미지 설정
                });
                centerMarker.setMap(this.map);
                this.findPark(this.map);
            },
            findPark(map){
            var ps = new kakao.maps.services.Places(); 
                var imageSrc1 = "/parkMarker.png",// 마커이미지의 주소입니다
                imageSrc2 = "/hospitalMarker.png",// 마커이미지의 주소입니다
                imageSize = new kakao.maps.Size(32, 34), // 마커이미지의 크기입니다
                imageOption = {offset: new kakao.maps.Point(16, 34)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.            
                var markerImage1 = new kakao.maps.MarkerImage(imageSrc1, imageSize, imageOption);  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다.
                var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize, imageOption);  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다.

                // var imageSrc2 = "/hospitalMarker.png";
                // var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize, imageOption);

                ps.keywordSearch('공원',placesSearchPark,{
                    location:this.centerL,
                    radius:5000
                    });
                ps.keywordSearch('병원',placesSearchHos,{
                    location:this.centerL,
                    radius:5000
                    });

                   function placesSearchPark(data, status,pagination) {
                       console.log(pagination);
                    if (status === kakao.maps.services.Status.OK) {
                    
                        for (var i = 0; i < data.length; i++) {
                            data[i].icontype = "1";
                            displayMarker(data[i]);
                        }
                    }
                }
                function placesSearchHos(data, status,pagination) {
                       console.log(pagination);
                    if (status === kakao.maps.services.Status.OK) {
                        
                        for (var i = 0; i < data.length; i++) {
                            data[i].icontype = "2";
                            displayMarker(data[i]);
                        }
                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                    }
                }
                function displayMarker(place) {
                    if(place.icontype=="1"){

                    var marker1 = new kakao.maps.Marker({
                        map: map,
                        position: new kakao.maps.LatLng(place.y, place.x),
                        image: markerImage1 // 마커이미지 설정 
                    });
                    marker1.setMap(map);
                    }
                    else if(place.icontype=="2"){
                    var marker2 = new kakao.maps.Marker({
                        map: map,
                        position: new kakao.maps.LatLng(place.y, place.x),
                        image: markerImage2 // 마커이미지 설정 
                    });
                    marker2.setMap(map);
                    }
                }
            },
            addScript() {
                const script = document.createElement('script');/* global kakao */
                script.onload = () => kakao.maps.load(this.initMap);
                script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=cc8b9917b26332077dadcd3de8d506b6&libraries=services';
                document.head.appendChild(script);
            }
        }
    }
</script>
<style scoped="scoped">
    #map {
        width: 100%;
        height: 600px;
        background-color : red;
    }
</style>
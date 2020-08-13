<template>
    <div>
        <div id="map"></div>
    </div>
</template>

<script>
    export default {
        mounted() {
            window.kakao && window.kakao.maps
                ? this.initMap()
                : this.addScript();
        },
        methods: {
            initMap() {
                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(33.450701, 126.570667),
                    level: 3
                };
                var map = new kakao
                    .maps
                    .Map(container, options); 
                var ps = new kakao.maps.services.Places(); 

                var String ="송파구"

                var imageSrc1 = "/parkMarker.png",// 마커이미지의 주소입니다
                imageSrc2 = "/hospitalMarker.png",// 마커이미지의 주소입니다
                imageSize = new kakao.maps.Size(32, 34), // 마커이미지의 크기입니다
                imageOption = {offset: new kakao.maps.Point(16, 34)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.            
                var markerImage1 = new kakao.maps.MarkerImage(imageSrc1, imageSize, imageOption);  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다.
                var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize, imageOption);  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다.

                // var imageSrc2 = "/hospitalMarker.png";
                // var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize, imageOption);

                ps.keywordSearch(String + '공원',placesSearchPark);

                ps.keywordSearch(String + '병원',placesSearchHos);

                   function placesSearchPark(data, status,pagination) {
                       console.log(pagination);
                    if (status === kakao.maps.services.Status.OK) {
                        
                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
                        var bounds = new kakao
                            .maps
                            .LatLngBounds();
                        for (var i = 0; i < data.length; i++) {
                            data[i].icontype = "1";
                            displayMarker(data[i]);
                            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                        }
                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                        map.setBounds(bounds);
                    }
                }
                function placesSearchHos(data, status,pagination) {
                       console.log(pagination);
                    if (status === kakao.maps.services.Status.OK) {
                        
                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
                        var bounds = new kakao
                            .maps
                            .LatLngBounds();
                        for (var i = 0; i < data.length; i++) {
                            data[i].icontype = "2";
                            displayMarker(data[i]);
                            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                        }
                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                        map.setBounds(bounds);
                    }
                }
                function displayMarker(place) {
                    console.log(place);
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
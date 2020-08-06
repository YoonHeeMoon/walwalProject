<template>
    <div id="map"></div>
</template>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cc8b9917b26332077da&libraries=services"></script>

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
                ps.keywordSearch(String + '공원',placesSearchCB);
            
                var markerImage = new daum.maps.MarkerImage(
                'https://t1.daumcdn.net/cfile/tistory/9924734F5AE03D2729?original', new daum.maps.Size(30, 40));
                

                   function placesSearchCB(data, status,pagination) {
                       console.log(pagination);
                    if (status === kakao.maps.services.Status.OK) {

                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
                        var bounds = new kakao
                            .maps
                            .LatLngBounds();

                        for (var i = 0; i < data.length; i++) {
                            displayMarker(data[i]);
                            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                        }

                        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                        map.setBounds(bounds);
                    }
                }
                function displayMarker(place) {
    
                    // 마커를 생성하고 지도에 표시합니다
                    var marker = new kakao.maps.Marker({
                        map: map,
                        image: markerImage,
                        position: new kakao.maps.LatLng(place.y, place.x)
                    });
                    marker.setImage(markerImage);
                    marker.setMap(map);
                }
                 
                
            },
            addScript() {
                const script = document.createElement('script');/* global kakao */
                script.onload = () => kakao
                    .maps
                    .load(this.initMap);
                script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=cc8b9917b26332077dadcd3de8d506b6&libraries=services';
                        
                document.head.appendChild(script);
            }
        }
    }
</script>
<style scoped="scoped">
    #map {
        width: 1500px;
        height: 1500px;
    }
</style>
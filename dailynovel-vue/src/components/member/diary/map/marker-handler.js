


class Markerhandler {
    constructor(vueKakaoMap, place, options) {
        this.vueMap = vueKakaoMap;
        this.map = place;
        this.options = options

        
        
    }
    add(place) {
        const marker = (place) => { // marker 함수를 추가로 정의
          const option = place
          const markerInstance = new window.kakao.maps.Marker({
            map: this.vueMap.mapInstance,
            position: new window.kakao.maps.LatLng(option.y, option.x),
          });
      
          markerInstance.$$ = {
            place,
          };
          if(this.options.markerClicked){
            window.kakao.maps.event.addListener(markerInstance, 'click', () =>{
              this.options.markerClicked(markerInstance.$$.place);
            });
          }
        }
      
        marker(place); // marker 함수 호출
      }
}

export default Markerhandler;
class KakaoOverlay {
    constructor(vueKakaoMap, content) {
        this.vuemap = vueKakaoMap;
        this.content = content;

        

        //init instance
        this.instance = new window.kakao.maps.CustomOverlay({
            map: this.vuemap.mapInstance, //아직안올림
            clickable: false,
            content: content,


            position: null,



            xAnchor: 0.5,
            yAnchor: 1,
            zIndex: 3
        })
    }
    showAt(lat, lng) {
        console.log('overlay show!!', lat, lng);
        const pos = new window.kakao.maps.LatLng(lat, lng);

        this.instance.setMap(this.vuemap.mapInstance);
        this.instance.setPosition(pos);
    }

    hide(){
        this.instance.setMap(null);
    }
}

export default KakaoOverlay
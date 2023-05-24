<template>
    <div>

        <div class="controll" >
            <div class="addr-box">
                <button v-show="selectedAddr">장소 : <input type="text" id="myPlace" @click="showMap()"></button>
            </div>
            <button @click="zoom(-1)" v-show="ZOOM">
                <span class="material-symbols-outlined">
                    zoom_in
                </span>
            </button>
            <button @click="zoom(1)" v-show="ZOOM"><span class="material-symbols-outlined">
                    zoom_out
                </span>
            </button>
        </div>

        <div class="map-area" v-show="MAP">

            <div class="searchbox" v-show="isSearchBoxHidden">
                <input type="text" placeholder="장소를 입력해주세요" @keyup.enter="searchPlace" />

                <div class="results">
                    <div class="place-result" v-for="rs in search.results" :key="rs.id" @click="showPlace(rs)"
                        :class="{ active: rs === activemyplace }">
                        <h4 class="place-name">{{ rs.place_name }}</h4>
                        <div class="place-addr">{{ rs.address_name }}</div>
                    </div>
                </div>
            </div>



            <KakaoMap ref="kmap" class="kmap" :options="mapOption">
                <div class="overlay-popup" ref="myplaceOverlay" slot="overlay">
                    <div class="overlay-box" v-if="overlayMyplace">
                        <h3 class="overlay-head">{{ overlayMyplace.place_name }}</h3>
                        <div class="addr">{{ overlayMyplace.address_name }}
                            <a class="register" href="#" @click.prevent="register()" >
                                <span class="material-symbols-outlined">
                                    check_circle
                                </span>
                            </a>
                        </div>
                        <a class="close" href="#" @click.prevent="closeOverlay()">
                            <span class="material-symbols-outlined">
                                close
                            </span>
                        </a>

                    </div>
                </div>
            </KakaoMap>
        </div>
    </div>
</template>



<script>
import KakaoMap from './map/KakaoMap.vue';
import MarkerHandler from '../Diary/map/marker-handler.js';
import KakaoOverlay from '../Diary/map/overlay';
export default {
    components: {
        KakaoMap,
    },
    props:['myLocate'],
    // setDiaryLocate
    data() {
        return {
            mapOption: {
                center: {
                    lat: this.myLocate.lat,
                    lng: this.myLocate.lng,
                },
                level: 3,
            },
            myplaces: [],
            markers: null,
            activemyplace: null, //selected
            setmyplace: null,
            overlay: null,// overlay 인스턴스
            overlayMyplace: null, //오버레이에 붙여줄 장소
            search: {
                keyword: null,
                pgn: null,
                results: [],
            },

            draggable: true,
            isSearchBoxHidden: true,
            selectedAddr: false,
            ZOOM:true,
            MAP: true,

        };
    },
    mounted() {
        const vueKakaoMap = this.$refs.kmap;
        this.overlay = new KakaoOverlay(vueKakaoMap, this.$refs.myplaceOverlay);
        // api.myplace.all(res => {
        //     console.log("[내 장소]", res.myplaces)
        //     this.myplaces = res.myplaces
        //     //create markers
        //     this.markers.add(this.myplaces, (myplace) => {
        //         return { lat: myplace.lat, lng: myplace.lng };
        //     })
        // })
    },
    methods: {
        zoom(delta) {
            //delta 1 or -1
            console.log("[delta]", delta)
            const level = Math.min(8, Math.max(1, this.mapOption.level + delta))
            this.mapOption.level = level;
            console.log(this.mapOption.level)

        },
        closeOverlay() {
            const vueKakaoMap = this.$refs.kmap;
            console.log('[overlay]', 'close')
            this.isSearchBoxHidden = true;
            this.draggable = true;
            this.overlay.hide();
            vueKakaoMap.mapInstance.setDraggable(this.draggable);
            

        },
        searchPlace(e) {
            const keyword = e.target.value.trim();
            if (keyword.length === 0) {
                return;
            }
            console.log(keyword)
            const ps = new kakao.maps.services.Places();
            ps.keywordSearch(keyword, (data, status, pgn) => {
                this.search.keyword = keyword;
                this.search.pgn = pgn;
                this.search.results = data;
            });
        },

        // setDMap(setDiaryLocate){
        //     this.mapOption.center = {
        //         lat: setDiaryLocate.lat,
        //         lng: setDiaryLocate.lng
        //     }
        //     console.log("테스트세트텟트ㅔ스테슽",this.mapOption.center.lat)
        // },

        showonMap(place) {
            console.log("[center]", place)
            this.activemyplace = place;
            this.mapOption.center = {
                lat: place.y,
                lng: place.x
            }
        },
        showPlace(place) {
            const vueKakaoMap = this.$refs.kmap;
            this.markers = new MarkerHandler(vueKakaoMap, place, {
                markerClicked: (place) => {
                    this.showonMap(place)
                    //마커 클릭시
                    this.overlayMyplace = place;
                    const input = document.getElementById("myPlace");
                    input.value = this.overlayMyplace.place_name;
                    console.log(this.overlayMyplace, "오버레이")
                    this.overlay.showAt(place.y, place.x);
                },
            });

            this.activemyplace = place;
            this.mapOption.center = {
                lat: place.y,
                lng: place.x
            },

                this.markers.add(place, (place) => {
                    return { lat: place.lat, lng: place.lng };
                })
        },


        register() {
            const vueKakaoMap = this.$refs.kmap;
            const lat = vueKakaoMap.options.center.lat;
            const lng = vueKakaoMap.options.center.lng;
            const coor = {lat, lng};
            this.$emit('coorGood', coor)
            // emit한것은 파라미터로 들어간다. 직접전인값이아님.

            this.draggable = false;
            // this.isSearchBoxHidden = true;
            this.isSearchBoxHidden = false;
            console.log(this.overlayMyplace,"이거야이거!")
            vueKakaoMap.mapInstance.setDraggable(this.draggable);
            this.selectedAddr = true;
            this.ZOOM = false;
        },

        showMap(){
            this.MAP = true;
            this.selectedAddr = false;
            this.ZOOM = true;
        },

        




    }
};
</script>


<style >
.controll {
    position: relative;
}

#myPlace {
    border: none;
    text-align: center;
    
}

button {
    border: 1px solid transparent;
    padding: 6px;
    background-color: #efefefdd;
    border-radius: 6px;
}

button:hover {
    background-color: #ddd;
    border-radius: #ddd;
}

.searchbox {
    position: absolute;
    top: 0;
    left: 0;
    height: 285px;
    z-index: 10000;
    background-color: #ffffffaa;
    width: 150px;
    overflow-x: auto;
    overflow-y: auto;

    display: flex;
    flex-direction: column;
}

.result {
    position: relative;
    flex: 1 1 auto;
    overflow-y: auto;
}


.place-result {
    padding: 8px;
}

.place-result:hover {
    background-color: antiquewhite;
    color: #124981;
    cursor: pointer;


}


.place-name {
    margin: 0;
}

.map-area {
    display: flex;
    position: relative;
}



.kmap {
    position: relative;
    flex: 1 1 auto;
    width: 100%;
    height: 285px;
}

.overlay-popup {

    background-color: white;
    box-shadow: 0 0 8px #0000004d, 0 0 1px 2px #00000022;
    max-width: 300px;
    min-width: 160px;
    position: absolute;
    bottom: 44px;
    left: 50%;
    transform: translateX(-50%);
}

.overlay-head {
    margin: 0;
    padding: 8px;
    background-color: rgba(0, 7, 49, 1);
    columns: white;
    color: white;
    font-weight: 400;
    font-size: 16px;

}

.overlay-box {
    position: relative;
}

.addr {
    padding: 8px;
    background-color: white;
    white-space: break-spaces;
}

.close {
    color: black;
    position: absolute;
    top: 0;
    left: 100%;
    transform: translate(-50%, -50%);
    background-color: white;
    border-radius: 100%;
    line-height: 0;
    padding: 6px;
    box-shadow: 0 0 6px #0000004d;
}

.register {
    color: green;
    bottom: 0;
    right: 0;
    transform: translate(20%, 10%);
    position: absolute;
    padding: 6px;
    text-decoration: none;
}
</style>
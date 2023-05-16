<template>


  <div  ref="map">
    <slot name:overlay></slot>
  </div>
</template>


<script>
export default {
  props: ['options'],
  data() {
    return {
      mapInstance: null,

    }
  },
  mounted() {
    let kakao = window.kakao;
    const container = this.$refs.map;
    // const options = {
    //   //지도를 생성할 때 필요한 기본 옵션
    //   center: new window.kakao.maps.LatLng(33.450901, 126.570667),
    //   level: 3,
    // };
    const { center, level } = this.options
    this.mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(center.lat, center.lng),
      level,
    });
    // this.mapInstance에 카카오 맵이 들어감.
  },
  methods: {
    
  },
  watch: {
    'options.level'(cur, prev) {
      console.log(`[LEVEL CHANGED] ${prev} => ${cur}`)
      this.mapInstance.setLevel(cur);
    },
    "options.center"(cur) {
      console.log("[NEW CENTER]", cur.lat, cur.lng)
      this.mapInstance.setCenter(new kakao.maps.LatLng(cur.lat, cur.lng));
    }
  }
}
</script>


<style>

</style>
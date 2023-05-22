<template>
  <div class="scroll">
    <div class="result" v-for="comment in comments" :key="comment.id">
      <div>{{ comment.id }}</div>
    </div>

    <InfiniteLoading @infinite="load" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import InfiniteLoading from "v3-infinite-loading";
import "v3-infinite-loading/lib/style.css";

let comments = ref([]);
let page = 0;
const load = async $state => {
  console.log("loading...");

  try {
    const response = await fetch(
      "http://localhost:8080/display/listall1?_limit=4&_page=" + page
      // "https://jsonplaceholder.typicode.com/comments?_limit=10&_page=" + page
      
    );
    const json = await response.json();
    console.log(json);
    if (json.length < 4) $state.complete();
    else {
      comments.value.push(...json);
      $state.loaded();
    }
    page++;
  } catch (error) {
    $state.error();
  }
};
</script>

<style scoped>
.scroll {
    overflow: auto;
    height: 648px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.result {
  display: flex;
  flex-direction: column;
  gap: 5px;
  font-weight: 300;
  width: 400px;
  padding: 10px;
  text-align: center;
  margin: 0 auto 10px auto;
  background: #eceef0;
  border-radius: 10px;
}
</style>
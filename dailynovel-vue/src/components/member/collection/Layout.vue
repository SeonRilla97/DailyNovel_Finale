<template>

<section class="collection-container">
    <header>
        <div class="pdl-5 h2 font-bold"><router-link class="" to="/member/room/collection/main">컬렉션</router-link></div>
    </header>
    <transition name="fade">
        <router-view></router-view>
    </transition>
</section>
</template>

<style scoped>
.collection-container{
    display: grid; 
    grid-template-rows: 72px auto;
    grid-template-areas: 1fr;
    /* overflow:hidden; */
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
    max-width: 1280px;
}

.collection-container > header {
    display:flex;
    align-items: center;
}


.fade-enter-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from
 {
    opacity: 0;
}
</style>
<script setup>
import { onMounted,ref} from 'vue';
const props = defineProps({
    filter: {
        type: Object,
        required:true
    }
})
let emit = defineEmits('filterClickedHandler')
const selectedMenu=  {
 menuname:null,
 menuIndex:null
}
function optionClickHandler(e){
    console.log(e);
//    console.log(e.target.innerText);  //value값 뽑기
//    console.log(e.target.dataset.fidx);  // dataset 뽑기
//    console.log(e.target.parentNode.dataset.menuname); //부모의 dataset 뽑기
    selectedMenu.menuname = e.target.parentNode.dataset.menuname;
    selectedMenu.menuIndex = e.target.dataset.idx;
    console.log(selectedMenu);  
    emit('filterClickedHandler',selectedMenu);
    console.log(e.target.parentNode.style);
}

let menuOpen = ref(null);
function menuOpenHandler(clickedMenu){
        menuOpen.value = clickedMenu
}
</script>


<template>
    <header class="diary-header">
        
        <h1></h1>
        <h1>나의일기</h1>
        <!-- 드롭다운 메뉴 -->
        
                <div class="dropdown">
                    <div class="btn" @click="menuOpenHandler(1)"><span>{{ filter.diary.feeling.menu[filter.diary.feeling.idx] }}</span><span class="icon-clamp"></span></div>
                    <transition name="bounce">
                        <div class="content" @click="optionClickHandler" data-menuname = "feeling" v-show="menuOpen==1">
                            <a href="#" v-for="(f,idx) in filter.diary.feeling.menu" :data-idx=idx @click="menuOpenHandler">{{ f }}</a>
                        </div>
                    </transition>
                </div>

                <div class="dropdown">
                    <div class="btn" @click="menuOpenHandler(2)"><span>{{ filter.diary.weather.menu[filter.diary.weather.idx] }}</span><span class="icon-clamp"></span></div>
                    <transition name="bounce">
                        <div class="content" @click="optionClickHandler" data-menuname = "weather" v-show="menuOpen==2">
                            <a href="#" v-for="(f,idx) in filter.diary.weather.menu" :data-idx=idx @click="menuOpenHandler">{{ f }}</a>
                        </div>
                    </transition>
                </div>

                <div class="dropdown">
                    <div class="btn" @click="menuOpenHandler(3)"><span>솔직함</span><span class="icon-clamp"></span></div>
                    <transition name="bounce">
                        <div class="content"  v-show="menuOpen==3">
                            <a href="#" v-for="w in filter.diary.weather.menu">{{ w }}</a>
                        </div>
                    </transition>
                </div>

                <div class="dropdown">
                    <div class="btn" @click="menuOpenHandler(4)"><span>날짜</span><span class="icon-clamp"></span></div>
                    <transition name="bounce">
                        <div class="content"  v-show="menuOpen==4">
                            <a href="#" v-for="w in filter.diary.weather.menu">{{ w }}</a>
                        </div>
                    </transition> 
                </div>

                <div class="dropdown">
                    <div class="btn" @click="menuOpenHandler(5)"><span>{{ filter.diary.sort.menu[filter.diary.sort.idx] }}</span><span class="icon-clamp"></span></div>
                    <transition name="bounce">
                        <div class="content"  v-show="menuOpen==5">
                            <a href="#" v-for="w in filter.diary.sort.menu">{{ w }}</a>
                        </div>
                    </transition>
                </div>

                <div class="dropdown">
                    <div class="btn" @click="menuOpenHandler(6)"><span>{{ filter.diary.collection.menu[filter.diary.collection.idx] }}</span><span class="icon-clamp"></span></div>
                    <transition name="bounce">
                        <div class="content"  v-show="menuOpen==6">
                            <a href="#" v-for="w in filter.diary.collection.menu">{{ w }}</a>
                        </div>
                    </transition>
                </div>

                <div class="search-container">
                    <form action="#">
                    <input type="text" placeholder="Search.." name="search">
                    <button>검색</button>
                    </form>
                </div>
            </header>
        
</template>

<style scoped>
/* 다이어리 페이지 헤더 */
.diary-header {
    display:flex;
    align-items: center;
    height:42px;
    position:relative;
}
    .diary-header > h1{
        margin-left:15px;
        font-weight:normal;
        font-size: 20px;
    }
    .diary-header .search-container{
        position:absolute;
        right: 0;
        margin: 2%;
    }


/* dropDown(필터 메뉴) */
.diary-header .dropdown .btn {

font-size: 16px;
border: none;
cursor: pointer;
box-sizing: border-box;
}

.diary-header .dropdown {
position: relative;
display: inline-block;
margin-left: 40px;
}

.diary-header .dropdown .content {
/* display: none; */
position: absolute;
background-color: #f9f9f9;
min-width: 160px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
text-align: center;
}

.diary-header .dropdown .content a {
color: black;
padding: 12px 16px;
text-decoration: none;
display: block;
}

.diary-header .dropdown .content a:hover {background-color: #f1f1f1}

.diary-header .dropdown.active .content{
display: block;
}
.diary-header .dropdown:hover .dropbtn {
background-color: #3e8e41;
}

.bounce-enter-active {
  animation: bounce-in 0.2s;
}
.bounce-leave-active {
  animation: bounce-in 0.4s reverse;
}
@keyframes bounce-in {
    0% {
            opacity: 0;
            transform: translate3d(0, 20%, 0);
        }
        to {
            opacity: 1;
            transform: translate(0);
        }
}
</style>
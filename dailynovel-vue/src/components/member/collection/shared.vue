<script setup>
import {ref} from 'vue'


let menuOpen = ref(true);
// 컬렉션 리스트 오픈 핸들러
function mOpenHandler(){
    menuOpen.value = !menuOpen.value;
}
</script>
<template>
    <div class="shared-container">
        <header class="header">
            <div class="pdl-5 h2 font-bold">
                <div class="ib active">인기</div>
                <div class="ib mgl-2">최신</div>
            </div>
            <div class="icon-back"></div>
        </header>
        <section class="main">
            <div class="slide-container" >


                    <section class="box" >
                            <div class="title">날씨가 유난히도 맑은 날</div>
                            <div class="main">
                                <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                            </div>
                            <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
                            <!-- <div class="reg-btn">추가</div>
                            <ul class=".collection-list dropdown">
                                <li class="item">가장 행복했던 순간들</li>
                                <li class="item">나의 영화일지</li>
                                <li class="item">여행 가볼까요</li>
                            </ul> -->
                            <div class="dropdown collection-list">
                                <div class="btn" @click="mOpenHandler"><span>추가</span><span class="icon-clamp"></span></div>
                                <transition name="bounce">
                                    <div class="content" @click="optionClickHandler" v-show="menuOpen">
                                        <a href="#"  class="item shared">가장 행복했던 순간들</a>
                                        <a href="#"  class="item">나의 영화일지</a>
                                        <a href="#"  class="item">여행 가볼까요</a>
                                        <a href="#"  class="item">여행 가볼까요</a>
                                        <a href="#"  class="item">여행 가볼까요</a>
                                    </div>
                                </transition>
                            </div>
                    </section>


                <section class="box" >
                        <div class="title">날씨가 유난히도 맑은 날</div>
                        <div class="main">
                            <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                        </div>
                        <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
                </section>
                <section class="box" >
                        <div class="title">날씨가 유난히도 맑은 날</div>
                        <div class="main">
                            <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                        </div>
                        <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
                </section>
                <section class="box" >
                        <div class="title">날씨가 유난히도 맑은 날</div>
                        <div class="main">
                            <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                        </div>
                        <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
                </section>

                <section class="box" >
                        <div class="title">날씨가 유난히도 맑은 날</div>
                        <div class="main">
                            <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                        </div>
                        <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
                </section>
                <section class="box" >
                        <div class="title">날씨가 유난히도 맑은 날</div>
                        <div class="main">
                            <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                        </div>
                        <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
                </section>

                


            </div>

        </section>
    </div>
</template>
<style scoped>

.shared-container{
    display:grid;
    grid-template-rows: 72px auto;
}

.shared-container{
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

.shared-container > header {
    display:flex;
    align-items: center;
    position:relative;
}
.shared-container > .main{
    display:flex;
    align-items: center;
    overflow-x:scroll;
    width:100%;
}
.shared-container .main .slide-container{
    display:flex;
    align-items: center;
    transform: translateY(-10%);
    padding:0 58px ;
    /* box-sizing: border-box; */
} 

.shared-container .main .box{
    width:240px;
    height: 360px;
    margin-left: 36px;
    border: 1px solid #00000020;
    border-radius: 12px;
    display:grid;
    grid-template-rows: 1fr 5fr 0.5fr;
    text-align: center;
    position:relative;
}
/* .shared-container .main .box:nth-child(1){
    margin-left:0
} */



.shared-container .main .box .title{
    font-size: 20px;
    font-weight: bold;
    border-bottom: 1px solid #00000020;
    /* justify-self:center; */

}
.shared-container .main .box{
    
}
.shared-container .main .box >.title{
    display:flex;
    justify-content: center;
    align-items: center;
}

.icon-like{
    background-image: url("../../../assets/img/like.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height: 16px;
}
.icon-back{
    background-image: url("../../../assets/img/backArrow.svg");
    background-repeat: no-repeat;
    background-size: 24px 24px;
    background-position: center center;
    width:24px;
    height: 24px;
    position:absolute;
    right:10%;
}


.shared-container .header .active{
    text-decoration: underline;
    color: #FCD602;
    text-underline-position : under;
}


/* DropDown */

/* dropDown(필터 메뉴) */
.collection-list.dropdown .btn {
    
    bottom:0;
    align-self:center;
    position:absolute;
    bottom: -40px;
    left: 50%;
    transform: translate(-50%);

    width:100px;
    height: 30px;
    background-color: #7594e960;
    color: #fcfcfc;
    display:flex;
    justify-content: center;
    align-items: center;
    border-radius: 20px;
    transition: all 0.2s linear;
}
.collection-list.dropdown .btn:hover{
    background-color: #7594e9;
    cursor: pointer;
}


.collection-list.dropdown {
position: relative;
display: inline-block;
/* margin-left: 40px; */
}

.collection-list.dropdown .content {
/* display: none;  */
position: absolute;
background-color: #fcfcfc;
min-width: 220px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
text-align: center;
left:4%;
/* transform:translate(-50% ,-100%) */
border-radius: 12px;
bottom:100%;
}


.collection-list.dropdown .content .item {
color: black;
padding: 12px 16px;
text-decoration: none;
display: block;
}

.collection-list.dropdown .content .item.shared {
color: #00000040;
}

.collection-list.dropdown .content a:hover {
    background-color: #f1f1f1;
    
    
}



/* vue Transition */
.bounce-enter-active {
  animation: bounce-in 0.5s;
}
.bounce-leave-active {
  animation: bounce-in 0.5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: translateY(+40%);
    opacity: 0;
  }
  50% {
    transform: translateY(-10%);
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}

</style>
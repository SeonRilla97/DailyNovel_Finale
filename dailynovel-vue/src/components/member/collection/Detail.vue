
<template>
    <div class="shared-container">
        <header class="header">
            <div class="pdl-5 h2 font-bold">
                <div class="ib active">일기</div>
                <div class="ib mgl-2">댓글</div>
            </div>
            <div class="icon-back"></div>
        </header>
        
        <router-view></router-view>
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

/* vue Transition */


.fade-enter-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from
 {
    opacity: 0;
}


</style>
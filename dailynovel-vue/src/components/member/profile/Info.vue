<template>
    
    <section class="m-pf-modify-container">
        <div class="m-pf-modify-image">
            <div class="m-pf-modify-image-box"></div>
            <section class="m-pf-modify-btns">
                <div class="m-pf-modify-selectBtn">선택</div>
                <div class="m-pf-modify-selectBtn">수정</div>
            </section>

        </div>
        <div class="m-pf-modify-message">sfdsf</div>        
        <section class="m-pf-load" v-show="false">
            
        </section>
    </section>
</template>

<style scoped>
.m-pf-load{
    background-color: rgba(0,0,0,0.3);
    width:100%;
    height:100%;
    z-index: 1;
    position: fixed;
    left:0;
    top:0;
}
.m-pf-modify-image-box{
    width:60%;
    height:60%;
    background-color: aqua;
    border-radius: 50%;
}
.m-pf-modify-image{
    width:100%;
    height:100%;
    background-color: green;
}

.m-pf-modify-message{
    width:100%;
    height:100%;
    background-color: brown;
}
.m-pf-modify-container{
    width:100%;
    height:100%;

    display:grid;
    grid-template-columns: repeat(2,1fr);
}
</style>
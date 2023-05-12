<template>
    <div class="container">
        <div class="diary-list" >

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
            <section class="box" >
                    <div class="title">날씨가 유난히도 맑은 날</div>
                    <div class="main">
                        <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                    </div>
                    <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">12</span></div>   
            </section>
        </div>
    </div>
</template>

<style scoped>
.container{

    display:flex;
    align-items: center;
    overflow-x:scroll;
    width:100%;
}
.container .diary-list{

    display:flex;
    align-items: center;
    transform: translateY(-10%);
    padding:0 58px ;
    /* box-sizing: border-box; */
} 

.container .box{

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

.container .box .title{

    font-size: 20px;
    font-weight: bold;
    border-bottom: 1px solid #00000020;
    /* justify-self:center; */

}
.container .box >.title{

    display:flex;
    justify-content: center;
    align-items: center;
}

.icon-like{
    background-image: url("../../../../assets/img/like.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height: 16px;
}

</style>
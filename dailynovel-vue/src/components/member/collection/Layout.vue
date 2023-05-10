<template>

<section class="collection-container">
    <header>
        <div class="pdl-5 h2 font-bold">컬렉션</div>
    </header>

    <section >
        <div class="box">
            <div class="question">컬렉션의 제목은<br/>무엇인가요?</div>
            <input class="input" type="text">
            <div class="title-err">컬렉션 이름은 중복할 수 없어요</div>
            <div class="btn-text register">생성</div>
            <div class="btn-text cancel">취소</div>
        </div>
        
        
        <div class="box">
            <div class="icon-plus"></div>
            <div class="question">나만의 컬렉션을<br/>추가해보세요</div>
        </div>

        <div class="box">
            
            <h1 class="question">공유되었던 일기들</h1>
        </div>

        <div class="box">
            <div class="icon-tack">핀셋</div>
            <h1 class="question">가장 행복했던 순간들</h1>
            <div><span class="icon-chat">아이콘</span><span>12</span></div>
        </div>

        <div class="box">
            <div class="icon-tack">핀셋</div>
            <h1 class="question">나의 영화 일지</h1>
            <div><span class="icon-chat">아이콘</span><span>12</span></div>
        </div>
    </section>
</section>
</template>

<style scoped>
.collection-container{
    display: grid; 
    grid-template-rows: 72px auto;
}

.collection-container header {
    display:flex;
    align-items: center;
}

.icon-plus{
    background-image: url("../../../assets/img/plus.svg");
    background-repeat: no-repeat;
    background-size: 32px 32px;
    background-position: center center;
    width:32px;
    height: 32px;
}
.icon-tack{
    background-image: url("../../../assets/img/pin.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;

}
</style>
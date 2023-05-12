<template>
    <section class="list">
        <!-- list의 Header(menu box) -->
        <header class="header" style="position:relative">
            <div class="editor-menu" style="font-size:24px; font-weight: bold; position:absolute; right: 3%;">+</div>
        </header>
        
        <!-- 달별 일기 -->
        <div class="content">
            
            
            <div class="date-list">
                <!-- 년 월 타이틀-->
                <div class="monthly-header">2023년 7월</div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div> 
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share"><span>공유중</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share active"><span>공유 완료</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    
                </div>
            </div>

            <div class="date-list">
                <!-- 년 월 타이틀-->
                <div class="monthly-header">2023년 7월</div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div> 
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share"><span>공유중</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share active"><span>공유 완료</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    
                </div>
            </div>

            <div class="date-list">
                <!-- 년 월 타이틀-->
                <div class="monthly-header">2023년 7월</div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div> 
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share"><span>공유중</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share active"><span>공유 완료</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    
                </div>
            </div>

            <div class="date-list">
                <!-- 년 월 타이틀-->
                <div class="monthly-header">2023년 7월</div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div> 
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share"><span>공유중</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    <div class="share active"><span>공유 완료</span></div>
                </div>
                <!-- 일 박스 -->
                <div class="date-container">
                    <div class="date"><span>화</span><span>10</span></div>
                    <div class="title"><span>자연에서 즐기는 커피</span><span>오후 02:01</span></div>
                    
                </div>
            </div>

            


      
        </div>

    </section>
</template>


<style scoped>


.diary-main .list{
    width:30%;
    /* background-color: #3e8e41; */
    height: 100%;
    display:flex;
    flex-direction: column;
    border-right: solid 1px #c4c4c4;
    /* display:grid; 
    grid-template-rows:2% 98%; */
    /* overflow:scroll; */
    
}
    
.diary-main .list .header{
    width:100%;
    height:32px;
    background-color: #F9F4F4;
}
.diary-main .list .content{
    width:100%;
    height: 100px;
    flex-grow:1;
    overflow-y: scroll;

    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
}

.diary-main .list .content .monthly-header{
    height:40px;
    font-size:22px;
    font-weight: bold;
    background-color: #F9F4F4;
    display:flex;
    align-items: center;
    text-indent: 10px;
}
.diary-main .list .content .date-container{
    height: 84px;
    display:grid;
    grid-template-columns : 1fr 3fr 1fr;
    border-bottom:1px solid #c4c4c4
}
.diary-main .list .content .date-list :last-child{
    border-bottom: 0px;
}
    .diary-main .list .content .date-container .date{
        width:56px;
        height: 66px;
        text-align: center;
        display:flex;
        flex-direction: column;
        justify-content: space-around;
        background-color: #656363;
        border-radius: 5px;;
        justify-self:center;
        align-self: center;
    }
        .diary-main .list .content .date-container .date span:nth-child(1){
            font-size:12px;
            color:#fcfcfc;
        }

        
        .diary-main .list .content .date-container .date span:nth-child(2){
            font-size:36px;
            color:#fcfcfc;
            font-weight: bolder;
        }
    .diary-main .list .content .date-container .title{
        display:flex;
        flex-direction: column;
        justify-content: space-around;
        box-sizing: border-box;
        padding:10px 20px;
    }
    
        .diary-main .list .content .date-container .title span:nth-child(1){
            font-weight: bold;
        }
        .diary-main .list .content .date-container .title span:nth-child(2){
            font-size:12px;
        }
    .diary-main .list .content .date-container .share{
        justify-self: center;
        align-self: center;
        width:60px;
        height:24px;

        display:flex;
        justify-content: center;
        align-items: center;
        color:#fcfcfc;
        font-size:12px;
        border-radius: 5px;
        background-color: #191F78;
    }
    .diary-main .list .content .date-container .share.active{
        background-color: transparent;
        border: 1px solid #8391FC;
        color: #8391FC;
    }
    .diary-main .list .content .date-container span{
        display:block;
    }


    
/* 스크롤바 커스터마이징 */
.diary-main .list .content::-webkit-scrollbar {
width: 10px;  /* 스크롤바의 너비 */
}
.diary-main .list .content::-webkit-scrollbar-thumb {
height: 30%; /* 스크롤바의 길이 */
background: #848484; /* 스크롤바의 색상 */
border-radius: 10px;
}

.diary-main .list .content::-webkit-scrollbar-track {
/* background: #84848447;  스크롤바 뒷 배경 색상 */
background-color: transparent;
}
</style>
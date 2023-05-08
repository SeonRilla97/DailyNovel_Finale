<template>
    <div class="diary-container">
        <header class="diary-header">
            <h1>나의일기</h1>
                <!-- 드롭다운 메뉴 -->
                <div class="dropdown">
                    <div class="btn"><span>기분</span><span class="icon-clamp"></span></div>
                    <div class="content">
                        <a href="#">좋음</a>
                        <a href="#">기쁨</a>
                        <a href="#">나쁨</a>
                    </div>
                </div>

                <div class="dropdown">
                    <div class="btn"><span>날씨</span><span class="icon-clamp"></span></div>
                    <div class="content">
                        <a href="#">좋음</a>
                        <a href="#">기쁨</a>
                        <a href="#">나쁨</a>
                    </div>
                </div>

                <div class="dropdown">
                    <div class="btn"><span>기분</span><span class="icon-clamp"></span></div>
                    <div class="content">
                        <a href="#">좋음</a>
                        <a href="#">기쁨</a>
                        <a href="#">나쁨</a>
                    </div>
                </div>

                <div class="dropdown">
                    <div class="btn"><span>솔직함</span><span class="icon-clamp"></span></div>
                    <div class="content">
                        <a href="#">좋음</a>
                        <a href="#">기쁨</a>
                        <a href="#">나쁨</a>
                    </div>
                </div>

                <div class="dropdown">
                    <div class="btn"><span>날짜</span><span class="icon-clamp"></span></div>
                    <div class="content">
                        <a href="#">좋음</a>
                        <a href="#">기쁨</a>
                        <a href="#">나쁨</a>
                    </div>
                </div>

                <div class="dropdown">
                    <div class="btn"><span>날짜</span><span class="icon-clamp"></span></div>
                    <div class="content">
                        <a href="#">좋음</a>
                        <a href="#">기쁨</a>
                        <a href="#">나쁨</a>
                    </div>
                </div>

                <div class="search-container">
                    <form action="#">
                    <input type="text" placeholder="Search.." name="search">
                    <button>검색</button>
                    </form>
                </div>
        </header>


        <section class="diary-main">
            <section class="list">
                <!-- list의 Header(menu box) -->
                <header class="header" style="position:relative">
                    <div class="editor-menu" style="font-size:24px; font-weight: bold; position:absolute; right: 3%;">+</div>
                </header>
                
                <!-- 달별 일기 -->
                <div class="content">
                    <!-- 년 월 타이틀-->
                    <div class="monthly-header">2023년 7월</div>
                    <div class="date-list">
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


                    <div class="monthly-header">2023년 8월</div>
                    <div class="date-list">
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


                    <div class="monthly-header">2023년 9월</div>
                    <div class="date-list">
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


                    <div class="monthly-header">2023년 10월</div>
                    <div class="date-list">
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

            <article class="content">
                s
            </article>
        </section>
    </div>
</template>


<style scpoed>
/* 페이지 컨테이너 */
.diary-container{
    display:flex;
    flex-direction: column;
}
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


/* 다이어리 컨텐츠 부분 레이아웃 잡기*/
.diary-main{
display:flex;
flex-grow: 1;
height: var(100%-42px);
}


.diary-main .list{
    width:30%;
    /* background-color: #3e8e41; */
    height: 100%;
    display:flex;
    flex-direction: column;
    border-right: solid 1px #c4c4c4;
    /* display:grid; 
    grid-template-rows:2% 98%; */
    
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
    user-select:none
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
    background: #84848447;  /*스크롤바 뒷 배경 색상*/
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



    .icon-clamp{
        background-image: url("/workspace/DailyNovel_Finale/dailynovel-vue/src/assets/img/clamp.svg");
        width:20px;
        height:20px;
        background-repeat: no-repeat;
        background-position: center center;
    }



/* dropDown(필터 메뉴) */
.dropdown .btn {

font-size: 16px;
border: none;
cursor: pointer;
box-sizing: border-box;
}

.dropdown {
position: relative;
display: inline-block;
margin-left: 40px;
}

.dropdown .content {
display: none;
position: absolute;
background-color: #f9f9f9;
min-width: 160px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
}

.dropdown .content a {
color: black;
padding: 12px 16px;
text-decoration: none;
display: block;
}

.dropdown .content a:hover {background-color: #f1f1f1}

.dropdown:hover .content{
display: block;
}
.dropdown:hover .dropbtn {
background-color: #3e8e41;
}
</style>
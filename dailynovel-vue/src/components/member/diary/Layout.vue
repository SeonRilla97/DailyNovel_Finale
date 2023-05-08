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

                <div>
                    <input type="text">
                </div>
        </header>


        <section class="diary-main">
            <nav class="list">
                s
            </nav>

            <article class="content">
                s
            </article>
        </section>
    </div>
</template>


<style scpoed>
.diary-container{
    display:flex;
    flex-direction: column;
    width:100%;
    height:100%
}

.diary-header {
    display:flex;
    align-items: center;
    height:42px;
}

    
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

.diary-main{
display:flex;
flex-grow: 1;
}
    .diary-main .list{
        width:30%;
        /* background-color: #3e8e41; */
        height: 100%;
    }
    .diary-main .content{
        width:70%;
        height:100%;
        background-color:aqua ;
    }




    .icon-clamp{
        background-image: url("/workspace/DailyNovel_Finale/dailynovel-vue/src/assets/img/clamp.svg");
        width:20px;
        height:20px;
        background-repeat: no-repeat;
        background-position: center center;
    }
</style>
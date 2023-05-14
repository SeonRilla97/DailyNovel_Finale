<template>
    <!-- 페이지 레이아웃 -->
    <div class="comment-layout">
        <!-- 컨테이너 레이아웃 -->
        <div class="main">
            <!-- 댓글 컨테이너 -->
            <div class="content">
                <!-- 댓글 -->
                <div class="comment">sd</div>
                <!-- 대댓글 -->
                <div class="depth-two">
                    <div class="comment">asd</div>
                </div>
            </div>
        </div>
    </div>

</template>

<style scoped>
.comment-layout{
    display:grid;
    grid-template-columns: 2fr 8fr 2fr;
}
    .main {
        background-color: aqua;
        grid-column-start: 2;
        grid-column-end: 3;
    }

    .content .comment {
        background-color: blueviolet;
        width:100%;
        height:fit-content;
    }
</style>
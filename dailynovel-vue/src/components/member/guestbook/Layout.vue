<template>
  <section class="m-guestbook-container">

    <span>방명록</span>
    <ul>
      <li>
        <div class="m-guestbook-content-section">
          <div class="m-guestbook-content-title"><span>방명록 제목</span></div>
          <div class="m-guestbook-content-text"><span>방명록 내용</span></div>
          <div class="m-guestbook-content-comment"><span>방명록 답글</span></div>
        </div>
      </li>
    </ul>

  </section>
</template>
<style scoped>
.m-guestbook-container {}

.m-guestbook-content-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  width: 300px;
  height: 290px;
  background-color: green;
}

.m-guestbook-content-title {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 20%;
}

.m-guestbook-content-text {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 70%;
}

.m-guestbook-content-comment {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 10%;
}
</style>
<template>
  <img src="../../assets/img/background.png" id="background" style="display: none" />
  <img src="../../assets/img/slime.png" id="slime" style="display: none" />
  <canvas
    id="canvas"
    class="canvas"
    width="1280"
    height="720"
    ref="canvas"
    @click="canvasClickHandler"
    @keydown="keyDownHandler"
    @keyup="keyUpHandler"
    :tabindex="0"
  ></canvas>
</template>

<script>
import Slime from "./Canvas/item/slime.js";
import Background from "./Canvas/item/background.js";
import Obstacle from "./Canvas/item/obstacle .js";
export default {
  data() {
    return {
      canvas: null,
      ctx: null,
      slime: null,
      background: null,
      obstacles: [],
      canvasOffsetX: 0,
      canvasOffsetY: 0,
    };
  },

  mounted() {
    this.canvas = this.$refs.canvas;
    this.ctx = this.canvas.getContext("2d");

    this.slime = new Slime(700, 600);
    this.background = new Background();
    this.createObstacles();
    this.run();

    // 캔버스에 포커스를 설정
    this.canvas.setAttribute("tabindex", "0");
    this.canvas.focus();

    const canvasElement = document.getElementById("canvas");
    if (canvasElement) {
      const canvasRect = canvasElement.getBoundingClientRect();
      this.canvasOffsetX = canvasRect.left;
      this.canvasOffsetY = canvasRect.top;
    }
  },

  beforeUnmount() {
    // 캔버스의 키보드 이벤트 리스너 제거
    this.canvas.removeEventListener("keydown", this.keyDownHandler);
  },

  methods: {
    createObstacles() {
      // 여러 개의 장애물 생성
      //책상
      this.obstacles.push(new Obstacle(70, 260, 130, 150, "green"));
      //책장
      this.obstacles.push(new Obstacle(435, 205, 250, 50, "red"));
      //컴퓨터
      this.obstacles.push(new Obstacle(958, 203, 300, 60, "blue"));
      //상단벽
      this.obstacles.push(new Obstacle(0, 0, 1280, 180, "blue"));

      this.obstacles.push(new Obstacle(0, 0, 5, 720, "blue"));
      this.obstacles.push(new Obstacle(0, 720, 1280, 5, "blue"));
      this.obstacles.push(new Obstacle(1275, 0, 5, 720, "blue"));
      //업적
      this.obstacles.push(new Obstacle(1100, 350, 200, 150, "red"));
      this.obstacles.push(new Obstacle(67.25, 508, 60, 130, "blue"));
      this.obstacles.push(new Obstacle(362.25, 516, 120.25, 110, "blue"));
      this.obstacles.push(new Obstacle( 1080.25, 604, 120.25, 70, "blue"));
  
      // 추가적인 장애물들 생성 가능
    },
    run() {
      this.update();
      this.draw();
      window.requestAnimationFrame(this.run);
    },

    update() {
      const prevX = this.slime.x;
      const prevY = this.slime.y;
      this.slime.update();
// 충돌 감지 로직
let collisionDetected = false;
  
  for (const obstacle of this.obstacles) {
    if (this.isCollision(this.slime, obstacle)) {
      // 충돌이 발생하면 슬라임의 위치를 이전 위치로 복원
      this.slime.x = prevX;
      this.slime.y = prevY;
      console.log("충돌했다!" +this.slime.sh+this.slime.y);
      collisionDetected = true;
    }
  }
  
  if (!collisionDetected) {
  }
    },

    isCollision(object1, object2) {
  const rect1 = {
    x: object1.x - object1.sw / 2,
    y: object1.y - object1.sh/2,
    width: object1.sw,
    height: object1.sh,
  };
  const rect2 = {
    x: object2.x,
    y: object2.y,
    width: object2.width,
    height: object2.height,
  };

  return (
    rect1.x < rect2.x + rect2.width &&
    rect1.x + rect1.width > rect2.x &&
    rect1.y < rect2.y + rect2.height &&
    rect1.y + rect1.height > rect2.y
  );
},
    draw() {
      // draw logic
      this.background.draw(this.ctx);
      for (const obstacle of this.obstacles) {
        obstacle.draw(this.ctx);
      }
      this.slime.draw(this.ctx);
    },

    canvasClickHandler(e) {
      let canvasX = e.x - this.canvasOffsetX; // 실제 좌표를 캔버스 상대 좌표로 변환
      let canvasY = e.y - this.canvasOffsetY;
      console.log(canvasX, canvasY);
      // this.slime.moveTo(canvasX, canvasY);
      //책상->일기작성
      if((70<=canvasX&260<=canvasY)&(canvasX<=200&canvasY<410) ){
        this.$router.push('/member/room/diary');
        this.$emit('modalOpenHandler');
      }
      //책장->컬렉션
      else if((435<=canvasX&205<=canvasY)&(canvasX<=680&canvasY<=255)){
        this.$router.push('/member/room/follow');
        this.$emit('modalOpenHandler');
      }
      //컴퓨터->커뮤니티
      else if((958<=canvasX&50<=canvasY)&(canvasX<=1258&canvasY<=260)){
        this.$router.push('/member/room/community');
        this.$emit('modalOpenHandler');
      }
      //업적->트로피
      else if((1100 <=canvasX&350<=canvasY)&(canvasX<=1300&canvasY<=500)){
        this.$router.push('/member/room/achievement');
        this.$emit('modalOpenHandler');
      }
      //상단벽->구독페이지
      else if((0<=canvasX&0<=canvasY)&(canvasX<=300&canvasY<=180)){
        this.$router.push('/member/room/guestbook/list');
        this.$emit('modalOpenHandler');
      }

      //침대-> 로그아웃
      else if((435<=canvasX&205<=canvasY)&(canvasX<=680&canvasY<255)){
        this.$router.push('/member/room/collection/main');
        this.$emit('modalOpenHandler');
      }

    },

    keyDownHandler(e) {
      if (e.key === "x") {
        if (
          this.slime.x >= 100 &&
          this.slime.x <= 300 &&
          this.slime.y >= 100 &&
          this.slime.y <= 300
        ) {
          console.log("상단 좌측 구간에서 x 키를 눌렀습니다.");
        }
      }
      this.slime.move(e.key);
      console.log(e.key);
    },
    keyUpHandler(e) {
      // this.boy.kreset();
      this.slime.stop(e.key);
    },
  },
};
</script>

<style scoped>
.canvas {
  border: 1px solid #5a4d4d;
}
</style>
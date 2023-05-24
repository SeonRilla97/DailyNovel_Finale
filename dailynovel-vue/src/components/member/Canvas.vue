<template>
  <div class="canvas-container">
    <img src="../../assets/img/background.png" id="background" style="display: none" />
    <img src="../../assets/img/slime.png" id="slime" style="display: none" />
    <img src="../../assets/img/dialog.png" id="dialog" style="display: none" />
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
  </div>
</template>

<script>
import { useUserDetailsStore } from "../store/useUserDetailsStore";
import Slime from "./Canvas/item/slime.js";
import Background from "./Canvas/item/background.js";
import Obstacle from "./Canvas/item/obstacle.js";
import Dialog from "./Canvas/item/dialog.js";
import BackgroundSound from "../../assets/sound/background.mp3";
import SlimeSound from "../../assets/sound/slime.mp3";
import { useRouter } from "vue-router";

export default {
  data() {
    return {
      canvas: null,
      ctx: null,
      slime: null,
      background: null,
      dialog: null,
      obstacles: [],
      canvasOffsetX: 0,
      canvasOffsetY: 0,
      showDialogBox: false,
      dialogText: "",
    };
  },

  mounted() {
    this.canvas = this.$refs.canvas;
    this.ctx = this.canvas.getContext("2d");
    this.slime = new Slime(700, 600);
    this.background = new Background();
    this.dialog = new Dialog();
    this.createObstacles();
    this.run();
    this.backgroundBgm = new Audio(BackgroundSound);
    // this.backgroundBgm.loop = true; // 무한 루프 설정
    // this.backgroundBgm.play(); // 음악 재생
    this.slimeSound = new Audio(SlimeSound);
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
      // 책상
      this.obstacles.push(new Obstacle(70, 260, 130, 150, "table"));
      // 책장
      this.obstacles.push(new Obstacle(435, 205, 250, 50, "shelf"));
      // 컴퓨터
      this.obstacles.push(new Obstacle(958, 203, 300, 60, "computer"));
      // 상단벽
      this.obstacles.push(new Obstacle(0, 0, 300, 180, "wall"));
      this.obstacles.push(new Obstacle(600, 0, 600, 180, "door"));
      this.obstacles.push(new Obstacle(0, 0, 5, 720));
      this.obstacles.push(new Obstacle(0, 720, 1280, 5));
      this.obstacles.push(new Obstacle(1275, 0, 5, 720));
      // 업적
      this.obstacles.push(new Obstacle(1100, 350, 200, 150, "trophy"));
      this.obstacles.push(new Obstacle(67.25, 508, 60, 130, "bed"));
      this.obstacles.push(new Obstacle(362.25, 516, 120.25, 110));
      this.obstacles.push(new Obstacle(1080.25, 604, 120.25, 70));

      // 추가적인 장애물들 생성 가능
    },
    run() {
      this.update();
      this.draw();
      window.requestAnimationFrame(this.run);
    },
    draw() {
      // 그리기 로직
      this.background.draw(this.ctx);
      for (const obstacle of this.obstacles) {
        obstacle.draw(this.ctx);
      }
      this.slime.draw(this.ctx);

      // 대화 상자 그리기
      if (this.showDialogBox) {
        this.dialog.draw(this.ctx);
      }
    },

    update() {
      const prevX = this.slime.x;
      const prevY = this.slime.y;
      this.slime.update();

      let collisionDetected = false;

      for (const obstacle of this.obstacles) {
        if (this.isWithinDistance(this.slime, obstacle)) {
          this.showDialogBox = true;
          collisionDetected = true;
          // 대화 상자를 표시할 좌표 계산
          const dialogX = this.slime.x;
          const dialogY = this.slime.y - this.slime.sh / 2 - 100;
          // 충돌하는 객체에 따라 대화 상자 표시
          if (obstacle.type === "table") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("일기를 쓰러\n가볼까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "shelf") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("일기장을 보러\n가볼까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "computer") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("다른 사람\n일기를 보러\n가볼까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "wall") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("구독 페이지를\n 보러 가볼까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "trophy") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("내 업적을 확인하러\n가볼까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "bed") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("로그 아웃 할까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "door") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("문으로 나가볼까요?(y)");
            this.dialog.show();
          } else {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText(`충돌했습니다.`);
            this.dialog.show();
          }
        }

        if (!collisionDetected) {
          // 충돌이 없을 때의 대화 상자 숨기기
          this.dialog.hide();
        }

        if (this.isCollision(this.slime, obstacle)) {
          this.showDialogBox = true;
          this.slime.x = prevX;
          this.slime.y = prevY;
          collisionDetected = true;

          // 대화 상자를 표시할 좌표 계산
          const dialogX = this.slime.x;
          const dialogY = this.slime.y - this.slime.sh / 2 - 100;

          // 충돌하는 객체에 따라 대화 상자 표시

          if (obstacle.type === "table") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("책상과 \n충돌했습니다.");
            this.dialog.show();
          } else if (obstacle.type === "shelf") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("책장과\n 충돌했습니다.");
            this.dialog.show();
          } else if (obstacle.type === "computer") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("컴퓨터 책상과\n 충돌했습니다.");
            this.dialog.show();
          } else if (obstacle.type === "wall") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("구독 페이지를\n 보러 가볼까요?(y)");
            this.dialog.show();
          } else if (obstacle.type === "trophy") {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText("트로피와 \n부딪혔습니다.");
            this.dialog.show();
          } else {
            this.dialog.setPosition(dialogX, dialogY);
            this.dialog.setText(` 충돌했습니다.`);
            this.dialog.show();
          }
        }

        if (!collisionDetected) {
          // 충돌이 없을 때의 대화 상자 숨기기
          this.dialog.hide();
        }
      }
    },

    isWithinDistance(object1, object2) {
      let rect1 = {
        x: object1.x - object1.sw / 2,
        y: object1.y - object1.sh / 2,
        width: object1.sw,
        height: object1.sh,
      };
      let rect2 = {
        x: object2.x,
        y: object2.y,
        width: object2.width,
        height: object2.height,
      };

      return (
        rect1.x < rect2.x + rect2.width + 20 &&
        rect1.x + rect1.width > rect2.x - 20 &&
        rect1.y < rect2.y + rect2.height + 20 &&
        rect1.y + rect1.height > rect2.y
      );
    },
    isCollision(object1, object2) {
      let rect1 = {
        x: object1.x - object1.sw / 2,
        y: object1.y - object1.sh / 2,
        width: object1.sw,
        height: object1.sh,
      };
      let rect2 = {
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

    drawDialogBox() {
      this.dialog.setPosition(this.slime.x - 200, this.slime.y + 100);
      this.dialog.setText(this.dialogText);
      this.dialog.show();
    },

    canvasClickHandler(e) {
      let canvasX = e.x - this.canvasOffsetX; // 실제 좌표를 캔버스 상대 좌표로 변환
      let canvasY = e.y - this.canvasOffsetY;
      console.log(canvasX, canvasY);
      // this.slime.moveTo(canvasX, canvasY);
      // 책상 -> 일기 작성
      if (70 <= canvasX && 260 <= canvasY && canvasX <= 200 && canvasY < 410) {
        this.$router.push("/member/room/diary");
        this.$emit("modalOpenHandler");
      }
      // 책장 -> 컬렉션
      else if (435 <= canvasX && 0 <= canvasY && canvasX <= 680 && canvasY <= 255) {
        this.$router.push("/member/room/collection/main");
        this.$emit("modalOpenHandler");
      }
      // 컴퓨터 -> 전시회
      else if (958 <= canvasX && 50 <= canvasY && canvasX <= 1258 && canvasY <= 260) {
        this.$router.push("/member/room/community");
        this.$emit("modalOpenHandler");
      }
      // 업적 -> 트로피
      else if (1100 <= canvasX && 350 <= canvasY && canvasX <= 1300 && canvasY <= 500) {
        this.$router.push("/member/room/achievement");
        this.$emit("modalOpenHandler");
      }
      // 상단벽 -> 방명록
      else if (0 <= canvasX && 0 <= canvasY && canvasX <= 300 && canvasY <= 180) {
        this.$router.push("/member/room/profile/guestbook");
        this.$emit("modalOpenHandler");
      }

      // 침대 -> 마이페이지
      else if (435 <= canvasX && 205 <= canvasY && canvasX <= 680 && canvasY < 255) {
        this.$router.push("/member/room/profile");
        this.$emit("modalOpenHandler");
      }
      // 추가적인 클릭 이벤트 처리 가능

      // 대화창 클릭 이벤트 처리
      if (this.showDialogBox) {
        if (
          this.slime.x - 100 <= canvasX &&
          this.slime.x + 100 >= canvasX &&
          this.slime.y - 70 <= canvasY &&
          this.slime.y - 20 >= canvasY
        ) {
          // 대화창 닫기
          this.showDialogBox = false;
        }
      }
    },

    //슬라임 소리
    SlimePlaySound(volume) {
      this.slimeSound.volume = volume; // 소리 크기 조절
      this.slimeSound.play(); // 음악 재생
    },

    keyDownHandler(e) {
      if (e.key === "y") {
        //침대
        if (
          152 <= this.slime.x &&
          522 <= this.slime.y &&
          this.slime.x <= 337 &&
          this.slime.y <= 627
        ) {
          // let userDetail = useUserDetailsStore();
          // userDetail.logout();
          // this.$router.push("/login");
          this.$router.push("/member/room/profile");
          this.$emit("modalOpenHandler");
        }
        const obstacle = this.obstacles.find((obstacle) =>
          this.isWithinDistance(this.slime, obstacle)
        );
        if (obstacle) {
          if (obstacle.type === "table") {
            this.$router.push("/member/room/diary");
            this.$emit("modalOpenHandler");
          } else if (obstacle.type === "shelf") {
            console.log("책장과 충돌했습니다.");
            this.$router.push("/member/room/collection");
            this.$emit("modalOpenHandler");
          } else if (obstacle.type === "computer") {
            console.log("컴퓨터와 충돌했습니다.");
            this.$router.push("/member/room/community");
            this.$emit("modalOpenHandler");
          } else if (obstacle.type === "trophy") {
            console.log("트로피와 충돌했습니다.");
            this.$router.push("/member/room/achievement");
            this.$emit("modalOpenHandler");
          } else if (obstacle.type === "wall") {
            console.log("벽이랑 충돌했습니다.");
            this.$router.push("/member/room/profile/guestbook");
            this.$emit("modalOpenHandler");
          } else if (obstacle.type === "door") {
            let userDetail = useUserDetailsStore();
            userDetail.logout();
            this.$router.push("/login");
          } else {
            console.log("충돌했습니다.");
          }
        }
      }
      if (
        e.key === "ArrowUp" ||
        e.key === "ArrowRight" ||
        e.key === "ArrowLeft" ||
        e.key === "ArrowDown"
      ) {
        this.slime.move(e.key);
        this.SlimePlaySound(1);
        console.log(e.key);
      }
    },
    keyUpHandler(e) {
      // this.boy.kreset();
      this.slime.stop(e.key);
    },
  },
};
</script>

export default class Dialog {
    constructor(x, y, width, height, type) {
      this.img = document.querySelector("#dialog");
      this.x = 0;
      this.y = 0;
      this.text = "";
      this.visible = false;
    }
  
    setPosition(x, y) {
      this.x = x;
      this.y = y;
    }
  
    setText(text) {
      this.text = text;
    }

    show() {
      this.visible = true;
    }
  
    hide() {
      this.visible = false;
    }
  
    draw(ctx) {
      if (!this.visible) {
        return;
      }
  
      ctx.drawImage(this.img, this.x, this.y);
      ctx.fillStyle = "#000000";
      ctx.font = "20px sans-serif";
      const lines = this.text.split('\n');
      const lineHeight = 20;
      for (let i = 0; i < lines.length; i++) {
        ctx.fillText(lines[i], this.x+20 , this.y + 30 + lineHeight * i);
      }
    }
  }
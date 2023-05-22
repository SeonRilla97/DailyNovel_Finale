export default class Obstacle {
    constructor(x, y, width, height , type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = "rgba(0, 0, 0, 0)"; // 투명한 색상
        this.type =type;
      }
  
    update() {}
  
    draw(ctx) {
        ctx.fillStyle = this.color;
        ctx.fillRect(this.x, this.y, this.width, this.height);
      }

    
}
  
export default class Sound {
  constructor(x,y) {
    this.img = document.querySelector("#sound");
    this.x = x||0;
    this.y = y||0;
  }

    update() {}
  
    draw(ctx) {
      ctx.drawImage(this.img, this.x, this.y);
    }

    
}
  
export default class filter{
    constructor(idx,menu,id){
        this.idx = idx,
        this.menu = menu,
        this.id = id
    }

    setidx(idx){
        this.idx = idx;    
    }
    getidx(){
        return this.idx;   
    }
}

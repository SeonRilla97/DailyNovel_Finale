
<script setup>
import { RouterLink, RouterView } from 'vue-router'
import {defineEmits} from 'vue';
import Header from './Modal/header.vue'

const handleClose =defineEmits('closeModal')

</script>

<template>
  <div class="modal-container">
    <Header @modalCloseEventHandler="$emit('closeModal')" class="header"/>
    <Router-view class="content"/>
  </div>
</template>

<style scoped>
  .modal-container{
    display:flex;
    flex-direction: column;
    width:100%;
    height:100%;
  }
    .modal-container .header{
      width:100%;
      height:72px;
    }
    .modal-container .content{
      width:100%;
      flex-grow: 1;

    }
/* 모달 화면 조정 css */
    @keyframes show-effect{
     from{
       transform: scale(0%);
       /* transform: rotate(360deg); */
      }
      to{
        transform: scale(100%);
        /* transform: rotate(100000deg); */
      }  
    }

    @keyframes close-effect{
     from{
       transform: scale(100%);
    }
    to{
      transform: scale(0);
     }  
    }


.screen{
  position:fixed;
  top:0;

  width:100vw;
  height: 100vh;
  display:flex;
  justify-content: center;
  align-items: center;

  background-color:  #00000080
}
.screen > .modal{
  background-color: #FAFFF9;

  position: fixed;
  max-width:1600px;
  width:90vw;
  height: 90vh;


  animation: show-effect 0.5s ease-in-out ;
  /* animation-fill-mode: forwards; */

  
}
.modal.active{
  animation: close-effect 0.5s ease-in-out !important;
  /* animation-direction:reverse !important; */
  animation-fill-mode: forwards;
  
}


/* util */
.d-none{
  display : none;
}

.flex-v{
  display:flex;
  flex-direction: column;
}
.flex-h{
  display: flex;
  flex-direction: row;
}
.flex-center{
  display:flex;
  justify-content: center;
  align-items: center;
}

.inline-block{
  display: inline-block;
}

/*============================================== */
/* style */

.icon-clamp{
  background-image: url(./components/icons/clamp.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 20px 20px;
  width:20px;
  height: 20px;
  display:inline-block;
  transform: rotate(-90deg);
}
.icon-search{
  background-image: url(./components/icons/search.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 20px 20px;
  width:20px;
  height: 20px;
  display:inline-block;
  border: 0;
  background-color: transparent;
  /* transform: rotate(-90deg); */
}

.icon-logo{
  background-image: url(./components/icons/app-logo.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 150px 100px;
  width:150px;
  height: 50px;
  display:inline-block;
  border: 0;
  background-color: transparent;
  /* transform: rotate(-90deg); */
}
.icon-mypage{
  background-image: url(./components/icons/myPage.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 24px 24px;
  width:24px;
  height: 24px;
  display:inline-block;
}
.icon-community{
  background-image: url(./components/icons/Community.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 24px 24px;
  width:24px;
  height: 24px;
  display:inline-block;
}
.icon-cancel{
  background-image: url(./components/icons/cancel.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 20px 20px;
  width:20px;
  height: 20px;
  display:inline-block;
}

.icon-bell{
  background-image: url(./components/icons/bell.svg);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 24px 24px;
  width:24px;
  height: 24px;
  display:inline-block;
}
/*============================================== */
/* reset */
input:focus{outline:none;}
input{
  border:none;
  background: transparent;
  text-indent: 10px;}
</style>

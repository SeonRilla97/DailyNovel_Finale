
<script setup>
import { RouterLink, RouterView } from 'vue-router'
import {defineEmits} from 'vue';
import Header from './Header.vue'

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
  max-width:1280px;
  max-height: 720px;
  width:90%;
  height: 90%;


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

</style>

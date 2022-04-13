<template>
  <div class="text-center">
    <h1>Success</h1>
  </div>
</template>

<script>
  import axios from 'axios';
export default {
  data(){
    return {
      user: null,
      sessionId: null
    }
  },
  methods: {
    placeOrder(){
       axios
        .post(`http://localhost:8080/order/add/${this.sessionId}/?username=${this.user.username}`)
        
    }
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem('user'));
    this.sessionId = localStorage.getItem('sessionId');
    
    this.placeOrder();
  },
  
};
</script>
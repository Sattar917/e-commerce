import { createApp } from 'vue'
import App from './App.vue'
import swal from 'sweetalert'
import router from './router/index.js'
import cookie from './plugins/cookie'
import store from './store'
// import "bootstrap"
// import "bootstrap/dist/css/bootstrap.min.css"

//using axios as a global object
window.axios = require('axios')


createApp(App).use(cookie).use(store).use(router).mount('#app');
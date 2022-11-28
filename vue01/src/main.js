import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

// 导入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'



createApp(App).use(store).use(router).mount('#app')
// const axios = require("axios");

Vue.prototype.$axios = axios;
App.use(ElementPlus)

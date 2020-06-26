import Vue from 'vue'
import router from './router'
import App from './App.vue'
import './plugins/element.js'
import './plugins/axios'
// 导入全局样式表
import './assets/css/global.css'

import axios from 'axios';
axios.defaults.baseURL="http://localhost:8081/"
Vue.prototype.$http=axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

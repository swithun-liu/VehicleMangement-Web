import Vue from 'vue'
import router from './router'
import App from './App.vue'
import './plugins/element.js'
import './plugins/axios'
// 导入全局样式表
import './assets/css/global.css'

import axios from 'axios';
axios.defaults.baseURL="http://localhost:8081/"
// axios请求拦截器, 添加token, 保证拥有获取数据的权限(在请求到达服务器之前在header添加一个字段存放token)
axios.interceptors.request.use(config=>{
  console.log(config)
  config.headers.Authorization=window.sessionStorage.getItem('token')
  //最后必须return config
  return config
})
Vue.prototype.$http=axios


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

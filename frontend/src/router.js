import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/Login.vue'
import Home from './components/Home.vue'
import Welcome from './components/Welcom.vue'
import Dirverinfo from './components/driver/driverinfo.vue'

Vue.use(Router)

const router = new Router({
    routes: [
        { path: '/', redirect: '/login' },
        { path: '/login', component: Login },
        {
            path: '/home', component: Home,
            redirect: '/welcome',
            children: [
                {
                    path: '/welcome', component: Welcome,
                }, {
                    path: '/driverinfo', component: Dirverinfo,
                }
            ]
        }
    ]
})

// 挂载路由导航守卫
router.beforeEach((to, before, next) => {
    //to 要访问
    //from 从哪个路径来
    // next 函数表示放行
    // next() 放行  next('/login') 强制跳转
    if (to.path == '/login') return next()
    //获取token
    const tokenStr = window.sessionStorage.getItem('token')
    if (!tokenStr) return next('/login')
    next()
})


export default router
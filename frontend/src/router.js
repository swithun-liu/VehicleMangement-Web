import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/Login.vue'
import Home from './components/Home.vue'
import Welcome from './components/Welcom.vue'
import Dirverinfo from './components/info/driverinfo.vue'
import Carinfo from './components/info/carinfo.vue'
import Maintenanceinfo from './components/info/maintenanceinfo.vue'
import Vehiclecostinfo from './components/info/vehiclecostinfo.vue'
import Order from './components/list/order.vue'
import Dispatch from './components/list/dispatch.vue'
import Transportation from './components/list/transportation.vue'
import Bill from './components/list/bill.vue'
import Registration from './components/list/registration.vue'

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
                }, {
                    path: '/carinfo', component: Carinfo
                }, {
                    path: '/maintenanceinfo', component: Maintenanceinfo
                }, {
                    path: '/vehiclecostinfo', component: Vehiclecostinfo
                }, {
                    path: '/order', component: Order
                }, {
                    path: '/dispatch', component: Dispatch
                }, {
                    path: '/transportation', component: Transportation
                }, {
                    path: '/bill', component: Bill
                }, {
                    path: '/registration', component: Registration
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
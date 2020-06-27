# 1. backend 后端

配置好Springboot,运行主函数, 启动后端


# 2. frontend 前端

## 2.1. Project setup
```
npm install
```

### 2.1.1. Compiles and hot-reloads for development

配置好vue, cd 进入 frontend, 运行, 启动前端(Vue)
```
npm run serve
```

### 2.1.2. Compiles and minifies for production
```
npm run build
```

### 2.1.3. Lints and fixes files
```
npm run lint
```

### 2.1.4. Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


# 3. 分支

1. 添加新功能新建一个分支 
2. 回到master, merge 新功能分支
3. 新功能分支 推送到远程仓库  `git push -u origin 分支名`


# 4. 记录


## 4.1. 结构

`backend`

```
└─backend
    ├─config
    ├─controller
    ├─Dao
    ├─entity
    └─service
        └─Impl
```

- config
  - CrossConfig.java: 用来解决跨域问题
- entity: 实体(pojo层)--对应一个数据库的表
  - 用到注解: 
    - `@Entity`
    - `@Tabel(name="表名")`
    - `@Colunm([name='列名',length=长度])`
- Dao: 调用entity=>增删改查
  - 用到注解: 
    - @Repository
  - 需要继承
    - `JapRepository<用到的Entity的类名,主键的类型>`
- Service: 业务模块的逻辑应用设计
  - 用到注解: `@Service`

## 4.2. 路由导航守卫

`frontend`-->`router.js`

- 原来的结构
  - ```js
    export default new Router(...)
    ```
- 修改添加守卫(检查浏览器是否保存了服务器返回来的token,没有说明没登陆-->发送去登录页面)
  - ```js
    const router = new Router({
        routes: [
            ...
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

## 4.3. 使用axios发送get request

`frontend`
```js
//`main.js`
import axios from 'axios';
axios.defaults.baseURL="http://localhost:8081/"
Vue.prototype.$http=axios
// .vue中调用
...
      const _this = this;
      this.$http
        .get("/driverinfo", {
          params: this.queryInfo //请求参数
        })
        .then(res => {//res请求返回结果->一般是json对象
          _this.userlist = res.data; //保存起来可以用
        });
...

```

## 4.4. 在请求的header中添加token

`frontend`-->`main.js`

```js
axios.defaults.baseURL="http://localhost:8081/"

// axios请求拦截器, 添加token, 保证拥有获取数据的权限(在请求到达服务器之前在header添加一个字段存放token)
axios.interceptors.request.use(config=>{
  console.log(config)
  config.headers.Authorization=window.sessionStorage.getItem('token')
  //最后必须return config
  return config
})

Vue.prototype.$http=axios
```
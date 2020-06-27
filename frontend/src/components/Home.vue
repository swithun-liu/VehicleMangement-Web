<template>
  <el-container class="home-container">
    <!--头部-->
    <el-header>
      <div>
        <span>运输汽车管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <!--主体-->

    <el-container>
      <!--侧边-->
      <el-aside width="200px">
        <!--侧边栏菜单区-->
        <el-menu
          background-color="darkslategray"
          text-color="#fff"
          active-text-color="#ffd04b"
          :router="true"
          :default-active="activePath"
        >
          <!--一级-->
          <el-submenu :index="item.id+''" v-for="item in menulist" :key="item.id">
            <!--一级菜单模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>{{item.name}}</span>
            </template>

            <!--二级-->
            <el-menu-item
              :index="'/'+subItem.path"
              v-for="subItem in item.children"
              :key="subItem.id"
              @click="saveNavState('/'+subItem.path)"
            >
              <template slot="title">
                <!--图标-->
                <i class="el-icon-location"></i>
                <!--文本-->
                <span>{{subItem.name}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!--后边主体-->

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      activePath: "",
      menulist: [
        {
          name: "信息管理",
          id: 1,
          children: [
            {
              name: "司机列表",
              id: 1,
              path: "driverinfo"
            },
            {
              name: "菜单一二",
              id: 2,
              path: "dirverinfo1"
            }
          ]
        },
        {
          name: "菜单二",
          id: 2,
          children: [
            {
              name: "菜单二一",
              id: 1,
              path: "dirverinfo2"
            },
            {
              name: "菜单二二",
              id: 2,
              path: "dirverinfo3"
            }
          ]
        },
        {
          name: "菜单三",
          id: 3,
          children: [
            {
              name: "菜单三一",
              id: 1,
              path: "dirverinfo4"
            },
            {
              name: "菜单三二",
              id: 2,
              path: "dirverinfo5"
            }
          ]
        }
      ]
    };
  },
  created() {
      this.activePath=window.sessionStorage.getItem('activePath')
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath)
      this.activePath=activePath
    }
  }
};
</script>

<style lang='less' scoped>
.el-header {
  background-color: darkcyan;
  display: flex;
  color: #eee;
  justify-content: space-between;
  align-items: center;
  > div {
    display: flex;
    align-items: center;
  }
}
.el-aside {
  background-color: darkslategray;
}
.el-main {
  background-color: gainsboro;
}
.home-container {
  height: 100%;
}
</style>
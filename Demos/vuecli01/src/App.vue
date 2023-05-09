<template>

  <div id="app">
    <router-link to="/">首页</router-link>---
    <router-link to="/Goods">货物</router-link>---
    <router-link to="/Manager">管理</router-link>---

    <router-link to="/User">用户</router-link>
    <!--在router-view中渲染当前路由对应的组件-->
    <router-view></router-view>

  </div>

</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'

import User from "./routerviews/User.vue";

export default {
  name: 'App',
  components: {User},
  // components: {
  //   HelloWorld
  // }
}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

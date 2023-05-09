<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome 2222 to Your Vue.js App"/>
    <User ref="myUser" name="蜗牛" age="18"/>

    <button @click="testRef">testRef</button>
    <button @click="testParentAndChildren">testAppParentAndChildren</button>
    <button @click="toManager">toManager</button>
    <br/>
    <hr/>

    <router-link to="/">首页</router-link>---
    <router-link to="/Goods">货物</router-link>---
    <router-link to="/Manager">管理</router-link>

    <!--在router-view中渲染当前路由对应的组件-->
    <router-view></router-view>
  </div>
</template>

<script>
//导入其它组件
import HelloWorld from './components/HelloWorld.vue'
import User from "@/components/User.vue";

//导出当前文件为一个组件
export default {
  name: 'App',  //名字
  components: {  //声明引用的外部组件
    HelloWorld   //例如这里是使用的HelloWorld组件 等效于 HelloWorld:HelloWorld
    ,User
  },
  methods:{
    toManager(){
      this.$router.push({
        name:"Manager",
        params:{
          name:"pushName",
          pass:"pushPass"
        }
      })
    },
    testRef(){
      console.log(this.$refs.myUser)
      console.log(this.$refs.myUser.detail)
      this.$refs.myUser.showDetail();
    },
    testParentAndChildren(){
      console.log(this.$parent)
      console.log(this.$children)
    }
  }
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

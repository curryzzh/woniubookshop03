import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
//引入并使用路由插件
import VueRouter from "vue-router";
Vue.use(VueRouter)

//引入路由要使用的组件
import Goods from "@/routerviews/Goods.vue";
import Index from "@/routerviews/Index.vue";
import Manager from "@/routerviews/Manager.vue";
import User from "@/routerviews/User.vue";
Vue.config.productionTip = false
//创建路由对象
let router = new VueRouter({
  routes:[
    {path:"/",name:"Index",component:Index},
    {path:"/Goods",name:"Goods",component:Goods},
    {path:"/Manager",name:"Manager",component:Manager},
    {path:"/User",name:"User",component:User}
  ],
  mode:"history"
})

new Vue({
  render: h => h(App),
  router
}).$mount('#app')

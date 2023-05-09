import Vue from 'vue'    //导入vue
import App from './App.vue'  //导入自定义组件App
import './registerServiceWorker'   //导入其它的js配置


//引入并使用路由插件
import VueRouter from "vue-router";
Vue.use(VueRouter)

//引入路由红要使用的组件
// import Goods from "@/routerviews/Goods.vue";
// import Index from "@/routerviews/Index.vue";
// import Manager from "@/routerviews/Manager.vue";
// import Arms from "@/routerviews/goods/Arms.vue";
// import Food from "@/routerviews/goods/Food.vue";
import router from "@/routers";


Vue.config.productionTip = false  //生产环境时关闭一些不必要的提示

//创建路由对象
// let router = new VueRouter({
//
//   routes:[
//     {
//       path:"/Goods",
//       name:"Goods",
//       component:Goods,
//       children:[ //配置二级路由
//         {path:"/Arms",name:"Arms",component:Arms},
//         {path:"/Food",name:"Food",component:Food}
//       ]
//     },
//     {path:"/",name:"Index",component:Index},
//     {path:"/Goods",name:"Goods",component:Goods},
//     {path:"/Manager",name:"Manager",component:Manager}
//   ],
//   mode:"history"
// })
//创建Vue对象
new Vue({
  render: h => h(App),  //渲染自定义的App组件
  router
}).$mount('#app')  //vue对象挂载到id为app的元素

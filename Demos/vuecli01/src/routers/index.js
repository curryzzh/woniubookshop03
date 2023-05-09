

import Vue from 'vue'

//引入并使用路由插件
import VueRouter from "vue-router";
Vue.use(VueRouter)

//引入组件
import Goods from "@/routerviews/Goods.vue";
import Index from "@/routerviews/Index.vue";
import Manager from "@/routerviews/Manager.vue";
import Arms from "@/routerviews/goods/Arms.vue";
import Food from "@/routerviews/goods/Food.vue";

//配置路由
let router = new VueRouter({
    routes:[
        {path:"/",name:"Index",component:Index},
        {path:"/index",name:"Index",component:Index},
        {path:"/index.html",name:"Index",component:Index},
        {
            path:"/Goods/:name/:pass",
            name:"Goods",
            component:Goods,
            children:[
                {path:"/Arms",name:"Arms",component:Arms},
                {path:"/Food",name:"Food",component:Food}
            ]
        },
        {path:"/Manager",name:"Manager",component:Manager}
    ],
    mode:"history"
})

//导出
export default router;
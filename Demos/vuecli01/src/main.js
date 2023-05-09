import Vue from 'vue'    //导入vue
import App from './App.vue'  //导入自定义组件App
import './registerServiceWorker'   //导入其它的js配置

Vue.config.productionTip = false  //生产环境时关闭一些不必要的提示

//创建Vue对象
new Vue({
  render: h => h(App),  //渲染自定义的App组件
}).$mount('#app')  //vue对象挂载到id为app的元素

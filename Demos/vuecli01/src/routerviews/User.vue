
<template>
  <!--页面模板 template下只能有一个根元素 -->
  <div>
    我是User  我叫{{name}}  我{{age}}岁了

    <p>{{detail}}</p>
    <br/>

    <button @click="showDetail">showDetail</button>
  </div>

</template>

<script>
// js代码
export default {
  name: "User",
  props: {  //声明组件的属性,需要应用该组件的地方指定属性值  props是properties的缩写
    name: String,
    age: Number
  },
  data(){

    return {
      detail:"我在盘石软件园等你呦~"
    }
  }
  ,
  methods:{
    showDetail(){
      alert(this.detail)
    }
  }
  ,
  created() {
    console.log("User组件创建了......")
  }
}
</script>

<style scoped>

/*css样式*/


</style>
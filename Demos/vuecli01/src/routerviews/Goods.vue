<template>
  <div>

    <h1>Goods商品管理</h1>


    <router-link to="/Food">食品</router-link>
    --
    <router-link to="/Arms">军火</router-link>

    <!--在router-view中渲染路由对应的组件-->
    <router-view></router-view>

  </div>
</template>

<script>
export default {
  name: "Goods"
}
</script>

<style scoped>

</style>
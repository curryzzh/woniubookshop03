
let myOrdersVue = new Vue({
  el: "#myOrders",
  data() {
    return {
      order:{},
      orderItems:[],
    }
  },
  methods: {
    initOrder(orderId){
      axios.get("/order/queryByOid?oid=" + orderId).then(response => {
        myOrdersVue.order = response.data
      })
      axios.get("/orderItem/queryByOid?oid=" + orderId).then(response => {
        myOrdersVue.orderItems = response.data;
      })
    }


  },
  created() {
    let orderId = sessionStorage.getItem("orderId")
    console.log("myOrdersVue 对象创建完成了")
    this.initOrder(orderId)
    console.log(orderId)
  }

});
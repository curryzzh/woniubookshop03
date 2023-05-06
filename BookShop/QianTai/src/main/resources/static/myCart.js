
let myCartVue = new Vue({
  el: "#myCart",
  data() {
    return {
      cartItems:[],
      multipleSelection: [],
      totalPrice:0.00
    }
  },
  methods: {
    handleSelectionChange(val) {  //处理选中项目发生变更时
      this.multipleSelection = val;
      console.log(this.multipleSelection)

      let total = 0.0;
      for (let i = 0; i < this.multipleSelection.length; i++) {
        total += this.multipleSelection[i].sumPrice
      }
      this.totalPrice=total;
    },
    handleBuyCountChange(cartItem){  //购买量更新时
      //新的值
      console.log(cartItem)
      //cartItem.sumPrice = cartItem.bookPrice * cartItem.buyCount;
      axios.postForm("/cart/freshBuycount",{bookId:cartItem.bookId,buyCount:cartItem.buyCount})
          .then(response => {
            let responseData = response.data;
            cartItem.buyCount = responseData.buyCount;
            cartItem.sumPrice = responseData.sumPrice;
          })

    },
    handleDelete(cartItem){  //点击删除按钮时
      console.log(cartItem)

      axios.postForm("/cart/freshBuycount",{bookId:cartItem.bookId,buyCount:0})
          .then(response => {
            //做删除,不需要更新小计之类的
          })

      this.$refs.multipleTable.toggleRowSelection(cartItem,false);
      for (let i = 0; i < this.cartItems.length; i++) {
        if (this.cartItems[i].bookId == cartItem.bookId){
          this.cartItems.splice(i,1)
          break;
        }
      }
    },
    toOrderPreview(){  //提交结算
      publicHeaderVue.refreshPublicContent("/orderPreview")
    }
    ,
    initCartItems(){  //初始购物车信息
      axios.postForm("/cart/cartItemList")
          .then(response => {
             console.log(response.data)
            myCartVue.cartItems = response.data;
          })
    }
  },
  created() {
    console.log("myCartVue 对象创建完成了")

    this.initCartItems();
  }

});

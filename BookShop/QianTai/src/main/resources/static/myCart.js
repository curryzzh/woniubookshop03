
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

      this.calculateTotalPrice();

      // let total = 0.0;
      // for (let i = 0; i < this.multipleSelection.length; i++) {
      //   total += this.multipleSelection[i].sumPrice
      // }
      // this.totalPrice=total;
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

            myCartVue.calculateTotalPrice();

          })

    },
    handleDelete(cartItem){  //点击删除按钮时
      console.log(cartItem)

      axios.postForm("/cart/freshBuycount",{bookId:cartItem.bookId,buyCount:0})
          .then(response => {
            //做删除,不需要更新小计之类的

            //手动触发下当前项的取消选中
            myCartVue.$refs.multipleTable.toggleRowSelection(cartItem,false);

            //删除cartItems中的对应项
            for (let i = 0; i < myCartVue.cartItems.length; i++) {
              if (myCartVue.cartItems[i].bookId == cartItem.bookId){
                myCartVue.cartItems.splice(i,1)
                break;
              }
            }

          })

    },
    toOrderPreview(){  //提交结算

      //至少一个计算项
      if(this.multipleSelection.length<1){
          alert("至少选中一个结算项")
          return
      }

        //携带 当前的选中项以及总价信息到订单预览页去
      sessionStorage.setItem("multipleSelection",JSON.stringify(this.multipleSelection));
      sessionStorage.setItem("totalPrice",this.totalPrice)

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
    ,
    calculateTotalPrice(){
        let bookIds = [];
      for (let i = 0; i <this.multipleSelection.length; i++) {
        bookIds.push(this.multipleSelection[i].bookId)
      }

      if (bookIds.length<1){
        console.log("没有选中项")
        myCartVue.totalPrice = 0.00;
        return;
      }

      axios.postForm("/cart/calculateTotalPrice",{bookIds:bookIds})
          .then(response => {
            myCartVue.totalPrice = response.data;
          })

    }
  },
  created() {
    console.log("myCartVue 对象创建完成了")

    this.initCartItems();
  }

});

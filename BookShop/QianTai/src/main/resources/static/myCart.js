
let myCartVue = new Vue({
  el: "#myCart",
  data() {
    return {
      cartItems:[{
        bookId: 3,
        bookName: 'name',
        bookImgSrc: '/1.png',
        bookPrice: 6.6,
        buyCount:2,
        sumPrice:13.2
      },
        {
          bookId: 5,
          bookName: 'name',
          bookImgSrc: '/1.png',
          bookPrice: 8.8,
          buyCount:2,
          sumPrice:17.6
        }
        ,
        {
          bookId: 6,
          bookName: 'name',
          bookImgSrc: '/1.png',
          bookPrice: 9.9,
          buyCount:2,
          sumPrice:19.8
        }],
      multipleSelection: [],
      totalPrice:0.00
    }
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection)

      let total = 0.0;
      for (let i = 0; i < this.multipleSelection.length; i++) {
        total += this.multipleSelection[i].sumPrice
      }
      this.totalPrice=total;
    },
    handleBuyCountChange(cartItem){
      //新的值
      console.log(cartItem)
      cartItem.sumPrice = cartItem.bookPrice * cartItem.buyCount;

    },
    handleDelete(cartItem){
      console.log(cartItem)
      this.$refs.multipleTable.toggleRowSelection(cartItem,false);
      for (let i = 0; i < this.cartItems.length; i++) {
        if (this.cartItems[i].bookId == cartItem.bookId){
          this.cartItems.splice(i,1)
          break;
        }
      }
    },
    toOrderPreview(){
      publicHeaderVue.refreshPublicContent("/orderPreview")
    }
  },
  created() {
    console.log("myCartVue 对象创建完成了")
  }

});

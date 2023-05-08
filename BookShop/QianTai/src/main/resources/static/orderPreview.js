
let orderPreview = new Vue({
  el: "#orderPreview",
  data() {
    return {
      addressId:'',  //选中地址的id
      //地址可选项
      options: [{
        id: 1,
        detail: '地址AAAAAAAA'
      }, {
        id: 2,
        detail: '地址BBBBBBBBBB'
      }, {
        id: 3,
        detail: '地址CCCCCCCCCC'
      }],
      //选中的购物项
      cartItems:[]
      ,
      //新增地址模态框相关的
      dialogFormVisible: false,
      closeOnClickModal: false,
      totalPrice:"0.00"
    }
  },
  methods: {

  },
  created() {

    let selectionBooks = JSON.parse( sessionStorage.getItem("multipleSelection") );
    let totalPrice = sessionStorage.getItem("totalPrice")

    console.log(selectionBooks)
    console.log(totalPrice)

    this.cartItems = selectionBooks;
    this.totalPrice = totalPrice;

    console.log("orderPreviewVue 对象创建完成了")
  }

});

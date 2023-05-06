
let orderPreview = new Vue({
  el: "#orderPreview",
  data() {
    return {
      addressId:'',
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
          bookPrice: 6.6,
          buyCount:2,
          sumPrice:13.2
        }]
      ,
      dialogFormVisible: false,
      closeOnClickModal: false
    }
  },
  methods: {

  },
  created() {
    console.log("orderPreviewVue 对象创建完成了")
  }

});

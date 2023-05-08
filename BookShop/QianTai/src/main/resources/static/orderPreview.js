
let orderPreviewVue = new Vue({
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
      cartItems:[],
      totalPrice:"0.00",
      //新增地址模态框相关的
      dialogFormVisible: false,
      closeOnClickModal: false,
      addAddressForm:{
        province:'',
        city:'',
        area:'',
        detailAddress:'',
        tel:'',
        reciver:'',
        emailCode:'',
        isDefault:'0'
      },
      provinceList:[],
      provinceId:-1,
      cityList:[],
      cityId:-1,
      areaList:[],
      areaId:-1
    }
  },
  methods: {
    initProvinceLsit(){
      axios.postForm("/address/queryByPid",{Pid:0})
          .then( response => {
            orderPreviewVue.provinceList = response.data;
          })
    },
    reloadCityList(){
      //复原子一级的数据
      this.cityList=[]
      this.cityId=-1
      this.areaList=[]
      this.areaId=-1

      axios.postForm("/address/queryByPid",{Pid:this.provinceId})
          .then( response => {
            orderPreviewVue.cityList = response.data;
          })

    }
    ,
    reloadAreaList(){
      this.areaList=[]
      this.areaId=-1


      axios.postForm("/address/queryByPid",{Pid:this.cityId})
          .then( response => {
            orderPreviewVue.areaList = response.data;
          })

    }

  },
  created() {

    let selectionBooks = JSON.parse( sessionStorage.getItem("multipleSelection") );
    let totalPrice = sessionStorage.getItem("totalPrice")

    console.log(selectionBooks)
    console.log(totalPrice)

    this.cartItems = selectionBooks;
    this.totalPrice = totalPrice;

    console.log("orderPreviewVue 对象创建完成了")

    //初始化数据
    this.initProvinceLsit();

  }

});

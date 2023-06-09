
let orderPreviewVue = new Vue({
  el: "#orderPreview",
  data() {
    return {
      addressId:'',  //选中地址的id
      //地址可选项
      addressList: [],
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
        isDefault:''
      },
      provinceList:[],
      provinceId:-1,
      cityList:[],
      cityId:-1,
      areaList:[],
      areaId:-1,
      isDefaultAddress:false,
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
    ,
    submitAddAddressForm(){

      //处理省市区 id转字符串
      for (let i = 0; i <this.provinceList.length; i++) {
        if (this.provinceList[i].id == this.provinceId){
          this.addAddressForm.province = this.provinceList[i].name;
          break;
        }
      }
      for (let i = 0; i <this.cityList.length; i++) {
        if (this.cityList[i].id == this.cityId){
          this.addAddressForm.city = this.cityList[i].name;
          break;
        }
      }
      for (let i = 0; i <this.areaList.length; i++) {
        if (this.areaList[i].id == this.areaId){
          this.addAddressForm.area = this.areaList[i].name;
          break;
        }
      }

      //处理是否默认地址
      this.addAddressForm.isDefault = this.isDefaultAddress ? "1" : "0";

      console.log(this.addAddressForm)

      //保存地址
      axios.post("/address/add",this.addAddressForm)
          .then( response => {
            orderPreviewVue.dialogFormVisible = false;
            orderPreviewVue.initAddressList()
          })

    }
    ,
    showAddAddressForm(){
      //重置表单关联的数据
      this.addAddressForm={
          province:'',
          city:'',
          area:'',
          detailAddress:'',
          tel:'',
          reciver:'',
          emailCode:'',
          isDefault:''
      }

      this.provinceId=-1
      this.cityId=-1
      this.areaId=-1
      this.isDefaultAddress=false

      //显示表单
      this.dialogFormVisible = true;
    }
    ,
    initAddressList(){
      axios.postForm("/address/list")
          .then( response => {
            let addressList = response.data;
            for (let i = 0; i < addressList.length; i++) {
              let item = addressList[i];
              item.detail = item.province + item.city +item.area +item.detailAddress+"    " +item.reciver;

              //确认默认地址
              if (item.isDefault == "1"){
                orderPreviewVue.addressId = item.id;
              }

            }

            orderPreviewVue.addressList = addressList;
          })
    }
    ,
    submitOrder(){
      if (!this.addressId){
        alert("请选择收货地址后再提交")
        return
      }

      let bookIds = []
      for (let i = 0; i < this.cartItems.length; i++) {
        bookIds.push(this.cartItems[i].bookId)
      }

      console.log(this.addressId)
      console.log(bookIds)

      let params = {
        addressId:this.addressId,
        bookIds:bookIds
      }
      axios.postForm("/order/create",params)
          .then(response => {
            let responseData = response.data;
            if (responseData.split("-")[0] === "ok"){
              sessionStorage.setItem("orderId", responseData.split("-")[1])
              publicHeaderVue.refreshPublicContent("/myOrders")
            }else {
              alert(responseData)
            }
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
    this.initAddressList();

  }

});

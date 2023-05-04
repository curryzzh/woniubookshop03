
let loginVue = new Vue({
  el: "#login",
  data() {
    return {

    }
  },
  methods: {
    refreshKaptchaCode(){
      document.getElementById("codeImg").src = "/user/getKaptchaCode?date="+new Date().valueOf();
    }


  },
  created() {
    console.log("loginVue 对象创建完成了")

  }

});
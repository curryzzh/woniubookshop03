
let loginVue = new Vue({
  el: "#login",
  data() {
    return {
      loginForm:{
        "username":'',
        "password":'',
        "code":''
      }

    }
  },
  methods: {
    refreshKaptchaCode(){
      document.getElementById("codeImg").src = "/user/getKaptchaCode?date="+new Date().valueOf();
    }
    ,
    toLogin(){
      //前端校验.....

      axios.postForm("/user/login",this.loginForm)
          .then(response => {
            let responseData = response.data;
             if (responseData == "ok"){
               //alert("登录成功")
               location.href="/"
             }else {
               alert(responseData)
             }
          })
    }


  },
  created() {
    console.log("loginVue 对象创建完成了")

  }

});
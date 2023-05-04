
let registerVue = new Vue({
  el: "#register",
  data() {
    return {
      registerForm:{
        username:'',
        password:'',
        repass:'',
        email:'',
        emailCode:''
      }

    }
  },
  methods: {
    getEmailCode(){
      if (this.registerForm.email.length<1){
        alert("请填写邮箱地址")
        return;
      }

      // 可以校验邮箱地址是否合法

      //获取验证码
      axios.postForm("/user/requestEmailCode",{email:this.registerForm.email})
          .then( (response) => {
              let responseData = response.data;
              if (responseData=="ok"){
                 alert("验证码发送成功")
              }else {
                alert(responseData)
              }
          } )

    }
    ,
    registerUser(){
      // 前端数据校验 ....

      console.log(this.registerForm)

      axios.postForm("/user/register",this.registerForm)
          .then( response => {
            let responseData = response.data;
            if (responseData=="ok"){
              alert("注册成功")
            }else {
              alert(responseData)
            }
          } )

    }

  },
  created() {
    console.log("registerVue 对象创建完成了")

  }

});
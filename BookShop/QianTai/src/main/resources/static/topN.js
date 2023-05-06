

  let topNVue = new Vue({
    el: "#topN",
    data() {
        return {
          N:9,
          topNBooks:[]
        }
    },
    methods: {
      initTopNBooks(){
         axios.postForm("/book/topN",{N:this.N})
             .then( response => {
                 topNVue.topNBooks = response.data;
                 console.log(topNVue.topNBooks)
             } )
      }
      ,
      seeBookDetailOf(bookId){
          // sessionStorage  数据仅在页面打开期间存在
          // sessionStorage.setItem("key","vaule")
          // sessionStorage.getItem("key")
          // sessionStorage.removeItem("key")
          // sessionStorage.clear();

          //localStorage  数据会一直被浏览器保留,  api和sessionStorage 基本一致

          //先会话保存bookId
          sessionStorage.setItem("bookIdOfSeeDetail",bookId)

          //切换页面到图书详情页
          publicHeaderVue.refreshPublicContent("/bookDetail")
      }
      ,
        addToCart(bookId){
            if (!publicHeaderVue.currentUser){
                alert("请先登录再加购")
                return;
            }

            axios.postForm("/cart/addBook",{bookId:bookId})
                .then( response => {
                    alert(response.data)
                } )

        }
    },
    created() {
      console.log("topNVue 对象创建完成了")

      this.initTopNBooks();
    }

  });


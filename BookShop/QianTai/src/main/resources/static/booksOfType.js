
let booksOfTypeVue = new Vue({
  el: "#booksOfType",
  data() {
    return {
        bookType: {},
        pageData:{
          current:1,
          size:3,
          total:-1,
          records:[],
          pages:-1
        }
    }
  },
  methods: {
    initPageData(){
        let params = {
          current: this.pageData.current,
          size: this.pageData.size,
          typeId: this.bookType.id,
        }

        axios.postForm("/book/pageOfType",params)
            .then( response => {
              booksOfTypeVue.pageData = response.data;
            })

    }
    ,
      goPage(pageNum){
        if (pageNum<1 || pageNum > this.pageData.pages || pageNum==this.pageData.current){
            console.log("无效pageNum值 "+pageNum)
            return;
        }

        console.log("查询第"+pageNum+"页数据")
        this.pageData.current=pageNum;
        this.initPageData();
      }
      ,
      seeBookDetailOf(bookId){
          //先会话保存bookId
          sessionStorage.setItem("bookIdOfSeeDetail",bookId)

          //切换页面到图书详情页
          publicHeaderVue.refreshPublicContent("/bookDetail")
      }
  },
  created() {
    console.log("booksOfTypeVue 对象创建完成了")

    let bookTypeJsonString = sessionStorage.getItem("bookType");
    console.log(bookTypeJsonString)
    this.bookType = JSON.parse(bookTypeJsonString)

    this.initPageData();
  }

});
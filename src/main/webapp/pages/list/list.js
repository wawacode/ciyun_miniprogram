// pages/list/list.js
var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    // currentTab:1
    "result":"" ,
    datas:""
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    //载入时请求数据更新array和currentTab
    var that = this;
    app.postCallBack('medrpt/list', { thirdSession: getApp().thirdSession }, that.callback);
  },
callback:function(res){
    var datass = res.data.datas
    console.log(res.data.datas.medExamType)
    var that = this
      that.setData({
        result: 0,
        datas: datass
      })
},
  //查报告
  mechanism:function(){
    wx.navigateTo({
      url: '../mechanism/mechanism',
    })
  },
  details: function (e) {
    var id = e.target.dataset.id
    //将id值传给后台
    app.listId=id
    wx.navigateTo({
      url: "../detail/detail"
    })
  }
})
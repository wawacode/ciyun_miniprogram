// pages/list/list.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    // currentTab:1
    "result": 1,
    "datas": [
      { "id":10001,"medCorpName": "航天健康管理中心", "medDate": "2017-08-20", "name": "张三"},
      { "id":10002,"medCorpName": "北大健康管理中心", "medDate": "2016-01-12", "name": "张三"},
      { "id":10003,"medCorpName": "北大健康管理中心", "medDate": "2016-01-12", "name": "张三"},
      { "id":10004,"medCorpName": "北大健康管理中心", "medDate": "2016-01-12", "name": "李四"}
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //载入时请求数据更新array和currentTab
    // currentTab = 1表示没有体检报告，currentTab=0表示有体检报告
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
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
    console.log(id)
    wx.navigateTo({
      url: "../detail/detail"
    })
  }
})
// pages/detail/detail.js
//获取应用实例
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['基本信息', '汇总分析','超标项', '报告详情'],
    navbarIndex:0,
    searchSwich:false,
    currentClassifyId:0, //当前分类列表ID
    detailStyle:"padding-top:64px;",
    detail_th:"top:42px;"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var json={
      id:getApp().listId,
      thirdSession: getApp().thirdSession
    }
    this.setReportListHeight();
    app.postCallBack('medrpt/detail/' + getApp().listId, json, function (res) {
        that.setData({
          data:res.data.datas
        });
        console.log(that.data);
    });
  },
  // 设置项目列表高度
  setReportListHeight:function(){
    this.setData({
      reportListHeight: app.globalData.deviceHeigth + "px"
    })
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
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      // console.log(this.prototype.route)
    }
    console.log(getCurrentPages()[0].route)
    return {
      title: '慈云微报告',
      path: getCurrentPages()[0].route,
      success: function (res) {
        console.log('转发成功',res)
      },
      fail: function (res) {
        console.log('转发失败',res)
      }
    }
  },
  /**
   * 用户点击tab切换
   */
  navbarTap: function (e) {
    this.setData({
      navbarIndex: e.currentTarget.dataset.idx
    })
    console.log(e.currentTarget);
  },
  //打开报告分类列表
  openSearchList:function(e){
    console.log(e.currentTarget.dataset.name);
    
    this.setData({
      searchSwich: !this.data.searchSwich,
      currentClassifyId: e.currentTarget.dataset.name || 0
    })
  },
  // 图片展示
  previewImage:function(e){
    var that = this;
    var idx = e.target.dataset.idx;
    console.log(e.target.dataset.idx);
    wx.previewImage({
      urls: that.data.medDetails.idx.mediaList
    })
  }
})
// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['基本信息', '汇总分析','超标项', '报告详情'],
    navbarIndex:0,
    searchSwich:false,
    currentClassifyId:0 //当前分类列表ID 
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
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
    console.log(this.data);
    this.setData({
      searchSwich: !this.data.searchSwich
    })
  },
  // 图片展示
  previewImage:function(e){
    console.log(e.target.dataset.url);
    wx.previewImage({
      urls: [e.target.dataset.url, "https://fdstatic.ciyun.cn//group1/M00/64/F8/CgHMCFlxsU-AH5f2AArEuEP0T8Q689.png"]
    })
  }
})
// pages/list/list.js
var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    // currentTab:1
    "result": 1,
    datas:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    //载入时请求数据更新array和currentTab
    // currentTab = 1表示没有体检报告，currentTab=0表示有体检报告
    console.log(getApp().thirdSession)
    // app.postCallBack('medrpt/list', { "thirdSession": getApp().thirdSession }, that.callback);
    var that = this
    wx.request({
      url: 'https://minirpt.ciyun.cn/user/medrpt/list',
      data: { thirdSession: getApp().thirdSession },
      method: 'POST',
      header: {
        'content-type': 'application/json',
        'Cookie': 'JSESSIONID=' + wx.getStorageSync('jSessionId')
      },
      success: function (res) {
          console.log(res.data)
          console.log(res.data.datas.length)
          // 
          var datass = res.data.datas
          that.setData({
            "datas": datass
          })
          console.log(that.data.datas)
          if (res.data.datas.length == 0) {
            that.setData({
              result: 1
            })
          } else {
            that.setData({
              result: 0
            })
          }
          console.log(that.data.datas)
      },
      fail: function (res) {
      },
      complete: function (res) {
        wx.hideLoading();
        // var that = this
        var datass = res.data.datas
        that.setData({
          "datas": datass
        })
        if (res.data.datas.length == 0) {
          that.setData({
            result: 1
          })
        } else {
          that.setData({
            result: 0
          })
        }
      }

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
    app.listId=id
    console.log(id)
    wx.navigateTo({
      url: "../detail/detail"
    })
  }
})
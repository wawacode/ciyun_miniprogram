//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    array: [{
      message: 'foo',
    }, {
      message: 'bar'
    }],
    motto: 'Hello World',
    userInfo: {},
    item: {
      index: 0,
      msg: 'this is a template',
      time: '2016-09-15'
    },
    currentTab: 0,
    hover_class: "",
    second_height: "",
    data: []
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    var that = this;
    //微信获取设备可用高度
    this.setData({
      second_height: app.globalData.deviceHeigth + "px"
    });

    // 可以通过 wx.getSetting 先查询一下用户是否授权了 "scope.record" 这个 scope
    wx.getSetting({
      success(res) {
        console.log(res);
        if (!res.authSetting['scope.userInfo']) {
          wx.authorize({
            scope: 'scope.userInfo',
            success() {
              // 用户已经同意小程序使用录音功能，后续调用 wx.startRecord 接口不会弹窗询问
            }
          })
        }
        wx.getUserInfo({
          success: function (res) {
            var userInfo = res.userInfo
            var nickName = userInfo.nickName
            var avatarUrl = userInfo.avatarUrl
            var gender = userInfo.gender //性别 0：未知、1：男、2：女 
            var province = userInfo.province
            var city = userInfo.city
            var country = userInfo.country;
            app.city = province;
            console.log(res);
          }
        })
      }
    })
    //微信获取设备物理位置
    wx.getLocation({
      type: 'wgs84',
      success: function (res) {
        var latitude = res.latitude
        var longitude = res.longitude
        var speed = res.speed
        var accuracy = res.accuracy
        console.log(res);
      }
    });
    //页面请求接口；
    app.postCallBack('medrpt/listMedCorp', { thirdSession: wx.getStorageSync('thirdSession') }, that.callBack);
  },
  navbarTap: function (e) {

    console.log(this.data.currentTab, e.target.dataset.idx);
    this.setData({
      currentTab: e.target.dataset.idx
    })
  },
  //
  callBack: function (res) {
    var userCity = app.city;
    var datas = res.data.datas;
    for (var i in datas){
      
    }
      this.setData({
        data: res.data.datas
      });
  },
  //全部订单
  importPage: function (e) {
    var medcorpid = e.currentTarget.dataset.medcorpid;
    console.log(medcorpid);
    wx.setStorageSync('medcorpid', medcorpid);
    wx.navigateTo({
      url: '../report/report' + "?medcorpid=" + medcorpid
    })
  }
})

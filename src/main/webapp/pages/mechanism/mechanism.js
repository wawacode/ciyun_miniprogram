//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    currentTab: 0,//默认第一个
    second_height: "",//设备可用高度
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
    this.setData({
      second_height: app.globalData.deviceHeigth + "px"
    });
    // 可以通过 wx.getSetting 先查询一下用户是否授权了 "scope.record" 这个 scope
    console.log(app.loginStatus);
    if (!app.loginStatus){
      setTimeout(function(){
        wx.openSetting({
          success(res) {
            console.log("one");
            if (res.authSetting['scope.userInfo']) {
              console.log("tuo");
              wx.getUserInfo({
                lang: "zh_CN",
                success: function (res) {
                  var userInfo = res.userInfo
                  var nickName = userInfo.nickName
                  var avatarUrl = userInfo.avatarUrl
                  var gender = userInfo.gender //性别 0：未知、1：男、2：女 
                  var province = userInfo.province
                  var city = userInfo.city
                  var country = userInfo.country;
                  app.province = province;
                  app.loginStatus = true
                  console.log(province);
                  //页面请求接口；
                  app.postCallBack('medrpt/listMedCorp', { thirdSession: wx.getStorageSync('thirdSession') }, that.callBack);
                },fail:function(res){
                  //页面请求接口；
                  app.postCallBack('medrpt/listMedCorp', { thirdSession: wx.getStorageSync('thirdSession') }, that.callBack);
                }
              })
            } else {
              //页面请求接口；
              app.postCallBack('medrpt/listMedCorp', { thirdSession: wx.getStorageSync('thirdSession') }, that.callBack);
            }
          }, fail(res) {
            console.log(res)
          }
        });
      },500)

    }else{
      app.postCallBack('medrpt/listMedCorp', { thirdSession: wx.getStorageSync('thirdSession') }, that.callBack);
    }
    
    //微信获取设备物理位置
    // wx.getLocation({
    //   type: 'wgs84',
    //   success: function (res) {
    //     var latitude = res.latitude
    //     var longitude = res.longitude
    //     var speed = res.speed
    //     var accuracy = res.accuracy
    //   }
    // });
  },
  
  //焦点省份切换
  navbarTap: function (e) {
    this.setData({
      currentTab: e.target.dataset.idx
    })
  },
  //页面模板渲染
  callBack: function (res) {
    var userCity = app.province || '';
    console.log(userCity);
    var datas = res.data.datas;

    for (var i in datas){
      if (datas[i].city == userCity){
        var arr = datas.splice(i, 1);
        datas.unshift(arr[0]);
        break;
      }
    }
      this.setData({
        data: res.data.datas
      });
      console.log(datas);
  },
  //选择机构跳转
  importPage: function (e) {
    var medcorpid = e.currentTarget.dataset.medcorpid;
    console.log(medcorpid);
    wx.setStorageSync('medcorpid', medcorpid);
    wx.navigateTo({
      url: '../report/report' + "?medcorpid=" + medcorpid
    })
  }
})

//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: '欢迎查看慈云微报告',
    item:"images/logo.png",
    name: "中金慈云", 
    btn:"login",
    code:"",
    Return:"",
    disabled: true,
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  
  onLoad: function () {
    
  },
  onShow: function () {
    app.loginStatus = false
    var that = this;
    // 在页面加载时获取thirdSession值
    wx.login({
      success: function (res) {
        var code = res.code
        app.postCallBack('authorize/getThirdSession', { code: code }, that.callback);
      }
    })
    // 获取用户信息
    wx.getUserInfo({
      lang: "zh_CN",
      success: function (res) {
        var userInfo = res.userInfo
        // 敏感数据
        var watermark = res.encryptedData;
        // 用户信息
        var nickName = userInfo.nickName
        var avatarUrl = userInfo.avatarUrl
        var gender = userInfo.gender //性别 0：未知、1：男、2：女 
        var province = userInfo.province
        var city = userInfo.city
        var country = userInfo.country
        app.gender = gender
        app.nickName = nickName
        app.avatarUrl = avatarUrl
        app.city = city
        app.province = province
        app.country = country
        app.loginStatus = true
      }
    })
  },
  //向后台获取到的值后，执行函数
  callback: function (res) {
      this.setData({
        Return: res.data,
        disabled: false,
      })
      app.thirdSession = res.data.datas.thirdSession;
      wx.setStorageSync('thirdSession', res.data.datas.thirdSession)
      wx.setStorageSync('jSessionId', res.data.datas.jSessionId)
  },
  // 登录/注册
  register:function(){
    var personStatus = this.data.Return.datas.personStatus;
    console.log(app.appId)
    if (personStatus == 0) {//用户未注册过慈云平台或已注册但是未登录过慈云小程序
        wx.navigateTo({
          url: '../register/register'
        })
    } else if (personStatus == 1) {//登录过慈云小程序
        wx.navigateTo({
          url: '../list/list'
        })
      }
    }
})

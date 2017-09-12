//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: '欢迎查看慈云微报告',
    userInfo: {
    },
    item:"images/logo.png",
    name: "中金慈云", 
    btn:"login",
    code:"",
    Return:""
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    var that = this;
    wx.login({
      success: function (res) {
        var code=res.code
        app.postCallBack('authorize/getThirdSession', { code: code }, that.callback);
      }
    })
    
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      this.setData({
        userInfo:userInfo
      })
    })
    wx.getUserInfo({
      lang:"zh_CN",
      success: function (res){
        var userInfo = res.userInfo
        // 敏感数据
        var watermark = res.encryptedData;
        console.log(watermark)
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
        app.loginStatus=true
        console.log(province)
        console.log(city)
        console.log(getApp().city)
      }
    })
  },
  //获取数据
  callback: function (res) { 
    console.log(res);
    this.setData({
      Return: res.data
    })
    app.thirdSession = res.data.datas.thirdSession;
    wx.setStorageSync('thirdSession', res.data.datas.thirdSession)
    wx.setStorageSync('jSessionId',res.data.datas.jSessionId)
  },
  // 登录/注册
  register:function(){
    var personStatus = this.data.Return.datas.personStatus;
    console.log(personStatus)
    if (personStatus==""){
      return false
    }else{
      if (personStatus == 0) {
        wx.navigateTo({
          url: '../register/register'
        })
      } else if (personStatus == 1) {
        wx.navigateTo({
          url: '../list/list'
        })
      }
    }
  }
})

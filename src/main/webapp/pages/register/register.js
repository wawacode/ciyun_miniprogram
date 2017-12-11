// pages/register/register.js
var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userName: "",
    Verification: "",
    verifyInfo: "获取验证码",
    isdisable: false,
    color:'#666666',
    background:"#d3d3d3",
    state:"下一步",
    disabled: true,
  },

  //手机号
  userNameInput: function (e) {
    this.setData({
      userName: e.detail.value
    })
  },
  //获取验证码
  code: function () {
    var that = this
    var count = 60;
    var re = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    //向后台请求数据
    if (!re.test(this.data.userName)) {
      app.showToast("手机号错误",0)
      return false;
    } else {
      if (that.data.isdisable == false) {
        var json={
          telephone: this.data.userName,
          thirdSession: getApp().thirdSession
        }
        app.postCallBack('authorize/validsmscode', json, that.callback);
      }
    }
  },
  callback: function (res){
    var count = 120;
    var that=this
    app.showToast("发送成功")
      var timer = setInterval(function () {
        count--;
        if (count >= 1) {
          that.setData({
            verifyInfo: count + 's',
            color: '#6fba2c'
          })
        } else {
          that.setData({
            verifyInfo: '获取验证码',
            color: '#666666',
            background: "#6fba2c"
          })
          clearInterval(timer);
          that.data.isdisable = false;
        }
      },1000);
      that.data.isdisable = true;

  },
  //验证码
  Verification: function (e) {
    if (e.detail.value.length ==4 ) {
      this.setData({
        background: "#6fba2c",
        disabled: false
      })
      this.setData({
        Verification: e.detail.value
      })
    } else {
      this.setData({
        background: "#d3d3d3",
        disabled: true
      })
    }
    
  },
  //登录
  nexts: function () {
    var that=this
    var json={
      telephone: this.data.userName,
      smscode: this.data.Verification,
      thirdSession: getApp().thirdSession
    }
    app.postCallBack('authorize/login', json, that.callback2);
  },
  callback2:function(res){
    var that = this
    var result = res.data.result

      if (res.data.datas.isRegisterAndLogin == 0) {
        // that.setData({
        //   btn: 'nexts'
        // })
        var loginStatus = getApp().loginStatus;
        if (!loginStatus) {
          wx.openSetting({
            //重新请求获取定位
            success: function (res) {
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
                  wx.reLaunch({
                    url: "../lower/lower"
                  })
                }
              })
            }
          })
        } else {
          wx.navigateTo({
            url: "../lower/lower"
          })
        }
      } else if (res.data.datas.isRegisterAndLogin == 1) {

        if (result !=0) {
          app.showToast(res.data.message, 0)
        } else{
          wx.redirectTo({
            url: '../list/list',
          })
        }
       
      }
  }
})
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
    btn:"login"
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      this.setDaxta({
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
        console.log(gender + '......' + gender + '......' + avatarUrl + '......' + city + '......' + province + '......' + country)
        app.gender = gender
        app.nickName = nickName
        app.avatarUrl = avatarUrl
        app.city = city
        app.province = province
        app.country = country
        app.loginStatus=true
        /*wx.request({
          url: '/user/authorize/valSignature',//请求地址
          data: {//发送给后台的数据
            rawData:watermark,
            // signature:,
            // thirdSession:,
          },
          header: {//请求头
            "Content-Type": "applciation/json"
          },
          method: "POST",//get为默认方法/POST
          success: function (res) {//成功
            console.log(res.data);
  　　　　　　that.setData({
    　　　　　　logs: res.data.result
  　　　　　　})
          },
          fail: function (err) { },//请求失败
          complete: function () { }//请求完成后执行的函数
        })*/
      }
    })
  },
  
  // 登录/注册
  register:function(){
    wx.reLaunch({
      url: '../register/register'
    })
  }
})

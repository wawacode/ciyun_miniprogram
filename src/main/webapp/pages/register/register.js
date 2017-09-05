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
    state:"下一步",
    "result": 0,
    "message": "ok",
    "datas": {
      "isRegister": 0
    }
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
  onShareAppMessage: function () {

  },
  //手机号
  userNameInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      userName: e.detail.value
    })
  },
  //获取验证码
  code: function () {
    console.log(this.data.userName)
    var that = this
    var count = 60;
    var re = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    //向后台请求数据
    if (!re.test(this.data.userName)) {
      wx.showModal({
        title: "请输入正确的手机号"
      });
      return false;
    } else {
      if (that.data.isdisable == false) {
        // that.sendcode()
       /* wx.request({
          url: '/user/authorize/validsmscode',//请求地址
          data: {//发送给后台的数据
            telephone: that.data.userName
            // thirdSession:,
          },
          header: {//请求头
            "Content-Type": "applciation/json"
          },
          method: "POST",//get为默认方法/POST
          success: function (res) {//成功
            console.log(res.data);
            wx.showModal({
              title: "发送成功"
            });
            var timer = setInterval(function () {
              count--;
              if (count >= 1) {
                that.setData({
                  verifyInfo: count + 's'
                })
              } else {
                that.setData({
                  verifyInfo: '获取验证码'
                })
                clearInterval(timer);
                that.data.isdisable = false;
              }
            }, 1000);
            that.data.isdisable = true;
          },
          fail: function (err) { },//请求失败
          complete: function () { }//请求完成后执行的函数
        })*/
        wx.showModal({
          title: "发送成功"
        });
        var timer = setInterval(function () {
          count--;
          if (count >= 1) {
            that.setData({
              verifyInfo: count + 's'
            })
          } else {
            that.setData({
              verifyInfo: '获取验证码'
            })
            clearInterval(timer);
            that.data.isdisable = false;
          }
        }, 1000);
        that.data.isdisable = true;
      }
    }
  },
  //验证码
  Verification: function (e) {
    this.setData({
      Verification: e.detail.value
    })
  },
  //登录
  nexts: function () {
    console.log(this.data.datas.isRegister)
    console.log(this.data.userName)
    var that=this
    if (that.data.datas.isRegister == 0) {
      // that.setData({
      //   btn: 'nexts'
      // })
      var loginStatus = getApp().loginStatus;
      if (!loginStatus) {
        wx.openSetting({
          //重新请求获取定位
          success: function (res) {
            // console.info(res.userInfo);
            wx.getUserInfo({
              lang: "zh_CN",
              success: function (res) {
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
                // console.log(gender + '......' + gender + '......' + avatarUrl + '......' + city + '......' + province + '......' + country)
                app.gender = gender
                app.nickName = nickName
                app.avatarUrl = avatarUrl
                app.city = city
                app.province = province
                app.country = country
                // console.log(getApp().gender + '......' + getApp().nickName)
                wx.navigateTo({
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
    } else if(that.data.datas.isRegister == 1) {
      
      var username = this.data.userName
      var Verification = this.data.Verification
      if (username == "") {
        wx.showModal({
          title: "请输入手机号"
        });
      } else if (Verification == '') {
        wx.showModal({
          title: "请输入验证码"
        });
      } else {
        wx.reLaunch({
          url: '../list/list',
        })
      }
    }
    
    
  },
})
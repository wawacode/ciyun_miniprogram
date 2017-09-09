//app.js
App({
  onLaunch: function () {
    var that = this;
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now());
    console.log("执行了");
    // wx.login({
    //   success: function (res) {
    //     console.log(res);

    //     if (res.code) {
    //       wx.request({
    //         url: 'https://minirpt.ciyun.cn/user/authorize/getThirdSession',
    //         method: 'POST',
    //         header: {
    //           'content-type': 'application/json',
              
    //         },
    //         data: {
    //           code: res.code
    //         }, success: function (res) {
    //         },
    //         fail: function (res) {
    //           console.log(res)
    //         },
    //         complete: function (res) {
    //           wx.hideLoading()
    //           console.log(res.data);
    //           wx.setStorageSync('thirdSession', res.data.datas.thirdSession);
    //           wx.setStorageSync('jSessionId', res.data.datas.jSessionId);
    //         }

    //       })
    //     } else {
    //       console.log('获取用户登录态失败！' + res.errMsg)
    //     }
    //   },
    //   fail:function(res){
    //     console.log(res);
    //   }
    // });
    wx.setStorageSync('logs', logs);
    //微信获取设备可用高度
    wx.getSystemInfo({
      success: function (res) {
        console.log(res);
        // 可使用窗口宽度、高度
        console.log('height=' + res.windowHeight);
        console.log('width=' + res.windowWidth);
        // 计算主体部分高度,单位为px
        that.globalData.deviceHeigth = res.windowHeight;
        that.globalData.deviceWidth = res.windowWidth;
      }
    });
  },
  getUserInfo: function (cb) {
    var that = this
    if (this.globalData.userInfo) {
      //typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      //调用登录接口
      wx.getUserInfo({
        withCredentials: false,
        success: function (res) {
          that.globalData.userInfo = res.userInfo
          //typeof cb == "function" && cb(that.globalData.userInfo)
        }
      })
    }
  },

  globalData: {
    userInfo: null,
    shareTitle: "慈云微报告",
    errorMsg:''

  },
  showToast:function(title){
    wx.showToast({
      title: title || '',
      duration: 1000
    });
  },
  //简写为空判断
  isEmpty: function (str) {
    return (str === '' || str === null) ? false : true;
  },
  //请求回调结果
  postCallBack: function (type, data, callback) {
    wx.showLoading({
      title: '加载中',
    });
    var host = 'https://minirpt.ciyun.cn' + '/user/' + type;
    console.log(wx.getStorageSync('jSessionId'));
    wx.request({
      url: host, //仅为示例，并非真实的接口地址
      data: data,
      method: 'POST',
      header: {
        'content-type': 'application/json',
        'Cookie': 'JSESSIONID=' + wx.getStorageSync('jSessionId')
      },
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) {
        console.log(res)
      },
      complete: function (res) {
        wx.hideLoading();
        callback(res.data);
      }

    })
  }
});


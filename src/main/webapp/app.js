//app.js
App({
  onLaunch: function () {
    var that = this;
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now());
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
        // 可使用窗口宽度、高度
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
    errorMsg:'',
  },
  //通用提示语
  showToast:function(title){
    wx.showToast({
      title: title || '',
      duration: 1000
    });
  },
  //简写为空判断
  isEmpty: function (str) {
    return (str === '' || str === null || str === undefined) ? false : true;
  },
  //请求回调结果
  postCallBack: function (type, data, callback) {
    var that = this;
    wx.showLoading({
      title: '加载中',
    });
    //网络异常捕获；
    wx.getNetworkType({
      success: function (res) {
        // 返回网络类型, 有效值：
        // wifi/2g/3g/4g/unknown(Android下不常见的网络类型)/none(无网络)
        var networkType = res.networkType
        console.log(networkType);
        if(networkType == 'none'){
          that.showToast(res.data.message);
        }else{
          var host = 'https://minirpt.ciyun.cn' + '/user/' + type;
          wx.request({
            url: host, //仅为示例，并非真实的接口地址
            data: data,
            method: 'POST',
            header: {
              'content-type': 'application/json',
              'Cookie': 'JSESSIONID=' + wx.getStorageSync('jSessionId')
            },
            success: function (res) {
              wx.hideLoading();
              if (res.data.result == 0) {
                callback(res);
              } else {
                that.showToast(res.data.message);
              }
            },
            fail: function (res) {
              console.log(res);
              wx.hideLoading();
              that.showToast(res.data.message);
            }

          })
        }
      },
      fail:function(res){
        console.log(res);
      }
    })


  }
});


//app.js
App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  getUserInfo: function (cb) {
    var that = this
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      //调用登录接口
      wx.getUserInfo({
        withCredentials: false,
        success: function (res) {
          that.globalData.userInfo = res.userInfo
          // typeof cb == "function" && cb(that.globalData.userInfo)
        }
      })
    }
  },
  

  globalData: {
    userInfo: null,
    shareTitle: "慈云微报告",

  },
  //简写为空判断
  isEmpty: function (str) {
    return (str === '' || str === null) ? false : true;
  },
  //请求回调结果
  postCallBack: function (type,data,callback) {
    wx.showLoading({
      title: '加载中',
    });
    var host = 'https://minirpt.ciyun.cn/user/'+type;
    switch (type) {
      case 'medrpt/list':
        break;
      case 'medrpt/detail':
        break;
      case 'authorize/login':
        break;
      case 'authorize/validsmscode':
        break;
      case 'authorize/getThirdSession':
        break;
      case 'authorize/valSignature':
        break;
      case 'medrpt/listMedCorp':
        break;
      case 'authorize/saveUserinfo':
        break;
      case 'medrpt/queryRptRules':
        break;
      case 'medrpt/importRpt':
        break;
    };
    wx.request({
      url: host, //仅为示例，并非真实的接口地址
      data: data,
      method:'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        // callback(res);
      },
      fail: function (res) {
      },
      complete: function (res) {
        wx.hideLoading();
        callback(res);
      }

    })
  },
  
  test: function (str) {
    console.log(str);
  }
});


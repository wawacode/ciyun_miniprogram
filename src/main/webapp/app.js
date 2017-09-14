//app.js
App({
  onLaunch: function () {
    var that = this;
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now());
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
    errorMsg: '',
  },
  //会话机制处理
  checkThirdSession: function () {
    var that = this;
    if (this.clear){
      console.log("已经存在定时器了");
      clearInterval(this.clear);
    };
    this.clear=setInterval(function(){
      wx.login({
        success: function (res) {
          var code = res.code
          that.postCallBack('authorize/getThirdSession', { code: code }, function(res){
            that.thirdSession = res.data.datas.thirdSession;
            wx.setStorageSync('thirdSession', res.data.datas.thirdSession)
            wx.setStorageSync('jSessionId', res.data.datas.jSessionId)
            console.log('登入成功');
          });
        },fail:function(res){
          
        }
      })
    },1000*60);
  },
  //通用提示语
  showToast: function (title) {
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
    this.checkThirdSession();
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
        if (networkType == 'none') {
          that.showToast(res.data.message);
        } else {
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
      fail: function (res) {
        console.log(res);
      }
    })


  }
});


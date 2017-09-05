//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    array: [{
      message: 'foo',
    }, {
      message: 'bar'
    }],
    motto: 'Hello World',
    userInfo: {},
    item: {
      index: 0,
      msg: 'this is a template',
      time: '2016-09-15'
    },
    currentTab: 0,
    hover_class: "",
    second_height: "",
    province: [
      {
        message: '北京',
      }, {
        message: '上海'
      }, {
        message: '天津',
      }, {
        message: '重庆'
      }, {
        message: '河北',
      }, {
        message: '河南'
      }, {
        message: '云南'
      }, {
        message: '辽宁',
      }, {
        message: '海南'
      }, {
        message: '黑龙江',
      }, {
        message: '湖北'
      }, {
        message: '吉林'
      }, {
        message: '广西',
      }, {
        message: '山东'
      }, {
        message: '山西',
      }, {
        message: '江苏'
      }, {
        message: '浙江'
      }, {
        message: '安徽',
      }, {
        message: '福建'
      }, {
        message: '江西',
      }, {
        message: '广东'
      }
    ],
    mechanism: [
      {
        property: [
          { name: '中国人民解放军第三0六医院' },
          { name: '航天中心医院' }
        ]
      },
      {
        property: [
          { name: '奥术大师大所' },
          { name: '奥术大师大所大所' }
        ]
      }
    ],
    data: [
      {
        "city": "北京市",
        "hidMedCorpList": [
          {
            "corpName": "中国人民解放军第三〇六医院",
            "medCorpId": "1383"
          },
          {
            "corpName": "航天中心医院",
            "medCorpId": "721"
          }
        ]
      },
      {
        "city": "上海市",
        "hidMedCorpList": [
          {
            "corpName": "上海市第一人民医院",
            "medCorpId": "13843"
          },
          {
            "corpName": "上海市第六人民医院",
            "medCorpId": "722"
          }
        ]
      }
    ]
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    console.log('onLoad')
    var that = this
    //微信获取设备可用高度
    wx.getSystemInfo({
      success: function (res) {
        console.log(res);
        // 可使用窗口宽度、高度
        console.log('height=' + res.windowHeight);
        console.log('width=' + res.windowWidth);
        // 计算主体部分高度,单位为px
        that.setData({
          // second部分高度 = 利用窗口可使用高度 - first部分高度（这里的高度单位为px，所有利用比例将300rpx转换为px）
          second_height: res.windowHeight + 'px'
        })
      }
    });
    // 可以通过 wx.getSetting 先查询一下用户是否授权了 "scope.record" 这个 scope
    wx.getSetting({
      success(res) {
        console.log(res);
        if (!res.authSetting['scope.userInfo']) {
          wx.authorize({
            scope: 'scope.userInfo',
            success() {
              // 用户已经同意小程序使用录音功能，后续调用 wx.startRecord 接口不会弹窗询问
            }
          })
        }
        wx.getUserInfo({
          success: function (res) {
            var userInfo = res.userInfo
            var nickName = userInfo.nickName
            var avatarUrl = userInfo.avatarUrl
            var gender = userInfo.gender //性别 0：未知、1：男、2：女 
            var province = userInfo.province
            var city = userInfo.city
            var country = userInfo.country
            console.log(res);
          }
        })
      }
    })
    //微信获取设备物理位置
    wx.getLocation({
      type: 'wgs84',
      success: function (res) {
        var latitude = res.latitude
        var longitude = res.longitude
        var speed = res.speed
        var accuracy = res.accuracy
        console.log(res);
      }
    });
    console.log(app);
    //页面请求接口；
    //app.postCallBack('medrpt/detail');
  },
  navbarTap: function (e) {

    console.log(this.data.currentTab, e.target.dataset.idx);
    this.setData({
      currentTab: e.target.dataset.idx
    })
  },
  //全部订单
  importPage: function (e) {
    var medcorpid = e.currentTarget.dataset.medcorpid;
    console.log(medcorpid);
    wx.navigateTo({
      url: '../report/report' + "?medcorpid=" + medcorpid
    })
  }
})

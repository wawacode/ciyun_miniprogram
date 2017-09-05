// pages/import/import.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //
    defaultSize: 'default',
    primarySize: 'default',
    warnSize: 'default',
    disabled: false,
    plain: false,
    loading: false,
    cardTypeIndex: 0,
    sex: ['男','女','未知'],
    sexIndex: 0,
    date: "请选择体检日期",
    importCode: [-1, 0, 1, 2],
    phone: 13832322217,
    datas: {
      "username": "张三",
      "sex": 1,
      "telephone": 13412312311,
      "medCorpId": "1383",
      "ruleState": 1,
      "ruleIds": "idCard|sex|mobile|medDate|medPersonNo|userName",
      "ruleCardType": "1|2|3|4|5|6|23|26"
    }
  },
  /**
   * 证件类型过滤器
   */
  ruleCardTypeFilter: function (str, type) {
    if (type == 'post') {

    } else {
      var ruleCardTypeArr = str.split("|").map(function (index) {
        switch (index) {
          case '1':
            return '身份证';
          case '2':
            return '回乡证';
          case '3':
            return '护照';
          case '4':
            return '军官证';
          case '5':
            return '医保卡号';
          case '6':
            return '警察证';
          case '23':
            return '员工号';
          case '26':
            return '唯一号';

        }
      });
      this.setData({
        "ruleCardType": ruleCardTypeArr
      })
      console.log(ruleCardTypeArr);
    }
  },
  callBack: function (res) {
   this.ruleCardTypeFilter(this.data.datas.ruleCardType);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.medcorpid);
    var that = this;
    var data = {
      thirdSession: options.thirdSession,
      medCorpId: options.medcorpid
    };
    app.postCallBack('medrpt/queryRptRules', data, that.callBack);

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
   * button控制
   */
  setPlain: function (e) {
    this.setData({
      plain: !this.data.plain
    })
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //事件处理函数
  queryimport: function () {
    // wx.navigateTo({
    //   url: '../detail/detail'
    // })
    wx.showToast({
      title: '失败',
      icon: 'error',
      duration: 1000
    });

    setTimeout(function () {
      wx.navigateTo({
        url: '../detail/detail'
      })
    }, 1500)
    // wx.showModal({
    //   title: '提示',
    //   content: '这是一个模态弹窗',
    //   success: function (res) {
    //     if (res.confirm) {
    //       console.log('用户点击确定')
    //     } else if (res.cancel) {
    //       console.log('用户点击取消')
    //     }
    //   }
    // })
  },
  // 证件类型
  bindCardTypeChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      cardTypeIndex: e.detail.value
    })
  },
  // 性别
  bindSexChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      sexIndex: e.detail.value
    })
  },
  // 体检日期切换
  bindDateChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date: e.detail.value
    })
  }

})
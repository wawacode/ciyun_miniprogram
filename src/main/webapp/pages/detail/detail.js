// pages/detail/detail.js
//获取应用实例
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['基本信息', '汇总分析','超标项', '报告详情'],
    navbarIndex:0,
    searchSwich:false,
    currentClassifyId:0, //当前分类列表ID
    "datas": {
      "adviceList": [
        {
          "advice": " 建议2周后复查便潜血（复查前三天建议素食），如仍阳性消化内科就诊，必要时行肠镜检查。（请关注）",
          "createTime": 1496906676000,
          "doctorBegin": "卢冬梅",
          "doctorLast": "高金颖",
          "summary": "便潜血阳性"
        }
      ],
      "exceptionItems": [
        {
          "isNormal": 4,
          "itemHight": 45,
          "itemLow": 7,
          "itemName": "γ谷氨酰转肽酶",
          "itemResult": "100.1",
          "itemResultType": 1,
          "itemUnit": "IU/L",
          "stdRange": ""
        }
      ],
      "extrasConts": "",
      "extrasFlag": 1,
      "medDetails": {
        "1696669": {
          "itemClassList": {
          },
          "itemList": [
            {
              "itemName": "T值",
              "itemOrder": 1,
              "itemRecType": 1,
              "itemResult": "-0.6",
              "itemResultType": 1,
              "organName": "骨密度",
              "stdItemOrder": 1,
              "stdItemResultType": 1,
              "stdItemValueNum": -0.6,
              "stdItemValueStr": "-0.6",
              "stdRange": "",
              "itemLow": 10.3,
              "itemHight": 20.6,
              "isNormal": 4
            }
          ],
          "mediaArray": "",
          "mediaList": [

          ],
          "organName": "骨密度",
          "rptMode": 3,
          "showSummary": 1,
          "summaryList": [
            {
              "doctor": "谷洁",
              "organName": "骨密度",
              "revDoctor": "",
              "summary": "未见明显异常"
            }
          ]
        }
      },
      "medExamRpt": {
        "medCorpName": "航天中心医院",
        "medExamType": "体检报告",
        "medPersonId": "0000972602",
        "medYear": 2017,
        "name": "张三",
        "telephone": "13145678901"
      },
      "showSummary": 1
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setReportListHeight();
    app.postCallBack('medrpt/detail/269590', { thirdSession: wx.getStorageSync('thirdSession')}, function (data) {
      console.log(data);
    });
  },
  // 设置项目列表高度
  setReportListHeight:function(){
    this.setData({
      reportListHeight: app.globalData.deviceHeigth + "px"
    })
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
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      // console.log(this.prototype.route)
    }
    console.log(getCurrentPages()[0].route)
    return {
      title: '慈云微报告',
      path: getCurrentPages()[0].route,
      success: function (res) {
        console.log('转发成功',res)
      },
      fail: function (res) {
        console.log('转发失败',res)
      }
    }
  },
  /**
   * 用户点击tab切换
   */
  navbarTap: function (e) {
    this.setData({
      navbarIndex: e.currentTarget.dataset.idx
    })
    console.log(e.currentTarget);
  },
  //打开报告分类列表
  openSearchList:function(e){
    console.log(this.data);
    this.setData({
      searchSwich: !this.data.searchSwich
    })
  },
  // 图片展示
  previewImage:function(e){
    console.log(e.target.dataset.url);
    wx.previewImage({
      urls: [e.target.dataset.url, "https://fdstatic.ciyun.cn//group1/M00/64/F8/CgHMCFlxsU-AH5f2AArEuEP0T8Q689.png"]
    })
  }
})
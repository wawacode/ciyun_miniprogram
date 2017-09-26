// pages/detail/detail.js
//获取应用实例
var app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['基本信息', '汇总分析','超标项', '报告详情'],
    navbarIndex:0,//tab默认展示0
    searchSwich:false,//项目 科室列表默认显示状态
    currentClassifyId:0, //当前分类列表ID
    detailStyle:"padding-top:152rpx;",//tab下样式问题处理
    detail_th:"top:100rpx;"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var json={
      id: app.listId,
      thirdSession: app.thirdSession
    }
    this.setReportListHeight();//设置报告列表高度;
    app.postCallBack('medrpt/detail/' + app.listId, json, function (res) {
      var datas = res.data.datas;
        that.setData({
          data:res.data.datas
        });
    });
  },
  // 设置项目列表高度
  setReportListHeight:function(){
    this.setData({
      reportListHeight: app.globalData.deviceHeigth + "px"
    })
  },

  // /**
  //  * 用户点击右上角分享
  //  */
  // onShareAppMessage: function (res) {
  //   if (res.from === 'button') {
  //     // 来自页面内转发按钮
  //     // console.log(this.prototype.route)
  //   }
  //   return {
  //     title: '慈云微报告',
  //     path: getCurrentPages()[0].route,
  //     success: function (res) {
  //       console.log('转发成功',res)
  //     },
  //     fail: function (res) {
  //       console.log('转发失败',res)
  //     }
  //   }
  // },
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
    this.setData({
      searchSwich: !this.data.searchSwich,
      currentClassifyId: e.currentTarget.dataset.name || 0
    })
  },
  // 图片展示
  previewImage:function(e){
    var that = this;
    var idx = e.target.dataset.idx;
    var lev = e.target.dataset.lev;
    var curmedDetails = that.data.data.medDetails[idx];
    var urls = [];
    if (lev){
      urls=that.data.data.medDetails[idx].itemClassList[lev].mediaList;
    }else{
      urls=that.data.data.medDetails[idx].mediaList;
    }
    wx.previewImage({
      urls: urls
    })
  }
})
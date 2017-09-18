// pages/import/import.js
var app = getApp();
Page({

  /**
   * 页面的app-form-item clearfix
   */
  data: {
    //
    cardTypeIndex: 0,
    sex: ['男','女','未知'],
    sexIndex: 0,
    date: "请选择体检日期",
    importCode: [-1, 0, 1, 2],
    idCardValue:'',
    medPersonNo:'',
    idCard_boo:false,
    sex_boo:false,
    mobile_boo:false,
    medDate_boo:false,
    medPersonNo_boo:false,
    userName_boo:false,
    datas: {
      "username": "张三",
      "sex": 1,
      "telephone": 13412312311,
      "medCorpId": "1383",
      "ruleIds": "idCard|sex|mobile|medDate|medPersonNo|userName",
      "ruleCardType": "1|2|3|4|5|6|23|26"
    }
  },
  /**
   * 证件类型过滤器
   */
  ruleCardTypeFilter: function (obj, type) {
    if (type == 'post') {

    } else {
      var ruleCardTypeArr= [];
      for(var key in obj){
        ruleCardTypeArr.push(obj[key]);
      };
      this.setData({
        "ruleCardType": ruleCardTypeArr
      })
    }
  },
  // 赛选 条件渲染
  filterTypeRendering:function(){
    var ruleArr = this.data.datas.ruleIds.split("|");
    var sexIndex = this.data.datas.sex-1;
    this.setData({
      idCard_boo: ruleArr.includes('idCard'),
      sex_boo:ruleArr.includes('sex'),
      mobile_boo:ruleArr.includes('mobile'),
      medDate_boo:ruleArr.includes('medDate'),
      medPersonNo_boo:ruleArr.includes('medPersonNo'),
      userName_boo:ruleArr.includes('userName'),
      sexIndex: sexIndex
    })
  },
  changeIdCardValue:function(e){
      this.setData({
        idCardValue:e.detail.value
      })
  },
  changeMedPersonNoValue:function(e){
    this.setData({
      medPersonNo: e.detail.value
    })
  },
  changeuserNameValue: function (e) {
    
    this.setData({
      userName: e.detail.value
    })
  },
  //表单校验
  queryimport: function () {

    if (this.data.idCard_boo){
      if (!app.isEmpty(this.data.idCardValue)){
        console.log(this.data.idCardValue);
        app.showToast('证件号码不能为空',0);
        return false;
      } else if (this.data.cardTypeIndex == 0){
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!reg.test(this.data.idCardValue)){
          app.showToast('身份证号码格式有误', 0);
          console.log('身份证号码格式有误');
          return false;
        }
      };
    }
    if (this.data.medDate_boo){
      if (this.data.date == '请选择体检日期') {
        app.showToast('体检日期不能为空',0);
        return false;
      };
    }
    if (this.data.medPersonNo_boo){
      if (!this.data.medPersonNo) {
        app.showToast('档案ID不能为空',0);
        return false;
      };
    }
    if (this.data.userName_boo){
      if (!app.isEmpty(this.data.userName)) {
        app.showToast('体检人姓名不能为空',0);
        return false;
      };
    }
    var data={
      thirdSession: wx.getStorageSync('thirdSession'),
      idCardType:1,
      idCard: this.data.idCardValue,
      sex: (Number(this.data.sexIndex) + 1),
      mobile: this.data.datas.telephone,
      medDate: this.data.date,
      medPersonNo: this.data.medPersonNo,
      medCorpId: this.data.datas.medCorpId,
      userName: this.data.userName
    }
    app.postCallBack('medrpt/importRpt', data, function(res){
      if (res.data.datas.rptSize == 0){
        app.showToast("无新报告");
      }
      setTimeout(function(){
        wx.reLaunch({
          url: '../list/list'
        })
      },1000)

    });
  },
  //接口回调初始化渲染模板
  callBack: function (res) {
   if(res.data.result == 0){
     this.setData({
        datas:res.data.datas
     });
     this.ruleCardTypeFilter(this.data.datas.ruleCardType);
     this.filterTypeRendering();
   }else{
     app.showToast(res.data.message,1)
   }

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var data = {
      thirdSession: options.thirdSession || wx.getStorageSync('thirdSession'),
      medCorpId: options.medcorpid || wx.getStorageSync('medcorpid')
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
// pages/import/import.js
var app = getApp();
Page({

  /**
   * 页面的app-form-item clearfix
   */
  data: {
    //
    cardTypeIndex: 0,
    sex:[{"1":'男'},{"2":'女'},{'3':'未知'}],
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
    isShowMIniToast: false,
    toastMiniText:""
  },
  /**
   * 证件类型过滤器
   */
  ruleCardTypeFilter: function (obj, type) {
    var ruleCardTypeArr = [];
    var ruleCardCaptions = [];
    for (var key in obj) {
      ruleCardCaptions.push(obj[key])
      ruleCardTypeArr.push(key);
    };
    if (type) {
      for (var i in this.data.datas.curSex){
        for (var k = 0; k < ruleCardTypeArr.length -1 ; k++){
          if (i == ruleCardTypeArr[k]){
            var sexIndex = k;
            break;
          }
        }
      }
      this.setData({
        "sexCardType": ruleCardTypeArr,
        'sexCardCaptions': ruleCardCaptions,
        'sexIndex': sexIndex
      });
    } else {
      this.setData({
        "ruleCardType": ruleCardTypeArr,
        'ruleCardCaptions': ruleCardCaptions
      })
    }
  },
  // 赛选 条件渲染
  filterTypeRendering:function(){
    var ruleArr = this.data.datas.ruleIds.split("|");
    this.setData({
      idCard_boo: ruleArr.includes('idCard'),
      sex_boo:ruleArr.includes('sex'),
      mobile_boo:ruleArr.includes('mobile'),
      medDate_boo:ruleArr.includes('medDate'),
      medPersonNo_boo:ruleArr.includes('medPersonNo'),
      userName_boo:ruleArr.includes('userName')
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
      } else if (this.data.ruleCardType[this.data.cardTypeIndex] == '1'){
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
      idCardType: this.data.ruleCardType[this.data.cardTypeIndex],
      idCard: this.data.idCardValue,
      sex: this.data.sexCardType[this.data.sexIndex],
      mobile: this.data.datas.telephone,
      medDate: this.data.date,
      medPersonNo: this.data.medPersonNo,
      medCorpId: this.data.datas.medCorpId,
      userName: this.data.userName
    }
    app.postCallBack('medrpt/importRpt', data, this.callBackTList);
  },
  showMiniToast: function (fn) {
    var _this = this;
    // toast时间 
    _this.data.count = parseInt(_this.data.count) ? parseInt(_this.data.count) : 3000;
    // 显示toast 
    _this.setData({
      isShowMiniToast: true,
    });
    // 定时器关闭 
    setTimeout(function () {
      _this.setData({
        isShowMiniToast: false
      });
      fn();
    }, _this.data.count);
  }, 
  //接口回调初始化渲染模板
  callBackTList: function (res) {
    if (res.data.datas.rptSize == 0) {
      wx.hideLoading();
      this.setData({
        count: 2000,
        toastMiniText: '未查到您的报告!' 
      });
      this.showMiniToast(function(){
        wx.reLaunch({
          url: '../list/list'
        })
      }); 
    }else{
      wx.reLaunch({
          url: '../list/list'
      })
    }
  },
  //接口回调初始化渲染模板
  callBack: function (res) {
   if(res.data.result == 0){
     this.setData({
        datas:res.data.datas
     });
     this.ruleCardTypeFilter(this.data.datas.ruleCardType,0);
     this.ruleCardTypeFilter(this.data.datas.sex,1);
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
   * button控制
   */
  setPlain: function (e) {
    this.setData({
      plain: !this.data.plain
    })
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
  },
  

})
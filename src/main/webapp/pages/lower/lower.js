// pages/register/register.js
var app = getApp()
Page({
  /**
 * 页面的初始数据
 */
  data: {
    avatarUrl: "",
    username: "",
    sex: [
      { bindtap: 'sex1', txt: '男' },
      { bindtap: 'sex2', txt: '女' }
    ],
    sexHidden: true,
    gender: "",
    Age: "",
    array: [10, 20, 30, 40, 50, 60],
    array2: [160, 161, 165, 170, 175, 180],
    index: "35",
    index2: "175"
  },
  //身高
  bindPickerChange2: function (e) {
    this.setData({
      index2: this.data.array2[e.detail.value]
    })
  },
  sex: function () {
    this.setData({
      sexHidden: !this.data.sexHidden
    })
  },
  bindsex1: function () {
    this.setData({
      gender: this.data.sex[0].txt,
      sexHidden: !this.data.sexHidden
    })
  },
  bindsex2: function () {
    this.setData({
      gender: this.data.sex[1].txt,
      sexHidden: !this.data.sexHidden
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var age=[];
    var height=[]
    for(var i=0;i<=120;i++){
      age.push(i)
    }
    for(var i=50;i<=250;i++){
      height.push(i)
    }
    this.setData({
      avatarUrl: getApp().avatarUrl,
      username: getApp().nickName,
      array: age,
      array2: height
    })
    if (getApp().gender == 1) {
      this.setData({
        gender: '男'
      })
    } else if (getApp().gender == 2) {
      this.setData({
        gender: '女'
      })
    }
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
  onShareAppMessage: function () {

  },
  radioChange: function (e) {
    if (e.detail.value == '1') {
      this.setData({
        gender: '男',
      })
    } else {
      this.setData({
        gender: '女',
      })
    }
  },
  name: function (e) {
    this.setData({
      username: e.detail.value,
    })

  },
  Age: function (e) {
    this.setData({
      Age: e.detail.value,
    })
  },
  height: function () {
    this.setData({
      height: e.detail.value,
    })
  },
  register: function () {
    var avatarUrl = this.data.avatarUrl;
    var username = this.data.username;
    var gender = this.data.gender;
    var index=this.data.index;
    var index2=this.data.index2;
    var that=this
    var genderNumber
    if (gender=="男"){
      genderNumber=1
    } else if (gender == "女"){
      genderNumber = 2
    }else{
      genderNumber = 3
    }
    var json = {
      nickName: username,
      gender: genderNumber,
      age:index,
      height: index2, 
      thirdSession: getApp().thirdSession
    }
    app.postCallBack('authorize/updateUserinfo', json, that.callback);
  },
  callback:function(res){
    if (res.data.result==0){
      wx.reLaunch({
        url: '../list/list'
      })
    }else{
      wx.showModal({
        title: res.data.message
      })
      
    }
    
  },
  bindPickerChange: function (e) {
    this.setData({
      index: this.data.array[e.detail.value]
    })
  },
})





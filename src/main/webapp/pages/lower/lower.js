// pages/register/register.js
var app = getApp()
Page({
  /**
 * 页面的初始数据
 */
  data: {
    avatarUrl: "",
    username: "",
    male: 'true',
    girl: "",
    gender: "",
    Age: "",
    height: ""

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    // console.log(getApp().avatarUrl)
    this.setData({
      avatarUrl: getApp().avatarUrl,
      username: getApp().nickName
    })
    if (getApp().gender == 1) {
      this.setData({
        male: 'true',
        girl: 'false',
        gender: '男'
      })
    } else if (getApp().gender == 2) {
      this.setData({
        male: 'false',
        girl: 'true',
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
    console.log('携带value值为：', e.detail.value)
    if (e.detail.value == '1') {
      this.setData({
        gender: '男',
      })
    } else {
      this.setData({
        gender: '女',
      })
    }
    console.log(this.data.gender)
  },
  name: function (e) {
    console.log(e.detail)
    this.setData({
      username: e.detail.value,
    })

  },
  Age: function (e) {
    console.log(e.detail)
    this.setData({
      Age: e.detail.value,
    })
  },
  height: function () {
    console.log(e.detail)
    this.setData({
      height: e.detail.value,
    })
  },
  register: function () {
    wx.reLaunch({
      url: '../list/list'
    })
  }

})





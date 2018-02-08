// pages/login/login.js
const app = getApp()
var uid;
// uid = getApp().globalData.uid;
// console.log(getApp().globalData.uid)
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // casArray: ['北京', '上海', '南京', '无锡'],
    // casIndex1:0,
    inputname:'',
    inputinvite:"",
    inputcity:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
    // console.log(uid)

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
  nameInput:function(e){
    this.setData({
      inputname:e.detail.value
    })
  },
  inviteInput:function(e){
    this.setData({
      inputinvite:e.detail.value
    })
  },
  cityInput:function(e){
    this.setData({
      inputcity:e.detail.value
    })
  },
  taphandle: function (e) {  
    var self = this;
    var uidplus = wx.getStorageSync('uid')
    console.log(wx.getStorageSync('uid'))
    console.log(self.data.inputname)
    console.log(self.data.inputinvite)
    console.log(self.data.inputcity)
    wx.request({
      url: 'http://192.168.3.27:8080/user/sign_up',
      data: {
        uid: uidplus,
        username: self.data.inputname,
        invitationCode: self.data.inputinvite,
        area: self.data.inputcity
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'POST',
      success: function (res) {
        console.log(res)
        wx.setStorageSync('uid', res.data.uid)
        wx.redirectTo({
          url: '../homepage/homepage',
        })
      },
      fail:function(err){
        console.log(err)
      }
    })
  }
})
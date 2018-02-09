// pages/login/login.js
const app = getApp()
var uid;
var city_number;
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
    if (self.data.inputcity=='北京'){
      city_number=0
    } else if(self.data.inputcity == '上海'){
      city_number = 1
    } else if(self.data.inputcity == '无锡'){
      city_number = 2
    } else if(self.data.inputcity == '南京'){
      city_number = 3
    }
    wx.request({
      url: 'http://192.168.3.27:8080/user/sign_up',
      data: {
        uid: uidplus,
        username: self.data.inputname,
        invitationCode: self.data.inputinvite,
        area: city_number
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      method: 'POST',
      success: function (res) {
        console.log(res)
        // wx.setStorageSync('uid', res.data.uid)
        if (wx.getStorageSync('uid') == res.data.uid){
          wx.setStorageSync('key', res.data.uid)
          wx.redirectTo({
            url: '../homepage/homepage',
          })
        }else{
          wx.showModal({
            title: '错误',
            content: res.data,
          })
        }
        
      },
      fail:function(err){
        console.log(err)
      }
    })
  }
})
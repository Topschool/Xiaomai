//index.js
//获取应用实例
const app = getApp()
const ctx = wx.createCanvasContext('guagua_card')
Page({
  data: {
   word:'',
  },
  onLoad: function () {
    var self = this;
    wx.request({
      url: 'http://192.168.3.5:8080/test/get-api',
      // data: {
      //   data: ,
      // },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res) {
        console.log(res)
        self.setData({
          word:res.data
        })
      },
      fail:function(err){
        console.log(err)
      }
    })
  },

  
})

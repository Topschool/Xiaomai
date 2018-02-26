//app.js
var m = 0
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // wx.getUserInfo({
    //   success: function (res) {
    //     console.log(res)
    //   }
    // })
    // 登录
    wx.login({
      success: res => {
        var self = this;
        // console.log(res.code)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        var name = wx.getStorageSync('uid')
        console.log(name+'asd') 
        if (name == '' || name == null || wx.getStorageSync('uid') !=wx.getStorageSync('key')){
          console.log('qwerty')
          wx.request({
            url: 'http://192.168.3.27:8080/user/get_uid',
            data: {
              "code": res.code
            },
            header: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'POST',
            success: function (res_1) {
              console.log(res_1.data)
              var snc=wx.setStorageSync('uid', res_1.data)
              wx.redirectTo({
                url: '../login/login',
              })
            },
            fail:function(err){
              console.log(err)
            }
          })
        }else{

          wx.redirectTo({
            url: '../homepage/homepage',
          })
        }
        
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
  }
})
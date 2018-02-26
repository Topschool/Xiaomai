// pages/scratch_card/scratch_card.js
const app = getApp()
var uid = wx.getStorageSync('uid')
console.log(uid)
const ctx = wx.createCanvasContext('guagua_card') //加载画布
var startX = 0; //保存X坐标轴变量
var startY = 0;//保存X坐标轴变量
var timer;
var num = -1;
Page({
  data: {
    word: '',
    price: '',
    hidden: 'true',
    image_src_0: "http://ts-dingup-onlinetest.oss-cn-beijing.aliyuncs.com/img/shop/8-1.png",
    image_src_1: "http://ts-dingup-onlinetest.oss-cn-beijing.aliyuncs.com/img/shop/6-1.png",
    image_src_2: "http://ts-dingup-onlinetest.oss-cn-beijing.aliyuncs.com/img/shop/0-1.png",
    image_src_3: "http://ts-dingup-onlinetest.oss-cn-beijing.aliyuncs.com/img/shop/0-1.png",
    block_image_src: '../../images/001.ipg',
    name_0: '',
    name_4: '',
    name_1: '',
    name_3: '',
    toplist: '',
    timelist: true,
  },
  onLoad: function (options) {

  },
  toGet_Card() {                    //请求数据
    var self = this;
    console.log(uid + '-----------')
    wx.request({
      url: 'http://192.168.3.27:8080/wechat_applet_api/scratch-card',
      data: {
        uid: wx.getStorageSync('uid'),
        isScratch: num      //1挂卡请求
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      method: 'GET',
      success: function (res) {
        console.log(1)
        if (res.data.partnerStatus == "0") {
          self.draw();
          self.setData({
            price: res
          })
          if (res.data.topList != []) {
            if (res.data.topList.length == 2) {
              self.setData({
                name_0: res.data.topList[0].nickname,
                name_1: res.data.topList[1].nickname,
                image_src_0: res.data.topList[0].imgUrl,
                image_src_1: res.data.topList[1].imgUrl
              })
            } else if (res.data.topList.length == 1) {
              if (res.data.topList[0].money == 8) {
                self.setData({
                  name_0: res.data.topList[0].nickname,
                  image_src_0: res.data.topList[0].imgUrl
                })
              } else if (res.data.topList[0].money == 6) {
                self.setData({
                  name_1: res.data.topList[0].nickname,
                  image_src_1: res.data.topList[0].imgUrl
                })
              }
            }
          }
          if (res.data.lastList != []) {
            if (res.data.lastList.length == 2) {
              self.setData({
                name_4: res.data.lastList[0].nickname,
                name_3: res.data.lastList[1].nickname,
                image_src_2: res.data.lastList[0].imgUrl,
                image_src_3: res.data.lastList[1].imgUrl
              })
            } else if (res.data.lastList.length == 1) {
              self.setData({
                name_4: res.data.lastList[0].nickname,
                image_src_2: res.data.lastList[0].imgUrl

              })
            }
          }
        };
        if (res.data.partnerStatus == "1") {
          self.setData({
            price: res
          })
          if (res.data.topList != []) {
            if (res.data.topList.length == 2) {
              self.setData({
                name_0: res.data.topList[0].nickname,
                name_1: res.data.topList[1].nickname,
                image_src_0: res.data.topList[0].imgUrl,
                image_src_1: res.data.topList[1].imgUrl
              })

            } else if (res.data.topList.length == 1) {
              if (res.data.topList[0].money == 8) {
                self.setData({
                  name_0: res.data.topList[0].nickname,
                  image_src_0: res.data.topList[0].imgUrl
                })

              } else if (res.data.topList[0].money == 6) {
                self.setData({
                  name_1: res.data.topList[0].nickname,
                  image_src_1: res.data.topList[0].imgUrl

                })
              }
            }
          }
          if (res.data.lastList != []) {
            if (res.data.lastList.length == 2) {
              self.setData({
                name_4: res.data.lastList[0].nickname,
                name_3: res.data.lastList[1].nickname,
                image_src_2: res.data.lastList[0].imgUrl,
                image_src_3: res.data.lastList[1].imgUrl
              })
            } else if (res.data.lastList.length == 1) {
              self.setData({
                name_4: res.data.lastList[0].nickname,
                image_src_2: res.data.lastList[0].imgUrl

              })
            }
          }
        }
        if (res.data.partnerStatus == "-1") {
          self.setData({
            price: res
          })
          self.draw();
          if (res.data.topList != []) {
            if (res.data.topList.length == 2) {
              self.setData({
                name_0: res.data.topList[0].nickname,
                name_1: res.data.topList[1].nickname,
                image_src_0: res.data.topList[0].imgUrl,
                image_src_1: res.data.topList[1].imgUrl
              })

            } else if (res.data.topList.length == 1) {
              if (res.data.topList[0].money == 8) {
                self.setData({
                  name_0: res.data.topList[0].nickname,
                  image_src_0: res.data.topList[0].imgUrl
                })

              } else if (res.data.topList[0].money == 6) {
                self.setData({
                  name_1: res.data.topList[0].nickname,
                  image_src_1: res.data.topList[0].imgUrl

                })
              }
            }
          }
          if (res.data.lastList != []) {
            if (res.data.lastList.length == 2) {
              self.setData({
                name_4: res.data.lastList[0].nickname,
                name_3: res.data.lastList[1].nickname,
                image_src_2: res.data.lastList[0].imgUrl,
                image_src_3: res.data.lastList[1].imgUrl
              })
            } else if (res.data.lastList.length == 1) {
              self.setData({
                name_4: res.data.lastList[0].nickname,
                image_src_2: res.data.lastList[0].imgUrl

              })
            }
          }
        };
        console.log(res.data)

      },
      fail: function (err) {
        console.log(err)
      }
    })
  },

  touchStart: function (start) {
    var self = this;
    num = 1;
    timer = setTimeout(function () {
      self.toGet_Card()
      ctx.draw();      
      console.log("----Countdown----");
      self.setData({
        hidden: ''
      })
      self.toGet_Card()
      num = -1;
    }, 3000);
     
  },
  touchMove: function (start) {
    var start_X = start.changedTouches[0].x
    var start_Y = start.changedTouches[0].y
    ctx.save();
    ctx.moveTo(this.startX, this.startY)
    ctx.clearRect(start_X, start_Y, 20, 20)
    ctx.restore()
    this.startX = start_X;
    this.startY = start_Y;
    wx.drawCanvas({
      canvasId: 'guagua_card',
      reserve: true,
      actions: ctx.getActions() // 获取绘图动作数组
    })
  },
  // touchEnd: function (res) {
  //   var self = this;
  //   var ui = res.target;
  //   console.log(ui)
  // },
  taphandle1: function () {
    var self = this;
    self.setData({
      toplist: 'true',
      timelist: '',
    })
  },
  taphandle2: function () {
    var self = this;
    self.setData({
      timelist: 'true',
      toplist: '',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  draw() {
    ctx.setFillStyle('#919FAE'),
      ctx.fillRect(0, 0, 500, 180),
      ctx.draw()
    console.log(111)
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var self = this;
    self.toGet_Card();
    console.log(uid)
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

  }
})
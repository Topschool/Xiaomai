// pages/scratch_card/scratch_card.js
const app = getApp()
var uid = wx.getStorageSync('uid')
console.log(uid)
const ctx = wx.createCanvasContext('guagua_card') //加载画布
var startX = 0; //保存X坐标轴变量
var startY = 0;//保存X坐标轴变量
var timer;
var num=-1;
Page({
  data: {
    word: '',
    price: '',
    hidden: 'true',
    image_src_0: "../../images/007.png",
    image_src_1: "../../images/007.png",
    image_src_2: "../../images/007.png",
    image_src_3: "../../images/007.png",
    block_image_src: '../../images/001.ipg',
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
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res) {
        console.log(1)
        if (res.data.partnerStatus == "0") {
          self.draw();
            self.setData({
              price: res,
              // image_src_0: res.data.topList[0].url,
              // image_src_1: res.data.topList[1].url,
              // image_src_2: res.data.lastList[0].url,
              // image_src_3: res.data.lastList[1].url,
              block_image_src: "../../images/001.jpg",

            })
        };
        if (res.data.partnerStatus == "1") {
          self.setData({
            price: res,
            // image_src_0: res.data.topList[0].url,
            // image_src_1: res.data.topList[1].url,
            // image_src_2: res.data.lastList[0].url,
            // image_src_3: res.data.lastList[1].url,
            block_image_src: "../../images/001.jpg",
            // hidden: 'true'
          })
        }
        if (res.data.partnerStatus == "-1") {
          self.draw();
          self.setData({
            price: res,
            // image_src_0: res.data.topList[0].url,
            // image_src_1: res.data.topList[1].url,
            // image_src_2: res.data.lastList[0].url,
            // image_src_3: res.data.lastList[1].url,
            block_image_src: "../../images/001.jpg",
            // hidden: 'true'
          })
        };
        console.log(res.data)

      },
      fail: function (err) {
        // console.log(err)
      }
    })
  },
  
  touchStart: function (start) {
    var self = this;
    num = 1;
    timer = setTimeout(function () {
      self.toGet_Card();      
      console.log("----Countdown----");
      ctx.draw();
      self.setData({
        hidden: ''
      }),
      num=-1;
        self.toGet_Card()
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
  touchEnd: function (res) {
    var self = this;
    var ui = res.target;
    console.log(ui)
  },
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
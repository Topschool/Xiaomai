var num = 0;
var number = new Array();
var num_tap = -1;
var str = new Array();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    today: '',
    food: '',
    dinner_status: '',
    item_1: "circle",
    money: 0,
    orderFood_1: [],
    orderFood_2: '',
    shuliang: '',
    finished: '',
    check: "",
    length: ''
  },
  toGet() {
    var self = this;
    wx.request({
      url: 'http://localhost:8080/order_food/user_status',
      data: {
        uid: '25',      //1挂卡请求
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
        if (res.data.orderFoodStatus == false) {
          self.setData({
            dinner_status: 'order_begin',
          })
        } else {
          self.setData({
            dinner_status: 'order_finished',
          })
        }
      },
      fail: function (err) {

      }
    })
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
    var self = this;
    self.checkDinnerStatus()
    // self.taphandle()
    self.toGet()
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
  checkDinnerStatus: function () {
    var self = this;
    wx.request({
      url: 'http://localhost:8080/order_food/user_status',
      data: {
        uid: '25',
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data.scratchCardStatus)
        if (res.data.scratchCardStatus == true) {
          wx.request({
            url: 'http://localhost:8080/order_food/food_list',
            data: {
              uid: '25',
            },
            header: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'GET',
            success: function (res_1) {
              console.log(res_1.data);
              console.log(res_1.data.systemStatus);
              if (res_1.data.systemStatus == true) {
                console.log(res_1.data)
                // self.bindtaphandle();
                self.setData({
                  food: res_1,
                  dinner_status: 'order_begin',
                })
                console.log(res_1)
              } else if (res_1.data.systemStatus == false) {
                self.setData({
                  // food: res_1,
                  dinner_status: 'order_waiting'
                })
              }

            },
            fail: function (err) {
            }
          })
        } else if (res.data.scratchCardStatus == 'false') {
          wx.request({
            url: 'http://localhost:8080/order_food/food_list',
            data: {
              uid: '25',
            },
            header: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'GET',
            success: function (res_2) {
              // console.log(res_2),
              self.setData({
                // food: res_2,
                dinner_status: 'needscratchCard'
              })
            },
            fail: function (err) {
            }
          })
        }
      },
      fail: function (err) {
      }
    })

  },
  bindtaphandle: function (e) {
    var self = this;
    var num_length;
    num_tap++;
    console.log(e.detail.value);
    console.log(e.currentTarget.dataset.index)
    if (e.detail.value = true) {
      number[num_tap] = e.currentTarget.dataset.index
    } else {
      num_length=number.length-1;
    }
    
  },
  taphandle: function () {
    var self = this;
    console.log(number)
    wx.request({
      url: 'http://locahost:8080/order_food/foods_booking',
      data: {
        uid: '25',
        foods: number
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'POST',
      success: function (res_2) {
        // console.log(111)
      },
      fail: function (err) {
        console.log(111)
      }
    })
    // wx.request({
    //   url: 'http://localhost:8080/order_food/user_order',
    //   data: {
    //     uid: '25',
    //   },
    //   header: {
    //     'Content-Type': 'application/x-www-form-urlencoded'
    //   },
    //   method: 'GET',
    //   success: function (res_2) {
    //     self.setData({
    //       orderFood_2:res_2,
    //       dinner_status: 'order_finished',
    //     })
    //   },
    //   fail: function (err) {
    //   }
    // })
  },
  tapCancleHandle: function () {
    var self = this;
    number = [];
    console.log(number)
    wx.request({
      url: 'http://localhost:8080/order_food/cancel',
      data: {
        uid: '25',
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res) {
        self.setData({
          dinner_status: 'order_begin',
        })
      },
      fail: function (err) {
      }
    })
  }
})
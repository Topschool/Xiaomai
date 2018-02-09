var num = 0;
const app = getApp();
// var userNummber ='abcdefg';
var userNummber=wx.getStorageSync('uid');
console.log(userNummber)
var number = new Array();
var num_tap = 0;
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
      url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/user_status',
      data: {
        uid: wx.getStorageSync('uid'),      //1挂卡请求
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
    // self.toGet()
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
      url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/user_status',
      data: {
        uid: wx.getStorageSync('uid'),
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data.scratchCardStatus)
        if (res.data.scratchCardStatus == true) {
          wx.request({
            url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/food_list',
            data: {
              uid: wx.getStorageSync('uid'),
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
                if (res.data.orderFoodStatus == false) {
                  console.log(123)
                  self.setData({
                    food: res_1,
                    dinner_status: 'order_begin',
                  })
                } else {
                  wx.request({
                    url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/user_order',
                    data: {
                      uid: wx.getStorageSync('uid'),
                    },
                    header: {
                      'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    method: 'GET',
                    success: function (res_3) {
                      console.log(res_3.data)
                      self.setData({
                        orderFood_2: res_3,
                        dinner_status: 'order_finished',
                      })
                    },
                    fail: function (err) {
                    }
                  })
                }
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
        } else if (res.data.scratchCardStatus == null) {
          wx.request({
            url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/food_list',
            data: {
              uid: wx.getStorageSync('uid'),
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
    var self = this;console.log
    wx.request({
      url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/food_list',
      data: {
        uid: wx.getStorageSync('uid'),
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: function (res_2) {
        if (e.detail.value == true) {
          console.log(e.currentTarget.dataset.index)
          number[num_tap] = res_2.data.foods[e.currentTarget.dataset.index].id;
          num_tap++;
        } else {
          for (var i = 0; i < number.length; i++) {
            if (number[i] == (res_2.data.foods[e.currentTarget.dataset.index].id)) {
              number.splice(i, 1);
            }
          }
          num_tap--;
        }
      }
    })
    
  },
  taphandle: function () {
    var self = this;
    var number_new=[];
    for (var i = 0; i < number.length; i++) {
      if (typeof (number[i]) != 'undefined') {
        number_new.push(number[i]);
      }
    }
    console.log(number)
    wx.request({
      url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/foods_booking',
      data: {
        uid: wx.getStorageSync('uid'),
        foods: number_new
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'POST',
      success: function (res_2) {
        console.log(111)
        wx.request({
          url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/user_order',
          data: {
            uid: wx.getStorageSync('uid'),
          },
          header: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          method: 'GET',
          success: function (res_3) {
            console.log(res_3.data)
            self.setData({
              orderFood_2: res_3,

            })
          },
          fail: function (err) {
          }
        })
      },
      fail: function (err) {
      }
    })
    self.setData({
      dinner_status: 'order_finished'
    })
  },
  tapCancleHandle: function () {
    var self = this;
    number.splice(0, number.length);
    console.log(number)
    wx.request({
      url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/cancel',
      data: {
        uid: wx.getStorageSync('uid'),
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'POST',
      success: function (res) {
        wx.request({
          url: 'http://192.168.3.27:8080/wechat_applet_api/order_food/food_list',
          data: {
            uid: wx.getStorageSync('uid'),
          },
          header: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          method: 'GET',
          success: function (res_2) {
            // console.log(res_2),
            self.setData({
              food: res_2,
            })
          },
          fail: function (err) {
          }
        })
        self.setData({
          dinner_status: 'order_begin',
        })
      },
      fail: function (err) {
      }
    })
  }
})
<template>
    <div style="display: flex;align-items: center;overflow: hidden;height: 20px;margin-top: 5px">
    <img src="@/assets/image/horn.jpg" height="30" width="30"/>
      <div class="text-container" v-show="show_text" @click="click_view">
        <transition class="inner-container2" name="slide" mode="out-in">
          <el-button type="text" style="color: #21b2f4;display: inline-block;margin-left: 20px"
                     class="text2" :key="text.id">{{text.val}}
          </el-button>
          </transition>
      </div>
        <span v-show="!show_text" style="color: #21b2f4;display: inline-block;margin-left: 20px">——暂无消息——</span>
      </div>
</template>

<script>
export default {
    name: "Notice",
    data() {
      return {
        path: "ws://127.0.0.1:5691",
        socket: "",
        value: {},
        notice_dialog: false,
        number: 0,
        arr: [],
        timer:'',
        show_text:true
      }
    },
    computed: {
      text() {
        return {
          id: this.number,
          val: this.arr[this.number]
        }
      }
    },
    mounted() {
      // 初始化
      this.init()
      this.startMove()
    },
    methods: {
    //实现公告栏轮播（计时器）
      startMove() {
        this.timer = setTimeout(() => {

          this.number += 1
          console.log(this.arr.length)
          console.log(this.number)
          this.startMove()
        }, 10000)
      },
      stopMove(){
        clearTimeout(this.timer)
        // this.arr=[]
          this.show_text=false
          this.number=0
      },
      click_view() {
        this.notice_dialog = true
      },


      //WebSocket的使用
      init: function () {
        if (typeof (WebSocket) === "undefined") {
          alert("您的浏览器不支持socket")
        } else {
          // 实例化socket
          this.socket = new WebSocket(this.path)
          // 监听socket连接
          this.socket.onopen = this.open
          // 监听socket错误信息
          this.socket.onerror = this.error
          // 监听socket消息
          this.socket.onmessage = this.getMessage
          // 销毁监听
          this.socket.onclose = this.close
        }
      },
      open: function () {
        console.log("socket连接成功")

      },
      error: function () {
        console.log("连接错误")
      },
      getMessage: function (msg) {
        console.log('查看连接状态')
        console.log( this.socket.readyState)//查看连接状态
        console.log(msg.data)
        this.value = JSON.parse(msg.data)
        this.arr.push('测试' + this.value.device_id + '条消息')
      },

      send: function () {
        this.socket.send(params)
      },
      close: function () {
        console.log("socket已经关闭")
        this.arr.push('测试 条消息')
      }
    },
    destroyed() {
      console.log("destory")
      this.socket.close()
      this.stopMove()
    }
}
</script>
<style>
    .inner-container {
    height: 14px;
    animation: myMove 5s linear infinite;
    animation-fill-mode: forwards;
    display: inline-block;
  }

  .slide-enter-active, .slide-leave-active {
    transition: all 0.5s linear;
  }

  .slide-leave-to {
    transform: translateY(-20px);
  }

  .slide-enter {
    transform: translateY(20px);
  }

  .text2 {
    color: rgb(33, 178, 244);
    font-size: 14px;
    font-weight: 500;
  }
</style>
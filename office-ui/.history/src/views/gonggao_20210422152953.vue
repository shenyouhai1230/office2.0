<template>
    <div style="display: flex;align-items: center;overflow: hidden;height: 20px;margin-top: 5px">
      <img src="@/assets/images/horn.png" height="30" width="30"/>

      <div class="text-container" v-show="show_text" @click="click_view">
        <transition class="inner-container2" name="slide" mode="out-in">
          <el-button type="text" style="color: #21b2f4;display: inline-block;margin-left: 20px"
                     class="text2" :key="text.id">{{text.val}}
          </el-button>
        </transition>
      </div>
      <span v-show="!show_text" style="color: #21b2f4;display: inline-block;margin-left: 20px">——暂无消息——</span>
    </div>
</temlate>
<script>

</script>
<style lang="sass" scoped>
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
————————————————
版权声明：本文为CSDN博主「qq_42392995」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_42392995/article/details/103527505
</style>

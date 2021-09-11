<template>
    <div style="display: flex;align-items: center;overflow: hidden;height: 20px;margin-top: 5px">
    <img src="@/assets/image/horn.png" height="30" width="30"/>
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
    
}
</script>
<style>

</style>
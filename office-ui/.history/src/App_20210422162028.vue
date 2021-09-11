<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import PanelGroup from "@/views/notify/publicNotice";
export default  {
  name:  'App',
  components: {

  }
  mounted(){
    const h = this.$createElement;
      this.$notify({
          title: '通知',
          message: h('i', { style: 'color: red'}, '提示文案提示文案提示文案提示文案提示文案提示文案提示文案'),
          type: 'info',
          position: 'bottom-right'
        });
      
  }
  
}
</script>

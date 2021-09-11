<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App',
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

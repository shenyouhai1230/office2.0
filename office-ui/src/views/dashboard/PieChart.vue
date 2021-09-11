<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { listTaskbasic } from "@/api/firstpage/someChart";

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
    }
  },
  mounted() {
    this.$nextTick(() => {
      var Series = [];
      var kfNames = [];
listTaskbasic().then(response => {
        var resdata = response.rows;
        for (var i = 0; i < resdata.length; i++) {
            var item = {};
            item.name = resdata[i].name;
            item.value = resdata[i].counts;
            Series.push(item);
            kfNames.push(resdata[i].name);
        }
        this.initChart(Series,kfNames)
      });
      
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart(Series,kfNames) {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: kfNames
        },
        series: [
          {
            name: '任务比例图',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: Series,
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>

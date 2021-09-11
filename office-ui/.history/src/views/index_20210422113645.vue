<template>
  <div class="dashboard-editor-container">
    <panel-group
      @handleSetLineChartData="handleSetBarChartData"
      :devSum="devSum"
      :testSum="testSum"
      :allSum="allSum"
    />

    <el-row style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px">
      <bar-chart :chart-data="barChartData" />
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <line-chart :chart-data="lineChartData" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import LineChart from "./dashboard/LineChart";
import RaddarChart from "./dashboard/RaddarChart";
import PieChart from "./dashboard/PieChart";
import BarChart from "./dashboard/BarChart";
import { getQdnameCount } from "@/api/jobtask/taskdetailed";
var datas = [];
var kfNames = [];
var datas1 = [];
var kfNames1 = [];
var datas2 = [];
var kfNames2 = [];
var datas3 = [];
var kfNames3 = [];
var devCounts = 0;
var testCounts = 0;
var allCounts = 0;
var flag = false;
const barChartData = {
  newVisitis: {
    datas,
    kfNames,
    title: "开发",
  },
  messages: {
    datas: datas1,
    kfNames: kfNames1,
    title: "测试",
  },
  purchases: {
    datas: datas2,
    kfNames: kfNames2,
    title: "总任务",
  },
  shoppings: {
    datas: datas3,
    kfNames: kfNames3,
  },
};
const lineChartData = {
  newVisitis: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130],
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130],
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130],
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130],
  },
};
export default {
  name: "Index",
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
  },
  data() {
    return {
      barChartData: barChartData.newVisitis,
      lineChartData: lineChartData.newVisitis,
      devSum: devCounts,
      testSum: testCounts,
      allSum: allCounts,
    };
  },
  methods: {
    handleSetBarChartData(type) {
      this.barChartData = barChartData[type];
    },
    refreshdata() {
      getQdnameCount("开发中", "联调中").then((response) => {
        var resdata = response.data;
        for (var i = 0; i < resdata.length; i++) {
          kfNames.push(resdata[i].qdname);
          datas.push(resdata[i].nums);
          devCounts = devCounts + resdata[i].nums;
        }
        this.devSum = devCounts;
         getQdnameCount("一轮测试中", "验证测试中").then((response) => {
        var resdata = response.data;
        for (var i = 0; i < resdata.length; i++) {
          kfNames1.push(resdata[i].qdname);
          datas1.push(resdata[i].nums);
          testCounts = testCounts + resdata[i].nums;
        }
        for (var i = 0; i < kfNames1.length; i++) {
          kfNames2.push(kfNames[i]);
          datas2.push(datas[i] + datas1[i]);
          allCounts = allCounts + datas[i] + datas1[i];
        }
        this.testSum = testCounts;
        this.allSum = allCounts;
        flag = true;
      });
      });
    },
  },
  mounted() {
    if (!flag) {
      this.refreshdata();
    }
    const h = this.$createElement;
    this.$notify({
          title: '通知',
          message: h('i', { style: 'color: red'}, '提示文案'),
          type: 'info'
        });
  },
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>

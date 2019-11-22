Vue.component("charts-visualization", {
  template: `<div class="q-pt-xl">
  <!-- <pre>
    {{defaultDrillLevels}}
    {{$props.chartData}}
  </pre> -->
  <div class="back-btn">
    <q-btn
      size="xs"
      v-if="defaultDrillLevels.length > 1"
      color="primary"
      icon="fa fa-chevron-left"
      label="Back"
      @click="drillBack()"
    />
  </div>
  <div v-for="(chart,index) in defaultDrillLevels" :key="index">
    <v-chart
      v-if="chart.isSelected"
      ref="chart"
      theme="green"
      :autoresize="true"
      :options="customizedChartOptions"
      @contextmenu="chartContextMenu($event, chart)"
      @click="chartClicked($event, chart)"
    />
  </div>
</div>`,
  props: {
    chartData: {
      type: Array,
      default: []
    },
    finalVisualizationPayload: {
      type: Array,
      default: []
    },
    chartKey: {
      type: String,
      default: ""
    }
  },
  data() {
    let patternImg =
      "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBzdGFuZGFsb25lPSJubyI/PjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiPjxkZWZzPjxwYXR0ZXJuIGlkPSJwYXR0ZXJuX05rRGgwcCIgcGF0dGVyblVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgd2lkdGg9IjUuNSIgaGVpZ2h0PSI1LjUiIHBhdHRlcm5UcmFuc2Zvcm09InJvdGF0ZSg5MCkiPjxsaW5lIHgxPSIwIiB5PSIwIiB4Mj0iMCIgeTI9IjUuNSIgc3Ryb2tlPSIjMEUxQzE1IiBzdHJva2Utd2lkdGg9IjEiLz48L3BhdHRlcm4+PC9kZWZzPiA8cmVjdCB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSJ1cmwoI3BhdHRlcm5fTmtEaDBwKSIgb3BhY2l0eT0iMSIvPjwvc3ZnPg==";

    let selectedImg = new Image();
    selectedImg.src = patternImg;
    return {
      isdrilledBack: false,
      finalPayload: [],
      chartThreshold: {
        // roseType: {
        //   min: 0,
        //   max: 5,

        // },
        pie: { min: 0, max: 5 },
        donut: {
          min: 5,
          max: 15
        },
        scatter: { min: 50, max: 10000000 },
        bar: { min: 15, max: 50 }
      },
      chartType: "",
      // current default chart theme
      currentTheme: green,
      // initial default chart options to be used for any chart
      chartDefaultOptions: {
        tooltip: {
          trigger: "item",
          formatter: params => {
            return `${params.data.name} : ${params.data.originalValue}`;
          }
        },

        dataZoom: [
          {
            type: "slider",
            start: 0,
            end: 100,
            top: 0
          },
          {
            type: "inside",
            start: 0,
            end: 100
          }
        ],
        legend: {
          show: true,
          type: "scroll"
        },
        xAxis: {
          data: [],
          type: "category",
          axisLabel: {
            interval: 0,
            rotate: 30
          }
        },
        yAxis: {
          type: "value",
          axisLabel: {
            interval: 0,
            rotate: 30
          }
        }
      },
      tempChartDefaultOptions: {},
      defaultDrillLevels: [],
      currentChartSegment: {},
      customizedChartOptions: {
        series: []
      }
    };
  },
  methods: {
    resizeChart() {
      this.customizedChartOptions.resize();
    },
    drillBack() {
      this.isdrilledBack = true;
      this.defaultDrillLevels.pop();

      this.defaultDrillLevels.forEach(chart => {
        chart.chartData.forEach(item => {
          item.addedLog = true;
        });
      });

      this.decideChartType();
      this.defaultDrillLevels[this.defaultDrillLevels.length - 1][
        "isSelected"
      ] = true;
    },
    decideChartType() {
      this.tempChartDefaultOptions = JSON.parse(
        JSON.stringify(this.chartDefaultOptions)
      );
      let chartType = "";
      this.tempChartDefaultOptions.xAxis.data = this.defaultDrillLevels[
        this.defaultDrillLevels.length - 1
      ].chartData.map(item => item.name);
      for (let chartKey in this.chartThreshold) {
        if (
          this.defaultDrillLevels[this.defaultDrillLevels.length - 1].chartData
            .length > this.chartThreshold[chartKey]["min"] &&
          this.defaultDrillLevels[this.defaultDrillLevels.length - 1].chartData
            .length <= this.chartThreshold[chartKey]["max"]
        ) {
          chartType = chartKey;
        }
      }
      if (chartType) {
        this.buildChartOptions(chartType);
      } else {
        this.buildChartOptions("pie");
      }
    },
    buildChartOptions(key) {
      let series = {};
      if (["donut", "pie", "roseType"].indexOf(key) > -1) {
        let radius = false;
        let center = false;
        let roseType = false;
        if (key == "donut") {
          radius = ["50%", "70%"];
        } else if (key == "roseType") {
          // radius = ["20%", "100%"];
          // center = ["50%", "50%"];
          // roseType = "radius";
        }

        this.chartType = "pie";
        delete this.tempChartDefaultOptions.yAxis;
        delete this.tempChartDefaultOptions.xAxis;
        delete this.tempChartDefaultOptions.dataZoom;
        series = {
          type: "pie",
          label: { show: false },
          tooltip: {
            trigger: "item",
            formatter: params => {
              return `${params.data.name} : ${params.data.originalValue}`;
            }
          },
          selectedMode: "multiple",
          data: this.defaultDrillLevels[this.defaultDrillLevels.length - 1]
            .chartData,

          center: center,
          radius: radius
        };
        if (!radius) {
          delete series.radius;
        }
        if (!center) {
          delete series.center;
        }
        if (!roseType) {
          delete series.roseType;
        }
      } else if (key == "bar") {
        this.chartType = "bar";
        this.tempChartDefaultOptions.xAxis.data = this.defaultDrillLevels[
          this.defaultDrillLevels.length - 1
        ].chartData.map(item => item.name);

        this.tempChartDefaultOptions.yAxis = this.chartDefaultOptions.yAxis;

        series = {
          type: "bar",
          tooltip: {
            trigger: "item",
            formatter: params => {
              return `${params.data.name} : ${params.data.originalValue}`;
            }
          }
        };
        if (
          this.defaultDrillLevels[this.defaultDrillLevels.length - 1].chartData
            .length < 5
        ) {
          delete this.tempChartDefaultOptions.dataZoom;
        }
      } else if (key == "scatter") {
        this.chartType = "scatter";
        this.tempChartDefaultOptions.xAxis.data = this.defaultDrillLevels[
          this.defaultDrillLevels.length - 1
        ].chartData.map(item => item.name);

        this.tempChartDefaultOptions.yAxis = this.chartDefaultOptions.yAxis;

        series = {
          type: "scatter",
          tooltip: {
            trigger: "item",
            formatter: params => {
              return `${params.data.name} : ${params.data.originalValue}`;
            }
          }
        };
        if (
          this.defaultDrillLevels[this.defaultDrillLevels.length - 1].chartData
            .length < 5
        ) {
          delete this.tempChartDefaultOptions.dataZoom;
        }
      }

      series.type = this.chartType;

      let temp = Object.assign(this.tempChartDefaultOptions, {
        series: [
          {
            data: this.addLogFunction(
              this.defaultDrillLevels[this.defaultDrillLevels.length - 1]
                .chartData
            )
          }
        ],
        animationEasing: "cubicOut",
        animationDelayUpdate: function(idx) {
          return idx * 15;
        }
      });
      newTemp = Object.assign(temp.series[0], series);
      temp = Object.assign(temp, newTemp);
      this.customizedChartOptions = temp;

      this.$nextTick(() => {
        this.$emit("resize-chart-if-expanded", this.$props.chartKey);
      });
    },
    addLogFunction(chartData) {
      let logData = [];
      chartData.forEach(item => {
        if (!item.addedLog && !item.originalValue) {
          item.originalValue = JSON.parse(JSON.stringify(item.value));
          // added + 1 to every value as chart segment is not rendering since Math.log10(1) = 0.
          item.value = Math.log10(parseInt(item.value + 1));
          item.addedLog = true;
        }
        logData.push(item);
      });
      return logData;
    },
    filterCurrentNode(e) {
      let currentNode = this.defaultDrillLevels[
        this.defaultDrillLevels.length - 1
      ].chartData.filter(row => {
        if (row.name == e.name && row.value == e.value) {
          return row;
        }
      });

      if (currentNode.length == 0) {
        return { isLeafNode: true, data: [] };
      } else {
        if (currentNode[0]["parent_or_leaf_or_both"] == "Leaf") {
          return { isLeafNode: true, data: [] };
        } else {
          return { isLeafNode: false, data: currentNode[0] };
        }
      }
    },
    chartContextMenu(event, curChartData) {
      this.currentChartSegment = curChartData;
      let nodeData = this.filterCurrentNode(event);
      event.event.event.preventDefault();
      if (!nodeData.isLeafNode) {
        this.createNewDrillDownArr(nodeData.data, event);
      }
    },
    chartClicked(event, curChartData) {
      let options = this.customizedChartOptions;
      options.series[0].data.forEach((data, index) => {
        data.addedLog = false;
        if (index === event.dataIndex) {
          if (!data.selected) {
            data.selected = true;
            data.itemStyle = {
              borderWidth: 6,
              borderColor: "green"
            };
            this.prepareFinalPayload(data, "push", data.selected);
          } else {
            data.itemStyle = {
              borderWidth: 0,
              borderColor: "#000"
            };
            data.selected = false;
            this.prepareFinalPayload(data, "pop", data.selected);
          }
        }
      });
      this.customizedChartOptions = JSON.parse(JSON.stringify(options));
      this.customizedChartOptions.tooltip = this.chartDefaultOptions.tooltip;
      this.$emit("hide-current-chart", "reset");
    },
    isAnyChartSelected() {
      let itemClicked = false;
      this.$store.state.charts.finalVisualizationPayload.forEach(item => {
        if (item.data.length > 0) {
          itemClicked = true;
          return itemClicked;
        }
      });
      return itemClicked;
    },
    prepareFinalPayload(curData, action, isClicked) {
      if (action == "push") {
        this.finalPayload.push(curData);
      } else if (action == "pop") {
        let curIndex = this.finalPayload
          .map(item => item.id)
          .indexOf(curData.id);
        this.finalPayload.splice(curIndex, 1);
      }

      this.$emit("final-payload", this.finalPayload);
      this.$emit("chart-clicked", {
        key: this.$props.chartKey,
        isClicked: isClicked
      });
    },
    isChartSegmentSelected(segments) {
      for (let chartIndex in this.$store.state.charts
        .finalVisualizationPayload) {
        if (
          this.$props.chartKey ==
          this.$store.state.charts.finalVisualizationPayload[chartIndex]["key"]
        ) {
          segments.forEach(curChartSegments => {
            let curData = this.$store.state.charts.finalVisualizationPayload[
              chartIndex
            ]["data"].filter(
              item =>
                item.parent_id == curChartSegments.parent_id &&
                item.id == curChartSegments.id
            );
            if (curData.length > 0) {
              curChartSegments.itemStyle = curData[0]["itemStyle"];
              curChartSegments.selected = true;
            }
          });
        }
      }
      return segments;
    },
    prepareCurrentLevel(data, mouseEvt) {
      let rows = [];
      this.$props.chartData.forEach(item => {
        // get all the childrens of current node
        if (item.parent_id == data.id) {
          rows.push(item);
        }
      });
      if (
        !this.isAnyChartSelected() &&
        mouseEvt.type == "contextmenu" &&
        rows.length == 1
      ) {
        return this.prepareCurrentLevel(rows[0], mouseEvt);
      }
      return rows;
    },
    createNewDrillDownArr(nodeData, mouseEvt) {
      // create new array and push to the defaultDrillLevels and make is selected
      let rows = this.prepareCurrentLevel(nodeData, mouseEvt);
      // don't drill down further
      if (rows.length < 1) {
        // this.$emit("hide-current-chart", this.$props.chartKey);
        return false;
      }

      this.defaultDrillLevels.forEach(item => {
        // remove the previously selected chart before
        if (item.id == this.currentChartSegment.id) {
          item.isSelected = false;
        }
      });

      this.isChartSegmentSelected(rows);

      this.defaultDrillLevels.push({
        id: this.defaultDrillLevels.length + 1,
        name: `Level ${this.defaultDrillLevels.length + 1}`,
        isSelected: true,
        chartData: rows
      });
      this.decideChartType();
    },
    // special requirement by client
    checkToSkipSingleItemCharts(level = 1, rawData, isInitialLoading = false) {
      let curLevel = level;
      let curLevelData = [];
      rawData.forEach((element, index) => {
        if (element.tree_level == level) {
          element.itemStyle = {};
          curLevelData.push(element);
        }
      });
      if (curLevel == rawData.length) {
        return { level: level, data: [] };
      }
      if (
        !this.isAnyChartSelected() &&
        isInitialLoading &&
        curLevelData.length == 1
      ) {
        curLevel = level + 1;
        return this.checkToSkipSingleItemCharts(
          curLevel,
          rawData,
          isInitialLoading
        );
      }
      return { level: level, data: curLevelData };
    },
    prepareInitialChartObject(rawData, isInitialLoading = false) {
      let levelData = this.checkToSkipSingleItemCharts(
        1,
        rawData,
        isInitialLoading
      );

      if (levelData.data.length == 0) {
        this.$emit("hide-current-chart", this.$props.chartKey);
        return;
      }

      this.isChartSegmentSelected(levelData.data);
      this.defaultDrillLevels = [
        {
          id: levelData.level,
          name: `Level ${levelData.level}`,
          isSelected: false,
          chartData: levelData.data
        }
      ];
      this.decideChartType();
      this.defaultDrillLevels[this.defaultDrillLevels.length - 1][
        "isSelected"
      ] = true;
    }
  },
  created() {
    this.prepareInitialChartObject(this.$props.chartData, true);
  }
});

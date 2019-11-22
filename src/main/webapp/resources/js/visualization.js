Quasar.iconSet.set(Quasar.iconSet.fontawesomeV5); // fontawesomeV5 is just an example
Vue.component("v-chart", VueECharts);
const glb_chartsStore = {
  namespaced: true,
  actions: glb__chartsActions,
  getters: glb__chartsGetters,
  state: glb__chartsState,
  mutations: glb__chartsMutations
};

const glb_userStore = {
  namespaced: true,
  state: glb__userState
};
const store = new Vuex.Store({
  modules: {
    charts: glb_chartsStore,
    user: glb_userStore
  }
});

new Vue({
  el: "#q-app",
  store,
  name: "Charts",

  data() {
    return {
      showLoading: false,
      getCounts: {
        actIdsCount: 0,
        gvkIdsCount: 0,
        strIdsCount: 0,
        refIdsCount: 0,
        assayIdsCount: 0
      },
      showNoRelevantDataMsg: "",
      expand: false,
      dynamicClass: "",
      disableAllButtons: true,
      test: "",
      visualizationExportLoading: false,
      toggleOperators: true,
      currentClickedChart: false,
      pieData: {
        bibliography: {
          name: "Biblography",
          responseName: "bibliographyVisualization",
          data: [],
          loading: true,
          chartType: "rose",
          isExpanded: false
        },
        yearwise: {
          name: "Year Wise",
          responseName: "yearWiseVisualization",
          data: [],
          loading: true,
          chartType: "ring",
          isExpanded: false
        },
        indication: {
          name: "Indication",
          responseName: "indicationVisualization",
          data: [],
          loading: true,
          chartType: "pie",
          isExpanded: false
        },
        classification: {
          name: "Target/Protein Classification",
          responseName: "proteinClassification",
          data: [],
          loading: true,
          chartType: "pie",
          isExpanded: false
        }
      },
      options: [
        { label: "And", value: "and" },
        { label: "Or", value: "or" },
        { label: "Not", value: "not" }
      ],
      currUserRole: null
    };
  },
  mounted() {
    this.currUserRole = window.config.userRoles;
  },
  created() {
    this.checkToRestoreChartData();
  },
  methods: {
    resetChartDataToInitial() {
      axios({
        method: "POST",
        url:
          config.apiUrl +
          `${config.apiContextPath}security/allmapping/search/visualization`,
        data: JSON.parse(localStorage.getItem("searchPostObj")),
        headers: { userSessionId: window.config.userSessionId },
        contentType: "application/json"
      }).then(response => {
        this.getCounts = response.data;
        this.fetchChartData();
      });
    },
    checkToRestoreChartData() {
      var totalVisualizationStateData = JSON.parse(
        localStorage.getItem("visualizationData")
      );
      if (totalVisualizationStateData) {
        this.$store.state.charts = JSON.parse(
          JSON.stringify(totalVisualizationStateData)
        );

        let url =
          config.apiUrl +
          `${config.apiContextPath}security/allmapping/search/visualization`;

        axios
          .post(url, JSON.parse(localStorage.getItem("searchPostObj")))
          .then(response => {
            if (response.status == 200) {
              this.$store
                .dispatch(
                  "charts/UPDATE_CHART_VALUES",
                  totalVisualizationStateData.finalVisualizationPayload
                )
                .then(response => {
                  if (response.status == 200) {
                    this.showLoading = true;
                    this.getCounts = response.data;

                    this.getIndicationData();
                    this.getBibliographyData();
                    this.getYearwiseData();
                    this.getClassificationData();
                  }
                })
                .catch(e => {});
            }
          })
          .catch(e => e.response);
      } else {
        this.fetchChartData();
      }
    },
    handleNavigation(route) {
      if (
        route == "tabularView" &&
        this.currUserRole != this.$store.state.user.userRoles.ROLE_EVALUATEUSER
      ) {
        this.setVisualizationDataToLocalSt();
        window.location.href =
          window.config.contextPath +
          "/tabularview/" +
          window.config.userSessionId;
      } else if (route == "search") {
        window.location.href = window.config.contextPath + "/welcome";
      } else if (route == "visualization") {
        window.location.href =
          window.config.contextPath +
          "/visualization/" +
          window.config.userSessionId;
      } else {
        this.$refs.userAccessModal.show();
      }
    },
    hideChart(chartKey) {
      // reset all charts
      // requirement changed: don't show a chart once it has been hidden.
      // if (chartKey == "reset") {
      //   this.$store.state.charts.finalVisualizationPayload.forEach(chart => {
      //     chart.hideChart = false;
      //   });
      // }

      // hide current chart & check if all charts are hidden
      let chartsHiddenCount = 0;
      this.$store.state.charts.finalVisualizationPayload.forEach(chart => {
        if (chart.key == chartKey) {
          chart.hideChart = true;
        }
        if (chart.hideChart == true) {
          chartsHiddenCount = chartsHiddenCount + 1;
        }
      });

      if (
        chartsHiddenCount ==
        this.$store.state.charts.finalVisualizationPayload.length
      ) {
        this.showNoRelevantDataMsg =
          "Not enough data to generate charts please select another search criteria.";
      }
    },
    viewAllData() {
      this.resetState();
      axios({
        type: "GET",
        url:
          window.config.apiUrl + "security/allmapping/search/all/visualization",
        data: null,
        headers: { userSessionId: window.config.userSessionId },
        contentType: "application/json"
      }).then(response => {
        this.getCounts = response.data;
        this.fetchChartData();
      });
    },

    setVisualizationDataToLocalSt() {
      localStorage.setItem(
        "visualizationData",
        JSON.stringify(this.$store.state.charts)
      );
    },
    tabularViewClick() {
      if (
        this.currUserRole == this.$store.state.user.userRoles.ROLE_EVALUATEUSER
      ) {
        this.$refs.userAccessModal.show();
        return;
      }
      this.$store.state.charts.tabularViewButtonCLicked = true;

      this.setVisualizationDataToLocalSt();

      window.location.href =
        window.config.apiUrl + "tabularview/" + window.config.userSessionId;
    },
    searchViewClick() {
      window.location.href =
        window.config.apiUrl + "welcome";
    },
    analyzerViewClick() {
      window.location.href =
        window.config.apiUrl + "heatmap/" + window.config.userSessionId;
    },
    chartClickedEvent(payload) {
      this.showLoading = true;
      this.$store.state.charts.finalVisualizationPayload.forEach(
        (item, index) => {
          if (item.key == payload.key) {
            item.clicked = payload.isClicked ? 1 : 0;
            this.currentClickedChart = payload.key;
          } else {
            item.clicked = 0;
          }
        }
      );
      this.updateChartsOnSelection(payload.key);
    },
    updateChartsOnSelection: window.Quasar.utils.debounce(function(
      currSelection
    ) {
      for (let pie in this.pieData) {
        if (currSelection != pie) {
          this.pieData[pie]["loading"] = true;
        }
      }

      this.$store
        .dispatch("charts/UPDATE_CHART_VALUES", "")
        .then(response => {
          if (response.status == 200) {
            this.showLoading = true;
            this.getCounts = response.data;
            this.getBibliographyData(currSelection);
            this.getIndicationData(currSelection);
            this.getYearwiseData(currSelection);
            this.getClassificationData(currSelection);
          }
        })
        .catch(e => {});
    },
    300),
    resizeChart(chartKey, isExpanded = true) {
      if (this.$refs.chart && this.$refs.chart.length > 0) {
        this.$refs.chart.forEach((chart, index) => {
          if (chart.chartKey == chartKey) {
            if (isExpanded) {
              chart.$children[chart.$children.length - 1].$el.style.width =
                "80vw";
              chart.$children[chart.$children.length - 1].$el.style.height =
                "70vh";
            } else {
              chart.$children[chart.$children.length - 1].$el.style.width =
                "300px";
              chart.$children[chart.$children.length - 1].$el.style.height =
                "300px";
            }
          }
        });
      }
    },
    toggleFullScreen(chartKey, index, isExpanded) {
      if (isExpanded) {
        this.pieData[chartKey]["isExpanded"] = true;
        document.getElementById(index).classList.add("custom-modal");
        document.getElementById("modal-backdrop").style.display = "block";
      } else {
        this.pieData[chartKey]["isExpanded"] = false;
        document.getElementById(index).classList.remove("custom-modal");
        document.getElementById("modal-backdrop").style.display = "none";
      }
      this.resizeChart(chartKey, this.pieData[chartKey]["isExpanded"]);
    },
    resetState() {
      for (let i in this.pieData) {
        this.pieData[i]["data"] = [];
        this.pieData[i]["loading"] = true;
      }
      this.$store.state.charts.finalVisualizationPayload.forEach(item => {
        item.data = [];
        item.clicked = 0;
        item.operator = "or";
        item.hideChart = false;
      });

      this.currentClickedChart = false;
    },
    getBibliographyData(currSelection = null) {
      let key = "bibliography";

      if (currSelection == key) {
        return;
      }
      this.$store
        .dispatch("charts/GET_BIBLIOGRAPHY_CHART_DATA", "")
        .then(response => {
          this.setChartData(response.data, key);
        })
        .catch(e=> {
          console.log(e)
          this.showLoading = false
        }) 
    },
    getIndicationData(currSelection = null) {
      let key = "indication";

      if (currSelection == key) {
        return;
      }
      this.$store
        .dispatch("charts/GET_INDICATION_CHART_DATA", "")
        .then(response => {
          this.setChartData(response.data, key);
        })
        .catch(e=> {
          console.log(e)
          this.showLoading = false
        }) 
    },
    getYearwiseData(currSelection = null) {
      let key = "yearwise";

      if (currSelection == key) {
        return;
      }
      this.$store
        .dispatch("charts/GET_YEARWISE_CHART_DATA", "")
        .then(response => {
          this.setChartData(response.data, key);
        })
        .catch(e=> {
          console.log(e)
          this.showLoading = false
        }) 
    },
    getClassificationData(currSelection = null) {
      let key = "classification";

      if (currSelection == key) {
        return;
      }
      this.$store
        .dispatch("charts/GET_CLASSIFICATION_CHART_DATA", "")
        .then(response => {
          this.setChartData(response.data, key);
          axios({
            type: "GET",
            url:
              window.config.apiUrl +
              "security/allmapping/search/visualresults/count",
            data: null,
            headers: { userSessionId: window.config.userSessionId },
            contentType: "application/json"
          }).then(response => {
            this.getCounts = response.data;
          })
          .catch(e=> {
            console.log(e)
            this.showLoading = false
          }) 
        });
    },
    fetchChartData() {
      this.resetState();
      this.getBibliographyData();
      this.getIndicationData();
      this.getYearwiseData();
      this.getClassificationData();
    },
    setChartData(responseData, identifier) {
      this.showNoRelevantDataMsg = "";

      // this.hideChart("reset");
      // don't load/refresh current clicked chart data
      if (this.currentClickedChart == identifier) {
        this.hideChart(identifier);
        return;
      }

      this.resizeChart(identifier, false);
      this.pieData[identifier]["loading"] = true;
      this.pieData[identifier]["data"] = [];
      this.pieData[identifier]["data"] = responseData;

      this.pieData[identifier]["loading"] = false;
      if (responseData.length == 0) {
        this.hideChart(identifier);
        return;
      }

      let isDisable = false;
      let hideGlobalAppLoader = [];
      for (let chartKey in this.pieData) {
        hideGlobalAppLoader.push(this.pieData[chartKey]["loading"]);
        if (this.pieData[chartKey]["loading"] == true) {
          isDisable = true;
        }
      }

      // hide app loader
      if (hideGlobalAppLoader.indexOf(true) == -1) {
        this.showLoading = false;
      }

      this.disableAllButtons = isDisable == true ? true : false;
    },
    exportFile() {
      if (
        this.currUserRole ==
          this.$store.state.user.userRoles.ROLE_EVALUATEUSER ||
        this.currUserRole == this.$store.state.user.userRoles.ROLE_PARTIALUSER
      ) {
        this.$refs.userAccessModal.show();
        return;
      }
      this.visualizationExportLoading = true;
      this.$store
        .dispatch("charts/EXPORT_VISUALIZATION_DATA")
        .then(response => {
          if (response.status == 200) {
            this.visualizationExportLoading = false;
          }
        })
        .catch(e => e.response);
    }
  }
});

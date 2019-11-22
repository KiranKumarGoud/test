
const glb__chartsActions = {
  UPDATE_CHART_VALUES: ({ commit, rootState }, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/filteredvisualization`;

    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();

    let newPayload = rootState.charts.finalVisualizationPayload;

    if (payload) {
      newPayload = payload;
    }

    return axios
      .post(url, newPayload, {
        cancelToken: config.source.token
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  FETCH_CHART_DATA: (mainObj, payload) => {
    let url =
      config.apiUrl +
      `${config.apiContextPath}security/allmapping/search/visualization`;
    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();

    return axios
      .post(url, removeThisPayload, {
        cancelToken: config.source.token
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_CLASSIFICATION_CHART_DATA: (mainObj, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/visualization/classification`;

    return axios
      .get(url, {
        data: {}
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_YEARWISE_CHART_DATA: (mainObj, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/visualization/yearwise`;

    return axios
      .get(url, {
        data: {}
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_BIBLIOGRAPHY_CHART_DATA: (mainObj, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/visualization/bibliography`;

    return axios
      .get(url, {
        data: {}
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_INDICATION_CHART_DATA: (mainObj, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/visualization/indication`;

    return axios
      .get(url, {
        data: {}
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  EXPORT_VISUALIZATION_DATA: ({ commit }, payload) => {
    let payload1 = { selectedTabIds: {}, unSelectedTabIds: {} };
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/tabularview/export/download/fromtempdata`;
    return axios({
      url: url,
      method: "POST",
      data: payload1,
      responseType: "blob" // important
    })
      .then(response => {
       /* const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "export.tsv");
        document.body.appendChild(link);
        link.click();
        return response;*/
      const url = window.URL.createObjectURL(new Blob([response]));
  var zip = new JSZip();
  var timestamp = Number(new Date())
  var date = new Date(timestamp)
  var dateFullYear = date.getFullYear();
  //var dateMonth = date.getMonth()+1;
  var dateMonth =  "0" + (date.getMonth() + 1);//.slice(-2);
  var dateDate = date.getDate();
  var getHours = date.getHours();
  var getMinut = date.getMinutes();
  var getSeconds = date.getSeconds();

  var timeTimeStamp = dateFullYear + "-" + dateMonth +"-"+ dateDate + " " + getHours;
  console.log(timeTimeStamp);
  zip.file("export.csv" ,new Blob([response]));

  zip.generateAsync({type: "blob"}).then(function(content) {
      saveAs(content , "GoStarAllData" + ".zip");
    console.log("inside the generate function");
  }.bind(window));
      })
      .catch(e => response);
  }
};

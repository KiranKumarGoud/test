const glb__simpleSearchActions = {
  GET_SEARCH_RESULTS_AND_COUNT: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `${config.apiContextPath}security/allmapping/search/count`;
    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();

    return axios
      .post(url, payload, { cancelToken: config.source.token })
      .then(response => {
        return response;
      })
      .catch(e => e);
  },
  GET_SEARCH_DEFAULT_COUNTS: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `${config.apiContextPath}security/allmapping/search/default/count`;
    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();

    return axios({
      method: "get",
      url: url,
      data: null,
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(response => {
        return response;
      })
      .catch(e => e);
  },
  FETCH_CHART_DATA: (mainObj, payload) => {
    localStorage.setItem(
      "searchPostObj",
      JSON.stringify(mainObj.rootState.simpleSearch.searchPostObj)
    );
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
      .post(url, mainObj.rootState.simpleSearch.searchPostObj, {
        cancelToken: config.source.token
      })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  SAVE_STORE_DATA: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/visualization/storerequest`;

    return axios({
      method: "post",
      url: url,
      data: JSON.stringify(payload),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(response => {
        return response;
      })
      .catch(e => e);
  },
  GET_STORE_DATA: ({ commit, rootState }, payload) => {
    let url =
      config.apiUrl +
      `${
        config.apiContextPath
      }security/allmapping/search/visualization/fetchrequest`;
    config.source = axios.CancelToken.source();
    return axios({
      method: "get",
      url: url,
      data: null,
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(response => {
        return response;
      })
      .catch(e => {
        console.log(e);
      });
  }
};

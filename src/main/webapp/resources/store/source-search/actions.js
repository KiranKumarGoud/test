const glb__sourceSearchActions = {
  GET_MIN_MAX_VALUES: ({ commit, rootState }, payload) => {
    let url = config.apiUrl;

    url += `${
      config.apiContextPath
    }security/source/structural/properties/minmax`;
    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      console.log("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();
    return axios
      .get(url, payload, { cancelToken: config.source.token })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  }
};

const tabularViewActions = {
  GET_ACT_ID: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `security/tabularview/activity/bytempactids`;

    return axios
      .post(url, payload)
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_GVK_ID: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `security/tabularview/assay/bytempassayids`;

    return axios
      .post(url, payload)
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_REF_ID: ({ commit }, payload) => {
    // let url =
    //   config.apiUrl +
    //   `security/tabularview/reference/bytemprefids`;
    let url = 'http://172.21.10.202:3000/tabreferences';
    return axios
      .post(url, payload)
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  GET_STR_ID: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `security/tabularview/structuredetails/bytempstrids`;

    return axios
      .post(url, payload)
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  EXPORT_DATA: ({ commit }, payload) => {
    let url =
      config.apiUrl +
      `security/tabularview/export/download/filename`;

    return axios({
      url: url,
      method: "POST",
      data: payload,
      responseType: "blob" // important
    })
      .then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "export.csv");
        document.body.appendChild(link);
        link.click();
        return response;
      })
      .catch(e => response);
  },
  GET_REF_Filters: ({ commit }, payload) => {
    let url = 'http://172.21.10.202:3000/getfilteroptions';
    return axios
      .post(url, payload)
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  }
};

export default {
  LOGIN: ({ commit }, payload) => {
    let url = config.apiUrl + `${config.apiContextPath}token/generate-token`;

    return axios
      .post(url, payload)
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  }
};

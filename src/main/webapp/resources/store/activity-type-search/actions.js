const glb__activityTypeSearchActions = {
  GET_ACTIVITY_OPTIONS_LIST: ({ commit, rootState }, payload) => {
        // cancel the previous request
        if (typeof config.source != typeof undefined) {
          console.log("Operation canceled due to new request.");
        }
    
        // save the new request for cancellation
        config.source = axios.CancelToken.source();
        
        let url = `${config.contextPath}`
        if(payload.type == 'ACTIVITY_TYPE') {
          url += `/security/activity/type/bylabelvalue/type/${payload.query}`
        }else if(payload.type == 'ACTIVITY_UOM') {
          url += `/security/activity/type/bylabelvalue/uom/${payload.query}`
        }else if(payload.type == 'ACTIVITY_PREFIX') {
          url += `/security/activity/type/bylabelvalue/allprefixes`
        }
        
        return axios
          .get(url, { cancelToken: config.source.token })
          .then(response => {
            if (response.status === 200) {
              return response
            }
            return response;
          })
          .catch(e => e.response);
   }
};

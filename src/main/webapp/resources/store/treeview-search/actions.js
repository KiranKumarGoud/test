const glb__treeViewSearchActions = {
  GET_LAZY_LOAD_TREE_ITEMS: ({commit, state }, payload ) => {
    
    let url = config.apiUrl;
 
    switch (state.currentTreeViewSelected) {
      case 'TargetTreeView':
        url += 'security/treeview/target/' + payload;
        break;

      case "IndicationTreeView":
        url += 'security/treeview/indication/' + payload;
        break;

      case "PharmacokineticsTreeView":
        url += 'security/treeview/adme/' + payload;
        break;

      case "ToxicityTreeView":
        url += 'security/treeview/toxicity/' + payload;
        break;

      case "TaxonomyTreeView":
        url += 'security/treeview/taxonomy/' + payload;
        break;

      case "EndpointTreeView":
        url += 'security/treeview/activityendpoint/' + payload;
        break;
    }
    
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    config.source = axios.CancelToken.source();
    return axios
      .get(url, { cancelToken: config.source.token })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  },
  SEARCH_TREE_VIEW_ITEMS: ({commit, state}, payload ) => {

    let url = config.apiUrl


    switch (state.currentTreeViewSelected) {
      case 'TargetTreeView':
        url +=  'security/treeview/target/bytargetnamelist';
        break;

      case "IndicationTreeView":
        url += 'security/treeview/indication/bytherapeuticuselist';
        break;
      
      case "PharmacokineticsTreeView":
        url += 'security/treeview/adme/byadmenamelist';
        break;

      case "ToxicityTreeView":
        url += 'security/treeview/toxicity/bytoxicitynamelist';
        break;

      case "TaxonomyTreeView":
        url += 'security/treeview/taxonomy/bytaxnamelist';
        break;
      
      case "EndpointTreeView":
        url += 'security/treeview/activityendpoint/byendpointnamelist';
        break;
    }

    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    config.source = axios.CancelToken.source();
    return axios
      .post(url, payload, { cancelToken: config.source.token })
      .then(response => {
        return response;
      })
      .catch(e => e.response);
  }
};
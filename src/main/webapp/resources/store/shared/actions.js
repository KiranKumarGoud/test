const glb_sharedActions = {
  GET_SINGLE_AUTOCOMPLETE_RESULTS: ({ commit, rootState }, payload) => {
   // starts
    let url = config.apiUrl;

    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();
    // this encoded string conditions used only for source strain search
    let encodedSearchTerm = null;
    if (payload.searchTerm != '') {
      encodedSearchTerm = encodeURI(payload.searchTerm);
    }
    
    let apiEndpoints = {
      "CUSTOM_SEARCH": {
        "Journal": `${config.apiContextPath}security/bibliography/fetchjournalnames`,
      },
      "STRAIN_SEARCH": {
        "Genus": `${config.apiContextPath}security/source/strainsearch/forgenus/${encodedSearchTerm}`,
        "Species": `${config.apiContextPath}security/source/strainsearch/forspecies/Haemophilus/${encodedSearchTerm}`,
        "StrainType": `${config.apiContextPath}security/source/strainsearch/forstrain/Absidia/corymbifera/${encodedSearchTerm}`,
      }
    }
    mainloop:
    for(let type in apiEndpoints) {
      for(let key in apiEndpoints[type]) {
        if(payload.searchType == type && payload.selectedOptionValue == key) {
          url += apiEndpoints[type][key]
          break mainloop;
        }
      }
    }
    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      console.log("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();

    return axios
      .get(url, payload, { cancelToken: config.source.token })
      .then(response => {
        if (response.status === 200) {
          // this.$store.state.simpleSearch.simpleSearchAutoOptions = response.data;
          // commit('SET_TARGET_SEARCH_OPTIONS', response.data)
        }
        return response;
      })
      .catch(e => e.response);
    // ends 
    /* let url = config.apiUrl + 'security/bibliography/fetchjournalnames'
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }
    config.source = axios.CancelToken.source(); */
     /*return axios
      .get(url, { cancelToken: config.source.token })
      .then(response => {
        return response;
      })
      .catch(e => e.response); */
  },
  POST_SINGLE_AUTOCOMPLETE_RESULTS: ({ commit, rootState }, payload) => {
    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    let url = config.apiContextPath
    if(payload.selectedDataSource == 'j') {
      url += 'security/bibliography/customsearch/journal/fetch'
    }else{
      url += 'security/bibliography/customsearch/patent/fetch'
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();
    return axios
      .post(url, payload, { cancelToken: config.source.token })
      .then(response => response)
      .catch(e => e.response);
  },
  GET_AUTO_COMPLETE_RESULTS: ({ commit, rootState }, payload) => {
    let url = config.apiUrl;

    if (typeof config.source != typeof undefined) {
      config.source.cancel("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();
    let encodedSearchTerm = encodeURI(payload.searchTerm);

    let apiEndpoints = {
      "SIMPLE_SEARCH": {
        "Target": `${config.apiContextPath}security/target/contains/${encodedSearchTerm}`,
        "Assay": `${config.apiContextPath}security/ontoassay/byenddataPointLeaf/true/`,
        "Assay": `${config.apiContextPath}security/ontoassay/byenddataPointLeaf/true/`,
        "Structure": `${config.apiContextPath}security/structure/compoundsynonyms/contains/${encodedSearchTerm}`,
        "bibliography": `${config.apiContextPath}security/bibliography/referencename/contains/${encodedSearchTerm}`,
        "ActivityMechanism": `${config.apiContextPath}security/activity/mechanism/list`,
        "ActivityTypes": `${config.apiContextPath}security/activity/type/byactivitytype/${encodedSearchTerm}`,
        "Indication": `${config.apiContextPath}security/indication/bytherapuic/${encodedSearchTerm}`,
        "CompoundCategories": `${config.apiContextPath}security/compoundcategories/fetchall`,
        "ClinicalPhases": `${config.apiContextPath}security/clinicalphases/fetchall`,
        "Source": `${config.apiContextPath}security/source/bysynonym/${encodedSearchTerm}`,
      },
      "TARGET_ADVANCE_SEARCH": {
        "common": `${config.apiContextPath}security/target/targetadvsearch/${payload.selectedOptionValue}/${encodedSearchTerm}`
      },
      "INDICATION_ADVANCE_SEARCH": {
        "DiseaseName": `${config.apiContextPath}security/indication/bytherapuic/${encodedSearchTerm}`,
        "ICD-10": `${config.apiContextPath}security/indication/byicd10code/${encodedSearchTerm}`,
        "OMIM": `${config.apiContextPath}security/indication/byicd10code/${encodedSearchTerm}`,
        "MedDRA": `${config.apiContextPath}security/indication/byicd10code/${encodedSearchTerm}`,
      },
      "TERM_ADVANCE_SEARCH": {
        "compound_name": `${config.apiContextPath}security/structure/compoundsynonyms/contains/${encodedSearchTerm}`,
        "cas_no": `${config.apiContextPath}security/structure/casno/contains/${encodedSearchTerm}`,
      },
      "BIBLIOGRAPHY_ADVANCE_SEARCH": {
        // list search
        "pubmed_id": `${config.apiContextPath}security/bibliography/bibliographylistsearch/pubmedId/${encodedSearchTerm}`,
        "doi": `${config.apiContextPath}security/bibliography/bibliographylistsearch/doi/${encodedSearchTerm}`,
        "issn_no": `${config.apiContextPath}security/bibliography/refissno/contains/${encodedSearchTerm}`,
        "mesh": `${config.apiContextPath}security/bibliography/refmesh/contains/${encodedSearchTerm}`,
        "ref_id": `${config.apiContextPath}security/bibliography/refdoi/contains/${encodedSearchTerm}`,
        // "refpubmed": `${config.apiContextPath}security/bibliography/refpubmed/contains/${encodedSearchTerm}`,
      },
      "CRITERION_SEARCH": {
        "author": `${config.apiContextPath}security/bibliography/refcriterianauthor/contains/${encodedSearchTerm}`,
        "company": `${config.apiContextPath}security/bibliography/refcriteriancompanyname/contains/${encodedSearchTerm}`,
      },
      "TREE_VIEW_SEARCH": {
        "TargetTreeView": `${config.apiContextPath}security/treeview/target/searchbytargetname/${encodedSearchTerm}`,
        "IndicationTreeView": `${config.apiContextPath}security/treeview/indication/searchbytherapeuticuse/${encodedSearchTerm}`,
        "PharmacokineticsTreeView": `${config.apiContextPath}security/treeview/adme/searchbyadmename/${encodedSearchTerm}`,
        "ToxicityTreeView": `${config.apiContextPath}security/treeview/toxicity/searchbytoxicityname/${encodedSearchTerm}`,
        "TaxonomyTreeView": `${config.apiContextPath}security/treeview/taxonomy/searchbytaxname/${encodedSearchTerm}`,
        "EndpointTreeView": `${config.apiContextPath}security/treeview/activityendpoint/searchbyendpointname/${encodedSearchTerm}`,
      },
      "SOURCE_ADVANCE_SEARCH": {
        // list search
        "SourceId": `${config.apiContextPath}security/source/bysynonym/${encodedSearchTerm}`,
        "TaxId": `${config.apiContextPath}security/source/bytaxid/${encodedSearchTerm}`,
      },
      "STRAIN_SEARCH": {
        "genus": `${config.apiContextPath}security/source/strainsearch/forgenus/${encodedSearchTerm}`,
        "species": `${config.apiContextPath}security/source/strainsearch/forspecies/Haemophilus/${encodedSearchTerm}`,
        "strainType": `${config.apiContextPath}security/source/strainsearch/forstrain/Absidia/corymbifera/${encodedSearchTerm}`,
      }      
    }
    mainloop:
    for(let type in apiEndpoints) {
      for(let key in apiEndpoints[type]) {
        if(payload.searchType == type && payload.selectedOptionValue == key) {
          url += apiEndpoints[type][key]
          break mainloop;
        }
        if(payload.searchType == 'TARGET_ADVANCE_SEARCH') {
          url += apiEndpoints[payload.searchType]['common']
          break mainloop;
        }
        
      }
    }

    // cancel the previous request
    if (typeof config.source != typeof undefined) {
      console.log("Operation canceled due to new request.");
    }

    // save the new request for cancellation
    config.source = axios.CancelToken.source();

    return axios
      .get(url, payload, { cancelToken: config.source.token })
      .then(response => {
        if (response.status === 200) {
          // this.$store.state.simpleSearch.simpleSearchAutoOptions = response.data;
          // commit('SET_TARGET_SEARCH_OPTIONS', response.data)
        }
        return response;
      })
      .catch(e => e.response);
  }
};

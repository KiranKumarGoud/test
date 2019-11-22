Quasar.iconSet.set(Quasar.iconSet.fontawesomeV5); // fontawesomeV5 is just an example

const glb_simpleSearchStore = {
  namespaced: true,
  actions: glb__simpleSearchActions,
  getters: glb__simpleSearchGetters,
  state: glb__simpleSearchState,
  mutations: glb__simpleSearchMutations
};
const glb__advanceSearchStore = {
  namespaced: true,
  actions: glb__advanceSearchActions,
  getters: glb__advanceSearchGetters,
  state: glb__advanceSearchState,
  mutations: glb__advanceSearchMutations
};
const glb_sharedStore = {
  namespaced: true,
  actions: glb_sharedActions,
  getters: glb_sharedGetters,
  state: glb_sharedState,
  mutations: glb_sharedMutations
};
const glb_structureSearchStore = {
  namespaced: true,
  actions: glb__structureSearchActions,
  getters: glb__structureSearchGetters,
  state: glb__structureSearchState,
  mutations: glb__structureSearchMutations
};
const glb_bibliographySearchStore = {
  namespaced: true,
  actions: glb__bibliographySearchActions,
  getters: glb__bibliographySearchGetters,
  state: glb__bibliographySearchState,
  mutations: glb__bibliographySearchMutations
};
const glb_activityTypeSearchStore = {
  namespaced: true,
  actions: glb__activityTypeSearchActions,
  getters: glb__activityTypeSearchGetters,
  state: glb__activityTypeSearchState,
  mutations: glb__activityTypeSearchMutations
};

const glb_treeViewSearchStore = {
  namespaced: true,
  actions: glb__treeViewSearchActions,
  getters: glb__treeViewSearchGetters,
  state: glb__treeViewSearchState,
  mutations: glb__treeViewSearchMutations
};

const glb_sourceSearchStore = {
  namespaced: true,
  actions: glb__sourceSearchActions,
  getters: glb__sourceSearchGetters,
  state: glb__sourceSearchState,
  mutations: glb__sourceSearchMutations
};

const glb_userStore = {
  namespaced: true,
  state: glb__userState
};
const store = new Vuex.Store({
  modules: {
    activityTypeSearch: glb_activityTypeSearchStore,
    simpleSearch: glb_simpleSearchStore,
    advanceSearch: glb__advanceSearchStore,
    shared: glb_sharedStore,
    structureSearch: glb_structureSearchStore,
    bibliographySearch: glb_bibliographySearchStore,
    treeViewSearch: glb_treeViewSearchStore,
    sourceSearch: glb_sourceSearchStore,
    user: glb_userStore
  }
});
store.modules = localStorage.getItem("simpleSearchStore");
const userName = window.config.userName;
new Vue({
  el: "#q-app",
  store,
  name: "SimpleSearch",
  directives: {
    focus
  },
  mounted() {
    if (!localStorage.getItem("simpleSearchStore")) {
      this.getInitalCounts();
    }
    this.currUserRole = window.config.userRoles;
    this.getStoreData();
  },
  data() {
    return {
      reloadAutoComplete: false,
      showAssayBindingDeleteAlert: false,
      showAssayBindingRefreshAlert: false,
      openAdvSearch: false,
      prevPostReq: [],
      model: [],
      userAutocompleteSearchQuery: "",
      showLoader: false,
      currentSelectedValue: "",
      disableUpload: false,
      simpleSearchOptionValue: "",
      showAutoCompleteAndButtons: false,
      fileName: "",
      advanceSearchNewValuesUpdated: false,
      change: false,
      totalOperators: [
        { label: "And", value: "and" },
        { label: "Or", value: "or" },
        { label: "Not", value: "not" }
      ],
      showLoading: false,
      currUserRole: null,
      disableTabularAndVisualization: true,
      showActivityTypesAlert: false
    };
  },

  methods: {    
    showAlertPopup() {
      this.showAssayBindingRefreshAlert = true;
    },
    showAlertPopupActivity() {
      this.showActivityTypesAlert = true;
    },
    getInitalCounts() {
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_DEFAULT_COUNTS")
        .then(response => {
          if (response.status == 200) {
            this.$store.state.simpleSearch.searchCount = response.data;
            this.$store.state.simpleSearch.defatultSearchCount = response.data;
          }
        });
    },
    getStoreData() {
      if (localStorage.getItem("simpleSearchStore")) {
        let res = JSON.parse(localStorage.getItem("simpleSearchStore"));
        this.$store.state.advanceSearch = res.advanceSearch;
        this.$store.state.shared = res.shared;
        this.$store.state.simpleSearch = res.simpleSearch;
        this.$store.state.structureSearch = res.structureSearch;
        this.$store.state.user = res.user;
      }
    },
    handleNavigation(route) {
      if(!this.$store.state.simpleSearch.enableDashboardOnSubmit) {
        return false;
      }
      if (
        route == "tabularView" &&
        this.currUserRole != this.$store.state.user.userRoles.ROLE_EVALUATEUSER
      ) {
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
    popupClosed() {
      this.openAdvSearch = false;
    },
    handleResetAndDelete(item, itemIndex, identifier) {
      
      if (!item.hasValue) {        
        identifier == "reset"
          ? this.resetSimpleSearch(item, itemIndex)
          : this.deleteRowData(item, itemIndex);
        return;
      }
    // CHECK IF ACTIVITYtYPES  IS SELECTED START  
      let activityTypesData = this.$store.state.simpleSearch.simpleSearchRowData.filter(
        p => p.simpleSearchSelectedValue == "ActivityTypes" ||
        p.simpleSearchSelectedValue == "EndpointTreeView"
      );

      

      let doShowAlertMsgForActive = 0;

      if (activityTypesData.length > 0) {
        // if (activityTypesData.length == 0) {
        //   identifier == "reset"
        //     ? this.resetSimpleSearch(item, itemIndex)
        //     : this.deleteRowData(item, itemIndex);
  
        //   return;
        // }
       
        for (
          let index = 0;
          index < this.$store.state.simpleSearch.simpleSearchRowData.length;
          index++
        ) {
          if (
            this.$store.state.simpleSearch.simpleSearchRowData[index][
              "hasValue"
            ]
          ) {
            for (let item of this.$store.state.simpleSearch.simpleSearchOptions) {
              if (item.value == this.$store.state.simpleSearch.simpleSearchRowData[index][
                "simpleSearchSelectedValue"]) {
                  doShowAlertMsgForActive += 1;
              }
            }
          } else {
            if (this.$store.state.simpleSearch.simpleSearchRowData.length==2 && (item.simpleSearchSelectedValue != "ActivityTypes" || item.simpleSearchSelectedValue != "EndpointTreeView")) {
              this.showActivityTypesAlert = true;
              return;
              }
          }
        }
      }
      if(doShowAlertMsgForActive == 2) {
        if (item.simpleSearchSelectedValue != "ActivityTypes" && item.simpleSearchSelectedValue != "EndpointTreeView") {
        this.showActivityTypesAlert = true;
        return;
        }
      }
      // CHECK IF ACTIVITYtYPES  IS SELECTED END

      let doShowAlertMsg = 0;

      // check if Assay is selected
      let assayData = this.$store.state.simpleSearch.simpleSearchRowData.filter(
        p => p.simpleSearchSelectedValue == "Assay" && p.hasValue
      );

      if (assayData.length > 0) {
        let filterBindingLikeAssayData = assayData[0].simpleSearchAutoComplete.filter(
          p =>
            p.label.toLowerCase().indexOf("b") == 0 ||
            p.label.toLowerCase().indexOf("f") == 0
        );

        if (filterBindingLikeAssayData.length == 0) {
          identifier == "reset"
            ? this.resetSimpleSearch(item, itemIndex)
            : this.deleteRowData(item, itemIndex);

          return;
        }

        // if binding like data is selected then check if any one of the row has value
        for (
          let index = 0;
          index < this.$store.state.simpleSearch.simpleSearchRowData.length;
          index++
        ) {
          if (
            this.$store.state.simpleSearch.simpleSearchRowData[index][
              "hasValue"
            ]
          ) {
            doShowAlertMsg += 1;
          }
        }
      }
      if (doShowAlertMsg == 2) {
        if (item.simpleSearchSelectedValue != "Assay") {
          if (identifier == "reset") {
            this.showAssayBindingRefreshAlert = true;
            this.showAssayBindingDeleteAlert = false;
          } else {
            this.showAssayBindingRefreshAlert = false;
            this.showAssayBindingDeleteAlert = true;
          }
          return;
        }
        identifier == "reset"
          ? this.resetSimpleSearch(item, itemIndex)
          : this.deleteRowData(item, itemIndex);
        this.getSearchResultsAndCount();
      } else {
        identifier == "reset"
          ? this.resetSimpleSearch(item, itemIndex)
          : this.deleteRowData(item, itemIndex);
        this.getSearchResultsAndCount();
      }
    },
    reset(item, itemIndex) {
      this.handleResetAndDelete(item, itemIndex, "reset");
    },
    resetOnConfirm() {
      this.showAssayBindingRefreshAlert = false;

      for (const [
        itemIndex,
        item
      ] of this.$store.state.simpleSearch.simpleSearchRowData.entries()) {
        if (item.simpleSearchSelectedValue != "Assay") {
          let ind = 0;
          if (itemIndex > 0) ind = itemIndex - 1;

          let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
            ind
          ];

          if (item.disableAllFields) item.disableAllFields = false;
          if (
            item.advanceSearch.autoComplete.length > 0 ||
            item.fileName != ""
          ) {
            this.resetTargetSearch(item, itemIndex);
          } else if (item.isStructureAdvanceSearch) {
            this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
              "advanceSearch"
            ] = { autoComplete: [] };
            item.structureAdvanceSearchTab = null;
            /* this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT"); */
            this.getInitalCounts();
            this.$store.commit(
              "structureSearch/RESET_STRUCTURAL_PROPERTY_ROW_DATA"
            );
            this.$store.commit("structureSearch/RESET_MIN_MAX");
            item.isStructureAdvanceSearch = false;
            item.simpleSearchAutoComplete = [];
            item.hasValue = false;
          } else {
            // reset autocomplete
            this.$store.state.simpleSearch.simpleSearchRowData[
              itemIndex
            ].simpleSearchAutoComplete = [];
            for (let autoRef of this.$refs.simpleAutocomplete) {
              if (
                autoRef.$props.selectedValue == item.simpleSearchSelectedValue
              ) {
                autoRef.resetAutoCompleteValues();
              }
            }
          }
        }
        for (let autoRef of this.$refs.simpleAutocomplete) {
          if (autoRef.$props.selectedValue == item.simpleSearchSelectedValue) {
            autoRef.resetAutoCompleteValues();
          }
        }
        item.hasValue = false;
      }

      if (this.$store.state.simpleSearch.simpleSearchRowData.length > 1) {
        this.getSearchResultsAndCount();
      }
    },
    resetSimpleSearch(item, itemIndex) {
      this.$store.state.advanceSearch.currentItemIndex = itemIndex;
      item.hasValue = false;

      item.bibliographySearch = JSON.parse(glb__initialBibliographySearchState)
      item.sourceSearch = JSON.parse(glb__initialSourceSearchState)
      if (item.advanceSearch.autoComplete.length > 0 || item.fileName != "") {        
        this.resetTargetSearch(item, itemIndex);
      }      
      if (item.isStructureAdvanceSearch) {
        this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][  
          "advanceSearch"
        ] = { autoComplete: [] };
        item.structureAdvanceSearchTab = "chemistry";
        this.getInitalCounts();
        //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
        this.$store.commit(
          "structureSearch/RESET_STRUCTURAL_PROPERTY_ROW_DATA"
        );
        this.$store.commit("structureSearch/RESET_MIN_MAX");
        this.$store.state.structureSearch.drawStructureMolData = null;
        item.isStructureAdvanceSearch = false;
        item.simpleSearchAutoComplete = [];
        item.hasValue = false;
      } else {
        // reset autocomplete
        this.$store.state.simpleSearch.simpleSearchRowData[
          itemIndex
        ].simpleSearchAutoComplete = [];
        for (let autoRef of this.$refs.simpleAutocomplete) {         
          if (autoRef.$props.selectedValue == item.simpleSearchSelectedValue) {
            autoRef.resetAutoCompleteValues();
          }
        }
      }

      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "advanceSearchSelectedValue"
      ] = null
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "advanceSearchSelectedValueName"
      ] = ''
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['bibliographySearch']['listSearch']['fileName'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['bibliographySearch']['listSearch']['fileData'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['sourceSearch']['listSearch']['fileName'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['sourceSearch']['listSearch']['fileData'] = []

      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['bibliographySearch']['customSearch'] = {}
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['sourceSearch']['strainSearch'] = {}
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['sourceSearch']['listSearch'] = {}
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['sourceSearch']['currentSelectedTab'] = 'list'
      
      delete this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['treeViewSearch']
      this.$store.state.treeViewSearch = JSON.parse(glb__initialTreeViewSearchState);


    },
    operatorChanged(selectedOperator, item) {
      if (selectedOperator.target.checked) {
        item.operator = 'AND'
      } else {
        item.operator = 'NOT'
      }
      this.getSearchResultsAndCount();
    },
    simpleSearchOptionChange(event, item, itemIndex) {           
      if(this.$store.state.treeViewSearch.treeViewList.indexOf(event) > -1) {
        this.$store.state.treeViewSearch.currentTreeViewSelected = event;
        this.$store.state.advanceSearch.currentItemIndex = itemIndex;
      }
      this.simpleSearchOptionValue = event;
      item.advanceSearchSelectedValue = null;
      if (this.$store.state.simpleSearch.simpleSearchRowData.length == 1) {
        if (
          item.advanceSearch.autoComplete.length == 0 &&
          !item.fileData &&
          item.simpleSearchAutoComplete.length == 0
        ) {
          return;
        }
        this.getInitalCounts();
        //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
        for (let i of this.$store.state.simpleSearch.simpleSearchOptions) {
          if(i.value == 'ActivityTypes' || i.value == 'EndpointTreeView') {
            i.isDisabled = true;
          } else {
            i.isDisabled = false;
          }
        }
      } else {
        for (let item of this.$store.state.simpleSearch.simpleSearchOptions) {
          if (this.getAllSelectedOptionValues.indexOf(item.value) == -1) {
            this.$store.state.simpleSearch.simpleSearchRowData.forEach(item => {
              if (item.simpleSearchAutoComplete.length > 0) {
                item.isDisabled = false;
              }
            });
          }
        }
      }

      this.$store.state.simpleSearch.selectedValue = event;

      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "advanceSearch"
      ]["autoComplete"] = [];
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "simpleSearchAutoComplete"
      ] = [];
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "disableFields"
      ] = false;
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "fileData"
      ] = [];
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "fileName"
      ] = "";
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "advanceSearch"
      ]["structureInputField"] = [];
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "isStructureAdvanceSearch"
      ] = false;

      let disableAdvanceSearchFields = ["Assay","CompoundCategories","ClinicalPhases","ActivityMechanism","TargetTreeView","IndicationTreeView","EndpointTreeView","BibliographyTreeView","AssayMethodNameTreeView","ToxicityTreeView","PharmacokineticsTreeView","ClinicalPhase","PathwaysTreeView","TaxonomyTreeView","CompoundMolecule"]
      if (disableAdvanceSearchFields.indexOf(event) > -1 ) {
        this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
          "disableAdvanceSearchButton"
        ] = true;
      } else {
        this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
          "disableAdvanceSearchButton"
        ] = false;
      }

      delete this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]['treeViewSearch']
      this.$store.state.treeViewSearch = JSON.parse(glb__initialTreeViewSearchState);
      if(event.indexOf('TreeView') > -1) {
        this.$store.state.treeViewSearch.currentTreeViewSelected = event
      }

       // for setting aelected label start
       this.setAndShowSearchSelectedlabel(item, itemIndex);
       // for setting selected label end
       
      if (item.simpleSearchAutoComplete.length > 0) {
        this.resetTargetSearch(item, itemIndex);
        this.getSearchResultsAndCount();
      }
    },
    resetTargetSearch(item, itemIndex) {
      this.$store.state.simpleSearch.simpleSearchRowData[
        itemIndex
      ].advanceSearch = { autoComplete: [] };
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex].fileName =
        "";
      this.$store.state.simpleSearch.simpleSearchRowData[
        itemIndex
      ].fileData = [];

      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex].fileName =
        "";
      this.$store.state.simpleSearch.simpleSearchRowData[
        itemIndex
      ].advanceSearchSelectedValue = null;
      if (this.$store.state.simpleSearch.simpleSearchRowData.length == 0) {
        this.getInitalCounts();
      } else {
        this.getSearchResultsAndCount();
      }
      //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");

      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "hasValue"
      ] = false;
    },
    openAdvanceSearchPopup(item, itemIndex) {
      this.openAdvSearch = true;
      if (this.$store.state.simpleSearch.selectedValue) {
        this.$store.state.advanceSearch.toggleAdvanceSearchModal = true;
      }
      this.$store.state.advanceSearch.currentItem = JSON.parse(
        JSON.stringify(item)
      );
      this.$store.state.advanceSearch.currentItemIndex = itemIndex;
    },
    getSimpleSearchOptions(enteredVal) {
      let parms = {};
      parms.currentSelectedValue = this.currentSelectedValue;
      parms.enteredValue = enteredVal;
    },
    autoCompleteChange(event, item, itemIndex) {
      this.curSimpleSearchItem = item;

      let tempData = this.$store.state.simpleSearch.simpleSearchRowData[
        itemIndex
      ];
      this.$set(
        this.$store.state.simpleSearch.simpleSearchRowData,
        itemIndex,
        Object.assign(tempData, { simpleSearchAutoComplete: event })
      );

      // don't hit api if there are no values in autocomplete and there is only one row
      if (
        event.length == 0 &&
        this.$store.state.simpleSearch.simpleSearchRowData.length == 1
      ) { 
        console.log('initial counts')
        this.getInitalCounts();
        //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
        return;
      } else {
        console.log('update counts')
        // update all counts.
        if (
          this.$store.state.simpleSearch.simpleSearchRowData[itemIndex]
            .simpleSearchAutoComplete.length > 0 ||
          this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
            "advanceSearch"
          ]["autoComplete"].length > 0
        ) {
          this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
            "hasValue"
          ] = true;
        }

        this.getSearchResultsAndCount();
      }
      if (event.length == 0) {
        this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
          "hasValue"
        ] = false;
      }
    },
    getSearchResultsAndCount: Quasar.utils.debounce(function() {
      this.$store.commit("simpleSearch/GET_SEARCH_FINAL_PAYLOAD");
      let postParam = [];
      let duplicatePostParam = JSON.parse(
        JSON.stringify(this.$store.state.simpleSearch.searchPostObj)
      );
      if (duplicatePostParam.length > 0) {
        postParam = duplicatePostParam.filter(
          val =>
            val.sourceOption != "" &&
            val.sourceOption != this.$store.state.simpleSearch.deletedRowData
        );
        // don't send duplicate requests with same payload
        if (
          JSON.stringify(this.postParam) == JSON.stringify(this.prevPostReq)
        ) {
          return;
        }
        console.log('postParam ', postParam)
        this.showLoading = true;
        this.prevPostReq = duplicatePostParam;
        this.$store
          .dispatch("simpleSearch/GET_SEARCH_RESULTS_AND_COUNT", postParam)
          .then(response => {
            if (response.status == 200) {
              this.$store.commit(
                "simpleSearch/SET_SEARCH_COUNT",
                response.data
              );
              this.saveStoreData();
            }
            this.showLoading = false;
          })
          .catch(e => {
            console.log(e);
            this.showLoading = false;
          });
      } else {
        this.getInitalCounts();
        //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
        this.saveStoreData();
      }
    }, 300),

    //Add new row when we click on plus icon
    addNewRow(item, itemIndex) {
      // this.$store.state.simpleSearch.rowCount = itemIndex +1;
      this.$store.state.advanceSearch.currentItem = item;
      // this.$store.state.advanceSearch.currentItemIndex = itemIndex + 1;
      this.$store.state.advanceSearch.currentItemIndex = itemIndex;
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "disableAllFields"
      ] = true;
      this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
        "disableSimpleSearchSelect"
      ] = true;

      let fil = this.$store.state.simpleSearch.simpleSearchOptions.filter(
        p => p.value == item.simpleSearchSelectedValue
      );
      if (fil.length) {
        for (let i of fil) {
          i["isDisabled"] = true;
        }
      }
 
      this.$store.state.simpleSearch.simpleSearchRowData.push(
        JSON.parse(
          JSON.stringify(this.$store.state.simpleSearch.simpleSearchRow)
        )
      );

      // for setting aelected label start
      this.setAndShowSearchSelectedlabel(item, itemIndex);
      // for setting selected label end
    
    // DISBALED ACTIVE TYPE FROM INPUT UNTILL SELECT ANY INPUT START
 
        for (let activeItemEnable of this.$store.state.simpleSearch.simpleSearchOptions) {
          if (activeItemEnable.value == 'ActivityTypes' || activeItemEnable.value == 'EndpointTreeView') {
            activeItemEnable.isDisabled = false;
          }
        }
     
    // DISBALED ACTIVE TYPE FROM INPUT UNTILL SELECT ANY INPUT END

    // DISBALED TYPE IF ALREADY SELECTED START     
    this.$store.state.simpleSearch.simpleSearchOptions.filter((p, i) => {
      this.getAllSelectedOptionValues.filter((rd, ri) => {
      if(rd == 'ActivityTypes' || rd == 'EndpointTreeView') {
          if(p.value == 'ActivityTypes' || p.value == 'EndpointTreeView') {
              p.isDisabled = true;
          }
      } else if(rd == 'Indication' || rd == 'IndicationTreeView') {
          if(p.value == 'Indication' || p.value == 'IndicationTreeView') {
               p.isDisabled = true;
          }
      } else if(rd == 'Target' || rd == 'TargetTreeView') {
          if(p.value == 'Target' || p.value == 'TargetTreeView') {
              p.isDisabled = true;
          }
      } 
          if(rd && p.value.indexOf(rd) > -1) {
            console.log(p.value + '----' + rd);
             p.isDisabled = true;
          }
       });
     });
    // DISBALED TYPE IF ALREADY SELECTED END

      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]["showDelete"] = true;
      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]["disableAllFields"] = true;
      this.$store.state.simpleSearch.deletedRowData = "";
    },
    deleteRowData(item, itemIndex) {
      this.$store.state.simpleSearch.simpleSearchRowData.splice(itemIndex, 1);

      this.$store.state.simpleSearch.deletedRowData =
        item.simpleSearchSelectedValue;

    // let fil = this.$store.state.simpleSearch.simpleSearchOptions.filter(
    //   p => p.value == item.simpleSearchSelectedValue
    // );

    // if (fil.length) {
    //   fil[0]["isDisabled"] = false;
    // }
    // DELETE ROWS UPDATED START
      let fil = [];
      let rd =  item.simpleSearchSelectedValue;
      this.$store.state.simpleSearch.simpleSearchOptions.filter(
        p =>  {
          if(rd == 'ActivityTypes' || rd == 'EndpointTreeView') {
              if(p.value == 'ActivityTypes' || p.value == 'EndpointTreeView') {
                fil.push(p);
              }
          } else if(rd == 'Indication' || rd == 'IndicationTreeView') {
              if(p.value == 'Indication' || p.value == 'IndicationTreeView') {
                fil.push(p);
              }
          } else if(rd == 'Target' || rd == 'TargetTreeView') {
              if(p.value == 'Target' || p.value == 'TargetTreeView') {
                fil.push(p);
              }
          } else if(p.value == item.simpleSearchSelectedValue) {
            fil = [];
            fil.push(p);
          }
        }
      );
      if (fil.length) {
        fil.filter((v,i) => {
          fil[i]["isDisabled"] = false;
        });
        
      }
   
      if(this.$store.state.simpleSearch.simpleSearchRowData.length == 1) {
        for (let item of this.$store.state.simpleSearch.simpleSearchOptions) { 
             if (item.value == "ActivityTypes" || item.value == "EndpointTreeView") {
              item.isDisabled = true;
              }
            if (item.value == "Indication" || item.value == "IndicationTreeView") {
              item.isDisabled = false;
              }
            if (item.value == "Target" || item.value == "TargetTreeView") {
              item.isDisabled = false;
              }
          }
     }
      // DELETE ROWS UPDATED START END
      if (
        itemIndex > 0 &&
        this.$store.state.simpleSearch.simpleSearchRowData.length == itemIndex
      ) {
        let ind = itemIndex - 1;

        this.$store.state.simpleSearch.simpleSearchRowData[ind][
          "disableAllFields"
        ] = false;
        this.$store.state.simpleSearch.simpleSearchRowData[ind][
          "disableSimpleSearchSelect"
        ] = false;
        // for enable activity types enable option start
          if(this.$store.state.simpleSearch.simpleSearchRowData[ind][
            "simpleSearchSelectedValue"
          ] == 'ActivityTypes') {
            for (let item of this.$store.state.simpleSearch.simpleSearchOptions) { 
              if (item.value == "EndpointTreeView") {
              item.isDisabled = false;
              return
              }
            }
          }
          if(this.$store.state.simpleSearch.simpleSearchRowData[ind][
            "simpleSearchSelectedValue"
          ] == 'EndpointTreeView') {
            for (let item of this.$store.state.simpleSearch.simpleSearchOptions) { 
              if (item.value == "ActivityTypes") {
              item.isDisabled = false;
              return
              }
          }
        }
        // for enable activity types enable option end
      }

      this.getSearchResultsAndCount();
    },
    deleteRow(item, itemIndex) {
      this.handleResetAndDelete(item, itemIndex, "delete");
    },
    deleteOnConfirm() {
      /* if row deleted and binding is selected show alert */
      this.showAssayBindingDeleteAlert = false;
      let tempRowData = JSON.parse(
        JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData)
      );
      for (const [
        itemIndex,
        item
      ] of this.$store.state.simpleSearch.simpleSearchRowData.entries()) {
        if (
          item.simpleSearchSelectedValue &&
          item.simpleSearchSelectedValue != "Assay" &&
          item.hasValue
        ) {
          tempRowData.splice(itemIndex, 1);

          this.$store.state.simpleSearch.deletedRowData =
            item.simpleSearchSelectedValue;
          let fil = this.$store.state.simpleSearch.simpleSearchOptions.filter(
            p => p.value == item.simpleSearchSelectedValue
          );

          if (fil.length && item.hasValue) {
            fil[0]["isDisabled"] = false;
          }
        } else if (item.simpleSearchSelectedValue == "Assay") {
          let fil = this.$store.state.simpleSearch.simpleSearchOptions.filter(
            p => p.value == item.simpleSearchSelectedValue
          );

          if (fil.length && !item.hasValue) {
            fil[0]["isDisabled"] = false;
          }
          for (let autoRef of this.$refs.simpleAutocomplete) {
            if (autoRef.$props.selectedValue == "Assay") {
              autoRef.resetAutoCompleteValues();
              item.hasValue = false;
              item.simpleSearchAutoComplete = [];
            }
          }
        }
      }
      let lastValue = tempRowData[tempRowData.length - 1];
      lastValue["disableSimpleSearchSelect"] = false;
      lastValue["disableAllFields"] = lastValue.hasValue ? false : true;
      for (let i of tempRowData) {
        i.hasValue = false;
        i.simpleSearchAutoComplete = [];
      }
      this.$store.state.simpleSearch.simpleSearchRowData = JSON.parse(
        JSON.stringify(tempRowData)
      );

      if (this.$store.state.simpleSearch.simpleSearchRowData.length > 1) {
        this.getSearchResultsAndCount();
      }
    },
    navigateToVisualization() {
      this.showLoading = true;
      // clear visualization data before navigating
      localStorage.removeItem("visualizationData");

      store.dispatch("simpleSearch/FETCH_CHART_DATA").then(response => {
        if (response.status == 200) {
          this.showLoading = false;
          window.location.href =
            window.config.contextPath +
            "/visualization/" +
            window.config.userSessionId;
        }
      });
    },
    onSubmit() {
      let error = this.checkProperties();
      if (!error.msg) {
        this.$store.state.simpleSearch.enableDashboardOnSubmit = true;
        this.saveStoreData();

        this.navigateToVisualization();
      } else {
        this.$q.notify({
          message: error.msg,
          position: "top",
          color: "deep-orange-5",
          timeout: 2000
        });
      }
    },
    saveStoreData() {
      let postData = {
        advanceSearch: JSON.parse(
          JSON.stringify(this.$store.state.advanceSearch)
        ),
        shared: JSON.parse(JSON.stringify(this.$store.state.shared)),
        simpleSearch: JSON.parse(
          JSON.stringify(this.$store.state.simpleSearch)
        ),
        structureSearch: JSON.parse(
          JSON.stringify(this.$store.state.structureSearch)
        ),
        user: JSON.parse(JSON.stringify(this.$store.state.user))
      };
      localStorage.setItem("simpleSearchStore", JSON.stringify(postData));
    },
    checkProperties() {
      let error = { msg: "" };

      for (let item in this.$store.state.simpleSearch.searchCount) {
        if (!this.$store.state.simpleSearch.searchCount[item]) {
          error.msg = "No data to generate charts";
          break;
        }
      }

      for (let item in this.$store.state.simpleSearch.simpleSearchRowData) {
        if (
          !this.$store.state.simpleSearch.simpleSearchRowData[item][
            "simpleSearchSelectedValue"
          ]
        ) {
          error.msg = "Please select an option";
          break;
        }

        if (
          !(
            this.$store.state.simpleSearch.simpleSearchRowData[item][
              "simpleSearchAutoComplete"
            ]["length"] > 0 ||
            this.$store.state.simpleSearch.simpleSearchRowData[item][
              "fileData"
            ]["length"] > 0 ||
            this.$store.state.simpleSearch.simpleSearchRowData[item][
              "advanceSearch"
            ]["autoComplete"]["length"] > 0 ||
            this.$store.state.simpleSearch.simpleSearchRowData[item][
              "isStructureAdvanceSearch"
            ] ||
            (this.$store.state.simpleSearch.simpleSearchRowData[item][
              "treeViewSearch"
            ] && this.$store.state.simpleSearch.simpleSearchRowData[item][
              "treeViewSearch"
            ]["allSelectedNodes"]["length"] > 0 )
          )
        ) {
          error.msg = "Please select a value";
          break;
        }
      }

      return error;
    },
    disableAdd(currItem, currItemIndex) {
      if (
        (currItem.disableAllFields ||
          (currItem.simpleSearchAutoComplete.length ||
            currItem.advanceSearch.autoComplete.length ||
            currItem.fileData.length ||
            currItem.treeViewSearch)) &&
        currItem.hasValue
      ) {
        return false;
      } else if (currItem.isStructureAdvanceSearch) {
        return false;
      } else {
        return true;
      }
    },
    getTreeViewData(item, itemIndex,type) {
    //   this.$store.state.advanceSearch.currentItem = JSON.parse(
    //     JSON.stringify(item)
    //   );
    //   this.$store.state.advanceSearch.currentItemIndex = itemIndex;

    //  this.$store.state.treeViewSearch.currentTreeViewSelected = item.simpleSearchSelectedValue;
   
    if(type == 'reset') {
      this.$store.state.advanceSearch.currentItemIndex = itemIndex;
      this.simpleSearchOptionChange(item.simpleSearchSelectedValue,item, itemIndex);
    } else {
      this.$store.state.advanceSearch.currentItemIndex = itemIndex;
      this.$store.state.advanceSearch.currentItem = JSON.parse(
        JSON.stringify(item)
      );
     this.$store.state.treeViewSearch.currentTreeViewSelected = item.simpleSearchSelectedValue;
    }
    
    },
    toggleMenu(isHide = false) {
      if (isHide) {
        this.$store.state.simpleSearch.showUserProfileMenu = false
      } else {
        if (this.$store.state.simpleSearch.showUserProfileMenu) {
          this.$store.state.simpleSearch.showUserProfileMenu = false
        } else {
          this.$store.state.simpleSearch.showUserProfileMenu = true
        }
      }      
    },
    setAndShowSearchSelectedlabel(item, itemIndex) {
      for (let searchOptionsLabel of this.$store.state.simpleSearch.simpleSearchOptions) {
        if(searchOptionsLabel.value == item.simpleSearchSelectedValue) {
          this.$store.state.simpleSearch.simpleSearchRowData[itemIndex][
            "simpleSearchSelectedLabel"
          ] = searchOptionsLabel.label;
        }

      }
    }
  },
  computed: {
    getAllSelectedOptionValues() {

      return this.$store.getters["simpleSearch/getAllSelectedOptionValues"];
    },
    validateSearchCount() {
      return this.$store.getters["simpleSearch/validateSearchCount"];
    },
    disableAddingNewRow() {
      let filt = this.$store.state.simpleSearch.simpleSearchOptions.filter(
        p => p.isDisabled == false
      );
      if (filt.length == 1) return false;
      return true;
    }
  },
  watch: {
    simpleSearchRowDataChange(val) {},

    getFileName(val) {
      this.fileName = val;
    },
    getAutocompleteFieldValue(val) {
      this.getSimpleSearchOptions(val);
      this.$store.state.simpleSearch.simpleSearchAutoOptions = [];
    },
    selectedValuesChange: function() {

      return this.$store.state.advanceSearch.selectedAutoCompleteValues;
    },
    getSelectedValue(newValue, oldValue) {
      if (newValue == "Target") {
        this.currentSelectedValue = newValue;
      } else {
        this.$store.state.simpleSearch.simpleSearchAutoOptions = [];
      }
      this.$store.state.advanceSearch.showDisabledProps = false;

       
    }
  }
});

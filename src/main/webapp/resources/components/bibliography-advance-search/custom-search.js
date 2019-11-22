Vue.component("custom-search", {
  template: `
  <div class="tab-pane bibliography-custom-search active fade show" id="m_tabs_1_2" role="tabpanel">
      <div>
        <div class="row justify-start q-pa-sm q-mt-lg">
          <div class="col-sm-12 col-md-12">
          <div class="row items-center q-pa-sm" v-for="(customSearchRow, index) in $store.state.bibliographySearch.customSearch.totalRowData" :key="customSearchRow.year + index">
              <!-- DATASOURCE SELECTION OPTIONS -->
              <div class="col-2" style="vertical-align:
                baseline; top:-11px; padding-left: 0px;">
                  <b-select class="form-control shadow-efct-stl"
                  style="height:45px;"
                    v-model="customSearchRow.selectedDataSource"
                    @change="selectionChangeEvent($event, index)"
                    :options="$store.state.bibliographySearch.customSearch.dataSource"
                    >
                  </b-select>
              </div>
              <!-- PATENT FIELDS -->          
                <div class="col-3" v-if="customSearchRow.selectedDataSource == 'p'">
                  <multi-select
                    class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                    style="margin-top:-12px;"
                    :selectValue="customSearchRow.countryCode?customSearchRow.countryCode:[]"
                    @change-event="userSelectionChange($event, index, 'countryCode', 'patent')"
                    :optionsList="totalPatentCountryCodeOptionsList"
                    placeholder="Select Country Code"
                    >
                  </multi-select>
                </div>
                <div class="col-3" v-if="customSearchRow.selectedDataSource == 'p'">             
                <multi-select     
                  class="form-control shadow-efct-stl inpt-hgt-fld custom-select"  
                  style="margin-top:-12px;"      
                  :selectValue="customSearchRow.year ? customSearchRow.year :[]"
                  @change-event="userSelectionChange($event, index, 'year', 'patent')"
                  :disable="customSearchRow.countryCode && customSearchRow.countryCode.length ? false : true"
                  :readonly="customSearchRow.countryCode && customSearchRow.countryCode.length ? false : true"
                  :optionsList="customSearchRow.totalPatentYearOptionsList"
                  placeholder="Select Year"
                  >
                </multi-select>
                </div>
                <div class="col-3" v-if="customSearchRow.selectedDataSource == 'p'">
                <multi-select
                    class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                    style="margin-top:-12px;"
                    :selectValue="customSearchRow.patentNo?customSearchRow.patentNo:[]"
                    @change-event="userSelectionChange($event, index, 'patentNo', 'patent')"
                    :disable="customSearchRow.year && customSearchRow.year.length ? false : true"
                    :readonly="customSearchRow.year && customSearchRow.year.length ? false : true"
                    :optionsList="customSearchRow.totalPatentNoOptionsList"
                    placeholder="Select Patent No."
                  >
                </multi-select>
                </div>          
              <!-- JOURNAL FIELDS -->
              <div class="col-9 row" v-if="customSearchRow.selectedDataSource == 'j'">
                <div class="col-4">
              <q-select
                  class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                  style="margin-top:-12px; height:auto;"
                  dense
                  options-dense
                  emit-value                
                  @input="journalIsoNoChange($event, index)"
                  placeholder="Enter Journal ISO No."
                  v-model="customSearchRow.journalName"
                  use-input
                  fill-input
                  input-debounce="0"
                  :options="totalJournalIsoNoOptionsList"
                  @filter="singleAutocompleteResults"
                  :key="'journalName' + index"
                >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey">
                      No results
                    </q-item-section>
                  </q-item>
                </template>
                </q-select>
                </div>
                <div class="col-2" style="padding-right:0px;">
                <multi-select
                class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                style="margin-top:-12px;"
                  :selectValue="customSearchRow.year ? customSearchRow.year : []"
                  @change-event="userSelectionChange($event, index, 'year', 'journal')"
                  :disable="customSearchRow.journalName ? false : true"
                  :readonly="customSearchRow.journalName ? false : true"
                  :optionsList="customSearchRow.totalJournalYearOptionsList"
                  placeholder="Year"
                  :key="'year' + index"
                  >
                </multi-select>
                </div>
                <div class="col-2" style="padding-right:0px;">
                <multi-select
                class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                style="margin-top:-12px;"
                  :selectValue="customSearchRow.volume ? customSearchRow.volume: []"
                  @change-event="userSelectionChange($event, index, 'volume', 'journal')"
                  :disable="customSearchRow.year && customSearchRow.year.length && customSearchRow.year.length < 2 ? false : true"
                  :readonly="customSearchRow.year && customSearchRow.year.length && customSearchRow.year.length < 2 ? false : true"
                  :optionsList="customSearchRow.totalJournalVolumeOptionsList"
                  placeholder="Volume"
                  :key="'volume' + index"
                  >
                </multi-select>
                </div>
                <div class="col-2" style="padding-right:0px;">
                  <multi-select
                  class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                  style="margin-top:-12px;"
                    :selectValue="customSearchRow.issue ? customSearchRow.issue : []"
                    @change-event="userSelectionChange($event, index, 'issue', 'journal')"
                    :disable="customSearchRow.volume && customSearchRow.volume.length && customSearchRow.volume.length < 2? false : true"
                    :readonly="customSearchRow.volume && customSearchRow.volume.length && customSearchRow.volume.length < 2 ? false : true"
                    :optionsList="customSearchRow.totalJournalIssueOptionsList"
                    placeholder="Issue"
                    :key="'issue' + index"
                    >
                  </multi-select>
                </div>
                <div class="col-2" style="padding-right:0px;">
                  <multi-select
                  class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                  style="margin-top:-12px;"
                    :selectValue="customSearchRow.pageNo ? customSearchRow.pageNo :[]"
                    @change-event="userSelectionChange($event, index, 'pageNo', 'journal')"
                    :disable="customSearchRow.issue && customSearchRow.issue.length && customSearchRow.issue.length < 2? false : true"
                    :readonly="customSearchRow.issue && customSearchRow.issue.length && customSearchRow.issue.length < 2 ? false : true"
                    :optionsList="customSearchRow.totalJournalPageNoOptionsList"
                    placeholder="Page No."
                    :key="'page' + index"
                    >
                  </multi-select>
                </div>
              </div>
              <div class="col-1">
                <!-- Add New Row
                :disabled="($store.state.bibliographySearch.customSearch.totalRowData.length == 0 ) ? true : true" 
                -->
                <span
                  @click="addNewRow(index)"
                  :class="{'pointerNone': disableRowAdd(index)}"
                  class="m-portlet__nav-link m-portlet__nav-link--icon">
                    <i class="fa fa-plus-circle q-pa-sm"></i>
                </span>
                <span
                  @click="deleteRow(index)"
                  class="m-portlet__nav-link m-portlet__nav-link--icon" v-if="disableRowDelete" >
                    <i class="fa fa-trash q-pa-sm"></i>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer" style="margin: 25px -25px -25px -25px;">
        <button
          type="button"
          class="btn btn-danger btn-sm"
          :disabled="disableClear"
            @click="resetChanges()"
        >Clear</button>
        <button type="button" :disabled="disableAdd" class="btn btn-excelra btn-sm" @click="confirm()">Add</button>
      </div>
      <app-loader v-if="showLoading"></app-loader>
  </div>
  </div>
  `,
  mounted() {
    let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
    let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
      curItemIndex
    ]["advanceSearch"];

    if(this.$store.state.simpleSearch.simpleSearchRowData[
      curItemIndex
    ]["bibliographySearch"]['customSearch']['totalRowData']) {

      this.$store.state.bibliographySearch.customSearch = JSON.parse(JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["bibliographySearch"]['customSearch']));
    }
  },
  data() {
    return {
      temptotalJournalIsoNoOptionsList: [],
      totalJournalIsoNoOptionsList: [],     
      totalPatentCountryCodeOptionsList: [
        {"label": "US","value": "US"},
        {"label": "EP","value": "EP"},
        {"label": "JP","value": "JP"},
        {"label": "WO","value": "WO"},
        {"label": "GB","value": "GB"},
      ],
      showLoading: false
    };
  },
  methods: {
    resetChanges(event) {     
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      // add initial state to the main object.
      this.$store.state.bibliographySearch.customSearch.totalRowData = [JSON.parse(JSON.stringify(this.$store.state.bibliographySearch.customSearch.initialJournalRowData))]
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["advanceSearch"]['bibliographyRow'] = []
     
      
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['bibliographySearch']['customSearch'] =  JSON.parse(glb__initialBibliographySearchState).customSearch

      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearch']['autoComplete'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValueName'] = ''

      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['bibliographySearch'] = JSON.parse(glb__initialBibliographySearchState)    
      
      if (
        this.$store.state.simpleSearch.simpleSearchRowData.length == 1 &&
        this.$store.state.simpleSearch.simpleSearchRowData[0]
          .simpleSearchAutoComplete.length == 0
      ) {
        this.getInitalCounts();
      } else {
        this.getSearchResultsAndCount();
      }
    },
   
    confirm() {
      this.$bvModal
        .msgBoxConfirm("Are you sure?", {
          centered: true
        })
        .then(value => {
          if (!value) {
            return;
          }
          
          let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
          let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"];
          
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"]['bibliographyRow'] = this.$store.state.bibliographySearch.customSearch.totalRowData;
         
          if(this.$store.state.bibliographySearch.customSearch.totalRowData[0]['journalName']) {
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]['advanceSearchSelectedValueName'] = this.$store.state.bibliographySearch.customSearch.totalRowData[0]['journalName'];
          }else{
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]['advanceSearchSelectedValueName'] = this.$store.state.bibliographySearch.customSearch.totalRowData[0]['countryCode'];
          }
          // added for showing biblio graphy count 
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"]['autoComplete'] = this.$store.state.bibliographySearch.customSearch.totalRowData;
        // added for showing biblio graphy count END
        
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['bibliographySearch'] = JSON.parse(JSON.stringify(this.$store.state.bibliographySearch));

          // added for display add new row button in simple search page start
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["hasValue"] = true;
          // added for display add new row button in simple search page end
           
          this.getSearchResultsAndCount();
          this.closePopup();
        })
        .catch(err => {
          console.log(err)
          // An error occurred
        });
    },
    disableRowAdd(rowIndex) {
      if(this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['selectedDataSource'] == 'j' &&
      this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['journalName'] == null
      ) {
        return true;
      }

      if(this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['selectedDataSource'] == 'p' &&
      this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['countryCode'] &&
      this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['countryCode']['length'] == 0
      ) {
        return true;
      }

      return false
    },
    userSelectionChange(userSelectedValue, rowIndex, key, identifier) {
      if(userSelectedValue) {
        let payload = this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]
        if(identifier == 'journal') {
          if( key == 'year') {
            payload.volume = []
            payload.issue = []
            payload.pageNo = []
          }else if( key == 'volume') {
            payload.issue = []
            payload.pageNo = []
          }else if( key == 'issue') {
            payload.pageNo = []
          }
        }else if(identifier == 'patent') {
          if(key == 'countryCode') {
            payload.year = []
            payload.patentNo = []
          }else if(key == 'year') {
            payload.patentNo = []
          }
        }
        this.$set(this.$store.state.bibliographySearch.customSearch.totalRowData,rowIndex , Object.assign(
          this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex],
          payload))
        this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex][key] = userSelectedValue
        this.$store
        .dispatch("shared/POST_SINGLE_AUTOCOMPLETE_RESULTS", payload)
        .then(response => {
          if(response.data) {
            if(identifier == 'journal') {
              if(key == 'year') {
                this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex].totalJournalVolumeOptionsList = response.data;
              }else if(key == 'volume') {
                this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex].totalJournalIssueOptionsList = response.data;
              }else if(key == 'issue') {
                this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex].totalJournalPageNoOptionsList = response.data;
              }
            }else if(identifier == 'patent') {
              if(key == 'countryCode') {
                this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex].totalPatentYearOptionsList = response.data
              }else if(key == 'year') {
                this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex].totalPatentNoOptionsList = response.data
              }
            }
          }
        });
      }
    },
    journalIsoNoChange(userSelectedValue, rowIndex) {
      if(userSelectedValue) {
        this.$set(this.$store.state.bibliographySearch.customSearch.totalRowData,rowIndex , Object.assign(
          this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex],
          {
            year: [],
            volume: [],
            issue: [],
            pageNo: []
        }))
        let payload = this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]
        this.$store
        .dispatch("shared/POST_SINGLE_AUTOCOMPLETE_RESULTS", payload)
        .then(response => {
          this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex].totalJournalYearOptionsList = response.data
        });
      }
    },
    singleAutocompleteResults(val, update, abort) {
      update(() => {
        if(this.temptotalJournalIsoNoOptionsList.length == 0) {
          let payload = {
            searchTerm: '',
            selectedOptionValue: 'Journal',
            searchType: 'CUSTOM_SEARCH'
          };
          this.$store
          .dispatch("shared/GET_SINGLE_AUTOCOMPLETE_RESULTS", payload)
          .then(response => {
            this.temptotalJournalIsoNoOptionsList = JSON.parse(JSON.stringify(response.data));
            this.totalJournalIsoNoOptionsList = response.data.splice(0, 10)
          });
        }else if(val){
          const needle = val.toLowerCase()
          this.totalJournalIsoNoOptionsList = this.temptotalJournalIsoNoOptionsList.filter(v => v.label.toLowerCase().indexOf(needle) == 0)
        }
      })
    },
    deleteRow(index){
      this.$store.state.bibliographySearch.customSearch.totalRowData.splice(index, 1)
      if(this.$store.state.bibliographySearch.customSearch.totalRowData.length == 1) {
        
      }
    },
    addNewRow(index){
      if (this.$store.state.bibliographySearch.customSearch.totalRowData[index].journalName == null && this.$store.state.bibliographySearch.customSearch.totalRowData[index].countryCode.length == 0) {
        return
      }
      let newJournalRowData = Object.assign({},this.$store.state.bibliographySearch.customSearch.initialJournalRowData);
      this.$store.state.bibliographySearch.customSearch.totalRowData.push(newJournalRowData)
      
    },
    selectionChangeEvent(event, index){
      if(event == 'p') {
        this.$set(this.$store.state.bibliographySearch.customSearch.totalRowData, index, Object.assign({},this.$store.state.bibliographySearch.customSearch.initialPatentRowData))
        // on change get country code list
      }else if(event == 'j') {
        this.$set(this.$store.state.bibliographySearch.customSearch.totalRowData, index, Object.assign({},this.$store.state.bibliographySearch.customSearch.initialJournalRowData))
      }
    },
    getInitalCounts() {
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_DEFAULT_COUNTS")
        .then(response => {
          if (response.status == 200) {
            this.$store.state.simpleSearch.searchCount = response.data;
          }
        });
    },
    closePopup() {
      this.$emit("adv-popup-closed", true);
    },
    
    getSearchResultsAndCount() {
      this.$store.state.advanceSearch.showCountsLoader = true;
      let postParam = [];
      this.$store.commit("simpleSearch/GET_SEARCH_FINAL_PAYLOAD");
      postParam = this.$store.state.simpleSearch.searchPostObj;
      this.showLoading = true;
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_RESULTS_AND_COUNT", postParam)
        .then(res => {
          this.showLoading = false;
          this.searchCountData = res.data;
          this.$store.commit("simpleSearch/SET_SEARCH_COUNT", res.data);
          this.$store.state.advanceSearch.showCountsLoader = false;
          this.saveStoreData();
          // this.closePopup();
        })
        .catch(e => {
          this.$store.state.advanceSearch.showCountsLoader = false;
          return e.response;
        });
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
  },
  computed: {
    disableRowDelete(){
      if(this.$store.state.bibliographySearch.customSearch.totalRowData.length == 1) {
        return false
      }
      return true;
    },
    disableAdd(){
      let disable = false
      this.$store.state.bibliographySearch.customSearch.totalRowData.forEach(customItem => {
        if(customItem.selectedDataSource == 'j' && customItem.journalName == null) {
          disable = true
        }else if(customItem.selectedDataSource == 'p' && customItem.countryCode.length == 0){
          disable = true
        }
      })
      return disable;
    },
    disableClear() {
      let disable = false
      this.$store.state.bibliographySearch.customSearch.totalRowData.forEach(customItem => {
        if(customItem.selectedDataSource == 'j' && customItem.journalName == null) {
          disable = true
        }else if(customItem.selectedDataSource == 'p' && customItem.countryCode.length == 0){
          disable = true
        }
      })
      return disable;
      // return false;
    },
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    }
  }
});

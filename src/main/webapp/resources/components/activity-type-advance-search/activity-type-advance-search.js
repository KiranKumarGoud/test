Vue.component("activity-type-advance-search", {
  template: `
    <div class="modal-dialog modal-big-lg" role="document">
      <div class="modal-content activity-type-advance-search">
        <div class="modal-header">
          <h5 class="modal-title txt-wht-col" id="exampleModalLabel">Grouped Actions</h5>
          <button @click="closePopup()" type="button" data-dismiss="modal" aria-label="Close"> 
            <span aria-hidden="true"><i class="fa fa-times"></i></span> </button>
        </div>
        <div class="modal-body">
          <div class="m-portlet__body">
            <div class="tab-content">
              <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel"> 
                <!-- Tab 2 Container part -->
                <div>
                  <div class="m-portlet__body  m-portlet__body--no-padding">
                    <div class="col-sm-12 col-md-12">
                    <!--begin:: Widgets/Daily Sales-->
                    <div class="m-widget14" style="padding: 0.75rem;">
                      <div class="m-widget14__header m--margin-bottom-30">
                        <h3 class="m-widget14__title">Activity Type/Endpoints </h3>
                      </div>
                    </div>
                      <div class="row" style="padding-left:24px; padding-bottom: 10px;" v-for="(activityTypeRow, index) in $store.state.activityTypeSearch.totalRowData" :key="index">
                        <!-- ACTIVITY TYPE -->   
                        <div class="col-10 row">                    
                          <div class="col-4">
                            <q-select
                              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                              outlined
                              dense
                              :multiple="false"
                              v-model="activityTypeRow.activityType"
                              use-input
                              fill-input
                              emit-value
                              @input="activityTypeChanged"
                              clearable 
                              :placeholder="!activityTypeRow.activityType ?  'Enter Activity Type' : ''"
                              input-debounce="0"
                              :options="totalActivityTypeOptionsList"
                              @filter="getActivityTypeOptions"
                              :key="'activityType' + index"
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
                          <div class="col-2">
                            <q-select
                              :multiple="false"
                              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                              outlined
                              dense
                              emit-value
                              v-model="activityTypeRow.activityUom"
                              use-input
                              fill-input
                              :disable="activityTypeRow.activityType ? false : true"
                              :readonly="activityTypeRow.activityType ? false : true"
                              clearable 
                              :placeholder="!activityTypeRow.activityUom ?  'Enter Activity Uom' : ''"
                              input-debounce="0"
                              :options="totalActivityUomOptionsList"
                              @filter="getActivityUomOptions"
                              :key="'uom' + index"
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
                          <div class="col-2">
                            <q-select
                              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                              outlined
                              dense
                              emit-value
                              :multiple="false"
                              v-model="activityTypeRow.prefix"
                              use-input
                              fill-input
                              :disable="activityTypeRow.activityUom ? false : true"
                              :readonly="activityTypeRow.activityUom ? false : true"
                              clearable 
                              :placeholder="!activityTypeRow.prefix ?  'Enter Prefix' : ''"
                              input-debounce="0"
                              :options="totalActivityPrefixOptionsList"
                              @filter="getActivityPrefixOptions"
                              :key="'prefix' + index"
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
                          <div class="col-2">
                            <q-input
                              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                              outlined
                              :disable="activityTypeRow.prefix ? false : true"
                              :readonly="activityTypeRow.prefix ? false : true"
                              dense
                              v-model="activityTypeRow.minValue"
                              placeholder="Enter Min Value"
                              :key="'min' + index"
                            />
                          </div>
                          <div class="col-2">
                            <q-input
                              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                              outlined
                              :disable="activityTypeRow.prefix ? false : true"
                              :readonly="activityTypeRow.prefix ? false : true"
                              dense
                              v-model="activityTypeRow.maxValue"
                              placeholder="Enter Max Value"
                              :key="'max' + index"
                            />
                          </div>  
                          </div>                      
                        <div class="col-1">
                          <span
                            @click="addNewRow()"
                            :class="{'pointerNone': disableRowAdd(index)}"
                            class="m-portlet__nav-link m-portlet__nav-link--icon">
                              <i class="fa fa-plus-circle q-pa-sm"></i>
                          </span>
                          <span
                            @click="deleteRow(index)"
                            class="m-portlet__nav-link m-portlet__nav-link--icon" v-if="!disableDelete" >
                              <i class="fa fa-trash q-pa-sm"></i>
                          </span>
                        </div>
                      </div>
                    </div>                             
                  </div>
                </div>            
              </div>
            </div>      
          </div>         
        </div>
        <div class="modal-footer">
        <button
          type="button"
          class="btn btn-danger btn-sm"
          :disabled="disableClear"
          @click="resetChanges()"
          >
          Clear
        </button>
        <button
          type="button"
          :disabled="disableAdd"
          class="btn btn-excelra btn-sm"
          @click="confirm()"
          >
          Add
        </button>
      </div>
  </div>
  <app-loader v-if="showLoading"></app-loader>
</div>
  `,
  mounted() {
    let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
    let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
      curItemIndex
    ]["advanceSearch"];

     if(this.$store.state.simpleSearch.simpleSearchRowData[
      curItemIndex
     ]["activityTypeSearch"]['totalRowData']) {

       this.$store.state.activityTypeSearch = JSON.parse(JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData[
         curItemIndex
     ]["activityTypeSearch"]));
    }
  },
  data() {
    return {
      totalActivityTypeOptionsList: [],
      totalActivityUomOptionsList: [],
      totalActivityPrefixOptionsList: [],
      disableDelete: true,
      showLoading: false,
      currentActivityTypeValue: ''
    };
  },
  methods: {
    activityTypeChanged(event) {
      this.currentActivityTypeValue = event;
    },
    getActivityTypeOptions (val, update, abort) {
      if (val.length < 2) {
        abort()
        return
      }
      update(() => {
        this.$store
          .dispatch("activityTypeSearch/GET_ACTIVITY_OPTIONS_LIST", {type: 'ACTIVITY_TYPE', query: val})
          .then(response => {
            this.totalActivityTypeOptionsList = response.data
          });
      })
    },
    getActivityUomOptions (val, update, abort) {
      
      update(() => {
        this.$store
          .dispatch("activityTypeSearch/GET_ACTIVITY_OPTIONS_LIST", {type: 'ACTIVITY_UOM', query: this.currentActivityTypeValue})
          .then(response => {
            this.totalActivityUomOptionsList = response.data
          });
      })
    },
    getActivityPrefixOptions (val, update, abort) {
      update(() => {
        this.$store
          .dispatch("activityTypeSearch/GET_ACTIVITY_OPTIONS_LIST", {type: 'ACTIVITY_PREFIX', query: val})
          .then(response => {
            this.totalActivityPrefixOptionsList = response.data
          });
      })
    },
    resetChanges(event) {
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      // add initial state to the main object.
      this.$store.state.activityTypeSearch.totalRowData = [JSON.parse(JSON.stringify(this.$store.state.activityTypeSearch.totalRowData))]
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['advanceSearchSelectedValue'] = null
     

      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["advanceSearch"]['activityTypeRow'] = []
     
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['activityTypeSearch'] = JSON.parse(glb__initialActivityTypeSearchState)

      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearch']['autoComplete'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValueName'] = ''

      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['activityTypeSearch'] = JSON.parse(glb__initialActivityTypeSearchState) 
      
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


          console.log('total row data - ', this.$store.state.activityTypeSearch.totalRowData)
          
          let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
          let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"];

          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"]['activityTypeRow'] = this.$store.state.activityTypeSearch.totalRowData;
         
          if(this.$store.state.activityTypeSearch.totalRowData[0]['activityType']) {
            this.$store.state.activityTypeSearch.totalRowData.forEach(element => {  
              console.log(element)         
              this.$store.state.simpleSearch.simpleSearchRowData[
                curItemIndex
              ]['advanceSearchSelectedValue'] = 'ActivityTypes';
            });
          }
          // added for showing source count 
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"]['autoComplete'] = this.$store.state.activityTypeSearch.totalRowData;
        // added for showing source count END
        
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['activityTypeSearch'] = JSON.parse(JSON.stringify(this.$store.state.activityTypeSearch));

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
      // if(this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['selectedDataSource'] == 'j' &&
      // !this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['journalName']
      // ) {
      //   return true;
      // }

      // if(this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['selectedDataSource'] == 'p' &&
      // this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['countryCode'] &&
      // this.$store.state.bibliographySearch.customSearch.totalRowData[rowIndex]['countryCode']['length'] == 0
      // ) {
      //   return true;
      // }

      return false
    },
    userSelectionChange(userSelectedValue, rowIndex, key, identifier) {
      console.log(userSelectedValue)
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
                this.totalJournalVolumeOptionsList = response.data;
              }else if(key == 'volume') {
                this.totalJournalIssueOptionsList = response.data;
              }else if(key == 'issue') {
                this.totalJournalPageNoOptionsList = response.data;
              }
            }else if(identifier == 'patent') {
              if(key == 'countryCode') {
                this.totalPatentYearOptionsList = response.data
              }else if(key == 'year') {
                this.totalPatentNoOptionsList = response.data
              }
            }
          }
        });
      }
    },
   
    singleAutocompleteResults(val, update, abort) {
      update(() => {
        if(this.temptotalJournalIsoNoOptionsList.length == 0) {
          let payload = {}
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
      this.$store.state.activityTypeSearch.totalRowData.splice(index, 1)
      if(this.$store.state.activityTypeSearch.totalRowData.length == 1) {
        this.disableDelete = true
      }
    },
    addNewRow(){
      let newRowData = Object.assign({},this.$store.state.activityTypeSearch.initialRowData);
      this.$store.state.activityTypeSearch.totalRowData.push(newRowData)
      this.disableDelete = false
    },
    selectionChangeEvent(event, index){
      // if(event == 'p') {
      //   this.$set(this.$store.state.bibliographySearch.customSearch.totalRowData, index, Object.assign({},this.$store.state.bibliographySearch.customSearch.initialPatentRowData))
      //   // on change get country code list
      // }else if(event == 'j') {
      //   this.$set(this.$store.state.bibliographySearch.customSearch.totalRowData, index, Object.assign({},this.$store.state.bibliographySearch.customSearch.initialJournalRowData))
      // }
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
      this.$emit("popup-closed", true);
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
    disableAdd(){
      // let disable = false
      // this.$store.state.bibliographySearch.customSearch.totalRowData.forEach(customItem => {
      //   if(customItem.selectedDataSource == 'j' && customItem.journalName == null) {
      //     disable = true
      //   }else if(customItem.selectedDataSource == 'p' && customItem.countryCode.length == 0){
      //     disable = true
      //   }
      // })
      // return disable;
    },
    disableClear() {
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
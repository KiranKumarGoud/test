Vue.component("strain-search", {
  template: `<div class="tab-pane source-strain-search active fade show" id="m_tabs_1_2" role="tabpanel">
    <div>
      <div class="row justify-start q-pa-sm q-mt-lg">
        <div class="col-sm-12 col-md-12">
        <div class="row items-center q-pa-sm" v-for="(strainSearchhRow, index) in $store.state.sourceSearch.strainSearch.totalRowData" :key="strainSearchhRow + index">
            <!-- DATASOURCE SELECTION OPTIONS -->
            <div class="col-3" style="vertical-align:
              baseline; top:0px; padding-left: 15px;">
              <q-select
                :key="'genus' + index"
                class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
                style="width:100%; margin-top: -11px; height: auto;"
                dense
                options-dense
                emit-value
                placeholder="Enter Genus"
                v-model="strainSearchhRow.genusList"
                use-input
                fill-input
                input-debounce="0"
                :options="totalGenusOptionsList"
                @filter="genusSingleAutocompleteResults"
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
            <div class="col-3" style="vertical-align:
              baseline; top:0px; padding-left: 50px;">           
              <q-select
              :key="'species' + index"
              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
              style="width:100%; margin-top: -11px; height: auto;"
              dense
              options-dense
              emit-value
              placeholder="Enter Species"
              v-model="strainSearchhRow.speciesList"
              use-input
              fill-input
              input-debounce="0"
              :options="totalSpeciesOptionsList"
              @filter="speciesSingleAutocompleteResults"
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
            <div class="col-3" style="vertical-align:
              baseline; top:0px; padding-left: 86px;">            
              <q-select
              :key="'strain' + index"
              class="form-control shadow-efct-stl inpt-hgt-fld custom-select"
              style="width:100%; margin-top: -11px; height: auto;"
              dense
              multiple
              options-dense
              emit-value
              placeholder="Enter Strain"
              v-model="strainSearchhRow.strainTypeList"
              use-input
              fill-input
              input-debounce="0"
              :options="totalStrainTypeOptionsList"
              @filter="strainTypeSingleAutocompleteResults"
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
            <!-- PATENT FIELDS -->
           
            <div class="col-1" style="padding-left: 100px;">
              <!-- Add New Row
              :disabled="($store.state.sourceSearch.strainSearch.totalRowData.length == 0 ) ? true : true" 
               -->
              <span
                @click="addNewRow()"
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
    ]["sourceSearch"]['strainSearch']['totalRowData']) {

      this.$store.state.sourceSearch.strainSearch = JSON.parse(JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["sourceSearch"]['strainSearch']));
    }
  },
  data() {
    return {
      temptotalGenusOptionsList: [],
      totalGenusOptionsList: [],
      temptotalSpeciesOptionsList: [],
      totalSpeciesOptionsList: [],
      temptotalStrainTypeOptionsList: [],
      totalStrainTypeOptionsList: [],
      showLoading: false
    };
  },
  methods: {
    resetChanges(event) {
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      // add initial state to the main object.
      this.$store.state.sourceSearch.strainSearch.totalRowData = [JSON.parse(JSON.stringify(this.$store.state.sourceSearch.strainSearch.initialJournalRowData))]
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["advanceSearch"]['sourceRow'] = []        
      
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['sourceSearch']['strainSearch'] =  JSON.parse(glb__initialSourceSearchState).strainSearch

      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearch']['autoComplete'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValueName'] = ''

      this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]['sourceSearch'] = JSON.parse(glb__initialSourceSearchState)

      this.temptotalGenusOptionsList = []
      this.totalGenusOptionsList = []
      this.temptotalSpeciesOptionsList = []
      this.totalSpeciesOptionsList = []
      this.temptotalStrainTypeOptionsList = []
      this.totalStrainTypeOptionsList = []
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
          ]["advanceSearch"]['sourceRow'] = this.$store.state.sourceSearch.strainSearch.totalRowData;
         
          if(this.$store.state.sourceSearch.strainSearch.totalRowData[0]['genusList']) {
            this.$store.state.sourceSearch.strainSearch.totalRowData.forEach(element => {  
              console.log(element)         
              this.$store.state.simpleSearch.simpleSearchRowData[
                curItemIndex
              ]['advanceSearchSelectedValueName'] = element['genusList'];
            });
          }
          // added for showing source count 
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]["advanceSearch"]['autoComplete'] = this.$store.state.sourceSearch.strainSearch.totalRowData;
        // added for showing source count END
        
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['sourceSearch'] = JSON.parse(JSON.stringify(this.$store.state.sourceSearch));
           
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
      if(this.$store.state.sourceSearch.strainSearch.totalRowData[rowIndex]['genusList'].length == 0 && 
      this.$store.state.sourceSearch.strainSearch.totalRowData[rowIndex]['speciesList'].length == 0 &&
      this.$store.state.sourceSearch.strainSearch.totalRowData[rowIndex]['strainTypeList'].length == 0
      ) {
        return true;
      }
      return false
    },
    genusSingleAutocompleteResults(val, update, abort) {      
      update(() => {      
          let payload = {
            searchTerm: val,
            selectedOptionValue: 'Genus',
            searchType: 'STRAIN_SEARCH'
          };
          this.$store
          .dispatch("shared/GET_SINGLE_AUTOCOMPLETE_RESULTS", payload)
          .then(response => {
            this.temptotalGenusOptionsList = JSON.parse(JSON.stringify(response.data));
            this.totalGenusOptionsList = response.data
          });     
      })
    },
    speciesSingleAutocompleteResults(val, update, abort) {      
      update(() => {
        let payload = {
          searchTerm: val,
          selectedOptionValue: 'Species',
          searchType: 'STRAIN_SEARCH'
        };
        this.$store
        .dispatch("shared/GET_SINGLE_AUTOCOMPLETE_RESULTS", payload)
        .then(response => {
          this.temptotalSpeciesOptionsList = JSON.parse(JSON.stringify(response.data));
          this.totalSpeciesOptionsList = response.data
        });     
      })
    },
    strainTypeSingleAutocompleteResults(val, update, abort) {  
      console.log('val ', val)    
      update(() => {
        let payload = {
          searchTerm: val,
          selectedOptionValue: 'StrainType',
          searchType: 'STRAIN_SEARCH'
        };
        this.$store
        .dispatch("shared/GET_SINGLE_AUTOCOMPLETE_RESULTS", payload)
        .then(response => {
          this.temptotalStrainTypeOptionsList = JSON.parse(JSON.stringify(response.data));
          this.totalStrainTypeOptionsList = response.data
        });
      })
    },
    
    deleteRow(index){
      this.$store.state.sourceSearch.strainSearch.totalRowData.splice(index, 1)
      if(this.$store.state.sourceSearch.strainSearch.totalRowData.length == 1) {
        
      }
    },
    addNewRow(){
      let newJournalRowData = Object.assign({},this.$store.state.sourceSearch.strainSearch.initialJournalRowData);
      this.$store.state.sourceSearch.strainSearch.totalRowData.push(newJournalRowData)
      
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
      if(this.$store.state.sourceSearch.strainSearch.totalRowData.length == 1) {
        return false
      }
      return true;
    },
    disableAdd(){
      let disable = false
      this.$store.state.sourceSearch.strainSearch.totalRowData.forEach(customItem => {
        if(customItem.genusList.length == 0 && customItem.speciesList.length == 0 && customItem.strainTypeList.length == 0) {
          disable = true
        }
      })
      return disable;
    },
    disableClear() {
      return false;
    },
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    }
  }
});

Vue.component("criterion-search", {
  template: `
  <div class="tab-pane bibliography-criterion-search active" id="m_tabs_1_2" role="tabpanel"> 
    <!-- Tab 2 Container part -->
    <div>
        <div class="m-portlet__body  m-portlet__body--no-padding">
            <div class="row m-row--no-padding m-row--col-separator-xl" style="border: 1px solid #bfc0c1;">
                <div class="col-xl-9" > 
                    <!--begin:: Widgets/Daily Sales-->           
                    <!-- First block row --> 
                    <div class="pad-tp-50px"></div>
                    <div class="row" v-for="criterionItem in $store.state.bibliographySearch.criterionSearch.data" :key="criterionItem.id">
                        <div class="col-3" style="padding-left:35px; padding-top:10px" >
                            {{criterionItem.label}}
                        </div>	
                        <div v-if="criterionItem.componentType == 'AUTOCOMPLETE'" class="col-7" style="vertical-align: baseline; top:0px;">
                            <div v-if="!criterionItem.fileName">
                                <!-- STARTS Multi Select drop down control -->
                                <auto-complete
                                :selected-value="criterionItem.selectedValue"
                                :search-type="$store.state.bibliographySearch.criterionSearch.key"
                                :current-index="-1"
                                :hideOperators = true
                                :is-target-search="false"
                                :ref="'autoComplete_' + criterionItem.id"
                                :default-selected-options="JSON.parse(JSON.stringify(criterionItem.autoCompleteSelections))"
                                :disable-auto-complete="false"
                                @auto-complete-selection-change="autoCompleteChange($event, criterionItem)"
                                ></auto-complete>
                                <!-- ENDS Multi Select drop down control -->
                            </div>
                            <div class="col-md-6 freeze-field" v-else>
                                {{criterionItem.fileName}}
                            </div>
                        </div>
                        <div class="col-6 row justify-left items-center q-ma-xs"  v-if="criterionItem.componentType == 'YEARRANGE'">
                            <div class="col-3" style="padding-left: 0px;">
                                <b-input class="card-shadow" style="border-color:none" v-model="criterionItem.fromYear" placeholder="From" type="text"></b-input> 
                            </div>
                            <div class="col-1">
                            -
                            </div>
                            <div class="col-3" style="padding-left: 0px;">
                                <b-input class="card-shadow" style="border-color:none" v-model="criterionItem.toYear" placeholder="To" type="text"></b-input>
                            </div>
                        </div>                    
                        <div class="col-1" v-if="criterionItem.componentType == 'AUTOCOMPLETE'"> 
                            <!-- File upload comonent -->
                            <div class="row">
                                <!-- File upload comonent --> 
                                <q-uploader
                                url
                                class="col"
                                accept=".txt"
                                @added="fileUpload($event, criterionItem)"
                                :disable="(criterionItem.selectedValue != null ) ? false : true"
                                :ref="'bibliographyCriterionSearchUpload_' + criterionItem.id"
                                style="max-width: 40px"
                                flat
                                >
                                <template v-slot:header="scope">
                                    <div class="no-wrap bg-white">
                                        <q-btn
                                            color="primary"
                                            size="21px"
                                            icon="fa fa-file-upload"
                                            @click="scope.pickFiles"
                                            round
                                            flat
                                            ></q-btn>
                                    </div>
                                </template>
                                </q-uploader>
                            </div>                            
                            <div class=""></div>
                        </div>
                        <div class="col-1" style="padding-left: 0px;" v-if="criterionItem.componentType == 'AUTOCOMPLETE'"> 
                            <!-- Refressing the entire row inputs --> 
                            <a href="javascript:void(0)" @click="resetAutoComplete(criterionItem)" :disabled="(criterionItem.selectedValue != null ) ? false : true" m-portlet-tool="reload" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-refresh refresh-btn-stl" ></i> </a> 
                        </div>
                        <div class="clearfix heght-20px"></div>
                    </div> 
                    <div class="heght-30px"></div>                                       
                    <!--end:: Widgets/Daily Sales--> 
                </div>   
                <div class="col-xl-3" style="width: auto;"> 
                    <!--begin:: Widgets/Profit Share-->
                    <div class="m-widget14">
                        <div class="m-widget14__header">
                            <h3 class="m-widget14__title"> Data Source</h3>                            
                        </div>
                        <div class="row  align-items-center">
                            <div class="col">
                                <div class="m-widget14__legends">
                                    <div class="m-form__group form-group"> 
                                        <!--<label>Bold Radios</label> -->
                                        <div class="m-radio-list">
                                            <b-form-group>
                                                <template slot="label">
                                                <b class="m-widget14__title">Choose Data Source:</b><br>
                                                <b-form-checkbox
                                                v-model="allSelected"
                                                :indeterminate="indeterminate"
                                                aria-describedby="dataSourceOp"
                                                aria-controls="dataSourceOp"
                                                @change="toggleAll"
                                                >
                                                {{ allSelected ? 'Un-select All' : 'Select All' }}
                                                </b-form-checkbox>
                                                </template>
                                                <b-form-checkbox-group
                                                id="flavors"
                                                v-model="selected"
                                                :options="dataSourceOp"
                                                name="flavors"
                                                class="ml-4"
                                                aria-label="Individual dataSourceOp"
                                                stacked
                                                ></b-form-checkbox-group>
                                            </b-form-group>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>					
                    </div>
                    <!--end:: Widgets/Profit Share--> 
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

    this.selected = this.$store.state.bibliographySearch.criterionSearch.dataSource;
    if(Object.keys(this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['bibliographySearch']['criterionSearch']).length > 0) {
      this.$store.state.bibliographySearch.criterionSearch = this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['bibliographySearch']['criterionSearch']
    }
  },
  data() {
    return {
      dataSourceOp: [
        { text: 'Journal (J)', value: 'J' },
        { text: 'Patent (P)', value: 'P' },
        { text: 'Others (O)', value: 'O' },
      ],
      selected: [],
      allSelected: false,
      indeterminate: false,
      disableAllRadioAndCheckbox: false,
      iniitalItemObject: {
        id: 1,
        label:"Author Name",
        selectedValue: "author",
        autoCompleteSelections: [],
        fileName: "",
        fileData: []
      },
      showLoading: false,
      selectedValue: ""
    };
  },

  watch: {
    selected(newVal, oldVal) {
      // Handle changes in individual flavour checkboxes
      if (!newVal) {
        return;
      }
      if (newVal.length === 0) {
        this.indeterminate = false
        this.allSelected = false
      } else if (newVal.length === this.dataSourceOp.length) {
        this.indeterminate = false
        this.allSelected = true
      } else {
        this.indeterminate = true
        this.allSelected = false
      }
      this.$store.state.bibliographySearch.criterionSearch.dataSource = newVal;

    }
  },
  methods: {
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
          // make sure to update the rowdata advanceSearch keys for reflecting in the simple search
          let tempCriterionSearchData = {
            fileData: [],
            autoCompleteSelections: []
          }

          this.$store.state.bibliographySearch.criterionSearch.data.forEach(item => {
            // filedata
            if (item.fileData.length > 0) {
              tempCriterionSearchData = item
              return;
            }

            // autocompletion
            if (item.autoCompleteSelections.length > 0) {
              tempCriterionSearchData = item
              return;
            }

            // year
            if (item.fromYear || item.toYear) {
              tempCriterionSearchData = item
              return;
            }
          })

          // raise a validation error since nothing is selected
          if (tempCriterionSearchData.fileData.length == 0 && tempCriterionSearchData.autoCompleteSelections.length == 0 && !tempCriterionSearchData.fromYear) {
            this.$q.notify({
              message: 'Please select any value',
              position: "top",
              color: "deep-orange-5",
              timeout: 2000
            });
            return;
          }

          // POPULATE ALL THE CRITERION SEARCH FIELDS TO SIMPLESEARCH ROW DATA
          // ON MOUNT OF THIS COMPONENT REPOPULATE BIBLIOGRAPHY CRITERION SEARCH WITH EXACT BELOW DATA.
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['bibliographySearch'] = this.$store.state.bibliographySearch;

          if(tempCriterionSearchData.fileData.length == 0) {
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]["advanceSearch"]['autoComplete'] = tempCriterionSearchData.autoCompleteSelections;
          }else{
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]["fileData"] = tempCriterionSearchData.fileData;
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]["fileName"] = tempCriterionSearchData.fileName;
          }

          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['advanceSearchSelectedValue'] = tempCriterionSearchData.selectedValue;
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['advanceSearchSelectedValueName'] = tempCriterionSearchData.label

          // added for display add new row button in simple search page start
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["hasValue"] = true;
          // added for display add new row button in simple search page end

          this.getSearchResultsAndCount();
          this.$emit("adv-popup-closed", true);
        })
        .catch(err => {
          console.log(err)
          // An error occurred
        });
    },
    resetChanges() {
      this.resetAutoComplete();
      
      this.$store.state.bibliographySearch.criterionSearch = JSON.parse(glb__initialBibliographySearchState).criterionSearch

      // clear in the simple search box row also.
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["advanceSearch"];
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['bibliographySearch']['criterionSearch'] =  JSON.parse(glb__initialBibliographySearchState).criterionSearch

      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearch']['autoComplete'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValueName'] = ''
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['fileData'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['fileName'] = ''
      this.selected = this.$store.state.bibliographySearch.criterionSearch.dataSource;
       
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
    toggleAll(checked) {
      this.selected = checked ? this.dataSourceOp.map(item => item.value) : []
    },
    resetFileUpload(criterionItem) {
      let curRef = 'bibliographyCriterionSearchUpload_' + criterionItem.id
      this.$refs[curRef][0].reset();
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
    resetAutoComplete(criterionItem) {
     
      this.$store.state.bibliographySearch.criterionSearch.data.forEach(item => {
        if(criterionItem && criterionItem.id == item.id) {
          if(this.$refs['autoComplete_' + item.id] && this.$refs['autoComplete_' + item.id]['length'] > 0) {
            this.$refs['autoComplete_' + item.id][0]['gb__selections'] = {
              items: [],
              model: [],
              owner: null
            };
          }

          item.autoCompleteSelections = []
          item.fileData = []
          item.fileName = ''
          return ;
        }

        if(!criterionItem) {
          if(this.$refs['autoComplete_' + item.id] && this.$refs['autoComplete_' + item.id]['length'] > 0) {
            this.$refs['autoComplete_' + item.id][0]['gb__selections'] = {
              items: [],
              model: [],
              owner: null
            };
          }
          
          item.autoCompleteSelections = []
          item.fileData = []
          item.fileName = ''
        }
      })
      
      this.$store.state.advanceSearch.currentItem.advanceSearch.autoComplete = [];
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["bibliographySearch"]['criterionSearch'] = JSON.parse(glb__initialBibliographySearchState).criterionSearch
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
    fileUpload(event, criterionItem) {
      this.resetFileUpload(criterionItem)
      criterionItem.autoCompleteSelections = [];
      const input = event;
      let filterFileContents = [];
      this.readFileContent(input[0])
        .then(content => {
          if (content.toString().match(/\r?\n|\r/g)) {
            let tempContent = content.toString().replace(/\r?\n|\r/g, "~");

            fileContents = tempContent.split("~");
            filterFileContents = fileContents.filter(p => p != "");
          } else if (content.toString().includes(",")) {
            fileContents = content.toString().split(",");
            filterFileContents = fileContents.filter(p => p != "");
          } else if (
            !content.toString().match(/\r?\n|\r/g) &&
            !content.toString().includes(",") &&
            content.length != 0
          ) {
            filterFileContents.push(content);
          }


          if (filterFileContents.length > 0) {
            criterionItem.fileName = event[0].name;
            criterionItem.fileData = filterFileContents;
          } else {
            this.$q.notify({
              message: "Please select Valid File to Proceed",
              color: "red",
              timeout: 2000,
              position: "top"
            });
          }
        })
        .catch(error => console.log(error));
    },
    readFileContent(file) {
      const reader = new FileReader();
      return new Promise((resolve, reject) => {
        reader.onload = event => resolve(event.target.result);
        reader.onerror = error => reject(error);
        reader.readAsText(file);
      });
    },
    autoCompleteChange(event, criterionItem) {
      if (event.length > 0) {
        criterionItem.autoCompleteSelections = event;
      }
    }
  },
  computed: {
    disableAdd(){
      let disable = true
      this.$store.state.bibliographySearch.criterionSearch.data.forEach(criterionItem => {
        if(criterionItem.autoCompleteSelections.length > 0 || criterionItem.fromYear) {
          disable = false;
        }
      })
      return disable
    },
    disableClear(){
      let disable = true
      this.$store.state.bibliographySearch.criterionSearch.data.forEach(criterionItem => {
        if(criterionItem.autoCompleteSelections.length > 0 || criterionItem.fromYear) {
          disable = false;
        }
      })
      return disable
    },
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    }
  }
});

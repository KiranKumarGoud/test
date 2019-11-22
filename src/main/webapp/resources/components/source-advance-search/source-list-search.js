Vue.component("source-list-search", {
  template: `
  <div class="tab-pane source-list-search active" id="m_tabs_1_2" role="tabpanel"> 
    <!-- Tab 1 Container part -->
    <div>
        <div class="m-portlet__body  m-portlet__body--no-padding">
            <div class="row m-row--no-padding m-row--col-separator-xl" style="border: 1px solid #bfc0c1;">
                <div class="col-xl-12"> 
                    <!--begin:: Widgets/Daily Sales-->
                    <div class="m-widget14 head-no-1">                      
                        <!-- Draw Structure component resource code goes here -->
                        <div>
                            <div class="heght-10px">
                            </div>
                            <h3 class="m-widget14__title head-no-3" > List Search Criterion</h3>
                        </div>
                    </div>
                    <div class="row" style="margin: 0px;">
                        <div class="col-5">
                            <b-form-select class="form-control shadow-efct-stl inpt-tp-10 custom-select"
                            v-model="$store.state.sourceSearch.listSearch.advanceSearch.selectedValue"
                            @change="selectChange"
                            :options="$store.state.sourceSearch.listSearch.advanceSearch.selectOptions"
                            >
                            </b-form-select>
                        </div>
                        <div class="col-5"> 
                            <!-- STARTS Multi Select drop down control -->
                            <div v-if="$store.state.sourceSearch.listSearch.advanceSearch.selectedValue == 'ref_id' && $store.state.sourceSearch.listSearch.fileName == null">
                                <!--  show input to accept comma seperated list. -->
                                <b-form-input
                                    type="text"
                                    v-model="refIds"
                                    @change="captureRefIds()"
                                    placeholder="Enter | seperated ref id's EG: 123|234"
                                >
                                </b-form-input>
                            </div>
                            <div v-if="$store.state.sourceSearch.listSearch.advanceSearch.selectedValue != 'ref_id' && $store.state.sourceSearch.listSearch.fileName == null">
                                <!-- STARTS Multi Select drop down control -->
                                <auto-complete
                                    :selected-value="$store.state.sourceSearch.listSearch.advanceSearch.selectedValue"
                                    search-type="SOURCE_ADVANCE_SEARCH"
                                    :current-index="$store.state.advanceSearch.currentItemIndex"
                                    :is-target-search="false"
                                    ref="autoComplete"
                                    :default-selected-options="$store.state.advanceSearch.currentItem.advanceSearch.autoComplete"
                                    :disable-auto-complete="($store.state.sourceSearch.listSearch.advanceSearch.selectedValue != null ) ? false : true"
                                    @auto-complete-selection-change="autoCompleteChange($event)"
                                ></auto-complete>
                            </div>
                            <div class="col-md-6 freeze-field" v-else-if="$store.state.sourceSearch.listSearch.fileName != null">
                                {{$store.state.sourceSearch.listSearch.fileName}}
                            </div>
                            <!-- ENDS Multi Select drop down control -->             
                        </div>
                        <div class="col-1" style="width: 5%"> 
                            <!-- File upload comonent -->                            
                            <q-uploader
                                url
                                class="col"
                                accept=".txt"
                                @added="fileAdded"
                                :disable="($store.state.sourceSearch.listSearch.advanceSearch.selectedValue != null ) ? false : true"
                                ref="sourceListSearchUpload"
                                style="max-width: 50px"
                                flat
                            >
                                <template v-slot:header="scope">
                                <div class="no-wrap bg-white">
                                    <q-btn
                                    color="primary"
                                    size="23px"
                                    icon="fa fa-file-upload"
                                    @click="scope.pickFiles"
                                    round
                                    flat
                                    ></q-btn>
                                </div>
                                </template>
                            </q-uploader>
                            <div class="">
                            </div>
                        </div>
                        <div class="col-1" style="margin-top:-2px;"> 
                            <!-- Refressing the entire row inputs --> 
                            <a href="javascript:void(0)" @click="resetAutoComplete()" :disabled="($store.state.sourceSearch.listSearch.advanceSearch.selectedValue != null ) ? false : true" m-portlet-tool="reload" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-refresh refresh-btn-stl" style="font-size: 2.5rem;"></i> </a> 
                        </div>
                    </div>
                    <div class="heght-30px"></div>
                    <!--end:: Widgets/Daily Sales--> 
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
    if(Object.keys(this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['sourceSearch']['listSearch']).length > 0) {
      this.$store.state.sourceSearch.listSearch = this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['sourceSearch']['listSearch']
    }else{
      this.$store.state.sourceSearch = JSON.parse(glb__initialSourceSearchState)
    }
  },
  data() {
    return {
      refIds: '',
      fileuploadvalue: "",
      tempAdvanceSearch: [],
      disableAllRadioAndCheckbox: false,
      model: "",
      options: [],
      fileName: "",
      fileData: [],
      disableUpload: false,
      
      selectedValue: "",
      showLoading: false
    };
  },
  methods: {
    captureRefIds(){
      let formatedrefIds = this.refIds.split('|')
      this.$store.state.sourceSearch.listSearch.advanceSearch.refIds = formatedrefIds;
    },
    resetFileUpload() {
      this.$refs.sourceListSearchUpload.reset();
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
    resetAutoComplete() {
      
      if (this.$refs.autoComplete) {
        this.$refs.autoComplete.gb__selections = {
          items: [],
          model: [],
          owner: null
        };
      }

      this.$store.state.sourceSearch.listSearch.advanceSearch.autoCompleteSelections = []
      this.$store.state.sourceSearch.listSearch.fileData = []
      this.$store.state.sourceSearch.listSearch.fileName = null
      this.$store.state.advanceSearch.currentItem.advanceSearch.autoComplete = []
    },
    selectChange(event) {
      this.resetAutoComplete();
      if (event) {
        this.$store.state.sourceSearch.listSearch.advanceSearch.selectedValue = event;
      }
    },
    resetChanges() {
      this.resetAutoComplete();
      this.$store.state.sourceSearch.listSearch = JSON.parse(glb__initialSourceSearchState).listSearch

      // clear in the simple search box row also.
      let curItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let curAdvanceSearch = this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["advanceSearch"];
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['sourceSearch']['listSearch'] =  JSON.parse(glb__initialSourceSearchState).listSearch
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearch']['autoComplete'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValue'] = null
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['advanceSearchSelectedValueName'] = ''
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['fileData'] = []
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex]['fileName'] = ''

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
    getSelectedValueName(selectedValue) {
      let textName = ''
      this.$store.state.sourceSearch.listSearch.advanceSearch.selectOptions.forEach(element => {
        if(element.value == selectedValue) {
          textName = element.text;
        }
      });
      return textName;
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
          ]['isSourceAdvanceSearch'] = true

          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['sourceSearch']['listSearch'] = this.$store.state.sourceSearch.listSearch;

          // make sure to update the rowdata advanceSearch keys for reflecting in the simple search
          if(this.$store.state.sourceSearch.listSearch.fileData.length == 0) {
            console.log('here ', this.$store.state.sourceSearch.listSearch.advanceSearch.autoCompleteSelections)
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]["advanceSearch"]['autoComplete'] = this.$store.state.sourceSearch.listSearch.advanceSearch.autoCompleteSelections;
           
          }else{
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]["fileData"] = this.$store.state.sourceSearch.listSearch.fileData;
            this.$store.state.simpleSearch.simpleSearchRowData[
              curItemIndex
            ]["fileName"] = this.$store.state.sourceSearch.listSearch.fileName;
          }

          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['advanceSearchSelectedValue'] = this.$store.state.sourceSearch.listSearch.advanceSearch.selectedValue;
          this.$store.state.simpleSearch.simpleSearchRowData[
            curItemIndex
          ]['advanceSearchSelectedValueName'] = this.getSelectedValueName(this.$store.state.sourceSearch.listSearch.advanceSearch.selectedValue)
          
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
    fileAdded(event) {
      this.resetFileUpload()
      this.$store.state.sourceSearch.listSearch.advanceSearch.autoCompleteSelections = [];
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

            this.$store.state.simpleSearch.sourceListSearchFileUpload = true
           
            
            this.$store.state.sourceSearch.listSearch.fileName = event[0].name;
            this.$store.state.sourceSearch.listSearch.fileData = filterFileContents;
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
    autoCompleteChange(event) {
      if (event.length > 0) {
        this.$store.state.sourceSearch.listSearch.advanceSearch.autoCompleteSelections = event;
        
      }
    }
  },
  computed: {
    disableAdd(){
      if(
        this.$store.state.sourceSearch.listSearch.advanceSearch.autoCompleteSelections.length > 0 ||
        this.$store.state.sourceSearch.listSearch.advanceSearch.selectedValue == 'ref_id' ||
        this.$store.state.sourceSearch.listSearch.fileData.length > 0
        ){
          return false
        }
        
      return true;
    },
    disableClear(){
      if(this.$store.state.sourceSearch.listSearch.advanceSearch.selectedValue){
          return false
        }
      return true;
    },
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    }
  }
});

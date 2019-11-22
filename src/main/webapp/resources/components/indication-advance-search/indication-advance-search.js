Vue.component("indication-advance-search", {
  template: `
  <div class="modal-dialog modal-big-lg" role="document">
    <div class="modal-content indication-search gs-advance-search">
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
                        <div class="row m-row--no-padding m-row--col-separator-xl">
                          <div class="col-xl-12"> 
                            <!--begin:: Widgets/Daily Sales-->
                            <div class="m-widget14">
                              <div class="m-widget14__header m--margin-bottom-30">
                                  <h3 class="m-widget14__title">Indication </h3>
                                <!-- <span class="m-widget14__desc"> Selected value </span> </div> -->
                              </div>
                              <div class="row">
                                <div class="col-5">
                                  <b-form-select
                                  class="form-control shadow-efct-stl inpt-tp-10"
                                  v-model="selectedOptionValue"
                                  @change="selectChange"
                                  :options="selectOptions"
                                  >
                                  </b-form-select>
                                </div>
                                <div class="col-5"> 
                                  <!-- STARTS Multi Select drop down control -->
                                  <div v-if="fileuploadvalue == ''">
                                      <auto-complete
                                      :selected-value="selectedOptionValue"
                                      search-type="INDICATION_ADVANCE_SEARCH"
                                      :current-index="$store.state.advanceSearch.currentItemIndex"
                                      :is-target-search="false"
                                      ref="autoComplete"
                                      :default-selected-options="JSON.parse(JSON.stringify($store.state.advanceSearch.currentItem.advanceSearch.autoComplete))"
                                      :disable-auto-complete="(selectedOptionValue != null ) ? false : true"
                                      @auto-complete-selection-change="autoCompleteChange($event)"/>
                                  </div>
                                  <div class="col-md-6 freeze-field" v-else>{{fileuploadvalue}}</div>
                                  <!-- ENDS Multi Select drop down control -->
                                </div>
                                <div class="col-1 file-upload-btn"> 
                                  <!-- File upload comonent -->
                                  <div class="row">
                                    <!-- File upload comonent -->
                                    <q-uploader
                                        url
                                        class="col"
                                        accept=".txt"
                                        @added="fileAdded"
                                        :disable="(selectedOptionValue != null ) ? false : true"
                                        ref="indicationAdvSearchUpload"
                                        style="max-width: 63px"
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
                                                >
                                                </q-btn>
                                            </div>
                                        </template>
                                    </q-uploader>
                                  </div>
                                  <div class=""></div>
                                </div>
                                <div class="col-1">
                                <!-- Refressing the entire row inputs -->
                                    <a @click="resetAutoComplete()" :disabled="(selectedOptionValue != null ) ? false : true" href="javascript:void(0)" m-portlet-tool="reload" class="m-portlet__nav-link m-portlet__nav-link--icon">
                                        <i class="la la-refresh refresh-btn-stl"></i>
                                    </a>					
                                </div>	
                              </div>
                              <!--end:: Widgets/Daily Sales--> 
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
                  @click="resetTargetAdvanceSearchValues()"
                >Clear</button>
                <button type="button" :disabled="disableAdd" class="btn btn-excelra btn-sm" @click="confirm()">Add</button>
              </div>
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

    if (
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex][
        "fileData"
      ].length == 0
    ) {
      this.tempAdvanceSearch = curAdvanceSearch.autoComplete;
    }

    if (curAdvanceSearch.targetCategory) {
      this.selected = curAdvanceSearch.targetCategory;
    }

    if (curAdvanceSearch.autoComplete.length > 0) {
      this.disableAutocomplete = false;
    }

    this.selectedValue = this.$store.state.simpleSearch.simpleSearchRowData[
      curItemIndex
    ]["advanceSearchSelectedValue"];

    this.selectedOptionValue = this.$store.state.simpleSearch.simpleSearchRowData[
      curItemIndex
    ]["advanceSearchSelectedValue"];

    if (
      this.$store.state.simpleSearch.simpleSearchRowData[curItemIndex][
        "fileData"
      ].length > 0
    ) {
      this.fileuploadvalue = this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["fileName"];

      this.fileData = this.$store.state.simpleSearch.simpleSearchRowData[
        curItemIndex
      ]["fileData"];
    }

    this.selectOptions = this.$store.state.advanceSearch.IndicationSelectOptions;

    if (this.selectedOptionValue) {
      this.disableAutocomplete = false;
      this.disableAdd = false;
      this.disableClear = false;
    }
  },
  data() {
    return {
      selected: ["primary", "secondary", "profile"],
      allSelected: true,
      indeterminate: false,
      fileuploadvalue: "",
      disableAdd: true,
      disableClear: true,
      tempAdvanceSearch: [],
      disableAllRadioAndCheckbox: false,
      model: "",
      options: [],
      selectAll: "selectAll",
      fileName: "",
      fileData: [],
      selectOptions: [],
      disableUpload: false,
      selectedOptionValue: null,
      test: "",
      tempryData: "",
      disableAutocomplete: true,
      selectedValue: "",
      showLoading: false
    };
  },
  methods: {
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
    resetAutoComplete() {
      this.disableAdd = true;
      this.disableClear = false;
      this.fileuploadvalue = "";

      this.$refs.indicationAdvSearchUpload.reset();
      if (this.$refs.autoComplete) {
        this.$refs.autoComplete.gb__selections = {
          items: [],
          model: [],
          owner: null
        };
      }
    },
    selectChange(event) {
      this.resetAutoComplete();
      this.disableAutocomplete = false;
      //this.$store.state.advanceSearch.selectedValue = event;
      if (event) {
        this.selectedValue = event;
        this.disableClear = false;
      }

      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ].fileData = [];

      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ].fileName = "";
      let rowData = JSON.parse(
        JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData)
      );

      rowData.forEach((item, ind) => {
        if (item.simpleSearchSelectedValue == "Indication") {
          this.selectedValue = event;
        }
      });
    },
    resetTargetAdvanceSearchValues() {
      this.$store.state.advanceSearch.selectedValue = null;
      this.selectedOptionValue = null;
      this.disableAdd = true;
      this.disableClear = false;
      this.resetAutoComplete();

      let advSearchItem = this.$store.state.advanceSearch.currentItemIndex;
      let curItem = this.$store.state.simpleSearch.simpleSearchRowData[
        advSearchItem
      ];

      // this.$refs.simpleSearchUpload.reset();

      // reset to default values
      this.$store.state.simpleSearch.simpleSearchRowData[
        advSearchItem
      ] = this.$set(
        this.$store.state.simpleSearch.simpleSearchRowData,
        advSearchItem,
        Object.assign(curItem, {
          advanceSearch: {
            autoComplete:
              curItem.simpleSearchAutoComplete.length == 0
                ? curItem.advanceSearch.autoComplete
                : [],
            autoComplete: [],
            selectedValue: ""
          },
          advanceSearchSelectedValue: null,
          fileData: [],
          fileName: ""
        })
      );

      this.disableAutocomplete = true;
      if (
        this.$store.state.simpleSearch.simpleSearchRowData.length == 1 &&
        this.$store.state.simpleSearch.simpleSearchRowData[0]
          .simpleSearchAutoComplete.length == 0
      ) {
        this.getInitalCounts();
        //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
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
          this.$store.state.advanceSearch.showDisabledProps = true;
          this.$store.state.advanceSearch.toggleAdvanceSearchModal = false;
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ].simpleSearchAutoComplete = [];
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["advanceSearch"]["autoComplete"] = this.tempAdvanceSearch;
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["advanceSearchSelectedValue"] = this.selectedValue;

          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["fileName"] = this.fileuploadvalue;
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["fileData"] = this.fileData;
          this.$store.state.advanceSearch.selectedValue = this.selectedValue;

          let filterSelectedOption = this.$store.state.advanceSearch.IndicationSelectOptions.filter(
            p => p.value == this.selectedValue
          );
          if (filterSelectedOption.length != 0)
            this.$store.state.advanceSearch.selectedValueNameIndication =
              filterSelectedOption[0].text;
 
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["hasValue"] = true;
          this.getSearchResultsAndCount();

          this.$emit("popup-closed", true);
        })
        .catch(err => {
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
      console.log(event);
      this.$refs.indicationAdvSearchUpload.reset();
      this.$store.state.advanceSearch.currentItem.advanceSearch.autoComplete = [];
      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ].advanceSearch.autoComplete = [];
      this.tempAdvanceSearch = [];
      let fileContents = [];

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

          this.fileuploadvalue = event[0].name;
          this.fileData = filterFileContents;

          if (filterFileContents.length > 0) {
            this.disableAdd = false;
            this.disableClear = false;
          } else {
            this.disableAdd = true;
            this.disableClear = false;
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
      this.tempAdvanceSearch = [];
      this.disableAdd = true;

      if (event.length > 0) {
        this.tempAdvanceSearch = event;
        this.disableAdd = false;
        this.disableClear = false;
      }
    }
  },
  watch: {
    
  },
  computed: {
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    }
  }
});

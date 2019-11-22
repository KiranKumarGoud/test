Vue.component("term-search", {
  template: `
  <div>  
    <div class="tab-pane term-search" id="m_tabs_6_2" role="tabpanel"> 
    <!-- START Tab 2 Container part -->
    <div>
      <div class="m-portlet__body  m-portlet__body--no-padding">
      <div class="row m-row--no-padding m-row--col-separator-xl">
        <div class="col-xl-12" > 
        <!--begin:: Widgets/Daily Sales-->
        <!--<div class="m-widget14">
          <div class="m-widget14__header m--margin-bottom-30">
          <h3 class="m-widget14__title"> Term Search </h3> 
          </div>
        </div>-->
        <div class="row">
          <div class="col-5" style="vertical-align: baseline; top:0px; margin-top:10px">
            <b-form-select  class="form-control shadow-efct-stl"
            v-model="termSearchSelectedValue" 
            :options="termSearchSelectOptions" 
            @change="termSearchSelectChange"  
            disabled-field="disabled"  >
            </b-form-select>
          </div>
          <div class="col-5" v-if="uploadedFileName == '' && !showAutocomplete">
            <b-form-input class="card-shadow form-control" v-model="termSearchField" placeholder="Search Term" :disabled="disableAllFields" @keyup="captureSearchQuery"  @keydown="preventSpaceKeyPress($event)"></b-form-input>
          </div>
          <div class="col-5" v-else-if="showAutocomplete">
            <!-- STARTS Multi Select drop down control -->      
              <auto-complete
                :selected-value = "termSearchSelectedValue"
                ref="termSearchAutocomplete"
                search-type="TERM_ADVANCE_SEARCH"
                :current-index="$store.state.advanceSearch.currentItemIndex"
                :default-selected-options="JSON.parse(JSON.stringify(autoCompleteSelectedValue))"
                @auto-complete-selection-change="autoCompleteChange($event)"
                > 
              </auto-complete>
            <!-- ENDS Multi Select drop down control -->                             
          </div>
          <div class="col-5" v-else>
            <div class="freeze-field">{{uploadedFileName}}</div>
          </div>
          
          <div class="col-1 file-upload-btn"> 
            <!-- File upload comonent -->
            <q-uploader
              url
              class="col"
              accept=".txt"
              @added="fileAdded"
              :disabled="disableAllFields"
              :class="{'pointerNone':(disableAllFields  )?true:false}"
              ref="fileUploader"
              style="max-width: 30px"
              flat>
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
            <div class=""></div>
          </div>
            <div class="col-1"> 
              <!-- Refressing the entire row inputs --> 
              <a href="javascript:void(0);" @click="refreshFields($event)" m-portlet-tool="reload" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-refresh refresh-btn-stl" ></i> </a> 
            </div>
        </div>
          <div class="heght-50px"></div>
        <!--end:: Widgets/Daily Sales--> 
        </div>                      
      </div>
      </div>
    </div>
    <!-- ENDS Tab 2 Container part -->  
  </div>
    <div class="modal-footer" style="margin: 0px -13px -13px -13px;">
      <button type="button" class="btn btn-danger btn-sm" @click="onClear" :disabled="disableClear">Clear</button>
      <button type="button" class="btn btn-excelra btn-sm" @click="confirm" :disabled="disableAdd">Add</button>
    </div>
  </div>`,
  mounted() {
    let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
      this.$store.state.advanceSearch.currentItemIndex
    ];
    console.log(currItem);
    if (currItem.advanceSearchSelectedValue != null) {
      this.termSearchSelectedValue = currItem.advanceSearchSelectedValue;
      this.isMounted = true;
    }
    if (this.$store.state.structureSearch.selectedValueName) {
      this.termSearchSelectedName = this.$store.state.structureSearch.selectedValueName;
    }
    if (currItem.fileData.length > 0) {
      this.uploadedFileName = currItem.fileName;
      this.uploadedFileData = currItem.fileData;
      this.disableAdd = false;
      this.disableClear = false;
      this.disableAllFields = false;
    } else if (
      currItem.advanceSearch.structureInputField &&
      currItem.advanceSearch.structureInputField.length > 0 &&
      !currItem.advanceSearch.isAutoComplete
    ) {
      this.eneteredInputVal = currItem.advanceSearch.structureInputField;
      let searchField = "";
      for (let item of currItem.advanceSearch.structureInputField) {
        searchField += item + "|";
      }
      this.termSearchField = searchField.substr(0, searchField.length - 1);
      this.disableAdd = false;
      this.disableClear = false;
      this.disableAllFields = false;
    } else if (
      currItem.advanceSearch.structureInputField &&
      currItem.advanceSearch.structureInputField.length > 0 &&
      currItem.advanceSearch.isAutoComplete
    ) {
      this.autoCompleteSelectedValue = JSON.parse(
        JSON.stringify(currItem.advanceSearch.structureInputField)
      );
      this.disableAdd = false;
      this.disableClear = false;
      this.disableAllFields = false;
      this.showAutocomplete = true;
    }
  },
  data() {
    return {
      termSearchSelectedValue: null,
      termSearchField: "",
      termSearchSelectedName: "",
      isMounted: false,
      disableAdd: true,
      disableClear: true,
      showAutocomplete: false,
      termSearchSelectOptions: [
        { value: null, text: "Read Only Input", disabled: true },
        {
          value: "compound_name",
          text: "Compound Name",
          disabled: false
        },
        {
          value: "cas_no",
          text: "CAS ID",
          disabled: false
        },
        // {
        //   value: "atc_code",
        //   text: "ATC Code",
        //   disabled: false
        // },
        {
          value: "gvk_id",
          text: "GVK ID",
          disabled: false
        },
        {
          value: "str_id",
          text: "STR ID",
          disabled: false
        }
      ],
      disableAllFields: true,
      uploadedFileName: "",
      uploadedFileData: [],
      eneteredInputVal: [],
      autoCompleteSelectedValue: []
    };
  },
  methods: {
    termSearchSelectChange(event) {
      if (event == "compound_name" || event == "cas_no") {
        this.showAutocomplete = true;
        this.eneteredInputVal = [];
      } else {
        this.showAutocomplete = false;
      }
      let filterSelectedValue = this.termSearchSelectOptions.filter(
        p => p.value == event
      );
      this.termSearchSelectedName = filterSelectedValue[0].text;
      this.disableAllFields = false;
      this.disableClear = false;
      this.autoCompleteSelectedValue = [];
      this.refreshFields();

      this.isMounted = false;
    },
    captureSearchQuery(event) {
      let eneteredVal = event.target.value;
      this.eneteredInputVal = eneteredVal.split("|");
      this.disableAdd = false;
      this.disableClear = false;
    },
    preventSpaceKeyPress(event) {
      if (event.keyCode === 32) {
        event.preventDefault();
      }
    },
    fileAdded(event) {
      this.$refs.fileUploader.reset();
      const input = event;
      let filterFileContents = [];
      this.uploadedFileName = input[0].name;
      this.readFileContent(input[0])
        .then(content => {
          if (content.toString().match(/\r?\n|\r/g)) {
            let tempContent = content.toString().replace(/\r?\n|\r/g, "~");

            fileContents = tempContent.split("~");
            filterFileContents = fileContents.filter(p => p != "");
          } else if (
            /* Comma seperation not handled due to issue in backend */
            !content.toString().match(/\r?\n|\r/g) &&
            content.length != 0
          ) {
            filterFileContents.push(content);
          }
          this.showAutocomplete = false;
          this.uploadedFileData = filterFileContents;
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["advanceSearch"].structureInputField = [];
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
    refreshFields(event) {
      this.uploadedFileName = "";
      this.uploadedFileData = [];
      this.termSearchField = "";
      this.$refs.fileUploader.reset();
      this.disableAdd = true;
      this.eneteredInputVal = [];
      if (
        this.termSearchSelectedValue == "compound_name" ||
        this.termSearchSelectedValue == "cas_no"
      ) {
        this.showAutocomplete = true;
        this.autoCompleteSelectedValue = [];
      }
      if (
        this.showAutocomplete &&
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]["advanceSearch"]["trmSearchAutoComplete"] &&
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]["advanceSearch"]["trmSearchAutoComplete"].length > 0
      ) {
        this.$refs.termSearchAutocomplete.resetAutoCompleteValues();
      }
    },
    onClear() {
      this.refreshFields();
      this.showAutocomplete = false;
      this.termSearchSelectedValue = null;
      let advSearchItem = this.$store.state.advanceSearch.currentItemIndex;
      let curItem = this.$store.state.simpleSearch.simpleSearchRowData[
        advSearchItem
      ];
      this.$store.state.simpleSearch.simpleSearchRowData[
        advSearchItem
      ] = this.$set(
        this.$store.state.simpleSearch.simpleSearchRowData,
        advSearchItem,
        Object.assign(curItem, {
          advanceSearch: {
            autoComplete: [],
            trmSearchAutoComplete: []
          },
          structureAdvanceSearchTab: null,
          advanceSearchSelectedValue: null,
          selectedValue: null,
          fileData: [],
          fileName: "",
          isStructureAdvanceSearch: false
        })
      );
      this.disableAdd = true;
      this.disableClear = true;
      this.disableAllFields = true;
      if (
        this.$store.state.simpleSearch.simpleSearchRowData.length == 1 &&
        !this.$store.state.simpleSearch.simpleSearchRowData[0].advanceSearch
          .structureInputField
      ) {
        this.getInitalCounts();
        //this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
      } else {
        this.getSearchResultsAndCount();
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
    confirm() {
      this.$bvModal
        .msgBoxConfirm("Are you sure?", {
          centered: true
        })
        .then(value => {
          if (!value) {
            return;
          }
          let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ];
          let tempItem = JSON.parse(
            JSON.stringify(this.$store.state.simpleSearch.simpleSearchRowData)
          );
          currentItem[
            "advanceSearchSelectedValue"
          ] = this.termSearchSelectedValue;
          currentItem["advanceSearch"][
            "isAutoComplete"
          ] = this.showAutocomplete;
          if (this.uploadedFileData.length > 0) {
            currentItem.fileData = this.uploadedFileData;
            currentItem.fileName = this.uploadedFileName;
          } else {
            currentItem.fileData = [];
            currentItem.fileName = "";
            let filterEmpty = this.eneteredInputVal.filter(p => p == "");
            for (let item of filterEmpty) {
              this.eneteredInputVal.splice(
                this.eneteredInputVal.indexOf(item),
                1
              );
            }
            currentItem.advanceSearch.structureInputField =
              this.autoCompleteSelectedValue.length == 0
                ? this.eneteredInputVal
                : JSON.parse(JSON.stringify(this.autoCompleteSelectedValue));
          }
          currentItem.isStructureAdvanceSearch = true;
          currentItem["hasValue"] = true;
          currentItem.structureAdvanceSearchTab = "term";
          currentItem.simpleSearchAutoComplete = [];

          let isValid = this.getSearchResultsAndCount();

          isValid.then(res => {
            if (!res) {
              this.$store.state.simpleSearch.simpleSearchRowData = JSON.parse(
                JSON.stringify(tempItem)
              );
            }
          });
        })
        .catch(err => {
          // An error occurred
        });
    },
    closeAdvanceSearchModal() {
      this.$emit("adv-popup-closed", true);
    },
    getSearchResultsAndCount: async function() {
      let postParam = [];
      this.$store.commit("simpleSearch/GET_SEARCH_FINAL_PAYLOAD");

      postParam = this.$store.state.simpleSearch.searchPostObj;
      let isValid = Boolean;
      await this.$store
        .dispatch("simpleSearch/GET_SEARCH_RESULTS_AND_COUNT", postParam)
        .then(res => {
          /* this.searchCountData = res.data; */
          console.log(res);
          if (res.status == 406) {
            this.$q.notify({
              message: "Please enter a valid data without special characters",
              color: "red",
              timeout: 2000,
              position: "top"
            });
            isValid = false;
          } else {
            this.$store.commit("simpleSearch/SET_SEARCH_COUNT", res.data);
            this.saveValuesToStructureSearchState();
            this.saveStoreData();
            this.closeAdvanceSearchModal();
            isValid = true;
          }
        })
        .catch(e => {
          console.log(e);
          return e.response;
        });
      return isValid;
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
    saveValuesToStructureSearchState() {
      this.$store.state.structureSearch.termSearch.selectedValue = this.termSearchSelectedValue;
      this.$store.state.structureSearch.termSearch.selectedValueName = this.termSearchSelectedName;
      this.$store.state.structureSearch.termSearch.inputSearchValue = this.termSearchField;
      this.$store.state.structureSearch.termSearch.fileName = this.uploadedFileName;
      this.$store.state.structureSearch.termSearch.fileData = this.uploadedFileData;
      this.$store.state.structureSearch.selectedValueName = this.termSearchSelectedName;
    },
    autoCompleteChange(event) {
      let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ];
      currItem.advanceSearch.trmSearchAutoComplete = event;
      this.autoCompleteSelectedValue = JSON.parse(JSON.stringify(event));
      this.disableAdd = true;
      if (event.length > 0) {
        this.disableAdd = false;
      } else {
      }
    }
  }
});

Vue.component("chemistry-search", {
  template: `<div>
                <div class="tab-pane chemistry-search" id="m_tabs_6_1" role="tabpanel"> 
                <!-- START Tab 1 Container part -->
                <div>
                  <div class="m-portlet__body  m-portlet__body--no-padding">
                    <div class="row m-row--no-padding m-row--col-separator-xl">
                      <div class="col-xl-8"> 
                        <!--begin:: Widgets/Daily Sales-->
                        <div class="m-widget14 head-no-1">                     
              <!-- Draw Structure component resource code goes here -->
              <div>            
                <b-form-radio v-model="chemistrySearchRadioVal"  value="drawStructure" @change="radioChanged" ><h5>Draw Structure</h5></b-form-radio>
                <div class="drawArea">       
                  <!--<draw-structure-search ref="drawStructure" ></draw-structure-search>-->
                  <div :disabled="!disableSelectAndInput" style="margin-left:21px">
                    <table class="custom-checkbox">
                      <tr>
                        <td style="width: 130px;"><strong>Display options</strong></td>
                        <td style="width: 110px;"><input :disabled="!disableSelectAndInput" class="custom-control-input" type="checkbox" id="chbx-carbonVis" /><label
                        for="chbx-carbonVis" class="custom-control-label">Carbon labels</label></td>
                        <td style="width: 142px;"><input :disabled="!disableSelectAndInput" class="custom-control-input" type="checkbox" id="chbx-map" checked="checked" /><label
                        for="chbx-map" class="custom-control-label">Atom maps visible</label></td>
                        <td style="width: 110px;"><input :disabled="!disableSelectAndInput" class="custom-control-input" type="checkbox" id="chbx-coloring" checked="checked" /><label
                        for="chbx-coloring" class="custom-control-label">CPK coloring</label></td>
                    </tr>
                    </table>          
                    <table width="70%" border="1" bordercolor="#febb80" class="sample" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>
                          <iframe v-bind:style="disableSelectAndInput == false ? 'pointer-events: none' : ''" id="sketch"	src="<../../marvinjs/editorws.html" style="overflow: hidden; min-width: 750px; min-height: 350px; border: 1px solid darkgray;">
                            </script>
                          </iframe>
                        </td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
                        </div>
                  <div class="row m-widget14 head-no-1" style="margin-left: 0px;">  
                        <b-form-radio v-model="chemistrySearchRadioVal" value="manual" @change="radioChanged" ></b-form-radio>                     
                        <div class="col-4 inpt-bas-top" style="top:10px">
                            <b-form-select class="card-shadow form-control" 
                              v-model="selectedOptionVal" 
                              :options="options"  
                              disabled-field="disabled" 
                              :disabled="disableSelectAndInput" 
                              @change="chemistrySearchSelectChange" size="sm">
                            </b-form-select>
                        </div>
                    <div class="col-5" v-if="uploadedFileName ==''">
                        <b-form-input
                          class="card-shadow form-control" 
                          v-model="searchedTerm" 
                          placeholder="Search Term" 
                          :disabled="disableSelectAndInput || disableIfNoOption" 
                          @change="searchTermChange"
                          @keydown="preventSpaceKeyPress($event)"
                          @keyup="captureSearchQuery">
                        </b-form-input>          
                                      <!-- ENDS Multi Select drop down control -->
                    </div>
                <div class="col-5" v-else>
                    <div class="freeze-field">{{uploadedFileName}}</div>
                </div>
              <div class="col-1" style="padding: 5px 0px 0px 0px;"> 
                <!-- File upload comonent -->
                <q-uploader
                  url
                  class="col"
                  :multiple="false"
                  accept=".txt"
                  @added="fileAdded"
                  :disable="disableSelectAndInput || disableIfNoOption"
                  ref="chemistrySearchUpload"
                  style="max-width: 50px; padding-left: 0px;"
                  flat>
                  <template v-slot:header="scope">
                    <div class="row no-wrap bg-white" style="width:15px; left:0px;">
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
                <div class=""></div>
              </div>
                <div class="col-1" style="padding-left: 20px;"> 
                <!-- Refressing the entire row inputs --> 
                <a href="javascript:void(0)" :disabled="disableSelectAndInput || disableIfNoOption" @click="refreshFields($event)" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-refresh refresh-btn-stl" ></i> </a> 
                </div>
                        </div>
              <div class="heght-10px"></div>
                        <!--end:: Widgets/Daily Sales--> 
                      </div>
                      <div class="col-xl-4" style="width:auto;"> 
                        <!--begin:: Widgets/Profit Share-->
                        <div class="m-widget14">
                          <div class="m-widget14__header">
                            <h3 class="m-widget14__title"> Structure Category / Search Options</h3>
                          </div>
                          <div class="row  align-items-center">
                            <div class="col">
                              <div class="m-widget14__legends">
                                <div class="m-form__group form-group"> 
                                  <!--<label>Bold Radios</label> -->
                                  <div class="m-radio-list">
                  <div class="">
                    <b-form-radio v-model="structureCategoryRadio" :disabled="disableAllRadioAndCheckbox" value="exact" @input="structureCategoryChange" >Exact Structure</b-form-radio>
                  </div>
                  <div class="">
                    <b-form-radio v-model="structureCategoryRadio"  :disabled="disableAllRadioAndCheckbox" value="subStructure" @input="structureCategoryChange" >Sub-Structure</b-form-radio>
                  </div>
                  <div class="">
                    <b-form-radio v-if="chemistrySearchRadioVal === 'drawStructure'" v-model="structureCategoryRadio" :disabled="disableAllRadioAndCheckbox" value="similarity" @input="structureCategoryChange" >Similarity %</b-form-radio>
                  </div>
                    <div class="" v-if="chemistrySearchRadioVal === 'drawStructure'">
                      <!-- <div class="col-6">
                        <span>Minimum: <input class="form-control m-input" placeholder="Min %" type="number" value="Minimum Value"></span> 
                      </div>	
                      <div class="col-6">
                        <span>Maximum: <input class="form-control m-input" placeholder="Max %" type="number" value="Maximum Value"></span>
                      </div>   -->
                      <q-range
                      v-model="minMaxRange"
                      :min="0"
                      :max="100"
                      :step="1"
                      class="chemistrySlider"
                      label-always
                      :color="minMaxRange.min < 70 ? 'red' : 'green'"
                      :disable="!enableMinMax || disableAllRadioAndCheckbox"
                      ></q-range>
                    </div>									  
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
              <div class="m-widget14__header">
                            <h3 class="m-widget14__title"> Structure Feature</h3>                            
              </div>
                        <div class="row  align-items-center">
                            <div class="col">
                              <div class="m-widget14__legends">
                                <div class="m-form__group form-group">
                                  <b-form-select
                                  v-model="structureFeatureSelectedValue"
                                  disabled-field="disabled" 
                                  :disabled ="disableStructureFeatureSelect || disableAllRadioAndCheckbox"  
                                  :options="structureFeatureSelectOptions" 
                                  @input="structureFeatureSelectChange">
                                  </b-form-select> 
                                  <div style="height:10px;"></div>                 	
                                  <div class="m-form__group form-group">
                                      <div class="m-widget14__header">
                                        <h3 class="m-widget14__title"> Restrict To</h3>
                                      </div>
                                      <div class="m-checkbox-list">	
                                        <b-form-checkbox-group id="restrict-to" v-model="restrictTo" stacked name="restrict-to" >
                                          <b-form-checkbox value="metabolite" v-if="!disableMetaAndTox" >Metobolite</b-form-checkbox>
                                          <b-form-checkbox value="tox">Toxic</b-form-checkbox>
                                          <b-form-checkbox value="npd" :disabled="disableNpd"> Natural Product</b-form-checkbox>
                                        </b-form-checkbox-group>
                                      </div>
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
                </div>
                

                
                
                
                
                
                
                <!-- ENDS Tab 1 Container part -->                    
              </div>
  <div class="modal-footer" style="margin: 0px -13px -13px -13px;">
      <button type="button" class="btn btn-danger btn-sm" @click="onClear" >Clear</button>
      <button type="button" class="btn btn-excelra btn-sm" @click="onAdd" >Add</button>
    </div>   
    <b-modal id="modal-center" v-model="showAlertForEmptyData" title="Alert" hide-footer hide-header-close :no-close-on-backdrop="true" :no-close-on-esc="true" centered>
    
    <div style="z-index: 112" class="modal-custom-content" v-if="showAlertForEmptyData">
     {{emptyErrorAlertMessage}}
      <div class="modal-footer" style="background-color:#fff;">
        <button type="button" class="btn btn-excelra"  @click="showAlertForEmptyData = false">OK</button>
      </div>
    </div>
   </b-modal>
    <app-loader v-if="showLoading"></app-loader>
  </div>`,
  mounted() {
    let currentItemIndex = this.$store.state.advanceSearch.currentItemIndex;
    let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
      currentItemIndex
    ];
    let chemSearch = currentItem.advanceSearch.chemistrySearch;
    if (chemSearch && currentItem.isStructureAdvanceSearch) {
      if (currentItem.advanceSearchSelectedValue) {
        this.chemistrySearchRadioVal =
          currentItem.advanceSearchSelectedValue == "draw"
            ? "drawStructure"
            : "manual";
      }
      if (currentItem.advanceSearchSelectedValue == "readOnly") {
        this.selectedOptionVal = chemSearch.strReadonly;
        if (this.selectedOptionVal == "inchikey") {
          this.hideSearchOptions = true;
        } else {
          this.hideSearchOptions = false;
        }
        this.disableSelectAndInput = false;
        this.disableIfNoOption = false;
        if (chemSearch.strComboFileData.length > 0) {
          this.uploadedFileData = chemSearch.strComboFileData;
          this.uploadedFileName = currentItem.fileName;
        }
        this.eneteredInputVal = chemSearch.strComboData;
        let searchField = "";
        for (let item of chemSearch.strComboData) {
          searchField += item + "|";
        }
        this.searchedTerm = searchField.substr(0, searchField.length - 1);
      }

      this.structureCategoryRadio = chemSearch.strCategory;

      if (chemSearch.strCategory == "similarity") {
        this.minMaxRange = {
          min: chemSearch.strSimilarityMinValue,
          max: chemSearch.strSimilarityMaxValue
        };
      }

      this.structureFeatureSelectedValue = chemSearch.strFeature;
      if (
        this.$store.state.structureSearch.userSelectedRestrictToValues.length >
        0
      ) {
        this.restrictTo = this.$store.state.structureSearch.userSelectedRestrictToValues;
      }
    }
    if (currentItem.advanceSearchSelectedValue == "draw") {
      if (this.$store.state.structureSearch.drawStructureMolData != null) {
        this.smileStructure = this.$store.state.structureSearch.drawStructureMolData;
      }
      this.populateStructure();
      this.disableClear = false;
    }
  },
  data() {
    return {
      minMaxRange: {
        min: 70,
        max: 100
      },
      showLoading: false,
      searchedTerm: "",
      restrictTo: [],
      structureFeatureSelectedValue: "desalted",
      structureCategoryRadio: "subStructure",
      chemistrySearchRadioVal: "drawStructure",
      selectedOptionVal: null,
      /* disable varibales */
      disableSelectAndInput: true,
      disableAllRadioAndCheckbox: false,
      disableMetaAndTox: false,
      disableNpd: false,
      disableStructureFeatureSelect: false,
      disableIfNoOption: true,
      disableAdd: true,
      disableClear: true,
      enableMinMax: false,
      showError: false,
      hideSearchOptions: false,
      eneteredInputVal: [],
      structureFeatureSelectOptions: [
        {
          value: "desalted",
          text: "Desalted",
          disabled: false
        },
        {
          value: "skeleton",
          text: "Skeleton",
          disabled: false
        },
        {
          value: "framework1",
          text: "Framework 1",
          disabled: false
        },
        {
          value: "framework2",
          text: "Framework 2",
          disabled: false
        }
      ],
      options: [
        { value: null, text: "Please Select", disabled: true },
        {
          value: "smiles",
          text: "SMILES/SMARTS",
          disabled: false
        },
        {
          value: "inchikey",
          text: "InChi/InChiKey",
          disabled: false
        }
      ],
      uploadedFileName: "",
      uploadedFileData: [],
      smileStructure: null,
      showAlertForEmptyData: false,
      emptyErrorAlertMessage:''
    };
  },
  methods: {
    populateStructure() {
      that = this;

      var p = MarvinJSUtil.getEditor("#sketch");
      p.then(
        function(sketcherInstance) {
          marvinSketcherInstance = sketcherInstance;
          marvinSketcherInstance
            .importStructure("mol", that.smileStructure)
            .catch(function(error) {
              alert(error);
            });
          marvinSketcherInstance.importStructure("mol", that.smileStructure);
        },
        function(error) {
          alert("Cannot retrieve sketcher instance from iframe:" + error);
        }
      );
    },
    structureFeatureSelectChange(event) {
      if (event == "desalted") {
        this.restrictTo = [];
        this.disableMetaAndTox = false;
        this.disableNpd = false;
      } else {
        this.restrictTo = [];
        this.restrictTo.push("npd");
        this.disableMetaAndTox = true;
       // this.disableNpd = true;
      }
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
    searchTermChange(event) {
      if (event) {
        this.disableAdd = false;
        this.disableClear = false;
      }
    },
    chemistrySearchSelectChange(val) {
      this.eneteredInputVal = [];
      this.searchedTerm = "";
      this.structureFeatureSelectedValue = "desalted";
      this.restrictTo = [];
      this.structureCategoryRadio = "subStructure";
      this.refreshFields();
      this.resetFields();
      if (val == "inchikey") {
        this.hideSearchOptions = true;
        this.disableAllRadioAndCheckbox = false;
        this.disableIfNoOption = false;
      } else {
        this.disableAllRadioAndCheckbox = false;
        this.hideSearchOptions = false;
        this.disableIfNoOption = false;
      }
      this.selectedOptionVal = val;
    },

    radioChanged(value) {
      if (value == "drawStructure") {
        this.disableSelectAndInput = true;
        this.disableAllRadioAndCheckbox = false;
        this.uploadedFileData = [];
        this.uploadedFileName = "";
        this.eneteredInputVal = [];
      } else {
        this.disableSelectAndInput = false;
        this.disableAllRadioAndCheckbox = true;
        this.smileStructure = null;
        this.populateStructure();
      }
      this.disableAdd = false;
      this.disableClear = false;
      this.resetFields();
    },
    fileAdded(event) {
      const input = event;
      let filterFileContents = [];
      this.uploadedFileName = input[0].name;
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
          this.uploadedFileData = filterFileContents;
          this.eneteredInputVal = [];
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]["advanceSearch"].chemistrySearch = null;
          if (filterFileContents.length > 0) {
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
    resetFields() {
      this.structureFeatureSelectedValue = "desalted";
      this.selectedOptionVal = null;
      this.structureCategoryRadio = "subStructure";
      this.minMaxRange = {
        min: 70,
        max: 100
      };
      this.searchedTerm = "";
      this.restrictTo = [];

      this.structureCategoryRadio = "subStructure";
      this.selection = ["desalted"];
      this.enableMinMax = false;

      this.hideSearchOptions = false;
      this.disableAdd = true;
      this.disableIfNoOption = true;
    },
    refreshFields(event) {
      this.uploadedFileData = [];
      this.uploadedFileName = "";
      this.searchedTerm = "";
      this.$refs.chemistrySearchUpload.reset();
      this.disableAdd = true;
    },
    structureCategoryChange(val) {
      this.enableMinMax = false;
      if (val == "similarity") {
        this.enableMinMax = true;
      }
    },

    onAdd() {
      if (this.chemistrySearchRadioVal == "drawStructure") {
        that = this;
        var marvinSketcherInstance;

        var molPromise = MarvinJSUtil.getEditor("#sketch");

        molPromise.then(
          function(sketcherInstance) {
            marvinSketcherInstance = sketcherInstance;
            marvinSketcherInstance.exportStructure("smiles").then(
              function(source) {
                console.log(source);
                if (source.length == 0) {
                  that.showAlertForEmpty("Please draw structure");
                } else if (source.length <= 5) {
                  that.showAlertLessThanFive();
                } else if (source.length > 5 && source.length <= 10) {
                  that.showBetweenFiveToTenAlert();
                } else {
                  that.confirm();
                }
              },
              function(error) {
                alert("Molecule export failed:" + error);
              }
            );
          },
          function(error) {
            alert("Cannot retrieve sketcher instance from iframe:" + error);
          }
        );
      } else {
        if(!this.selectedOptionVal) {
          that.showAlertForEmpty("Please select option");
          return;
        }
        if(this.uploadedFileData.length==0 && this.eneteredInputVal=='') {
          that.showAlertForEmpty("Please enter search term");
          return;
        }
        
        if (this.uploadedFileData.length > 0) {
          this.validateSmilesLength(this.uploadedFileData);
        } else if (
          this.uploadedFileData.length == 0 &&
          this.eneteredInputVal.length > 0
        ) {
          this.validateSmilesLength(this.eneteredInputVal);
        }
      }
    },
    validateSmilesLength(val) {
      let showLessThanFiveAlert = false;
      let showFiveToTenAlert = false;
      for (let item of val) {
        if (item.length <= 5) {
          showLessThanFiveAlert = true;
          showFiveToTenAlert = false;
          break;
        } else if (item.length > 5 && item.length <= 10) {
          showLessThanFiveAlert = false;
          showFiveToTenAlert = true;
          break;
        }
      }
      if (showLessThanFiveAlert) {
        this.showAlertLessThanFive();
      } else if (showFiveToTenAlert) {
        this.showBetweenFiveToTenAlert();
      } else {
        this.confirm();
      }
    },
    showAlertLessThanFive() {
      this.$bvModal.msgBoxConfirm(
        "GOSTAR has identified the search to be generic. Please use more limits/filters after clicking OK or click Cancel.",
        {
          centered: true,
          style: {
            zIndex: 111111111
          }
        }
      );
    },
    showAlertForEmpty(errorMsg) {
      this.showAlertForEmptyData = true;
      this.emptyErrorAlertMessage = errorMsg;
    },
    showBetweenFiveToTenAlert() {
      this.$bvModal
        .msgBoxConfirm(
          "GOSTAR has identified large number of compounds. Please click OK to fetch results or click Cancel.",
          {
            centered: true
          }
        )
        .then(value => {
          if (value) this.onAddConfirm();
          else return;
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
          this.onAddConfirm();
        })
        .catch(err => {
          // An error occurred
        });
    },
    onAddConfirm() {
      let currentItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ];

      currentItem.structureAdvanceSearchTab = "chemistry";
      currentItem.hasValue = true;
      currentItem.isStructureAdvanceSearch = true;

      this.$store.commit("structureSearch/RESET_CHEMISTRY_SEARCH");
      let chemistrySearch = this.$store.state.structureSearch
        .chemistryAdvanceSearch;

      if (this.chemistrySearchRadioVal == "drawStructure") {
        that = this;
        currentItem.advanceSearchSelectedValue = "draw";

        that.$store.state.structureSearch.selectedValueName = "Draw Structure";

        var marvinSketcherInstance;

        var molPromise = MarvinJSUtil.getEditor("#sketch");

        molPromise.then(
          function(sketcherInstance) {
            marvinSketcherInstance = sketcherInstance;
            marvinSketcherInstance.exportStructure("mol").then(
              function(source) {
                that.$store.state.structureSearch.drawStructureMolData = source;
                that.smileStructure = source;
              },
              function(error) {
                alert("Molecule export failed:" + error);
              }
            );
          },
          function(error) {
            alert("Cannot retrieve sketcher instance from iframe:" + error);
          }
        );

        var p = MarvinJSUtil.getEditor("#sketch");

        p.then(
          function(sketcherInstance) {
            marvinSketcherInstance = sketcherInstance;
            marvinSketcherInstance.exportStructure("smiles").then(
              function(source) {
                if (source == "") {
                  that.showAlertOnAdd("draw");
                  return;
                }
                if (chemistrySearch) {
                  chemistrySearch.strDraw = source;
                }
               // chemistrySearch.strDraw = source;
                chemistrySearch.strCategory = that.structureCategoryRadio;
                chemistrySearch.strReadonly = "draw";
                if (that.structureCategoryRadio == "similarity") {
                  chemistrySearch.strSimilarityMinValue = that.minMaxRange.min;
                  chemistrySearch.strSimilarityMaxValue = that.minMaxRange.max;
                }
                chemistrySearch.strFeature = that.structureFeatureSelectedValue;
                chemistrySearch.strFeatureOptions =
                  that.restrictTo.length == 0
                    ? ["metabolite", "tox", "npd"]
                    : that.restrictTo;
                that.$store.state.structureSearch.userSelectedRestrictToValues =
                  that.restrictTo;
                currentItem.advanceSearch.chemistrySearch = chemistrySearch;
                that.getSearchResultsAndCount();
                that.closeAdvanceSearchModal();
              },
              function(error) {
                alert("Molecule export failed:" + error);
              }
            );
          },
          function(error) {
            alert("Cannot retrieve sketcher instance from iframe:" + error);
          }
        );
      } else {
        if (
          this.uploadedFileData.length == 0 &&
          this.eneteredInputVal.length == 0
        ) {
          this.showAlertOnAdd("readOnly");
          return;
        }
        currentItem.advanceSearchSelectedValue = "readOnly";
        this.$store.state.structureSearch.selectedValueName = this.selectedOptionVal;

        chemistrySearch.strReadonly = this.selectedOptionVal;
        chemistrySearch.strFeature = this.structureFeatureSelectedValue;
        chemistrySearch.strFeatureOptions =
          this.restrictTo.length == 0
            ? ["metabolite", "tox", "npd"]
            : this.restrictTo;
        this.$store.state.structureSearch.userSelectedRestrictToValues = this.restrictTo;
        chemistrySearch.strComboData = this.eneteredInputVal;

        chemistrySearch.strComboFileData =
          this.uploadedFileData.length > 0 ? this.uploadedFileData : [];

        currentItem.fileData = this.uploadedFileData;
        currentItem.fileName = this.uploadedFileName;
        chemistrySearch.strCategory = this.structureCategoryRadio;
        if (this.selectedOptionVal == "smiles") {
          if (this.structureCategoryRadio == "similarity") {
            chemistrySearch.strSimilarityMinValue = this.minMaxRange.min;
            chemistrySearch.strSimilarityMaxValue = this.minMaxRange.max;
          }
        }
        this.$store.state.structureSearch.userSelectedRestrictToValues = this.restrictTo;

        currentItem.advanceSearch.chemistrySearch = chemistrySearch;
        this.getSearchResultsAndCount();
        this.closeAdvanceSearchModal();
      }
    },
    showAlertOnAdd(selectedRadioValue) {
      let errorMessage = "";
      errorMessage =
        selectedRadioValue == "draw"
          ? "Please draw a structure in draw area."
          : "Please enter a value in search input field";
      this.$q.notify({
        message: errorMessage,
        position: "top",
        color: "deep-orange-5",
        timeout: 2000
      });
    },
    onClear() {
      this.resetFields();
      this.refreshFields();
      this.disableAdd = true;
      this.$store.state.structureSearch.drawStructureMolData = null;
      this.smileStructure = null;
      this.populateStructure();
      this.$store.commit("structureSearch/RESET_CHEMISTRY_SEARCH");
      let currentItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ];
      this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ].userSelectedRestrictToValues = [];
      this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ].drawStructureMolData = null;
      this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ].isStructureAdvanceSearch = false;
      this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ].hasValue = false;
      this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ].advanceSearchSelectedValue = null;
      this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ].advanceSearch.chemistrySearch = this.$store.state.structureSearch.chemistryAdvanceSearch;

      this.uploadedFileData = [];
      this.uploadedFileName = "";
      this.eneteredInputVal = [];
      this.disableIfNoOption = true;
      this.chemistrySearchRadioVal = "drawStructure";
      this.disableSelectAndInput = true;
      this.getSearchResultsAndCount();
      this.getInitalCounts();
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
    getSearchResultsAndCount() {
      this.showLoading = true;
      let postParam = [];
      this.$store.commit("simpleSearch/GET_SEARCH_FINAL_PAYLOAD");
      postParam = this.$store.state.simpleSearch.searchPostObj;
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_RESULTS_AND_COUNT", postParam)
        .then(res => {
          this.searchCountData = res.data;
          this.$store.commit("simpleSearch/SET_SEARCH_COUNT", res.data);
          this.$store.state.advanceSearch.showCountsLoader = false;
          this.showLoading = false;
          this.saveStoreData();
        })
        .catch(e => {
          this.$store.state.advanceSearch.showCountsLoader = false;
          this.showLoading = false;
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
    closeAdvanceSearchModal() {
      this.$emit("adv-popup-closed", true);
    }
  }
});

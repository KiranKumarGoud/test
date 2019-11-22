Vue.component("structural-property", {
  template: `
  <div>
<!--begin::Item-->
<div class="m-accordion__item">
   <div class="m-accordion__item-body" id="m_accordion_1_item_1_body"  role="tabpanel" aria-labelledby="m_accordion_1_item_1_head" data-parent="#m_accordion_1">
      <div class="m-accordion__item-content">
         <!-- STARTS Accordian 01 Container block for 'Structural Property' -->
         <div class="col-12">
            <!--begin:: Widgets/Daily Sales-->									
            <div>
               <div class="m-widget14 m-widget-styl">
                  <div class="m-checkbox-inline">
                     <div>
                        <div class="row">
                           <div class="col-lg-6">
                              <label class="m-option">
                                 <b-form-checkbox v-model="ruleVal" value="rule5" color="grey-13" @change="ruleChanged($event)" >
                                    Lipinski's Rule #5
                                 </b-form-checkbox>
                              </label>
                           </div>
                           <div class="col-lg-6">
                              <label class="m-option">
                                 <b-form-checkbox v-model="ruleVal" value="rule3" color="grey-13" @change="ruleChanged($event)" >
                                    Lipinski's Rule #3
                                 </b-form-checkbox>
                              </label>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="row">
               <!-- Table block layout -->
               <div class="m-section__content custom-slider">              
                  <div class="table-responsive custom-slider" style="overflow-x:hidden;">
                     <table class="table table-hover custom-slider" style="margin:20px;">
                        <tbody>
                           <tr v-for="(item,itemIndex) in structuralPropData">
                              <td style="width: 30%; padding: 27px 0px 0px 0px;">
                                 <label class="m-checkbox m-checkbox--bold heght-25px">
                                    <b-form-checkbox    
                                       v-model="item.model"       
                                       :disabled="item.disabled"
                                       :value="item.value"
                                       @change="structuralPropCheckboxChange($event,item,itemIndex)">
                                       {{item.name}}
                                    </b-form-checkbox>
                                 </label>
                              </td>
                              <td style="width: 70%; padding: 1.55rem 58px .75rem .75rem;">
                              <div>
                                <q-range
                                v-model="item.range"
                                :min="item.minVal"
                                :max="item.maxVal"
                                :step="0.01"
                                :decimals="2"
                                class="chemistrySlider"
                                label-always
                                color="light-blue-9"
                                :disable="item.disableMinMax"
                              ></q-range>
                              </div>
                              </td>
                           </tr>
                        </tbody>
                     </table>
                     </div>
                  </div>
               </div>
               <!--end:: Widgets/Daily Sales--> 
            </div>
            <!-- ENDS Accordian 01 Container block for 'Structural Propert' -->    
         </div>
      </div>
   </div>
   <!--end::Item-->
   <div class="modal-footer" style="margin: 0px -13px -13px -13px;">
      <button type="button" class="btn btn-danger btn-sm" @click="onClear" :disabled="disableClear">Clear</button>
      <button type="button" class="btn btn-excelra btn-sm" @click="confirm" :disabled="disableAdd">Add</button>     
   </div>
   <app-loader v-if="showLoading"></app-loader>
</div>`,
  mounted() {
    let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
      this.$store.state.advanceSearch.currentItemIndex
    ];

    this.ruleVal = this.$store.state.structureSearch.selectedRule;

    this.structuralPropData = JSON.parse(
      JSON.stringify(this.$store.state.structureSearch.structuralPropertyRows)
    );
    if (this.$store.state.structureSearch.minMaxData) {
      this.minMaxData = JSON.parse(
        JSON.stringify(this.$store.state.structureSearch.minMaxData)
      );
    }
    if (!currentItem.hasValue) {
      this.getMinMaxValues();
    }
  },
  created() {},
  data() {
    return {
      ruleVal: "rule5",
      disableClear: false,
      disableAdd: false,
      structuralPropData: [],
      minMaxData: null,
      showLoading: false
    };
  },
  methods: {
    getMinMaxValues() {
      this.showLoading = true;
      this.$store
        .dispatch("structureSearch/GET_MIN_MAX_VALUES", "")
        .then(res => {
          this.showLoading = false;
          this.minMaxData = res.data;
          this.$store.state.structureSearch.minMaxData = res.data;
          this.setMinMaxValues(res.data, "rule5");
        })
        .catch(error => {
          console.log(error);
        });
    },
    setMinMaxValues(resData, selectedRule) {
      for (let rul of this.$store.state.structureSearch[selectedRule]) {
        for (let item of this.structuralPropData) {
          if (item.value == rul.model) {
            item.range = JSON.parse(JSON.stringify(rul.range));
            item.disabled = JSON.parse(JSON.stringify(rul.disabled));
          }
          item.range.min = resData[item.apiMin];
          item.minVal = item.range.min;
          item.maxVal = item.range.max;
        }
      }
      for (let row of this.structuralPropData) {
        if (row.model === "") {
          row.range.min = resData[row.apiMin];
          row.range.max = resData[row.apiMax];
          row.minVal = row.range.min;
          row.maxVal = row.range.max;
        }
      }
    },

    ruleChanged(event) {
      this.disableAdd = false;
      this.disableClear = false;
      if (event == "rule3") {
        this.setRuleBasedValues("rule3");
      } else if (event == "rule5") {
        this.setRuleBasedValues("rule5");
      } else if (!event) {
        this.disableAdd = true;
        this.disableClear = true;
        for (let item of this.structuralPropData) {
          item.model = "";
          item.disabled = false;
          item.minVal = this.minMaxData[item.apiMin];
          item.maxVal = this.minMaxData[item.apiMax];
          item.range.min = this.minMaxData[item.apiMin];
          item.range.max = this.minMaxData[item.apiMax];
          item.disableMinMax = true;
        }
      }
    },
    setRuleBasedValues(selectedRule) {
      for (let rul of this.$store.state.structureSearch[selectedRule]) {
        for (let item of this.structuralPropData) {
          if (item.value == rul.model) {
            item.range = JSON.parse(JSON.stringify(rul.range));
            item.disabled = JSON.parse(JSON.stringify(rul.disabled));
            item.disableMinMax = true;
            item.range.min = this.minMaxData[item.apiMin];
            item.minVal = item.range.min;
            item.maxVal = item.range.max;
            if (item.disabled)
              item.model = JSON.parse(JSON.stringify(rul.model));
          }
        }
      }
      if (selectedRule == "rule5") {
        let filterRotatble = this.structuralPropData.filter(
          p => p.model == "freeRotatableBonds" || p.model == "psa"
        );
        if (filterRotatble.length > 0) {
          for (let item of filterRotatble) {
            item.model = "";
            item.disabled = false;
            item.disableMinMax = true;
          }
        }
      }
      if (selectedRule == "rule3") {
        let filterRotatble = this.structuralPropData.filter(
          p => p.model == "psa"
        );
        if (filterRotatble.length > 0) {
          for (let item of filterRotatble) {
            item.model = "";
            item.disabled = false;
            item.disableMinMax = true;
          }
        }
      }
      for (let row of this.structuralPropData) {
        if (row.model === "") {
          row.range.min = this.minMaxData[row.apiMin];
          row.range.max = this.minMaxData[row.apiMax];
          row.minVal = row.range.min;
          row.maxVal = row.range.max;
        }
      }
    },
    structuralPropCheckboxChange(event, item, itemIndex) {
      item.disableMinMax = !item.model ? false : true;
      setTimeout(() => {
        let filterChecked = this.structuralPropData.filter(p => p.model != "");
        if (filterChecked.length > 0) {
          this.disableAdd = false;
          this.disableClear = false;
        } else {
          this.disableAdd = true;
          this.disableAdd = true;
        }
      }, 0);
      if (!event) {
        item.range.min = this.minMaxData[item.apiMin];
        item.range.max = this.minMaxData[item.apiMax];
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
          let currentItemIndex = this.$store.state.advanceSearch
            .currentItemIndex;
          let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
            currentItemIndex
          ];
          this.$store.state.structureSearch.selectedRule = this.ruleVal;
          currentItem["hasValue"] = true;
          currentItem.structureAdvanceSearchTab = "property";
          currentItem.advanceSearchSelectedValue = "structural";
          currentItem.isStructureAdvanceSearch = true;

          this.$store.commit("structureSearch/RESET_STRUCTURAL_PROPERTIES");
          let structuralProp = this.$store.state.structureSearch
            .propertyAdvanceSearch.structuralProperties;
          for (let item of this.structuralPropData) {
            if (item.model != "") {
              structuralProp[item.apiProp].minValue = item.range.min;
              structuralProp[item.apiProp].maxValue = item.range.max;
            }
          }
          let propertySearch = {
            structuralProperties: structuralProp,
            physicoChemicalProperties: null
          };
          currentItem.advanceSearch.propertySearch = propertySearch;
          this.$store.state.structureSearch.structuralPropertyRows = JSON.parse(
            JSON.stringify(this.structuralPropData)
          );
          console.log(JSON.parse(JSON.stringify(structuralProp)));
          console.log(this.structuralPropData);

          this.getSearchResultsAndCount();
          this.closeAdvanceSearchPopup();
        })
        .catch(err => {
          // An error occurred
        });
    },
    getSearchResultsAndCount() {
      let postParam = [];
      this.$store.commit("simpleSearch/GET_SEARCH_FINAL_PAYLOAD");
      postParam = this.$store.state.simpleSearch.searchPostObj;
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_RESULTS_AND_COUNT", postParam)
        .then(res => {
          this.searchCountData = res.data;
          this.$store.commit("simpleSearch/SET_SEARCH_COUNT", res.data);
          this.$store.state.advanceSearch.showCountsLoader = false;
          this.$store.state.advanceSearch.showCountsLoader = false;
          this.saveStoreData();
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
    getInitalCounts() {
      this.$store
        .dispatch("simpleSearch/GET_SEARCH_DEFAULT_COUNTS")
        .then(response => {
          if (response.status == 200) {
            this.$store.state.simpleSearch.searchCount = response.data;
          }
        });
    },
    onClear() {
      let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ];
      currentItem.hasValue = false;
      currentItem.structureAdvanceSearchTab = null;
      currentItem.advanceSearchSelectedValue = null;
      currentItem.isStructureAdvanceSearch = false;
      this.$store.commit("structureSearch/RESET_STRUCTURAL_PROPERTIES");
      this.structuralPropData = JSON.parse(
        JSON.stringify(this.$store.state.structureSearch.structuralPropertyRows)
      );
      this.getInitalCounts();
      this.ruleVal = "rule5";
      this.disableAdd = false;
      this.disableClear = false;
      this.setMinMaxValues(this.minMaxData, "rule5");
      this.setRuleBasedValues("rule5");

      this.$store.state.structureSearch.structuralPropertyRows = JSON.parse(
        JSON.stringify(this.structuralPropData)
      );
    },
    closeAdvanceSearchPopup() {
      this.$emit("adv-popup-closed", true);
    }
  }
});

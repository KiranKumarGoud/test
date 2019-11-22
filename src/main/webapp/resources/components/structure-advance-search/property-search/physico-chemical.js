Vue.component("physico-chemical", {
  template: `
  <div>
    <!--begin::Item-->
    <div class="m-accordion__item">						
      <div class="m-accordion__item-body" id="m_accordion_1_item_2_body" role="tabpanel" aria-labelledby="m_accordion_1_item_2_head" data-parent="#m_accordion_1">
        <div class="m-accordion__item-content">
          <!-- STARTS Accordian 02 Container block for 'Physico-Chemical Properties' -->
          <div class="col-xl-12"> 
            <!--begin:: Widgets/Profit Share-->									
            <div calss="row">
              <div class="col-lg-12">   
                <div class="m-widget14 m-option mrgn-10px">
                  <div class="m-checkbox-inline" id="select_all2">	
                    <div class="m-checkbox-list">
                      <b-form-checkbox    
                        v-model="selectAll"       
                        value="selectAll"
                        @change="selectAllChange">
                        Select All
                      </b-form-checkbox>
                    </div>   
                  </div>  
                </div>
              </div>  
            </div>						  
            <div class="row">
              <!-- Table block layout -->
              <div class="m-section__content custom-slider">
                <div class="table-responsive custom-slider" style="overflow-x:hidden">
                  <table class="table table-hover custom-slider" style="margin:20px;">
                    <tbody>
                      <tr v-for="(item,itemIndex) in physicoChemData">
                        <td style="width: 30%; padding: 27px 0px 0px 28px;">
                          <b-form-checkbox    
                          v-model="item.model"       
                          :value="item.value"
                          @change="physicoCheckboxChange($event,item,itemIndex)"
                          >
                          {{item.name}}
                          </b-form-checkbox>
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
                            :disable="item.disableMinMax">
                            </q-range>
                          </div>
                        </td>
                      </tr>														
                    </tbody>
                  </table>
                </div>
              </div>
            </div>						  
          </div>										
          <!-- ENDS Accordian 02 Container block for 'Physico-Chemical Properties' -->   
        </div>
      </div>
    </div>
    <!--end::Item-->
    <div class="modal-footer" style="margin: 0px -13px -13px -13px;">
      <button type="button" class="btn btn-danger btn-sm" :disabled="disableClear" @click="onClear" >Clear</button>
      <button type="button" class="btn btn-excelra btn-sm":disabled="disableAdd" @click="confirm" >Add</button>
    </div>
    <app-loader v-if="showLoading"></app-loader>
</div>`,
  mounted() {
    let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
      this.$store.state.advanceSearch.currentItemIndex
    ];

    if (this.$store.state.structureSearch.isAllPhysicoPropsSelected) {
      this.selectAll = this.$store.state.structureSearch.isAllPhysicoPropsSelected;
    }
    this.physicoChemData = JSON.parse(
      JSON.stringify(this.$store.state.structureSearch.physicoChemicalRows)
    );
    let filterChecked = this.physicoChemData.filter(p => p.model != "");
    if (filterChecked.length > 0) {
      this.disableAdd = false;
      this.disableClear = false;
    }
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
      physicoChemData: [],
      selectAll: "",
      disableAdd: true,
      disableClear: true,
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
          this.$store.state.structureSearch.minMaxData = res.data;
          this.minMaxData = res.data;
          this.setMinMaxValues(res.data);
        })
        .catch(error => {
          console.log(error);
        });
    },
    setMinMaxValues(resData) {
      for (let item of this.physicoChemData) {
        item.minVal = resData[item.apiMin];
        item.maxVal = resData[item.apiMax];
        item.range.min = resData[item.apiMin];
        item.range.max = resData[item.apiMax];
      }
    },
    selectAllChange(event) {
      console.log(event);
      if (event) {
        for (let item of this.physicoChemData) {
          item.model = item.value;
          item.disableMinMax = false;
        }
        this.$store.state.structureSearch.isAllPhysicoPropsSelected =
          "selectAll";
        this.disableAdd = false;
        this.disableClear = false;
      } else {
        for (let item of this.physicoChemData) {
          item.model = "";
          item.disableMinMax = true;
        }
        this.$store.state.structureSearch.isAllPhysicoPropsSelected = "";
        this.disableAdd = true;
        this.disableClear = true;
      }
    },
    physicoCheckboxChange(event, item, itemIndex) {
      item.disableMinMax = !item.model ? false : true;
      let isAllChecked = false;

      setTimeout(() => {
        let filterChecked = this.physicoChemData.filter(p => p.model != "");

        if (filterChecked.length > 0) {
          this.disableAdd = false;
          this.disableClear = false;
        } else {
          this.disableAdd = true;
          this.disableClear = true;
        }
        for (let itm of this.physicoChemData) {
          isAllChecked = itm.model != "" ? true : false;
          if (!isAllChecked) {
            this.selectAll = "";
            this.$store.state.structureSearch.isAllPhysicoPropsSelected = "";
            break;
          } else {
            this.selectAll = "selectAll";
            this.$store.state.structureSearch.isAllPhysicoPropsSelected =
              "selectAll";
          }
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
          currentItem["hasValue"] = true;
          currentItem.structureAdvanceSearchTab = "property";
          currentItem.advanceSearchSelectedValue = "physico";
          currentItem.isStructureAdvanceSearch = true;

          this.$store.commit(
            "structureSearch/RESET_PHYSICO_CHEMICAL_PROPERTIES"
          );
          let physicoChemProp = this.$store.state.structureSearch
            .propertyAdvanceSearch.physicoChemicalProperties;
          for (let item of this.physicoChemData) {
            if (item.model != "") {
              physicoChemProp[item.apiProp].minValue = item.range.min;
              physicoChemProp[item.apiProp].maxValue = item.range.max;
            }
          }
          let propertySearch = {
            structuralProperties: null,
            physicoChemicalProperties: physicoChemProp
          };
          currentItem.advanceSearch.propertySearch = propertySearch;
          this.$store.state.structureSearch.physicoChemicalRows = JSON.parse(
            JSON.stringify(this.physicoChemData)
          );

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
      this.$store.commit("structureSearch/RESET_PHYSICO_CHEMICAL_ROW_DATA");
      this.$store.state.structureSearch.isAllPhysicoPropsSelected = "";
      this.physicoChemData = JSON.parse(
        JSON.stringify(this.$store.state.structureSearch.physicoChemicalRows)
      );
      this.setMinMaxValues(this.minMaxData);
      let currentItemIndex = this.$store.state.advanceSearch.currentItemIndex;
      let currentItem = this.$store.state.simpleSearch.simpleSearchRowData[
        currentItemIndex
      ];
      currentItem["hasValue"] = false;
      currentItem.structureAdvanceSearchTab = null;
      currentItem.advanceSearchSelectedValue = null;
      currentItem.isStructureAdvanceSearch = false;

      this.selectAll = "";
      this.disableAdd = true;
      this.disableClear = true;
      this.getInitalCounts();
    },
    closeAdvanceSearchPopup() {
      this.$emit("adv-popup-closed", true);
    }
  }
});

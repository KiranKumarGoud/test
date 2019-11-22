Vue.component("property-search", {
  template: `
  <div class="" id="m_tabs_6_3" role="tabpanel"> 
    <div>
      <div class="m-portlet__body  m-portlet__body--no-padding">			      
          <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--success" role="tablist">
            <li class="nav-item m-tabs__item"> <a class="nav-link" :class="{active: newTAb == 'structural'}" @click="tabChanged($event,'structural')"   data-toggle="tab" href="javascript:void(0)" role="tab" aria-selected="true"><i class="fa flaticon-user-ok"></i> Structural Property</a> </li>
            <li class="nav-item m-tabs__item"> <a class="nav-link" :class="{active: newTAb == 'physico'}" @click="tabChanged($event,'physico')"  data-toggle="tab" href="javascript:void(0)" role="tab" aria-selected="false"><i class="fa  flaticon-placeholder"></i> Physico-Chemical Properties</a> </li>
          </ul>
          <div class="tab-content">
            <div v-if="newTAb=='structural'">
              <structural-property  @adv-popup-closed="closeAdvanceSearchPopup"></structural-property>
            </div>
            <div v-if="newTAb=='physico'">
              <physico-chemical @adv-popup-closed="closeAdvanceSearchPopup"></physico-chemical>
            </div>
          </div>
          <b-modal id="modal-center" v-model="confirmationModal" :hide-header="true"  :no-close-on-backdrop="true" :no-close-on-esc="true" centered @ok="changeTab" @cancel="cancelTabChange">
            <div>
              <p>Are you sure you want to continue? The selected data will be lost.</p>
            </div>
          </b-modal> 
      </div>
    </div>	
  </div>`,
  mounted() {
    if (this.$store.state.structureSearch.propertySearchTab) {
      this.newTAb = this.$store.state.structureSearch.propertySearchTab;
      this.selectedTab = this.$store.state.structureSearch.propertySearchTab;
    }
  },
  created() {},
  data() {
    return {
      currentTab: "structural",
      selectedTab: "structural",
      newTAb: "structural",
      confirmationModal: false
    };
  },
  methods: {
    tabChanged(event, selectedTab) {
      if (this.selectedTab != selectedTab) {
        this.selectedTab = selectedTab;
        this.confirmationModal = true;
      }
    },
    changeTab() {
      this.newTAb =
        this.newTAb != this.selectedTab ? this.selectedTab : this.newTAb;
      this.$store.commit("structureSearch/RESET_MIN_MAX");
      this.$store.commit("structureSearch/RESET_STRUCTURAL_PROPERTY_ROW_DATA");
      this.$store.commit("structureSearch/RESET_PHYSICO_CHEMICAL_ROW_DATA");

      this.$store.state.structureSearch.selectedRule = "rule5";
      this.$store.state.structureSearch.propertySearchTab = this.newTAb;
      this.$store.state.structureSearch.isAllPhysicoPropsSelected = "";
      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]["hasValue"] = false;
       this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]['isStructureAdvanceSearch'] = false;
      if(this.$store.state.simpleSearch.simpleSearchRowData.length == 1){
         this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
      }
    },
    cancelTabChange() {
      this.selectedTab = this.newTAb;
    },
    confirm() {
      this.currentTab = this.selectedTab;
    },
    closeAdvanceSearchPopup() {
      this.$emit("adv-popup-closed", true);
    }
  }
});

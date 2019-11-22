Vue.component("structure-search", {
  template: `
  <div role="document" class="modal-dialog modal-big-lg" style="max-width: 1220px;">
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title txt-wht-col" id="exampleModalLabel">Advanced Search</h5>
        <button @click="closePopup()" type="button" class="md-effect-13" data-dismiss="modal" aria-label="Close"> 
          <span aria-hidden="true"><i class="fa fa-times"></i></span> 
        </button>
      </div>
      <div class="modal-body">
        <div> 
          <!-- STARTS Tabination panel blocks from here -->
          <div class="m-portlet__body">
            <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--success" role="tablist">
              <li class="nav-item m-tabs__item"> <a class="nav-link" :class="{active: newTAb == 'chemistry'}" @click="tabChanged($event,'chemistry')" data-toggle="tab" href="javascript:void(0)" role="tab" aria-selected="true"><i class="la la-cog"></i> Chemistry Search</a> </li>
              <li class="nav-item m-tabs__item"> <a class="nav-link" :class="{active: newTAb == 'term'}" @click="tabChanged($event,'term')"  data-toggle="tab" href="javascript:void(0)" role="tab" aria-selected="false"><i class="la la-cog"></i> Term Search</a> </li>
              <li class="nav-item m-tabs__item"> <a class="nav-link" :class="{active: newTAb == 'property'}" @click="tabChanged($event,'property')"  data-toggle="tab" href="javascript:void(0)" role="tab" aria-selected="false"><i class="la la-cog"></i> Property Search</a> </li>
            </ul>
            <div class="tab-content">
              <chemistry-search v-if="newTAb =='chemistry'" ref="chemistry" @adv-popup-closed="closePopup"></chemistry-search>
        
              <term-search v-else-if="newTAb =='term'" ref="term" @adv-popup-closed="closePopup"></term-search>
        
              <property-search v-else-if="newTAb =='property'" ref="property" @adv-popup-closed="closePopup"></property-search>
            </div>
            <b-modal id="modal-center" v-model="confirmationModal" :hide-header="true"  :no-close-on-backdrop="true" :no-close-on-esc="true" centered @ok="changeTab" @cancel="cancelTabChange">
              <div>
                <p>Are you sure you want to continue? The selected data will be lost.</p>
              </div>
            </b-modal> 
          </div>
      </div>
    </div>
    <div></div>
   <!--
         <div class="modal-footer">
      <button type="button" class="btn btn-danger btn-sm" @click="onClear">Clear</button>
      <button type="button" class="btn btn-excelra btn-sm" @click="onAdd">Add</button>
    </div>
   -->
   
  </div>
  
  

  
  
  
  
<!---->
</div>`,
  mounted() {
    let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
      this.$store.state.advanceSearch.currentItemIndex
    ];
    if (currItem.structureAdvanceSearchTab) {
      this.newTAb = currItem.structureAdvanceSearchTab;
      this.selectedTab = currItem.structureAdvanceSearchTab;
    //  this.$store.state.structureAdvanceSearchTab = currItem.structureAdvanceSearchTab;
    }
  },
  data() {
    return {
      newTAb: "chemistry",
      selectedTab: "chemistry",
      confirmationModal: false
    };
  },
  methods: {
    closePopup() {
      this.$emit("popup-closed", true);
      let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ];
      if (!currItem.hasValue) {
        currItem.structureAdvanceSearchTab = null;
      }
    },
    tabChanged(event, selectedTab) {
      if (this.selectedTab != selectedTab) {
        this.selectedTab = selectedTab;
        this.confirmationModal = true;
      }
    },
    cancelTabChange() {
      this.selectedTab = this.newTAb;
    },
    changeTab() {
      if (this.newTAb != this.selectedTab) {
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]["hasValue"] = false;
        let payload = {
          currItem: this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ],
          currIndex: this.$store.state.advanceSearch.currentItemIndex
        };
        this.$store.commit("structureSearch/RESET_TERM_SEARCH");
        this.$store.commit("structureSearch/RESET_PHYSICO_CHEMICAL_PROPERTIES");
        this.$store.commit("structureSearch/RESET_STRUCTURAL_PROPERTIES");
        this.$store.commit("structureSearch/RESET_PHYSICO_CHEMICAL_ROW_DATA");

        this.$store.commit("simpleSearch/RESET_TERM_SEARCH_VALUES", payload);
      }

      this.newTAb = this.selectedTab;
      this.$store.state.structureSearch.propertySearchTab = "structural";
      this.$store.state.structureSearch.isAllPhysicoPropsSelected = "";
      this.$store.state.structureSearch.drawStructureMolData = null;
      this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]["structureAdvanceSearchTab"] = this.selectedTab;
      if (this.$store.state.simpleSearch.simpleSearchRowData.length == 1) {
        this.$store.commit("simpleSearch/RESET_SIMPLE_SERACH_COUNT");
      }
    }
  },
  computed: {
    currentTab: function() {
      console.log(this.$refs[this.newTAb]);
      return this.newTAb.toLowerCase() + "-search";
    }
  }
});

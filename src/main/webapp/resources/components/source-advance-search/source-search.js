Vue.component("source-search", {
  template: `
  <div class="modal fade show list-search" id="m_modal_1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-big-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel" >Grouped Actions</h3>
                <button type="button" @click="closePopup()" class="" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true"><i class="fa fa-times"></i></span> </button>
            </div>
            <div class="modal-body" style="padding: 25px;">
                <!-- Inside container part START from here -->
                <div> 
                    <!-- STARTS Tabination panel blocks from here -->
                    <div class="m-portlet__body">
                        <ul class="nav nav-tabs" role="tablist" style="margin-bottom:0px;">
                            <li class="nav-item"> <a class="nav-link" :class="{active: newTAb == 'list'}"   @click="tabChanged($event,'list')" data-toggle="tab" href="javascript:void(0)" data-target="#m_tabs_1_1">List Search</a> </li>
                            <li class="nav-item"> <a class="nav-link" :class="{active: newTAb == 'strain'}"  @click="tabChanged($event,'strain')" data-toggle="tab" href="javascript:void(0)">Strain Search</a> </li>
                        </ul>
                        <div class="tab-content">
                            <source-list-search v-if="newTAb =='list'" ref="listSearch" @adv-popup-closed="closePopup"></source-list-search>
                            <strain-search v-else-if="newTAb =='strain'" ref="strain" @adv-popup-closed="closePopup"></strain-search>
                            <b-modal id="modal-center" v-model="confirmationModal" :hide-header="true"  :no-close-on-backdrop="true" :no-close-on-esc="true" centered @ok="changeTab" @cancel="cancelTabChange">
                                <div>
                                    <p>Are you sure you want to continue? The selected data will be lost.</p>
                                </div>
                            </b-modal>
                        </div>
                    </div>
                    <!-- ENDS Tabination panel blocks from here --> 
                </div>
            </div>          
        </div>
    </div>
</div>`,
  mounted() {
    let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
      this.$store.state.advanceSearch.currentItemIndex
    ];
    if (currItem.sourceSearch.currentSelectedTab) {
      this.newTAb = currItem.sourceSearch.currentSelectedTab;
      this.selectedTab = currItem.sourceSearch.currentSelectedTab;
      this.$store.state.sourceSearch.currentSelectedTab = currItem.sourceSearch.currentSelectedTab;
    }
    //this.newTAb = this.$store.state.sourceSearch.currentSelectedTab;
  },
  data() {
    return {
      newTAb: "list",
      selectedTab: "list",
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
      console.log(this.$store.state.sourceSearch.currentSelectedTab + '!=' + selectedTab)
      if (this.$store.state.sourceSearch.currentSelectedTab != selectedTab) {
        this.$store.state.sourceSearch.currentSelectedTab = selectedTab;
        this.confirmationModal = true;
      }
    },
    cancelTabChange() {
      this.$store.state.sourceSearch.currentSelectedTab = this.newTAb;
    },
    changeTab() {
      if (this.newTAb != this.$store.state.sourceSearch.currentSelectedTab) {
        this.newTAb = this.$store.state.sourceSearch.currentSelectedTab
       if(this.newTAb == 'list') {         
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]['sourceSearch']['strainSearch'] = {}
          this.$store.state.sourceSearch.strainSearch = JSON.parse(glb__initialSourceSearchState).strainSearch
        } else if(this.newTAb == 'strain') {         
          this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]['sourceSearch']['listSearch'] = {}
          this.$store.state.sourceSearch.listSearch = JSON.parse(glb__initialSourceSearchState).listSearch
        }

        this.$store.state.advanceSearch.currentItem.advanceSearch.autoComplete = []
        this.$store.state.advanceSearch.currentItem.advanceSearch.advanceSearchSelectedValue = null
        this.$store.state.advanceSearch.currentItem.advanceSearch.advanceSearchSelectedValueName = ''



        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['advanceSearch']['autoComplete'] = []
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['advanceSearchSelectedValue'] = ''
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['sourceSearch']['currentSelectedTab'] = this.newTAb

        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['advanceSearchSelectedValueName'] = ''
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['sourceSearch'] = JSON.parse(glb__initialSourceSearchState)

      }
    }
  },
  computed: {
    currentTab: function() {
      return this.newTAb.toLowerCase() + "-search";
    }
  }
});

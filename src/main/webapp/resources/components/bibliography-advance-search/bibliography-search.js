Vue.component("bibliography-search", {
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
                            <li class="nav-item"> <a class="nav-link" :class="{active: newTAb == 'criterion'}"  @click="tabChanged($event,'criterion')" data-toggle="tab" href="javascript:void(0)">Criterion Search</a> </li>
                            <li class="nav-item"> <a class="nav-link" :class="{active: newTAb == 'custom'}" @click="tabChanged($event,'custom')" data-toggle="tab" href="javascript:void(0)">Custom Search</a> </li>
                        </ul>                        
                        <div class="tab-content">
                            <list-search v-if="newTAb =='list'" ref="list" @adv-popup-closed="closePopup"></list-search>
                            <criterion-search v-else-if="newTAb =='criterion'" ref="criterion" @adv-popup-closed="closePopup"></criterion-search>
                            <custom-search v-else-if="newTAb =='custom'" ref="custom" @adv-popup-closed="closePopup"></custom-search>
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
          <!--  <div class="modal-footer" style="background-color:#f1f1f1">
                <button type="button" :disabled="disableClear" @click="resetTargetAdvanceSearchValues()" class="btn btn-danger " data-dismiss="modal">Clear</button>
                <button type="button" :disabled="disableAdd" @click="confirm()" class="btn btn-primary">Add</button>
            </div> -->
        </div>
    </div>
</div>`,
  mounted() {
   // this.newTAb = this.$store.state.bibliographySearch.currentSelectedTab;
    let currItem = this.$store.state.simpleSearch.simpleSearchRowData[
      this.$store.state.advanceSearch.currentItemIndex
    ];
    if (currItem.bibliographySearch.currentSelectedTab) {
      this.newTAb = currItem.bibliographySearch.currentSelectedTab;
      this.selectedTab = currItem.bibliographySearch.currentSelectedTab;
      this.$store.state.bibliographySearch.currentSelectedTab = currItem.bibliographySearch.currentSelectedTab;
    }
    
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
      if (this.$store.state.bibliographySearch.currentSelectedTab != selectedTab) {
        this.$store.state.bibliographySearch.currentSelectedTab = selectedTab;
        this.confirmationModal = true;
      }
    },
    cancelTabChange() {
      this.$store.state.bibliographySearch.currentSelectedTab = this.newTAb;
    },
    changeTab() {
      if (this.newTAb != this.$store.state.bibliographySearch.currentSelectedTab) {
        this.newTAb = this.$store.state.bibliographySearch.currentSelectedTab
        if(this.newTAb == 'custom') {
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]['bibliographySearch']['listSearch'] = {}
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]['bibliographySearch']['criterionSearch'] = {}

          this.$store.state.bibliographySearch.listSearch = JSON.parse(glb__initialBibliographySearchState).listSearch
          this.$store.state.bibliographySearch.criterionSearch = JSON.parse(glb__initialBibliographySearchState).criterionSearch

        } else if(this.newTAb == 'list') {
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]['bibliographySearch']['customSearch'] = {}
          this.$store.state.simpleSearch.simpleSearchRowData[
            this.$store.state.advanceSearch.currentItemIndex
          ]['bibliographySearch']['criterionSearch'] = {}

          this.$store.state.bibliographySearch.customSearch = JSON.parse(glb__initialBibliographySearchState).customSearch
          this.$store.state.bibliographySearch.criterionSearch = JSON.parse(glb__initialBibliographySearchState).criterionSearch
        } else if(this.newTAb == 'criterion') {
          this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]['bibliographySearch']['customSearch'] = {}
          this.$store.state.simpleSearch.simpleSearchRowData[
        this.$store.state.advanceSearch.currentItemIndex
      ]['bibliographySearch']['listSearch'] = {}

          this.$store.state.bibliographySearch.listSearch = JSON.parse(glb__initialBibliographySearchState).listSearch
          this.$store.state.bibliographySearch.customSearch = JSON.parse(glb__initialBibliographySearchState).customSearch
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
        ]['bibliographySearch']['currentSelectedTab'] = this.newTAb

        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['advanceSearchSelectedValueName'] = ''
        this.$store.state.simpleSearch.simpleSearchRowData[
          this.$store.state.advanceSearch.currentItemIndex
        ]['bibliographySearch'] = JSON.parse(glb__initialBibliographySearchState)

      }
    }
  },
  computed: {
    currentTab: function() {
      return this.newTAb.toLowerCase() + "-search";
    }
  }
});

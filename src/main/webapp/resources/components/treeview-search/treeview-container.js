Vue.component("treeview-container", {
  template: `<div class="tab-pane active" id="m_tabs_1_2" role="tabpanel">
    
  <!-- TARGET TREE VIEW SEARCH  START-->
  <target-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='TargetTreeView'"
    ref="TargetTreeView"
    @adv-popup-closed="closePopup">
  </target-treeview-search>
  <!-- TARGET TREE VIEW SEARCH END-->


  <!-- TAXONOMY TREE VIEW SEARCH  START-->
  <taxonomy-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='TaxonomyTreeView'"
    ref="TaxonomyTreeView"
    @adv-popup-closed="closePopup">
  </taxonomy-treeview-search>
  <!-- TAXONOMY TREE VIEW SEARCH END-->

  <!-- PATHWAYS TREE VIEW SEARCH  START-->
  <pathways-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='PathwaysTreeView'"
    ref="PathwaysTreeView"
    @adv-popup-closed="closePopup">
  </pathways-treeview-search>
  <!-- PATHWAYS TREE VIEW SEARCH END-->

  <!-- CLINICALPHASE TREE VIEW SEARCH  START-->
  <clinical-phase-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='ClinicalPhaseTreeView'"
    ref="ClinicalPhaseTreeView"
    @adv-popup-closed="closePopup">
  </clinical-phase-treeview-search>
  <!-- CLINICALPHASE TREE VIEW SEARCH END-->

  <!-- PHARMACOKINETICS TREE VIEW SEARCH  START-->
  <pharmacokinetics-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='PharmacokineticsTreeView'"
    ref="PharmacokineticsTreeView"
    @adv-popup-closed="closePopup">
  </pharmacokinetics-treeview-search>
  <!-- PHARMACOKINETICS TREE VIEW SEARCH END-->


  <!-- Toxicity TREE VIEW SEARCH  START-->
  <toxicity-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='ToxicityTreeView'"
    ref="ToxicityTreeView"
    @adv-popup-closed="closePopup">
  </toxicity-treeview-search>
  <!-- Toxicity TREE VIEW SEARCH END-->
  
  <!-- Toxicity TREE VIEW SEARCH  START-->
  <assay-method-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='AssayMethodNameTreeView'"
    ref="AssayMethodNameTreeView"
    @adv-popup-closed="closePopup">
  </assay-method-treeview-search>
  <!-- Toxicity TREE VIEW SEARCH END-->

  
  <!-- BIBLIOGRAPHY TREE VIEW SEARCH  START-->
  <bibliography-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='BibliographyTreeView'"
    ref="BibliographyTreeView"
    @adv-popup-closed="closePopup">
  </bibliography-treeview-search>
  <!-- BIBLIOGRAPHY TREE VIEW SEARCH END-->
  
  <!-- ACTIVITY TYPE TREE VIEW SEARCH  START-->  
  <activity-type-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='EndpointTreeView'"
    ref="EndpointTreeView"
    @adv-popup-closed="closePopup">
  </activity-type-treeview-search>
  <!-- ACTIVITY TYPE TREE VIEW SEARCH END-->


  <!-- INDICATION TREE VIEW SEARCH  START-->
  <indication-treeview-search
    v-if="$store.state.treeViewSearch.currentTreeViewSelected =='IndicationTreeView'"
    ref="IndicationTreeView"
    @adv-popup-closed="closePopup">
  </indication-treeview-search>
  <!-- INDICATION TREE VIEW SEARCH END-->

  
  <b-modal id="modal-center" v-model="confirmationModal" :hide-header="true"  :no-close-on-backdrop="true" :no-close-on-esc="true" centered>
    <div>
    <p>Are you sure you want to continue? The selected data will be lost.</p>
    </div>
  </b-modal>
  
  </div>`,
  mounted() {   
  },
  data() {
    return {
      confirmationModal: false
    };
  },
  methods: {
    closePopup() {
      this.$emit("popup-closed", true);
      this.$store.state.treeViewSearch.currentTreeViewSelected = null
      
      // this.$store.state.simpleSearch.simpleSearchRowData[
      //   this.$store.state.advanceSearch.currentItemIndex
      // ]['simpleSearchSelectedValue'] = null
    },
  },
  computed: {
    
  }
});

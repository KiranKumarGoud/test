Vue.component("clinical-phase-treeview-search", {
  template: `<div class="tab-pane active fade show">
  <div>
    CLINICAL PHASE TREE VIEW UNDER DEVELOPMENT
  </div>
  <div class="modal-footer" >
    <button
      type="button"
      class="btn btn-danger"
      :disabled="disableClear"
      @click="resetChanges()"
      >
      Clear
    </button>
    <button
      type="button"
      :disabled="disableAdd"
      class="btn btn-excelra"
      @click="confirm()"
      >
      Add
    </button>
  </div>
  <app-loader v-if="showLoading"></app-loader>
</div>
  `,
  mounted() {
     
  },
  data() {
    return {
      showLoading: false
    };
  },
  
  watch: {
    
  },
  methods: {
    confirm() {
      this.$bvModal
        .msgBoxConfirm("Are you sure?", {
          centered: true
        })
        .then(value => {
          if (!value) {
            return;
          }
         
        })
        .catch(err => {
          console.log(err)
          // An error occurred
        });
    },
    resetChanges() {
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
    closePopup() {
      this.$emit("adv-popup-closed", true);
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
  },
  computed: {
    disableAdd(){
      let disable = true
      
      return disable
    },
    disableClear(){
      let disable = true
      
      return disable
    },
    getAdvanceSelectedValue() {
      return this.$store.getters["advanceSearch/getAdvanceSelectedValue"];
    },
    getAutocompleteFieldValue() {
      return this.$store.getters["advanceSearch/getAutocompleteFieldValue"];
    }
  }
});

const glb__advanceSearchMutations = {
  SET_TARGET_SEARCH_OPTIONS: (state, payload) => {
    state.targetSearch.autoCompleteOptions = payload;
  },
  RESET_TARGET_SEARCH: (state, payload) => {
    state.selectedValue = "";
    state.targetSearch.autoCompleteOptions = [];
  }
};

const glb__bibliographySearchMutations = {
  RESET_TERM_SEARCH: state => {
    state.fileData = [];
    state.fileName = "";
    state.inputSearchValue = [];
    state.selectedValue = "";
    state.selectedValueName = "";
  },
};

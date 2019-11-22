const glb__advanceSearchGetters = {
  getAdvanceSelectedValue: state => {
    state.autoCompleteOptions = [];
    state.selectedAutoCompleteValues = [];
    return state.selectedValue;
  },
  resetAutoCompleteFields: state => {
    return (state.resetAutoCompleteValues = !state.resetAutoCompleteValues);
  },
  getAutocompleteFieldValue: state => {
    return state.autoCompleteInputFieldValue;
  }
};

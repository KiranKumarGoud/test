const glb__simpleSearchGetters = {
  getSelectedValue: state => {
    return state.selectedValue;
  },
  simpleSearchRowDataChange: state => {
    return state.simpleSearchRowData;
  },
  getAllSelectedOptionValues: state => {
    let selValues = [];
    state.simpleSearchRowData.forEach(element => {
      selValues.push(element.simpleSearchSelectedValue);
    });
    return [...new Set(selValues)];
  },
  validateSearchCount: state => {
    for (var key in state.searchCount) {
      if (state.searchCount[key] !== null && state.searchCount[key] != 0)
        return false;
    }
    return true;
  }
};

const Store = new Vuex.Store({
  modules: {
    app,
    user,
    simpleSearch,
    advanceSearch,
    shared,
    fileUpload,
    charts,
    tabularView,
    structureSearch,
    bibliographySearch,
    activityTypeSearch
  }
});

return Store;

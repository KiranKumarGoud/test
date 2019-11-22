function initialState() {
  return state;
}

export default {
  namespaced: true,
  state: initialState,
  getters,
  mutations: {
    reset(state) {
      // acquire initial state
      const s = initialState();
      Object.keys(s).forEach(key => {
        state[key] = s[key];
      });
    },
    ...mutations
  },
  actions,
  state
};

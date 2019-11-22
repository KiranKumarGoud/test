import defaultState from "./default-state";
function getInitialState() {
  return defaultState;
}
export default {
  reset(state) {
    // acquire initial state
    const s = getInitialState();
    Object.keys(s).forEach(key => {
      state[key] = s[key];
    });
  }
};

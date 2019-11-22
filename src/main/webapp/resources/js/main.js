// if using a Quasar language pack other than the default "en-us";
// requires the language pack style tag from above
// Quasar.lang.set(Quasar.lang.ptBr); // notice camel-case "ptBr"

// if you want Quasar components to use a specific icon library
// other than the default Material Icons;
// requires the icon set style tag from above
Quasar.iconSet.set(Quasar.iconSet.fontawesomeV5); // fontawesomeV5 is just an example

// start the UI; assumes you have a <div id="q-app"></div> in your <body>
const store = new Vuex.Store({
  state: {
    all: "hi all asdfs"
  }
});

new Vue({
  el: "#q-app",
  store,
  data() {
    return {
      userEmail: "",
      password: "",
      submitCLicked: false
    };
  },
  methods: {}
});

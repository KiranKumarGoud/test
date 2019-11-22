const glb__structureSearchMutations = {
  RESET_TERM_SEARCH: state => {
    state.fileData = [];
    state.fileName = "";
    state.inputSearchValue = [];
    state.selectedValue = "";
    state.selectedValueName = "";
  },
  RESET_CHEMISTRY_SEARCH: state => {
    state.chemistryAdvanceSearch = {
      strDraw: "",
      strCategory: "",
      strSimilarityMinValue: null,
      strSimilarityMaxValue: null,
      strReadonly: "",
      strComboData: [],
      strComboFileData: [],
      strFeature: "",
      strFeatureOptions: []
    };
  },
  RESET_STRUCTURAL_PROPERTIES: state => {
    state.propertyAdvanceSearch.structuralProperties = {
      mol_weight: { minValue: null, maxValue: null },
      hb_donors: { minValue: null, maxValue: null },
      hb_acceptor: { minValue: null, maxValue: null },
      clogp: { minValue: null, maxValue: null },
      rot_bonds: { minValue: null, maxValue: null },
      psa: { minValue: null, maxValue: null }
    };
  },
  RESET_PHYSICO_CHEMICAL_PROPERTIES: state => {
    state.propertyAdvanceSearch.physicoChemicalProperties = {
      pka_value: { minValue: null, maxValue: null },
      logd_calc: { minValue: null, maxValue: null },
      solubility: { minValue: null, maxValue: null },
      pkb: { minValue: null, maxValue: null }
    };
  },
  RESET_MIN_MAX: state => {
    state.minMaxData = null;
  },
  RESET_PHYSICO_CHEMICAL_ROW_DATA: state => {
    state.physicoChemicalRows = [
      {
        name: "pKa",
        model: "",
        value: "pka",
        apiProp: "pka_value",
        apiMin: "pkamin",
        apiMax: "pkamax",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disableMinMax: true,
        checked: false
      },
      {
        name: "pKb",
        model: "",
        value: "pkb",
        apiProp: "pkb",
        apiMin: "pkbmin",
        apiMax: "pkbmax",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disableMinMax: true,
        checked: false
      },
      {
        name: "LogD",
        model: "",
        value: "logd",
        apiProp: "logd_calc",
        apiMin: "logdmin",
        apiMax: "logdmax",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disableMinMax: true,
        checked: false
      },
      {
        name: "Solubility(Aqs.)",
        model: "",
        value: "solubility",
        apiProp: "solubility",
        apiMin: "solubilitymin",
        apiMax: "solubilitymax",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disableMinMax: true,
        checked: false
      }
    ];
  },
  RESET_STRUCTURAL_PROPERTY_ROW_DATA: state => {
    state.structuralPropertyRows = [
      {
        name: "Molecular Weight",
        value: "molecularWeight",
        model: "molecularWeight",
        apiMin: "molmin",
        apiMax: "molmax",
        apiProp: "mol_weight",
        minVal: 0,
        maxVal: 500,
        range: {
          min: 0,
          max: 500
        },
        disabled: false,
        minLimit: 20,
        disableMinMax: true
      },
      {
        name: "No.of H-Bonds Donor",
        model: "hBondsDonor",
        value: "hBondsDonor",
        apiMin: "hbdonormin",
        apiMax: "hbdonormax",
        apiProp: "hb_donors",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disabled: false,
        minLimit: 20,
        disableMinMax: true
      },
      {
        name: "No.of H-Bonds Acceptor",
        model: "hBondsAcceptor",
        value: "hBondsAcceptor",
        apiMin: "hbacceptormin",
        apiMax: "hbacceptormax",
        apiProp: "hb_acceptor",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disabled: false,
        minLimit: 20,
        disableMinMax: true
      },
      {
        name: "No.of Free Rotatable Bonds",
        model: "",
        value: "freeRotatableBonds",
        apiMin: "rotbondsmin",
        apiMax: "rotbondsmax",
        apiProp: "rot_bonds",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disabled: false,
        minLimit: 20,
        disableMinMax: true
      },
      {
        name: "Log P",
        model: "logP",
        value: "logP",
        apiMin: "logpmin",
        apiMax: "logpmax",
        apiProp: "clogp",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disabled: false,
        minLimit: 20,
        disableMinMax: true
      },
      {
        name: "PSA",
        model: "",
        value: "psa",
        apiMin: "psamin",
        apiMax: "psamax",
        apiProp: "psa",
        minVal: 0,
        maxVal: 100,
        range: {
          min: 0,
          max: 100
        },
        disabled: false,
        minLimit: 20,
        disableMinMax: true
      }
    ];
  }
};

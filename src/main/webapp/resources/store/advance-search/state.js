const glb__advanceSearchState = {
  toggleAdvanceSearchModal: false,
  resetAutoCompleteValues: false,
  showDisabledProps: false,
  selectedValue: "",
  selectedAutoCompleteValues: [],
  selectOptions: [
    { text: "Please Select an Option", value: null, disabled: true },
    { text: "Entrez Gene ID", value: "enterez", disabled: false },
    { text: "Uni Prot ID", value: "upprot", disabled: false },
    { text: "PDB ID", value: "pdb", disabled: false },
    { text: "Official Name", value: "office", disabled: false },
    { text: "Common Name", value: "common", disabled: false }
  ],
  IndicationSelectOptions: [
    { text: "Please Select an Option", value: null, disabled: true, title: "Please Select an Option" },
    { text: "Indication / Disease Name", value: "DiseaseName", disabled: false, title: "Indication / Disease Name" },
    { text: "ICD-10", value: "ICD-10", disabled: false, title: "ICD-10" }
    // { text: "MedDRA", value: "MedDRA", disabled: false, title: "MedDRA" }
  ],
  indicationAdvanceSearch: {
    selectedValue: "",
    autoCompleteOptions: []
  },
  targetSearch: {
    selectedValue: "",
    autoCompleteOptions: []
  },
  structureSearch: {
    chemistrySearchSelectedValues: {},
    propertySearchSelectedValues: {}
  },
  autoCompleteInputFieldValue: "",
  currentItem: {},
  currentItemIndex: 0,
  showCountsLoader: false,
  selectedValueName: ""
};

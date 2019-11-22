const glb__simpleSearchState = {
  simpleSearchAutoOptions: [],
  simpleSearchParents: [
    {
      label: "Input",
      value: "input"
    },
    {
      label: "Selection",
      value: "selection"
    },
    {
      label: "Tree View",
      value: "treeview"
    }
  ],
  simpleSearchOptions: [
    { 
      label: "Target",
      value: "Target",
      isDisabled: false,
      parent: "input"
    },
    {
      label: "Structure",
      value: "Structure",
      isDisabled: false,
      parent: "input"
    },
    {
      label: "Activity Type",
      value: "ActivityTypes",
      isDisabled: true,
      parent: "input"
    },
    {
      label: "Indication",
      value: "Indication",
      isDisabled: false,
      parent: "input"
    },
    {
      label: "Assay",
      value: "Assay",
      isDisabled: false,
      parent: "selection"
    },
    {
      label: "Clinical Phase / Drug status",
      value: "ClinicalPhases",
      isDisabled: false,
      parent: "selection"
    },
    {
      label: "Compound / Molecule Categories",
      value: "CompoundCategories",
      isDisabled: false,
      parent: "selection"
    },
    // {
    //   label: "Compound Phases",
    //   value: "CompoundPhases",
    //   isDisabled: false,
    //   parent: "selection"
    // },
    {
      label: "Mechanism of Action (MoA)",
      value: "ActivityMechanism",
      isDisabled: false,
      parent: "selection"
    },
    {
      label: "Target / Enzyme / Protein",
      value: "TargetTreeView",
      isDisabled: false,
      parent: "treeview"
    },
    {
      label: "Indication / Disease",
      value: "IndicationTreeView",
      isDisabled: false,
      parent: "treeview"
    },
    {
      label: "Activity Type / Endpoints",
      value: "EndpointTreeView",
      isDisabled: true,
      parent: "treeview"
    },
    // {
    //   label: "Bibliography",
    //   value: "BibliographyTreeView",
    //   isDisabled: false,
    //   parent: "treeview"
    // },
    // {
    //   label: "Assay Method Name",
    //   value: "AssayMethodNameTreeView",
    //   isDisabled: false,
    //   parent: "treeview"
    // },
    {
      label: "Toxicity",
      value: "ToxicityTreeView",
      isDisabled: false,
      parent: "treeview"
    },
    {
      label: "Pharmacokinetics",
      value: "PharmacokineticsTreeView",
      isDisabled: false,
      parent: "treeview"
    },
    // {
    //   label: "Pathways",
    //   value: "PathwaysTreeView",
    //   isDisabled: false,
    //   parent: "treeview"
    // },
    {
      label: "Taxonomy",
      value: "TaxonomyTreeView",
      isDisabled: false,
      parent: "treeview"
    },
    {
      label: "Bibliography",
      value: "bibliography",
      isDisabled: false,
      parent: "input"
    },
    { 
      label: "Source",
      value: "Source",
      isDisabled: false,
      parent: "input"
    }
  ],
  selectedValue: "",
  searchCount: {
    actIdsCount: null,
    refIdsCount: null,
    gvkIdsCount: null,
    strIdsCount: null
  },
  defatultSearchCount: {
    actIdsCount: null,
    refIdsCount: null,
    gvkIdsCount: null,
    strIdsCount: null
  },
  simpleSearchRowData: [
    {
      simpleSearchSelectedLabel: null,
      simpleSearchSelectedValue: null,
      simpleSearchOptions: [],
      simpleSearchAutoComplete: [],
      fileData: [],
      fileName: "",
      advanceSearch: {
        autoComplete: []
      },
      advanceSearchSelectedValue: null,
      advanceSearchSelectedValueName: '',
      structureAdvanceSearchTab: null,
      showAllFields: false,
      operator: 'AND',
      showDelete: false,
      disableAllFields: false,
      disableFields: true,
      disableAdvanceSearchButton: false,
      disableSimpleSearchSelect: false,
      hasValue: false,
      isStructureAdvanceSearch: false,
      bibliographySearch: {
        listSearch: {},
        criterionSearch: {},
        customSearch: {},
        currentSelectedTab: 'list'
      },
      sourceSearch: {
        listSearch: {},
        strainSearch: {},
        currentSelectedTab: 'list'
      }
    }
  ],
  simpleSearchRow: {
    simpleSearchSelectedLabel: null,
    simpleSearchSelectedValue: null,
    simpleSearchOptions: [],
    simpleSearchAutoComplete: [],
    fileData: [],
    fileName: "",
    advanceSearch: {
      autoComplete: []
    },
    advanceSearchSelectedValue: null,
    advanceSearchSelectedValueName: '',
    structureAdvanceSearchTab: null,
    showAllFields: false,
    operator: 'AND',
    showDelete: false,
    disableFields: true,
    disableAllFields: false,
    disableAdvanceSearchButton: false,
    disableSimpleSearchSelect: false,
    hasValue: false,
    isStructureAdvanceSearch: false,
    bibliographySearch: {
      listSearch: {},
      criterionSearch: {},
      customSearch: {},
      currentSelectedTab: 'list'
    },
    sourceSearch: {
      listSearch: {},
      strainSearch: {},
      currentSelectedTab: 'list'
    }
  },
  resultCount: {
    sourceOption: "",
    simpleSearch: {
      option: ""
    },
    advancedSearch: null,
    operator: ""
  },
  targetAdvanceSearch: {
    option: "",
    targetAdvSearchInputDTOList: [],
    fileData: [],
    targetCategory: []
  },
  termAdvanceSearch: {
    strComboData: [],
    strComboFileData: [],
    strReadonly: ""
  },
  searchPostObj: [],
  enableDashboardOnSubmit: false,
  deletedRowData: [],
  rowCount: null,
  showUserProfileMenu: false
};

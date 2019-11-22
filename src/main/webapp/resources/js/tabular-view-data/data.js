var getFilterResultsGlobalCount = function() {
  // get global counts for filtered results also
  let payload = {};
  payload.selectedTabIds = gbl_main_request_payload.selectedTabIds();
  payload.unSelectedTabIds = gbl_main_request_payload.unSelectedTabIds();
  payload.numericFilters = gbl_main_request_payload.numericFilters();
  payload.stringFilters = gbl_main_request_payload.stringFilters();
  payload.stringFilters = gbl_main_request_payload.stringFilters();
  payload.pageRequestInformation = null;

  let params = {
    type: "POST",
    url: window.config.apiUrl + "security/allmapping/search/tabularview/count",
    data: JSON.stringify(payload)
  };
  window.getGlobalCounts(params);
};
var resetTabularViewData = function(tabKey) {
  plainTextInputValue = {};
  gbl_tabularview_store[tabKey] = JSON.parse(
    JSON.stringify(gbl_tabularview_initial_store[tabKey])
  );
  updateGblTabularviewStore(gbl_tabularview_store);
};

var applyImageOnCellHoverRenderer = function(
  row,
  columnfield,
  value,
  defaulthtml,
  columnproperties
) {
    let rowDetails = $("#" + this.gbl_tabularview_store.structure.uniqueIdentifier).jqxGrid(
        "getrowdata",
        row
    );
   // console.log(rowDetails);
  return (
    "<div style='width:100px; height:120px;' data-row=" + row.id + " class='apply-image-hover'> <img  class='test' src='/gostarjdbcservice/assets/images/smileimages/"+rowDetails.gvkId+".png' > "+ value +"</div>"
  );
}.bind(window);

var removeArrDuplicates = function(array) {
  var uniqueArray = [];
  for (i = 0; i < array.length; i++) {
    if (uniqueArray.indexOf(array[i]) === -1 && array[i] != null) {
      uniqueArray.push(array[i]);
    }
  }
  return uniqueArray;
};
var updateRowSelectedValues = function(type, selection, tabIdentifier) {
  if (typeof selection != "number" && selection.length == 0) {
    // this is for selection & unselection
    gbl_tabularview_store[tabIdentifier]["selectedRowIdsArr"] = [];
    gbl_tabularview_store[tabIdentifier]["unSelectedRowIdsArr"] = [];
  } else if (typeof selection == "number" && type == "SINGLE_SELECTED") {
    let unSelectedRowIdindex = gbl_tabularview_store[tabIdentifier][
      "unSelectedRowIdsArr"
    ].indexOf(selection);

    if (unSelectedRowIdindex > -1) {
      gbl_tabularview_store[tabIdentifier]["unSelectedRowIdsArr"].splice(
        unSelectedRowIdindex,
        1
      );
      gbl_tabularview_store[tabIdentifier]["selectedRowIdsArr"] = [];
    } else {
      gbl_tabularview_store[tabIdentifier][
        "selectedRowIdsArr"
      ] = removeArrDuplicates(
        gbl_tabularview_store[tabIdentifier]["selectedRowIdsArr"].concat(
          selection
        )
      );
      gbl_tabularview_store[tabIdentifier]["unSelectedRowIdsArr"] = [];
    }
  } else if (typeof selection == "number" && type == "SINGLE_UNSELECTED") {
    let selectedRowIdindex = gbl_tabularview_store[tabIdentifier][
      "selectedRowIdsArr"
    ].indexOf(selection);

    if (selectedRowIdindex > -1) {
      gbl_tabularview_store[tabIdentifier]["selectedRowIdsArr"].splice(
        selectedRowIdindex,
        1
      );
    } else {
      gbl_tabularview_store[tabIdentifier][
        "unSelectedRowIdsArr"
      ] = removeArrDuplicates(
        gbl_tabularview_store[tabIdentifier]["unSelectedRowIdsArr"].concat(
          selection
        )
      );
      gbl_tabularview_store[tabIdentifier]["selectedRowIdsArr"] = [];
    }
  }
  $("#output_data").html(
    "<pre>" +
      JSON.stringify(
        gbl_tabularview_store[tabIdentifier]["selectedRowIdsArr"]
      ) +
      "</pre>" +
      "<pre>" +
      JSON.stringify(
        gbl_tabularview_store[tabIdentifier]["unSelectedRowIdsArr"]
      ) +
      "</pre>"
  );
  updateGblTabularviewStore(gbl_tabularview_store);
};

var updateGblTabularviewStore = function(newValue = null) {
  // if value is null reset the store
  if (!newValue) {
    gbl_tabularview_store = gbl_tabularview_initial_store;
    localStorage.setItem("tabluarview", gbl_tabularview_initial_store);
  } else {
    // update updated data to the gbl_tabularview_store and update localstorage
    gbl_tabularview_store = newValue;
    localStorage.setItem("tabluarview", JSON.stringify(newValue));
  }
};

var getAggregateNumericResults = function(type,filterGroup) {
  let newData = JSON.parse(localStorage.getItem("tabluarview"));

  if (!newData) {
    newData = gbl_tabularview_store;
    localStorage.setItem("tabluarview", JSON.stringify(gbl_tabularview_store));
  }

  if (type == "numericFilters") {
    let payload = {};
    if(filterGroup != undefined && filterGroup.length > 0) {
      for (let tabData in newData) {
        const element = newData[tabData];
        for (let el in element[type]) {
          var index = filterGroup.findIndex(function(item, i){
            return item.field == el
          });
          if(index >= 0) 
          payload[el] = element[type][el];
        }
      }
    }
    
    return payload;
  }
}

var getAggregateResults = function(type) {
  let newData = JSON.parse(localStorage.getItem("tabluarview"));

  if (!newData) {
    newData = gbl_tabularview_store;
    localStorage.setItem("tabluarview", JSON.stringify(gbl_tabularview_store));
  }

  if (type == "numericFilters") {
    let payload = {};
    for (let tabData in newData) {
      const element = newData[tabData];
      for (let el in element[type]) payload[el] = element[type][el];
    }
    return payload;
  } else if (type == "stringFilters") {
    let payload = {};
    for (let tabData in newData) {
      const element = newData[tabData];
      for (let el in element[type]) payload[el] = element[type][el];
    }
    return payload;
  } else if (type == "selectedRowIdsArr") {
    let payload = { selectedTabIds: {} };
    for (let tabKey in gbl_tabularview_store) {
      var curTabData = gbl_tabularview_store[tabKey];
      if (curTabData["selectedRowIdsArr"]["length"] > 0) {
        payload["selectedTabIds"][curTabData["currentTabPayloadId"]] =
          curTabData["selectedRowIdsArr"];
      }
    }
    return payload.selectedTabIds;
  } else if (type == "unSelectedRowIdsArr") {
    let payload = { unSelectedTabIds: {} };
    for (let tabKey in gbl_tabularview_store) {
      var curTabData = gbl_tabularview_store[tabKey];
      if (curTabData["unSelectedRowIdsArr"]["length"] > 0) {
        payload["unSelectedTabIds"][curTabData["currentTabPayloadId"]] =
          curTabData["unSelectedRowIdsArr"];
      }
    }
    return payload.unSelectedTabIds;
  }
}.bind(window);
var gbl_filter_request_payload = {
  selectedTabIds: function() {
    return getAggregateResults("selectedRowIdsArr");
  },
  unSelectedTabIds: function() {
    return getAggregateResults("unSelectedRowIdsArr");
  },
  numericFilters: function() {
    return getAggregateResults("numericFilters");
  },
  stringFilters: function() {
    return getAggregateResults("stringFilters");
  },
  fieldName: "activityType",
  fieldType: "String",
  fieldOperator: "",
  fieldValue: "",
  pageNumber: 0,
  pageSize: 10
};

var gbl_main_request_payload = {
  selectedTabIds: function() {
    return getAggregateResults("selectedRowIdsArr");
  },
  unSelectedTabIds: function() {
    return getAggregateResults("unSelectedRowIdsArr");
  },
  numericFilters: function(filterGroup) {
    return getAggregateNumericResults("numericFilters",filterGroup);
  },
  stringFilters: function() {
    return getAggregateResults("stringFilters");
  },
  pageRequestInformation: {
    pageNumber: 0,
    pageSize: 10,
    sortField: "actId",
    sortType: "DESC"
  }
};

var gbl_tabularview_store = {
  activity: {
    tabPosition: 0,
    uniqueIdentifier: "activity-grid",
    columnUniqueId: "actId",
    currentTab: "activity",
    currentTabPayloadId: "Activity",
    isDataChecked: 0,
    initialDataUrl: window.config.apiUrl + "security/tabularview/activitytab",
    filterDataUrl:
      window.config.apiUrl + "security/tabularview/activitytab/filterfield",
    selectedRowIdsArr: [],
    unSelectedRowIdsArr: [],
    filterableColumns: {
      stringFilters: [],
      numericFilters: []
    },
    datafields: [
      { name: "actId", type: "string" },
      { name: "activityType", type: "string" },
      { name: "stdActivityType", type: "string" },
      { name: "activityUom", type: "string" },
      { name: "standardUom", type: "string" },
      { name: "activityPrefix", type: "string" },
      { name: "stdActPrefix", type: "string" },
      { name: "activityValue", type: "number" },
      { name: "sd", type: "number" },
      { name: "activityRemarks", type: "string" },
      { name: "microMolarvalue", type: "number" },
      { name: "assayType", type: "string" },
      { name: "enzymeCellAssay", type: "string" },
      { name: "commonName", type: "string" },
      { name: "activityMechanism", type: "string" },
      { name: "source", type: "string" },
      { name: "cellsCelllineOrgan", type: "string" },
      { name: "measured", type: "string" },
      { name: "roa", type: "string" },
      { name: "assayMethodName", type: "string" },
      { name: "dose", type: "number" },
      { name: "doseUom", type: "string" },
      { name: "dosePrefix", type: "string" },
      { name: "activity", type: "string" },
      { name: "parameter", type: "string" },
      { name: "reference", type: "string" }
    ],
    columns: [
      {
        datafield: "actId",
        text: "Act Id",
        filtertype: "number",
        width: 127,

        sortable: false,
        filterable: false
      },
      {
        datafield: "activityType",
        text: "Activity Type",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "stdActivityType",
        text: "Std Activity Type",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "activityUom",
        text: "Activity Uom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "standardUom",
        text: "Standard Uom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      // {
      //   datafield: "activityPrefix",
      //   text: "Activity Prefix",
      //   filtertype: "string",
      //   width: 127
      // },
      // {
      //   datafield: "stdActPrefix",
      //   text: "Std Act Prefix",
      //   filtertype: "string",
      //   width: 127
      // },
      {
        datafield: "activityValue",
        text: "Activity Value",
        filtertype: "number",
        width: 127
      },
      {
        datafield: "sd",
        text: "Sd",
        filtertype: "number",
        width: 127
      },
      {
        datafield: "activityRemarks",
        text: "Activity Remarks",
        filtertype: "custom",
        width: 127,
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            true
          );
        }.bind(window)
      },
      {
        datafield: "microMolarvalue",
        text: "Micro Molarvalue",
        filtertype: "number",
        width: 127
      },
      {
        datafield: "assayType",
        text: "Assay Type",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "enzymeCellAssay",
        text: "Enzyme Cell Assay",
        filtertype: "custom",
        width: 127,
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            true
          );
        }.bind(window)
      },
      {
        datafield: "commonName",
        text: "Common Name",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "activityMechanism",
        text: "Activity Mechanism",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "source",
        text: "Source",
        filtertype: "string",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "cellsCelllineOrgan",
        text: "Cells Cellline Organ",
        filtertype: "string",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "measured",
        text: "Measured",
        filtertype: "custom",
        width: 127,
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            true
          );
        }.bind(window)
      },
      {
        datafield: "roa",
        text: "Roa",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "assayMethodName",
        text: "Assay Method Name",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "dose",
        text: "Dose",
        filtertype: "number",
        width: 127
      },
      {
        datafield: "doseUom",
        text: "Dose Uom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "dosePrefix",
        text: "Dose Prefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "activity",
        text: "Activity",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "parameter",
        text: "Parameter",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        datafield: "reference",
        text: "Reference",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewActivity.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      }
    ],

    pagination: {
      pageNum: 1,
      pageSize: 10
    },
    numericFilters: {},
    stringFilters: {},
    sorting: {
      field: "act_id",
      type: "asc"
    }
  },
  reference: {
    tabPosition: 1,
    uniqueIdentifier: "reference-grid",
    columnUniqueId: "refId",
    currentTab: "reference",
    currentTabPayloadId: "Reference",
    isDataChecked: 0,
    initialDataUrl: window.config.apiUrl + "security/tabularview/referencetab",
    filterDataUrl:
      window.config.apiUrl + "security/tabularview/referencetab/filterfield",
    selectedRowIdsArr: [],
    unSelectedRowIdsArr: [],
    filterableColumns: {
      stringFilters: [],
      numericFilters: []
    },
    datafields: [
      { name: "refId", type: "number" },
      { name: "refType", type: "string" },
      { name: "startPage", type: "string" },
      { name: "applicationType", type: "string" },
      { name: "issue", type: "string" },
      { name: "year", type: "number" },
      { name: "title", type: "string" },
      { name: "pubmedId", type: "string" },
      { name: "companyAddresses", type: "string" },
      { name: "companyNames", type: "string" },
      { name: "reference", type: "string" },
      { name: "volume", type: "string" },
      { name: "abstrac", type: "string" },
      { name: "issnNo", type: "string" },
      { name: "endPage", type: "string" },
      { name: "patentNo", type: "string" },
      { name: "journalPatentName", type: "string" },
      { name: "doi", type: "string" },
      { name: "authors", type: "string" }
    ],
    columns: [
      {
        text: "Ref Id",
        datafield: "refId",
        width: 127,
        filterable: false,
        sortable: false
      },
      {
        text: "Ref Type",
        datafield: "refType",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Journal Patent Name",
        datafield: "journalPatentName",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Reference",
        datafield: "reference",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Year",
        datafield: "year",
        filtertype: "number",
        width: 127
      },
      {
        text: "Volume",
        datafield: "volume",
        type: "string",
        filtertype: "number",
        width: 127,
        filterable: false
      },
      {
        text: "Issue",
        datafield: "issue",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Start Page",
        datafield: "startPage",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "End Page",
        datafield: "endPage",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Doi",
        datafield: "doi",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window),
        sortable: false
      },
      {
        text: "Pubmed Id",
        datafield: "pubmedId",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window),
        sortable: false
      },
      {
        text: "Patent No",
        datafield: "patentNo",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Application Type",
        datafield: "applicationType",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Issn No",
        datafield: "issnNo",
        type: "string",
        filtertype: "number",
        width: 127,
        filterable: false
      },

      {
        text: "Title",
        datafield: "title",
        //type: "string",
        filtertype: "custom",
        width: 127,
        //filterable: false
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            true
          );
        }.bind(window)
      },
      {
        text: "Abstrac",
        datafield: "abstrac",
        //type: "string",
        filtertype: "custom",
        width: 127,
        //filterable: false,
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            true
          );
        }.bind(window)
      },
      {
        text: "Authors",
        datafield: "authors",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Company Names",
        datafield: "companyNames",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Company Addresses",
        datafield: "companyAddresses",
        //type: "string",
        filtertype: "custom",
        width: 127,
        //filterable: false
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewReference.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            true
          );
        }.bind(window)
      }
    ],

    pagination: {
      pageNum: 1,
      pageSize: 10
    },
    numericFilters: {},
    stringFilters: {},
    sorting: {
      field: "refId",
      type: "asc"
    }
  },
  structure: {
    tabPosition: 2,
    uniqueIdentifier: "structure-grid",
    columnUniqueId: "gvkId",
    currentTab: "structure",
    currentTabPayloadId: "Structure",
    isDataChecked: 0,
    initialDataUrl: window.config.apiUrl + "security/tabularview/structuretab",
    filterDataUrl:
      window.config.apiUrl + "security/tabularview/structuretab/filterfield",
    selectedRowIdsArr: [],
    unSelectedRowIdsArr: [],
    filterableColumns: {
      stringFilters: [],
      numericFilters: []
    },
    datafields: [
      { name: "gvkId", type: "Number" },
      { name: "derivatives", type: "String" },
      { name: "primaryTargets", type: "String" },
      { name: "inchiKey", type: "String" },
      { name: "bioAssay", type: "String" },
      { name: "molFormula", type: "String" },
      { name: "compoundStatus", type: "String" },
      { name: "subSmiles", type: "String" },
      { name: "compoundNames", type: "String" },
      { name: "originator", type: "String" },
      { name: "mechanisms", type: "String" },
      { name: "molWeight", type: "String" }
    ],
    columns: [
      {
        text: "Gvk Id",
        datafield: "gvkId",
        width: 127,
        sortable: false,
        filterable: false
      },
      {
        text: "Compound Names",
        datafield: "compoundNames",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Sub Smiles",
        datafield: "subSmiles",
        filtertype: "number",
       // width: 127,
        width: 150,
        filterable: false,
        sortable: false,
        cellsrenderer: applyImageOnCellHoverRenderer
      },
      {
        text: "Inchi Key",
        datafield: "inchiKey",
        filtertype: "number",
        width: 127,
        filterable: false,
        sortable: false
      },
      {
        text: "Originator",
        datafield: "originator",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Derivatives",
        datafield: "derivatives",
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Bio Assay",
        datafield: "bioAssay",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Primary Targets",
        datafield: "primaryTargets",

        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Mechanisms",
        datafield: "mechanisms",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Compound Status",
        datafield: "compoundStatus",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewStructure.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },

      {
        text: "Mol Formula",
        datafield: "molFormula",
        filtertype: "number",
        width: 127,
        filterable: false
      },

      {
        text: "Mol Weight",
        datafield: "molWeight",
        filtertype: "number",
        width: 127,
        filterable: false
      }
    ],

    pagination: {
      pageNum: 1,
      pageSize: 10
    },
    numericFilters: {},
    stringFilters: {},
    sorting: {
      field: "gvkId",
      type: "asc"
    }
  },
  assay: {
    tabPosition: 3,
    uniqueIdentifier: "assay-grid",
    columnUniqueId: "assayId",
    currentTab: "assay",
    currentTabPayloadId: "Assay",
    isDataChecked: 0,
    initialDataUrl: window.config.apiUrl + "security/tabularview/assaytab",
    filterDataUrl:
      window.config.apiUrl + "security/tabularview/assaytab/filterfield",
    selectedRowIdsArr: [],
    unSelectedRowIdsArr: [],
    filterableColumns: {
      stringFilters: [],
      numericFilters: []
    },
    datafields: [
      { name: "inPresenceOfPrefix", type: "String" },
      { name: "p_s_m", type: "String" },
      { name: "coAdministeredPrefix", type: "String" },
      { name: "coAdministeredValue", type: "Number" },
      { name: "substrateUom", type: "String" },
      { name: "assayId", type: "String" },
      { name: "incubationTime", type: "Number" },
      { name: "assayPosition", type: "String" },
      { name: "substratePrefix", type: "String" },
      { name: "radioLigandPrefix", type: "String" },
      { name: "radioLigandUom", type: "String" },
      { name: "inPresenceOf", type: "String" },
      { name: "incubationPrefix", type: "String" },
      { name: "newResidue", type: "String" },
      { name: "coAdministeredUom", type: "String" },
      { name: "inPresenceOfConc", type: "Number" },
      { name: "bufferConc", type: "Number" },
      { name: "radioLigand", type: "String" },
      { name: "protein", type: "String" },
      { name: "bufferPrefix", type: "String" },
      { name: "radioLigandConc", type: "Number" },
      { name: "temperature", type: "Number" },
      { name: "bufferUom", type: "String" },
      { name: "buffer", type: "String" },
      { name: "substrate", type: "String" },
      { name: "inPresenceOfUom", type: "String" },
      { name: "incubationUom", type: "String" },
      { name: "standardName", type: "String" },
      { name: "coAdministered", type: "String" },
      { name: "temperatureUom", type: "String" },
      { name: "wildType", type: "String" },
      { name: "oldResidue", type: "String" },
      { name: "phPrefix", type: "String" },
      { name: "temperaturePrefix", type: "String" },
      { name: "ph", type: "Number" },
      { name: "targetProfile", type: "String" },
      { name: "mutant", type: "String" },
      { name: "operation", type: "String" },
      { name: "substrateConc", type: "Number" }
    ],
    columns: [
      {
        text: "Assay Id",
        datafield: "assayId",
        sortable: false,
        width: 127
      },
      {
        text: "Protein",
        datafield: "protein",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Standard Name",
        datafield: "standardName",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Mutant",
        datafield: "mutant",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Wild Type",
        datafield: "wildType",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "New Residue",
        datafield: "newResidue",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Old Residue",
        datafield: "oldResidue",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Operation",
        datafield: "operation",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },

      {
        text: "Assay Position",
        datafield: "assayPosition",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Target Profile",
        datafield: "targetProfile",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "P S M",
        datafield: "p_s_m",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Buffer",
        datafield: "buffer",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Buffer Conc",
        datafield: "bufferConc",
        width: 127,
        filtertype: "number"
      },

      {
        text: "Buffer Prefix",
        datafield: "bufferPrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "BufferUom",
        datafield: "bufferUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Substrate",
        datafield: "substrate",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Substrate Conc",
        datafield: "substrateConc",
        width: 127,
        filtertype: "number"
      },
      {
        text: "Substrate Prefix",
        datafield: "substratePrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Substrate Uom",
        datafield: "substrateUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Radio Ligand",
        datafield: "radioLigand",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Radio Ligand Prefix",
        datafield: "radioLigandPrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Radio Ligand Uom",
        datafield: "radioLigandUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Radio Ligand Conc",
        datafield: "radioLigandConc",
        width: 127,
        filtertype: "number"
      },
      {
        text: "Co Administered",
        datafield: "coAdministered",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Co Administered Prefix",
        datafield: "coAdministeredPrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Co Administered Uom",
        datafield: "coAdministeredUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Co Administered Value",
        datafield: "coAdministeredValue",
        filtertype: "number",
        width: 180
      },
      {
        text: "In Presence Of",
        datafield: "inPresenceOf",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "In PresenceOfPrefix",
        datafield: "inPresenceOfPrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },

      {
        text: "In Presence Of Uom",
        datafield: "inPresenceOfUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },

      {
        text: "In Presence Of Conc",
        datafield: "inPresenceOfConc",
        width: 127,
        filtertype: "number"
      },

      {
        text: "Incubation Time",
        datafield: "incubationTime",
        width: 127,
        filtertype: "number"
      },
      {
        text: "Incubation Prefix",
        datafield: "incubationPrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Incubation Uom",
        datafield: "incubationUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },

      {
        text: "Temperature",
        datafield: "temperature",
        width: 127,
        filtertype: "number"
      },
      {
        text: "Temperature Prefix",
        datafield: "temperaturePrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },

      {
        text: "Temperature Uom",
        datafield: "temperatureUom",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Ph Prefix",
        datafield: "phPrefix",
        width: 127,
        filtertype: "custom",
        createfilterpanel: function(datafield, filterPanel) {
          tabularViewAssay.buildCheckboxWithInputFilterPanel(
            filterPanel,
            datafield,
            false
          );
        }.bind(window)
      },
      {
        text: "Ph",
        datafield: "ph",
        width: 127,
        filtertype: "number"
      }
    ],

    pagination: {
      pageNum: 1,
      pageSize: 10
    },
    numericFilters: {},
    stringFilters: {},
    sorting: {
      field: "assayId",
      type: "asc"
    }
  }
};

var gbl_tabularview_initial_store = JSON.parse(
  JSON.stringify(gbl_tabularview_store)
);

// this array will be updated based on user criteria of selected tabs.
// unselected tabs will not be included in this array
var gbl_active_tabular_tabs = ["activity", "reference", "structure"];

var gbl_total_tabular_tabs = [
  { label: "(Select All)", value: "(Select All)" },
  { label: "Assay", value: "assay" },
  { label: "Reference", value: "reference" },
  { label: "Structure", value: "structure" },
  { label: "Activity", value: "activity" }
];

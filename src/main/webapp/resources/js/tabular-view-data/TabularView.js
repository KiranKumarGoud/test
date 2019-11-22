function TabularView(CUR_TAB) {
  this.CUR_TAB = CUR_TAB;
  var curr_data;
  this.source = {
    datatype: "json",
    // get these columes from different file
    datafields: this.CUR_TAB.datafields,
    cache: false,
    id: "id",
    url: this.CUR_TAB.initialDataUrl,
    type: "POST",
    filter: function() {
      // update the grid and send a request to the server.
      $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
        "updatebounddata",
        "filter"
      );
    }.bind(this),

    beforeprocessing: function(data) {
      this.source.totalrecords = data.count;

      // hide/display tabs
      if (data.count == 0) {
        // $("#jqxTabs .jqx-tabs-title:eq(" + this.CUR_TAB.tabPosition + ")").css(
        //   "display",
        //   "none"
        // );
        // $("#jqxTabs").jqxTabs("select", this.CUR_TAB.tabPosition + 1);
      } else {
        // $("#jqxTabs .jqx-tabs-title:eq(" + this.CUR_TAB.tabPosition + ")").css(
        //   "display",
        //   "block"
        // );
      }
    }.bind(this),
    rendergridrows: function(params) {
      return params.data;
    }.bind(this),
    sort: function(data) {
      // update the grid and send a request to the server.
      $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid("updatebounddata", "sort");
    }.bind(this)
  };

  //@TODO make this dynamic for all the tabs
  setTimeout(function() {
    $("#contentactivity-grid").mouseout(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[0]) {
        $(".jqx-tooltip")[0].style.visibility = "hidden";
        $(".jqx-tooltip")[0].firstChild.children[0].innerText = "";
      }
    });
    $("#contentactivity-grid").mouseover(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[0]) {
        $(".jqx-tooltip")[0].style.visibility = "visible";
      }
    });

    $("#contentreference-grid").mouseout(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[1]) {
        $(".jqx-tooltip")[1].style.visibility = "hidden";
        $(".jqx-tooltip")[1].firstChild.children[0].innerText = "";
      }
    });
    $("#contentreference-grid").mouseover(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[1]) {
        $(".jqx-tooltip")[1].style.visibility = "visible";
      }
    });

    $("#contentstructure-grid").mouseout(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[2]) {
        $(".jqx-tooltip")[2].style.visibility = "hidden";
        $(".jqx-tooltip")[2].firstChild.children[0].innerText = "";
      }
    });
    $("#contentstructure-grid").mouseover(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[2]) {
        $(".jqx-tooltip")[2].style.visibility = "visible";
      }
    });

    $("#contentassay-grid").mouseout(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[3]) {
        $(".jqx-tooltip")[3].style.visibility = "hidden";
        $(".jqx-tooltip")[3].firstChild.children[0].innerText = "";
      }
    });
    $("#contentassay-grid").mouseover(function() {
      if (undefined != $(".jqx-tooltip") && undefined != $(".jqx-tooltip")[3]) {
        $(".jqx-tooltip")[3].style.visibility = "visible";
      }
    });
  }, 0);

  $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid({
    virtualmode: true,
    columnsreorder: true,
    width: "99%",
    height: "450px",
    cellhover: function(element, pageX, pageY) {
      var showSubSmilesElement = $(element).find(".apply-image-hover");

      if (element.innerText) {
        var rowData = $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
          "getrowdata",
          $(element)
            .find("span")
            .data("row")
        );

        if (showSubSmilesElement.length && rowData) {
          // update tooltip.
          // $(this).jqxTooltip({
          //   content: "<img src='assets/smiles-images/12345.png' />"
          // });
          // // open tooltip.
          // $(this).jqxTooltip("open", pageX + 15, pageY + 15);
          // return;
        }
        // update tooltip.
        $(this).jqxTooltip({ content: element.innerHTML });
        // open tooltip.
        $(this).jqxTooltip("open", pageX + 15, pageY + 15);
      } else {
        $(this).jqxTooltip("close");
      }
    }.bind(this),
    source: new $.jqx.dataAdapter(this.source, {
      loadComplete: function(data) {
        if (this.CUR_TAB.selectedRowIdsArr.length > 0) {
          $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid("clearselection");
          for (z = 0; z < this.CUR_TAB.selectedRowIdsArr.length; z++) {
            var val = this.CUR_TAB.selectedRowIdsArr[z];
            if (
              this.CUR_TAB.uniqueIdentifier == "activity-grid" &&
              undefined != data.activityTabDTOList
            ) {
              curr_data = data.activityTabDTOList;
              var index = data.activityTabDTOList.findIndex(function(item, i) {
                return item.actId === val;
              });
            } else if (
              this.CUR_TAB.uniqueIdentifier == "reference-grid" &&
              undefined != data.referenceTabDTOList
            ) {
              curr_data = data.referenceTabDTOList;
              var index = data.referenceTabDTOList.findIndex(function(item, i) {
                return item.refId === val;
              });
            } else if (
              this.CUR_TAB.uniqueIdentifier == "structure-grid" &&
              undefined != data.structureDetailsTabDTOList
            ) {
              curr_data = data.structureDetailsTabDTOList;
              var index = data.structureDetailsTabDTOList.findIndex(function(
                item,
                i
              ) {
                return item.gvkId === val;
              });
            } else if (
              this.CUR_TAB.uniqueIdentifier == "assay-grid" &&
              undefined != data.assayTabDTOList
            ) {
              curr_data = data.assayTabDTOList;
              var index = data.assayTabDTOList.findIndex(function(item, i) {
                return item.assayId === val;
              });
            } else {
              var currIdenti = this.CUR_TAB.uniqueIdentifier;
              var indexVal =
                this.CUR_TAB.pagination.pageNum *
                this.CUR_TAB.pagination.pageSize;
              var resVal;
              var index = this.source.records.findIndex(function(item, i) {
                if (currIdenti == "activity-grid") resVal = item.actId;
                else if (currIdenti == "reference-grid") resVal = item.refId;
                else if (currIdenti == "structure-grid") resVal = item.gvkId;
                else if (currIdenti == "assay-grid") resVal = item.assayId;

                return resVal === val;
              });
            }

            if (index >= 0) {
              // $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid('updatebounddata', 'row');
              var paginginformation = $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid('getpaginginformation');
             
              let newIndex = index + (paginginformation.pagenum * paginginformation.pagesize);
              $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
                "selectrow",
                newIndex
              );
              
            }
          }
        }
      }.bind(this),
      beforeSend: function(jqXHR, settings) {
        jqXHR.setRequestHeader("Content-Type", "application/json");
        jqXHR.setRequestHeader(
          "userSessionId",
          localStorage.getItem("userSessionId")
        );
      }.bind(this),
      // format and sent post params here
      formatData: function(data) {
        let currentActiveTabKey = this.getCurrentActiveTab();
        this.source.url =
          gbl_tabularview_store[currentActiveTabKey]["initialDataUrl"];
        return this.formatRequestPayload(data, "main");
      }.bind(this)
    }),
    showfilterrow: false,
    selectionmode: "checkbox",
    altrows: true,
    // filterMode: 'excel',
    filterable: true,
    columnmenuopening: function(menu, datafield, height) {
      var column = $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
        "getcolumn",
        datafield
      );
      this.filterStatus = false;      
      if (column.filtertype === "custom") {
        menu.height(300);
        setTimeout(function() {
          menu.find("input").focus();
        }, 25);
      } else menu.height(height);
    }.bind(this),
    columns: this.CUR_TAB.columns,
    pageable: true,
    // filterMode: 'excel',
    showsortmenuitems: false,
    // selectionmode: 'multiplecellsextended',
    // showfilterrow: true,
    rendergridrows: function(params) {
      return params.data;
    }.bind(this),
    sortable: true,
    sorttogglestates: 1
  });

  // Tab columns reorder.
  $("#" + this.CUR_TAB.uniqueIdentifier).on("columnreordered", function(event) {
    var column = event.args.columntext;
    var newindex = event.args.newindex;
    var oldindex = event.args.oldindex;
    console.log(
      "Column: " +
        column +
        ", " +
        "New Index: " +
        newindex +
        ", Old Index: " +
        oldindex
    );
  });

  // TAB level filter reset button
  $("#" + this.CUR_TAB.uniqueIdentifier + "-clearfilteringbutton").jqxButton({
    height: 25
  });
  $("#" + this.CUR_TAB.uniqueIdentifier + "-clearfilteringbutton").click(
    function() {
      window.getFilterResultsGlobalCount();
      $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid("clearfilters");
    }.bind(this)
  );

  // ROWS SELECTION LOGIC

  $("#" + this.CUR_TAB.uniqueIdentifier).bind(
    "rowselect",
    function(event) {
      let rowData = "";
      if (Array.isArray(event.args.rowindex)) {
        if (event.args.rowindex.length > 0) {
          this.isAllSelected = "ALL_SELECTED";
          this.CUR_TAB.isDataChecked = 2;
        } else {
          this.isAllSelected = "ALL_UNSELECTED";
          this.CUR_TAB.isDataChecked = 0;
        }
      } else {
        rowData = $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
          "getrowdata",
          event.args.rowindex
        );

        if (rowData == undefined) {
          rowData = {};
          if (this.CUR_TAB.uniqueIdentifier == "activity-grid") {
            rowData[this.CUR_TAB.columnUniqueId] =
              curr_data[event.args.rowindex].actId;
          } else if (this.CUR_TAB.uniqueIdentifier == "reference-grid") {
            rowData[this.CUR_TAB.columnUniqueId] =
              curr_data[event.args.rowindex].refId;
          } else if (this.CUR_TAB.uniqueIdentifier == "structure-grid") {
            rowData[this.CUR_TAB.columnUniqueId] =
              curr_data[event.args.rowindex].gvkId;
          } else if (this.CUR_TAB.uniqueIdentifier == "assay-grid") {
            rowData[this.CUR_TAB.columnUniqueId] =
              curr_data[event.args.rowindex].assayId;
          }
        }

        this.isAllSelected = "SINGLE_SELECTED";
        this.CUR_TAB.isDataChecked = 1;
      }

      if (
        this.isAllSelected == "ALL_SELECTED" ||
        this.isAllSelected == "ALL_UNSELECTED"
      ) {
        updateRowSelectedValues(
          this.isAllSelected,
          [],
          this.CUR_TAB.currentTab
        );
      } else {
        updateRowSelectedValues(
          this.isAllSelected,
          rowData[this.CUR_TAB.columnUniqueId],
          this.CUR_TAB.currentTab
        );
      }
    }.bind(this)
  );

  $("#" + this.CUR_TAB.uniqueIdentifier).bind(
    "rowunselect",
    function(event) {
      var selectedRowIndexes = $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
        "getselectedrowindexes"
      );
      console.log("Unselected row has index = " + event.args.rowindex);
      console.log(selectedRowIndexes);
      this.isAllSelected = "SINGLE_UNSELECTED";
      if (selectedRowIndexes.length == 0) {
        this.CUR_TAB.isDataChecked = 0;
      } else {
        this.CUR_TAB.isDataChecked = 1;
      }

      let rowData = $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
        "getrowdata",
        event.args.rowindex
      );

      if (undefined != rowData && rowData) {
        updateRowSelectedValues(
          this.isAllSelected,
          rowData[this.CUR_TAB.columnUniqueId],
          this.CUR_TAB.currentTab
        );
      }
    }.bind(this)
  );
}

TabularView.prototype.getCurrentTabData = function(tabIndex) {
  let tabKey = "";
  for (let i in gbl_tabularview_store) {
    if (gbl_tabularview_store[i]["tabPosition"] == tabIndex) {
      gbl_tabularview_store[i]["isActiveTab"] = true;
      tabKey = gbl_tabularview_store[i]["currentTab"];
    } else {
      gbl_tabularview_store[i]["isActiveTab"] = false;
    }
  }

  updateGblTabularviewStore(gbl_tabularview_store);
  return tabKey;
};

TabularView.prototype.setFilterParams = function(
  currentActiveTabKey,
  filterParams
) {
  for (let filter in filterParams) {
    let sameFieldConditions = {};
    let currentField = "";
    currentField = filterParams[filter]["field"];
    if (filterParams[filter]["filters"][0]["type"] == "stringfilter") {
      gbl_tabularview_store[currentActiveTabKey]["stringFilters"][
        currentField
      ] = filterParams[filter]["filters"][0]["value"].split(",");
    } else if (filterParams[filter]["filters"][0]["type"] == "numericfilter") {
      if (filterParams[filter]["filters"]["length"] == 2) {
        sameFieldConditions["minValue"] = parseFloat(
          filterParams[filter]["filters"][0]["value"]
        );
        sameFieldConditions["maxValue"] = parseFloat(
          filterParams[filter]["filters"][1]["value"]
        );
        sameFieldConditions["operator"] = "between";
      } else if (filterParams[filter]["filters"]["length"] == 1) {
        sameFieldConditions["minValue"] = parseFloat(
          filterParams[filter]["filters"][0]["value"]
        );
        sameFieldConditions["maxValue"] = parseFloat(
          filterParams[filter]["filters"][0]["value"]
        );
        if (filterParams[filter]["filters"][0]["condition"] == "LESS_THAN") {
          sameFieldConditions["operator"] = "<";
        } else if (
          filterParams[filter]["filters"][0]["condition"] ==
          "LESS_THAN_OR_EQUAL"
        ) {
          sameFieldConditions["operator"] = "<=";
        } else if (
          filterParams[filter]["filters"][0]["condition"] ==
          "GREATER_THAN_OR_EQUAL"
        ) {
          sameFieldConditions["operator"] = ">=";
        } else if (
          filterParams[filter]["filters"][0]["condition"] == "GREATER_THAN"
        ) {
          sameFieldConditions["operator"] = ">";
        } else if (filterParams[filter]["filters"][0]["condition"] == "EQUAL") {
          sameFieldConditions["operator"] = "=";
        } else if (
          filterParams[filter]["filters"][0]["condition"] == "NOT_EQUAL"
        ) {
          sameFieldConditions["operator"] = "!=";
        }
      }
    }
    if (Object.keys(sameFieldConditions).length > 0) {
      gbl_tabularview_store[currentActiveTabKey]["numericFilters"][
        currentField
      ] = sameFieldConditions;
    }
  }

  updateGblTabularviewStore(gbl_tabularview_store);
};
let filterStatus = false;
var selectedFilterItems = [];
var unSelectedFilterItems = [];
var selectedFilterItemsFlag = false;
TabularView.prototype.bindFilterResultsToPopup = function(
  filterResponse,
  datafield,
  filterPanel
) {
  var curTab = this.CUR_TAB;      
  let query = filterPanel.find("input").val() || "";
  if (query) {
    let newArr = [];
    this.filterStatus = true;
    filterResponse.forEach((item, index) => {
      newArr.push(filterResponse[index][datafield]);
    });
    if (newArr.length) {
      newArr.splice(0, 0, "(Select All)");
    } else {
      filterPanel
        .find("#filterbox")
        .find("#listBoxContentfilterbox")
        .html("No results found");
    }
    filterPanel.find("#filterbox").jqxListBox({
      source: newArr,
      displayMember: datafield
    });
    if(selectedFilterItemsFlag) { 
      var listItems = filterPanel.find("#filterbox").jqxListBox('getItems');
      for(var j=0;j<selectedFilterItems.length;j++) {
        if(selectedFilterItems[j] && selectedFilterItems[j] != '(Select All)') {
          var listVal = selectedFilterItems[j];
          var index = listItems.findIndex(function(item, i) {
            return item.value === listVal;
          });
          if(index >= 0) {
            filterPanel.find("#filterbox").jqxListBox('checkIndex', index);
          }
        }          
      } 
      var listCheckedItems = filterPanel.find("#filterbox").jqxListBox('getCheckedItems');
      if(listItems.length-1 == listCheckedItems.length || listItems.length == listCheckedItems.length) 
        filterPanel.find("#filterbox").jqxListBox('checkIndex', 0);
      else 
        filterPanel.find("#filterbox").jqxListBox('uncheckIndex', 0);
    }  
    return;
  } 
  if(this.filterStatus){
    let newArr = [];
    filterResponse.forEach((item, index) => {
      newArr.push(filterResponse[index][datafield]);
    });
    if (newArr.length) {
      newArr.splice(0, 0, "(Select All)");
    }
    filterPanel.find("#filterbox").jqxListBox({
      source: newArr,
      displayMember: datafield
    });  
    if(selectedFilterItemsFlag) { 
      var listItems = filterPanel.find("#filterbox").jqxListBox('getItems');
      for(var j=0;j<selectedFilterItems.length;j++) {
        if(selectedFilterItems[j] && selectedFilterItems[j] != '(Select All)') {
          var listVal = selectedFilterItems[j];
          var index = listItems.findIndex(function(item, i) {
            return item.value === listVal;
          });
          if(index >= 0) {
            filterPanel.find("#filterbox").jqxListBox('checkIndex', index);
          }
        }          
      } 
      var listCheckedItems = filterPanel.find("#filterbox").jqxListBox('getCheckedItems');
      if(listItems.length-1 == listCheckedItems.length || listItems.length == listCheckedItems.length) 
        filterPanel.find("#filterbox").jqxListBox('checkIndex', 0);
      else 
        filterPanel.find("#filterbox").jqxListBox('uncheckIndex', 0);
    }          
    return;
  }
  
  var textInput = $("<input style='margin:5px;'/>");
  var applyinput = $(
    "<div class='filter' style='height: 290px; margin: 7px 0 0 10px;'></div>"
  );

  var filterListBox = $(
    "<div id='filterbox' ></div><div id='no-results></div>'"
  ).jqxListBox({
    checkboxes: true,
    width: "90%",
    height: "100px"
  });

  let newArr = [];
  filterResponse.forEach((item, index) => {
    newArr.push(filterResponse[index][datafield]);
  });
  newArr.splice(0, 0, "(Select All)");
  $(filterListBox).jqxListBox({
    source: newArr,
    displayMember: datafield
  });

  applyinput.append(filterListBox);
  $(filterListBox).jqxListBox("checkAll");

  var filterbutton = $(
    '<span tabindex="0" style="padding: 4px 12px; margin: 2px;">Filter</span>'
  );
  applyinput.append(filterbutton);
  var filterclearbutton = $(
    '<span tabindex="0" style="padding: 4px 12px; margin-left: 5px;">Clear</span>'
  );
  applyinput.append(filterclearbutton);
  if($("#gridmenu" + curTab.uniqueIdentifier) && $("#gridmenu" + curTab.uniqueIdentifier).find('.filter').find("#filterbox").length < 1) {
    filterPanel.append(textInput);
    filterPanel.append(applyinput);
  }  
  filterbutton.jqxButton({ height: 20 });
  filterclearbutton.jqxButton({ height: 20 });
  var column = $("#" + curTab.uniqueIdentifier).jqxGrid("getcolumn", datafield);

  textInput.jqxInput({
    placeHolder: "Enter " + column.text,
    popupZIndex: 9999999,
    height: 23,
    width: 175
  });
  textInput.keyup(
    function(event) {
      this.updateFilterBox(datafield, filterPanel, event.target.value,false);
      // if (event.target.value.length > 3) {
      // }
    }.bind(this)
  );

  // STRING FILTERS -- SEND REQUEST ON FILTER BTN CLICK
  filterbutton.click(function() {
    var filtergroup = new $.jqx.filter();

    // get all items.
    var items = $(filterListBox).jqxListBox("getItems");

    let i = 0;
    var selectedItems = [];
    for (var index in items) {
      if (items[index]["checked"] && items[index]["value"]) {
        selectedItems[i] = items[index]["value"];
        i++;
      }
    }

    var filter_or_operator = 1;
    var filtervalue = selectedItems;
    var filtercondition = "contains";
    var filter1 = filtergroup.createfilter(
      "stringfilter",
      filtervalue,
      filtercondition
    );

    filtergroup = Object.assign(filtergroup, {
      fieldName: datafield,
      fieldType: "String",
      fieldOperator: "contains",
      fieldValue: JSON.stringify(selectedItems)
    });
    filtergroup.addfilter(filter_or_operator, filter1);
    // // add the filters.
    $("#" + curTab.uniqueIdentifier).jqxGrid(
      "addfilter",
      datafield,
      filtergroup
    );
    // apply the filters.
    $("#" + curTab.uniqueIdentifier).jqxGrid("applyfilters");
    $("#" + curTab.uniqueIdentifier).jqxGrid("closemenu");

    window.getFilterResultsGlobalCount();
  });

  // CLEAR COLUMN FILTERS BY COLUMN NAME
  filterclearbutton.click(
    function() {
      if($('#gridmenu'+ curTab.uniqueIdentifier).find('.jqx-input-widget')[0])
        $('#gridmenu'+ curTab.uniqueIdentifier).find('.jqx-input-widget')[0].value = "";
      this.filterStatus = true;
      selectedFilterItemsFlag = false;
      selectedFilterItems = [];
      $(filterListBox).jqxListBox("checkAll");      
      this.resetColumnFilters(curTab.currentTab, datafield);
      $("#" + curTab.uniqueIdentifier).jqxGrid("removefilter", datafield);
      // apply the filters.
      $("#" + curTab.uniqueIdentifier).jqxGrid("applyfilters");
      $("#" + curTab.uniqueIdentifier).jqxGrid("closemenu");
      this.source.url = "/gostarjdbcservice/security/tabularview/"+this.CUR_TAB.currentTab+"tab/filterfield";
      this.source.datafield = datafield;
      this.source.dataFieldQuery = undefined;      
      var filterBoxAdapter = new $.jqx.dataAdapter(this.source, {
        loadComplete: function() {
          this.bindFilterResultsToPopup(
            filterBoxAdapter.records,
            datafield,
            filterPanel
          );
        }.bind(this),
        beforeSend: function(jqXHR, settings) {
          jqXHR.setRequestHeader("Content-Type", "application/json");
          jqXHR.setRequestHeader(
            "userSessionId",
            localStorage.getItem("userSessionId")
          );
        }.bind(this),
        // format and sent post params here
        formatData: function(data) {
          return this.formatRequestPayload(data, "filter", this.source.datafield);
        }.bind(this),
        autoBind: false
      });
      filterBoxAdapter.dataBind();
    }.bind(this)
  );

  // SEARCH STRING INPUT IN THE FILTERS
  filterclearbutton.keydown(function(event) {
    if (event.keyCode === 13) {
      filterclearbutton.trigger("click");
    }
    textInput.val("");
  });

  // FILTER SEARCH LIST BOX FOR STRING FILTERS SELECT EVENT CHANGES
  $(filterListBox).bind("select", function(event) {
    var args = event.args;
    selectedFilterItemsFlag = true;
    //get the item and it's label and value fields.
    var item = args.item;
    //I check or un check all just if the item with 0 value is changed
    if (item.value == "(Select All)") {
      // get new check state.
      var checked = args.item.checked;
      if (checked) {
        $(this).jqxListBox("checkAll");       
          var listItems = $(this).jqxListBox("getCheckedItems");
          if(selectedFilterItems.length == 0) {
            for(var i=0;i<listItems.length;i++) {
              selectedFilterItems.push(listItems[i].value);
            }
          } else {
            for(var j=0;j<listItems.length;j++) {
              var index = selectedFilterItems.findIndex(function(selectItems, i) {
                return selectItems === listItems[j].value;
              });
              if(index < 0 && listItems[j].value != '(Select All)') {
                selectedFilterItems.push(listItems[j].value);
              }
            } 
          }                
      } else {
        var listItems = $(this).jqxListBox("getCheckedItems");
        for(var j=0;j<listItems.length;j++) {
          var index = selectedFilterItems.findIndex(function(selectItems, i) {
            return selectItems === listItems[j].value;
          });
          if(index >= 0) {
            selectedFilterItems.splice(index,1);
          }
        }
        $(this).jqxListBox("uncheckAll");
      }
    } else {
      var totalItems = $(this).jqxListBox("getItems");
      let selectedItems = $(this).jqxListBox("getCheckedItems");
      if((totalItems.length-1) == selectedItems.length) {
        if(args.item.checked) {
          $(this).jqxListBox("checkAll");
        } else {
          selectedItems[0].checked = false;
        }
      }
      if(selectedFilterItems.length == 0) {
        selectedFilterItems.push(item.value);
      } else {
        var index = selectedFilterItems.findIndex(function(filterItem, i) {
          return filterItem === item.value;
        });
        if(index < 0)
          selectedFilterItems.push(item.value);
        else if(!item.checked)
          selectedFilterItems.splice(index,1);
      }
    }
  });
};
var plainTextInputValue = {};
TabularView.prototype.bindFilterResultsToFreeTextPopup = function(  
  datafield,
  filterPanel
) {
  var curTab = this.CUR_TAB;      
  var textInput = $("<input style='margin:5px;'/>");
  var applyinput = $(
    "<div class='filter' style='height: 290px; margin: 7px 0 0 10px;'></div>"
  );

  var filterListBox = $(
    "<div id='filterbox' style='display:none;'></div><div id='no-results></div>'"
  ).jqxListBox({
    checkboxes: true,
    width: "90%",
    height: "100px"
  }); 
  applyinput.append(filterListBox);
  var filterbutton = $(
    '<span tabindex="0" style="padding: 4px 12px; margin: 2px;">Filter</span>'
  );
  applyinput.append(filterbutton);
  var filterclearbutton = $(
    '<span tabindex="0" style="padding: 4px 12px; margin-left: 5px;">Clear</span>'
  );
  applyinput.append(filterclearbutton);
  if($("#gridmenu" + curTab.uniqueIdentifier) && $("#gridmenu" + curTab.uniqueIdentifier).find('.filter').find("#filterbox").length < 1) {
    filterPanel.append(textInput);
    filterPanel.append(applyinput);
  }  
  if(plainTextInputValue[datafield])
    textInput[0].value  = plainTextInputValue[datafield]; 
  filterbutton.jqxButton({ height: 20 });
  filterclearbutton.jqxButton({ height: 20 });
  var column = $("#" + curTab.uniqueIdentifier).jqxGrid("getcolumn", datafield);
  
  textInput.jqxInput({
    placeHolder: "Enter " + column.text,
    popupZIndex: 9999999,
    height: 23,
    width: 175
  });
  textInput.keyup(
    function(event) {
      this.updateFilterBox(datafield, filterPanel, event.target.value,true);
      // if (event.target.value.length > 3) {
      // }
    }.bind(this)
  );

  // STRING FILTERS -- SEND REQUEST ON FILTER BTN CLICK
  filterbutton.click(function() {
    var filterBoxValue = $('#gridmenu' + curTab.uniqueIdentifier).find('.jqx-input-widget')[0].value;  
    if(filterBoxValue == "" || filterBoxValue == undefined) 
      delete plainTextInputValue[datafield];
    else 
      plainTextInputValue[datafield] = filterBoxValue;
    $("#" + curTab.uniqueIdentifier).jqxGrid("closemenu");
    this.source.url = "/gostarjdbcservice/security/tabularview/"+this.CUR_TAB.currentTab+"tab";
    this.source.datafield = datafield;
    //this.source.dataFieldQuery = filterBoxValue;     
    var dataBoxAdapter = new $.jqx.dataAdapter(this.source, {
      loadComplete: function(data) {
        $("#" + this.CUR_TAB.uniqueIdentifier).jqxGrid(
          "updatebounddata"
        ); 
      }.bind(this),
      beforeSend: function(jqXHR, settings) {
        jqXHR.setRequestHeader("Content-Type", "application/json");
        jqXHR.setRequestHeader(
          "userSessionId",
          localStorage.getItem("userSessionId")
        );
      }.bind(this),
      // format and sent post params here
      formatData: function(data) {  
        data.pagenum = this.CUR_TAB.pagination.pageNum-1;
        data.pagesize = this.CUR_TAB.pagination.pageSize;
        return this.formatRequestPayload(data, "main");
      }.bind(this)
    });
    dataBoxAdapter.dataBind();
    window.getFilterResultsGlobalCount();
  }.bind(this));

  // CLEAR COLUMN FILTERS BY COLUMN NAME
  filterclearbutton.click(
    function() {
      if($('#gridmenu' + curTab.uniqueIdentifier).find('.jqx-input-widget')[0])
      $('#gridmenu' + curTab.uniqueIdentifier).find('.jqx-input-widget')[0].value = "";
      this.filterStatus = true;
      delete plainTextInputValue[datafield];
      selectedFilterItemsFlag = false;
      selectedFilterItems = [];
      $(filterListBox).jqxListBox("checkAll");      
      this.resetColumnFilters(curTab.currentTab, datafield);
      $("#" + curTab.uniqueIdentifier).jqxGrid("removefilter", datafield);
      // apply the filters.
      $("#" + curTab.uniqueIdentifier).jqxGrid("applyfilters");
      $("#" + curTab.uniqueIdentifier).jqxGrid("closemenu");
      this.source.url = "/gostarjdbcservice/security/tabularview/"+this.CUR_TAB.currentTab+"tab/filterfield";
      this.source.datafield = datafield;
      this.source.dataFieldQuery = undefined;      
      var filterBoxAdapter = new $.jqx.dataAdapter(this.source, {
        loadComplete: function() {
          this.bindFilterResultsToFreeTextPopup(            
            datafield,
            filterPanel
          );
        }.bind(this),
        beforeSend: function(jqXHR, settings) {
          jqXHR.setRequestHeader("Content-Type", "application/json");
          jqXHR.setRequestHeader(
            "userSessionId",
            localStorage.getItem("userSessionId")
          );
        }.bind(this),
        // format and sent post params here
        formatData: function(data) {
          return this.formatRequestPayload(data, "filter", this.source.datafield);
        }.bind(this),
        autoBind: false
      });
      filterBoxAdapter.dataBind();
    }.bind(this)
  );

  // SEARCH STRING INPUT IN THE FILTERS
  filterclearbutton.keydown(function(event) {
    if (event.keyCode === 13) {
      filterclearbutton.trigger("click");
    }
    textInput.val("");
  });

  // FILTER SEARCH LIST BOX FOR STRING FILTERS SELECT EVENT CHANGES
  $(filterListBox).bind("select", function(event) {
    var args = event.args;
    selectedFilterItemsFlag = true;
    //get the item and it's label and value fields.
    var item = args.item;
    //I check or un check all just if the item with 0 value is changed
    if (item.value == "(Select All)") {
      // get new check state.
      var checked = args.item.checked;
      if (checked) {
        $(this).jqxListBox("checkAll");       
          var listItems = $(this).jqxListBox("getCheckedItems");
          if(selectedFilterItems.length == 0) {
            for(var i=0;i<listItems.length;i++) {
              selectedFilterItems.push(listItems[i].value);
            }
          } else {
            for(var j=0;j<listItems.length;j++) {
              var index = selectedFilterItems.findIndex(function(selectItems, i) {
                return selectItems === listItems[j].value;
              });
              if(index < 0 && listItems[j].value != '(Select All)') {
                selectedFilterItems.push(listItems[j].value);
              }
            } 
          }                
      } else {
        var listItems = $(this).jqxListBox("getCheckedItems");
        for(var j=0;j<listItems.length;j++) {
          var index = selectedFilterItems.findIndex(function(selectItems, i) {
            return selectItems === listItems[j].value;
          });
          if(index >= 0) {
            selectedFilterItems.splice(index,1);
          }
        }
        $(this).jqxListBox("uncheckAll");
      }
    } else {
      var totalItems = $(this).jqxListBox("getItems");
      let selectedItems = $(this).jqxListBox("getCheckedItems");
      if((totalItems.length-1) == selectedItems.length) {
        if(args.item.checked) {
          $(this).jqxListBox("checkAll");
        } else {
          selectedItems[0].checked = false;
        }
      }
      if(selectedFilterItems.length == 0) {
        selectedFilterItems.push(item.value);
      } else {
        var index = selectedFilterItems.findIndex(function(filterItem, i) {
          return filterItem === item.value;
        });
        if(index < 0)
          selectedFilterItems.push(item.value);
        else if(!item.checked)
          selectedFilterItems.splice(index,1);
      }
    }
  });
};

TabularView.prototype.resetColumnFilters = function(
  tabIdentifier,
  columnIdentifier
) {
  delete gbl_tabularview_store[tabIdentifier]["stringFilters"][
    columnIdentifier
  ];
  delete gbl_tabularview_store[tabIdentifier]["numericFilters"][
    columnIdentifier
  ];
  updateGblTabularviewStore(gbl_tabularview_store);
};

TabularView.prototype.getCurrentActiveTab = function() {
  var tabIndex = $("#jqxTabs").jqxTabs("selectedItem");

  if (tabIndex == undefined) {
    tabIndex = this.CUR_TAB.tabPosition;
  }

  return this.getCurrentTabData(tabIndex);
};
var plainTextFilterStatus;
TabularView.prototype.formatRequestPayload = function(data, type, datafield) {
  let mainPayload = gbl_main_request_payload;
  if (type == "filter") {
    mainPayload = gbl_filter_request_payload;
  }

  currentActiveTabKey = this.getCurrentActiveTab();

  this.setFilterParams(currentActiveTabKey, data.filterGroups);

  let payload = {};
  payload.selectedTabIds = mainPayload.selectedTabIds();

  payload.unSelectedTabIds = mainPayload.unSelectedTabIds();

  // don't send selected/unselectedtabs for current selected tab as pagination will not work
  delete payload["selectedTabIds"][
    gbl_tabularview_store[currentActiveTabKey]["currentTabPayloadId"]
  ];
  delete payload["unSelectedTabIds"][
    gbl_tabularview_store[currentActiveTabKey]["currentTabPayloadId"]
  ];

  payload.numericFilters = mainPayload.numericFilters(data.filterGroups);

  payload.stringFilters = mainPayload.stringFilters();

  if (type == "filter") {
    payload.fieldName = datafield;
    payload.fieldType = "String";
    payload.fieldOperator = "";
    payload.fieldValue = this.source.dataFieldQuery || "";
    payload.pageNumber = 0;
    payload.pageSize = 10;
  } else {
    payload.pageRequestInformation = {
      pageNumber: data.pagenum,
      pageSize: data.pagesize,
      sortField:
        data.sortdatafield ||
        gbl_tabularview_store[currentActiveTabKey]["columnUniqueId"],
      sortType: data.sortorder || "DESC"
    };
  }
  if(plainTextFilterStatus) {
    payload.freeTextFilters = {};
    payload.freeTextFilters = plainTextInputValue;
  }

  return JSON.stringify(payload);
};

TabularView.prototype.updateFilterBox = function(
  datafield,
  filterPanel,
  dataFieldQuery,
  freeTextStatus
) {
  let currentActiveTabKey = this.getCurrentActiveTab();
  plainTextFilterStatus = freeTextStatus;
  this.source.url = gbl_tabularview_store[currentActiveTabKey]["filterDataUrl"];
  this.source.datafield = datafield;
  this.source.dataFieldQuery = dataFieldQuery;
  this.CUR_TAB.stringFilters = {};
  this.filterStatus = true;
  var filterBoxAdapter = new $.jqx.dataAdapter(this.source, {
    loadComplete: function() {
      if(freeTextStatus)
        this.bindFilterResultsToFreeTextPopup(         
          datafield,
          filterPanel
        );        
      else
        this.bindFilterResultsToPopup(
          filterBoxAdapter.records,
          datafield,
          filterPanel
        );
    }.bind(this),
    beforeSend: function(jqXHR, settings) {
      jqXHR.setRequestHeader("Content-Type", "application/json");
      jqXHR.setRequestHeader(
        "userSessionId",
        localStorage.getItem("userSessionId")
      );
    }.bind(this),
    // format and sent post params here
    formatData: function(data) {
      return this.formatRequestPayload(data, "filter", this.source.datafield);
    }.bind(this),
    autoBind: false
  });
  filterBoxAdapter.dataBind();
};

TabularView.prototype.buildCheckboxWithInputFilterPanel = function(
  filterPanel,
  datafield,
  freeTextStatus
) {
  // append select box with checkboxes to the custom filter
  if(freeTextStatus) 
    this.updateFilterBox(datafield, filterPanel,undefined,true);
  else 
    this.updateFilterBox(datafield, filterPanel,undefined,false);
};

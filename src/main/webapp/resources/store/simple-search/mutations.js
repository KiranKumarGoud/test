function getTargetAdvanceSearchObject(state, row) {
  let targetAdvSearch = JSON.parse(JSON.stringify(state.targetAdvanceSearch));
  let targetCategoryValues = row.advanceSearch.targetCategory.map(p => p);

  targetAdvSearch.option = row.advanceSearchSelectedValue;
  targetAdvSearch.targetCategory = targetCategoryValues;
  if (row.fileData.length > 0) {
    targetAdvSearch.fileData = row.fileData;
    targetAdvSearch.targetAdvSearchInputDTOList = null;
    return targetAdvSearch;
  }

  targetAdvSearch.targetAdvSearchInputDTOList = row.advanceSearch.autoComplete;

  return targetAdvSearch;
}


function getTreeViewSearchPostData(state, row) {
   // treeview post data payload preparation  
   if(row.treeViewSearch && row.treeViewSearch.currentTreeViewSelected) {
    let payload = {
      "sourceOption": '',
      "simpleSearch": null,
      "advancedSearch": null,
      "treeSearch": {
      },
      "operator": ""
    }
    
    payload.sourceOption =  row.treeViewSearch['apiSourceOption'][row.treeViewSearch.currentTreeViewSelected]
    
    if(row.treeViewSearch.currentTreeViewSelected == 'TargetTreeView') {
      payload.treeSearch = {
        targetTreeviewDTO: {
          targetOntologyId: row.treeViewSearch.selectedNodeIds
        }
      }
    }else if(row.treeViewSearch.currentTreeViewSelected == 'IndicationTreeView') {
      payload.treeSearch = {
        indicationTreeviewDTO: {
          indicationIcd10Code: row.treeViewSearch.selectedNodeIds
        } 
      }
    }else if(row.treeViewSearch.currentTreeViewSelected == 'PharmacokineticsTreeView') {
      payload.treeSearch = {
        admeTreeviewDTO: {
          admeOntologyId: row.treeViewSearch.selectedNodeIds
        } 
      }
    }else if(row.treeViewSearch.currentTreeViewSelected == 'ToxicityTreeView') {
      payload.treeSearch = {
        toxicityTreeviewDTO: {
          toxicityOntologyId: row.treeViewSearch.selectedNodeIds
        }
      }
    }else if(row.treeViewSearch.currentTreeViewSelected == 'TaxonomyTreeView') {
      payload.treeSearch = {
        taxonomyTreeviewDTO: {
          taxId: row.treeViewSearch.selectedNodeIds
        }
      }
    }else if(row.treeViewSearch.currentTreeViewSelected == 'EndpointTreeView') {
      payload.treeSearch = {
        endpointTreeviewDTO: {
          endpointId: row.treeViewSearch.selectedNodeIds
        }
      }
    }

    return payload 
  }
  return null
}
function getBibliographyPostData(state, row, whichSearch = 'simple_search') {
  let bibliographyRowData = {}
    if(whichSearch == 'advance_search') {
      // initial bibliography advance search post object
      bibliographyRowData = {
        "sourceOption": "bibliography",
        "simpleSearch": null,
        advancedSearch: {
          bibliographyAdvSearchDTO: {
            listSearch: {
              "refReadonly": "",
              "refComboFileData": [],
              "refComboData": [],
              "bibliographyPubmedDTOList": null,
              "bibliographyDoiDTOList": null,
              "bibliographyIssnNoDTOList": null,
              "bibliographyMeshDTOList": null
            },
            criterionSearch: {
              "dataSource": [],
              "listAuthorsDTOs": null,
              "listCompaniesDTOs": null,
              "fromYear": null,
              "toYear": null,
              "authorsFileData": null,
              "companiesFileData": null
            },
            customSearch: {
              "listSearch": null,
              "criterionSearch": null,
              "customSearch": {
                "journalData": [{
                  "journalName": "",
                  "year": [],
                  "volume": [],
                  "issue": [],
                  "pageNo": []
                }],
                "patentData": [{
                  "countryCode": [],
                  "year": [],
                  "patentNo": []
                }]
              }
            },
          }
        }
      }

    // bibliography listSearch advance search post data preparation
    if(row.bibliographySearch.currentSelectedTab == 'list') {
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch = null
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.customSearch = null
      if(row.fileData.length == 0) {
        if(row.bibliographySearch.listSearch.advanceSearch.selectedValue == 'pubmed_id') {
          bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.bibliographyPubmedDTOList = row.bibliographySearch.listSearch.advanceSearch.autoCompleteSelections
        }else if(row.bibliographySearch.listSearch.advanceSearch.selectedValue == 'doi') {
          bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.bibliographyDoiDTOList = row.bibliographySearch.listSearch.advanceSearch.autoCompleteSelections
        }else if(row.bibliographySearch.listSearch.advanceSearch.selectedValue == 'issn_no') {
          bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.bibliographyIssnNoDTOList = row.bibliographySearch.listSearch.advanceSearch.autoCompleteSelections
        }else if(row.bibliographySearch.listSearch.advanceSearch.selectedValue == 'mesh') {
          bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.bibliographyMeshDTOList = row.bibliographySearch.listSearch.advanceSearch.autoCompleteSelections
        }
      }

      if(row.bibliographySearch.listSearch.advanceSearch.selectedValue == 'ref_id' ) {
        if(!state.bibliographyListSearchFileUpload) {
          bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.refComboData = row.bibliographySearch.listSearch.advanceSearch.refIds        
        }else {
          bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.refComboFileData = row.bibliographySearch.listSearch.fileData
        }
        bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.refReadonly = row.bibliographySearch.listSearch.advanceSearch.selectedValue
      } else {
        bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.refComboFileData = row.fileData
        bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch.refReadonly = row.bibliographySearch.listSearch.advanceSearch.selectedValue
      }

    }

    // bibliography criterionSearch advance search post data preparation
    if(row.bibliographySearch.currentSelectedTab == 'criterion') {
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch = null
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.customSearch = null
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.dataSource = row.bibliographySearch.criterionSearch.dataSource
      row.bibliographySearch.criterionSearch.data.forEach(criterionItem => {
          if(criterionItem.selectedValue == 'author') {
            if(criterionItem.fileData.length > 0) {
              bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.authorsFileData = criterionItem.fileData
            }else{
              bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.listAuthorsDTOs = criterionItem.autoCompleteSelections.length > 0 ? criterionItem.autoCompleteSelections : null
            }
          }else if(criterionItem.selectedValue == 'company') {
            if(criterionItem.fileData.length > 0) {
              bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.companiesFileData = criterionItem.fileData
            }else{
              bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.listCompaniesDTOs = criterionItem.autoCompleteSelections.length > 0 ? criterionItem.autoCompleteSelections : null 
            }
          }else if(criterionItem.selectedValue == 'year') {
            bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.toYear = criterionItem.toYear
            bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch.fromYear = criterionItem.fromYear
          }
      });
    }

     // bibliography customSearch advance search post data preparation
     if(row.bibliographySearch.currentSelectedTab == 'custom') {
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.listSearch = null
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.criterionSearch = null
      let payload = {
        journalData: [],
        patentData: []
      }
      for(index in row.bibliographySearch.customSearch.totalRowData) {
        if(row.bibliographySearch.customSearch.totalRowData[index]['selectedDataSource'] == 'j') {          
          payload.journalData.push(row.bibliographySearch.customSearch.totalRowData[index])          
        }else{
          payload.patentData.push(row.bibliographySearch.customSearch.totalRowData[index])
        }
      }
      let finalPayload = {}
      if (payload.journalData.length > 0) {
        finalPayload.journalData = '';
        finalPayload.journalData = payload.journalData
      }
      if (payload.patentData.length > 0) {
        finalPayload.patentData = '';
        finalPayload.patentData = payload.patentData
      }
      bibliographyRowData.advancedSearch.bibliographyAdvSearchDTO.customSearch = finalPayload
     }

    state.bibliographyListSearchFileUpload = false;
  } else {
    bibliographyRowData = {
      advancedSearch: null,
      operator: "",
      simpleSearch: {
        option: "bibliography",
        bibliographyReferenceMasterDToList: row.simpleSearchAutoComplete
      },
      sourceOption: "bibliography",
    }
  }
  return bibliographyRowData;
}

function getStructureAdvanceSearchObject(state, row) {
  let strTermAdvSearch = JSON.parse(JSON.stringify(state.termAdvanceSearch));

  if (row.structureAdvanceSearchTab == "term") {
    let strTermAdvSearch = JSON.parse(JSON.stringify(state.termAdvanceSearch));
    strTermAdvSearch.strReadonly = row.advanceSearchSelectedValue;
    if (row.fileData.length > 0) {
      strTermAdvSearch.strComboFileData = row.fileData;
    } else if (
      row.advanceSearch.isAutoComplete &&
      row.advanceSearchSelectedValue == "compound_name"
    ) {
      strTermAdvSearch.compoundSynonymsDTOList =
        row.advanceSearch.structureInputField;
    } else if (
      row.advanceSearch.isAutoComplete &&
      row.advanceSearchSelectedValue == "cas_no"
    ) {
      strTermAdvSearch.listCasNosDTOList =
        row.advanceSearch.structureInputField;
    } else {
      strTermAdvSearch.strComboData = row.advanceSearch.structureInputField;
    }
    return strTermAdvSearch;
  } else if (row.structureAdvanceSearchTab == "chemistry") {
  }
}

function getSourcePostData(state, row, whichSearch = 'simple_search') {
  let sourceRowData = {}
    if(whichSearch == 'advance_search') {
      // initial bibliography advance search post object
      sourceRowData = {
        "sourceOption": "Source",
        "simpleSearch": null,
        advancedSearch: {
          sourceAdvSearchDTO: {
            listSearchDTO: {
              "option": "",
              "sourceSynonymsDTOList": null,              
              "sourceIdList": null,
              "sourceTaxIdsDTOList": null,
              "taxIdList": null              
            },
            strainSearchDTOList:[{
                "genusList": [],
                "speciesList": [],
                "strainList": []              
              }]
            ,
          }
        }
      }

    // source listSearch advance search post data preparation    
    if(row.sourceSearch.currentSelectedTab == 'list') {
      sourceRowData.advancedSearch.sourceAdvSearchDTO.strainSearchDTOList = null         
      if(row.sourceSearch.listSearch.advanceSearch.selectedValue == 'SourceId') {
        sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO.option = row.sourceSearch.listSearch.advanceSearch.selectedValue 
        if (row.fileData.length == 0) {
          sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO.sourceSynonymsDTOList = row.sourceSearch.listSearch.advanceSearch.autoCompleteSelections
        } else {
          sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO.sourceIdList = row.fileData
        }        
      }else if(row.sourceSearch.listSearch.advanceSearch.selectedValue == 'TaxId') {
        sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO.option = row.sourceSearch.listSearch.advanceSearch.selectedValue
        if (row.fileData.length == 0) {
          sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO.sourceTaxIdsDTOList = row.sourceSearch.listSearch.advanceSearch.autoCompleteSelections
        } else {
          sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO.taxIdList = row.fileData
        }        
      } 
    }
     // source strain advance search post data preparation
     if(row.sourceSearch.currentSelectedTab == 'strain') {
      sourceRowData.advancedSearch.sourceAdvSearchDTO.listSearchDTO = null      
      for(index in row.sourceSearch.strainSearch.totalRowData) {     
        let payload = {};
        if (row.sourceSearch.strainSearch.totalRowData[index].genusList.length > 0) {
          payload.genusList = [];         
          payload.genusList.push(row.sourceSearch.strainSearch.totalRowData[index].genusList)
        } 
        if (row.sourceSearch.strainSearch.totalRowData[index].speciesList.length > 0) {
          payload.speciesList = [];         
          payload.speciesList.push(row.sourceSearch.strainSearch.totalRowData[index].speciesList)
        }  
        if (row.sourceSearch.strainSearch.totalRowData[index].strainTypeList.length > 0) {
          payload.strainList = '';
          payload.strainList = row.sourceSearch.strainSearch.totalRowData[index].strainTypeList
        }
        sourceRowData.advancedSearch.sourceAdvSearchDTO.strainSearchDTOList[index] = payload
      }      
     }
    state.sourceListSearchFileUpload = false;
  } else {
    sourceRowData = {
      advancedSearch: null,
      operator: "",
      simpleSearch: {
        option: "Source",
        sourceSynonymsDTOList: row.simpleSearchAutoComplete
      },
      sourceOption: "Source",
    }
  }
  return sourceRowData;
}

function getActivityTypesAdvanceSearchObject(state, row) {
  let activityTypeAdvSearch = JSON.parse(JSON.stringify(state.activityTypesAdvanceSearch));
  let activityTypeRowValues = row.advanceSearch.activityTypeRow.map(p => p);
  activityTypeAdvSearch.option = row.advanceSearchSelectedValue;
  activityTypeAdvSearch.activityTypeRow = activityTypeRowValues;

  activityTypeAdvSearch.activityTypesDTOList = row.advanceSearch.autoComplete;

  return activityTypeAdvSearch;
}

const glb__simpleSearchMutations = {
  SET_SIMPLE_SEARCH_OPTIONS: (state, payload) => {
    state.simpleSearchAutoOptions = payload;
  },
  SET_SEARCH_COUNT: (state, payload) => {
    // console.log(state.simpleSearchRowData.length);
    // console.log(payload);

    let showDefaultCounts = 0;
    for (let id in payload) {
      if (payload[id] == null) {
        showDefaultCounts++;
      }
    }

    if (
      Object.keys(payload).length == showDefaultCounts &&
      state.simpleSearchRowData.length == 1
    ) {
      state.searchCount = state.defatultSearchCount;
    } else {
      state.searchCount = payload;
    }
  },
  RESET_SIMPLE_SERACH_COUNT: state => {
    state.searchCount = state.defatultSearchCount;
  },
  GET_SEARCH_FINAL_PAYLOAD: (state, payload) => {
    let rowData = [];
    rowData = JSON.parse(JSON.stringify(state.simpleSearchRowData));
    console.log('rowData ', rowData)
    let postData = [];
    for (const [ind, row] of rowData.entries()) {
      /*  if(row.simpleSearchSelectedValue == "Target" && (row.simpleSearchAutoComplete.length == 0 &&
        row.advanceSearch.autoComplete.length == 0 && row.fileData.length == 0) ) {
        break ;
      }
      if(row.simpleSearchSelectedValue == "Assay" && row.simpleSearchAutoComplete.length == 0){
        break
      } */
      console.log('row - ', row);
      if (row.simpleSearchSelectedValue) {
        state.deletedRowData = "";
        let resCount = JSON.parse(JSON.stringify(state.resultCount));

        resCount.sourceOption = row.simpleSearchSelectedValue;
        resCount.simpleSearch.option = row.simpleSearchSelectedValue;

        if (row.simpleSearchSelectedValue == "Target") {
          if (
            row.advanceSearch.autoComplete.length == 0 &&
            row.fileData.length == 0
          ) {
            resCount.simpleSearch.targetSynonymsDTOList =
              row.simpleSearchAutoComplete;
          } else if (row.fileData.length != 0) {
            resCount.advancedSearch = {
              targetAdvSearchDTO: getTargetAdvanceSearchObject(state, row)
            };
            resCount.simpleSearch = null;
          } else {
            resCount.advancedSearch = {
              targetAdvSearchDTO: getTargetAdvanceSearchObject(state, row)
            };
            resCount.simpleSearch = null;
          }
        } else if (row.simpleSearchSelectedValue == "Assay") {
          resCount.simpleSearch.ontoAssayTypeDTOList =
            row.simpleSearchAutoComplete;
        } else if (row.simpleSearchSelectedValue == "Structure") {
          resCount.sourceOption = row.simpleSearchSelectedValue;
          resCount.simpleSearch.option = row.simpleSearchSelectedValue;
          if (!row.isStructureAdvanceSearch) {
            resCount.simpleSearch.compoundSynonymsDTOList =
              row.simpleSearchAutoComplete;
          } else {
            resCount.simpleSearch = null;
            if (row.structureAdvanceSearchTab == "term") {
              resCount.advancedSearch = {
                structureAdvSearchDTO: {
                  termSearch: getStructureAdvanceSearchObject(state, row),
                  chemistrySearch: null,
                  propertySearch: null
                }
              };
            } else if (row.structureAdvanceSearchTab == "chemistry") {
              resCount.advancedSearch = {
                structureAdvSearchDTO: {
                  chemistrySearch: row.advanceSearch.chemistrySearch,
                  termSearch: null,
                  propertySearch: null
                }
              };
            } else if (row.structureAdvanceSearchTab == "property") {
              resCount.advancedSearch = {
                structureAdvSearchDTO: {
                  chemistrySearch: null,
                  termSearch: null,
                  propertySearch: row.advanceSearch.propertySearch
                }
              };
            }
          }
        } else if(row.simpleSearchSelectedValue == 'bibliography') {
          // add bibliography advance search object
          if(row.advanceSearchSelectedValue || row.advanceSearchSelectedValueName) {
            resCount = getBibliographyPostData(state,row, 'advance_search')
          }else {
            resCount = getBibliographyPostData(state,row,'simple_search')
          }

        } else if(row.simpleSearchSelectedValue.indexOf('TreeView') > -1) {         
          resCount = getTreeViewSearchPostData(state, row);
        } else if(row.simpleSearchSelectedValue == 'ActivityMechanism') {
          resCount.simpleSearch.activityMechanismDTOList = row.simpleSearchAutoComplete;
          
        } else if(row.simpleSearchSelectedValue == 'CompoundCategories') {
          resCount.simpleSearch.compoundCategoriesDTOList = row.simpleSearchAutoComplete;

        }  else if(row.simpleSearchSelectedValue == 'ClinicalPhases') {
          resCount.simpleSearch.clinicalPhasesDTOList = row.simpleSearchAutoComplete;

        }
        else if(row.simpleSearchSelectedValue == 'CompoundPhases') {
          resCount.simpleSearch.compoundPhasesDTOList = row.simpleSearchAutoComplete;

        } else if(row.simpleSearchSelectedValue == 'ActivityTypes') {
          if(row.advanceSearchSelectedValue) {
            if(row.advanceSearch.autoComplete.length > 0) {
              resCount.simpleSearch = null;
              resCount.advancedSearch = {
                activityTypeAdvSearchDTO: {
                  option: row.advanceSearchSelectedValue,
                }
              }
              resCount.advancedSearch.activityTypeAdvSearchDTO.activityTypeInputDTOList = row.advanceSearch.autoComplete  
            }  
          } else {
            resCount.simpleSearch = {
              option: row.simpleSearchSelectedValue,
              activityTypesDTOList: row.simpleSearchAutoComplete
          }
        }       
        } else if(row.simpleSearchSelectedValue == 'Indication') {          
          if(row.advanceSearchSelectedValue) {
              if(row.advanceSearch.autoComplete.length > 0) {
                resCount.simpleSearch = null;
                resCount.advancedSearch = {
                  indicationAdvSearchDTO: {
                    option: row.advanceSearchSelectedValue,
                    fileData: null
                  }
                }
    
                if(row.advanceSearchSelectedValue == 'ICD-10') {
                  resCount.advancedSearch.indicationAdvSearchDTO.icd10CodesDTOList = row.advanceSearch.autoComplete
                }
                
                if(row.advanceSearchSelectedValue == 'DiseaseName') {
                  resCount.advancedSearch.indicationAdvSearchDTO.therapeuticUseDTOList = row.advanceSearch.autoComplete
                }
              }else if(row.fileData.length > 0) {
                resCount.simpleSearch = null;
                resCount.advancedSearch = {
                  indicationAdvSearchDTO: {
                    option: row.advanceSearchSelectedValue,
                    therapeuticUseDTOList: null,
                    fileData: row.fileData
                  }
                }
              }
          }else{
            resCount.simpleSearch = {
              option: row.simpleSearchSelectedValue,
              therapeuticUseDTOList: row.simpleSearchAutoComplete
            }
          }
        } else if (row.simpleSearchSelectedValue == 'Source') {
           // add source advance search object          
           if(row.advanceSearchSelectedValue || row.advanceSearchSelectedValueName) {
            resCount = getSourcePostData(state,row, 'advance_search')
          }else {
            resCount = getSourcePostData(state,row,'simple_search')
          }
        }
        if (ind + 1 != rowData.length && row.hasValue) {
          resCount.operator = row.operator;
        }
        console.log("Final Object", resCount.sourceOption, row.hasValue);
        if (row.hasValue ||
            (row.simpleSearchSelectedValue == 'bibliography' && row.simpleSearchAutoComplete.length > 0) || 
            (row.simpleSearchSelectedValue == 'Source' && row.simpleSearchAutoComplete.length > 0) || 
            row.simpleSearchSelectedValue.indexOf('TreeView') > -1) {
          postData.push(resCount);
        }
      }
    }
    if (postData.length > 0) {
      postData[postData.length - 1].operator = "";
    }
    console.log('final postdata - ', postData);
    state.searchPostObj = postData;
  },
  RESET_TERM_SEARCH_VALUES: (state, payload) => {
    state.simpleSearchRowData[payload.currIndex]["advanceSearch"] = {
      autoComplete: []
    };
    state.simpleSearchRowData[payload.currIndex][
      "advanceSearchSelectedValue"
    ] = null;
    state.simpleSearchRowData[payload.currIndex]["fileName"] = "";
    state.simpleSearchRowData[payload.currIndex]["fileData"] = [];
    state.simpleSearchRowData[payload.currIndex][
      "isStructureAdvanceSearch"
    ] = false;
  }
};

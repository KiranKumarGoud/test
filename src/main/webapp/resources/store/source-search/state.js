const glb__sourceSearchState = {
   currentSelectedTab: 'list',
   listSearch: {
      advanceSearch: {
         selectedValue: null,
         selectOptions: [
            { text: "Please Select an Option", value: null, disabled: false, show: 'autocomplete' },
            { text: "Species Name", value: "SourceId", disabled: false, show: 'autocomplete' },
            { text: "Taxonomy ID", value: "TaxId", disabled: false, show: 'autocomplete' }
          ],
         autoCompleteSelections: []
      },
      fileData: [],
      fileName: null,
   },
   strainSearch: {
      advanceSearch: {
         selectedValue: null
      },
      initialJournalRowData: {         
         genusList: [],
         speciesList: [],
         strainTypeList: []        
      },
      totalRowData: [
         {
            genusList: [],
            speciesList: [],
            strainTypeList: []
         }
      ]
   }  
};

const glb__initialSourceSearchState = JSON.stringify(glb__sourceSearchState)

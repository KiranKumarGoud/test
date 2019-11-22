const glb__bibliographySearchState = {
   currentSelectedTab: 'list',
   listSearch: {
      advanceSearch: {
         selectedValue: null,
         selectOptions: [
            { text: "Please Select an Option", value: null, disabled: false, show: 'autocomplete' },
            { text: "PUBMED ID", value: "pubmed_id", disabled: false, show: 'autocomplete' },
            { text: "DOI", value: "doi", disabled: false, show: 'autocomplete' },
            { text: "ISSN", value: "issn_no", disabled: false, show: 'autocomplete' },
            { text: "MeSH", value: "mesh", disabled: false, show: 'autocomplete' },
            { text: "Ref ID", value: "ref_id", disabled: false, show: 'freetext' }
          ],
         autoCompleteSelections: [],
         refIds: [],
      },
      fileData: [],
      fileName: null,
   },
   criterionSearch: {
      key: "CRITERION_SEARCH",
      dataSource: ['J','P','O'],
      data: [
         {
            id: 1,
            componentType: "AUTOCOMPLETE",
            label:"Author Name",
            selectedValue: "author",
            autoCompleteSelections: [],
            fileName: "",
            fileData: [],
         },
         {
            id: 2,
            componentType: "AUTOCOMPLETE",
            label:"Company Name",
            selectedValue: "company",
            autoCompleteSelections: [],
            fileName: "",
            fileData: []
         },
         {
            id: 3,
            componentType: "YEARRANGE",
            label:"Year",
            selectedValue: "year",
            autoCompleteSelections: [],
            fileData: [],
            fileName: "",
            toYear: "",
            fromYear: ""
         }
      ]
   },
   customSearch: {
      dataSource: [
         { text: "Journal (J)", value: 'j', disabled: false },
         { text: "Patent (P)", value: "p", disabled: false },
      ],
      initialJournalRowData: {
         selectedDataSource: 'j',
         journalName: null,
         year: [],
         volume: [],
         issue: [],
         pageNo: [],
         totalJournalYearOptionsList: [],
         totalJournalVolumeOptionsList: [],
         totalJournalPageNoOptionsList: [],
         totalJournalIssueOptionsList: []         
      },
      initialPatentRowData: {
         selectedDataSource: 'p',
         countryCode: [],
         year: [],
         patentNo: [],
         totalPatentYearOptionsList: [],
         totalPatentNoOptionsList: []
      },
      totalRowData: [
         {
            selectedDataSource: 'j',
            journalName: null,
            year: [],
            volume: [],
            issue: [],
            pageNo: [],
            totalJournalYearOptionsList: [],
            totalJournalVolumeOptionsList: [],
            totalJournalPageNoOptionsList: [],
            totalJournalIssueOptionsList: [],
            totalPatentYearOptionsList: [],
            totalPatentNoOptionsList: []
         }
      ]
   }
};

const glb__initialBibliographySearchState = JSON.stringify(glb__bibliographySearchState)

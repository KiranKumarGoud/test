const glb__treeViewSearchState = {
   currentTreeViewSelected: null,
   selectedNodeIds: [],
   allSelectedNodes: [],
   userTreeSearchKey: [],
   totalTreeNodes: [],
   apiSourceOption: {
      'TargetTreeView':'TargetTreeView', // done
      'IndicationTreeView':'IndicationTreeView', // done
      'PharmacokineticsTreeView':'AdmeTreeview', // done
      'ToxicityTreeView':'ToxicityTreeview', // done
      'TaxonomyTreeView':'TaxonomyTreeView',
      'PathwaysTreeView':'PathwaysTreeView',
      'ClinicalPhaseTreeView':'ClinicalPhaseTreeView',
      'AssayMethodNameTreeView':'AssayMethodNameTreeView',
      'BibliographyTreeView':'BibliographyTreeView',
      'EndpointTreeView':'EndpointTreeView',
   },
   treeViewList: [
      'TargetTreeView',
      'TaxonomyTreeView',
      'PathwaysTreeView',
      'ClinicalPhaseTreeView',
      'PharmacokineticsTreeView',
      'ToxicityTreeView',
      'AssayMethodNameTreeView',
      'BibliographyTreeView',
      'EndpointTreeView',
      'IndicationTreeView',
   ],
};

const glb__initialTreeViewSearchState = JSON.stringify(glb__treeViewSearchState)

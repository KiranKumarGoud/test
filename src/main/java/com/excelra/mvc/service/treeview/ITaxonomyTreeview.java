package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.TaxonomyTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TaxonomyMasterDTO;
import com.excelra.mvc.model.treeview.TaxonomyTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface ITaxonomyTreeview {

    List<TaxonomyTreeviewDTO> prepareTreeviewForNode(Long taxId, UserJdbc userJdbc);

    List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForTaxName(String taxName, UserJdbc userJdbc);

    List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc);

    List<TaxonomyMasterDTO> fetchTaxonomyTaxNameByContaining(String taxName, UserJdbc userJdbc);

    List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTaxNames(List<String> taxNameList, UserJdbc userJdbc);

}

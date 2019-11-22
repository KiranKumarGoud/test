package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.ToxicityTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.ToxicityOntologyMasterDTO;
import com.excelra.mvc.model.treeview.ToxicityTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface IToxicityTreeview {

    List<ToxicityTreeviewDTO> prepareTreeviewForNode(Long toxicityOntologyId, UserJdbc userJdbc);

    List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForToxicityName(String toxicityName, UserJdbc userJdbc);

    List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc);

    List<ToxicityOntologyMasterDTO> fetchToxicityOntologyToxicityNameByContaining(String toxicityName, UserJdbc userJdbc);

    List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForSelectedToxicityNames(List<String> toxicityNameList, UserJdbc userJdbc);

}

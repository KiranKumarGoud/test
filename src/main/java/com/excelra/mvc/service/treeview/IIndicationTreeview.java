package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.IndicationTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.IndicationOntologyMasterDTO;
import com.excelra.mvc.model.treeview.IndicationTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface IIndicationTreeview {

    List<IndicationTreeviewDTO> prepareTreeviewForNode(String icd10Code, UserJdbc userJdbc);

    List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForTherapeuticUse(String therapeuticUse, UserJdbc userJdbc);

    List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc);

    List<IndicationOntologyMasterDTO> fetchIndicationOntologyTherapeuticUseByContaining(String therapeuticUse, UserJdbc userJdbc);

    List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTherapeuticUses(List<String> therapeuticUseList, UserJdbc userJdbc);
}

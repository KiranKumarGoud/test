package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.TargetTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TargetOntologyMasterDTO;
import com.excelra.mvc.model.treeview.TargetTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface ITargetTreeview {

    List<TargetTreeviewDTO> prepareTreeviewForNode(Long ontologyId, UserJdbc userJdbc);

    List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForTargetName(String targetName, UserJdbc userJdbc);

    List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc);

    List<TargetOntologyMasterDTO> fetchTargetOntologyTargetNameByContaining(String targetName, UserJdbc userJdbc);

    List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTargetNames(List<String> targetNameList, UserJdbc userJdbc);
}

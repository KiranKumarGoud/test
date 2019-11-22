package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.AdmeTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.AdmeOntologyMasterDTO;
import com.excelra.mvc.model.treeview.AdmeTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface IAdmeTreeview {

    List<AdmeTreeviewDTO> prepareTreeviewForNode(Long admeOntologyId, UserJdbc userJdbc);

    List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForAdmeName(String admeName, UserJdbc userJdbc);

    List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc);

    List<AdmeOntologyMasterDTO> fetchAdmeOntologyAdmeNameByContaining(String admeName, UserJdbc userJdbc);

    List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForSelectedAdmeNames(List<String> admeNameList, UserJdbc userJdbc);
}

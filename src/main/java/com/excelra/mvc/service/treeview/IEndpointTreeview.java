package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.EndpointTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.EndpointMasterDTO;
import com.excelra.mvc.model.treeview.EndpointTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface IEndpointTreeview {

    List<EndpointTreeviewDTO> prepareTreeviewForNode(Long endpointId, UserJdbc userJdbc);

    List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForEndpointName(String endpointName, UserJdbc userJdbc);

    List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc);

    List<EndpointMasterDTO> fetchEndpointEndpointNameByContaining(String endpointName, UserJdbc userJdbc);

    List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForSelectedEndpointNames(List<String> endpointNameList, UserJdbc userJdbc);
}

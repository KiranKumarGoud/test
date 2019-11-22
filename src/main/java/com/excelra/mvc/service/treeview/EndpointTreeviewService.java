package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.EndpointTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.EndpointMasterDTO;
import com.excelra.mvc.model.treeview.EndpointTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.treeview.EndpointTreeviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class EndpointTreeviewService implements IEndpointTreeview {

    @Autowired
    private EndpointTreeviewDAO endpointTreeviewDAO;

    /**
     *
     * @param endpointId
     * @param userJdbc
     * @return
     */
    @Override
    public List<EndpointTreeviewDTO> prepareTreeviewForNode(Long endpointId, UserJdbc userJdbc) {
        return endpointTreeviewDAO.prepareTreeviewForNode(endpointId, userJdbc);
    }

    /**
     *
     * @param endpointName
     * @param userJdbc
     * @return
     */
    @Override
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForEndpointName(String endpointName, UserJdbc userJdbc) {
        return endpointTreeviewDAO.prepareTreeviewForEndpointName(endpointName, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {
        return endpointTreeviewDAO.prepareTreeviewForParentLevel(userJdbc);
    }

    /**
     *
     * @param endpointName
     * @param userJdbc
     * @return
     */
    @Override
    public List<EndpointMasterDTO> fetchEndpointEndpointNameByContaining(String endpointName, UserJdbc userJdbc) {
        return endpointTreeviewDAO.fetchEndpointEndpointNameByContaining(endpointName, userJdbc);
    }

    /**
     *
     * @param endpointNameList
     * @param userJdbc
     * @return
     */
    @Override
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForSelectedEndpointNames(List<String> endpointNameList, UserJdbc userJdbc) {
        return endpointTreeviewDAO.prepareTreeviewForSelectedEndpointNames(endpointNameList, userJdbc);
    }
}

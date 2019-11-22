package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.AdmeTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.AdmeOntologyMasterDTO;
import com.excelra.mvc.model.treeview.AdmeTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.treeview.AdmeTreeviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class AdmeTreeviewService implements IAdmeTreeview {

    @Autowired
    private AdmeTreeviewDAO admeTreeviewDAO;

    /**
     *
     * @param admeOntologyId
     * @param userJdbc
     * @return
     */
    @Override
    public List<AdmeTreeviewDTO> prepareTreeviewForNode(Long admeOntologyId, UserJdbc userJdbc) {
        return admeTreeviewDAO.prepareTreeviewForNode(admeOntologyId, userJdbc);
    }

    /**
     *
     * @param admeName
     * @param userJdbc
     * @return
     */
    @Override
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForAdmeName(String admeName, UserJdbc userJdbc) {
        return admeTreeviewDAO.prepareTreeviewForAdmeName(admeName, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {
        return admeTreeviewDAO.prepareTreeviewForParentLevel(userJdbc);
    }

    /**
     *
     * @param admeName
     * @param userJdbc
     * @return
     */
    @Override
    public List<AdmeOntologyMasterDTO> fetchAdmeOntologyAdmeNameByContaining(String admeName, UserJdbc userJdbc) {
        return admeTreeviewDAO.fetchAdmeOntologyAdmeNameByContaining(admeName, userJdbc);
    }

    /**
     *
     * @param admeNameList
     * @param userJdbc
     * @return
     */
    @Override
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForSelectedAdmeNames(List<String> admeNameList, UserJdbc userJdbc) {
        return admeTreeviewDAO.prepareTreeviewForSelectedAdmeNames(admeNameList, userJdbc);
    }
}

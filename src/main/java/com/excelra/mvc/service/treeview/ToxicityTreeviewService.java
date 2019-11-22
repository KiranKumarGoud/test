package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.ToxicityTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.ToxicityOntologyMasterDTO;
import com.excelra.mvc.model.treeview.ToxicityTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.treeview.ToxicityTreeviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class ToxicityTreeviewService implements IToxicityTreeview {

    @Autowired
    private ToxicityTreeviewDAO toxicityTreeviewDAO;

    /**
     *
     * @param toxicityOntologyId
     * @param userJdbc
     * @return
     */
    @Override
    public List<ToxicityTreeviewDTO> prepareTreeviewForNode(Long toxicityOntologyId, UserJdbc userJdbc) {
        return toxicityTreeviewDAO.prepareTreeviewForNode(toxicityOntologyId, userJdbc);
    }

    /**
     *
     * @param toxicityName
     * @param userJdbc
     * @return
     */
    @Override
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForToxicityName(String toxicityName, UserJdbc userJdbc) {
        return toxicityTreeviewDAO.prepareTreeviewForToxicityName(toxicityName, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {
        return toxicityTreeviewDAO.prepareTreeviewForParentLevel(userJdbc);
    }

    /**
     *
     * @param toxicityName
     * @param userJdbc
     * @return
     */
    @Override
    public List<ToxicityOntologyMasterDTO> fetchToxicityOntologyToxicityNameByContaining(String toxicityName, UserJdbc userJdbc) {
        return toxicityTreeviewDAO.fetchToxicityOntologyToxicityNameByContaining(toxicityName, userJdbc);
    }

    /**
     *
     * @param toxicityNameList
     * @param userJdbc
     * @return
     */
    @Override
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForSelectedToxicityNames(List<String> toxicityNameList, UserJdbc userJdbc) {
        return toxicityTreeviewDAO.prepareTreeviewForSelectedToxicityNames(toxicityNameList, userJdbc);
    }

}

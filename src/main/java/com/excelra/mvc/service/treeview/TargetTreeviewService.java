package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.TargetTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TargetOntologyMasterDTO;
import com.excelra.mvc.model.treeview.TargetTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.treeview.TargetTreeviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetTreeviewService implements ITargetTreeview {

    @Autowired
    private TargetTreeviewDAO targetTreeviewDAO;

    /**
     *
     * @param ontologyId
     * @param userJdbc
     * @return
     */
    @Override
    public List<TargetTreeviewDTO> prepareTreeviewForNode(Long ontologyId, UserJdbc userJdbc) {
        return targetTreeviewDAO.prepareTreeviewForNode(ontologyId, userJdbc);
    }

    /**
     *
     * @param targetName
     * @param userJdbc
     * @return
     */
    @Override
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForTargetName(String targetName, UserJdbc userJdbc) {
        return targetTreeviewDAO.prepareTreeviewForTargetName(targetName, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {
        return targetTreeviewDAO.prepareTreeviewForParentLevel(userJdbc);
    }

    /**
     *
     * @param targetName
     * @param userJdbc
     * @return
     */
    @Override
    public List<TargetOntologyMasterDTO> fetchTargetOntologyTargetNameByContaining(String targetName, UserJdbc userJdbc) {
        return targetTreeviewDAO.fetchTargetOntologyTargetNameByContaining(targetName, userJdbc);
    }

    /**
     *
     * @param targetNameList
     * @param userJdbc
     * @return
     */
    @Override
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTargetNames(List<String> targetNameList, UserJdbc userJdbc) {
        return targetTreeviewDAO.prepareTreeviewForSelectedTargetNames(targetNameList, userJdbc);
    }
}

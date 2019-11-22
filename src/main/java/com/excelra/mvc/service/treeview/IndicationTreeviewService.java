package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.IndicationTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.IndicationOntologyMasterDTO;
import com.excelra.mvc.model.treeview.IndicationTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.treeview.IndicationTreeviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class IndicationTreeviewService implements IIndicationTreeview {

    @Autowired
    private IndicationTreeviewDAO indicationTreeviewDAO;

    /**
     *
     * @param icd10Code
     * @param userJdbc
     * @return
     */
    @Override
    public List<IndicationTreeviewDTO> prepareTreeviewForNode(String icd10Code, UserJdbc userJdbc) {
        return indicationTreeviewDAO.prepareTreeviewForNode(icd10Code, userJdbc);
    }

    /**
     *
     * @param therapeuticUse
     * @param userJdbc
     * @return
     */
    @Override
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForTherapeuticUse(String therapeuticUse, UserJdbc userJdbc) {
        return indicationTreeviewDAO.prepareTreeviewForTherapeuticuse(therapeuticUse, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {
        return indicationTreeviewDAO.prepareTreeviewForParentLevel(userJdbc);
    }

    /**
     *
     * @param therapeuticUse
     * @param userJdbc
     * @return
     */
    @Override
    public List<IndicationOntologyMasterDTO> fetchIndicationOntologyTherapeuticUseByContaining(String therapeuticUse, UserJdbc userJdbc) {
        return indicationTreeviewDAO.fetchIndicationOntologyTherapeuticUseByContaining(therapeuticUse, userJdbc);
    }

    /**
     *
     * @param therapeuticUseList
     * @param userJdbc
     * @return
     */
    @Override
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTherapeuticUses(List<String> therapeuticUseList, UserJdbc userJdbc) {
        return indicationTreeviewDAO.prepareTreeviewForSelectedTherapeuticUses(therapeuticUseList, userJdbc);
    }
}

package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.TaxonomyTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TaxonomyMasterDTO;
import com.excelra.mvc.model.treeview.TaxonomyTreeviewDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.treeview.TaxonomyTreeviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class TaxonomyTreeviewService implements ITaxonomyTreeview {

    @Autowired
    private TaxonomyTreeviewDAO taxonomyTreeviewDAO;

    /**
     *
     * @param taxId
     * @param userJdbc
     * @return
     */
    @Override
    public List<TaxonomyTreeviewDTO> prepareTreeviewForNode(Long taxId, UserJdbc userJdbc) {
        return taxonomyTreeviewDAO.prepareTreeviewForNode(taxId, userJdbc);
    }

    /**
     *
     * @param taxName
     * @param userJdbc
     * @return
     */
    @Override
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForTaxName(String taxName, UserJdbc userJdbc) {
        return taxonomyTreeviewDAO.prepareTreeviewForTaxName(taxName, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {
        return taxonomyTreeviewDAO.prepareTreeviewForParentLevel(userJdbc);
    }

    /**
     *
     * @param taxName
     * @param userJdbc
     * @return
     */
    @Override
    public List<TaxonomyMasterDTO> fetchTaxonomyTaxNameByContaining(String taxName, UserJdbc userJdbc) {
        return taxonomyTreeviewDAO.fetchTaxonomyTaxNameByContaining(taxName, userJdbc);
    }

    /**
     *
     * @param taxNameList
     * @param userJdbc
     * @return
     */
    @Override
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTaxNames(List<String> taxNameList, UserJdbc userJdbc) {
        return taxonomyTreeviewDAO.prepareTreeviewForSelectedTaxNames(taxNameList, userJdbc);
    }
}

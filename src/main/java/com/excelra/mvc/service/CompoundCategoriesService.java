package com.excelra.mvc.service;

import com.excelra.mvc.model.ListCompoundCategoriesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.CompoundCategoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class CompoundCategoriesService implements ICompoundCategories {

    @Autowired
    private CompoundCategoriesDAO compoundCategoriesDAO;

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListCompoundCategoriesDTO> fetchCompoundCategoriesList(UserJdbc userJdbc) {
        return compoundCategoriesDAO.fetchCompoundCategoriesList(userJdbc);
    }
}

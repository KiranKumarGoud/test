package com.excelra.mvc.service;

import com.excelra.mvc.model.OntoAssayTypeDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.OntoAssayTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Onto Assay type default service
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class OntoAssayTypeService implements IOntoAssayType {

    @Autowired
    private OntoAssayTypeDAO ontoAssayTypeDAO;

    @Override
    public List<OntoAssayTypeDTO> findByEnddataPointLeaf(boolean enddataPointLeaf, UserJdbc userJdbc) {
        return ontoAssayTypeDAO.findByEnddataPointLeaf(enddataPointLeaf, userJdbc);
    }

    @Override
    public List<OntoAssayTypeDTO> fetchAll(UserJdbc userJdbc) {
        return ontoAssayTypeDAO.fetchAll(userJdbc);
    }

}

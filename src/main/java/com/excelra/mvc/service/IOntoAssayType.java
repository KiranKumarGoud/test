package com.excelra.mvc.service;

import com.excelra.mvc.model.OntoAssayTypeDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * <p>
 *  Onto assay type service
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface IOntoAssayType {

    List<OntoAssayTypeDTO> findByEnddataPointLeaf(boolean enddataPointLeaf, UserJdbc userJdbc);

    List<OntoAssayTypeDTO> fetchAll(UserJdbc userJdbc);

}

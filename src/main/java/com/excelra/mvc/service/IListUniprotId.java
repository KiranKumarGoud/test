package com.excelra.mvc.service;

import com.excelra.mvc.model.ListUniprotIdDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * <p>
 *  List UniprotId service.
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface IListUniprotId {
    List<ListUniprotIdDTO> findByUniprotId(String uniprotId, UserJdbc userJdbc);
}

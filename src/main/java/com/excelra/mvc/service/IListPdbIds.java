package com.excelra.mvc.service;

import com.excelra.mvc.model.ListPdbIdsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * <p>
 *  List PdbIds service
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface IListPdbIds {

    List<ListPdbIdsDTO> findByPdbId(String pdbId, UserJdbc userJdbc);
}

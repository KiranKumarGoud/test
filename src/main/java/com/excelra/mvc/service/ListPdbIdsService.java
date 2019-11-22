package com.excelra.mvc.service;

import com.excelra.mvc.model.ListPdbIdsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ListPdbIdsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  List PdbIds Default service.
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class ListPdbIdsService implements IListPdbIds {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListPdbIdsService.class);

    @Autowired
    private ListPdbIdsDAO listPdbIdsDAO;

    /**
     *
     * @param pdbId
     * @return
     */
    @Override
    public List<ListPdbIdsDTO> findByPdbId(String pdbId, UserJdbc userJdbc) {
        return listPdbIdsDAO.findByPdbId(pdbId.toUpperCase(), userJdbc);
    }
}

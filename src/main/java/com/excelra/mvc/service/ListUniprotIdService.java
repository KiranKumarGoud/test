package com.excelra.mvc.service;

import com.excelra.mvc.model.ListUniprotIdDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ListUniprotIdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  List UniprotId default service.
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class ListUniprotIdService implements IListUniprotId {

    @Autowired
    private ListUniprotIdDAO listUniprotIdDAO;

    /**
     *
     * @param uniprotId
     * @return
     */
    @Override
    public List<ListUniprotIdDTO> findByUniprotId(String uniprotId, UserJdbc userJdbc) {
        return listUniprotIdDAO.findByUniprotId(uniprotId.toUpperCase(), userJdbc);
    }
}

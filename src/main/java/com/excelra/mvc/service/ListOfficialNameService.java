package com.excelra.mvc.service;

import com.excelra.mvc.model.ListOfficialNameDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ListOfficialNameDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  List Official name Default service.
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class ListOfficialNameService implements IListOfficialName {

    @Autowired
    private ListOfficialNameDAO listOfficialNameDAO;

    /**
     *
     * @param officialName
     * @return
     */
    @Override
    public List<ListOfficialNameDTO> findByOfficialName(String officialName, UserJdbc userJdbc) {
        return listOfficialNameDAO.findByOfficialName(officialName.toUpperCase(), userJdbc);
    }
}

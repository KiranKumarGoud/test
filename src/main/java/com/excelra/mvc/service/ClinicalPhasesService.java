package com.excelra.mvc.service;

import com.excelra.mvc.model.ListClinicalPhasesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ClinicalPhasesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class ClinicalPhasesService implements IClinicalPhases {

    @Autowired
    private ClinicalPhasesDAO clinicalPhasesDAO;

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListClinicalPhasesDTO> fetchClinicalPhasesList(UserJdbc userJdbc) {
        return clinicalPhasesDAO.fetchClinicalPhasesList(userJdbc);
    }
}

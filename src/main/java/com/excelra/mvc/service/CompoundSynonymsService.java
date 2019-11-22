package com.excelra.mvc.service;

import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNo;
import com.excelra.mvc.model.StructuralPropertiesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.CompoundSynonymsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class CompoundSynonymsService implements ICompoundSynonyms {

    @Autowired
    private CompoundSynonymsDAO compoundSynonymsDAO;

    /**
     *
     * @param compoundSynonyms
     * @param userJdbc
     * @return
     */
    @Override
    public List<CompoundSynonymsDTO> findByCompoundSynonymsContaining(String compoundSynonyms, UserJdbc userJdbc) {
        return compoundSynonymsDAO.findByCompoundSynonymsContaining(compoundSynonyms, userJdbc);
    }

    /**
     *
     * @param casNo
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListCasNo> fetchByCasNoContains(String casNo, UserJdbc userJdbc) {
        return compoundSynonymsDAO.fetchByCasNoContains(casNo, userJdbc);
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public StructuralPropertiesDTO structuralPropertiesMinMax(UserJdbc userJdbc) {
        return compoundSynonymsDAO.fetchByCasNoContains(userJdbc);
    }
}

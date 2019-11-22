package com.excelra.mvc.service;

import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNo;
import com.excelra.mvc.model.StructuralPropertiesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface ICompoundSynonyms {

    List<CompoundSynonymsDTO> findByCompoundSynonymsContaining(String compoundSynonyms, UserJdbc userJdbc);

    List<ListCasNo> fetchByCasNoContains(String casNo, UserJdbc userJdbc);

    StructuralPropertiesDTO structuralPropertiesMinMax(UserJdbc userJdbc);
}

package com.excelra.mvc.service;

import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.TargetSynonymsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * <p>
 *  Target service
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface ITarget {

    /**
     *
     * @return
     */
    List<TargetSynonymsDTO> fetchAllSynonyms(UserJdbc userJdbc);

    /**
     *
     * @param synonyms
     * @return
     */
    List<TargetSynonymsDTO> fetchBySynonymContains(String synonyms, UserJdbc userJdbc);

    /**
     *
     * @param synonyms
     * @return
     */
    List<TargetSynonymsDTO> fetchBySynonyms(String synonyms, UserJdbc userJdbc);

    /**
     *
     * @param targetSynonymsDTOList
     * @return
     */
    List<Integer> findStdnameIdBySynonymsList(List<TargetSynonymsDTO> targetSynonymsDTOList, UserJdbc userJdbc);

    /**
     *
     * @return
     */
    List<TargetSynonymsDTO> findAll(UserJdbc userJdbc);

    /**
     *
     * @param synonyms
     * @return
     */
    List<TargetSynonymsDTO> findBySynonyms(String synonyms, UserJdbc userJdbc);

    /**
     *
     * @param synonyms
     * @return
     */
    List<TargetSynonymsDTO> findBySynonymsContaining(String synonyms, UserJdbc userJdbc);

    /**
     *
     * @param option
     * @param optionValue
     * @param userJdbcObject
     * @return
     */
    List<TargetAdvSearchInputDTO> fetchTargetByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject);
}

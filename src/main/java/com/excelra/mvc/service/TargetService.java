package com.excelra.mvc.service;

import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.TargetSynonymsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.TargetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  target default service
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class TargetService implements ITarget {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetService.class);

    @Autowired
    private TargetDAO targetDao;

    /**
     *
     * @return
     */
    @Override
    public List<TargetSynonymsDTO> fetchAllSynonyms(UserJdbc userJdbc) {
        if(LOGGER.isDebugEnabled()) LOGGER.debug("TargetService :: Fetch all synonyms");
        return targetDao.findAll(userJdbc);
    }

    /**
     *
     * @param synonyms
     * @return
     */
    @Override
    public List<TargetSynonymsDTO> fetchBySynonymContains(String synonyms, UserJdbc userJdbc) {
        if(LOGGER.isDebugEnabled()) LOGGER.debug("TargetService :: Fetch all synonyms contains {} ", synonyms);
        return targetDao.findBySynonymsContaining(synonyms.toUpperCase(), userJdbc);
    }

    /**
     *
     * @param synonyms
     * @return
     */
    @Override
    public List<TargetSynonymsDTO> fetchBySynonyms(String synonyms, UserJdbc userJdbc) {
        if(LOGGER.isDebugEnabled()) LOGGER.debug("TargetService :: Fetch by synonyms {} ", synonyms);
        return targetDao.findBySynonyms(synonyms.toUpperCase(), userJdbc);
    }

    @Override
    public List<Integer> findStdnameIdBySynonymsList(List<TargetSynonymsDTO> targetSynonymsDTOList, UserJdbc userJdbc) {
        if(LOGGER.isDebugEnabled()) LOGGER.debug("TargetService :: Fetch by synonyms List  {} ", targetSynonymsDTOList);
        return targetDao.findStdnameIdBySynonymsList(targetSynonymsDTOList,userJdbc);
    }

    @Override
    public List<TargetSynonymsDTO> findAll(UserJdbc userJdbc) {
        return targetDao.findAll(userJdbc);
    }

    @Override
    public List<TargetSynonymsDTO> findBySynonyms(String synonyms, UserJdbc userJdbc) {
        return targetDao.findBySynonyms(synonyms, userJdbc);
    }

    @Override
    public List<TargetSynonymsDTO> findBySynonymsContaining(String synonyms, UserJdbc userJdbc) {
        return targetDao.findBySynonymsContaining(synonyms, userJdbc);
    }

    @Override
    public List<TargetAdvSearchInputDTO> fetchTargetByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject) {
        return targetDao.fetchTargetByOptionAndValue(option, optionValue, userJdbcObject);
    }
}

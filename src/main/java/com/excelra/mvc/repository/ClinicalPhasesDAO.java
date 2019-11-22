package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ListClinicalPhasesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ClinicalPhasesDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalPhasesDAO.class);

    @Value("${clinical.phase.list.query}")
    private String clinicalPhaseListQuery;

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<ListClinicalPhasesDTO> fetchClinicalPhasesList(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchClinicalPhasesList - Query - {} ", clinicalPhaseListQuery);

            List<ListClinicalPhasesDTO> listClinicalPhasesDTOS = userJdbc.getJdbcTemplate().query(clinicalPhaseListQuery, new BeanPropertyRowMapper<>(ListClinicalPhasesDTO.class));

            return listClinicalPhasesDTOS;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchClinicalPhasesList service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchClinicalPhasesList service method, error is {} ", e);
        }
    }
}

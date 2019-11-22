package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * This is Indication DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class IndicationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationDAO.class);

    @Value("${indication.search.therapeuic.query}")
    private String indicationSearchTherapeuicQuery;

    @Value("${indication.search.icd10codes.query}")
    private String indicationSearchIcd10CodesQuery;

    /**
     *
     * @param therapeuic
     * @param userJdbc
     * @return
     */
    public List<ListTherapeuticUseDTO> fetchTherapeuicListByContaining(String therapeuic, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchTherapeuicListByContaining - Query - {} ", indicationSearchTherapeuicQuery);

            List<ListTherapeuticUseDTO> listTherapeuticUseDTOList = userJdbc.getJdbcTemplate().query(indicationSearchTherapeuicQuery, new String[]{therapeuic.toLowerCase()}, new BeanPropertyRowMapper<>(ListTherapeuticUseDTO.class));

            List<ListTherapeuticUseDTO> listTherapeuticUseDuplicatesRemoveList = listTherapeuticUseDTOList.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ListTherapeuticUseDTO::getTherapeuticUse))),
                            ArrayList::new));

            return listTherapeuticUseDuplicatesRemoveList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchTherapeuicListByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchTherapeuicListByContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param icd10Code
     * @param userJdbc
     * @return
     */
    public List<ListIcd10CodesDTO> fetchIcd10CodesListByContaining(String icd10Code, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchIcd10CodesListByContaining - Query - {} ", indicationSearchIcd10CodesQuery);

            List<ListIcd10CodesDTO> listIcd10CodesDTOList = userJdbc.getJdbcTemplate().query(indicationSearchIcd10CodesQuery, new String[]{icd10Code.toLowerCase()}, new BeanPropertyRowMapper<>(ListIcd10CodesDTO.class));

            List<ListIcd10CodesDTO> listIcd10CodesDuplicatesRemoveList = listIcd10CodesDTOList.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ListIcd10CodesDTO::getIcd10Code))),
                            ArrayList::new));

            return listIcd10CodesDuplicatesRemoveList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchIcd10CodesListByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchIcd10CodesListByContaining service method, error is {} ", e);
        }

    }

}

package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ListUniprotIdDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ListUniprotIdDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListUniprotIdDAO.class);

    private static final String UNIPROT_QUERY = "select uniprot_id as uniprot_id, uniprot_id as label, uniprot_id as value, '|' as operator from target_search.list_uniprot_id where uniprot_id like ?";

    /**
     *
     * @param uniprotId
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<ListUniprotIdDTO> findByUniprotId(String uniprotId, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByUniprotId - Query - {} ", UNIPROT_QUERY);

            List<ListUniprotIdDTO> uniprotIdList = userJdbc.getJdbcTemplate().query(UNIPROT_QUERY, new String[]{uniprotId.toUpperCase() + "%"}, new BeanPropertyRowMapper<>(ListUniprotIdDTO.class));

            return uniprotIdList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByUniprotId service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByUniprotId service method, error is {} ", e);

        }
    }
}

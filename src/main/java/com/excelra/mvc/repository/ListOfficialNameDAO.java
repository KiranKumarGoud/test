package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ListOfficialNameDTO;
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
public class ListOfficialNameDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListOfficialNameDAO.class);

    private static final String OFFICIAL_NAME_QUERY = "select official_name as official_name, official_name as label, official_name as value, '|' as operator from target_search.list_official_name where official_name like ?";

    /**
     *
     * @param officialName
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<ListOfficialNameDTO> findByOfficialName(String officialName, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByOfficialName - Query - {} ", OFFICIAL_NAME_QUERY);

            List<ListOfficialNameDTO> officialNamesList = userJdbc.getJdbcTemplate().query(OFFICIAL_NAME_QUERY, new String[]{officialName.toUpperCase() + "%"}, new BeanPropertyRowMapper<>(ListOfficialNameDTO.class));

            return officialNamesList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByOfficialName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByOfficialName service method, error is {} ", e);
        }
    }
}

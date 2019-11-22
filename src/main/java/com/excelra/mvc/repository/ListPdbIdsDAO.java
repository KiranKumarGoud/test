package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ListPdbIdsDTO;
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
public class ListPdbIdsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListPdbIdsDAO.class);

    private static final String PDB_QUERY = "select pdb_id as pdb_id, pdb_id as label, pdb_id as value, '|' as operator from target_search.list_pdb_ids where pdb_id like ?";

    /**
     *
     * @param pdbId
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<ListPdbIdsDTO> findByPdbId(String pdbId, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByPdbId - Query - {} ", PDB_QUERY);

            List<ListPdbIdsDTO> listPdbIdsList = userJdbc.getJdbcTemplate().query(PDB_QUERY, new String[]{pdbId.toUpperCase() + "%"}, new BeanPropertyRowMapper<>(ListPdbIdsDTO.class));

            return listPdbIdsList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByPdbId service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByPdbId service method, error is {} ", e);

        }
    }
}

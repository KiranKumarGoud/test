package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.OntoAssayTypeDTO;
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
public class OntoAssayTypeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OntoAssayTypeDAO.class);

    private static final String ONTO_ASSAY_BYLEAF_QUERY = "SELECT assaytype_ont_id,assaytype,parent_ont_id,enddata_point_leaf,ont_level,disply_order,assaytype_desc, assaytype_desc as label, assaytype_desc as value, '|' as operator FROM target_search.onto_assaytype WHERE enddata_point_leaf = ?";

    private static final String ONTO_ASSAY_QUERY = "SELECT assaytype_ont_id,assaytype,parent_ont_id,enddata_point_leaf,ont_level,disply_order,assaytype_desc, assaytype_desc as label, assaytype_desc as value, '|' as operator FROM target_search.onto_assaytype";

    /**
     *
     * @param enddataPointLeaf
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<OntoAssayTypeDTO> findByEnddataPointLeaf(boolean enddataPointLeaf, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByEnddataPointLeaf - Query - {} ", ONTO_ASSAY_BYLEAF_QUERY);

            List<OntoAssayTypeDTO> ontoAssyTypeDtoList = userJdbc.getJdbcTemplate().query(ONTO_ASSAY_BYLEAF_QUERY, new Boolean[]{enddataPointLeaf}, new BeanPropertyRowMapper<>(OntoAssayTypeDTO.class));

            return ontoAssyTypeDtoList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing findByEnddataPointLeaf service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByEnddataPointLeaf service method, error is {} ", e);
        }
    }

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<OntoAssayTypeDTO> fetchAll(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByEnddataPointLeaf - Query - {} ", ONTO_ASSAY_QUERY);

            List<OntoAssayTypeDTO> ontoAssyTypeDtoList = userJdbc.getJdbcTemplate().query(ONTO_ASSAY_QUERY, new BeanPropertyRowMapper<>(OntoAssayTypeDTO.class));

            return ontoAssyTypeDtoList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchAll service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchAll service method, error is {} ", e);
        }
    }
}

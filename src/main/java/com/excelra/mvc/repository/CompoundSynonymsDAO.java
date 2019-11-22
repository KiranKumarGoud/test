package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNo;
import com.excelra.mvc.model.StructuralPropertiesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author venkateswarlu.s
 */
@Repository
public class CompoundSynonymsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompoundSynonymsDAO.class);

    @Value("${structure.compound.search.like.query}")
    private String structureCompoundSearchLikeQuery;

    @Value("${structure.casno.search.like.query}")
    private String casNoSearchLikeQuery;

    @Value("${structural.properties.min.max.query}")
    private String structuralPropertiesMinMaxQuery;


    /**
     *
     * @param compoundSynonyms
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<CompoundSynonymsDTO> findByCompoundSynonymsContaining(String compoundSynonyms, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByCompoundSynonymsContaining - Query - {} ", structureCompoundSearchLikeQuery);

            List<CompoundSynonymsDTO> compoundSynonymsDTOList = userJdbc.getJdbcTemplate().query(structureCompoundSearchLikeQuery, new String[]{compoundSynonyms.toUpperCase(), compoundSynonyms.toUpperCase(), compoundSynonyms.toUpperCase()}, new BeanPropertyRowMapper<>(CompoundSynonymsDTO.class));

            return compoundSynonymsDTOList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByCompoundSynonymsContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByCompoundSynonymsContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param casNo
     * @param userJdbc
     * @return
     */
    public List<ListCasNo> fetchByCasNoContains(String casNo, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchByCasNoContains - Query - {} ", casNoSearchLikeQuery);

            List<ListCasNo> listCasNos = userJdbc.getJdbcTemplate().query(casNoSearchLikeQuery, new String[]{casNo.toUpperCase(), casNo.toUpperCase(), casNo.toUpperCase()}, new BeanPropertyRowMapper<>(ListCasNo.class));

            return listCasNos;
        } catch(Exception e) {

            LOGGER.error(" Error while processing fetchByCasNoContains service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchByCasNoContains service method, error is {} ", e);

        }
    }

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public StructuralPropertiesDTO fetchByCasNoContains(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new StructuralPropertiesDTO();

            LOGGER.info(" :: fetchByCasNoContains - Query - {} ", structuralPropertiesMinMaxQuery);

            List<StructuralPropertiesDTO> structuralPropertiesDTOList = userJdbc.getJdbcTemplate().query(structuralPropertiesMinMaxQuery,
                    new StructuralPropertiesDTODataMapper()
            );

            StructuralPropertiesDTO structuralPropertiesDTO = null;

            if (!structuralPropertiesDTOList.isEmpty()) structuralPropertiesDTO = structuralPropertiesDTOList.get(0);

            return structuralPropertiesDTO;
        } catch(Exception e) {

            LOGGER.error(" Error while processing fetchByCasNoContains service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchByCasNoContains service method, error is {} ", e);

        }

    }


    /**
     *
     */
    public static final class StructuralPropertiesDTODataMapper implements RowMapper<StructuralPropertiesDTO> {
        public StructuralPropertiesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            StructuralPropertiesDTO structuralPropertiesDTO = new StructuralPropertiesDTO();

            structuralPropertiesDTO.setHbacceptormin(getRoundDecimalValue(rs.getBigDecimal("hbacceptormin")));
            structuralPropertiesDTO.setHbacceptormax(getRoundDecimalValue(rs.getBigDecimal("hbacceptormax")));

            structuralPropertiesDTO.setHbdonormin(getRoundDecimalValue(rs.getBigDecimal("hbdonormin")));
            structuralPropertiesDTO.setHbdonormax(getRoundDecimalValue(rs.getBigDecimal("hbdonormax")));

            structuralPropertiesDTO.setLogdmin(getRoundDecimalValue(rs.getBigDecimal("logdmin")));
            structuralPropertiesDTO.setLogdmax(getRoundDecimalValue(rs.getBigDecimal("logdmax")));

            structuralPropertiesDTO.setLogpmin(getRoundDecimalValue(rs.getBigDecimal("logpmin")));
            structuralPropertiesDTO.setLogpmax(getRoundDecimalValue(rs.getBigDecimal("logpmax")));

            structuralPropertiesDTO.setMolmin(getRoundDecimalValue(rs.getBigDecimal("molmin")));
            structuralPropertiesDTO.setMolmax(getRoundDecimalValue(rs.getBigDecimal("molmax")));

            structuralPropertiesDTO.setPkamin(getRoundDecimalValue(rs.getBigDecimal("pkamin")));
            structuralPropertiesDTO.setPkamax(getRoundDecimalValue(rs.getBigDecimal("pkamax")));

            structuralPropertiesDTO.setSolubilitymin(getRoundDecimalValue(rs.getBigDecimal("solubilitymin")));
            structuralPropertiesDTO.setSolubilitymax(getRoundDecimalValue(rs.getBigDecimal("solubilitymax")));

            structuralPropertiesDTO.setRotbondsmin(getRoundDecimalValue(rs.getBigDecimal("rotbondsmin")));
            structuralPropertiesDTO.setRotbondsmax(getRoundDecimalValue(rs.getBigDecimal("rotbondsmax")));

            structuralPropertiesDTO.setPsamin(getRoundDecimalValue(rs.getBigDecimal("psamin")));
            structuralPropertiesDTO.setPsamax(getRoundDecimalValue(rs.getBigDecimal("psamax")));

            structuralPropertiesDTO.setPkbmin(getRoundDecimalValue(rs.getBigDecimal("pkbmin")));
            structuralPropertiesDTO.setPkbmax(getRoundDecimalValue(rs.getBigDecimal("pkbmax")));


            return structuralPropertiesDTO;
        }

        public BigDecimal getRoundDecimalValue(BigDecimal inputVal) {

            if(Objects.nonNull(inputVal)) {
                return inputVal.setScale(2, RoundingMode.HALF_UP);
            } else {
                return null;
            }

        }
    }
}

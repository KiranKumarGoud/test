package com.excelra.mvc.repository;

import com.excelra.mvc.entity.TargetProteinMaster;
import com.excelra.mvc.entity.TargetProteinPdbIds;
import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.PdbIdsDTO;
import com.excelra.mvc.model.TargetProteinMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.TargetProteinMasterUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Repository
public class TargetProteinMasterDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetProteinMasterDAO.class);

    @Value("${targetprotein.commonname.query}")
    private String commonnameQuery;

    /**
     *
     * @param commonName
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    //@Transactional
    public List<TargetProteinMasterDTO> findByCommonName(String commonName, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByCommonName - Query - {} ", commonnameQuery);

            List<TargetProteinMasterDTO> targetSynonymsDtoList = userJdbc.getJdbcTemplate().query(commonnameQuery, new String[]{commonName.toUpperCase(), commonName.toUpperCase(), commonName.toUpperCase()}, new BeanPropertyRowMapper<>(TargetProteinMasterDTO.class));

            return targetSynonymsDtoList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing findByCommonName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByCommonName service method, error is {} ", e);
        }

    }

}

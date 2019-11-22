package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.TargetSynonymsDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.TargetSynonymsUtility;
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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


@Repository
public class TargetDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetDAO.class);

    @Autowired
    private TargetSynonymsUtility targetSynonymsUtility;

    @Value("${target.synonyms.findall.query}")
    private String targetSynonymsFindAllQuery;

    @Value("${target.synonyms.findlike.query}")
    private String targetSynonymsLikeQuery;

    @Value("${target.synonysm.stdnameid.query}")
    private String targetSynonymsStdNameIdQuery;

    @Value("${target.advsearch.locus.query}")
    private String targetAdvSearchLocusQuery;

    @Value("${target.advsearch.officialname.query}")
    private String targetAdvSearchOfficialNameQuery;

    @Value("${target.advsearch.uniprot.query}")
    private String targetAdvSearchUniprotQuery;

    @Value("${target.advsearch.pdbids.query}")
    private String targetAdvSearchPdbIdsQuery;

    @Value("${target.advsearch.commonname.query}")
    private String targetAdvSearchCommonNameQuery;

    private static final String LOCUS_OPTION = "enterez";

    private static final String UNIPROT_OPTION = "upprot";

    private static final String PDB_OPTION = "pdb";

    private static final String OFFICALNAME_OPTION = "office";

    private static final String COMMON_OPTION = "common";

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<TargetSynonymsDTO> findAll(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findAll - Query - {} ", targetSynonymsFindAllQuery);

            List<TargetSynonymsDTO> targetSynonymsDtoList = userJdbc.getJdbcTemplate().query(targetSynonymsFindAllQuery, new BeanPropertyRowMapper<>(TargetSynonymsDTO.class));

            return targetSynonymsDtoList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing findAll service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findAll service method, error is {} ", e);
        }
    }

    /**
     *
     * @param synonyms
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<TargetSynonymsDTO> findBySynonymsContaining(String synonyms, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findBySynonymsContaining - Query - {} ", targetSynonymsLikeQuery);

            List<TargetSynonymsDTO> targetSynonymsDtoList = userJdbc.getJdbcTemplate().query(targetSynonymsLikeQuery, new String[]{synonyms.toUpperCase(), synonyms.toUpperCase(), synonyms.toUpperCase(), synonyms.toUpperCase(), synonyms.toUpperCase()}, new BeanPropertyRowMapper<>(TargetSynonymsDTO.class));

            List<TargetSynonymsDTO> targetSynonymsDuplicatesRemoveList = targetSynonymsDtoList.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(TargetSynonymsDTO::getSynonyms))),
                            ArrayList::new));

            return targetSynonymsDuplicatesRemoveList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing findBySynonymsContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findBySynonymsContaining service method, error is {} ", e);
        }
    }

    /**
     *
     * @param synonyms
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<TargetSynonymsDTO> findBySynonyms(String synonyms, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findBySynonyms - Query - {} ", targetSynonymsLikeQuery);

            List<TargetSynonymsDTO> targetSynonymsDtoList = userJdbc.getJdbcTemplate().query(targetSynonymsLikeQuery, new String[]{synonyms.toUpperCase(), synonyms.toUpperCase(), synonyms.toUpperCase(), synonyms.toUpperCase(), synonyms.toUpperCase()}, new BeanPropertyRowMapper<>(TargetSynonymsDTO.class));

            return targetSynonymsUtility.removeDuplicates(targetSynonymsDtoList);
        } catch(Exception e) {
            LOGGER.error(" Error while processing findBySynonyms service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findBySynonyms service method, error is {} ", e);
        }
    }

    /**
     *
     * @param targetSynonymsDTOList
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<Integer> findStdnameIdBySynonymsList(List<TargetSynonymsDTO> targetSynonymsDTOList, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<String> strContains = new ArrayList<>();
            for (TargetSynonymsDTO targetSynonymsDTO : targetSynonymsDTOList) {
                strContains.add(targetSynonymsDTO.getSynonyms());
            }

            LOGGER.info(" :: findStdnameIdBySynonymsList - Query - {} ", targetSynonymsStdNameIdQuery);

            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(userJdbc.getJdbcTemplate());
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("synonymsList", strContains);
            List<Integer> finalStdnameIdList = namedParameterJdbcTemplate.query(targetSynonymsStdNameIdQuery,
                    parameters,
                    new IntegerDataMapper()
            );

            return finalStdnameIdList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing findStdnameIdBySynonymsList service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findStdnameIdBySynonymsList service method, error is {} ", e);
        }
    }

    /**
     *
     * @param option
     * @param optionValue
     * @param userJdbcObject
     * @return
     * @throws GoStarSqlException
     */
    public List<TargetAdvSearchInputDTO> fetchTargetByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbcObject)) return new ArrayList<>();

            List<TargetAdvSearchInputDTO> targetAdvSearchInputDTOList = null;

            if (option.equalsIgnoreCase(LOCUS_OPTION)) {

                LOGGER.info(" :: fetchTargetByOptionAndValue - LOCUS_OPTION Query - {} ", targetAdvSearchLocusQuery);

                targetAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(targetAdvSearchLocusQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new TargetAdvSearchInputDTODataMapper()
                );

            } else if (option.equalsIgnoreCase(UNIPROT_OPTION)) {

                LOGGER.info(" :: fetchTargetByOptionAndValue - UNIPROT_OPTION Query - {} ", targetAdvSearchUniprotQuery);

                targetAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(targetAdvSearchUniprotQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new TargetAdvSearchInputDTODataMapper()
                );

            } else if (option.equalsIgnoreCase(PDB_OPTION)) {

                LOGGER.info(" :: fetchTargetByOptionAndValue - PDB_OPTION Query - {} ", targetAdvSearchPdbIdsQuery);

                targetAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(targetAdvSearchPdbIdsQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new TargetAdvSearchInputDTODataMapper()
                );

            } else if (option.equalsIgnoreCase(OFFICALNAME_OPTION)) {

                LOGGER.info(" :: fetchTargetByOptionAndValue - OFFICALNAME_OPTION Query - {} ", targetAdvSearchOfficialNameQuery);

                targetAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(targetAdvSearchOfficialNameQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new TargetAdvSearchInputDTODataMapper()
                );

            } else if (option.equalsIgnoreCase(COMMON_OPTION)) {

                LOGGER.info(" :: fetchTargetByOptionAndValue - COMMON_OPTION Query - {} ", targetAdvSearchCommonNameQuery);

                targetAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(targetAdvSearchCommonNameQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new TargetAdvSearchInputDTODataMapper()
                );
            }

            return targetAdvSearchInputDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchTargetByOptionAndValue service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchTargetByOptionAndValue service method, error is {} ", e);
        }
    }

    /**
     *
     */
    private static final class IntegerDataMapper implements RowMapper<Integer> {
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("stdname_id");
        }
    }

    /**
     *
     */
    public static final class TargetAdvSearchInputDTODataMapper implements RowMapper<TargetAdvSearchInputDTO> {
        public TargetAdvSearchInputDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            TargetAdvSearchInputDTO targetAdvSearchInputDTO = new TargetAdvSearchInputDTO();

            if(doesColumnExist("pdb_id", rs)) targetAdvSearchInputDTO.setPdbId(rs.getString("pdb_id"));
            if(doesColumnExist("locus_id", rs)) targetAdvSearchInputDTO.setLocusId(rs.getString("locus_id"));
            if(doesColumnExist("uniprot_id", rs)) targetAdvSearchInputDTO.setUniprotId(rs.getString("uniprot_id"));
            if(doesColumnExist("official_name", rs)) targetAdvSearchInputDTO.setOfficalName(rs.getString("official_name"));

            targetAdvSearchInputDTO.setCommonName(rs.getString("common_name"));
            targetAdvSearchInputDTO.setStdnameId(rs.getBigDecimal("stdname_id"));
            targetAdvSearchInputDTO.setLabel(rs.getString("label"));
            targetAdvSearchInputDTO.setValue(rs.getString("value"));
            targetAdvSearchInputDTO.setOperator(rs.getString("operator"));

            return targetAdvSearchInputDTO;
        }

        public static boolean doesColumnExist(String columnName, ResultSet rs) throws SQLException{
            ResultSetMetaData meta = rs.getMetaData();
            int numCol = meta.getColumnCount();
            for (int i = 1; i <= numCol; i++) {
                if(meta.getColumnName(i).equalsIgnoreCase(columnName)) {
                    return true;

                }

            }
            return false;
        }
    }
}

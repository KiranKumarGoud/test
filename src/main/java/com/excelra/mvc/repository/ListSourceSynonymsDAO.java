package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.excelra.mvc.model.source.ListSourceTaxIdsDTO;
import com.excelra.mvc.model.source.SourceClassificationMasterDTO;
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

@Repository
public class ListSourceSynonymsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListSourceSynonymsDAO.class);

    @Value("${source.bysourcename.contains.query}")
    private String bySourcenameContainsQuery;

    @Value("${source.bysynonym.contains.query}")
    private String bySynonymContainsQuery;

    @Value("${source.bytaxid.contains.query}")
    private String byTaxIdsContainsQuery;

    @Value("${source.strainsearch.genus.contains.query}")
    private String strainSearchGenusContainsQuery;

    @Value("${source.strainsearch.genus.default.query}")
    private String strainSearchGenusDefaultQuery;

    @Value("${source.strainsearch.species.contains.query}")
    private String strainSearchSpeciesContainsQuery;

    @Value("${source.strainsearch.species.default.query}")
    private String strainSearchSpeciesDefaultQuery;

    @Value("${source.strainsearch.strain.contains.query}")
    private String strainSearchStrainContainsQuery;

    @Value("${source.strainsearch.strain.default.query}")
    private String strainSearchStrainDefaultQuery;

    /**
     *
     * @param sourceName
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<ListSourceSynonymsDTO> findBySourcenameContaining(String sourceName, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findBySourcenameContaining - Query - {} ", bySourcenameContainsQuery);

            List<ListSourceSynonymsDTO> listSourceSynonymsDTOS = userJdbc.getJdbcTemplate().query(bySourcenameContainsQuery, new String[]{sourceName.toLowerCase()}, new BeanPropertyRowMapper<>(ListSourceSynonymsDTO.class));

            return removeDuplicates(listSourceSynonymsDTOS);
        } catch(Exception e) {

            LOGGER.error(" Error while processing findBySourcenameContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findBySourcenameContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param synonyms
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<ListSourceSynonymsDTO> findBySynonymContaining(String synonyms, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findBySynonymContaining - Query - {} ", bySynonymContainsQuery);

            List<ListSourceSynonymsDTO> listSourceSynonymsDTOS = userJdbc.getJdbcTemplate().query(bySynonymContainsQuery, new String[]{synonyms.toLowerCase()}, new BeanPropertyRowMapper<>(ListSourceSynonymsDTO.class));

            return removeDuplicates(listSourceSynonymsDTOS);
        } catch(Exception e) {

            LOGGER.error(" Error while processing findBySynonymContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findBySynonymContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param taxId
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<ListSourceTaxIdsDTO> findByTaxIdContaining(String taxId, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByTaxIdContaining - Query - {} ", byTaxIdsContainsQuery);

            List<ListSourceTaxIdsDTO> listSourceSynonymsDTOS = userJdbc.getJdbcTemplate().query(byTaxIdsContainsQuery, new String[]{taxId}, new BeanPropertyRowMapper<>(ListSourceTaxIdsDTO.class));

            return listSourceSynonymsDTOS;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByTaxIdContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByTaxIdContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param genus
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<SourceClassificationMasterDTO> findByGenusContaining(String genus, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = new ArrayList<>();

            if(!genus.equalsIgnoreCase("null")) {


                LOGGER.info(" :: findByGenusContaining - Query - {} ", strainSearchGenusContainsQuery);
                sourceClassificationMasterDTOList = userJdbc.getJdbcTemplate().query(strainSearchGenusContainsQuery, new String[]{genus.toLowerCase()}, new BeanPropertyRowMapper<>(SourceClassificationMasterDTO.class));

            } else {

                LOGGER.info(" :: findByGenusContaining - Query - {} ", strainSearchGenusDefaultQuery);
                sourceClassificationMasterDTOList = userJdbc.getJdbcTemplate().query(strainSearchGenusDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(SourceClassificationMasterDTO.class));

            }

            return sourceClassificationMasterDTOList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByGenusContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByGenusContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param genus
     * @param species
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<SourceClassificationMasterDTO> findBySpeciesContaining(String genus, String species, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = new ArrayList<>();

            if(!species.equalsIgnoreCase("null")) {

                LOGGER.info(" :: findBySpeciesContaining - Query - {} ", strainSearchSpeciesContainsQuery);
                sourceClassificationMasterDTOList = userJdbc.getJdbcTemplate().query(strainSearchSpeciesContainsQuery, new String[]{genus, species.toLowerCase()}, new BeanPropertyRowMapper<>(SourceClassificationMasterDTO.class));

            } else {

                LOGGER.info(" :: findBySpeciesContaining - Query - {} ", strainSearchSpeciesDefaultQuery);
                sourceClassificationMasterDTOList = userJdbc.getJdbcTemplate().query(strainSearchSpeciesDefaultQuery, new String[]{genus}, new BeanPropertyRowMapper<>(SourceClassificationMasterDTO.class));

            }

            return sourceClassificationMasterDTOList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findBySpeciesContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findBySpeciesContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param genus
     * @param species
     * @param strain
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<SourceClassificationMasterDTO> findByStrainContaining(String genus, String species, String strain, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = new ArrayList<>();

            if(!strain.equalsIgnoreCase("null")) {

                LOGGER.info(" :: findByStrainContaining - Query - {} ", strainSearchStrainContainsQuery);
                sourceClassificationMasterDTOList = userJdbc.getJdbcTemplate().query(strainSearchStrainContainsQuery, new String[]{genus, species, strain.toLowerCase()}, new BeanPropertyRowMapper<>(SourceClassificationMasterDTO.class));

            } else {

                LOGGER.info(" :: findByStrainContaining - Query - {} ", strainSearchStrainDefaultQuery);
                sourceClassificationMasterDTOList = userJdbc.getJdbcTemplate().query(strainSearchStrainDefaultQuery, new String[]{genus, species}, new BeanPropertyRowMapper<>(SourceClassificationMasterDTO.class));

            }

            return sourceClassificationMasterDTOList;
        } catch(Exception e) {

            LOGGER.error(" Error while processing findByStrainContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByStrainContaining service method, error is {} ", e);

        }
    }

    /**
     *
     * @param listSourceSynonymsDTOS
     * @return
     */
    public List<ListSourceSynonymsDTO> removeDuplicates(List<ListSourceSynonymsDTO> listSourceSynonymsDTOS) {

        List<ListSourceSynonymsDTO> sourceSynonymsDuplicatesRemoveList = listSourceSynonymsDTOS.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ListSourceSynonymsDTO::getLabel))),
                        ArrayList::new));

        return sourceSynonymsDuplicatesRemoveList;

    }
}

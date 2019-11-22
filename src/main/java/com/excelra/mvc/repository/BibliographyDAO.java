package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class BibliographyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BibliographyDAO.class);

    @Value("${bibliography.reference.search.like.query}")
    private String referenceNameSearchLikeQuery;

    @Value("${bibliography.pubmed.search.like.query}")
    private String bibliographyPumbedSearchLikeQuery;

    @Value("${bibliography.doi.search.like.query}")
    private String bibliographyDoiSearchLikeQuery;

    @Value("${bibliography.issnno.search.like.query}")
    private String bibliographyIssnNoSearchLikeQuery;

    @Value("${bibliography.mesh.search.like.query}")
    private String bibliographyMeshSearchLikeQuery;

    @Value("${bibliography.author.search.like.query}")
    private String bibliographyAuthorSearchLikeQuery;

    @Value("${bibliography.company.search.like.query}")
    private String bibliographyCompanySearchLikeQuery;

    @Value("${bibliography.journalname.search.query}")
    private String bibliographyJournalNamesQuery;

    @Value("${bibliography.custom.search.country.codes}")
    private String bibliographyCountryCodes;

    private static final String PUBMEDID_OPTION = "pubmedId";

    private static final String DOI_OPTION = "doi";

    private static final String ISSN_NO_OPTION = "issnNo";

    private static final String MESH_OPTION = "mesh";

    private static final String REF_OPTION = "refId";

    private static final String AUTHOR_OPTION = "author";

    private static final String COMPANY_OPTION = "company";


    /**
     * Reference name Simple search service for auto complete.
     *
     * @param referenceName
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<ReferenceMasterDTO> fetchByReferenceNameContains(String referenceName, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchByReferenceNameContains - Query - {} ", referenceNameSearchLikeQuery);

            List<ReferenceMasterDTO> targetSynonymsDtoList = userJdbc.getJdbcTemplate().query(referenceNameSearchLikeQuery, new String[]{referenceName.toLowerCase(), referenceName.toLowerCase(), referenceName.toLowerCase()}, new BeanPropertyRowMapper<>(ReferenceMasterDTO.class));

            List<ReferenceMasterDTO> referenceNameDuplicatesRemoveList = targetSynonymsDtoList.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ReferenceMasterDTO::getReferenceName))),
                            ArrayList::new));

            return referenceNameDuplicatesRemoveList;
        } catch (Exception e) {
            LOGGER.error(" Error while processing fetchByReferenceNameContains service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchByReferenceNameContains service method, error is {} ", e);
        }
    }


    /****
     *
     * @param pubmedId
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public List<BibliographyPubmedDTO> findByPubmedIdContaining(String pubmedId, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByPubmedIdContaining - Query - {} ", bibliographyPumbedSearchLikeQuery);

            List<BibliographyPubmedDTO> bibliographyPubmedDTOList = userJdbc.getJdbcTemplate().query(bibliographyPumbedSearchLikeQuery, new String[]{pubmedId.toUpperCase(), pubmedId.toUpperCase(), pubmedId.toUpperCase()}, new BeanPropertyRowMapper<>(BibliographyPubmedDTO.class));

            return bibliographyPubmedDTOList;
        } catch (Exception e) {

            LOGGER.error(" Error while processing findByPubmedIdContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByPubmedIdContaining service method, error is {} ", e);

        }
    }


    /***
     *
     * @param doi
     * @param userJdbc
     * @return
     */
    public List<BibliographyDoiDTO> findByDoiRefContaining(String doi, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByDoiContaining - Query - {} ", bibliographyDoiSearchLikeQuery);

            List<BibliographyDoiDTO> bibliographyDoiDTOList = userJdbc.getJdbcTemplate().query(bibliographyDoiSearchLikeQuery, new String[]{doi.toUpperCase(), doi.toUpperCase(), doi.toUpperCase()}, new BeanPropertyRowMapper<>(BibliographyDoiDTO.class));

            return bibliographyDoiDTOList;
        } catch (Exception e) {

            LOGGER.error(" Error while processing findByDoiContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByDoiContaining service method, error is {} ", e);

        }

    }

    /****
     *
     * @param issnNo
     * @param userJdbc
     * @return
     */
    public List<BibliographyIssnNoDTO> findByRefIssnNoContaining(String issnNo, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByRefIssnNoContaining - Query - {} ", bibliographyIssnNoSearchLikeQuery);

            List<BibliographyIssnNoDTO> bibliographyIssnNoDTOList = userJdbc.getJdbcTemplate().query(bibliographyIssnNoSearchLikeQuery, new String[]{issnNo.toUpperCase(), issnNo.toUpperCase(), issnNo.toUpperCase()}, new BeanPropertyRowMapper<>(BibliographyIssnNoDTO.class));

            return bibliographyIssnNoDTOList;
        } catch (Exception e) {

            LOGGER.error(" Error while processing findByRefIssnNoContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByRefIssnNoContaining service method, error is {} ", e);

        }

    }

    /****
     *
     * @param mesh
     * @param userJdbc
     * @return
     */
    public List<BibliographyMeshDTO> findByRefPubmedMeshContaining(String mesh, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByRefMeshContaining - Query - {} ", bibliographyMeshSearchLikeQuery);

            List<BibliographyMeshDTO> bibliographyMeshDTOSList = userJdbc.getJdbcTemplate().query(bibliographyMeshSearchLikeQuery, new String[]{mesh.toUpperCase(), mesh.toUpperCase(), mesh.toUpperCase()}, new BeanPropertyRowMapper<>(BibliographyMeshDTO.class));

            return bibliographyMeshDTOSList;
        } catch (Exception e) {

            LOGGER.error(" Error while processing findByRefMeshContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByRefMeshContaining service method, error is {} ", e);

        }

    }

    /****
     *<p>convereted to the single object mapped for ListSearch as discussed</p>
     * @param option
     * @param optionValue
     * @param userJdbcObject
     * @return
     * @throws GoStarSqlException
     */
    public List<BibliographyAdvSearchInputDTO> fetchBibliographyByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbcObject)) return new ArrayList<>();

            List<BibliographyAdvSearchInputDTO> bibliographyAdvSearchInputDTOList = null;

            if (option.equalsIgnoreCase(PUBMEDID_OPTION)) {

                LOGGER.info(" :: fetchBibliographyByOptionAndValue - PUBMED_ID Query - {} ", bibliographyPumbedSearchLikeQuery);

                bibliographyAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(bibliographyPumbedSearchLikeQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new BibliographyAdvSearchInputDTODataMapper()
                );

            } else if (option.equalsIgnoreCase(DOI_OPTION)) {
                LOGGER.info(" :: fetchBibliographyByOptionAndValue - DOI Query - {} ", bibliographyDoiSearchLikeQuery);

                bibliographyAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(bibliographyDoiSearchLikeQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new BibliographyAdvSearchInputDTODataMapper()
                );
            } else if (option.equalsIgnoreCase(ISSN_NO_OPTION)) {
                LOGGER.info(" :: fetchBibliographyByOptionAndValue - ISSN_NO - {} ", bibliographyIssnNoSearchLikeQuery);

                bibliographyAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(bibliographyIssnNoSearchLikeQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new BibliographyAdvSearchInputDTODataMapper()
                );
            } else if (option.equalsIgnoreCase(MESH_OPTION)) {
                LOGGER.info(" :: fetchBibliographyByOptionAndValue - MESH_OPTION Query - {} ", bibliographyMeshSearchLikeQuery);

                bibliographyAdvSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(bibliographyMeshSearchLikeQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new BibliographyAdvSearchInputDTODataMapper()
                );
            }
            return bibliographyAdvSearchInputDTOList;
        } catch (Exception e) {
            LOGGER.error(" Error while processing fetchBibliographyByOptionAndValue service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchBibliographyByOptionAndValue service method, error is {} ", e);
        }
    }

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<ListJournalNamesDTO> fetchJournalNames(UserJdbc userJdbc) {
        try {
            List<ListJournalNamesDTO> listJournalNamesDTOs = userJdbc.getJdbcTemplate().query(bibliographyJournalNamesQuery, new BeanPropertyRowMapper<>(ListJournalNamesDTO.class));
            return listJournalNamesDTOs;
        } catch (Exception e) {
            LOGGER.error(" Error while processing fetchJournalNames service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchJournalNames service method, error is {} ", e);
        }
    }

    /**
     * <p> methdod is for criterian search </p>
     *
     * @param option
     * @param optionValue
     * @param userJdbcObject
     * @return
     * @throws GoStarSqlException
     */
    public List<BibliographyCriterianSearchInputDTO> fetchBibliographyCriterianSearchAdvByOptionAndValue(String option, String optionValue, UserJdbc userJdbcObject) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbcObject)) return new ArrayList<>();

            List<BibliographyCriterianSearchInputDTO> bibliographyCriterianSearchInputDTOList = null;

            if (option.equalsIgnoreCase(AUTHOR_OPTION)) {
                LOGGER.info(" :: fetchBibliographyCriterianSearchAdvByOptionAndValue - AUTHOR_OPTION Query - {} ", bibliographyAuthorSearchLikeQuery);

                bibliographyCriterianSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(bibliographyAuthorSearchLikeQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new BibliographyCriterianSearchInputDTODataMapper()
                );

            } else if (option.equalsIgnoreCase(COMPANY_OPTION)) {
                LOGGER.info(" :: fetchBibliographyCriterianSearchAdvByOptionAndValue - COMPANY_OPTION Query - {} ", bibliographyCompanySearchLikeQuery);

                bibliographyCriterianSearchInputDTOList = userJdbcObject.getJdbcTemplate().query(bibliographyCompanySearchLikeQuery,
                        new Object[]{optionValue.toUpperCase(), optionValue.toUpperCase(), optionValue.toUpperCase()},
                        new BibliographyCriterianSearchInputDTODataMapper()
                );
            }
            return bibliographyCriterianSearchInputDTOList;
        } catch (Exception e) {
            LOGGER.error(" Error while processing fetchBibliographyCriterianSearchAdvByOptionAndValue service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchBibliographyCriterianSearchAdvByOptionAndValue service method, error is {} ", e);

        }
    }

    /**
     *
     * @return
     * @throws GoStarSqlException
     */
    public List<JournalLabelValueDTO> fetchCountryCodes() throws GoStarSqlException {

        try {

            List<JournalLabelValueDTO> journalLabelValueDTOs = new ArrayList<>();

            String[] countryCodes = bibliographyCountryCodes.split(",");
            for(String countryCode : countryCodes) {
                JournalLabelValueDTO journalLabelValueDTO = new JournalLabelValueDTO();
                journalLabelValueDTO.setLabel(countryCode);
                journalLabelValueDTO.setValue(countryCode);

                journalLabelValueDTOs.add(journalLabelValueDTO);
            }

            return journalLabelValueDTOs;
        } catch (Exception e) {
            LOGGER.error(" Error while processing fetchCountryCodes service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchCountryCodes service method, error is {} ", e);

        }
    }

    /***
     *
     */
    public static final class BibliographyCriterianSearchInputDTODataMapper implements RowMapper<BibliographyCriterianSearchInputDTO> {
        public BibliographyCriterianSearchInputDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BibliographyCriterianSearchInputDTO bibliographyAdvSearchInputDTO = new BibliographyCriterianSearchInputDTO();

            if (doesColumnExist("author", rs)) bibliographyAdvSearchInputDTO.setAuthor(rs.getString("author"));
            if (doesColumnExist("company_name", rs))
                bibliographyAdvSearchInputDTO.setCompanyName(rs.getString("company_name"));
            if (doesColumnExist("ref_type", rs)) bibliographyAdvSearchInputDTO.setRefType(rs.getString("ref_type"));

            bibliographyAdvSearchInputDTO.setLabel(rs.getString("label"));
            bibliographyAdvSearchInputDTO.setValue(rs.getString("value"));
            bibliographyAdvSearchInputDTO.setOperator(rs.getString("operator"));

            return bibliographyAdvSearchInputDTO;
        }

        public static boolean doesColumnExist(String columnName, ResultSet rs) throws SQLException {
            ResultSetMetaData meta = rs.getMetaData();
            int numCol = meta.getColumnCount();
            for (int i = 1; i <= numCol; i++) {
                if (meta.getColumnName(i).equalsIgnoreCase(columnName)) {
                    return true;

                }

            }
            return false;
        }
    }

    /***
     *
     * @param author
     * @param userJdbc
     * @return
     */
    public List<ListAuthorsDTO> findByRefCriterionSeachAuthorContaining(String author, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByRefCriterionSeachAuthorContaining - Query - {} ", bibliographyAuthorSearchLikeQuery);

            List<ListAuthorsDTO> listAuthorsDTOList = userJdbc.getJdbcTemplate().query(bibliographyAuthorSearchLikeQuery, new String[]{author.toUpperCase(), author.toUpperCase(), author.toUpperCase()}, new BeanPropertyRowMapper<>(ListAuthorsDTO.class));

            return listAuthorsDTOList;
        } catch (Exception e) {

            LOGGER.error(" Error while processing findByRefCriterionSeachAuthorContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByRefCriterionSeachAuthorContaining service method, error is {} ", e);

        }

    }

    /****
     *
     * @param companyname
     * @param userJdbc
     * @return
     */
    public List<ListCompaniesDTO> findByRefCriterionSearchcompanyNameContaining(String companyname, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: findByRefCriterionSearchcompanyNameContaining - Query - {} ", bibliographyCompanySearchLikeQuery);

            List<ListCompaniesDTO> listCompaniesDTOList = userJdbc.getJdbcTemplate().query(bibliographyCompanySearchLikeQuery, new String[]{companyname.toUpperCase(), companyname.toUpperCase(), companyname.toUpperCase()}, new BeanPropertyRowMapper<>(ListCompaniesDTO.class));

            return listCompaniesDTOList;
        } catch (Exception e) {

            LOGGER.error(" Error while processing findByRefCriterionSearchcompanyNameContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing findByRefCriterionSearchcompanyNameContaining service method, error is {} ", e);

        }

    }

    /**
     *
     * @param jonurnalInputDTO
     * @param userJdbc
     * @return
     */
    public List<JournalLabelValueDTO> customSearchJournalFetch(JonurnalInputDTO jonurnalInputDTO, UserJdbc userJdbc) {

        String prepareQuery = StringUtils.EMPTY;

        if((Objects.nonNull(jonurnalInputDTO.getIssue()) && !jonurnalInputDTO.getIssue().isEmpty())
                && (Objects.nonNull(jonurnalInputDTO.getVolume()) && !jonurnalInputDTO.getVolume().isEmpty())
                && (Objects.nonNull(jonurnalInputDTO.getYear()) && !jonurnalInputDTO.getYear().isEmpty())
                && (Objects.nonNull(jonurnalInputDTO.getJournalName()) && !jonurnalInputDTO.getJournalName().isEmpty())) {

            prepareQuery = "select distinct start_page from reference_search.reference_master where start_page is not null " +
                    "and journal_patent_name = '"+jonurnalInputDTO.getJournalName()+"' " +
                    "and ref_year in ("+getCollectionDataInString(jonurnalInputDTO.getYear())+") " +
                    "and volume in ("+getCollectionDataInString(jonurnalInputDTO.getVolume())+") " +
                    "and issue in ("+getCollectionDataInString(jonurnalInputDTO.getIssue())+")";

        } else if((Objects.nonNull(jonurnalInputDTO.getVolume()) && !jonurnalInputDTO.getVolume().isEmpty())
                && (Objects.nonNull(jonurnalInputDTO.getYear()) && !jonurnalInputDTO.getYear().isEmpty())
                && (Objects.nonNull(jonurnalInputDTO.getJournalName()) && !jonurnalInputDTO.getJournalName().isEmpty())) {

            prepareQuery = "select distinct issue from reference_search.reference_master where issue is not null " +
                    "and journal_patent_name = '"+jonurnalInputDTO.getJournalName()+"' " +
                    "and ref_year in ("+getCollectionDataInString(jonurnalInputDTO.getYear())+") " +
                    "and volume in ("+getCollectionDataInString(jonurnalInputDTO.getVolume())+")";

        } else if((Objects.nonNull(jonurnalInputDTO.getYear()) && !jonurnalInputDTO.getYear().isEmpty())
                && (Objects.nonNull(jonurnalInputDTO.getJournalName()) && !jonurnalInputDTO.getJournalName().isEmpty())) {

            prepareQuery = "select distinct volume from reference_search.reference_master where volume is not null " +
                    "and journal_patent_name = '"+jonurnalInputDTO.getJournalName()+"' " +
                    "and ref_year in ("+getCollectionDataInString(jonurnalInputDTO.getYear())+")";

        } else if((Objects.nonNull(jonurnalInputDTO.getJournalName()) && !jonurnalInputDTO.getJournalName().isEmpty())) {

            prepareQuery = "select distinct ref_year from reference_search.reference_master where ref_year is not null " +
                    "and journal_patent_name = '"+jonurnalInputDTO.getJournalName()+"'";
        }

        List<JournalLabelValueDTO> listOfCustomSearchJournal = userJdbc.getJdbcTemplate().query(prepareQuery, new CustomSearchJournalPatentDataMapper());

        return listOfCustomSearchJournal;
    }


    /**
     *
     * @param listOfStrings
     * @return
     */
    private String getCollectionDataInString(List<String> listOfStrings) {
        return String.join(",", listOfStrings.stream().map(name -> ("'" + name + "'")).collect(Collectors.toList()));
    }


    public List<JournalLabelValueDTO> customSearchPatentFetch(PatentInputDTO patentInputDTO, UserJdbc userJdbc) {

        String prepareQuery = StringUtils.EMPTY;

        if((!patentInputDTO.getCountryCode().isEmpty() && Objects.nonNull(patentInputDTO.getCountryCode()))
                && (!patentInputDTO.getYear().isEmpty() && Objects.nonNull(patentInputDTO.getYear()))) {

            List<String> countryCodes = new ArrayList<>();
            for(String strVal : patentInputDTO.getCountryCode()) {
                countryCodes.add("patent_no like '"+strVal+"%'");
            }

            prepareQuery = "select distinct patent_no from reference_search.reference_master where patent_no is not null " +
                    "and ("+String.join(" or ", countryCodes)+") " +
                    "and ref_year in ("+getCollectionDataInString(patentInputDTO.getYear())+") order by patent_no ASC";

        } else if(!patentInputDTO.getCountryCode().isEmpty() && Objects.nonNull(patentInputDTO.getCountryCode())) {

            List<String> countryCodes = new ArrayList<>();
            for(String strVal : patentInputDTO.getCountryCode()) {
                countryCodes.add("patent_no like '"+strVal+"%'");
            }

            prepareQuery = "select distinct ref_year from reference_search.reference_master where ref_year is not null " +
                    "and ("+String.join(" or ", countryCodes)+") order by ref_year ASC";

        }

        List<JournalLabelValueDTO> listOfCustomSearchJournal = userJdbc.getJdbcTemplate().query(prepareQuery, new CustomSearchJournalPatentDataMapper());

        return listOfCustomSearchJournal;
    }

    /**
     *
     */
    private static final class CustomSearchJournalPatentDataMapper implements RowMapper<JournalLabelValueDTO> {
        public JournalLabelValueDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            JournalLabelValueDTO journalLabelValueDTO = new JournalLabelValueDTO();

            // For Journal Fields
            if(BibliographyAdvSearchInputDTODataMapper.doesColumnExist("start_page", rs)) {
                journalLabelValueDTO.setLabel(rs.getString("start_page"));
                journalLabelValueDTO.setValue(rs.getString("start_page"));
            }
            if(BibliographyAdvSearchInputDTODataMapper.doesColumnExist("issue", rs)) {
                journalLabelValueDTO.setLabel(rs.getString("issue"));
                journalLabelValueDTO.setValue(rs.getString("issue"));
            }
            if(BibliographyAdvSearchInputDTODataMapper.doesColumnExist("volume", rs)) {
                journalLabelValueDTO.setLabel(rs.getString("volume"));
                journalLabelValueDTO.setValue(rs.getString("volume"));
            }
            if(BibliographyAdvSearchInputDTODataMapper.doesColumnExist("ref_year", rs)) {
                journalLabelValueDTO.setLabel(rs.getString("ref_year"));
                journalLabelValueDTO.setValue(rs.getString("ref_year"));
            }

            // For Patent Fields
            if(BibliographyAdvSearchInputDTODataMapper.doesColumnExist("patent_no", rs)) {
                journalLabelValueDTO.setLabel(rs.getString("patent_no"));
                journalLabelValueDTO.setValue(rs.getString("patent_no"));
            }

            return journalLabelValueDTO;
        }
    }

    /***
     *<p>
     *     BibliographyAdvSearchInputDTODataMapper is used for to map the
     *     all the field values there corresponding to its columns
     *</p>
     */
    public static final class BibliographyAdvSearchInputDTODataMapper implements RowMapper<BibliographyAdvSearchInputDTO> {
        public BibliographyAdvSearchInputDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BibliographyAdvSearchInputDTO bibliographyAdvSearchInputDTO = new BibliographyAdvSearchInputDTO();

            if (doesColumnExist("pubmed_id", rs)) bibliographyAdvSearchInputDTO.setPubmedId(rs.getString("pubmed_id"));
            if (doesColumnExist("doi", rs)) bibliographyAdvSearchInputDTO.setDoi(rs.getString("doi"));
            if (doesColumnExist("issn_no", rs)) bibliographyAdvSearchInputDTO.setIssnNo(rs.getString("issn_no"));
            if (doesColumnExist("mesh", rs)) bibliographyAdvSearchInputDTO.setMesh(rs.getString("mesh"));

            //  bibliographyAdvSearchInputDTO.setCommonName(rs.getString("common_name"));
            bibliographyAdvSearchInputDTO.setRefId(rs.getLong("ref_id"));
            bibliographyAdvSearchInputDTO.setLabel(rs.getString("label"));
            bibliographyAdvSearchInputDTO.setValue(rs.getString("value"));
            bibliographyAdvSearchInputDTO.setOperator(rs.getString("operator"));

            return bibliographyAdvSearchInputDTO;
        }

        public static boolean doesColumnExist(String columnName, ResultSet rs) throws SQLException {
            ResultSetMetaData meta = rs.getMetaData();
            int numCol = meta.getColumnCount();
            for (int i = 1; i <= numCol; i++) {
                if (meta.getColumnName(i).equalsIgnoreCase(columnName)) {
                    return true;

                }

            }
            return false;
        }
    }

}


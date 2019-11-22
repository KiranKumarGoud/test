package com.excelra.mvc.repository;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.*;
import com.excelra.mvc.model.search.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.model.userjdbc.UserSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * This is AllMappingIds DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class AllMappingIdsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllMappingIdsDAO.class);

    private static final String ALL_MAPPING_IDS_SEARCH_COUNT_QUERY = "SELECT count(distinct act_id) as actIdsCount, count(distinct ref_id) as refIdsCount, count(distinct str_id) as strIdsCount, count(distinct gvk_id) as gvkIdsCount, count(distinct assay_id) as assayIdsCount from target_search.all_mapping_ids where  ";

    private static final String ALL_MAPING_IDS_SEARCH_QUERY = "SELECT act_id, ref_id, str_id, gvk_id, assay_id, stdname_id, assay_type, target_id, skeleton_id, scaffold2_id, framework2_id, adme_ontology_id, toxicity_ontology_id, source_id, endpoint_id, database_name from target_search.all_mapping_ids where ";

    private static final String PROTEIN_CLASSIFICATION = "ProteinClassification";

    private static final String YEAR_WISE = "Yearwise";

    private static final String INDICATION = "Indication";

    private static final String BIBLIOOGRAPHY = "Bibliography";

    private static final String TOXICITY = "Toxicity";

    private static final String TAXONOMY = "Taxonomy";

    private static final String ENDPOINT = "Endpoint";

    @Autowired
    private AllMappingIdsComponent allMappingIdsComponent;

    @Autowired
    private AllMappingQueryComponent allMappingQueryComponent;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    @Value("${search.create.temptables.query}")
    private String createTempTablesQuery;

    @Value("${search.results.insert.query}")
    private String searchResultsInsertQuery;

    @Value("${visual.results.insert.query}")
    private String visualResultsInsertQuery;

    @Value("${protein.classification.query}")
    private String proteinClassificationQuery;

    @Value("${bibliography.query}")
    private String bibliographyQuery;

    @Value("${toxicity.query}")
    private String toxicityQuery;

    @Value("${taxonomy.query}")
    private String taxonomyQuery;

    @Value("${endpoint.query}")
    private String endpointQuery;

    @Value("${yearwise.query}")
    private String yearwiseQuery;

    @Value("${indication.query}")
    private String indicationQuery;

    @Value("${visual.temp.table.truncate.query}")
    private String truncateTempTableQuery;

    @Value("${searchresults.truncate.query}")
    private String searchResultsTruncateQuery;

    @Value("${visualresults.truncate.query}")
    private String visualResultsTruncateQuery;

    @Value("${allmaping.query}")
    private String allmappingQuery;

    @Value("${allmapping.assay.query}")
    private String allMappingAssayQuery;

    @Value("${visual.count.from.temptable.query}")
    private String visualResultCounts;

    @Value("${search.count.from.defaulttable.query}")
    private String searchCountsFromDefaultTable;

    @Value("${visual.results.all.insert.query}")
    private String visualResultsAllInsertQuery;

    List<UserSearch> userSearchList = new ArrayList<>();

    Map<String, UserJdbcTemp> userJdbcTemp = new HashMap<>();

    private Boolean completedStatus = Boolean.FALSE;

    /**
     * @param userJdbc
     * @return
     */
    @Transactional
    public String createTempTables(UserJdbc userJdbc) throws GoStarSqlException {

        LOGGER.info("createTempTables ");

        System.out.println(":: Step1 - Creating Temp Tables Query :: ---> " + createTempTablesQuery);

        try {
            userJdbc.getJdbcTemplate().execute(createTempTablesQuery);

            return "Temp Tables are created Successfully";
        } catch (Exception e) {

            return "Error while creating temp tables";
        }
    }

    /**
     * @param searchCountInputDTOList
     * @param userJdbc
     * @return
     */
    @Transactional
    public SearchVisualizationResultDTO searchWithVisualizationData(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) throws GoStarSqlException {

        LOGGER.info("searchWithVisualizationData {} ", searchCountInputDTOList);

        // Return Empty data if UserJdbc Object is null.
        if (Objects.isNull(userJdbc)) return new SearchVisualizationResultDTO();

        if (Objects.nonNull(searchCountInputDTOList)) {
            UserSearch userSearch = new UserSearch();
            userSearch.setUserSessionId(userJdbc.getUserSessionId());
            userSearch.setSearchCountInputDTOList(searchCountInputDTOList);

            userSearchList.add(userSearch);
        }

        System.out.println(":: Step1 - Creating Temp Tables ... ::");
        // createTempTables(userJdbc);
        System.out.println(":: Step1 - Temp Tables Completed ::");

        // String finalQuery = ALL_MAPING_IDS_SEARCH_QUERY + prepareCombinationQuery(searchCountInputDTOList, userJdbc);

        // String combinationQuery = allMappingIdsComponent.prepareCombinationQuery(searchCountInputDTOList, userJdbc);

        String combinationQuery = allMappingQueryComponent.prepareCombinationQuery(searchCountInputDTOList, userJdbc);

        if (combinationQuery.isEmpty()) {
            return new SearchVisualizationResultDTO();
        }

        String finalQuery = ALL_MAPING_IDS_SEARCH_QUERY + combinationQuery;

        // For Bibliography Single Selection
        // finalQuery = finalQuery.replaceAll("str_id\\s+in\\s+ref_id\\s+in", " ref_id in ");

        System.out.println(":: Step2 - Final Search Query... :: searchWithVisualizationData ---> " + finalQuery);

        // Insert Search results into temp tables
        insertSearchResultsIntoTempTables(finalQuery, userJdbc);

        SearchVisualizationResultDTO searchVisualizationResultDTO = new SearchVisualizationResultDTO();

        return searchVisualizationResultDTO;
    }


    /**
     * @param searchCountInputDTOList
     * @param userJdbc
     */
    @Transactional
    public void reloadVisualizationData(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) throws GoStarSqlException {

        try {
            LOGGER.info("reloadVisualizationData {} ", searchCountInputDTOList);

            System.out.println(":: Step1 - Creating Temp Tables ... ::");
            createTempTables(userJdbc);
            System.out.println(":: Step1 - Temp Tables Completed ::");

            // String finalQuery = ALL_MAPING_IDS_SEARCH_QUERY + allMappingIdsComponent.prepareCombinationQuery(searchCountInputDTOList, userJdbc);

            String finalQuery = ALL_MAPING_IDS_SEARCH_QUERY + allMappingQueryComponent.prepareCombinationQuery(searchCountInputDTOList, userJdbc);

            // For Bibliography Single Selection
            // finalQuery = finalQuery.replaceAll("str_id\\s+in\\s+ref_id\\s+in", " ref_id in ");

            System.out.println(":: Step2 - Final Search Query... :: reloadVisualizationData ---> " + finalQuery);

            // Insert Search results into temp tables
            insertSearchResultsIntoTempTables(finalQuery, userJdbc);
        } catch(Exception e) {
            LOGGER.error(" Error while processing reloadVisualizationData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing reloadVisualizationData service method, error is {} ", e);
        }
    }


    /**
     * @param filterInputVisualDTOList
     * @param userJdbc
     * @return
     */
    @Transactional
    public AllMappingFinalResultCountDTO searchWithFilterVisualizationData(List<FilterInputVisualizationDTO> filterInputVisualDTOList, UserJdbc userJdbc) throws GoStarSqlException {

        LOGGER.info("searchWithFilterVisualizationData {} ", filterInputVisualDTOList);

        // Return Empty data if UserJdbc Object is null.
        if (Objects.isNull(userJdbc)) return new AllMappingFinalResultCountDTO();

        String classificationQuery = StringUtils.EMPTY;
        String bibliographyQuery = StringUtils.EMPTY;
        String yearwiseQuery = StringUtils.EMPTY;
        String indicationQuery = StringUtils.EMPTY;
        String taxonomyQuery = StringUtils.EMPTY;
        String visualFilterQuery = StringUtils.EMPTY;

        for (FilterInputVisualizationDTO filterInputVisualizationDTO : filterInputVisualDTOList) {

            List<VisualChartData> visualChartDataList = filterInputVisualizationDTO.getData();

            switch (filterInputVisualizationDTO.getKey()) {

                case "classification":
                    if(!visualChartDataList.isEmpty()) classificationQuery = allMappingIdsComponent.getClassificationFilterQuery(visualChartDataList);
                    LOGGER.info("Classification Filtered Query - {} ", classificationQuery);

                    break;
                case "bibliography":
                    if(!visualChartDataList.isEmpty()) bibliographyQuery = allMappingIdsComponent.getBibliographyFilterQuery(visualChartDataList);
                    LOGGER.info("Bibliography Filtered Query - {} ", bibliographyQuery);

                    break;
                case "yearwise":
                    if(!visualChartDataList.isEmpty()) yearwiseQuery = allMappingIdsComponent.getYearwiseFilterQuery(visualChartDataList);
                    LOGGER.info("Yearwise Filtered Query - {} ", yearwiseQuery);

                    break;
                case "indication":
                    if(!visualChartDataList.isEmpty()) indicationQuery = allMappingIdsComponent.getIndicationFilterQuery(visualChartDataList);
                    LOGGER.info("Indication Filtered Query - {} ", indicationQuery);

                    break;
                case "taxonomy":
                    if(!visualChartDataList.isEmpty()) taxonomyQuery = allMappingIdsComponent.getTaxonomyFilterQuery(visualChartDataList);
                    LOGGER.info("Indication Filtered Query - {} ", taxonomyQuery);

                    break;

                default:

            }

        }

        visualFilterQuery = "insert into visual_results select * from search_results ";
        if(!classificationQuery.isEmpty() ||
                !bibliographyQuery.isEmpty() ||
                !yearwiseQuery.isEmpty() ||
                !indicationQuery.isEmpty() ||
                !taxonomyQuery.isEmpty()) visualFilterQuery = visualFilterQuery + " where ";
        visualFilterQuery = visualFilterQuery + ((!classificationQuery.isEmpty()) ? " and " + classificationQuery : "");
        visualFilterQuery = visualFilterQuery + ((!bibliographyQuery.isEmpty()) ? " and " + bibliographyQuery : "");
        visualFilterQuery = visualFilterQuery + ((!yearwiseQuery.isEmpty()) ? " and " + yearwiseQuery : "");
        visualFilterQuery = visualFilterQuery + ((!indicationQuery.isEmpty()) ? " and " + indicationQuery : "");
        visualFilterQuery = visualFilterQuery + ((!taxonomyQuery.isEmpty()) ? " and " + taxonomyQuery : "");

        visualFilterQuery = visualFilterQuery.replaceAll("where\\s+and", " where ");
        LOGGER.info(" Final Merged Filtered query with Options - visualFilterQuery {} ", visualFilterQuery);

        /**
         * Truncate before insert the data
         */
        executeQueryForVisualFilters(userJdbc, visualResultsTruncateQuery);

        if(visualFilterQuery.trim().isEmpty()) {
            executeQueryForVisualFilters(userJdbc, visualResultsInsertQuery);
        } else {
            executeQueryForVisualFilters(userJdbc, visualFilterQuery);
        }

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = searchCountsForVisualResults(userJdbc);

        return allMappingFinalResultCountDTO;
    }

    /**
     *
     * @param userJdbc
     * @param filterQuery
     * @return
     * @throws GoStarSqlException
     */
    public Boolean executeQueryForVisualFilters(UserJdbc userJdbc, String filterQuery) throws GoStarSqlException {

        /**
         * Get The available connections for executing query
         */
        JdbcTemplate proteinSpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, PROTEIN_CLASSIFICATION);
        JdbcTemplate bibliographySpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, YEAR_WISE);
        JdbcTemplate yearwiseSpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, BIBLIOOGRAPHY);
        JdbcTemplate indicationSpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, INDICATION);
        JdbcTemplate taxonomySpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, TAXONOMY);

        try {
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                LOGGER.info("Executing the Protein Specific Jdbc Query - filterQuery {} ", filterQuery);
                proteinSpecificJdbc.execute(filterQuery);
                return "1. Completed";
            });

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                LOGGER.info("Executing the Bibliography Specific Jdbc Query - filterQuery {} ", filterQuery);
                bibliographySpecificJdbc.execute(filterQuery);
                return "2. Completed";
            });

            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
                LOGGER.info("Executing the Yearwise Specific Jdbc Query - filterQuery {} ", filterQuery);
                yearwiseSpecificJdbc.execute(filterQuery);
                return "3. Completed";
            });

            CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
                LOGGER.info("Executing the Indication Specific Jdbc Query - filterQuery {} ", filterQuery);
                indicationSpecificJdbc.execute(filterQuery);
                return "4. Completed";
            });

            CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
                LOGGER.info("Executing the Taxonomy Specific Jdbc Query - filterQuery {} ", filterQuery);
                taxonomySpecificJdbc.execute(filterQuery);
                return "5. Completed";
            });

            CompletableFuture.allOf(future1, future2, future3, future4, future5).join();
            if (!future1.get().trim().isEmpty()
                    && !future2.get().trim().isEmpty()
                    && !future3.get().trim().isEmpty()
                    && !future4.get().trim().isEmpty()
                    && !future5.get().trim().isEmpty()) {
                completedStatus = Boolean.TRUE;
            }
        } catch (Exception ex) {
            System.out.println("Error while Inserting Search result because, " + ex.getMessage());
        }

        return completedStatus;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public AllMappingFinalResultCountDTO searchForCounts(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) throws GoStarSqlException {

        LOGGER.info("searchForCounts {} ", searchCountInputDTOList);

        // Return Empty data if UserJdbc Object is null.
        if (Objects.isNull(userJdbc)) return new AllMappingFinalResultCountDTO();

        if (searchCountInputDTOList.isEmpty()) {
            return new AllMappingFinalResultCountDTO();
        }

        // String combinationQuery = allMappingIdsComponent.prepareCombinationQuery(searchCountInputDTOList, userJdbc);

        String combinationQuery = allMappingQueryComponent.prepareCombinationQuery(searchCountInputDTOList, userJdbc);

        if (combinationQuery.isEmpty()) {
            AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = new AllMappingFinalResultCountDTO();
            allMappingFinalResultCountDTO.setGvkIdsCount(new BigDecimal(0));
            allMappingFinalResultCountDTO.setActIdsCount(new BigDecimal(0));
            allMappingFinalResultCountDTO.setAssayIdsCount(new BigDecimal(0));
            allMappingFinalResultCountDTO.setRefIdsCount(new BigDecimal(0));
            allMappingFinalResultCountDTO.setStrIdsCount(new BigDecimal(0));

            return allMappingFinalResultCountDTO;
        }

        String finalQuery = ALL_MAPPING_IDS_SEARCH_COUNT_QUERY + combinationQuery;

        // For Bibliography Single Selection
        // finalQuery = finalQuery.replaceAll("str_id\\s+in\\s+ref_id\\s+in", " ref_id in ");

        System.out.println(":: Step1 - Counts - Final Search Query... :: ---> " + finalQuery);

        AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = userJdbc.getJdbcTemplate().queryForObject(finalQuery, new BeanPropertyRowMapper<>(AllMappingFinalResultCountDTO.class));

        return allMappingFinalResultCountDTO;
    }



    /**
     * @param recordsQuery
     * @param userJdbc
     */
    private void insertSearchResultsIntoTempTables(String recordsQuery, UserJdbc userJdbc) {
        try {

            JdbcTemplate proteinJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();
            JdbcTemplate yearwiseJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();
            JdbcTemplate bibliographyJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();
            JdbcTemplate indicationJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();
            JdbcTemplate toxicityJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();
            JdbcTemplate taxonomyJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();
            JdbcTemplate endpointJdbcConnection = customJdbcConnection.getJdbcTemplateConnection();

            UserJdbcTemp ujt = new UserJdbcTemp();
            ujt.setProteinJdbcConnection(proteinJdbcConnection);
            ujt.setYearwiseJdbcConnection(yearwiseJdbcConnection);
            ujt.setBibliographyJdbcConnection(bibliographyJdbcConnection);
            ujt.setIndicationJdbcConnection(indicationJdbcConnection);
            ujt.setToxicityJdbcConnection(toxicityJdbcConnection);
            ujt.setTaxonomyJdbcConnection(taxonomyJdbcConnection);
            ujt.setEndpointJdbcConnection(taxonomyJdbcConnection);
            userJdbcTemp.put(userJdbc.getUserSessionId(), ujt);

            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {

                System.out.println(":: Step1 - Creating Temp Tables Query :: (ProteinClassification) ---> " + createTempTablesQuery);
                proteinJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (ProteinClassification) ---> " + searchResultsTruncateQuery);
                proteinJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (ProteinClassification) ---> " + visualResultsTruncateQuery);
                proteinJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (ProteinClassification) ---> " + searchResultsInsertQuery + recordsQuery);
                proteinJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (ProteinClassification) ---> " + visualResultsInsertQuery);
                proteinJdbcConnection.execute(visualResultsInsertQuery);

                return "1. Completed";
            });

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {

                System.out.println(":: Step1 - Creating Temp Tables Query :: (Yearwise) ---> " + createTempTablesQuery);
                yearwiseJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (Yearwise) ---> " + searchResultsTruncateQuery);
                yearwiseJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (Yearwise) ---> " + visualResultsTruncateQuery);
                yearwiseJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (Yearwise) ---> " + searchResultsInsertQuery + recordsQuery);
                yearwiseJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (Yearwise) ---> " + visualResultsInsertQuery);
                yearwiseJdbcConnection.execute(visualResultsInsertQuery);

                return "2. Completed";
            });

            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {

                System.out.println(":: Step1 - Creating Temp Tables Query :: (Bibliography) ---> " + createTempTablesQuery);
                bibliographyJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (Bibliography) ---> " + searchResultsTruncateQuery);
                bibliographyJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (Bibliography) ---> " + visualResultsTruncateQuery);
                bibliographyJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (Bibliography) ---> " + searchResultsInsertQuery + recordsQuery);
                bibliographyJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (Bibliography) ---> " + visualResultsInsertQuery);
                bibliographyJdbcConnection.execute(visualResultsInsertQuery);

                return "3. Completed";
            });

            CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {

                System.out.println(":: Step1 - Creating Temp Tables Query :: (Indication) ---> " + createTempTablesQuery);
                indicationJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (Indication) ---> " + searchResultsTruncateQuery);
                indicationJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (Indication) ---> " + visualResultsTruncateQuery);
                indicationJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (Indication) ---> " + searchResultsInsertQuery + recordsQuery);
                indicationJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (Indication) ---> " + visualResultsInsertQuery);
                indicationJdbcConnection.execute(visualResultsInsertQuery);

                return "4. Completed";
            });

            CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {

    

                System.out.println(":: Step1 - Creating Temp Tables Query :: (Toxicity) ---> " + createTempTablesQuery);
                toxicityJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (Toxicity) ---> " + searchResultsTruncateQuery);
                toxicityJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (Toxicity) ---> " + visualResultsTruncateQuery);
                toxicityJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (Toxicity) ---> " + searchResultsInsertQuery + recordsQuery);
                toxicityJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (Toxicity) ---> " + visualResultsInsertQuery);
                toxicityJdbcConnection.execute(visualResultsInsertQuery);

                return "5. Completed";
            });

            CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> {

                

                System.out.println(":: Step1 - Creating Temp Tables Query :: (Taxonomy) ---> " + createTempTablesQuery);
                taxonomyJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (Taxonomy) ---> " + searchResultsTruncateQuery);
                taxonomyJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (Taxonomy) ---> " + visualResultsTruncateQuery);
                taxonomyJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (Taxonomy) ---> " + searchResultsInsertQuery + recordsQuery);
                taxonomyJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (Taxonomy) ---> " + visualResultsInsertQuery);
                taxonomyJdbcConnection.execute(visualResultsInsertQuery);

                return "6. Completed";
            });

            CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> {

                LOGGER.info("Entered into future7 call");

                System.out.println(":: Step1 - Creating Temp Tables Query :: (Endpoint) ---> " + createTempTablesQuery);
                endpointJdbcConnection.execute(createTempTablesQuery);

                System.out.println(":: Step2 - Truncate Search results Query... :: (Endpoint) ---> " + searchResultsTruncateQuery);
                endpointJdbcConnection.execute(searchResultsTruncateQuery);

                System.out.println(":: Step3 - Truncate Visual results Query... :: (Endpoint) ---> " + visualResultsTruncateQuery);
                endpointJdbcConnection.execute(visualResultsTruncateQuery);

                System.out.println(":: Step4 - Insert Search results Query... :: (Endpoint) ---> " + searchResultsInsertQuery + recordsQuery);
                endpointJdbcConnection.execute(searchResultsInsertQuery + recordsQuery);

                System.out.println(":: Step5 - Insert Visual results Query... :: (Endpoint) ---> " + visualResultsInsertQuery);
                endpointJdbcConnection.execute(visualResultsInsertQuery);

                return "7. Completed";
            });

            /*CompletableFuture<Void> allCompleted = CompletableFuture.allOf(future1,future2,future3,future4);
            allCompleted.get();*/

            CompletableFuture.allOf(future1, future2, future3, future4, future5, future6, future7).join();
            if (!future1.get().trim().isEmpty()
                    && !future2.get().trim().isEmpty()
                    && !future3.get().trim().isEmpty()
                    && !future4.get().trim().isEmpty()
                    && !future5.get().trim().isEmpty()
                    && !future6.get().trim().isEmpty()
                    && !future7.get().trim().isEmpty()) {
                completedStatus = Boolean.TRUE;
            }



        } catch (Exception ex) {
            System.out.println("Error while Inserting Search result because, " + ex.getMessage());
        }
    }

    /**
     * Fetch for Target Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationResultDTO> fetchForProteinClassification(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            // reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, PROTEIN_CLASSIFICATION);

            System.out.println(":: Step3 - Protein Classification Query... :: ---> " + proteinClassificationQuery);
            LOGGER.info(" :: Step3 - Protein Classification Query... :: ---> {} ", proteinClassificationQuery);

            return specificJdbc.query(proteinClassificationQuery, new BeanPropertyRowMapper<>(VisualizationResultDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForProteinClassification service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForProteinClassification service method, error is {} ", e);
        }
    }

    /**
     * Fetch for Yearwise Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationResultDTO> fetchForYearWiseVisualization(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            // reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, YEAR_WISE);

            System.out.println(":: Step3 - Yearwise Query... :: ---> " + yearwiseQuery);
            LOGGER.info(" :: Step3 - Yearwise Query... :: --->  {} ", yearwiseQuery);

            return specificJdbc.query(yearwiseQuery, new BeanPropertyRowMapper<>(VisualizationResultDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForYearWiseVisualization service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForYearWiseVisualization service method, error is {} ", e);
        }
    }

    /**
     * Fetch for Bibliography Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationResultDTO> fetchForBibliographyVisualization(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            // reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, BIBLIOOGRAPHY);

            System.out.println(":: Step3 - Bibliography Query... :: ---> " + bibliographyQuery);
            LOGGER.info(":: Step3 - Bibliography Query... :: --->  {} ", bibliographyQuery);

            return specificJdbc.query(bibliographyQuery, new BeanPropertyRowMapper<>(VisualizationResultDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForBibliographyVisualization service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForBibliographyVisualization service method, error is {} ", e);
        }
    }

    /**
     * Fetch for Indication Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationIndicationDTO> fetchForIndicationVisualization(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            //  reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, INDICATION);

            System.out.println(":: Step4 - Indication Query... :: ---> " + indicationQuery);
            LOGGER.info(":: Step4 - Indication Query... :: --->  {} ", indicationQuery);

            return specificJdbc.query(indicationQuery, new BeanPropertyRowMapper<>(VisualizationIndicationDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForIndicationVisualization service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForIndicationVisualization service method, error is {} ", e);
        }
    }

    /**
     * Fetch for Toxicity Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationTaxonomyEndPointToxicityDTO> fetchForToxicityVisualization(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            // reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, TOXICITY);

            System.out.println(":: Step5 - Toxicity Query... :: ---> " + toxicityQuery);
            LOGGER.info(":: Step5 - Toxicity Query... :: --->  {} ", toxicityQuery);

            return specificJdbc.query(toxicityQuery, new BeanPropertyRowMapper<>(VisualizationTaxonomyEndPointToxicityDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForToxicityVisualization service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForToxicityVisualization service method, error is {} ", e);
        }
    }

    /**
     * Fetch for Taxonomy Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationTaxonomyEndPointToxicityDTO> fetchForTaxonomyVisualization(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            // reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, TAXONOMY);

            System.out.println(":: Step6 - Taxonomy Query... :: ---> " + taxonomyQuery);
            LOGGER.info(":: Step6 - Taxonomy Query... :: --->  {} ", taxonomyQuery);

            return specificJdbc.query(taxonomyQuery, new BeanPropertyRowMapper<>(VisualizationTaxonomyEndPointToxicityDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForTaxonomyVisualization service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForTaxonomyVisualization service method, error is {} ", e);
        }
    }

    /**
     * Fetch for Taxonomy Visualizations
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<VisualizationTaxonomyEndPointToxicityDTO> fetchForEndpointVisualization(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();
            // reloadVisualization(userJdbc);

            JdbcTemplate specificJdbc = getActivitySpecificJdbcConnection(userJdbc, ENDPOINT);

            System.out.println(":: Step7 - Enpoint Query... :: ---> " + endpointQuery);
            LOGGER.info(":: Step7 - Enpoint Query... :: --->  {} ", endpointQuery);

            return specificJdbc.query(endpointQuery, new BeanPropertyRowMapper<>(VisualizationTaxonomyEndPointToxicityDTO.class));
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchForEndpointVisualization service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchForEndpointVisualization service method, error is {} ", e);
        }
    }

    /**
     * @return
     */
    public List<UserSearch> getUpdatedSearchResults() {
        return userSearchList;
    }


    /**
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<VisualizationResultDTO> visualizationForProteinClassification(UserJdbc userJdbc) {
        return fetchForProteinClassification(userJdbc);
    }

    /**
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<VisualizationResultDTO> visualizationForYearWiseVisualization(UserJdbc userJdbc) {
        return fetchForYearWiseVisualization(userJdbc);
    }

    /**
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<VisualizationResultDTO> visualizationForBibliographyVisualization(UserJdbc userJdbc) {
        return fetchForBibliographyVisualization(userJdbc);
    }

    /**
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<VisualizationIndicationDTO> visualizationForIndicationVisualization(UserJdbc userJdbc) {
        return fetchForIndicationVisualization(userJdbc);
    }

    @Transactional
    public List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForToxicityVisualization(UserJdbc userJdbc) {
        return fetchForToxicityVisualization(userJdbc);
    }

    @Transactional
    public List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForTaxonomyVisualization(UserJdbc userJdbc) {
        return fetchForTaxonomyVisualization(userJdbc);
    }

    @Transactional
    public List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForEndpointVisualization(UserJdbc userJdbc) {
        return fetchForEndpointVisualization(userJdbc);
    }

    //@Transactional
    public void reloadVisualization(UserJdbc userJdbc) {
        // Refilling the search details for Filter visualization. - Later may change this logic
        if (Objects.nonNull(userSearchList)) {
            for (UserSearch userSearch : userSearchList) {
                if (userSearch.getUserSessionId().equalsIgnoreCase(userJdbc.getUserSessionId())) {
                    reloadVisualizationData(userSearch.getSearchCountInputDTOList(), userJdbc);
                }
            }
        }
    }

    /**
     * @param userJdbc
     * @param activity
     * @return
     */
    public JdbcTemplate getActivitySpecificJdbcConnection(UserJdbc userJdbc, String activity) {
        UserJdbcTemp visualizationJdbc = null;
        JdbcTemplate specificJdbc = null;

        for (Map.Entry<String, UserJdbcTemp> entry : userJdbcTemp.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(userJdbc.getUserSessionId())) {
                visualizationJdbc = (UserJdbcTemp) entry.getValue();

                switch (activity) {
                    case PROTEIN_CLASSIFICATION:
                        specificJdbc = visualizationJdbc.getProteinJdbcConnection();
                        break;
                    case YEAR_WISE:
                        specificJdbc = visualizationJdbc.getYearwiseJdbcConnection();
                        break;
                    case BIBLIOOGRAPHY:
                        specificJdbc = visualizationJdbc.getBibliographyJdbcConnection();
                        break;
                    case INDICATION:
                        specificJdbc = visualizationJdbc.getIndicationJdbcConnection();
                        break;
                    case TOXICITY:
                        specificJdbc = visualizationJdbc.getToxicityJdbcConnection();
                        break;
                    case TAXONOMY:
                        specificJdbc = visualizationJdbc.getTaxonomyJdbcConnection();
                        break;
                    case ENDPOINT:
                        specificJdbc = visualizationJdbc.getEndpointJdbcConnection();
                        break;
                    default:
                        System.out.println("INVALID Activity");
                }
            }

        }
        return specificJdbc;
    }

    /**
     * Count after filtered data from Visual Results Table to display in Visualization and Tabularview pages
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public AllMappingFinalResultCountDTO searchCountsForVisualResults(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            LOGGER.info("searchCountsForVisualResults Start Execution ");

            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new AllMappingFinalResultCountDTO();

            JdbcTemplate jdbcTemplate = getActivitySpecificJdbcConnection(userJdbc, "ProteinClassification");

            String finalQuery = visualResultCounts;

            System.out.println(":: Counts - Final Search Query... :: ---> " + finalQuery);
            LOGGER.info(":: Counts - Final Search Query... :: ---> {} ", finalQuery);

            AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = jdbcTemplate.queryForObject(finalQuery, new BeanPropertyRowMapper<>(AllMappingFinalResultCountDTO.class));

            return allMappingFinalResultCountDTO;
        } catch(Exception e) {
            LOGGER.error(" Error while processing searchCountsForVisualResults service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing searchCountsForVisualResults service method, error is {} ", e);
        }

    }

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public AllMappingFinalResultCountDTO searchAllVisualizationData(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            LOGGER.info("searchAllVisualizationData - searchCountsForVisualResults Start Execution ");

            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new AllMappingFinalResultCountDTO();

            JdbcTemplate jdbcTemplate = getActivitySpecificJdbcConnection(userJdbc, "ProteinClassification");

            /**
             * Get the available connections and ready to insert the AllMapping data
             */
            JdbcTemplate proteinSpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, PROTEIN_CLASSIFICATION);
            JdbcTemplate bibliographySpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, YEAR_WISE);
            JdbcTemplate yearwiseSpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, BIBLIOOGRAPHY);
            JdbcTemplate indicationSpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, INDICATION);
            JdbcTemplate toxicitySpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, TOXICITY);
            JdbcTemplate taxonomySpecificJdbc = getActivitySpecificJdbcConnection(userJdbc, TAXONOMY);

            /** Truncate before insert into Visual Result **/
            proteinSpecificJdbc.execute(visualResultsTruncateQuery);
            bibliographySpecificJdbc.execute(visualResultsTruncateQuery);
            yearwiseSpecificJdbc.execute(visualResultsTruncateQuery);
            indicationSpecificJdbc.execute(visualResultsTruncateQuery);
            toxicitySpecificJdbc.execute(visualResultsTruncateQuery);
            taxonomySpecificJdbc.execute(visualResultsTruncateQuery);

            /** Insert the Visual Results from Search Results Avaialble GVKIDs and fetch from AllMappingIds Table to Insert into Visual Results **/
            proteinSpecificJdbc.execute(visualResultsAllInsertQuery);
            bibliographySpecificJdbc.execute(visualResultsAllInsertQuery);
            yearwiseSpecificJdbc.execute(visualResultsAllInsertQuery);
            indicationSpecificJdbc.execute(visualResultsAllInsertQuery);
            toxicitySpecificJdbc.execute(visualResultsAllInsertQuery);
            taxonomySpecificJdbc.execute(visualResultsAllInsertQuery);

            String finalQuery = visualResultCounts;

            System.out.println(":: Counts - Final Search Query... :: ---> " + finalQuery);
            LOGGER.info(":: Counts - Final Search Query... :: ---> {} ", finalQuery);

            AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = jdbcTemplate.queryForObject(finalQuery, new BeanPropertyRowMapper<>(AllMappingFinalResultCountDTO.class));

            return allMappingFinalResultCountDTO;
        } catch(Exception e) {
            LOGGER.error(" Error while processing searchAllVisualizationData service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing searchAllVisualizationData service method, error is {} ", e);
        }

    }

    /**
     * Find the Default counts for ActId, GvkId, StrId, RefId and AssayId
     * Default Counts when loading the page, this count will fetch from default table from existing schema.
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public AllMappingFinalResultCountDTO searchForDefaultCounts(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            LOGGER.info("Default Counts for Search ");

            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new AllMappingFinalResultCountDTO();

            String finalQuery = searchCountsFromDefaultTable;
            System.out.println(":: Counts - Default Counts Query... :: ---> " + finalQuery);

            String allMappingDefaultJson = userJdbc.getJdbcTemplate().queryForObject(finalQuery, String.class);

            ObjectMapper mapper = new ObjectMapper();
            AllMappingFinalResultCountDTO allMappingFinalResultCountDTO = new AllMappingFinalResultCountDTO();
            try {
                LOGGER.info(" :: Object Mapper for the response String {} ", allMappingDefaultJson);
                allMappingFinalResultCountDTO = mapper.readValue(allMappingDefaultJson, AllMappingFinalResultCountDTO.class);
            } catch (Exception e) {
                LOGGER.error("Exception in ObjectMapper {} ", e.getMessage());
            }

            return allMappingFinalResultCountDTO;
        } catch(Exception e) {
            LOGGER.error(" Error while processing searchForDefaultCounts service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing searchForDefaultCounts service method, error is {} ", e);
        }

    }
}

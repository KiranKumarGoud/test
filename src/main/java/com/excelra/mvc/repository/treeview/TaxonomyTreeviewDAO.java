package com.excelra.mvc.repository.treeview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.treeview.TaxonomyTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TaxonomyTreeviewDTO;
import com.excelra.mvc.model.treeview.TaxonomyMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * This is Taxonomy Treeview DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class TaxonomyTreeviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToxicityTreeviewDAO.class);

    @Value("${taxonomy.treeview.default.query}")
    private String taxonomyDefaultQuery;

    @Value("${taxonomy.treeview.bytaxid.query}")
    private String taxonomyByIdQuery;

    @Value("${taxonomy.treeview.search.query}")
    private String taxonomySearchQuery;

    @Value("${taxonomy.treeview.parentlevel.query}")
    private String taxonomyParentLevelQuery;

    @Value("${taxonomy.treeview.search.bytaxname.query}")
    private String taxonomyTreeviewSearchByTaxnameQuery;

    @Value("${taxonomy.treeview.search.in.query}")
    private String taxonomyTreeviewSearchInQuery;

    @Autowired
    private StringUtility stringUtility;


    /**
     *
     * @param taxId
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<TaxonomyTreeviewDTO> prepareTreeviewForNode(Long taxId, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TaxonomyTreeviewDTO> taxonomyTreeviewDTOList = new ArrayList<>();

            if(taxId == 0) {
                LOGGER.info(" :: prepareTreeviewForNode - Default Query - {} ", taxonomyDefaultQuery);
                taxonomyTreeviewDTOList = userJdbc.getJdbcTemplate().query(taxonomyDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(TaxonomyTreeviewDTO.class));
            } else {
                LOGGER.info(" :: prepareTreeviewForNode - Toxicity OntologyId Query - {} ", taxonomyByIdQuery);
                taxonomyTreeviewDTOList = userJdbc.getJdbcTemplate().query(taxonomyByIdQuery, new Long[]{ taxId }, new BeanPropertyRowMapper<>(TaxonomyTreeviewDTO.class));
            }

            return taxonomyTreeviewDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForNode service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForNode service method, error is {} ", e);
        }
    }

    /**
     *
     * @param taxName
     * @param userJdbc
     * @return
     */
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForTaxName(String taxName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForTaxName - Search Query - {} ", taxonomySearchQuery);
            taxonomyTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(taxonomySearchQuery, new String[]{taxName.toLowerCase(), taxName.toLowerCase()}, new BeanPropertyRowMapper<>(TaxonomyTreeviewParentChildNodeDTO.class));

            // Load All existing Parents from the master table
            List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentsDTOs = new ArrayList<>();
            taxonomyTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(taxonomyParentLevelQuery, new BeanPropertyRowMapper<>(TaxonomyTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            taxonomyTreeviewParentChildNodeDTOs.addAll(taxonomyTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewDuplicatesRemoveList = taxonomyTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(TaxonomyTreeviewParentChildNodeDTO::getTaxName))),
                            ArrayList::new));

            return prepareTreeviewParentChildHierarchy(taxonomyTreeviewDuplicatesRemoveList);
        } catch (Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForTaxName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForToxicityName service method, error is {} ", e);
        }

    }


    /**
     *
     * @param taxNameList
     * @param userJdbc
     * @return
     */
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTaxNames(List<String> taxNameList, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForSelectedTaxNames - Search Query - {}", taxonomyTreeviewSearchInQuery);

            String taxonomyTreeviewSearchInQueryToMerge = "with search_parents as ( select tax_id from target_search.taxonomy_all_childs where child_tax_id in (select tax_id from target_search.taxonomy_master where tax_name in ("+stringUtility.prepareInStringList(taxNameList)+") ) and child_tax_id <> tax_id ) select tax_id as taxId, parent_tax_id as parentTaxId, tax_name as taxName, tax_name as label, tree_level as treeLevel, display_order as displayOrder from target_search.taxonomy_master where coalesce(parent_tax_id, tax_id) in ( select * from search_parents )";

            LOGGER.info(" :: prepareTreeviewForSelectedTaxNames - Search Query - {}", taxonomyTreeviewSearchInQueryToMerge);

            taxonomyTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(taxonomyTreeviewSearchInQueryToMerge, new String[]{}, new BeanPropertyRowMapper<>(TaxonomyTreeviewParentChildNodeDTO.class));

            /*
                // Load All existing Parents from the master table
                List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentsDTOs = new ArrayList<>();
                taxonomyTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(taxonomyParentLevelQuery, new BeanPropertyRowMapper<>(TaxonomyTreeviewParentChildNodeDTO.class));

                // Merge both the responses
                taxonomyTreeviewParentChildNodeDTOs.addAll(taxonomyTreeviewParentsDTOs);

                // Remove Duplicates if any found
                List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewDuplicatesRemoveList = taxonomyTreeviewParentChildNodeDTOs.stream()
                        .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(TaxonomyTreeviewParentChildNodeDTO::getTaxName))),
                                ArrayList::new));
            */

            return prepareTreeviewParentChildHierarchy(taxonomyTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForSelectedTaxNames service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForSelectedTaxNames service method, error is {} ", e);
        }

    }

    /**
     *
     * @param taxName
     * @param userJdbc
     * @return
     */
    public List<TaxonomyMasterDTO> fetchTaxonomyTaxNameByContaining(String taxName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TaxonomyMasterDTO> taxonomyMasterDTOList = new ArrayList<>();

            LOGGER.info(" :: fetchTaxonomyTaxNameByContaining - Search Query - {} ", taxonomyTreeviewSearchByTaxnameQuery);
            taxonomyMasterDTOList = userJdbc.getJdbcTemplate().query(taxonomyTreeviewSearchByTaxnameQuery, new String[]{taxName.toLowerCase()}, new BeanPropertyRowMapper<>(TaxonomyMasterDTO.class));

            return taxonomyMasterDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchTaxonomyTaxNameByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchTaxonomyTaxNameByContaining service method, error is {} ", e);
        }

    }


    /**
     *
     * @param userJdbc
     * @return
     */
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForParentLevel - Search Query - {} ", taxonomyParentLevelQuery);
            taxonomyTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(taxonomyParentLevelQuery, new BeanPropertyRowMapper<>(TaxonomyTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(taxonomyTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e);
        }
    }


    /**
     *
     * @param taxonomyTreeviewParentChildNodeDTOs
     * @return
     */
    public List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchyOld(List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs) {

        final List<TaxonomyTreeviewParentChildNodeDTO> copyList = new ArrayList<>(taxonomyTreeviewParentChildNodeDTOs);

        copyList.forEach(element -> {
            taxonomyTreeviewParentChildNodeDTOs
                    .stream()
                    .filter(parent -> parent.getTaxId() == element.getParentTaxId())
                    .findAny()
                    .ifPresent(parent -> {
                        if (parent.getChildren() == null) {
                            parent.children = new ArrayList<>();
                        }
                        parent.children.add(element);
                        taxonomyTreeviewParentChildNodeDTOs.remove(element);
                    });
        });

        return copyList;
    }

    /**
     *
     * @param taxonomyTreeviewParentChildNodeDTOs
     * @return
     */
    private List<TaxonomyTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchy(List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOs) {

        Map<Long, Integer> map=new HashMap();
        TaxonomyTreeviewParentChildNodeDTO node = null;
        List<TaxonomyTreeviewParentChildNodeDTO> roots = new ArrayList<>();

        for (int i = 0; i < taxonomyTreeviewParentChildNodeDTOs.size(); i += 1) {
            map.put(taxonomyTreeviewParentChildNodeDTOs.get(i).getTaxId(), i);
            taxonomyTreeviewParentChildNodeDTOs.get(i).children = new ArrayList<>();
        }

        for (int i = 0; i < taxonomyTreeviewParentChildNodeDTOs.size(); i += 1) {
            node = taxonomyTreeviewParentChildNodeDTOs.get(i);
            if (node.getParentTaxId() != null) {

                if(Objects.nonNull(map.get(node.getParentTaxId())))
                    taxonomyTreeviewParentChildNodeDTOs.get(map.get(node.getParentTaxId())).children.add(node);

            } else {
                roots.add(node);
            }
        }

        return roots;
    }

}

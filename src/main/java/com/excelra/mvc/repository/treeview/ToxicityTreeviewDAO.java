package com.excelra.mvc.repository.treeview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.treeview.ToxicityTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.ToxicityOntologyMasterDTO;
import com.excelra.mvc.model.treeview.ToxicityTreeviewDTO;
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
 * This is Toxicity Treeview DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class ToxicityTreeviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToxicityTreeviewDAO.class);

    @Value("${toxicity.treeview.default.query}")
    private String toxicityOntologyDefaultQuery;

    @Value("${toxicity.treeview.bytoxicityontologyid.query}")
    private String toxicityOntologyByIdQuery;

    @Value("${toxicity.treeview.search.query}")
    private String toxicityOntologySearchQuery;

    @Value("${toxicity.treeview.parentlevel.query}")
    private String toxicityOntologyParentLevelQuery;

    @Value("${toxicity.treeview.search.bytoxicityname.query}")
    private String toxicityTreeviewSearchByToxicitynameQuery;

    @Value("${toxicity.treeview.search.in.query}")
    private String toxicityTreeviewSearchInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param ontologyId
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<ToxicityTreeviewDTO> prepareTreeviewForNode(Long ontologyId, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<ToxicityTreeviewDTO> toxicityTreeviewDTOList = new ArrayList<>();

            if(ontologyId == 0) {
                LOGGER.info(" :: prepareTreeviewForNode - Default Query - {} ", toxicityOntologyDefaultQuery);
                toxicityTreeviewDTOList = userJdbc.getJdbcTemplate().query(toxicityOntologyDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(ToxicityTreeviewDTO.class));
            } else {
                LOGGER.info(" :: prepareTreeviewForNode - Toxicity OntologyId Query - {} ", toxicityOntologyByIdQuery);
                toxicityTreeviewDTOList = userJdbc.getJdbcTemplate().query(toxicityOntologyByIdQuery, new Long[]{ ontologyId }, new BeanPropertyRowMapper<>(ToxicityTreeviewDTO.class));
            }

            return toxicityTreeviewDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForNode service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForNode service method, error is {} ", e);
        }
    }

    /**
     *
     * @param toxicityName
     * @param userJdbc
     * @return
     */
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForToxicityName(String toxicityName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForToxicityName - Search Query - {} ", toxicityOntologySearchQuery);
            toxicityTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(toxicityOntologySearchQuery, new String[]{toxicityName.toLowerCase(), toxicityName.toLowerCase()}, new BeanPropertyRowMapper<>(ToxicityTreeviewParentChildNodeDTO.class));

            // Load All existing Parents from the master table
            List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentsDTOs = new ArrayList<>();
            toxicityTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(toxicityOntologyParentLevelQuery, new BeanPropertyRowMapper<>(ToxicityTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            toxicityTreeviewParentChildNodeDTOs.addAll(toxicityTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewDuplicatesRemoveList = toxicityTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ToxicityTreeviewParentChildNodeDTO::getToxicityName))),
                            ArrayList::new));

            return prepareTreeviewParentChildHierarchy(toxicityTreeviewDuplicatesRemoveList);
        } catch (Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForToxicityName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForToxicityName service method, error is {} ", e);
        }

    }

    /**
     *
     * @param toxicityNameList
     * @param userJdbc
     * @return
     */
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForSelectedToxicityNames(List<String> toxicityNameList, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForSelectedToxicityNames - Search Query - {}", toxicityTreeviewSearchInQuery);

            String toxicityTreeviewSearchInQueryToMerge = "with search_parents as ( select toxicity_ontology_id from target_search.toxicity_ontology_all_childs where child_toxicity_ontology_id in (select toxicity_ontology_id from target_search.toxicity_ontology_master where toxicity_name in ("+stringUtility.prepareInStringList(toxicityNameList)+") ) and child_toxicity_ontology_id <> toxicity_ontology_id ) select toxicity_ontology_id as toxicityOntologyId, parent_toxicity_ontology_id as parentToxicityOntologyId, toxicity_name as toxicityName, toxicity_name as label, tree_level as treeLevel, display_order as displayOrder from target_search.toxicity_ontology_master where coalesce(parent_toxicity_ontology_id, toxicity_ontology_id) in ( select * from search_parents )";

            LOGGER.info(" :: prepareTreeviewForSelectedToxicityNames - Search Query - {}", toxicityTreeviewSearchInQueryToMerge);

            toxicityTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(toxicityTreeviewSearchInQueryToMerge, new String[]{}, new BeanPropertyRowMapper<>(ToxicityTreeviewParentChildNodeDTO.class));

            /*
                // Load All existing Parents from the master table
                List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentsDTOs = new ArrayList<>();
                toxicityTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(toxicityOntologyParentLevelQuery, new BeanPropertyRowMapper<>(ToxicityTreeviewParentChildNodeDTO.class));

                // Merge both the responses
                toxicityTreeviewParentChildNodeDTOs.addAll(toxicityTreeviewParentsDTOs);

                // Remove Duplicates if any found
                List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewDuplicatesRemoveList = toxicityTreeviewParentChildNodeDTOs.stream()
                        .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ToxicityTreeviewParentChildNodeDTO::getToxicityName))),
                                ArrayList::new));
            */

            return prepareTreeviewParentChildHierarchy(toxicityTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForSelectedToxicityNames service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForSelectedToxicityNames service method, error is {} ", e);
        }

    }

    /**
     *
     * @param toxicityName
     * @param userJdbc
     * @return
     */
    public List<ToxicityOntologyMasterDTO> fetchToxicityOntologyToxicityNameByContaining(String toxicityName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<ToxicityOntologyMasterDTO> toxicityOntologyMasterDTOList = new ArrayList<>();

            LOGGER.info(" :: fetchToxicityOntologyToxicityNameByContaining - Search Query - {} ", toxicityTreeviewSearchByToxicitynameQuery);
            toxicityOntologyMasterDTOList = userJdbc.getJdbcTemplate().query(toxicityTreeviewSearchByToxicitynameQuery, new String[]{toxicityName.toLowerCase()}, new BeanPropertyRowMapper<>(ToxicityOntologyMasterDTO.class));

            return toxicityOntologyMasterDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchToxicityOntologyToxicityNameByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchToxicityOntologyToxicityNameByContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForParentLevel - Search Query - {} ", toxicityOntologyParentLevelQuery);
            toxicityTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(toxicityOntologyParentLevelQuery, new BeanPropertyRowMapper<>(ToxicityTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(toxicityTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e);
        }
    }

    /**
     *
     * @param toxicityTreeviewParentChildNodeDTOs
     * @return
     */
    public List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchyOld(List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs) {

        final List<ToxicityTreeviewParentChildNodeDTO> copyList = new ArrayList<>(toxicityTreeviewParentChildNodeDTOs);

        copyList.forEach(element -> {
            toxicityTreeviewParentChildNodeDTOs
                    .stream()
                    .filter(parent -> parent.getToxicityOntologyId() == element.getParentToxicityOntologyId())
                    .findAny()
                    .ifPresent(parent -> {
                        if (parent.getChildren() == null) {
                            parent.children = new ArrayList<>();
                        }
                        parent.children.add(element);
                        toxicityTreeviewParentChildNodeDTOs.remove(element);
                    });
        });

        return copyList;
    }

    /**
     *
     * @param toxicityTreeviewParentChildNodeDTOs
     * @return
     */
    private List<ToxicityTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchy(List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOs) {

        Map<Long, Integer> map=new HashMap();
        ToxicityTreeviewParentChildNodeDTO node = null;
        List<ToxicityTreeviewParentChildNodeDTO> roots = new ArrayList<>();

        for (int i = 0; i < toxicityTreeviewParentChildNodeDTOs.size(); i += 1) {
            map.put(toxicityTreeviewParentChildNodeDTOs.get(i).getToxicityOntologyId(), i);
            toxicityTreeviewParentChildNodeDTOs.get(i).children = new ArrayList<>();
        }

        for (int i = 0; i < toxicityTreeviewParentChildNodeDTOs.size(); i += 1) {
            node = toxicityTreeviewParentChildNodeDTOs.get(i);
            if (node.getParentToxicityOntologyId() != null) {

                if(Objects.nonNull(map.get(node.getParentToxicityOntologyId())))
                    toxicityTreeviewParentChildNodeDTOs.get(map.get(node.getParentToxicityOntologyId())).children.add(node);

            } else {
                roots.add(node);
            }
        }

        return roots;
    }

}

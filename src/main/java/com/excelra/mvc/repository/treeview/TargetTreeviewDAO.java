package com.excelra.mvc.repository.treeview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.treeview.TargetTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TargetOntologyMasterDTO;
import com.excelra.mvc.model.treeview.TargetTreeviewDTO;
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
 * This is Target Treeview DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class TargetTreeviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetTreeviewDAO.class);

    @Value("${target.treeview.default.query}")
    private String targetOntologyDefaultQuery;

    @Value("${target.treeview.byontologyid.query}")
    private String targetOntologyByIdQuery;

    @Value("${target.treeview.search.query}")
    private String targetOntologySearchQuery;

    @Value("${target.treeview.parentlevel.query}")
    private String targetOntologyParentLevelQuery;

    @Value("${target.treeview.search.bytargetname.query}")
    private String targetTreeviewSearchByTargetnameQuery;

    @Value("${target.treeview.search.in.query}")
    private String targetTreeviewSearchInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param ontologyId
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<TargetTreeviewDTO> prepareTreeviewForNode(Long ontologyId, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TargetTreeviewDTO> targetTreeviewDTOList = new ArrayList<>();

            if(ontologyId == 0) {
                LOGGER.info(" :: prepareTreeviewForNode - Default Query - {} ", targetOntologyDefaultQuery);
                targetTreeviewDTOList = userJdbc.getJdbcTemplate().query(targetOntologyDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(TargetTreeviewDTO.class));
            } else {
                LOGGER.info(" :: prepareTreeviewForNode - OntologyId Query - {} ", targetOntologyByIdQuery);
                targetTreeviewDTOList = userJdbc.getJdbcTemplate().query(targetOntologyByIdQuery, new Long[]{ ontologyId }, new BeanPropertyRowMapper<>(TargetTreeviewDTO.class));
            }

            return targetTreeviewDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForNode service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForNode service method, error is {} ", e);
        }
    }

    /**
     *
     * @param targetName
     * @param userJdbc
     * @return
     */
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForTargetName(String targetName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForTargetName - Search Query - {} ", targetOntologySearchQuery);
            targetTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(targetOntologySearchQuery, new String[]{targetName.toLowerCase(), targetName.toLowerCase()}, new BeanPropertyRowMapper<>(TargetTreeviewParentChildNodeDTO.class));

            // Load All existing Parents from the master table
            List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentsDTOs = new ArrayList<>();
            targetTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(targetOntologyParentLevelQuery, new BeanPropertyRowMapper<>(TargetTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            targetTreeviewParentChildNodeDTOs.addAll(targetTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<TargetTreeviewParentChildNodeDTO> targetTreeviewDuplicatesRemoveList = targetTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(TargetTreeviewParentChildNodeDTO::getTargetName))),
                            ArrayList::new));

            return prepareTreeviewParentChildHierarchy(targetTreeviewDuplicatesRemoveList);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForTargetName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForTargetName service method, error is {} ", e);
        }

    }

    /**
     *
     * @param targetNameList
     * @param userJdbc
     * @return
     */
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTargetNames(List<String> targetNameList, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForSelectedTargetNames - Search Query - {}", targetTreeviewSearchInQuery);

            String targetTreeviewSearchInQueryToMerge = "with search_parents as ( select target_ontology_id from target_search.target_ontology_all_childs where child_target_ontology_id in (select target_ontology_id from target_search.target_ontology_master where target_name in ("+stringUtility.prepareInStringList(targetNameList)+") ) and child_target_ontology_id <> target_ontology_id ) select target_ontology_id as ontologyId, parent_target_ontology_id as parentOntologyId, target_name as targetName, target_name as label, tree_level as treeLevel, display_order as displayOrder from target_search.target_ontology_master where coalesce(parent_target_ontology_id, target_ontology_id) in ( select * from search_parents )";

            LOGGER.info(" :: prepareTreeviewForSelectedTargetNames - Search Query - {}", targetTreeviewSearchInQueryToMerge);

            targetTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(targetTreeviewSearchInQueryToMerge, new String[]{}, new BeanPropertyRowMapper<>(TargetTreeviewParentChildNodeDTO.class));
/*

            // Load All existing Parents from the master table
            List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentsDTOs = new ArrayList<>();
            targetTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(targetOntologyParentLevelQuery, new BeanPropertyRowMapper<>(TargetTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            targetTreeviewParentChildNodeDTOs.addAll(targetTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<TargetTreeviewParentChildNodeDTO> targetTreeviewDuplicatesRemoveList = targetTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(TargetTreeviewParentChildNodeDTO::getTargetName))),
                            ArrayList::new));
*/

            return prepareTreeviewParentChildHierarchy(targetTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForSelectedTargetNames service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForSelectedTargetNames service method, error is {} ", e);
        }

    }

    /**
     *
     * @param targetName
     * @param userJdbc
     * @return
     */
    public List<TargetOntologyMasterDTO> fetchTargetOntologyTargetNameByContaining(String targetName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TargetOntologyMasterDTO> targetOntologyMasterDTOList = new ArrayList<>();

            LOGGER.info(" :: fetchTargetOntologyTargetNameByContaining - Search Query - {} ", targetTreeviewSearchByTargetnameQuery);
            targetOntologyMasterDTOList = userJdbc.getJdbcTemplate().query(targetTreeviewSearchByTargetnameQuery, new String[]{targetName.toLowerCase()}, new BeanPropertyRowMapper<>(TargetOntologyMasterDTO.class));

            return targetOntologyMasterDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchTargetOntologyTargetNameByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchTargetOntologyTargetNameByContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForParentLevel - Search Query - {} ", targetOntologyParentLevelQuery);
            targetTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(targetOntologyParentLevelQuery, new BeanPropertyRowMapper<>(TargetTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(targetTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e);
        }
    }

    /**
     *
     * @param targetTreeviewParentChildNodeDTOs
     * @return
     */
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchyOld(List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs) {

        final List<TargetTreeviewParentChildNodeDTO> copyList = new ArrayList<>(targetTreeviewParentChildNodeDTOs);

        copyList.forEach(element -> {
            targetTreeviewParentChildNodeDTOs
                    .stream()
                    .filter(parent -> parent.getOntologyId() == element.getParentOntologyId())
                    .findAny()
                    .ifPresent(parent -> {
                        if (parent.getChildren() == null) {
                            parent.children = new ArrayList<>();
                        }
                        parent.children.add(element);
                        targetTreeviewParentChildNodeDTOs.remove(element);
                    });
        });

        return copyList;
    }


    /**
     *
     * @param targetTreeviewParentChildNodeDTOs
     * @return
     */
    public List<TargetTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchy(List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOs) {

        Map<Long, Integer> map=new HashMap();
        TargetTreeviewParentChildNodeDTO node = null;
        List<TargetTreeviewParentChildNodeDTO> roots = new ArrayList<>();

        for (int i = 0; i < targetTreeviewParentChildNodeDTOs.size(); i += 1) {
            map.put(targetTreeviewParentChildNodeDTOs.get(i).getOntologyId(), i);
            targetTreeviewParentChildNodeDTOs.get(i).children = new ArrayList<>();
        }

        for (int i = 0; i < targetTreeviewParentChildNodeDTOs.size(); i += 1) {
            node = targetTreeviewParentChildNodeDTOs.get(i);
            if (node.getParentOntologyId() != null) {
                // System.out.println("====> "+map.get(node.getParentOntologyId()));

                if(Objects.nonNull(map.get(node.getParentOntologyId())))
                    targetTreeviewParentChildNodeDTOs.get(map.get(node.getParentOntologyId())).children.add(node);

            } else {
                roots.add(node);
            }
        }

        return roots;
    }


}

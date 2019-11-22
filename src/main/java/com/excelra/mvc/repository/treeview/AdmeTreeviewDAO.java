package com.excelra.mvc.repository.treeview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.treeview.AdmeTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.AdmeOntologyMasterDTO;
import com.excelra.mvc.model.treeview.AdmeTreeviewDTO;
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
 * This is Adme Treeview DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class AdmeTreeviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmeTreeviewDAO.class);

    @Value("${adme.treeview.default.query}")
    private String admeOntologyDefaultQuery;

    @Value("${adme.treeview.byadmeontologyid.query}")
    private String admeOntologyByIdQuery;

    @Value("${adme.treeview.search.query}")
    private String admeOntologySearchQuery;

    @Value("${adme.treeview.parentlevel.query}")
    private String admeOntologyParentLevelQuery;

    @Value("${adme.treeview.search.byadmename.query}")
    private String admeTreeviewSearchByAdmenameQuery;

    @Value("${adme.treeview.search.in.query}")
    private String admeTreeviewSearchInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param ontologyId
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<AdmeTreeviewDTO> prepareTreeviewForNode(Long ontologyId, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<AdmeTreeviewDTO> admeTreeviewDTOList = new ArrayList<>();

            if(ontologyId == 0) {
                LOGGER.info(" :: prepareTreeviewForNode - Default Query - {} ", admeOntologyDefaultQuery);
                admeTreeviewDTOList = userJdbc.getJdbcTemplate().query(admeOntologyDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(AdmeTreeviewDTO.class));
            } else {
                LOGGER.info(" :: prepareTreeviewForNode - Adme OntologyId Query - {} ", admeOntologyByIdQuery);
                admeTreeviewDTOList = userJdbc.getJdbcTemplate().query(admeOntologyByIdQuery, new Long[]{ ontologyId }, new BeanPropertyRowMapper<>(AdmeTreeviewDTO.class));
            }

            return admeTreeviewDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForNode service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForNode service method, error is {} ", e);
        }
    }

    /**
     *
     * @param admeName
     * @param userJdbc
     * @return
     */
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForAdmeName(String admeName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForAdmeName - Search Query - {} ", admeOntologySearchQuery);
            admeTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(admeOntologySearchQuery, new String[]{admeName.toLowerCase(), admeName.toLowerCase()}, new BeanPropertyRowMapper<>(AdmeTreeviewParentChildNodeDTO.class));

            // Load All existing Parents from the master table
            List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentsDTOs = new ArrayList<>();
            admeTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(admeOntologyParentLevelQuery, new BeanPropertyRowMapper<>(AdmeTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            admeTreeviewParentChildNodeDTOs.addAll(admeTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<AdmeTreeviewParentChildNodeDTO> admeTreeviewDuplicatesRemoveList = admeTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(AdmeTreeviewParentChildNodeDTO::getAdmeName))),
                            ArrayList::new));

            return prepareTreeviewParentChildHierarchy(admeTreeviewDuplicatesRemoveList);
        } catch (Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForAdmeName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForAdmeName service method, error is {} ", e);
        }

    }


    /**
     *
     * @param admeNameList
     * @param userJdbc
     * @return
     */
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForSelectedAdmeNames(List<String> admeNameList, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForSelectedAdmeNames - Search Query - {}", admeTreeviewSearchInQuery);

            String admeTreeviewSearchInQueryToMerge = "with search_parents as ( select adme_ontology_id from target_search.adme_ontology_all_childs where child_adme_ontology_id in (select adme_ontology_id from target_search.adme_ontology_master where adme_name in ("+stringUtility.prepareInStringList(admeNameList)+") ) and child_adme_ontology_id <> adme_ontology_id ) select adme_ontology_id as admeOntologyId, parent_adme_ontology_id as parentAdmeOntologyId, adme_name as admeName, adme_name as label, tree_level as treeLevel, display_order as displayOrder from target_search.adme_ontology_master where coalesce(parent_adme_ontology_id, adme_ontology_id) in ( select * from search_parents )";

            LOGGER.info(" :: prepareTreeviewForSelectedAdmeNames - Search Query - {}", admeTreeviewSearchInQueryToMerge);

            admeTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(admeTreeviewSearchInQueryToMerge, new String[]{}, new BeanPropertyRowMapper<>(AdmeTreeviewParentChildNodeDTO.class));

            /*
                // Load All existing Parents from the master table
                List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentsDTOs = new ArrayList<>();
                admeTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(admeOntologyParentLevelQuery, new BeanPropertyRowMapper<>(AdmeTreeviewParentChildNodeDTO.class));

                // Merge both the responses
                admeTreeviewParentChildNodeDTOs.addAll(admeTreeviewParentsDTOs);

                // Remove Duplicates if any found
                List<AdmeTreeviewParentChildNodeDTO> admeTreeviewDuplicatesRemoveList = admeTreeviewParentChildNodeDTOs.stream()
                        .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(AdmeTreeviewParentChildNodeDTO::getAdmeName))),
                                ArrayList::new));
            */

            return prepareTreeviewParentChildHierarchy(admeTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForSelectedAdmeNames service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForSelectedAdmeNames service method, error is {} ", e);
        }

    }

    /**
     *
     * @param admeName
     * @param userJdbc
     * @return
     */
    public List<AdmeOntologyMasterDTO> fetchAdmeOntologyAdmeNameByContaining(String admeName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<AdmeOntologyMasterDTO> admeOntologyMasterDTOList = new ArrayList<>();

            LOGGER.info(" :: fetchToxicityOntologyToxicityNameByContaining - Search Query - {} ", admeTreeviewSearchByAdmenameQuery);
            admeOntologyMasterDTOList = userJdbc.getJdbcTemplate().query(admeTreeviewSearchByAdmenameQuery, new String[]{admeName.toLowerCase()}, new BeanPropertyRowMapper<>(AdmeOntologyMasterDTO.class));

            return admeOntologyMasterDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchAdmeOntologyAdmeNameByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchAdmeOntologyAdmeNameByContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForParentLevel - Search Query - {} ", admeOntologyParentLevelQuery);
            admeTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(admeOntologyParentLevelQuery, new BeanPropertyRowMapper<>(AdmeTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(admeTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e);
        }
    }

    /**
     *
     * @param admeTreeviewParentChildNodeDTOs
     * @return
     */
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchyOld(List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs) {

        final List<AdmeTreeviewParentChildNodeDTO> copyList = new ArrayList<>(admeTreeviewParentChildNodeDTOs);

        copyList.forEach(element -> {
            admeTreeviewParentChildNodeDTOs
                    .stream()
                    .filter(parent -> parent.getAdmeOntologyId() == element.getParentAdmeOntologyId())
                    .findAny()
                    .ifPresent(parent -> {
                        if (parent.getChildren() == null) {
                            parent.children = new ArrayList<>();
                        }
                        parent.children.add(element);
                        admeTreeviewParentChildNodeDTOs.remove(element);
                    });
        });

        return copyList;
    }

    /**
     *
     * @param admeTreeviewParentChildNodeDTOs
     * @return
     */
    public List<AdmeTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchy(List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOs) {

        Map<Long, Integer> map=new HashMap();
        AdmeTreeviewParentChildNodeDTO node = null;
        List<AdmeTreeviewParentChildNodeDTO> roots = new ArrayList<>();

        for (int i = 0; i < admeTreeviewParentChildNodeDTOs.size(); i += 1) {
            map.put(admeTreeviewParentChildNodeDTOs.get(i).getAdmeOntologyId(), i);
            admeTreeviewParentChildNodeDTOs.get(i).children = new ArrayList<>();
        }

        for (int i = 0; i < admeTreeviewParentChildNodeDTOs.size(); i += 1) {
            node = admeTreeviewParentChildNodeDTOs.get(i);
            if (node.getParentAdmeOntologyId() != null) {

                if(Objects.nonNull(map.get(node.getParentAdmeOntologyId())))
                    admeTreeviewParentChildNodeDTOs.get(map.get(node.getParentAdmeOntologyId())).children.add(node);

            } else {
                roots.add(node);
            }
        }

        return roots;
    }

}

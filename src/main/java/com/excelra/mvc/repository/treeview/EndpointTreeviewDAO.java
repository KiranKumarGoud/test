package com.excelra.mvc.repository.treeview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.treeview.EndpointTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.EndpointTreeviewDTO;
import com.excelra.mvc.model.treeview.EndpointMasterDTO;
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
 * This is Activity Endpoint Treeview DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class EndpointTreeviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToxicityTreeviewDAO.class);

    @Value("${endpoint.treeview.default.query}")
    private String endpointDefaultQuery;

    @Value("${endpoint.treeview.byendpointid.query}")
    private String endpointByIdQuery;

    @Value("${endpoint.treeview.search.query}")
    private String endpointSearchQuery;

    @Value("${endpoint.treeview.parentlevel.query}")
    private String endpointParentLevelQuery;

    @Value("${endpoint.treeview.search.byendpointname.query}")
    private String endpointTreeviewSearchByEndpointNameQuery;

    @Value("${endpoint.treeview.search.in.query}")
    private String endpointTreeviewSearchInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param endpointId
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<EndpointTreeviewDTO> prepareTreeviewForNode(Long endpointId, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<EndpointTreeviewDTO> endpointTreeviewDTOList = new ArrayList<>();

            if(endpointId == 0) {
                LOGGER.info(" :: prepareTreeviewForNode - Default Query - {} ", endpointDefaultQuery);
                endpointTreeviewDTOList = userJdbc.getJdbcTemplate().query(endpointDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(EndpointTreeviewDTO.class));
            } else {
                LOGGER.info(" :: prepareTreeviewForNode - Toxicity OntologyId Query - {} ", endpointByIdQuery);
                endpointTreeviewDTOList = userJdbc.getJdbcTemplate().query(endpointByIdQuery, new Long[]{ endpointId }, new BeanPropertyRowMapper<>(EndpointTreeviewDTO.class));
            }

            return endpointTreeviewDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForNode service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForNode service method, error is {} ", e);
        }
    }

    /**
     *
     * @param endpointName
     * @param userJdbc
     * @return
     */
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForEndpointName(String endpointName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForEndpointName - Search Query - {} ", endpointSearchQuery);
            endpointTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(endpointSearchQuery, new String[]{endpointName.toLowerCase(), endpointName.toLowerCase()}, new BeanPropertyRowMapper<>(EndpointTreeviewParentChildNodeDTO.class));

            // Load All existing Parents from the master table
            List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentsDTOs = new ArrayList<>();
            endpointTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(endpointParentLevelQuery, new BeanPropertyRowMapper<>(EndpointTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            endpointTreeviewParentChildNodeDTOs.addAll(endpointTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewDuplicatesRemoveList = endpointTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(EndpointTreeviewParentChildNodeDTO::getEndpointName))),
                            ArrayList::new));

            return prepareTreeviewParentChildHierarchy(endpointTreeviewDuplicatesRemoveList);
        } catch (Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForEndpointName service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForToxicityName service method, error is {} ", e);
        }

    }

    /**
     *
     * @param endpointNameList
     * @param userJdbc
     * @return
     */
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForSelectedEndpointNames(List<String> endpointNameList, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForSelectedEndpointNames - Search Query - {}", endpointTreeviewSearchInQuery);

            String endpointTreeviewSearchInQueryToMerge = "with search_parents as ( select endpoint_id from target_search.endpoint_all_childs where child_endpoint_id in (select endpoint_id from target_search.endpoint_master where endpoint_name in ("+stringUtility.prepareInStringList(endpointNameList)+") ) and child_endpoint_id <> endpoint_id ) select endpoint_id as endpointId, parent_endpoint_id as parentEndpointId, endpoint_name as endpointName, endpoint_name as label, tree_level as treeLevel, display_order as displayOrder from target_search.endpoint_master where coalesce(parent_endpoint_id, endpoint_id) in ( select * from search_parents )";

            LOGGER.info(" :: prepareTreeviewForSelectedEndpointNames - Search Query - {}", endpointTreeviewSearchInQueryToMerge);

            endpointTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(endpointTreeviewSearchInQueryToMerge, new String[]{}, new BeanPropertyRowMapper<>(EndpointTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(endpointTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForSelectedEndpointNames service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForSelectedEndpointNames service method, error is {} ", e);
        }

    }

    /**
     *
     * @param endpointName
     * @param userJdbc
     * @return
     */
    public List<EndpointMasterDTO> fetchEndpointEndpointNameByContaining(String endpointName, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<EndpointMasterDTO> endpointMasterDTOList = new ArrayList<>();

            LOGGER.info(" :: fetchEndpointEndpointNameByContaining - Search Query - {} ", endpointTreeviewSearchByEndpointNameQuery);
            endpointMasterDTOList = userJdbc.getJdbcTemplate().query(endpointTreeviewSearchByEndpointNameQuery, new String[]{endpointName.toLowerCase()}, new BeanPropertyRowMapper<>(EndpointMasterDTO.class));

            return endpointMasterDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchEndpointEndpointNameByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchEndpointEndpointNameByContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForParentLevel - Search Query - {} ", endpointParentLevelQuery);
            endpointTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(endpointParentLevelQuery, new BeanPropertyRowMapper<>(EndpointTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(endpointTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e);
        }
    }

    /**
     *
     * @param endpointTreeviewParentChildNodeDTOs
     * @return
     */
    public List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchyOld(List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs) {

        final List<EndpointTreeviewParentChildNodeDTO> copyList = new ArrayList<>(endpointTreeviewParentChildNodeDTOs);

        copyList.forEach(element -> {
            endpointTreeviewParentChildNodeDTOs
                    .stream()
                    .filter(parent -> parent.getEndpointId() == element.getParentEndpointId())
                    .findAny()
                    .ifPresent(parent -> {
                        if (parent.getChildren() == null) {
                            parent.children = new ArrayList<>();
                        }
                        parent.children.add(element);
                        endpointTreeviewParentChildNodeDTOs.remove(element);
                    });
        });

        return copyList;
    }

    /**
     *
     * @param endpointTreeviewParentChildNodeDTOs
     * @return
     */
    private List<EndpointTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchy(List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOs) {

        Map<Long, Integer> map=new HashMap();
        EndpointTreeviewParentChildNodeDTO node = null;
        List<EndpointTreeviewParentChildNodeDTO> roots = new ArrayList<>();

        for (int i = 0; i < endpointTreeviewParentChildNodeDTOs.size(); i += 1) {
            map.put(endpointTreeviewParentChildNodeDTOs.get(i).getEndpointId(), i);
            endpointTreeviewParentChildNodeDTOs.get(i).children = new ArrayList<>();
        }

        for (int i = 0; i < endpointTreeviewParentChildNodeDTOs.size(); i += 1) {
            node = endpointTreeviewParentChildNodeDTOs.get(i);
            if (node.getParentEndpointId() != null) {

                if(Objects.nonNull(map.get(node.getParentEndpointId())))
                    endpointTreeviewParentChildNodeDTOs.get(map.get(node.getParentEndpointId())).children.add(node);

            } else {
                roots.add(node);
            }
        }

        return roots;
    }
}

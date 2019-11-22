package com.excelra.mvc.repository.treeview;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.search.treeview.IndicationTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.IndicationOntologyMasterDTO;
import com.excelra.mvc.model.treeview.IndicationTreeviewDTO;
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
 * This is Indication Treeview DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class IndicationTreeviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationTreeviewDAO.class);

    @Value("${indication.treeview.default.query}")
    private String indicationDefaultQuery;

    @Value("${indication.treeview.byicd10code.query}")
    private String indicationByIcd10CodeQuery;

    @Value("${indication.treeview.search.query}")
    private String indicationSearchQuery;

    @Value("${indication.treeview.parentlevel.query}")
    private String indicationOntologyParentLevelQuery;

    @Value("${indication.treeview.search.bytherapeuticuse.query}")
    private String indicationTreeviewSearchByTherapeuticUseQuery;

    @Value("${indication.treeview.search.in.query}")
    private String indicationTreeviewSearchInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param icd10Code
     * @param userJdbc
     * @return
     */
    @Transactional
    public List<IndicationTreeviewDTO> prepareTreeviewForNode(String icd10Code, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<IndicationTreeviewDTO> indicationTreeviewDTOList = new ArrayList<>();

            if(icd10Code.equalsIgnoreCase("null")) {
                LOGGER.info(" :: prepareTreeviewForNode - Default Query - {} ", indicationDefaultQuery);
                indicationTreeviewDTOList = userJdbc.getJdbcTemplate().query(indicationDefaultQuery, new String[]{}, new BeanPropertyRowMapper<>(IndicationTreeviewDTO.class));
            } else {
                LOGGER.info(" :: prepareTreeviewForNode - OntologyId Query - {} ", indicationByIcd10CodeQuery);
                indicationTreeviewDTOList = userJdbc.getJdbcTemplate().query(indicationByIcd10CodeQuery, new String[]{ icd10Code }, new BeanPropertyRowMapper<>(IndicationTreeviewDTO.class));
            }

            return indicationTreeviewDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForNode service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForNode service method, error is {} ", e);
        }
    }

    /**
     *
     * @param therapeuticUse
     * @param userJdbc
     * @return
     */
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForTherapeuticuse(String therapeuticUse, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<IndicationTreeviewParentChildNodeDTO>indicationTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForTargetName - Search Query - {} ", indicationSearchQuery);
            indicationTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(indicationSearchQuery, new String[]{therapeuticUse.toLowerCase(), therapeuticUse.toLowerCase()}, new BeanPropertyRowMapper<>(IndicationTreeviewParentChildNodeDTO.class));

            // Load All existing Parents from the master table
            List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentsDTOs = new ArrayList<>();
            indicationTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(indicationOntologyParentLevelQuery, new BeanPropertyRowMapper<>(IndicationTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            indicationTreeviewParentChildNodeDTOs.addAll(indicationTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewDuplicatesRemoveList = indicationTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(IndicationTreeviewParentChildNodeDTO::getTherapeuticUse))),
                            ArrayList::new));

            return prepareTreeviewParentChildHierarchy(indicationTreeviewDuplicatesRemoveList);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForTherapeuticuse service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForTherapeuticuse service method, error is {} ", e);
        }

    }

    /**
     *
     * @param therapeuticUseList
     * @param userJdbc
     * @return
     */
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForSelectedTherapeuticUses(List<String> therapeuticUseList, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForSelectedTherapeuticUses - Search Query - {}", indicationTreeviewSearchInQuery);

            String indicationTreeviewSearchInQueryToMerge = "with search_parents as ( select icd10_code from target_search.indication_ontology_all_childs where child_icd10_code in (select icd10_code from target_search.indication_ontology_master where therapeutic_use in ("+stringUtility.prepareInStringList(therapeuticUseList)+") ) and child_icd10_code <> icd10_code ) select icd10_code as icd10Code, parent_icd10_code as parentIcd10Code, therapeutic_use as therapeuticUse, therapeutic_use as label, tree_level as treeLevel, display_order as displayOrder from target_search.indication_ontology_master where coalesce(parent_icd10_code, icd10_code) in ( select * from search_parents )";

            LOGGER.info(" :: prepareTreeviewForSelectedTherapeuticUses - Search Query - {}", indicationTreeviewSearchInQueryToMerge);

            indicationTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(indicationTreeviewSearchInQueryToMerge, new String[]{}, new BeanPropertyRowMapper<>(IndicationTreeviewParentChildNodeDTO.class));
/*

            // Load All existing Parents from the master table
            List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentsDTOs = new ArrayList<>();
            indicationTreeviewParentsDTOs = userJdbc.getJdbcTemplate().query(indicationOntologyParentLevelQuery, new BeanPropertyRowMapper<>(IndicationTreeviewParentChildNodeDTO.class));

            // Merge both the responses
            indicationTreeviewParentChildNodeDTOs.addAll(indicationTreeviewParentsDTOs);

            // Remove Duplicates if any found
            List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewDuplicatesRemoveList = indicationTreeviewParentChildNodeDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(IndicationTreeviewParentChildNodeDTO::getTherapeuticUse))),
                            ArrayList::new));
*/

            return prepareTreeviewParentChildHierarchy(indicationTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForSelectedTherapeuticUses service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForSelectedTherapeuticUses service method, error is {} ", e);
        }

    }

    /**
     *
     * @param therapeuticUse
     * @param userJdbc
     * @return
     */
    public List<IndicationOntologyMasterDTO> fetchIndicationOntologyTherapeuticUseByContaining(String therapeuticUse, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<IndicationOntologyMasterDTO> indicationOntologyMasterDTOList = new ArrayList<>();

            LOGGER.info(" :: fetchIndicationOntologyTherapeuticUseByContaining - Search Query - {} ", indicationTreeviewSearchByTherapeuticUseQuery);
            indicationOntologyMasterDTOList = userJdbc.getJdbcTemplate().query(indicationTreeviewSearchByTherapeuticUseQuery, new String[]{therapeuticUse.toLowerCase()}, new BeanPropertyRowMapper<>(IndicationOntologyMasterDTO.class));

            return indicationOntologyMasterDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchIndicationOntologyTherapeuticUseByContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchIndicationOntologyTherapeuticUseByContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewForParentLevel(UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOs = new ArrayList<>();

            LOGGER.info(" :: prepareTreeviewForParentLevel - Search Query - {} ", indicationOntologyParentLevelQuery);
            indicationTreeviewParentChildNodeDTOs = userJdbc.getJdbcTemplate().query(indicationOntologyParentLevelQuery, new BeanPropertyRowMapper<>(IndicationTreeviewParentChildNodeDTO.class));

            return prepareTreeviewParentChildHierarchy(indicationTreeviewParentChildNodeDTOs);
        } catch(Exception e) {
            LOGGER.error(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing prepareTreeviewForParentLevel service method, error is {} ", e);
        }
    }

    /**
     *
      * @param indicationTreeviewParentChildNodeDTOs
     * @return
     */
    public List<IndicationTreeviewParentChildNodeDTO> prepareTreeviewParentChildHierarchy(List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOs) {

        Map<String, Integer> map=new HashMap();
        IndicationTreeviewParentChildNodeDTO node = null;
        List<IndicationTreeviewParentChildNodeDTO> roots = new ArrayList<>();

        for (int i = 0; i < indicationTreeviewParentChildNodeDTOs.size(); i += 1) {
            map.put(indicationTreeviewParentChildNodeDTOs.get(i).getIcd10Code(), i);
            indicationTreeviewParentChildNodeDTOs.get(i).children = new ArrayList<>();
        }

        for (int i = 0; i < indicationTreeviewParentChildNodeDTOs.size(); i += 1) {
            node = indicationTreeviewParentChildNodeDTOs.get(i);
            if (node.getParentIcd10Code() != null) {

                if(Objects.nonNull(map.get(node.getParentIcd10Code())))
                    indicationTreeviewParentChildNodeDTOs.get(map.get(node.getParentIcd10Code())).children.add(node);

            } else {
                roots.add(node);
            }
        }

        return roots;
    }
}

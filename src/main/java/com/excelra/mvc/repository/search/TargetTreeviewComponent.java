package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.treeview.TargetTreeviewDTO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Target Treeview search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class TargetTreeviewComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetTreeviewComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param targetTreeviewDTO
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(TargetTreeviewDTO targetTreeviewDTO) {

        Map<String, String> targetQueries = new HashMap<>();

        String targetInnerQuery = StringUtils.EMPTY;
        String targetOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(!targetTreeviewDTO.getTargetOntologyId().isEmpty()) {

            targetInnerQuery = " stdname_ids && (select array_agg(stdname_id) from target_search.target_ontology_all_childs " +
                    "where target_ontology_id in ("+stringUtility.prepareInNumbericList(targetTreeviewDTO.getTargetOntologyId())+")) ";

            targetOuterQuery = " (stdname_id in (select stdname_id from target_search.target_ontology_all_childs " +
                    "where target_ontology_id in ("+stringUtility.prepareInNumbericList(targetTreeviewDTO.getTargetOntologyId())+"))) ";

            orStatus = " stdname_id in (select child_target_ontology_id from target_search.target_ontology_all_childs where " +
                    "target_ontology_id in ("+stringUtility.prepareInNumbericList(targetTreeviewDTO.getTargetOntologyId())+")) ";
        }

        targetQueries.put("Inner", targetInnerQuery);
        targetQueries.put("Outer", targetOuterQuery);
        targetQueries.put("psw", StringUtils.EMPTY);
        targetQueries.put("orstatus", orStatus);

        return targetQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkTargetTreeviewCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

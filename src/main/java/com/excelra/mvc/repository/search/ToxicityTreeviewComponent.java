package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.treeview.ToxicityTreeviewDTO;
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
 * Toxicity Treeview search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class ToxicityTreeviewComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToxicityTreeviewComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param toxicityTreeviewDTO
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(ToxicityTreeviewDTO toxicityTreeviewDTO) {

        Map<String, String> toxicityQueries = new HashMap<>();

        String toxicityInnerQuery = StringUtils.EMPTY;
        String toxicityOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(!toxicityTreeviewDTO.getToxicityOntologyId().isEmpty()) {

            toxicityInnerQuery = " toxicity_ontology_ids && (select array_agg(child_toxicity_ontology_id) from target_search.toxicity_ontology_all_childs " +
                    "where toxicity_ontology_id in ("+stringUtility.prepareInNumbericList(toxicityTreeviewDTO.getToxicityOntologyId())+")) ";

            toxicityOuterQuery = " ( toxicity_ontology_id in (select child_toxicity_ontology_id from target_search.toxicity_ontology_all_childs " +
                    "where toxicity_ontology_id in ("+stringUtility.prepareInNumbericList(toxicityTreeviewDTO.getToxicityOntologyId())+")) )";

            orStatus = " toxicity_ontology_id in (select child_toxicity_ontology_id from target_search.toxicity_ontology_all_childs " +
                    "where toxicity_ontology_id in ("+stringUtility.prepareInNumbericList(toxicityTreeviewDTO.getToxicityOntologyId())+")) ";
        }

        toxicityQueries.put("Inner", toxicityInnerQuery);
        toxicityQueries.put("Outer", toxicityOuterQuery);
        toxicityQueries.put("psw", StringUtils.EMPTY);
        toxicityQueries.put("orstatus", orStatus);

        return toxicityQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkToxicityTreeviewCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.treeview.AdmeTreeviewDTO;
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
 * Adme Treeview search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class AdmeTreeviewComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmeTreeviewComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param admeTreeviewDTO
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(AdmeTreeviewDTO admeTreeviewDTO) {

        Map<String, String> admeQueries = new HashMap<>();

        String admeInnerQuery = StringUtils.EMPTY;
        String admeOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(!admeTreeviewDTO.getAdmeOntologyId().isEmpty()) {

            admeInnerQuery = " adme_ontology_ids && (select array_agg(child_adme_ontology_id) from target_search.adme_ontology_all_childs " +
                    "where adme_ontology_id in ("+stringUtility.prepareInNumbericList(admeTreeviewDTO.getAdmeOntologyId())+")) ";

            admeOuterQuery = " ( adme_ontology_id in (select child_adme_ontology_id from target_search.adme_ontology_all_childs " +
                    "where adme_ontology_id in ("+stringUtility.prepareInNumbericList(admeTreeviewDTO.getAdmeOntologyId())+")) )";

            orStatus = " adme_ontology_id in (select child_adme_ontology_id from target_search.adme_ontology_all_childs " +
                    "where adme_ontology_id in ("+stringUtility.prepareInNumbericList(admeTreeviewDTO.getAdmeOntologyId())+")) ";
        }

        admeQueries.put("Inner", admeInnerQuery);
        admeQueries.put("Outer", admeOuterQuery);
        admeQueries.put("psw", StringUtils.EMPTY);
        admeQueries.put("orstatus", orStatus);

        return admeQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkAdmeTreeviewCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

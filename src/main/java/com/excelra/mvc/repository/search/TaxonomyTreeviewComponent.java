package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.treeview.TaxonomyTreeviewDTO;
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
 * Taxonomy Treeview search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class TaxonomyTreeviewComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxonomyTreeviewComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param taxonomyTreeviewDTO
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(TaxonomyTreeviewDTO taxonomyTreeviewDTO) {

        Map<String, String> taxonomyQueries = new HashMap<>();

        String taxonomyInnerQuery = StringUtils.EMPTY;
        String taxonomyOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(!taxonomyTreeviewDTO.getTaxId().isEmpty()) {

            taxonomyInnerQuery = " tax_ids && (select array_agg(child_tax_id) from target_search.taxonomy_all_childs " +
                    "where tax_id in ("+stringUtility.prepareInNumbericList(taxonomyTreeviewDTO.getTaxId())+")) ";

            taxonomyOuterQuery = " source_id in (select source_id from target_search.taxonomy_all_childs tac " +
                    "inner join target_search.source_classification_master scm on (scm.tax_id = tac.child_tax_id) " +
                    "where tac.tax_id in ("+stringUtility.prepareInNumbericList(taxonomyTreeviewDTO.getTaxId())+")) ";

            orStatus = " source_id in (select source_id from target_search.taxonomy_all_childs tac inner join target_search.source_classification_master " +
                    "scm on (scm.tax_id = tac.child_tax_id) where tac.tax_id in ("+stringUtility.prepareInNumbericList(taxonomyTreeviewDTO.getTaxId())+")) ";
        }

        taxonomyQueries.put("Inner", taxonomyInnerQuery);
        taxonomyQueries.put("Outer", taxonomyOuterQuery);
        taxonomyQueries.put("psw", StringUtils.EMPTY);
        taxonomyQueries.put("orstatus", orStatus);

        return taxonomyQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkTaxonomyTreeviewCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }

}

package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.treeview.IndicationTreeviewDTO;
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
 * Indication Treeview search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class IndicationTreeviewComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationTreeviewComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param indicationTreeviewDTO
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(IndicationTreeviewDTO indicationTreeviewDTO) {

        Map<String, String> targetQueries = new HashMap<>();

        String indicationInnerQuery = StringUtils.EMPTY;
        String indicationOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(!indicationTreeviewDTO.getIndicationIcd10Code().isEmpty()) {

            indicationInnerQuery = " ( icd10_codes && ( select array_agg(distinct ic.child_icd10_code) from target_search.indication_ontology_all_childs ic " +
                    "where ic.icd10_code in ("+stringUtility.prepareInStringList(indicationTreeviewDTO.getIndicationIcd10Code())+") ) ) ";

            indicationOuterQuery = " ( gvk_id in (select gvk_id from target_search.therapeutic_area_agg " +
                    "where therapeutic_uses && ( select array_agg(distinct ic.child_icd10_code) from target_search.indication_ontology_all_childs ic where " +
                    "ic.icd10_code in ("+stringUtility.prepareInStringList(indicationTreeviewDTO.getIndicationIcd10Code())+") ) ) ) ";

            orStatus = " ( gvk_id in ( select gvk_id from target_search.therapeutic_area_agg where icd10_codes && (select array_agg(distinct ic.child_icd10_code) " +
                    "from target_search.indication_ontology_all_childs ic where ic.icd10_code in ("+stringUtility.prepareInStringList(indicationTreeviewDTO.getIndicationIcd10Code())+") ) ) ) ";

        }

        targetQueries.put("Inner", indicationInnerQuery);
        targetQueries.put("Outer", indicationOuterQuery);
        targetQueries.put("psw", StringUtils.EMPTY);
        targetQueries.put("orstatus", orStatus);

        return targetQueries;

    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkIndicationTreeviewCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

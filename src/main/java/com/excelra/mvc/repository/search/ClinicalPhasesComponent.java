package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.ListClinicalPhasesDTO;
import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Compound Phases search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class ClinicalPhasesComponent {

    @Autowired
    private StringUtility stringUtility;

    public Map<String, String> prepareClinicalPhasesSimpleQuery(List<ListClinicalPhasesDTO> clinicalPhasesDTOList) {

        Map<String, String> clinicalPhasesQueries = new HashMap<>();

        String clinicalPhasesInnerQuery = StringUtils.EMPTY;
        String clinicalPhasesOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        List<String> clinicalPhasesList = new ArrayList<>();

        for(ListClinicalPhasesDTO listClinicalPhasesDTO : clinicalPhasesDTOList) {
            clinicalPhasesList.add(listClinicalPhasesDTO.getClinicalPhase());
        }

        if(!clinicalPhasesList.isEmpty()) {
            clinicalPhasesInnerQuery = " clinical_phases && cast(array["+stringUtility.prepareInStringList(clinicalPhasesList)+"] as varchar[]) ";

            orStatus = "str_id in ( select str_id from target_search.str_id_based where clinical_phases && cast(array["+stringUtility.prepareInStringList(clinicalPhasesList)+"] as varchar[]) )";
        }

        clinicalPhasesQueries.put("Inner", clinicalPhasesInnerQuery);
        clinicalPhasesQueries.put("Outer", clinicalPhasesOuterQuery);
        clinicalPhasesQueries.put("psw", StringUtils.EMPTY);
        clinicalPhasesQueries.put("orstatus", orStatus);

        return clinicalPhasesQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkClinicalPhasesCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

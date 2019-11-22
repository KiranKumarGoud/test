package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.ListActivityMechanismDTO;
import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Activity Mechanism search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class ActivityMechanismComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityMechanismComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     * Activity Mechanism (MOA) field Simple Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param activityMechanismDTOs
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(List<ListActivityMechanismDTO> activityMechanismDTOs) {

        Map<String, String> activityMechanismQueries = new HashMap<>();

        String mechanismInnerQuery = StringUtils.EMPTY;
        String mechanismOuterQuery = StringUtils.EMPTY;

        List<String> andList = new ArrayList<>();
        List<String> orList = new ArrayList<>();
        List<String> notList = new ArrayList<>();

        String andOrNotStatus = StringUtils.EMPTY;

        for(ListActivityMechanismDTO listActivityMechanismDTO : activityMechanismDTOs) {
            if (listActivityMechanismDTO.getOperator().equals("&")) {
                andList.add(listActivityMechanismDTO.getActivityMechanism());
            } else if (listActivityMechanismDTO.getOperator().equals("|")) {
                orList.add(listActivityMechanismDTO.getActivityMechanism());
            } else if (listActivityMechanismDTO.getOperator().equals("!")) {
                notList.add(listActivityMechanismDTO.getActivityMechanism());
            }
        }
        orList.addAll(andList);

        if(!orList.isEmpty() && !andList.isEmpty() && !notList.isEmpty()) {

            mechanismInnerQuery = " ( activity_mechanisms @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) and not activity_mechanisms && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            mechanismOuterQuery = " activity_mechanisms && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ";
            andOrNotStatus = "ANDORNOT";

        } else if(!orList.isEmpty() && !andList.isEmpty()) {

            mechanismInnerQuery = " ( activity_mechanisms @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ) ";
            mechanismOuterQuery = " activity_mechanisms && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ";
            andOrNotStatus = "ANDOR";

        } else if(!orList.isEmpty() && !notList.isEmpty()) {

            mechanismInnerQuery = " ( and not activity_mechanisms && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            mechanismOuterQuery = " activity_mechanisms && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ";
            andOrNotStatus = "ORNOT";

        } else if(!andList.isEmpty() && !notList.isEmpty()) {

            mechanismInnerQuery = " ( activity_mechanisms @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) and not activity_mechanisms && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            mechanismOuterQuery = " activity_mechanisms && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ";
            andOrNotStatus = "ANDNOT";

        } else if(!orList.isEmpty()) {

            mechanismInnerQuery = " ( activity_mechanisms @> cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            mechanismOuterQuery = " activity_mechanisms && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ";
            andOrNotStatus = "OR";

        } else if(!andList.isEmpty()) {

            mechanismInnerQuery = " ( activity_mechanisms @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ) ";
            mechanismOuterQuery = " activity_mechanisms && cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ";
            andOrNotStatus = "AND";
        }


        activityMechanismQueries.put("Inner", mechanismInnerQuery);
        activityMechanismQueries.put("Outer", mechanismOuterQuery);
        activityMechanismQueries.put("psw",StringUtils.EMPTY);
        activityMechanismQueries.put("orstatus",andOrNotStatus);

        return activityMechanismQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkActivityMechanismCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.ListActivityTypesDTO;
import com.excelra.mvc.model.search.ActivityType.ActivityTypeAdvSearchDTO;
import com.excelra.mvc.model.search.ActivityType.ActivityTypeInputDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Activity Types search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class ActivityTypesComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTypesComponent.class);

    String[] equalsOperator = {"=", ">=", "<=", "~", "~="};

    String[] lessThanOperator = {"<=", "<", "~", "~="};

    String[] lessThanEqualsOperator = {"=", "<=", "<", "~", "~="};

    String[] gratherThanOperator = {">=", ">", "~", "~="};

    String[] gratherThanEqualsOperator = {"=", ">=", ">", "~", "~="};

    String[] rangeLowestOperator = {"<", "<=", "=", "~", "~="};

    String[] rangeHighestOperator = {"=", ">=", ">", "~", "~="};

    @Autowired
    private StringUtility stringUtility;

    /**
     * Activity Type field Simple Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param activityTypesDTOs
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(List<ListActivityTypesDTO> activityTypesDTOs) {

        Map<String, String> activityTypesQueries = new HashMap<>();

        String typesInnerQuery = StringUtils.EMPTY;
        String typesOuterQuery = StringUtils.EMPTY;

        List<String> andList = new ArrayList<>();
        List<String> orList = new ArrayList<>();
        List<String> notList = new ArrayList<>();

        String andOrNotStatus = StringUtils.EMPTY;

        for(ListActivityTypesDTO listActivityTypesDTO : activityTypesDTOs) {
            if (listActivityTypesDTO.getOperator().equals("&")) {
                andList.add(listActivityTypesDTO.getActivityType());
            } else if (listActivityTypesDTO.getOperator().equals("|")) {
                orList.add(listActivityTypesDTO.getActivityType());
            } else if (listActivityTypesDTO.getOperator().equals("!")) {
                notList.add(listActivityTypesDTO.getActivityType());
            }
        }
        List<String> andOrgList = andList;
        List<String> orOrgList = orList;
        orList.addAll(andOrgList);
        andList.addAll(orOrgList);


        if(!orList.isEmpty() && !andList.isEmpty() && !notList.isEmpty()) {

            typesInnerQuery = " ( activity_types @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) and not activity_types && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            typesOuterQuery = " activity_type in ("+stringUtility.prepareString(orList)+") ";
            andOrNotStatus = "ANDORNOT";

        } else if(!orList.isEmpty() && !andList.isEmpty()) {

            typesInnerQuery = " ( activity_types @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ) ";
            typesOuterQuery = " activity_type in ("+stringUtility.prepareString(orList)+") ";
            andOrNotStatus = "ANDOR";

        } else if(!orList.isEmpty() && !notList.isEmpty()) {

            typesInnerQuery = " ( activity_types @> cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) and not activity_types && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            typesOuterQuery = " activity_type in ("+stringUtility.prepareString(orList)+") ";
            andOrNotStatus = "ORNOT";

        } else if(!andList.isEmpty() && !notList.isEmpty()) {

            typesInnerQuery = " ( activity_types @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) and not activity_types && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            typesOuterQuery = " activity_type in ("+stringUtility.prepareString(orList)+") ";
            andOrNotStatus = "ANDNOT";

        } else if(!orList.isEmpty()) {

            typesInnerQuery = " ( activity_types @> cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            typesOuterQuery = " activity_type in ("+stringUtility.prepareString(orList)+") ";
            andOrNotStatus = "OR";

        } else if(!andList.isEmpty()) {

            typesInnerQuery = " ( activity_types @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ) ";
            typesOuterQuery = " activity_type in ("+stringUtility.prepareString(andList)+") ";
            andOrNotStatus = "AND";
        }

        activityTypesQueries.put("Inner", typesInnerQuery);
        activityTypesQueries.put("Outer", typesOuterQuery);
        activityTypesQueries.put("psw",StringUtils.EMPTY);
        activityTypesQueries.put("orstatus",andOrNotStatus);

        return activityTypesQueries;
    }

    /**
     *
     * @param activityTypeAdvSearchDTO
     * @param userJdbc
     * @return
     */
    public Map<String, String> getAdvancedSearchQueryCombination(ActivityTypeAdvSearchDTO activityTypeAdvSearchDTO, UserJdbc userJdbc) {

        Map<String, String> activityTypesQueries = new HashMap<>();

        String typesInnerQuery = StringUtils.EMPTY;
        String typesOuterQuery = StringUtils.EMPTY;

        String andOrNotStatus = StringUtils.EMPTY;

        List<ActivityTypeInputDTO> activityTypeInputDTOList = activityTypeAdvSearchDTO.getActivityTypeInputDTOList();

        List<String> prepareSelectedCombinations = new ArrayList<>();

        prepareSelectedCombinations = prepareOperatorCombinations(activityTypeInputDTOList);

        if(!prepareSelectedCombinations.isEmpty()) {
            typesInnerQuery = "( str_id in ("+String.join(" or ", prepareSelectedCombinations)+") )";
            typesOuterQuery = "( str_id in ("+String.join(" or ", prepareSelectedCombinations)+") )";
            andOrNotStatus = "ANDORNOT";
        }

        if(!typesInnerQuery.isEmpty() && !typesOuterQuery.isEmpty()) {
            activityTypesQueries.put("Inner", typesInnerQuery);
            activityTypesQueries.put("Outer", typesOuterQuery);
            activityTypesQueries.put("psw", StringUtils.EMPTY);
            activityTypesQueries.put("orstatus", andOrNotStatus);
        }

        return activityTypesQueries;

    }

    /**
     *
     * @param activityTypeInputDTOList
     * @return
     */
    private List<String> prepareOperatorCombinations(List<ActivityTypeInputDTO> activityTypeInputDTOList) {

        List<String> allCombinations = new ArrayList<>();

        for(ActivityTypeInputDTO activityTypeInputDTO : activityTypeInputDTOList) {

            String operatorCombinationQuery = StringUtils.EMPTY;

            switch(activityTypeInputDTO.getPrefix()) {

                case "=":
                    operatorCombinationQuery = prepareQueryExcludingROperator(activityTypeInputDTO, equalsOperator);

                    break;

                case "<":
                    operatorCombinationQuery = prepareQueryExcludingROperator(activityTypeInputDTO, lessThanOperator);
                    break;

                case "<=":
                    operatorCombinationQuery = prepareQueryExcludingROperator(activityTypeInputDTO, lessThanEqualsOperator);
                    break;

                case ">":
                    operatorCombinationQuery = prepareQueryExcludingROperator(activityTypeInputDTO, gratherThanOperator);
                    break;

                case ">=":
                    operatorCombinationQuery = prepareQueryExcludingROperator(activityTypeInputDTO, gratherThanEqualsOperator);
                    break;

                case "R":
                    operatorCombinationQuery = prepareQueryIncludingROperator(activityTypeInputDTO);
                    break;

                default:
                    System.out.println("No Condition is available");
            }

            if(!operatorCombinationQuery.isEmpty()) {
                allCombinations.add(operatorCombinationQuery);
            }

        }

        return allCombinations;
    }

    /**
     *
     * @param activityTypeInputDTO
     * @param operatorArray
     * @return
     */
    public String prepareQueryExcludingROperator(ActivityTypeInputDTO activityTypeInputDTO, String[] operatorArray) {
        if(!activityTypeInputDTO.getActivityType().isEmpty() &&
                !activityTypeInputDTO.getActivityUom().isEmpty() &&
                !activityTypeInputDTO.getPrefix().isEmpty() &&
                Objects.nonNull(activityTypeInputDTO.getMinValue())) {
            return "select str_id from target_search.all_mapping_ids where activity_type = '" + activityTypeInputDTO.getActivityType() + "' " +
                    "and activity_uom = '" + activityTypeInputDTO.getActivityUom() + "' " +
                    "and activity_value " + activityTypeInputDTO.getPrefix() + " " + activityTypeInputDTO.getMinValue() + " " +
                    "and activity_prefix in (" + stringUtility.prepareInStringList(stringUtility.convertArrayToList(operatorArray)) + ")";
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     *
     * @param activityTypeInputDTO
     * @return
     */
    public String prepareQueryIncludingROperator(ActivityTypeInputDTO activityTypeInputDTO) {
        if(!activityTypeInputDTO.getActivityType().isEmpty() &&
                !activityTypeInputDTO.getActivityUom().isEmpty() &&
                !activityTypeInputDTO.getPrefix().isEmpty() &&
                Objects.nonNull(activityTypeInputDTO.getMinValue()) &&
                Objects.nonNull(activityTypeInputDTO.getMaxValue())) {
            return "select str_id from target_search.all_mapping_ids where activity_type = '" + activityTypeInputDTO.getActivityType() + "' " +
                    "and activity_uom = '" + activityTypeInputDTO.getActivityUom() + "' " +
                    "and activity_value >= " + activityTypeInputDTO.getMinValue() + " " +
                    "and activity_prefix in (" + stringUtility.prepareInStringList(stringUtility.convertArrayToList(rangeLowestOperator)) + ") " +
                    "and activity_value <= " + activityTypeInputDTO.getMaxValue() + " " +
                    "and activity_prefix in (" + stringUtility.prepareInStringList(stringUtility.convertArrayToList(rangeHighestOperator)) + ")";
        } else {
            return StringUtils.EMPTY;
        }
    }
}

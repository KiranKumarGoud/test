package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.model.search.Indication.IndicationAdvSearchDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Indication search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class IndicationComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     * Indication field Simple Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param therapeuticUseDTOList
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(List<ListTherapeuticUseDTO> therapeuticUseDTOList) {

        Map<String, String> indicationTypesQueries = new HashMap<>();

        String indicationInnerQuery = StringUtils.EMPTY;
        String indicationOuterQuery = StringUtils.EMPTY;

        String andOrNotStatus = StringUtils.EMPTY;

        List<String> andList = new ArrayList<>();
        List<String> orList = new ArrayList<>();
        List<String> notList = new ArrayList<>();

        for(ListTherapeuticUseDTO listTherapeuticUseDTO : therapeuticUseDTOList) {
            if (listTherapeuticUseDTO.getOperator().equals("&")) {
                andList.add(listTherapeuticUseDTO.getTherapeuticUse());
            } else if (listTherapeuticUseDTO.getOperator().equals("|")) {
                orList.add(listTherapeuticUseDTO.getTherapeuticUse());
            } else if (listTherapeuticUseDTO.getOperator().equals("!")) {
                notList.add(listTherapeuticUseDTO.getTherapeuticUse());
            }
        }
        List<String> andOrgList = andList;
        // List<String> orOrgList = orList;
        orList.addAll(andOrgList);
        // andList.addAll(orOrgList);


        if(!orList.isEmpty() && !andList.isEmpty() && !notList.isEmpty()) {

            indicationInnerQuery = " ( therapeutic_uses @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) and not therapeutic_uses && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";


            andOrNotStatus = "ANDORNOT";

        } else if(!orList.isEmpty() && !andList.isEmpty()) {

            indicationInnerQuery = " ( therapeutic_uses @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ) ";
            indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            andOrNotStatus = "ANDOR";

        } else if(!orList.isEmpty() && !notList.isEmpty()) {

            indicationInnerQuery = " ( therapeutic_uses @> cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) and not therapeutic_uses && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            andOrNotStatus = "ORNOT";

        } else if(!andList.isEmpty() && !notList.isEmpty()) {

            indicationInnerQuery = " ( therapeutic_uses @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) and not therapeutic_uses && cast(array["+stringUtility.prepareString(notList)+"] as varchar[]) ) ";
            indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            andOrNotStatus = "ANDNOT";

        } else if(!orList.isEmpty()) {

            indicationInnerQuery = " ( therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            andOrNotStatus = "OR";

        } else if(!andList.isEmpty()) {

            indicationInnerQuery = " ( therapeutic_uses @> cast(array["+stringUtility.prepareString(andList)+"] as varchar[]) ) ";
            indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where therapeutic_uses && cast(array["+stringUtility.prepareString(orList)+"] as varchar[]) ) ";
            andOrNotStatus = "AND";
        }


        indicationTypesQueries.put("Inner", indicationInnerQuery);
        indicationTypesQueries.put("Outer", indicationOuterQuery);
        indicationTypesQueries.put("psw",StringUtils.EMPTY);
        indicationTypesQueries.put("orstatus",andOrNotStatus);

        return indicationTypesQueries;

    }

    /**
     * Indication field Advanced Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param indicationAdvSearchDTO
     * @param userJdbc
     * @return
     */
    public Map<String, String> getAdvancedSearchQueryCombination(IndicationAdvSearchDTO indicationAdvSearchDTO, UserJdbc userJdbc) {

        Map<String, String> indicationTypesQueries = new HashMap<>();

        String indicationInnerQuery = StringUtils.EMPTY;
        String indicationOuterQuery = StringUtils.EMPTY;

        String andOrNotStatus = StringUtils.EMPTY;

        List<String> andList = new ArrayList<>();
        List<String> orList = new ArrayList<>();
        List<String> notList = new ArrayList<>();

        String fieldNameByOption = StringUtils.EMPTY;

        if(indicationAdvSearchDTO.getOption().equalsIgnoreCase("DiseaseName")) {

            fieldNameByOption = "therapeutic_uses";

            if(Objects.nonNull(indicationAdvSearchDTO.getFileData())) {

                for(String indicationFileData : indicationAdvSearchDTO.getFileData()) {
                    orList.add(indicationFileData);
                }

            } else {

                for (ListTherapeuticUseDTO listTherapeuticUseDTO : indicationAdvSearchDTO.getTherapeuticUseDTOList()) {
                    if (listTherapeuticUseDTO.getOperator().equals("&")) {
                        andList.add(listTherapeuticUseDTO.getTherapeuticUse());
                    } else if (listTherapeuticUseDTO.getOperator().equals("|")) {
                        orList.add(listTherapeuticUseDTO.getTherapeuticUse());
                    } else if (listTherapeuticUseDTO.getOperator().equals("!")) {
                        notList.add(listTherapeuticUseDTO.getTherapeuticUse());
                    }
                }

            }

        } else if(indicationAdvSearchDTO.getOption().equalsIgnoreCase("ICD-10")) {

            fieldNameByOption = "icd10_codes";

            if(Objects.nonNull(indicationAdvSearchDTO.getFileData())) {

                for(String indicationFileData : indicationAdvSearchDTO.getFileData()) {
                    orList.add(indicationFileData);
                }

            } else {

                for (ListIcd10CodesDTO listIcd10CodesDTO : indicationAdvSearchDTO.getIcd10CodesDTOList()) {
                    if (listIcd10CodesDTO.getOperator().equals("&")) {
                        andList.add(listIcd10CodesDTO.getIcd10Code());
                    } else if (listIcd10CodesDTO.getOperator().equals("|")) {
                        orList.add(listIcd10CodesDTO.getIcd10Code());
                    } else if (listIcd10CodesDTO.getOperator().equals("!")) {
                        notList.add(listIcd10CodesDTO.getIcd10Code());
                    }
                }

            }

        }

        if(!fieldNameByOption.isEmpty()) {

            if (!orList.isEmpty() && !andList.isEmpty() && !notList.isEmpty()) {

                indicationInnerQuery = " ( " + fieldNameByOption + " @> cast(array[" + stringUtility.prepareString(andList) + "] as varchar[]) and not " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(notList) + "] as varchar[]) ) ";
                indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";


                andOrNotStatus = "ANDORNOT";

            } else if (!orList.isEmpty() && !andList.isEmpty()) {

                indicationInnerQuery = " ( " + fieldNameByOption + " @> cast(array[" + stringUtility.prepareString(andList) + "] as varchar[]) ) ";
                indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";
                andOrNotStatus = "ANDOR";

            } else if (!orList.isEmpty() && !notList.isEmpty()) {

                indicationInnerQuery = " ( " + fieldNameByOption + " @> cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) and not " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(notList) + "] as varchar[]) ) ";
                indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";
                andOrNotStatus = "ORNOT";

            } else if (!andList.isEmpty() && !notList.isEmpty()) {

                indicationInnerQuery = " ( " + fieldNameByOption + " @> cast(array[" + stringUtility.prepareString(andList) + "] as varchar[]) and not " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(notList) + "] as varchar[]) ) ";
                indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";
                andOrNotStatus = "ANDNOT";

            } else if (!orList.isEmpty()) {

                indicationInnerQuery = " ( " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";
                indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";
                andOrNotStatus = "OR";

            } else if (!andList.isEmpty()) {

                indicationInnerQuery = " ( " + fieldNameByOption + " @> cast(array[" + stringUtility.prepareString(andList) + "] as varchar[]) ) ";
                indicationOuterQuery = " gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + fieldNameByOption + " && cast(array[" + stringUtility.prepareString(orList) + "] as varchar[]) ) ";
                andOrNotStatus = "AND";
            }
        }

        indicationTypesQueries.put("Inner", indicationInnerQuery);
        indicationTypesQueries.put("Outer", indicationOuterQuery);
        indicationTypesQueries.put("psw",StringUtils.EMPTY);
        indicationTypesQueries.put("orstatus",andOrNotStatus);

        return indicationTypesQueries;
    }
}

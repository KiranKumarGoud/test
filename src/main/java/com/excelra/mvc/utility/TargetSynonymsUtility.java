package com.excelra.mvc.utility;

import com.excelra.mvc.model.TargetSynonymsDTO;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Target Synonyms Utility
 * <p>
 *
 * @author venkateswarlu.s
 */
@Component
public class TargetSynonymsUtility {

    /**
     *
     * @param targetsList
     * @return
     */
    public List<TargetSynonymsDTO> removeDuplicates(List<TargetSynonymsDTO> targetsList) {

        List<TargetSynonymsDTO> removeDuplicates = new ArrayList<>();
        for(TargetSynonymsDTO targetSynonymDto : targetsList) {
            if(!checkAvailability(removeDuplicates, targetSynonymDto.getSynonyms())) {
                removeDuplicates.add(targetSynonymDto);
            }
        }

        return removeDuplicates;
    }

    /**
     * Remove Duplicates from List
     *
     * @param targetsList
     * @param synonym
     * @return
     */
    private boolean checkAvailability(List<TargetSynonymsDTO> targetsList, String synonym) {
        Boolean status = Boolean.FALSE;
        for(TargetSynonymsDTO targetDto : targetsList) {
            if(targetDto.getSynonyms().trim().equalsIgnoreCase(synonym)) {
                return Boolean.TRUE;
            }
        }
        return status;
    }

    private String convertListToString(List<String> namesList) {
        String list= StringUtils.join(namesList, "','");
        list = "'" + list + "'";

        return list;
    }

    private String convertListToStringForIntegers(List<Integer> namesList) {
        String list= StringUtils.join(namesList, ",");

        return list;
    }
}


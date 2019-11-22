package com.excelra.mvc.utility;

import com.excelra.mvc.model.AllMappingFinalResultDTO;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  All Mapping Ids Utility
 * <p>
 *
 * @author venkateswarlu.s
 */
@Component
public class AllMappingIdsUtility {

    private static final String AND_OPTION = "and";

    private static final String OR_OPTION = "or";

    private static final String NOT_OPTION = "not";


    /**
     *
     * @param targetSynonymsAllMappings
     * @param ontoAssayTypeAllMappings
     * @param option
     */
    public void prepareConditionalData(AllMappingFinalResultDTO targetSynonymsAllMappings,
                                       AllMappingFinalResultDTO ontoAssayTypeAllMappings, String option) {

        AllMappingFinalResultDTO allMappingFinalResultDTO = new AllMappingFinalResultDTO();

        if(option.equalsIgnoreCase("and")) {

            allMappingFinalResultDTO = prepareDataForOption(targetSynonymsAllMappings, ontoAssayTypeAllMappings, "and");

        } else if(option.equalsIgnoreCase("or")) {

            allMappingFinalResultDTO = prepareDataForOption(targetSynonymsAllMappings, ontoAssayTypeAllMappings, "or");

        } else if(option.equalsIgnoreCase("not")) {

            allMappingFinalResultDTO = prepareDataForOption(targetSynonymsAllMappings, ontoAssayTypeAllMappings, "not");

        } else {

        }
    }


    /**
     *
     * @param firstObject
     * @param secondObject
     * @param option
     * @return
     */
    public AllMappingFinalResultDTO prepareDataForOption(AllMappingFinalResultDTO firstObject,
                                                         AllMappingFinalResultDTO secondObject, String option) {

        AllMappingFinalResultDTO allMappingFinalResultDTO = new AllMappingFinalResultDTO();

        if(option.equalsIgnoreCase(AND_OPTION)) {

            firstObject.getActIdsList().retainAll(secondObject.getActIdsList());
            firstObject.getStrIdsList().retainAll(secondObject.getStrIdsList());
            firstObject.getRefIdsList().retainAll(secondObject.getRefIdsList());
            firstObject.getGvkIdsList().retainAll(secondObject.getGvkIdsList());
            firstObject.getAssayIdsList().retainAll(secondObject.getAssayIdsList());

            allMappingFinalResultDTO = firstObject;
        }

        if(option.equalsIgnoreCase(OR_OPTION)) {
            firstObject.getActIdsList().addAll(secondObject.getActIdsList());
            firstObject.getGvkIdsList().addAll(secondObject.getGvkIdsList());
            firstObject.getRefIdsList().addAll(secondObject.getRefIdsList());
            firstObject.getStrIdsList().addAll(secondObject.getStrIdsList());
            firstObject.getAssayIdsList().addAll(secondObject.getAssayIdsList());

            allMappingFinalResultDTO = firstObject;
        }

        if(option.equalsIgnoreCase(NOT_OPTION)) {
            firstObject.getActIdsList().removeAll(secondObject.getActIdsList());
            firstObject.getGvkIdsList().removeAll(secondObject.getGvkIdsList());
            firstObject.getRefIdsList().removeAll(secondObject.getRefIdsList());
            firstObject.getStrIdsList().removeAll(secondObject.getStrIdsList());
            firstObject.getAssayIdsList().removeAll(secondObject.getAssayIdsList());

            allMappingFinalResultDTO = firstObject;
        }


        return allMappingFinalResultDTO;
    }
}

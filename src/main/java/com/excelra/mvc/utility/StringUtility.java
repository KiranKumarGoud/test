package com.excelra.mvc.utility;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All String related methods.
 *
 * @author venkateswarlu.s
 */
@Component
public class StringUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtility.class);

    /**
     * Preparing the String List to Comma separated String
     *
     * @param namesList - Names List
     * @return String
     */
    public static String prepareString(List<String> namesList) {
        return String.join(",", namesList
                .stream()
                .map(name -> ("'" + name.toUpperCase() + "'"))
                .collect(Collectors.toList()));
    }

    /***
     *
     * @param namesList
     * @return
     */
    public static String prepareCasNoString(List<String> namesList) {
        return String.join(",", namesList
                .stream()
                .map(name -> ("'" + name.toUpperCase() + "'"))
                .collect(Collectors.toList()));
    }

    /****
     *
     * @param namesList
     * @return
     */
    public static String prepareStringForCompoundName(List<String> namesList) {
        return String.join(",", namesList
                .stream()
                //.anyMatch(name -> name.contains("2,2'-AZOBIS 2-METHYLBUTANENITRILE"))).replace("2,2'-AZOBIS 2-METHYLBUTANENITRILE" ," 2,2-AZOBIS 2-METHYLBUTANENITRILE")
                .map(name -> ("'" + name.toUpperCase() + "'").replace("10'", "10").replace("13'", "13").replace("2,2'-AZOBIS 2-METHYLBUTANENITRILE", "2,2\''-AZOBIS 2-METHYLBUTANENITRILE").replace("1,1''-IMINOBIS-2-PROPANOL", "1,1''''-IMINOBIS-2-PROPANOL").replaceAll(" '' ", " ''' "))
                .collect(Collectors.toList()));
    }

    /**
     *
     * @param inputList
     * @return
     */
    public String prepareInStringList(List<String> inputList) {
        return String.join(",", inputList.stream().map(name -> ("'" + name + "'")).collect(Collectors.toList()));
    }

    /**
     *
     * @param inputList
     * @return
     */
    public String prepareInNumbericList(List<Long> inputList) {
        return String.join(",", inputList.stream().map(name -> ("" + name + "")).collect(Collectors.toList()));
    }

    /**
     *
     * @param inputArray
     * @return
     */
    public List<String> convertArrayToList(String[] inputArray) {
        return Arrays.asList(inputArray);
    }

    /**
     *
     * @param namesList
     * @param delimitor
     * @return
     */
    public String prepareStringWithSpecificDelimitor(List<String> namesList, String delimitor) {
        return String.join(delimitor, namesList
                .stream()
                .map(name -> ("" + name + ""))
                .collect(Collectors.toList()));
    }


    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkNotSourceStatus(List<SearchCountInputDTO> searchCountInputDTOList) {

        String notSourceCheck = StringUtils.EMPTY;
        for(SearchCountInputDTO sciDto : searchCountInputDTOList) {
            notSourceCheck = notSourceCheck + sciDto.getSourceOption() + sciDto.getOperator();
        }

        return notSourceCheck.contains("notSource");
    }

    /**
     *
     * @param finalCombinedQuery
     * @return
     */
    public String removeUnhandledQueryCombinations(String finalCombinedQuery) {

        LOGGER.info(" -- removeUnhandledQueryCombinations -- :: Before String processing -- {} ", finalCombinedQuery);

        finalCombinedQuery = finalCombinedQuery.replaceAll("where\\s+and", "where");
        finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+and", "and");
        finalCombinedQuery = finalCombinedQuery.replaceAll("varchar\\[\\]\\)\\s+\\)\\s+stdname_id", "varchar[])) or  stdname_id");
        finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+not\\s+and\\s+not", "and not");
        finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+not\\s+\\)", " ) ");

        LOGGER.info(" -- removeUnhandledQueryCombinations -- :: After String process -- {} ", finalCombinedQuery);

        return finalCombinedQuery;
    }
}

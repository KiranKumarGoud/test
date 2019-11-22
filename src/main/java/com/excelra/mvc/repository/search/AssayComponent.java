package com.excelra.mvc.repository.search;

import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assay search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class AssayComponent {

    @Autowired
    private StringUtility stringUtility;

    /**
     * Assay field Simple Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param andList
     * @param orList
     * @param notList
     * @param isAssayQuery
     * @return
     */
    public Map<String, String> prepareAssayQuery(List<String> andList, List<String> orList, List<String> notList, Boolean isAssayQuery) {

        String orStatus = "false";
        String notQuery = "false";

        String assayInnerQuery = StringUtils.EMPTY;
        String assayOuterQuery = StringUtils.EMPTY;

        Map<String, String> assayQueries = new HashMap<>();

        if (andList.size() > 0 && notList.size() > 0 && orList.size() > 0) {
            assayInnerQuery = " ( assay_types @> cast(array[" + stringUtility.prepareString(andList) + "] AS varchar[]) "
                    + " and not assay_types && cast(array[" + stringUtility.prepareString(notList) + "] AS varchar[]) ) ";
            if (notQuery == "true") {
                assayOuterQuery = " assay_type in (" + stringUtility.prepareString(orList) + ") ";
                isAssayQuery = true;

            } else {
                assayOuterQuery = " assay_type in (" + stringUtility.prepareString(orList) + ") ";
            }
            notQuery = "true";
        } else if (andList.size() > 0 && orList.size() > 0) {
            assayInnerQuery = " ( assay_types @> cast(array[" + stringUtility.prepareString(andList) + "] AS varchar[]) ) ";

            assayOuterQuery = "  assay_type in (" + stringUtility.prepareString(orList) + ") ";

        } else if (notList.size() > 0 && orList.size() > 0) {
            assayInnerQuery = " ( assay_types @> cast(array[" + stringUtility.prepareString(orList) + "] AS varchar[]) "
                    + "and not assay_types && cast(array[" + stringUtility.prepareString(notList) + "] AS varchar[]) ) ";

            //  assayTypeOrgQuery2 = " or assay_type in ("+prepareString(orList)+") ";
            assayOuterQuery = " assay_type in (" + stringUtility.prepareString(orList) + ") ";
            System.out.println("control came or not hre" + assayOuterQuery);


        } else if (andList.size() > 0 && notList.size() > 0) {
            assayInnerQuery = " ( assay_types @> cast(array[" + stringUtility.prepareString(andList) + "] AS varchar[]) "
                    + " and not assay_types && cast(array[" + stringUtility.prepareString(notList) + "] AS varchar[]) ) ";

            // assayTypeOrgQuery2 = " or assay_type in ("+prepareString(andList)+") ";
            assayOuterQuery = " assay_type in (" + stringUtility.prepareString(andList) + ") ";

        } else if (andList.size() > 0) {
            assayInnerQuery = " ( assay_types @> cast(array[" + stringUtility.prepareString(andList) + "] AS varchar[]) ) ";

            assayOuterQuery = " assay_type in (" + stringUtility.prepareString(andList) + ") ";

        } else if (orList.size() > 0) {
            assayInnerQuery = " ( assay_types && cast(array[" + stringUtility.prepareString(orList) + "] AS varchar[]) ) ";

            assayOuterQuery = " assay_type in (" + stringUtility.prepareString(orList) + ") ";

            orStatus = "true";
            notQuery = "true";
        }

        assayQueries.put("Inner", assayInnerQuery);
        assayQueries.put("Outer", assayOuterQuery);
        assayQueries.put("psw", StringUtils.EMPTY);
        assayQueries.put("orStatus", orStatus);
        assayQueries.put("notQuery", notQuery);

        return assayQueries;
    }
}

package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.source.SourceAdvSearchDTO;
import com.excelra.mvc.model.source.ListSearchDTO;
import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.excelra.mvc.model.source.ListSourceTaxIdsDTO;
import com.excelra.mvc.model.source.StrainSearchDTO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Source Simple and Advanced search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class SourceComponent {

    private static final String SOURCE_ID = "SourceId";

    private static final String TAX_ID = "TaxId";

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param sourceSynonymsDTOList
     * @return
     */
    public Map<String, String> prepareSourceSimpleQuery(List<ListSourceSynonymsDTO> sourceSynonymsDTOList) {

        Map<String, String> sourceQueries = new HashMap<>();

        String sourceInnerQuery = StringUtils.EMPTY;
        String sourceOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        List<Long> sourceIdList = new ArrayList<>();
        for(ListSourceSynonymsDTO listSourceSynonymsDTO : sourceSynonymsDTOList) {
            sourceIdList.add(listSourceSynonymsDTO.getSourceId());
        }

        List<Long> andList = new ArrayList<>();
        List<Long> orList = new ArrayList<>();
        List<Long> notList = new ArrayList<>();

        for(ListSourceSynonymsDTO listSourceSynonymsDTO : sourceSynonymsDTOList) {
            if (listSourceSynonymsDTO.getOperator().equals("&")) {
                andList.add(listSourceSynonymsDTO.getSourceId());
            } else if (listSourceSynonymsDTO.getOperator().equals("|")) {
                orList.add(listSourceSynonymsDTO.getSourceId());
            } else if (listSourceSynonymsDTO.getOperator().equals("!")) {
                notList.add(listSourceSynonymsDTO.getSourceId());
            }
        }
        orList.addAll(andList);

        if (andList.size() > 0 && notList.size() > 0 && orList.size() > 0) {
            sourceInnerQuery = " ( source_ids @> cast(array[" + stringUtility.prepareInNumbericList(andList) + "] AS bigint[]) "
                    + " and not source_ids && cast(array[" + stringUtility.prepareInNumbericList(notList) + "] AS bigint[]) ) ";

            sourceOuterQuery = " source_id in (" + stringUtility.prepareInNumbericList(orList) + ") ";

        } else if (andList.size() > 0 && orList.size() > 0) {
            sourceInnerQuery = " ( source_ids @> cast(array[" + stringUtility.prepareInNumbericList(andList) + "] AS bigint[]) ) ";

            sourceOuterQuery = "  source_id in (" + stringUtility.prepareInNumbericList(orList) + ") ";

        } else if (notList.size() > 0 && orList.size() > 0) {
            sourceInnerQuery = " ( source_ids @> cast(array[" + stringUtility.prepareInNumbericList(orList) + "] AS bigint[]) "
                    + "and not source_ids && cast(array[" + stringUtility.prepareInNumbericList(notList) + "] AS bigint[]) ) ";
            sourceOuterQuery = " source_id in (" + stringUtility.prepareInNumbericList(orList) + ") ";

        } else if (andList.size() > 0 && notList.size() > 0) {
            sourceInnerQuery = " ( source_ids @> cast(array[" + stringUtility.prepareInNumbericList(andList) + "] AS bigint[]) "
                    + " and not source_ids && cast(array[" + stringUtility.prepareInNumbericList(notList) + "] AS bigint[]) ) ";

            sourceOuterQuery = " source_id in (" + stringUtility.prepareInNumbericList(andList) + ") ";

        } else if (andList.size() > 0) {
            sourceInnerQuery = " ( source_ids @> cast(array[" + stringUtility.prepareInNumbericList(andList) + "] AS bigint[]) ) ";

            sourceOuterQuery = " source_id in (" + stringUtility.prepareInNumbericList(andList) + ") ";

        } else if (orList.size() > 0) {
            sourceInnerQuery = " ( source_ids && cast(array[" + stringUtility.prepareInNumbericList(orList) + "] AS bigint[]) ) ";

            sourceOuterQuery = " source_id in (" + stringUtility.prepareInNumbericList(orList) + ") ";

        }


        sourceQueries.put("Inner", sourceInnerQuery);
        sourceQueries.put("Outer", sourceOuterQuery);
        sourceQueries.put("psw", StringUtils.EMPTY);
        sourceQueries.put("orstatus", orStatus);

        return sourceQueries;
    }

    /**
     *
     * @param sourceAdvSearchDTO
     * @return
     */
    public Map<String, String> prepareSourceAdvancedQuery(SourceAdvSearchDTO sourceAdvSearchDTO) {

        Map<String, String> sourceQueries = new HashMap<>();

        String sourceInnerQuery = StringUtils.EMPTY;
        String sourceOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(Objects.nonNull(sourceAdvSearchDTO.getListSearchDTO())) {

            /**
             * When List search inputs are received
             */
            ListSearchDTO listSearchDTO = sourceAdvSearchDTO.getListSearchDTO();

            if(listSearchDTO.getOption().equalsIgnoreCase(SOURCE_ID)) {

                // For Source Option
                if(Objects.nonNull(listSearchDTO.getSourceSynonymsDTOList()) && !listSearchDTO.getSourceSynonymsDTOList().isEmpty()) {
                    return prepareSourceSimpleQuery(listSearchDTO.getSourceSynonymsDTOList());
                } else if(Objects.nonNull(listSearchDTO.getSourceIdList()) && !listSearchDTO.getSourceIdList().isEmpty()) {

                    sourceInnerQuery = " ( source_ids && cast(array[" + stringUtility.prepareInNumbericList(listSearchDTO.getSourceIdList()) + "] AS bigint[]) ) ";
                    sourceOuterQuery = " source_id in (" + stringUtility.prepareInNumbericList(listSearchDTO.getSourceIdList()) + ") ";
                }


            } else if(listSearchDTO.getOption().equalsIgnoreCase(TAX_ID)) {

                // For TaxId Option
                if(Objects.nonNull(listSearchDTO.getSourceTaxIdsDTOList()) && !listSearchDTO.getSourceTaxIdsDTOList().isEmpty()) {

                    List<Long> taxIdsList = new ArrayList<>();

                    for(ListSourceTaxIdsDTO sourceTaxIdsDTO : listSearchDTO.getSourceTaxIdsDTOList()) {
                        taxIdsList.add(sourceTaxIdsDTO.getTaxId());
                    }

                    sourceInnerQuery = " ( tax_ids && cast(array[" + stringUtility.prepareInNumbericList(taxIdsList) + "] AS bigint[]) ) ";
                    sourceOuterQuery = " source_id in (select source_id from target_search.source_classification_master where tax_id in ("+stringUtility.prepareInNumbericList(taxIdsList)+")) ";

                } else if(Objects.nonNull(listSearchDTO.getTaxIdList()) && !listSearchDTO.getTaxIdList().isEmpty()) {

                    sourceInnerQuery = " ( tax_ids && cast(array[" + stringUtility.prepareInNumbericList(listSearchDTO.getTaxIdList()) + "] AS bigint[]) ) ";
                    sourceOuterQuery = " source_id in (select source_id from target_search.source_classification_master where tax_id in ("+stringUtility.prepareInNumbericList(listSearchDTO.getTaxIdList())+")) ";
                }

            }
        } else if(Objects.nonNull(sourceAdvSearchDTO.getStrainSearchDTOList()) && !sourceAdvSearchDTO.getStrainSearchDTOList().isEmpty()) {

            /**
             * When Strain search Inputs are received
             */

            List<String> finalQueryOptions = new ArrayList<>();

            for(StrainSearchDTO strainSearchDTO : sourceAdvSearchDTO.getStrainSearchDTOList()) {

                String innerQuery = StringUtils.EMPTY;

                List<String> innerQueryOptions = new ArrayList<>();

                if(Objects.nonNull(strainSearchDTO.getGenusList()) && !strainSearchDTO.getGenusList().isEmpty()) {
                    innerQueryOptions.add(" genus in ("+stringUtility.prepareInStringList(strainSearchDTO.getGenusList())+") ");
                }

                if(Objects.nonNull(strainSearchDTO.getSpeciesList()) && !strainSearchDTO.getSpeciesList().isEmpty()) {
                    innerQueryOptions.add(" species in ("+stringUtility.prepareInStringList(strainSearchDTO.getSpeciesList())+") ");
                }

                if(Objects.nonNull(strainSearchDTO.getStrainList()) && !strainSearchDTO.getStrainList().isEmpty()) {
                    innerQueryOptions.add(" strain in ("+stringUtility.prepareInStringList(strainSearchDTO.getStrainList())+") ");
                }

                finalQueryOptions.add(" ( "+stringUtility.prepareStringWithSpecificDelimitor(innerQueryOptions, " and ")+" ) ");
            }

            sourceInnerQuery = " source_ids && (select array_agg(source_id) from target_search.source_classification_master where "+stringUtility.prepareStringWithSpecificDelimitor(finalQueryOptions, " or ")+") ";
            sourceOuterQuery = " source_id in (select source_id from target_search.source_classification_master where "+stringUtility.prepareStringWithSpecificDelimitor(finalQueryOptions, " or ")+") ";

        }

        sourceQueries.put("Inner", sourceInnerQuery);
        sourceQueries.put("Outer", sourceOuterQuery);
        sourceQueries.put("psw", StringUtils.EMPTY);
        sourceQueries.put("orstatus", orStatus);

        return sourceQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkSourceCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

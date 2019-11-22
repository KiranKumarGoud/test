package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.AllMappingIdsComponent;
import com.excelra.mvc.repository.AllMappingQueryComponent;
import com.excelra.mvc.repository.TargetDAO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * Target Advanced search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class TargetComponent {

    private static final String PRIMARY = "Primary";

    private static final String PROFILE = "Profile";

    private static final String SECONDARY = "Secondary";

    private static final String LOCUS_OPTION = "enterez";

    private static final String UNIPROT_OPTION = "upprot";

    private static final String PDB_OPTION = "pdb";

    private static final String OFFICALNAME_OPTION = "office";

    private static final String COMMON_OPTION = "common";

    @Value("${target.advsearch.locus.in.query}")
    private String targetAdvSearchLocusInQuery;

    @Value("${target.advsearch.officialname.in.query}")
    private String targetAdvSearchOfficialnameInQuery;

    @Value("${target.advsearch.uniprot.in.query}")
    private String targetAdvSearchUniprotInQuery;

    @Value("${target.advsearch.pdbids.in.query}")
    private String targetAdvSearchPdbIdsInQuery;

    @Value("${target.advsearch.commonname.in.query}")
    private String targetAdvSearchCommonnameInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     * Target field Advanced Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param searchCountInputDTO
     * @param userJdbc
     * @return
     */
    public Map<String, String> prepareTargetAdvancedSearchQuery(SearchCountInputDTO searchCountInputDTO, UserJdbc userJdbc) {

        Map<String, String> targetOrgQuery = new HashMap<>();

        List<BigDecimal> andList = new ArrayList<>();
        List<BigDecimal> orList = new ArrayList<>();
        List<BigDecimal> notList = new ArrayList<>();

        List<String> pswValues = new ArrayList<>();

        if (searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getTargetCategory().isEmpty()) {
            pswValues.add("p");
            pswValues.add("s");
            pswValues.add("m");
        } else {
            for (String objStr : searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getTargetCategory()) {
                /* if (objStr.isEmpty()) {*/
                if (objStr.equalsIgnoreCase(PRIMARY)) {
                    pswValues.add("p");
                } else if (objStr.equalsIgnoreCase(SECONDARY)) {
                    pswValues.add("s");
                } else if (objStr.equalsIgnoreCase(PROFILE)) {
                    pswValues.add("m");
                }
            }
            /*}*/
        }

        if (Objects.nonNull(searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getTargetAdvSearchInputDTOList()) && !searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getTargetAdvSearchInputDTOList().isEmpty()) {

            // For Targets
            List<TargetAdvSearchInputDTO> targetAdvSearchInputDTOList = searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getTargetAdvSearchInputDTOList();

            for (TargetAdvSearchInputDTO targetAdvSearchInputDTO : targetAdvSearchInputDTOList) {
                if (targetAdvSearchInputDTO.getOperator().equals("&")) {
                    andList.add(targetAdvSearchInputDTO.getStdnameId());
                } else if (targetAdvSearchInputDTO.getOperator().equals("|")) {
                    orList.add(targetAdvSearchInputDTO.getStdnameId());
                } else if (targetAdvSearchInputDTO.getOperator().equals("!")) {
                    notList.add(targetAdvSearchInputDTO.getStdnameId());
                }
            }
            orList.addAll(andList);

            // targetOrgQuery = allMappingIdsComponent.prepareTargetQuery_Version2(andList, orList, notList, (pswValues.size() > 0) ? " p_s_m in (" + allMappingIdsComponent.prepareString(pswValues) + ")" : StringUtils.EMPTY);

            targetOrgQuery = prepareTargetQuery(andList, orList, notList, (pswValues.size() > 0) ? " p_s_m in (" + stringUtility.prepareString(pswValues) + ")" : StringUtils.EMPTY);

        } else if (Objects.nonNull(searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData()) && !searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData().isEmpty()) {

            List<TargetAdvSearchInputDTO> targetAdvSearchInputDTOList = null;
            String inQueryValues = StringUtils.EMPTY;
            String mainQuery = StringUtils.EMPTY;

            if (searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getOption().equalsIgnoreCase(LOCUS_OPTION)) {

                List<String> locusIdList = new ArrayList<>();
                for (String rec : searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData()) {
                    if (rec.matches("-?\\d+(\\.\\d+)?")) {
                        locusIdList.add(rec);
                    }
                }

                if (!locusIdList.isEmpty()) {
                    String locusList = StringUtils.join(locusIdList, "', '");
                    if(!locusList.isEmpty()) {
                        mainQuery = targetAdvSearchLocusInQuery;
                        inQueryValues = "('" + locusList + "')";
                    }
                }

            } else if (searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getOption().equalsIgnoreCase(UNIPROT_OPTION)) {

                String uniprotList = StringUtils.join(searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData(), "','");
                if(!uniprotList.isEmpty()) {
                    mainQuery = targetAdvSearchUniprotInQuery;
                    inQueryValues = "('" + uniprotList + "')";
                }

            } else if (searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getOption().equalsIgnoreCase(PDB_OPTION)) {

                String pdbList = StringUtils.join(searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData(), "','");
                if(!pdbList.isEmpty()) {
                    mainQuery = targetAdvSearchPdbIdsInQuery;
                    inQueryValues = "('" + pdbList + "')";
                }

            } else if (searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getOption().equalsIgnoreCase(OFFICALNAME_OPTION)) {

                String officialnameList = StringUtils.join(searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData(), "','");
                if(!officialnameList.isEmpty()) {
                    mainQuery = targetAdvSearchOfficialnameInQuery;
                    inQueryValues = "('" + officialnameList + "')";
                }

            } else if (searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getOption().equalsIgnoreCase(COMMON_OPTION)) {

                String commonList = StringUtils.join(searchCountInputDTO.getAdvancedSearch().getTargetAdvSearchDTO().getFileData(), "','");
                if(!commonList.isEmpty()) {
                    mainQuery = targetAdvSearchCommonnameInQuery;
                    inQueryValues = "('" + commonList + "')";
                }

            }

            if(!mainQuery.isEmpty() && !inQueryValues.isEmpty()) {
                targetAdvSearchInputDTOList = userJdbc.getJdbcTemplate().query(mainQuery + inQueryValues,
                        new TargetDAO.TargetAdvSearchInputDTODataMapper()
                );
            }

            if (Objects.nonNull(targetAdvSearchInputDTOList) && !targetAdvSearchInputDTOList.isEmpty()) {
                for (TargetAdvSearchInputDTO targetAdvSearchInputDTO : targetAdvSearchInputDTOList) {
                    orList.add(targetAdvSearchInputDTO.getStdnameId());
                }
            }
            // targetOrgQuery = allMappingIdsComponent.prepareTargetQuery_Version2(andList, orList, notList, (pswValues.size() > 0) ? " p_s_m in (" + allMappingIdsComponent.prepareString(pswValues) + ")" : StringUtils.EMPTY);

            targetOrgQuery = prepareTargetQuery(andList, orList, notList, (pswValues.size() > 0) ? " p_s_m in (" + stringUtility.prepareString(pswValues) + ")" : StringUtils.EMPTY);

        }
        return targetOrgQuery;
    }

    /**
     * Target field Simple Search query preparation based on selected And, Or and Not Combinations.
     *
     * @param andList
     * @param orList
     * @param notList
     * @param pswQuery
     * @return
     */
    public Map<String, String> prepareTargetQuery(List<BigDecimal> andList, List<BigDecimal> orList, List<BigDecimal> notList, String pswQuery) {

        String orStatus = "false";
        String isTargetQuery = "false";

        String targetInnerQuery = StringUtils.EMPTY;
        String targetOuterQuery = StringUtils.EMPTY;

        Map<String, String> targetQueries = new HashMap<>();

        if (andList.size() > 0 && notList.size() > 0 && orList.size() > 0) {
            targetInnerQuery = " ( stdname_ids @> cast(array[" + StringUtils.join(andList, ',') + "] AS bigint[]) "
                    + " and not stdname_ids && cast(array[" + StringUtils.join(notList, ',') + "] AS bigint[]) ) ";

            targetOuterQuery = " stdname_id in (" + StringUtils.join(orList, ',') + ") ";

        } else if (andList.size() > 0 && orList.size() > 0) {
            targetInnerQuery = " ( stdname_ids @> cast(array[" + StringUtils.join(andList, ',') + "] AS bigint[]) ) ";

            targetOuterQuery = " stdname_id in (" + StringUtils.join(orList, ',') + ") ";

        } else if (notList.size() > 0 && orList.size() > 0) {
            targetInnerQuery = " ( stdname_ids @> cast(array[" + StringUtils.join(orList, ',') + "] AS bigint[]) "
                    + " and not stdname_ids && cast(array[" + StringUtils.join(notList, ',') + "] AS bigint[]) ) ";

            targetOuterQuery = " stdname_id in (" + StringUtils.join(orList, ',') + ") ";

        } else if (andList.size() > 0 && notList.size() > 0) {
            targetInnerQuery = " ( stdname_ids @> cast(array[" + StringUtils.join(andList, ',') + "] AS bigint[]) "
                    + " and not stdname_ids && cast(array[" + StringUtils.join(notList, ',') + "] AS bigint[]) ) ";

            targetOuterQuery = " stdname_id in (" + StringUtils.join(andList, ',') + ") ";

        } else if (andList.size() > 0) {
            targetInnerQuery = " ( stdname_ids @> cast(array[" + StringUtils.join(andList, ',') + "] AS bigint[]) ) ";

            targetOuterQuery = " stdname_id in (" + StringUtils.join(andList, ',') + ") ";

        } else if (orList.size() > 0) {
            targetInnerQuery = " ( stdname_ids &&  cast(array[" + StringUtils.join(orList, ',') + "] AS bigint[]) ) ";

            targetOuterQuery = " stdname_id in (" + StringUtils.join(orList, ',') + ") ";

            orStatus = "true";
            isTargetQuery = "true";
        }

        targetQueries.put("Inner", targetInnerQuery);
        targetQueries.put("Outer", targetOuterQuery);
        targetQueries.put("psw", pswQuery);
        targetQueries.put("orstatus", orStatus);
        targetQueries.put("isTargetQuery", isTargetQuery);

        return targetQueries;
    }

}

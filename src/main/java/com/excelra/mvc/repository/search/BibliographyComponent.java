package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.Bibliography.BibliographyAdvSearchDTO;
import com.excelra.mvc.model.search.Bibliography.CustomSearch;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import com.excelra.mvc.model.*;
import com.excelra.mvc.model.search.Bibliography.CriterionSearch;
import com.excelra.mvc.model.search.Bibliography.ListSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Bibliography search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class BibliographyComponent {

    //  private String criterionQuery = "select ref_id as refId, author_s_no as authorSNo, author as author, company_s_no as companySNo, company_name as companyName, ref_type as refType, ref_year as refYear, ref_author_id as refAuthorId, ref_company_id as refCompanyId from reference_search.list_author_companies where ";

    @Autowired
    private StringUtility stringUtility;

    /**
     * Bibliography (Reference) field Simple Search query preparation.
     *
     * @param refIdList
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(List<BigDecimal> refIdList) {

        String refInnerQuery = StringUtils.EMPTY;
        String refOuterQuery = StringUtils.EMPTY;

        Map<String, String> bibliographyQueries = new HashMap<>();

        if(refIdList.size() > 0)
            refOuterQuery = " ref_id in ( " + StringUtils.join(refIdList, ',') + " ) ";

        bibliographyQueries.put("Inner", refInnerQuery);
        bibliographyQueries.put("Outer", refOuterQuery);
        bibliographyQueries.put("psw",StringUtils.EMPTY);
        bibliographyQueries.put("orstatus","true");

        return bibliographyQueries;
    }

    /**
     * Bibliography (Reference) advanced search query combinations preparation
     *
     * @param bibliographyAdvSearchDTO
     * @return
     */
    public Map<String, String> getAdvancedSearchQueryCombination(BibliographyAdvSearchDTO bibliographyAdvSearchDTO, UserJdbc userJdbc) {

        List<Long> refIdList = new ArrayList<>();

        String refInnerQuery = StringUtils.EMPTY;
        String refOuterQuery = StringUtils.EMPTY;

        Map<String, String> bibliographyQueries = new HashMap<>();

        if(Objects.nonNull(bibliographyAdvSearchDTO.getListSearch())) {

            // This is for List Search Block
            refIdList = prepareListSearch(bibliographyAdvSearchDTO.getListSearch(), userJdbc);

        } else if(Objects.nonNull(bibliographyAdvSearchDTO.getCriterionSearch())) {

            // This is for Criterion Search Block
            List<ListAuthorCompaniesDTO> listAuthorCompaniesDTOs = prepareCriterionSearch(bibliographyAdvSearchDTO.getCriterionSearch(), userJdbc);
            for(ListAuthorCompaniesDTO listAuthorCompaniesDTO : listAuthorCompaniesDTOs) {
                refIdList.add(listAuthorCompaniesDTO.getRefId());
            }

        } else if(Objects.nonNull(bibliographyAdvSearchDTO.getCustomSearch())) {

            // This is for Custom Search Block - Yet to Implement
            refIdList = prepareCustomSearch(bibliographyAdvSearchDTO.getCustomSearch(), userJdbc);
        }

        if (refIdList.size() > 0)
            refOuterQuery = " ref_id in ( " + StringUtils.join(refIdList, ',') + " ) ";

        bibliographyQueries.put("Inner", refInnerQuery);
        bibliographyQueries.put("Outer", refOuterQuery);
        bibliographyQueries.put("psw",StringUtils.EMPTY);
        bibliographyQueries.put("orstatus","true");

        return bibliographyQueries;
    }

    /**
     *
     * @param listSearch
     * @return
     */
    private List<Long> prepareListSearch(ListSearch listSearch, UserJdbc userJdbc) {

        List<Long> refIdList = new ArrayList<>();

        // For PlumbedId Option
        if(Objects.nonNull(listSearch.getBibliographyPubmedList())) {
            List<BibliographyPubmedDTO> bibliographyPubmedDTOList = listSearch.getBibliographyPubmedList();
            for(BibliographyPubmedDTO bibliographyPubmedDTO : bibliographyPubmedDTOList) {
                refIdList.add(bibliographyPubmedDTO.getRefId());
            }
        }

        // For Doi Option
        if(Objects.nonNull(listSearch.getBibliographyDoiDTOList())) {
            List<BibliographyDoiDTO> bibliographyDoiDTOList = listSearch.getBibliographyDoiDTOList();
            for(BibliographyDoiDTO bibliographyDoiDTO : bibliographyDoiDTOList) {
                refIdList.add(bibliographyDoiDTO.getRefId());
            }
        }

        // For Issn Option
        if(Objects.nonNull(listSearch.getBibliographyIssnNoDTOList())) {
            List<BibliographyIssnNoDTO> bibliographyIssnNoDTOList = listSearch.getBibliographyIssnNoDTOList();
            for(BibliographyIssnNoDTO bibliographyIssnNoDTO : bibliographyIssnNoDTOList) {
                refIdList.add(bibliographyIssnNoDTO.getRefId());
            }
        }

        // For MesH Option
        if(Objects.nonNull(listSearch.getBibliographyMeshDTOList())) {
            List<BibliographyMeshDTO> bibliographyMeshDTOList = listSearch.getBibliographyMeshDTOList();
            for(BibliographyMeshDTO bibliographyMeshDTO : bibliographyMeshDTOList) {
                refIdList.add(bibliographyMeshDTO.getRefId());
            }
        }

        // Ref Ids Option
        if(Objects.nonNull(listSearch.getRefComboData()) && !listSearch.getRefComboData().isEmpty()) {
            for(String str : listSearch.getRefComboData()) {
                refIdList.add(Long.parseLong(str));
            }
        }

        // Import data Option
        if(Objects.nonNull(listSearch.getRefComboFileData()) && !listSearch.getRefComboFileData().isEmpty()) {
            refIdList = prepareFileComboDataForRefIdList(listSearch.getRefReadonly(), listSearch.getRefComboFileData(), userJdbc);
        }

        return refIdList;
    }

    /**
     *
     * @param criterionSearch
     * @param userJdbc
     * @return
     */
    private List<ListAuthorCompaniesDTO> prepareCriterionSearch(CriterionSearch criterionSearch, UserJdbc userJdbc) {

        String criterionQuery = "select ref_id as refId, author_s_no as authorSNo, author as author, company_s_no as companySNo, company_name as companyName, ref_type as refType, ref_year as refYear, ref_author_id as refAuthorId, ref_company_id as refCompanyId from reference_search.list_author_companies where ";

        List<String> authors = new ArrayList<>();
        if(Objects.nonNull(criterionSearch.getListAuthorsDTOs()) && !criterionSearch.getListAuthorsDTOs().isEmpty()) {
            List<ListAuthorsDTO> listAuthorsDTOs = criterionSearch.getListAuthorsDTOs();
            for (ListAuthorsDTO listAuthorsDTO : listAuthorsDTOs) {
                authors.add(listAuthorsDTO.getAuthor().replaceAll("'", "''"));
            }
        }
        if(Objects.nonNull(criterionSearch.getAuthorsFileData()) && !criterionSearch.getAuthorsFileData().isEmpty()) {
            for(String authorStr : criterionSearch.getAuthorsFileData()) {
                authors.add(authorStr.replaceAll("'", "''"));
            }
        }

        List<String> companNames = new ArrayList<>();
        if(Objects.nonNull(criterionSearch.getListCompaniesDTOs()) && !criterionSearch.getListCompaniesDTOs().isEmpty()) {
            List<ListCompaniesDTO> listCompaniesDTOs = criterionSearch.getListCompaniesDTOs();
            for(ListCompaniesDTO listCompaniesDTO : listCompaniesDTOs) {
                companNames.add(listCompaniesDTO.getCompanyName().replaceAll("'", "''"));
            }
        }
        if(Objects.nonNull(criterionSearch.getCompaniesFileData()) && !criterionSearch.getCompaniesFileData().isEmpty()) {
            for(String companyStr : criterionSearch.getCompaniesFileData()) {
                companNames.add(companyStr.replaceAll("'", "''"));
            }
        }

        Boolean checkAuthor = Boolean.FALSE;
        Boolean checkCompanyName = Boolean.FALSE;
        Boolean checkYears = Boolean.FALSE;

        if(!authors.isEmpty()) {
            // criterionQuery = criterionQuery + " author in ( "+String.join(",", authors.stream().map(name -> ("'" + name + "'")).collect(Collectors.toList()))+" ) ";
            criterionQuery = criterionQuery + " author in ( "+stringUtility.prepareInStringList(authors)+" ) ";
            checkAuthor = Boolean.TRUE;
        }

        if(!companNames.isEmpty()) {
            if(checkAuthor) criterionQuery = criterionQuery + " and ";
            // criterionQuery = criterionQuery + " company_name in ( "+String.join(",", companNames.stream().map(name -> ("'" + name + "'")).collect(Collectors.toList()))+" ) ";
            criterionQuery = criterionQuery + " company_name in ( "+stringUtility.prepareInStringList(companNames)+" ) ";
            checkCompanyName = Boolean.TRUE;
        }

        if(Objects.nonNull(criterionSearch.getFromYear()) && Objects.nonNull(criterionSearch.getToYear())) {
            if(checkAuthor || checkCompanyName) criterionQuery = criterionQuery + " and ";
            criterionQuery = criterionQuery + " ref_year between "+criterionSearch.getFromYear()+" and "+criterionSearch.getToYear();
            checkYears = Boolean.TRUE;
        }

        if(Objects.nonNull(criterionSearch.getDataSource())) {
            if(checkAuthor || checkCompanyName || checkYears) criterionQuery = criterionQuery + " and ";
            criterionQuery = criterionQuery + " ref_type in ("+stringUtility.prepareInStringList(criterionSearch.getDataSource()).toUpperCase()+") ";
        }

        System.out.println("Final Criterion Search Query ===> "+criterionQuery);

        List<ListAuthorCompaniesDTO> listAuthorCompaniesDTOs = userJdbc.getJdbcTemplate().query(criterionQuery, new BeanPropertyRowMapper<>(ListAuthorCompaniesDTO.class));

        return listAuthorCompaniesDTOs;
    }

    /**
     *
     * @param customSearch
     * @param userJdbc
     * @return
     */
    private List<Long> prepareCustomSearch(CustomSearch customSearch, UserJdbc userJdbc) {

        List<String> journalQueryList = null;
        List<String> patentQueryList = null;

        if(Objects.nonNull(customSearch.getJournalData()) && !customSearch.getJournalData().isEmpty()) {
            journalQueryList = new ArrayList<>();
            for(JonurnalInputDTO jonurnalInputDTO : customSearch.getJournalData()) {
                String innerJournalQuery = StringUtils.EMPTY;
                if(!jonurnalInputDTO.getJournalName().isEmpty()) {
                    innerJournalQuery = innerJournalQuery + "journal_patent_name in ('"+jonurnalInputDTO.getJournalName()+"')";
                }

                if(Objects.nonNull(jonurnalInputDTO.getYear()) && !jonurnalInputDTO.getYear().isEmpty()) {
                    innerJournalQuery = innerJournalQuery + " and ref_year in ("+stringUtility.prepareInStringList(jonurnalInputDTO.getYear())+") ";
                }

                if(Objects.nonNull(jonurnalInputDTO.getIssue()) && !jonurnalInputDTO.getIssue().isEmpty()) {
                    innerJournalQuery = innerJournalQuery + " and issue in ("+stringUtility.prepareInStringList(jonurnalInputDTO.getIssue())+") ";
                }

                if(Objects.nonNull(jonurnalInputDTO.getVolume()) && !jonurnalInputDTO.getVolume().isEmpty()) {
                    innerJournalQuery = innerJournalQuery + " and volume in ("+stringUtility.prepareInStringList(jonurnalInputDTO.getVolume())+") ";
                }

                if(Objects.nonNull(jonurnalInputDTO.getPageNo()) && !jonurnalInputDTO.getPageNo().isEmpty()) {
                    innerJournalQuery = innerJournalQuery + " and start_page in ("+stringUtility.prepareInStringList(jonurnalInputDTO.getPageNo())+") ";
                }

                journalQueryList.add(" ("+innerJournalQuery+") ");
            }
        }

        if(Objects.nonNull(customSearch.getPatentData()) && !customSearch.getPatentData().isEmpty()) {

            List<String> countryCodes = new ArrayList<>();
            List<String> years = new ArrayList<>();

            patentQueryList = new ArrayList<>();

            for(PatentInputDTO patentInputDTO : customSearch.getPatentData()) {
                String innerPatentQuery = StringUtils.EMPTY;
                if(patentInputDTO.getCountryCode().size() == 1 && patentInputDTO.getYear().size() == 1) {

                    if(Objects.nonNull(patentInputDTO.getCountryCode()) && !patentInputDTO.getCountryCode().isEmpty()) {
                        innerPatentQuery = innerPatentQuery + " patent_no like '"+patentInputDTO.getCountryCode()+"%' ";
                    }

                    if(Objects.nonNull(patentInputDTO.getYear()) && !patentInputDTO.getYear().isEmpty()) {
                        innerPatentQuery = innerPatentQuery + " and ref_year in ('"+patentInputDTO.getYear()+"') ";
                    }

                    if(Objects.nonNull(patentInputDTO.getPatentNo()) && !patentInputDTO.getPatentNo().isEmpty()) {
                        innerPatentQuery = innerPatentQuery + " and patent_no in ("+stringUtility.prepareInStringList(patentInputDTO.getPatentNo())+") ";
                    }

                    patentQueryList.add(" ("+innerPatentQuery+") ");

                } else {

                    if(Objects.nonNull(patentInputDTO.getCountryCode()) && !patentInputDTO.getCountryCode().isEmpty()) {
                        countryCodes.addAll(patentInputDTO.getCountryCode());
                    }

                    if(Objects.nonNull(patentInputDTO.getYear()) && !patentInputDTO.getYear().isEmpty()) {
                        years.addAll(patentInputDTO.getYear());
                    }
                }
            }

            if(!countryCodes.isEmpty() && !years.isEmpty()) {
                List<String> countryCodeQueryStr = new ArrayList<>();
                for(String codeStr : countryCodes) {
                    countryCodeQueryStr.add(" patent_no like '"+codeStr+"%' ");
                }
                String countryCodesQuery = "( "+String.join(" or ", countryCodeQueryStr)+" )";
                String yearsQuery = " and ref_year in ("+stringUtility.prepareInStringList(years)+") ";

                patentQueryList.add(" ("+countryCodesQuery + yearsQuery+") ");
            }
        }

        List<String> finalCombinations = new ArrayList<>();
        if(Objects.nonNull(journalQueryList)) finalCombinations.add(String.join(" or ", journalQueryList));
        if(Objects.nonNull(patentQueryList))  finalCombinations.add(String.join(" or ", patentQueryList));

        String customQuery = "select ref_id from reference_search.reference_master where ("+String.join(" or ", finalCombinations)+")";

        System.out.println(" Reference Ids for Custom Search Query ----> "+customQuery);

        List<Long> customQueryResults = userJdbc.getJdbcTemplate().query(customQuery, new ReferenceIdDataMapper());

        return customQueryResults;
    }

    /**
     *
     * @param refReadonly
     * @param refComboFileData
     * @return
     */
    private List<Long> prepareFileComboDataForRefIdList(String refReadonly, List<String> refComboFileData, UserJdbc userJdbc) {

        List<Long> refIdList = new ArrayList<>();

        switch(refReadonly) {

            case "pubmed_id" :

                List<Long> pubmedIdList = new ArrayList<>();
                for(String pubmedId : refComboFileData) {
                    if(isNumeric(pubmedId)) {
                        pubmedIdList.add(Long.parseLong(pubmedId));
                    }
                }
                if(!pubmedIdList.isEmpty()) {
                    String pubmedQuery = "select ref_id from reference_search.reference_master where pubmed_id in ("+stringUtility.prepareInNumbericList(pubmedIdList)+")";
                    refIdList = userJdbc.getJdbcTemplate().query(pubmedQuery, new ReferenceIdDataMapper());
                }

                break;
            case "doi" :

                if(!refComboFileData.isEmpty()) {
                    String doiQuery = "select ref_id from reference_search.reference_master where doi in ("+stringUtility.prepareInStringList(refComboFileData)+")";
                    refIdList = userJdbc.getJdbcTemplate().query(doiQuery, new ReferenceIdDataMapper());
                }

                break;
            case "issn_no" :

                if(!refComboFileData.isEmpty()) {
                    String issnNoQuery = "select ref_id from reference_search.reference_master where issn_no in ("+stringUtility.prepareInStringList(refComboFileData)+")";
                    refIdList = userJdbc.getJdbcTemplate().query(issnNoQuery, new ReferenceIdDataMapper());
                }

                break;
            case "mesh" :

                if(!refComboFileData.isEmpty()) {
                    String meshQuery = "select ref_id from reference_search.refs_pubmed_mesh where mesh in ("+stringUtility.prepareInStringList(refComboFileData)+")";
                    refIdList = userJdbc.getJdbcTemplate().query(meshQuery, new ReferenceIdDataMapper());
                }

                break;
            case "ref_id" :

                for(String refId : refComboFileData) {
                    if(isNumeric(refId)) {
                        refIdList.add(Long.parseLong(refId));
                    }
                }

                break;
            default:
                System.out.println("No Proper option is selected");
        }

        return refIdList;
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     *
     */
    public static final class ReferenceIdDataMapper implements RowMapper<Long> {
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong("ref_id");
        }
    }
}

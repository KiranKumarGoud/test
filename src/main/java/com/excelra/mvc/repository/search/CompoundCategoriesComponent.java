package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.ListCompoundCategoriesDTO;
import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Compound Categories search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class CompoundCategoriesComponent {

    @Autowired
    private StringUtility stringUtility;


    public Map<String, String> prepareCompoundCategoriesSimpleQuery(List<ListCompoundCategoriesDTO> compoundCategoriesDTOList) {

        Map<String, String> compoundCategoriesQueries = new HashMap<>();

        String compoundCategoriesInnerQuery = StringUtils.EMPTY;
        String compoundCategoriesOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        List<String> compoundCategoriesList = new ArrayList<>();

        for(ListCompoundCategoriesDTO listCompoundCategoriesDTO : compoundCategoriesDTOList) {
            compoundCategoriesList.add(listCompoundCategoriesDTO.getCompoundCategory());
        }

        if(!compoundCategoriesList.isEmpty()) {
            compoundCategoriesInnerQuery = " compound_categories && cast(array["+stringUtility.prepareInStringList(compoundCategoriesList)+"] as varchar[]) ";

            orStatus = "str_id in ( select str_id from target_search.str_id_based where compound_categories && cast(array["+stringUtility.prepareInStringList(compoundCategoriesList)+"] as varchar[]) )";
        }

        compoundCategoriesQueries.put("Inner", compoundCategoriesInnerQuery);
        compoundCategoriesQueries.put("Outer", compoundCategoriesOuterQuery);
        compoundCategoriesQueries.put("psw", StringUtils.EMPTY);
        compoundCategoriesQueries.put("orstatus", orStatus);

        return compoundCategoriesQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkCompoundCategoriesCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }
}

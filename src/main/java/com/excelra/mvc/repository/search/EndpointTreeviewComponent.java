package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.treeview.EndpointTreeviewDTO;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity Endpoint Treeview search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class EndpointTreeviewComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTreeviewComponent.class);

    @Autowired
    private StringUtility stringUtility;

    /**
     *
     * @param endpointTreeviewDTO
     * @return
     */
    public Map<String, String> getSimpleSearchQueryCombination(EndpointTreeviewDTO endpointTreeviewDTO) {

        Map<String, String> endpointQueries = new HashMap<>();

        String endpointInnerQuery = StringUtils.EMPTY;
        String endpointOuterQuery = StringUtils.EMPTY;

        String orStatus = StringUtils.EMPTY;

        if(!endpointTreeviewDTO.getEndpointId().isEmpty()) {

            endpointInnerQuery = " endpoint_ids && (select array_agg(child_endpoint_id) from target_search.endpoint_all_childs epac " +
                    "where epac.endpoint_id in ("+stringUtility.prepareInNumbericList(endpointTreeviewDTO.getEndpointId())+")) ";

            endpointOuterQuery = " endpoint_id in (select child_endpoint_id from target_search.endpoint_all_childs epac " +
                    "where epac.endpoint_id in ("+stringUtility.prepareInNumbericList(endpointTreeviewDTO.getEndpointId())+")) ";

            orStatus = " endpoint_id in (select child_endpoint_id from target_search.endpoint_all_childs epac where " +
                    "epac.endpoint_id in ("+stringUtility.prepareInNumbericList(endpointTreeviewDTO.getEndpointId())+")) ";
        }

        endpointQueries.put("Inner", endpointInnerQuery);
        endpointQueries.put("Outer", endpointOuterQuery);
        endpointQueries.put("psw", StringUtils.EMPTY);
        endpointQueries.put("orstatus", orStatus);

        return endpointQueries;
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public Boolean checkEndpointTreeviewCombinationStatus(List<SearchCountInputDTO> searchCountInputDTOList) {
        int increment = searchCountInputDTOList.size();

        return (increment == 1) ? Boolean.TRUE : Boolean.FALSE;

    }

}

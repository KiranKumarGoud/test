package com.excelra.mvc.model.userjdbc;

import com.excelra.mvc.model.search.SearchCountInputDTO;

import java.io.Serializable;
import java.util.List;

public class UserSearch implements Serializable {

    private String userSessionId;

    private List<SearchCountInputDTO> searchCountInputDTOList;

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public List<SearchCountInputDTO> getSearchCountInputDTOList() {
        return searchCountInputDTOList;
    }

    public void setSearchCountInputDTOList(List<SearchCountInputDTO> searchCountInputDTOList) {
        this.searchCountInputDTOList = searchCountInputDTOList;
    }
}

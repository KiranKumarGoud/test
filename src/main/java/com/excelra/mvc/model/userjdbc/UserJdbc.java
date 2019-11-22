package com.excelra.mvc.model.userjdbc;

import com.excelra.mvc.model.search.SearchCountInputDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.List;

public class UserJdbc implements Serializable {

    private String userSessionId;

    private JdbcTemplate jdbcTemplate;

    private List<SearchCountInputDTO> searchCountInputDTOList;

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SearchCountInputDTO> getSearchCountInputDTOList() {
        return searchCountInputDTOList;
    }

    public void setSearchCountInputDTOList(List<SearchCountInputDTO> searchCountInputDTOList) {
        this.searchCountInputDTOList = searchCountInputDTOList;
    }
}

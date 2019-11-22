package com.excelra.mvc.service;

import com.excelra.mvc.model.ListOfficialNameDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IListOfficialName {

    List<ListOfficialNameDTO> findByOfficialName(String officialName, UserJdbc userJdbc);
}

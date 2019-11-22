package com.excelra.mvc.service;

import com.excelra.mvc.model.ListCompoundCategoriesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface ICompoundCategories {

    List<ListCompoundCategoriesDTO> fetchCompoundCategoriesList(UserJdbc userJdbc);

}

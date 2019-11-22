package com.excelra.mvc.service;

import com.excelra.mvc.model.ListClinicalPhasesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IClinicalPhases {

    List<ListClinicalPhasesDTO> fetchClinicalPhasesList(UserJdbc userJdbc);

}

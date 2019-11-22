package com.excelra.mvc.service;

import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IIndication {

    List<ListTherapeuticUseDTO> fetchTherapeuicListByContaining(String therapeuic, UserJdbc userJdbc);

    List<ListIcd10CodesDTO> fetchIcd10CodesListByContaining(String icd10Code, UserJdbc userJdbc);
}

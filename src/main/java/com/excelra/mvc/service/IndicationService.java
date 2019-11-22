package com.excelra.mvc.service;

import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.IndicationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicationService implements IIndication {

    @Autowired
    private IndicationDAO indicationDAO;

    @Override
    public List<ListTherapeuticUseDTO> fetchTherapeuicListByContaining(String therapeuic, UserJdbc userJdbc) {
        return indicationDAO.fetchTherapeuicListByContaining(therapeuic, userJdbc);
    }

    @Override
    public List<ListIcd10CodesDTO> fetchIcd10CodesListByContaining(String icd10Code, UserJdbc userJdbc) {
        return indicationDAO.fetchIcd10CodesListByContaining(icd10Code, userJdbc);
    }
}

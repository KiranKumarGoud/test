package com.excelra.mvc.service;

import com.excelra.mvc.model.TargetProteinMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import java.util.List;

public interface ITargetProteinMaster {

    List<TargetProteinMasterDTO> findByCommonName(String commonName, UserJdbc userJdbc);

}

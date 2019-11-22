package com.excelra.mvc.service;

import com.excelra.mvc.model.ListActivityMechanismDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
public interface IActivityMechanism {

    List<ListActivityMechanismDTO> fetchActivityMechanism(UserJdbc userJdbc);
}

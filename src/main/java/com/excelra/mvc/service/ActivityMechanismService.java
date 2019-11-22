package com.excelra.mvc.service;

import com.excelra.mvc.model.ListActivityMechanismDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ActivityMechanismDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author venkateswarlu.s
 */
@Service
public class ActivityMechanismService implements IActivityMechanism {

    @Autowired
    private ActivityMechanismDAO activityMechanismDAO;

    /**
     *
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListActivityMechanismDTO> fetchActivityMechanism(UserJdbc userJdbc) {

        return activityMechanismDAO.fetchActivityMechanism(userJdbc);

    }
}

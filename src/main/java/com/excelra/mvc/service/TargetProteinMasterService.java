package com.excelra.mvc.service;

import com.excelra.mvc.model.TargetProteinMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.TargetProteinMasterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  target protein master service.
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class TargetProteinMasterService implements ITargetProteinMaster {

    @Autowired
    private TargetProteinMasterDAO targetProteinMasterDAO;

    /**
     *
     * @param commonName
     * @return
     */
    @Override
    public List<TargetProteinMasterDTO> findByCommonName(String commonName, UserJdbc userJdbc) {
        return targetProteinMasterDAO.findByCommonName(commonName.toUpperCase(), userJdbc);
    }

}

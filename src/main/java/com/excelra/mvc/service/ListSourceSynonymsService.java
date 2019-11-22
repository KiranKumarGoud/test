package com.excelra.mvc.service;

import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.excelra.mvc.model.source.ListSourceTaxIdsDTO;
import com.excelra.mvc.model.source.SourceClassificationMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.ListSourceSynonymsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  List Source synonyms service.
 * <p>
 *
 * @author venkateswarlu.s
 */
@Service
public class ListSourceSynonymsService implements IListSourceSynonyms {

    @Autowired
    private ListSourceSynonymsDAO listSourceSynonymsDAO;

    /**
     *
     * @param sourceName
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListSourceSynonymsDTO> findBySourcenameContaining(String sourceName, UserJdbc userJdbc) {
        return listSourceSynonymsDAO.findBySourcenameContaining(sourceName, userJdbc);
    }

    /**
     *
     * @param synonyms
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListSourceSynonymsDTO> findBySynonymContaining(String synonyms, UserJdbc userJdbc) {
        return listSourceSynonymsDAO.findBySynonymContaining(synonyms, userJdbc);
    }

    /**
     *
     * @param taxId
     * @param userJdbc
     * @return
     */
    @Override
    public List<ListSourceTaxIdsDTO> findByTaxIdContaining(String taxId, UserJdbc userJdbc) {
        return listSourceSynonymsDAO.findByTaxIdContaining(taxId, userJdbc);
    }

    /**
     *
     * @param genus
     * @param userJdbc
     * @return
     */
    @Override
    public List<SourceClassificationMasterDTO> findByGenusContaining(String genus, UserJdbc userJdbc) {
        return listSourceSynonymsDAO.findByGenusContaining(genus, userJdbc);
    }

    /**
     *
     * @param genus
     * @param species
     * @param userJdbc
     * @return
     */
    @Override
    public List<SourceClassificationMasterDTO> findBySpeciesContaining(String genus, String species, UserJdbc userJdbc) {
        return listSourceSynonymsDAO.findBySpeciesContaining(genus, species, userJdbc);
    }

    /**
     *
     * @param genus
     * @param species
     * @param strain
     * @param userJdbc
     * @return
     */
    @Override
    public List<SourceClassificationMasterDTO> findByStrainContaining(String genus, String species, String strain, UserJdbc userJdbc) {
        return listSourceSynonymsDAO.findByStrainContaining(genus, species, strain, userJdbc);
    }
}

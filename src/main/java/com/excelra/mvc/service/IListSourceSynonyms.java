package com.excelra.mvc.service;

import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.excelra.mvc.model.source.ListSourceTaxIdsDTO;
import com.excelra.mvc.model.source.SourceClassificationMasterDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

public interface IListSourceSynonyms {

    List<ListSourceSynonymsDTO> findBySourcenameContaining(String sourceName, UserJdbc userJdbc);

    List<ListSourceSynonymsDTO> findBySynonymContaining(String synonyms, UserJdbc userJdbc);

    List<ListSourceTaxIdsDTO> findByTaxIdContaining(String taxId, UserJdbc userJdbc);

    List<SourceClassificationMasterDTO> findByGenusContaining(String genus, UserJdbc userJdbc);

    List<SourceClassificationMasterDTO> findBySpeciesContaining(String genus, String species, UserJdbc userJdbc);

    List<SourceClassificationMasterDTO> findByStrainContaining(String genus, String species, String strain, UserJdbc userJdbc);
}

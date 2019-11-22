package com.excelra.mvc.service;

import com.excelra.mvc.model.AllMappingFinalResultDTO;
import com.excelra.mvc.model.AllMappingIdsInputDTO;
import com.excelra.mvc.model.search.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * <p>
 *  All Mapping Ids Service
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface IAllMappingIds {

    String createTempTables(UserJdbc userJdbc);

    SearchVisualizationResultDTO searchWithVisualizationData(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc);

    AllMappingFinalResultCountDTO searchWithFilterVisualizationData(List<FilterInputVisualizationDTO> filterVisualInputDTOList, UserJdbc userJdbc);

    AllMappingFinalResultCountDTO searchForCounts(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc);

    List<VisualizationResultDTO> visualizationForProteinClassification(UserJdbc userJdbc);

    List<VisualizationResultDTO> visualizationForYearWiseVisualization(UserJdbc userJdbc);

    List<VisualizationResultDTO> visualizationForBibliographyVisualization(UserJdbc userJdbc);

    List<VisualizationIndicationDTO> visualizationForIndicationVisualization(UserJdbc userJdbc);

    List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForToxicityVisualization(UserJdbc userJdbc);

    List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForTaxonomyVisualization(UserJdbc userJdbc);

    List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForEndpointVisualization(UserJdbc userJdbc);

    void reloadVisualization(UserJdbc userJdbc);

    AllMappingFinalResultCountDTO searchCountsForVisualResults(UserJdbc userJdbc);

    AllMappingFinalResultCountDTO searchAllVisualizationData(UserJdbc userJdbc);

    AllMappingFinalResultCountDTO searchForDefaultCounts(UserJdbc userJdbc);
}

package com.excelra.mvc.service;

import com.excelra.mvc.model.search.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.AllMappingIdsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllMappingIdsService implements IAllMappingIds {

    @Autowired
    private AllMappingIdsDAO allMappingIdsDAO;

    /**
     *
     * @return
     */
    public String createTempTables(UserJdbc userJdbc) {
        return allMappingIdsDAO.createTempTables(userJdbc);
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public SearchVisualizationResultDTO searchWithVisualizationData(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) {
        return allMappingIdsDAO.searchWithVisualizationData(searchCountInputDTOList, userJdbc);
    }

    /**
     *
     * @param filterInputVisualDTOList
     * @return
     */
    public AllMappingFinalResultCountDTO searchWithFilterVisualizationData(List<FilterInputVisualizationDTO> filterInputVisualDTOList, UserJdbc userJdbc) {
        return allMappingIdsDAO.searchWithFilterVisualizationData(filterInputVisualDTOList, userJdbc);
    }

    /**
     *
     * @param searchCountInputDTOList
     * @return
     */
    public AllMappingFinalResultCountDTO searchForCounts(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) {
        return allMappingIdsDAO.searchForCounts(searchCountInputDTOList, userJdbc);
    }

    @Override
    public List<VisualizationResultDTO> visualizationForProteinClassification(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForProteinClassification(userJdbc);
    }

    @Override
    public List<VisualizationResultDTO> visualizationForYearWiseVisualization(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForYearWiseVisualization(userJdbc);
    }

    @Override
    public List<VisualizationResultDTO> visualizationForBibliographyVisualization(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForBibliographyVisualization(userJdbc);
    }

    @Override
    public List<VisualizationIndicationDTO> visualizationForIndicationVisualization(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForIndicationVisualization(userJdbc);
    }

    @Override
    public List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForToxicityVisualization(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForToxicityVisualization(userJdbc);
    }

    @Override
    public List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForTaxonomyVisualization(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForTaxonomyVisualization(userJdbc);
    }

    @Override
    public List<VisualizationTaxonomyEndPointToxicityDTO> visualizationForEndpointVisualization(UserJdbc userJdbc) {
        return allMappingIdsDAO.visualizationForEndpointVisualization(userJdbc);
    }


    @Override
    public void reloadVisualization(UserJdbc userJdbc) {
        allMappingIdsDAO.reloadVisualization(userJdbc);
    }

    @Override
    public AllMappingFinalResultCountDTO searchCountsForVisualResults(UserJdbc userJdbc) {
        return allMappingIdsDAO.searchCountsForVisualResults(userJdbc);
    }

    @Override
    public AllMappingFinalResultCountDTO searchAllVisualizationData(UserJdbc userJdbc) {
        return allMappingIdsDAO.searchAllVisualizationData(userJdbc);
    }

    @Override
    public AllMappingFinalResultCountDTO searchForDefaultCounts(UserJdbc userJdbc) {
        return allMappingIdsDAO.searchForDefaultCounts(userJdbc);
    }
}

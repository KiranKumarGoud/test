package com.excelra.mvc.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * AllMappingIds Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllMappingIdsServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllMappingIdsServiceTests.class);

    @Autowired
    private AllMappingIdsService allMappingIdsService;

    @Before
    public void contextLoads() {
    }

    @Test
    public void testSearchWithVisualizationData() {

        LOGGER.info(" ------------------- Executing testSearchWithVisualizationData Test case ------------------- ");

    }

    @Test
    public void testSearchWithFilterVisualizationData() {

        LOGGER.info(" ------------------- Executing testSearchWithFilterVisualizationData Test case ------------------- ");

    }

    @Test
    public void testSearchForCounts() {

        LOGGER.info(" ------------------- Executing testSearchForCounts Test case ------------------- ");

    }

    @Test
    public void testVisualizationForProteinClassification() {

        LOGGER.info(" ------------------- Executing testVisualizationForProteinClassification Test case ------------------- ");

    }

    @Test
    public void testVisualizationForYearWiseVisualization() {

        LOGGER.info(" ------------------- Executing testVisualizationForYearWiseVisualization Test case ------------------- ");

    }

    @Test
    public void testVisualizationForBibliographyVisualization() {

        LOGGER.info(" ------------------- Executing testVisualizationForBibliographyVisualization Test case ------------------- ");

    }

    @Test
    public void testVisualizationForIndicationVisualization() {

        LOGGER.info(" ------------------- Executing testVisualizationForIndicationVisualization Test case ------------------- ");

    }

    @Test
    public void testSearchCountsForVisualResults() {

        LOGGER.info(" ------------------- Executing testSearchCountsForVisualResults Test case ------------------- ");

    }

    @Test
    public void testSearchAllVisualizationData() {

        LOGGER.info(" ------------------- Executing testSearchAllVisualizationData Test case ------------------- ");

    }

    @Test
    public void testSearchForDefaultCounts() {

        LOGGER.info(" ------------------- Executing testSearchForDefaultCounts Test case ------------------- ");

    }

}

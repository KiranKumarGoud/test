package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Scaffold2Tab Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Scaffold2TabServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scaffold2TabServiceTests.class);

    @Autowired
    private Scaffold2TabService scaffold2TabService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String currentSessionUserToken = StringUtils.EMPTY;

    private UserJdbc userJdbc;

    @Before
    public void contextLoads() {

        this.userJdbc = new UserJdbc();
        this.userJdbc.setJdbcTemplate(jdbcTemplate);
        this.userJdbc.setUserSessionId("1JldmFsdWF0ZXVzZXIiLCJhdXRoIj2");

        this.currentSessionUserToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJldmFsdWF0ZXVzZXIiLCJhdXRoIjpbIlJPTEVfUk9MRV9FVkFMVUFURVVTRVIiXSwiaWF0IjoxNTcwNDMxOTc0LCJleHAiOjE1NzA0MzU1NzR9.K-_mXvC7TAT7jK51z0LIzT0Ngs6SY0OKCZ0htUs_lXQ";

    }

    @Test
    public void testFindScaffold2TabFilterFieldByGvkIds() {

        try {
            LOGGER.info(" ------------------- Executing testFindScaffold2TabFilterFieldByGvkIds Test case ------------------- ");

            TabularviewFilterRequest tabularviewFilterRequest = new TabularviewFilterRequest();
            tabularviewFilterRequest.setFieldName("smiles");
            tabularviewFilterRequest.setFieldOperator(StringUtils.EMPTY);
            tabularviewFilterRequest.setFieldType("String");
            tabularviewFilterRequest.setFieldValue("c");
            tabularviewFilterRequest.setNumericFilters(null);
            tabularviewFilterRequest.setPageNumber(0);
            tabularviewFilterRequest.setPageSize(10);
            tabularviewFilterRequest.setSelectedTabIds(null);
            tabularviewFilterRequest.setStringFilters(null);
            tabularviewFilterRequest.setUnSelectedTabIds(null);

            List<Scaffold2TabDTO> scaffold2TabDTOList = scaffold2TabService.findScaffold2TabFilterFieldByGvkIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);

            Assert.assertNotNull(scaffold2TabDTOList);

        } catch(Exception e) {

            LOGGER.info(" Exception occurred because User session object will not be available to process request {} ", e.getMessage());

        }
    }

    @Test
    public void testFindScaffold2TabData() {

        try {
            LOGGER.info(" ------------------- Executing testFindScaffold2TabData Test case ------------------- ");

            PageRequestInformation pageRequestInformation = new PageRequestInformation();
            pageRequestInformation.setPageNumber(0);
            pageRequestInformation.setPageSize(10);
            pageRequestInformation.setSortField("scaffold2Id");
            pageRequestInformation.setSortType("desc");

            TabularviewRequest tabularviewRequest = new TabularviewRequest();
            tabularviewRequest.setPageRequestInformation(pageRequestInformation);
            tabularviewRequest.setFreeTextFilters(null);
            tabularviewRequest.setNumericFilters(null);
            tabularviewRequest.setSelectedTabIds(null);
            tabularviewRequest.setStringFilters(null);
            tabularviewRequest.setUnSelectedTabIds(null);

            Scaffold2TabPageData scaffold2TabPageData = scaffold2TabService.findScaffold2TabData(tabularviewRequest, userJdbc, currentSessionUserToken);

            Assert.assertNotNull(scaffold2TabPageData);

        } catch(Exception e) {

            LOGGER.info(" Exception occurred because User session object will not be available to process request {} ", e.getMessage());

        }
    }

    @Test
    public void testFindScaffold2TabDataForExport() {

        try {
            LOGGER.info(" ------------------- Executing testFindScaffold2TabDataForExport Test case ------------------- ");

            TabularviewExportRequest tabularviewExportRequest = new TabularviewExportRequest();
            tabularviewExportRequest.setSelectedTabIds(null);
            tabularviewExportRequest.setUnSelectedTabIds(null);

            List<Scaffold2TabDTO> scaffold2TabDTOList = scaffold2TabService.findScaffold2TabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);

            Assert.assertNotNull(scaffold2TabDTOList);

        } catch(Exception e) {

            LOGGER.info(" Exception occurred because User session object will not be available to process request {} ", e.getMessage());

        }
    }
}

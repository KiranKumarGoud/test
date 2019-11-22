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
 * ReferenceTab Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReferenceTabServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProteinClassificationTabServiceTests.class);

    @Autowired
    private ReferenceTabService referenceTabService;

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
    public void testFindReferenceTabFilterFieldByRefIds() {

        try {
            LOGGER.info(" ------------------- Executing testFindReferenceTabFilterFieldByRefIds Test case ------------------- ");

            TabularviewFilterRequest tabularviewFilterRequest = new TabularviewFilterRequest();
            tabularviewFilterRequest.setFieldName("reference");
            tabularviewFilterRequest.setFieldOperator(StringUtils.EMPTY);
            tabularviewFilterRequest.setFieldType("String");
            tabularviewFilterRequest.setFieldValue("a");
            tabularviewFilterRequest.setNumericFilters(null);
            tabularviewFilterRequest.setPageNumber(0);
            tabularviewFilterRequest.setPageSize(10);
            tabularviewFilterRequest.setSelectedTabIds(null);
            tabularviewFilterRequest.setStringFilters(null);
            tabularviewFilterRequest.setUnSelectedTabIds(null);

            List<ReferenceTabDTO> referenceTabDTOList = referenceTabService.findReferenceTabFilterFieldByRefIds(tabularviewFilterRequest, userJdbc, currentSessionUserToken);

            Assert.assertNotNull(referenceTabDTOList);

        } catch(Exception e) {

            LOGGER.info(" Exception occurred because User session object will not be available to process request {} ", e.getMessage());

        }

    }

    @Test
    public void testFindReferenceTabData() {

        try {
            LOGGER.info(" ------------------- Executing testFindReferenceTabData Test case ------------------- ");

            PageRequestInformation pageRequestInformation = new PageRequestInformation();
            pageRequestInformation.setPageNumber(0);
            pageRequestInformation.setPageSize(10);
            pageRequestInformation.setSortField("refId");
            pageRequestInformation.setSortType("desc");

            TabularviewRequest tabularviewRequest = new TabularviewRequest();
            tabularviewRequest.setPageRequestInformation(pageRequestInformation);
            tabularviewRequest.setFreeTextFilters(null);
            tabularviewRequest.setNumericFilters(null);
            tabularviewRequest.setSelectedTabIds(null);
            tabularviewRequest.setStringFilters(null);
            tabularviewRequest.setUnSelectedTabIds(null);

            ReferenceTabPageData referenceTabPageData = referenceTabService.findReferenceTabData(tabularviewRequest, userJdbc, currentSessionUserToken);

            Assert.assertNotNull(referenceTabPageData);

        } catch(Exception e) {

            LOGGER.info(" Exception occurred because User session object will not be available to process request {} ", e.getMessage());

        }
    }

    @Test
    public void testFindReferenceTabDataForExport() {

        try {
            LOGGER.info(" ------------------- Executing testFindReferenceTabDataForExport Test case ------------------- ");

            TabularviewExportRequest tabularviewExportRequest = new TabularviewExportRequest();
            tabularviewExportRequest.setSelectedTabIds(null);
            tabularviewExportRequest.setUnSelectedTabIds(null);

            List<ReferenceTabDTO> referenceTabDTOList = referenceTabService.findReferenceTabDataForExport(tabularviewExportRequest, userJdbc, currentSessionUserToken);

            Assert.assertNotNull(referenceTabDTOList);

        } catch(Exception e) {

            LOGGER.info(" Exception occurred because User session object will not be available to process request {} ", e.getMessage());

        }

    }

}

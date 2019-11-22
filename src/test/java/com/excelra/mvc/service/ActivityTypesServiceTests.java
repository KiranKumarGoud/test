package com.excelra.mvc.service;

import com.excelra.mvc.model.ActivityLabelValueDTO;
import com.excelra.mvc.model.ListActivityTypesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
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
 * ActivityTypes Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTypesServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTypesServiceTests.class);

    @Autowired
    private ActivityTypesService activityTypesService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserJdbc userJdbc;

    @Before
    public void contextLoads() {

        this.userJdbc = new UserJdbc();
        this.userJdbc.setJdbcTemplate(jdbcTemplate);
        this.userJdbc.setUserSessionId("1JldmFsdWF0ZXVzZXIiLCJhdXRoIj2");

    }

    @Test
    public void testFetchActivityTypeContaining() {

        LOGGER.info(" ------------------- Executing testFetchActivityTypeContaining Test case ------------------- ");

        String activityType = "cell";

        List<ListActivityTypesDTO> listActivityTypesDTOList = activityTypesService.fetchActivityTypeContaining(activityType, userJdbc);

        LOGGER.info(" ListActivityTypesDTO Records Count {} ", listActivityTypesDTOList.size());

        Assert.assertNotNull(listActivityTypesDTOList);
    }

    @Test
    public void testFetchActivityTypeLabelValue() {

        LOGGER.info(" ------------------- Executing testFetchActivityTypeLabelValue Test case ------------------- ");

        String activityType = "Q-T intervals";

        List<ActivityLabelValueDTO> activityLabelValueDTOList = activityTypesService.fetchActivityTypeLabelValue(activityType, userJdbc);

        LOGGER.info(" ActivityLabelValueDTO Records Count {} ", activityLabelValueDTOList.size());

        Assert.assertNotNull(activityLabelValueDTOList);

    }

    @Test
    public void testFetchActivityUomLabelValue() {

        LOGGER.info(" ------------------- Executing testFetchActivityUomLabelValue Test case ------------------- ");

        String activityType = "Q-T intervals";

        List<ActivityLabelValueDTO> activityLabelValueDTOList = activityTypesService.fetchActivityUomLabelValue(activityType, userJdbc);

        LOGGER.info(" ActivityLabelValueDTO Records Count {} ", activityLabelValueDTOList.size());

        Assert.assertNotNull(activityLabelValueDTOList);

    }

    @Test
    public void testFetchActivityPrefixLabelValue() {

        LOGGER.info(" ------------------- Executing testFetchActivityPrefixLabelValue Test case ------------------- ");

        String activityType = "Q-T intervals";

        List<ActivityLabelValueDTO> activityLabelValueDTOList = activityTypesService.fetchActivityPrefixLabelValue(activityType, userJdbc);

        LOGGER.info(" ActivityLabelValueDTO Records Count {} ", activityLabelValueDTOList.size());

        Assert.assertNotNull(activityLabelValueDTOList);
    }

    @Test
    public void testFetchActivityPrefixValues() {

        LOGGER.info(" ------------------- Executing testFetchActivityPrefixValues Test case ------------------- ");

        List<ActivityLabelValueDTO> activityLabelValueDTOList = activityTypesService.fetchActivityPrefixValues();

        LOGGER.info(" ActivityLabelValueDTO Records Count {} ", activityLabelValueDTOList.size());

        Assert.assertNotNull(activityLabelValueDTOList);

    }
}

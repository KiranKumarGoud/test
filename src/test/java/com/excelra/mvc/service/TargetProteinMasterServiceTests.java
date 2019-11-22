package com.excelra.mvc.service;

import com.excelra.mvc.model.TargetProteinMasterDTO;
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
 * TargetProtein Master Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetProteinMasterServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetProteinMasterServiceTests.class);

    @Autowired
    private TargetProteinMasterService targetProteinMasterService;

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
    public void testFindByCommonName() {

        LOGGER.info(" ------------------- Executing testFindByCommonName Test case ------------------- ");

        List<TargetProteinMasterDTO> targetProteinMasterDTOList = targetProteinMasterService.findByCommonName("beta", userJdbc);

        LOGGER.info(" TargetProteinMasterDTO Records Count {} ", targetProteinMasterDTOList.size());

        Assert.assertNotNull(targetProteinMasterDTOList);
    }
}

package com.excelra.mvc.service;

import com.excelra.mvc.model.ListUniprotIdDTO;
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
 * ListUniprotId Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListUniprotIdServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListUniprotIdServiceTests.class);

    @Autowired
    private ListUniprotIdService listUniprotIdService;

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
    public void testFindByUniprotId() {

        LOGGER.info(" ------------------- Executing testFindByUniprotId Test case ------------------- ");

        List<ListUniprotIdDTO> listUniprotIdDTOS = listUniprotIdService.findByUniprotId("p9", userJdbc);

        LOGGER.info(" ListUniprotIdDTO Records Count {} ", listUniprotIdDTOS.size());

        Assert.assertNotNull(listUniprotIdDTOS);

    }

}

package com.excelra.mvc.service;

import com.excelra.mvc.model.ListOfficialNameDTO;
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
 * ListOfficialName Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListOfficialNameServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListOfficialNameServiceTests.class);

    @Autowired
    private ListOfficialNameService listOfficialNameService;

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
    public void testFindByOfficialName() {

        LOGGER.info(" ------------------- Executing testFindByOfficialName Test case ------------------- ");

        List<ListOfficialNameDTO> listOfficialNameDTOS = listOfficialNameService.findByOfficialName("a", userJdbc);

        LOGGER.info(" ListOfficialNameDTO Records Count {} ", listOfficialNameDTOS.size());

        Assert.assertNotNull(listOfficialNameDTOS);
    }

}

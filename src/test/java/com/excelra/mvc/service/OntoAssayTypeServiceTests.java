package com.excelra.mvc.service;

import com.excelra.mvc.model.OntoAssayTypeDTO;
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
 * OntoAssayType Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OntoAssayTypeServiceTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(OntoAssayTypeServiceTests.class);

    @Autowired
    private OntoAssayTypeService ontoAssayTypeService;

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
    public void testFindByEnddataPointLeaf() {

        LOGGER.info(" ------------------- Executing testFindByEnddataPointLeaf Test case ------------------- ");

        List<OntoAssayTypeDTO> ontoAssayTypeDTOS = ontoAssayTypeService.findByEnddataPointLeaf(Boolean.TRUE, userJdbc);

        LOGGER.info(" OntoAssayTypeDTO Records Count {} ", ontoAssayTypeDTOS.size());

        Assert.assertNotNull(ontoAssayTypeDTOS);
    }

    @Test
    public void testFetchAll() {

        LOGGER.info(" ------------------- Executing testFetchAll Test case ------------------- ");

        List<OntoAssayTypeDTO> ontoAssayTypeDTOS = ontoAssayTypeService.fetchAll(userJdbc);

        LOGGER.info(" OntoAssayTypeDTO Records Count {} ", ontoAssayTypeDTOS.size());

        Assert.assertNotNull(ontoAssayTypeDTOS);
    }

}

package com.excelra.mvc.service;

import com.excelra.mvc.model.indication.ListIcd10CodesDTO;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
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
 * Indication Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndicationServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationServiceTests.class);

    @Autowired
    private IndicationService indicationService;

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
    public void testFetchTherapeuicListByContaining() {

        LOGGER.info(" ------------------- Executing testFetchTherapeuicListByContaining Test case ------------------- ");

        List<ListTherapeuticUseDTO> listTherapeuticUseDTOList = indicationService.fetchTherapeuicListByContaining("abd", userJdbc);

        LOGGER.info(" ListTherapeuticUseDTO Records Count {} ", listTherapeuticUseDTOList.size());

        Assert.assertNotNull(listTherapeuticUseDTOList);
    }

    @Test
    public void testFetchIcd10CodesListByContaining() {

        LOGGER.info(" ------------------- Executing testFetchIcd10CodesListByContaining Test case ------------------- ");

        List<ListIcd10CodesDTO> listIcd10CodesDTOList = indicationService.fetchIcd10CodesListByContaining("a0", userJdbc);

        LOGGER.info(" ListIcd10CodesDTO Records Count {} ", listIcd10CodesDTOList.size());

        Assert.assertNotNull(listIcd10CodesDTOList);
    }
}

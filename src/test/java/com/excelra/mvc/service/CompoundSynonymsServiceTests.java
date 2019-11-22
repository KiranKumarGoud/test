package com.excelra.mvc.service;

import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNo;
import com.excelra.mvc.model.StructuralPropertiesDTO;
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
 * CompoundSynonyms Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompoundSynonymsServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompoundSynonymsServiceTests.class);

    @Autowired
    private CompoundSynonymsService compoundSynonymsService;

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
    public void testFindByCompoundSynonymsContaining() {

        LOGGER.info(" ------------------- Executing testFindByCompoundSynonymsContaining Test case ------------------- ");

        List<CompoundSynonymsDTO> compoundSynonymsDTOList = compoundSynonymsService.findByCompoundSynonymsContaining("04", userJdbc);

        LOGGER.info(" CompoundSynonymsDTO Records Count {} ", compoundSynonymsDTOList.size());

        Assert.assertNotNull(compoundSynonymsDTOList);
    }

    @Test
    public void testFetchByCasNoContains() {

        LOGGER.info(" ------------------- Executing testFetchByCasNoContains Test case ------------------- ");

        List<ListCasNo> listCasNos = compoundSynonymsService.fetchByCasNoContains("1", userJdbc);

        LOGGER.info(" ListCasNo Records Count {} ", listCasNos.size());

        Assert.assertNotNull(listCasNos);
    }

    @Test
    public void testStructuralPropertiesMinMax() {

        LOGGER.info(" ------------------- Executing testStructuralPropertiesMinMax Test case ------------------- ");

        StructuralPropertiesDTO structuralPropertiesDTO = compoundSynonymsService.structuralPropertiesMinMax(userJdbc);

        LOGGER.info(" StructuralPropertiesDTO Records {} ", structuralPropertiesDTO.toString());

        Assert.assertNotNull(structuralPropertiesDTO);
    }

}

package com.excelra.mvc.service;

import com.excelra.mvc.model.TargetAdvSearchInputDTO;
import com.excelra.mvc.model.TargetSynonymsDTO;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Target Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetServiceTests.class);

    @Autowired
    private TargetService targetService;

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
    public void testFetchAllSynonyms() {

        LOGGER.info(" ------------------- Executing testFetchAllSynonyms Test case ------------------- ");

        List<TargetSynonymsDTO> targetSynonymsDTOList = targetService.fetchAllSynonyms(userJdbc);

        LOGGER.info(" TargetSynonymsDTO Records Count {} ", targetSynonymsDTOList.size());

        Assert.assertNotNull(targetSynonymsDTOList);

    }

    @Test
    public void testFetchBySynonymContains() {

        LOGGER.info(" ------------------- Executing testFetchBySynonymContains Test case ------------------- ");

        List<TargetSynonymsDTO> targetSynonymsDTOList = targetService.fetchBySynonymContains("test", userJdbc);

        LOGGER.info(" TargetSynonymsDTO Records Count {} ", targetSynonymsDTOList.size());

        Assert.assertNotNull(targetSynonymsDTOList);
    }

    @Test
    public void testFetchBySynonyms() {

        LOGGER.info(" ------------------- Executing testFetchBySynonyms Test case ------------------- ");

        List<TargetSynonymsDTO> targetSynonymsDTOList = targetService.fetchBySynonyms("test", userJdbc);

        LOGGER.info(" TargetSynonymsDTO Records Count {} ", targetSynonymsDTOList.size());

        Assert.assertNotNull(targetSynonymsDTOList);
    }

    @Test
    public void testFindStdnameIdBySynonymsList() {

        LOGGER.info(" ------------------- Executing testFindStdnameIdBySynonymsList Test case ------------------- ");

        TargetSynonymsDTO targetSynonymsDTO = new TargetSynonymsDTO();
        targetSynonymsDTO.setCommonName("ACETYLCHOLINE ALPHA 7 RECEPTOR");
        targetSynonymsDTO.setLabel("ACETYLCHOLINE ALPHA 7 RECEPTOR");
        targetSynonymsDTO.setOperator("|");
        targetSynonymsDTO.setStdnameId(1118);
        targetSynonymsDTO.setSynonyms("ACETYLCHOLINE ALPHA 7 RECEPTOR");
        targetSynonymsDTO.setValue("ACETYLCHOLINE ALPHA 7 RECEPTOR");

        List<TargetSynonymsDTO> targetSynonymsDTOList = new ArrayList<>();
        targetSynonymsDTOList.add(targetSynonymsDTO);

        List<Integer> listInteger = targetService.findStdnameIdBySynonymsList(targetSynonymsDTOList, userJdbc);

        LOGGER.info(" Integer Records Count {} ", listInteger.size());

        Assert.assertNotNull(listInteger);
    }

    @Test
    public void testFindAll() {

        LOGGER.info(" ------------------- Executing testFindAll Test case ------------------- ");

        List<TargetSynonymsDTO> targetSynonymsDTOList = targetService.findAll(userJdbc);

        LOGGER.info(" TargetSynonymsDTO Records Count {} ", targetSynonymsDTOList.size());

        Assert.assertNotNull(targetSynonymsDTOList);
    }

    @Test
    public void testFindBySynonyms() {

        LOGGER.info(" ------------------- Executing testFindBySynonyms Test case ------------------- ");

        List<TargetSynonymsDTO> targetSynonymsDTOList = targetService.findBySynonyms("test", userJdbc);

        LOGGER.info(" TargetSynonymsDTO Records Count {} ", targetSynonymsDTOList.size());

        Assert.assertNotNull(targetSynonymsDTOList);
    }

    @Test
    public void testFindBySynonymsContaining() {

        LOGGER.info(" ------------------- Executing testFindBySynonymsContaining Test case ------------------- ");

        List<TargetSynonymsDTO> targetSynonymsDTOList = targetService.findBySynonymsContaining("test", userJdbc);

        LOGGER.info(" TargetSynonymsDTO Records Count {} ", targetSynonymsDTOList.size());

        Assert.assertNotNull(targetSynonymsDTOList);

    }

    @Test
    public void testFetchTargetByOptionAndValue() {

        LOGGER.info(" ------------------- Executing testFetchTargetByOptionAndValue Test case ------------------- ");

        List<TargetAdvSearchInputDTO> targetAdvSearchInputDTOS = targetService.fetchTargetByOptionAndValue("enterez", "1", userJdbc);

        LOGGER.info(" TargetAdvSearchInputDTO Records Count {} ", targetAdvSearchInputDTOS.size());

        Assert.assertNotNull(targetAdvSearchInputDTOS);

    }
}

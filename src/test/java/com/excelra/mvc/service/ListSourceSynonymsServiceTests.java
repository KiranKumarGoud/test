package com.excelra.mvc.service;

import com.excelra.mvc.model.source.ListSourceSynonymsDTO;
import com.excelra.mvc.model.source.ListSourceTaxIdsDTO;
import com.excelra.mvc.model.source.SourceClassificationMasterDTO;
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
 * ListSourceSynonyms Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListSourceSynonymsServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListSourceSynonymsServiceTests.class);

    @Autowired
    private ListSourceSynonymsService listSourceSynonymsService;

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
    public void testFindBySourcenameContaining() {

        LOGGER.info(" ------------------- Executing testFindBySourcenameContaining Test case ------------------- ");

        List<ListSourceSynonymsDTO> listSourceSynonymsDTOS = listSourceSynonymsService.findBySourcenameContaining("my", userJdbc);

        LOGGER.info(" ListSourceSynonymsDTO Records Count {} ", listSourceSynonymsDTOS.size());

        Assert.assertNotNull(listSourceSynonymsDTOS);
    }

    @Test
    public void testFindBySynonymContaining() {

        LOGGER.info(" ------------------- Executing testFindBySynonymContaining Test case ------------------- ");

        List<ListSourceSynonymsDTO> listSourceSynonymsDTOS = listSourceSynonymsService.findBySynonymContaining("my", userJdbc);

        LOGGER.info(" ListSourceSynonymsDTO Records Count {} ", listSourceSynonymsDTOS.size());

        Assert.assertNotNull(listSourceSynonymsDTOS);
    }

    @Test
    public void testFindByTaxIdContaining() {

        LOGGER.info(" ------------------- Executing testFindByTaxIdContaining Test case ------------------- ");

        List<ListSourceTaxIdsDTO> listSourceTaxIdsDTOS = listSourceSynonymsService.findByTaxIdContaining("95", userJdbc);

        LOGGER.info(" ListSourceTaxIdsDTO Records Count {} ", listSourceTaxIdsDTOS.size());

        Assert.assertNotNull(listSourceTaxIdsDTOS);
    }

    @Test
    public void testFindByGenusContaining() {

        LOGGER.info(" ------------------- Executing testFindByGenusContaining Test case ------------------- ");

        List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = listSourceSynonymsService.findByGenusContaining("s", userJdbc);

        LOGGER.info(" SourceClassificationMasterDTO Records Count {} ", sourceClassificationMasterDTOList.size());

        Assert.assertNotNull(sourceClassificationMasterDTOList);
    }

    @Test
    public void testFindBySpeciesContaining() {

        LOGGER.info(" ------------------- Executing testFindBySpeciesContaining Test case ------------------- ");

        List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = listSourceSynonymsService.findBySpeciesContaining("Streptococcus", "pyogenes", userJdbc);

        LOGGER.info(" SourceClassificationMasterDTO Records Count {} ", sourceClassificationMasterDTOList.size());

        Assert.assertNotNull(sourceClassificationMasterDTOList);

    }

    @Test
    public void testFindByStrainContaining() {

        LOGGER.info(" ------------------- Executing testFindByStrainContaining Test case ------------------- ");

        List<SourceClassificationMasterDTO> sourceClassificationMasterDTOList = listSourceSynonymsService.findByStrainContaining("Streptococcus", "pyogenes", "07Z016", userJdbc);

        LOGGER.info(" SourceClassificationMasterDTO Records Count {} ", sourceClassificationMasterDTOList.size());

        Assert.assertNotNull(sourceClassificationMasterDTOList);
    }

}

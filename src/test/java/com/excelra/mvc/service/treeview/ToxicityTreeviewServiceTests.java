package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.ToxicityTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.ToxicityOntologyMasterDTO;
import com.excelra.mvc.model.treeview.ToxicityTreeviewDTO;
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
 * ToxicityTreeview Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ToxicityTreeviewServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxonomyTreeviewServiceTests.class);

    @Autowired
    private ToxicityTreeviewService toxicityTreeviewService;

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
    public void testPrepareTreeviewForNode() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForNode Test case ------------------- ");

        Long toxicityOntologyId = 1L;

        List<ToxicityTreeviewDTO> toxicityTreeviewDTOList = toxicityTreeviewService.prepareTreeviewForNode(toxicityOntologyId, userJdbc);

        LOGGER.info(" ToxicityTreeviewDTO Response Size {} ", toxicityTreeviewDTOList.size());

        Assert.assertNotNull(toxicityTreeviewDTOList);
    }

    @Test
    public void testPrepareTreeviewForToxicityName() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForToxicityName Test case ------------------- ");

        String toxicityName = "IN VITRO";

        List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOList = toxicityTreeviewService.prepareTreeviewForToxicityName(toxicityName, userJdbc);

        LOGGER.info(" ToxicityTreeviewParentChildNodeDTO Response Size {} ", toxicityTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(toxicityTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testPrepareTreeviewForParentLevel() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForParentLevel Test case ------------------- ");

        List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOList = toxicityTreeviewService.prepareTreeviewForParentLevel(userJdbc);

        LOGGER.info(" ToxicityTreeviewParentChildNodeDTO Response Size {} ", toxicityTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(toxicityTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testFetchToxicityOntologyToxicityNameByContaining() {

        LOGGER.info(" ------------------- Executing testFetchToxicityOntologyToxicityNameByContaining Test case ------------------- ");

        String toxicityName = "IN";

        List<ToxicityOntologyMasterDTO> toxicityOntologyMasterDTOList = toxicityTreeviewService.fetchToxicityOntologyToxicityNameByContaining(toxicityName, userJdbc);

        LOGGER.info(" ToxicityOntologyMasterDTO Response Size {} ", toxicityOntologyMasterDTOList.size());

        Assert.assertNotNull(toxicityOntologyMasterDTOList);

    }

    @Test
    public void testPrepareTreeviewForSelectedToxicityNames() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForSelectedToxicityNames Test case ------------------- ");

        List<String> toxicityNameList = new ArrayList<>();
        toxicityNameList.add("MICRONUCLEUS TEST");
        toxicityNameList.add("CONGO RED");

        List<ToxicityTreeviewParentChildNodeDTO> toxicityTreeviewParentChildNodeDTOList = toxicityTreeviewService.prepareTreeviewForSelectedToxicityNames(toxicityNameList, userJdbc);

        LOGGER.info(" ToxicityTreeviewParentChildNodeDTO Response Size {} ", toxicityTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(toxicityTreeviewParentChildNodeDTOList);
    }

}

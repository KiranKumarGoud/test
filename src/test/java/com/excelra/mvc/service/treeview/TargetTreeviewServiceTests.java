package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.TargetTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TargetOntologyMasterDTO;
import com.excelra.mvc.model.treeview.TargetTreeviewDTO;
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
 * TargetTreeview Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetTreeviewServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetTreeviewServiceTests.class);

    @Autowired
    private TargetTreeviewService targetTreeviewService;

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

        Long ontologyId = 1L;

        List<TargetTreeviewDTO> targetTreeviewDTOList = targetTreeviewService.prepareTreeviewForNode(ontologyId, userJdbc);

        LOGGER.info(" TargetTreeviewDTO Response Size {} ", targetTreeviewDTOList.size());

        Assert.assertNotNull(targetTreeviewDTOList);
    }

    @Test
    public void testPrepareTreeviewForTargetName() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForTargetName Test case ------------------- ");
        String targetName = "Others";

        List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOList = targetTreeviewService.prepareTreeviewForTargetName(targetName, userJdbc);

        LOGGER.info(" TargetTreeviewParentChildNodeDTO Response Size {} ", targetTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(targetTreeviewParentChildNodeDTOList);
    }

    @Test
    public void testPrepareTreeviewForParentLevel() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForParentLevel Test case ------------------- ");

        List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOList = targetTreeviewService.prepareTreeviewForParentLevel(userJdbc);

        LOGGER.info(" TargetTreeviewParentChildNodeDTO Response Size {} ", targetTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(targetTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testFetchTargetOntologyTargetNameByContaining() {

        LOGGER.info(" ------------------- Executing testFetchTargetOntologyTargetNameByContaining Test case ------------------- ");

        String targetName = "Others";

        List<TargetOntologyMasterDTO> targetOntologyMasterDTOList = targetTreeviewService.fetchTargetOntologyTargetNameByContaining(targetName, userJdbc);

        LOGGER.info(" TargetOntologyMasterDTO Response Size {} ", targetOntologyMasterDTOList.size());

        Assert.assertNotNull(targetOntologyMasterDTOList);

    }

    @Test
    public void testPrepareTreeviewForSelectedTargetNames() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForSelectedTargetNames Test case ------------------- ");

        List<String> targetNameList = new ArrayList<>();
        targetNameList.add("Others");

        List<TargetTreeviewParentChildNodeDTO> targetTreeviewParentChildNodeDTOList = targetTreeviewService.prepareTreeviewForSelectedTargetNames(targetNameList, userJdbc);

        LOGGER.info(" TargetTreeviewParentChildNodeDTO Response Size {} ", targetTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(targetTreeviewParentChildNodeDTOList);
    }

}

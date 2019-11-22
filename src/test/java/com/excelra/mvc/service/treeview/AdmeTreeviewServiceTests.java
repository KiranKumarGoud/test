package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.AdmeTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.AdmeOntologyMasterDTO;
import com.excelra.mvc.model.treeview.AdmeTreeviewDTO;
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
 * ActivityTypes Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdmeTreeviewServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmeTreeviewServiceTests.class);

    @Autowired
    private AdmeTreeviewService admeTreeviewService;

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

        Long admeOntologyId = 1L;

        List<AdmeTreeviewDTO> admeTreeviewDTOList = admeTreeviewService.prepareTreeviewForNode(admeOntologyId, userJdbc);

        LOGGER.info(" AdmeTreeviewDTO Response Size {} ", admeTreeviewDTOList.size());

        Assert.assertNotNull(admeTreeviewDTOList);
    }

    @Test
    public void testPrepareTreeviewForAdmeName() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForAdmeName Test case ------------------- ");

        String admeName = "ABSORPTION";

        List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOList = admeTreeviewService.prepareTreeviewForAdmeName(admeName, userJdbc);

        LOGGER.info(" AdmeTreeviewParentChildNodeDTO Response Size {} ", admeTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(admeTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testPrepareTreeviewForParentLevel() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForParentLevel Test case ------------------- ");

        List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOList = admeTreeviewService.prepareTreeviewForParentLevel(userJdbc);

        LOGGER.info(" AdmeTreeviewParentChildNodeDTO Response Size {} ", admeTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(admeTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testFetchAdmeOntologyAdmeNameByContaining() {

        LOGGER.info(" ------------------- Executing testFetchAdmeOntologyAdmeNameByContaining Test case ------------------- ");

        String admeName = "A";

        List<AdmeOntologyMasterDTO> admeOntologyMasterDTOList = admeTreeviewService.fetchAdmeOntologyAdmeNameByContaining(admeName, userJdbc);

        LOGGER.info(" AdmeOntologyMasterDTO Response Size {} ", admeOntologyMasterDTOList.size());

        Assert.assertNotNull(admeOntologyMasterDTOList);
    }

    @Test
    public void testPrepareTreeviewForSelectedAdmeNames() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForSelectedAdmeNames Test case ------------------- ");

        List<String> admeNameList = new ArrayList<>();
        admeNameList.add("ABSORPTION");

        List<AdmeTreeviewParentChildNodeDTO> admeTreeviewParentChildNodeDTOList = admeTreeviewService.prepareTreeviewForSelectedAdmeNames(admeNameList, userJdbc);

        LOGGER.info(" AdmeTreeviewParentChildNodeDTO Response Size {} ", admeTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(admeTreeviewParentChildNodeDTOList);
    }

}

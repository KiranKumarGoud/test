package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.EndpointTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.EndpointMasterDTO;
import com.excelra.mvc.model.treeview.EndpointTreeviewDTO;
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
 * EndpointTreeview Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EndpointTreeviewServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTreeviewServiceTests.class);

    @Autowired
    private EndpointTreeviewService endpointTreeviewService;

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

        Long endpointId = 1L;

        List<EndpointTreeviewDTO> endpointTreeviewDTOList = endpointTreeviewService.prepareTreeviewForNode(endpointId, userJdbc);

        LOGGER.info(" EndpointTreeviewDTO Response Size {} ", endpointTreeviewDTOList.size());

        Assert.assertNotNull(endpointTreeviewDTOList);
    }

    @Test
    public void testPrepareTreeviewForEndpointName() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForEndpointName Test case ------------------- ");

        String endpointName = "auc";

        List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOList = endpointTreeviewService.prepareTreeviewForEndpointName(endpointName, userJdbc);

        LOGGER.info(" EndpointTreeviewParentChildNodeDTO Response Size {} ", endpointTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(endpointTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testPrepareTreeviewForParentLevel() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForParentLevel Test case ------------------- ");

        List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOList = endpointTreeviewService.prepareTreeviewForParentLevel(userJdbc);

        LOGGER.info(" EndpointTreeviewParentChildNodeDTO Response Size {} ", endpointTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(endpointTreeviewParentChildNodeDTOList);
    }

    @Test
    public void testFetchEndpointEndpointNameByContaining() {

        LOGGER.info(" ------------------- Executing testFetchEndpointEndpointNameByContaining Test case ------------------- ");

        String endpointName = "a";

        List<EndpointMasterDTO> endpointMasterDTOList = endpointTreeviewService.fetchEndpointEndpointNameByContaining(endpointName, userJdbc);

        LOGGER.info(" EndpointMasterDTO Response Size {} ", endpointMasterDTOList.size());

        Assert.assertNotNull(endpointMasterDTOList);
    }

    @Test
    public void testPrepareTreeviewForSelectedEndpointNames() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForSelectedEndpointNames Test case ------------------- ");

        List<String> endpointNameList = new ArrayList<>();
        endpointNameList.add("Concentration endpoint");

        List<EndpointTreeviewParentChildNodeDTO> endpointTreeviewParentChildNodeDTOList = endpointTreeviewService.prepareTreeviewForSelectedEndpointNames(endpointNameList, userJdbc);

        LOGGER.info(" EndpointTreeviewParentChildNodeDTO Response Size {} ", endpointTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(endpointTreeviewParentChildNodeDTOList);
    }
}

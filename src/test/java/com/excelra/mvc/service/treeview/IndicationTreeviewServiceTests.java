package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.IndicationTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.IndicationOntologyMasterDTO;
import com.excelra.mvc.model.treeview.IndicationTreeviewDTO;
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
 * IndicationTreeview Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndicationTreeviewServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicationTreeviewServiceTests.class);

    @Autowired
    private IndicationTreeviewService indicationTreeviewService;

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

        String icd10Code = "A03";

        List<IndicationTreeviewDTO> indicationTreeviewDTOList = indicationTreeviewService.prepareTreeviewForNode(icd10Code, userJdbc);

        LOGGER.info(" IndicationTreeviewDTO Response Size {} ", indicationTreeviewDTOList.size());

        Assert.assertNotNull(indicationTreeviewDTOList);

    }

    @Test
    public void testPrepareTreeviewForTherapeuticUse() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForTherapeuticUse Test case ------------------- ");

        String therapeuticUse = "Amoebiasis";

        List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOList = indicationTreeviewService.prepareTreeviewForTherapeuticUse(therapeuticUse, userJdbc);

        LOGGER.info(" IndicationTreeviewParentChildNodeDTO Response Size {} ", indicationTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(indicationTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testPrepareTreeviewForParentLevel() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForParentLevel Test case ------------------- ");

        List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOList = indicationTreeviewService.prepareTreeviewForParentLevel(userJdbc);

        LOGGER.info(" IndicationTreeviewParentChildNodeDTO Response Size {} ", indicationTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(indicationTreeviewParentChildNodeDTOList);
    }

    @Test
    public void testFetchIndicationOntologyTherapeuticUseByContaining() {

        LOGGER.info(" ------------------- Executing testFetchIndicationOntologyTherapeuticUseByContaining Test case ------------------- ");

        String therapeuticUse = "Respiratory tuberculosis";

        List<IndicationOntologyMasterDTO> indicationOntologyMasterDTOList = indicationTreeviewService.fetchIndicationOntologyTherapeuticUseByContaining(therapeuticUse, userJdbc);

        LOGGER.info(" IndicationOntologyMasterDTO Response Size {} ", indicationOntologyMasterDTOList.size());

        Assert.assertNotNull(indicationOntologyMasterDTOList);
    }

    @Test
    public void testPrepareTreeviewForSelectedTherapeuticUses() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForSelectedTherapeuticUses Test case ------------------- ");

        List<String> therapeuticUseList = new ArrayList<>();
        therapeuticUseList.add("Tuberculosis of larynx");

        List<IndicationTreeviewParentChildNodeDTO> indicationTreeviewParentChildNodeDTOList = indicationTreeviewService.prepareTreeviewForSelectedTherapeuticUses(therapeuticUseList, userJdbc);

        LOGGER.info(" IndicationTreeviewParentChildNodeDTO Response Size {} ", indicationTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(indicationTreeviewParentChildNodeDTOList);

    }

}

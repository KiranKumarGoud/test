package com.excelra.mvc.service.treeview;

import com.excelra.mvc.model.search.treeview.TaxonomyTreeviewParentChildNodeDTO;
import com.excelra.mvc.model.treeview.TaxonomyMasterDTO;
import com.excelra.mvc.model.treeview.TaxonomyTreeviewDTO;
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
 * TaxonomyTreeview Service method JUnit test cases preparation for checking service methods healthy status
 *
 * @author venkateswarlu.s
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxonomyTreeviewServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxonomyTreeviewServiceTests.class);

    @Autowired
    private TaxonomyTreeviewService taxonomyTreeviewService;

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

        Long taxId = 2L;

        List<TaxonomyTreeviewDTO> taxonomyTreeviewDTOList = taxonomyTreeviewService.prepareTreeviewForNode(taxId, userJdbc);

        LOGGER.info(" TaxonomyTreeviewDTO Response Size {} ", taxonomyTreeviewDTOList.size());

        Assert.assertNotNull(taxonomyTreeviewDTOList);
    }

    @Test
    public void testPrepareTreeviewForTaxName() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForTaxName Test case ------------------- ");

        String taxName = "Bacteria";

        List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOList = taxonomyTreeviewService.prepareTreeviewForTaxName(taxName, userJdbc);

        LOGGER.info(" TaxonomyTreeviewParentChildNodeDTO Response Size {} ", taxonomyTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(taxonomyTreeviewParentChildNodeDTOList);

    }


    @Test
    public void testPrepareTreeviewForParentLevel() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForParentLevel Test case ------------------- ");

        List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOList = taxonomyTreeviewService.prepareTreeviewForParentLevel(userJdbc);

        LOGGER.info(" TaxonomyTreeviewParentChildNodeDTO Response Size {} ", taxonomyTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(taxonomyTreeviewParentChildNodeDTOList);

    }

    @Test
    public void testFetchTaxonomyTaxNameByContaining() {

        LOGGER.info(" ------------------- Executing testFetchTaxonomyTaxNameByContaining Test case ------------------- ");

        String taxName = "mic";

        List<TaxonomyMasterDTO> taxonomyMasterDTOList = taxonomyTreeviewService.fetchTaxonomyTaxNameByContaining(taxName, userJdbc);

        LOGGER.info(" TaxonomyMasterDTO Response Size {} ", taxonomyMasterDTOList.size());

        Assert.assertNotNull(taxonomyMasterDTOList);

    }

    @Test
    public void testPrepareTreeviewForSelectedTaxNames() {

        LOGGER.info(" ------------------- Executing testPrepareTreeviewForSelectedTaxNames Test case ------------------- ");

        List<String> taxNameList = new ArrayList<>();
        taxNameList.add("Caudovirales");

        List<TaxonomyTreeviewParentChildNodeDTO> taxonomyTreeviewParentChildNodeDTOList = taxonomyTreeviewService.prepareTreeviewForSelectedTaxNames(taxNameList, userJdbc);

        LOGGER.info(" TaxonomyTreeviewParentChildNodeDTO Response Size {} ", taxonomyTreeviewParentChildNodeDTOList.size());

        Assert.assertNotNull(taxonomyTreeviewParentChildNodeDTOList);
    }

}

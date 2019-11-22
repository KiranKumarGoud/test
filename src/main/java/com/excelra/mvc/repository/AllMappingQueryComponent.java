package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.*;
import com.excelra.mvc.model.indication.ListTherapeuticUseDTO;
import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.repository.search.*;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * This is AllMapping Component which is used in AllMappingIdsDAO Layer, the common methods are defined in Component for code reuseability.
 *
 * @author venkateswarlu.s
 */
@Component
public class AllMappingQueryComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllMappingQueryComponent.class);

    private static final String TARGET = "Target";

    private static final String ASSAY = "Assay";

    private static final String STRUCTURE = "Structure";

    private static final String BIBLIOGRAPHY_REFERENCE = "Bibliography";

    private static final String ACTIVITY_MECHANISM = "ActivityMechanism";

    private static final String ACTIVITY_TYPES = "ActivityTypes";

    private static final String TREEVIEW_TARGET = "TargetTreeview";

    private static final String TREEVIEW_INDICATION = "IndicationTreeview";

    private static final String TREEVIEW_ADME = "AdmeTreeview";

    private static final String TREEVIEW_TOXICITY = "ToxicityTreeview";

    private static final String TREEVIEW_TAXONOMY = "TaxonomyTreeview";

    private static final String TREEVIEW_ENDPOINT = "EndpointTreeview";

    private static final String INDICATION = "Indication";

    private static final String COMPOUND_CATEGORIES = "CompoundCategories";

    private static final String CLINICAL_PHASES = "ClinicalPhases";

    private static final String SOURCE = "Source";

    @Autowired
    private StructureComponent structureComponent;

    @Autowired
    private TargetComponent targetComponent;

    @Autowired
    private BibliographyComponent bibliographyComponent;

    @Autowired
    private ActivityMechanismComponent activityMechanismComponent;

    @Autowired
    private ActivityTypesComponent activityTypesComponent;

    @Autowired
    private AssayComponent assayComponent;

    @Autowired
    private TargetTreeviewComponent targetTreeviewComponent;

    @Autowired
    private IndicationComponent indicationComponent;

    @Autowired
    private IndicationTreeviewComponent indicationTreeviewComponent;

    @Autowired
    private AdmeTreeviewComponent admeTreeviewComponent;

    @Autowired
    private ToxicityTreeviewComponent toxicityTreeviewComponent;

    @Autowired
    private TaxonomyTreeviewComponent taxonomyTreeviewComponent;

    @Autowired
    private EndpointTreeviewComponent endpointTreeviewComponent;

    @Autowired
    private CompoundCategoriesComponent compoundCategoriesComponent;

    @Autowired
    private ClinicalPhasesComponent clinicalPhasesComponent;

    @Autowired
    private SourceComponent sourceComponent;

    @Autowired
    private StringUtility stringUtility;

    /**
     * @param searchCountInputDTOList
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    public String prepareCombinationQuery(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) throws GoStarSqlException {

        LOGGER.info("prepareCombinationQuery {} ", searchCountInputDTOList);

        String combinedQuery = StringUtils.EMPTY;

        Map<String, Map<String, String>> queryData = preparedQueryForSearch(searchCountInputDTOList, userJdbc);

        String innerCombination = StringUtils.EMPTY;
        String outerCombination = StringUtils.EMPTY;

        Boolean targetOuterFound = Boolean.FALSE;
        Boolean assayOuterFound = Boolean.FALSE;
        Boolean sourceOuterFound = Boolean.FALSE;

        Boolean onlyStructure = Boolean.TRUE;
        String structureQuery = StringUtils.EMPTY;
        Boolean onlyBibliography = Boolean.TRUE;
        String refQuery = StringUtils.EMPTY;
        String refInnerQuery = StringUtils.EMPTY;

        String targetNotAssay = StringUtils.EMPTY;
        String assayNotTarget = StringUtils.EMPTY;

        String activityMechanismInnerQuery = StringUtils.EMPTY;
        Boolean onlyActivityMechanism = Boolean.TRUE;
        Boolean activityMechanishOuterFound = Boolean.FALSE;

        String activityTypesInnerQuery = StringUtils.EMPTY;
        Boolean onlyActivityTypes = Boolean.TRUE;
        Boolean activityTypeOuterFound = Boolean.FALSE;

        String indicationInnerQuery = StringUtils.EMPTY;
        Boolean onlyIndication = Boolean.TRUE;
        Boolean indicationOuterFound = Boolean.FALSE;

        String targetTreeInnerQuery = StringUtils.EMPTY;
        Boolean onlyTargetTree = Boolean.TRUE;
        Boolean targetTreeOuterFound = Boolean.FALSE;

        String indicationTreeInnerQuery = StringUtils.EMPTY;
        Boolean onlyIndicationTree = Boolean.TRUE;
        Boolean indicationTreeOuterFound = Boolean.FALSE;

        String admeTreeInnerQuery = StringUtils.EMPTY;
        Boolean onlyAdmeTree = Boolean.TRUE;
        Boolean admeTreeOuterFound = Boolean.FALSE;

        String toxicityTreeInnerQuery = StringUtils.EMPTY;
        Boolean onlyToxicityTree = Boolean.TRUE;
        Boolean toxicityTreeOuterFound = Boolean.FALSE;

        String taxonomyTreeInnerQuery = StringUtils.EMPTY;
        Boolean onlyTaxonomyTree = Boolean.TRUE;
        Boolean taxonomyTreeOuterFound = Boolean.FALSE;

        String endpointTreeInnerQuery = StringUtils.EMPTY;
        Boolean onlyEndpointTree = Boolean.TRUE;
        Boolean endpointTreeOuterFound = Boolean.FALSE;

        String compoundCategoriesInnerQuery = StringUtils.EMPTY;
        Boolean onlyCompoundCategories = Boolean.TRUE;

        String clinicalPhasesInnerQuery = StringUtils.EMPTY;
        Boolean onlyClinicalPhases = Boolean.TRUE;

        String sourceInnerQuery = StringUtils.EMPTY;
        Boolean onlySource = Boolean.TRUE;

        for (SearchCountInputDTO searchCountInputDTO : searchCountInputDTOList) {

            /* :: Target Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TARGET)) {

                onlyBibliography = Boolean.FALSE;
                targetNotAssay = StringUtils.EMPTY;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                /**
                 * TARGET Verification
                 */

                if (!queryData.get(TARGET).get("Inner").isEmpty())
                    innerCombination = innerCombination + " " + queryData.get(TARGET).get("Inner");

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    if (!queryData.get(TARGET).get("Inner").isEmpty())
                        innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    if (!queryData.get(TARGET).get("Inner").isEmpty())
                        innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    targetNotAssay = searchCountInputDTO.getSourceOption() + searchCountInputDTO.getOperator();
                    if (!queryData.get(TARGET).get("Inner").isEmpty())
                        innerCombination = innerCombination + " and not ";
                }

                if (!queryData.get(TARGET).get("Outer").isEmpty()) {
                    if (assayNotTarget.isEmpty()) {
                        if (assayOuterFound || sourceOuterFound || activityMechanishOuterFound || activityTypeOuterFound || targetTreeOuterFound || indicationOuterFound || indicationTreeOuterFound || admeTreeOuterFound || toxicityTreeOuterFound || taxonomyTreeOuterFound || endpointTreeOuterFound) {
                            if (queryData.get(TARGET).get("psw").isEmpty()) {
                                outerCombination = outerCombination + " or " + queryData.get(TARGET).get("Outer");
                            } else {
                                outerCombination = outerCombination + " or (" + queryData.get(TARGET).get("Outer") + " and " + queryData.get(TARGET).get("psw") + " ) ";
                            }
                        } else {
                            if (queryData.get(TARGET).get("psw").isEmpty()) {
                                outerCombination = outerCombination + " " + queryData.get(TARGET).get("Outer");
                            } else {
                                outerCombination = outerCombination + " ( " + queryData.get(TARGET).get("Outer") + " and " + queryData.get(TARGET).get("psw") + " ) ";
                            }
                            targetOuterFound = Boolean.TRUE;
                        }
                    }
                }
            }

            /* :: Assay Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ASSAY)) {

                onlyBibliography = Boolean.FALSE;
                assayNotTarget = StringUtils.EMPTY;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                /**
                 * ASSAY Verification
                 */

                if (!queryData.get(ASSAY).get("Inner").isEmpty())
                    innerCombination = innerCombination + " " + queryData.get(ASSAY).get("Inner");

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    if (!queryData.get(ASSAY).get("Inner").isEmpty())
                        innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    if (!queryData.get(ASSAY).get("Inner").isEmpty())
                        innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    assayNotTarget = searchCountInputDTO.getSourceOption() + searchCountInputDTO.getOperator();
                    if (!queryData.get(ASSAY).get("Inner").isEmpty())
                        innerCombination = innerCombination + " and not ";
                }

                if (!queryData.get(ASSAY).get("Outer").isEmpty()) {
                    if (targetNotAssay.isEmpty()) {
                        if (targetOuterFound || sourceOuterFound || activityMechanishOuterFound || activityTypeOuterFound || targetTreeOuterFound || indicationOuterFound || indicationTreeOuterFound || admeTreeOuterFound || toxicityTreeOuterFound || taxonomyTreeOuterFound || endpointTreeOuterFound) {
                            outerCombination = outerCombination + " or " + queryData.get(ASSAY).get("Outer");
                        } else {
                            outerCombination = outerCombination + " " + queryData.get(ASSAY).get("Outer");
                            assayOuterFound = Boolean.TRUE;
                        }
                    }
                }
            }

            /* :: Source Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(SOURCE)) {

                onlyBibliography = Boolean.FALSE;
                assayNotTarget = StringUtils.EMPTY;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;

                /**
                 * ASSAY Verification
                 */

                if (!queryData.get(SOURCE).get("Inner").isEmpty()) {
                    innerCombination = innerCombination + " " + queryData.get(SOURCE).get("Inner");
                    // sourceInnerQuery = queryData.get(SOURCE).get("Inner");
                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    if (!queryData.get(SOURCE).get("Inner").isEmpty())
                        innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    if (!queryData.get(SOURCE).get("Inner").isEmpty())
                        innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    // assayNotTarget = searchCountInputDTO.getSourceOption() + searchCountInputDTO.getOperator();
                    if (!queryData.get(SOURCE).get("Inner").isEmpty())
                        innerCombination = innerCombination + " and not ";
                }

                if (!queryData.get(SOURCE).get("Outer").isEmpty()) {
                    sourceInnerQuery = queryData.get(SOURCE).get("Outer");
                    // if (targetNotAssay.isEmpty()) {
                        if (targetOuterFound || assayOuterFound || activityMechanishOuterFound || activityTypeOuterFound || targetTreeOuterFound || indicationOuterFound || indicationTreeOuterFound || admeTreeOuterFound || toxicityTreeOuterFound || taxonomyTreeOuterFound || endpointTreeOuterFound) {
                            // Check is NotSource
                            if(!stringUtility.checkNotSourceStatus(searchCountInputDTOList)) {
                                outerCombination = outerCombination + " or " + queryData.get(SOURCE).get("Outer");
                            }
                        } else {
                            outerCombination = outerCombination + " " + queryData.get(SOURCE).get("Outer");
                            sourceOuterFound = Boolean.TRUE;
                        }
                    // }
                }
            }

            /* :: Structure Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(STRUCTURE)) {

                onlyBibliography = Boolean.FALSE;
                targetNotAssay = StringUtils.EMPTY;
                assayNotTarget = StringUtils.EMPTY;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                /**
                 * STRUCTURE Verification
                 */

                if (!queryData.get(STRUCTURE).get("Inner").isEmpty())
                    innerCombination = innerCombination + " " + queryData.get(STRUCTURE).get("Inner");

                combinedQuery = structureComponent.structureCombinedQueryPreparation(structureQuery, queryData);

                System.out.println("+++++++++++++++++ >>> " + combinedQuery);
                if (Objects.nonNull(queryData.get(STRUCTURE).get("SearchType")) && !queryData.get(STRUCTURE).get("SearchType").equalsIgnoreCase("Term"))
                    combinedQuery = combinedQuery.replaceAll("str_id\\s+in\\s+\\(", "");
                System.out.println("+++++++++++++++++ >>> " + combinedQuery);

                if (!combinedQuery.isEmpty() && queryData.get(STRUCTURE).get("Inner").isEmpty())
                    innerCombination = innerCombination + " " + combinedQuery;

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    targetNotAssay = searchCountInputDTO.getSourceOption() + searchCountInputDTO.getOperator();
                    assayNotTarget = searchCountInputDTO.getSourceOption() + searchCountInputDTO.getOperator();

                    innerCombination = innerCombination + " and not ";
                }
            }

            /* :: Bibliography Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(BIBLIOGRAPHY_REFERENCE)) {

                /**
                 * BIBLIOGRAPHY Verification
                 */
                String previousOperator = StringUtils.EMPTY;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                int checkPosition = 1;

                for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                    if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(BIBLIOGRAPHY_REFERENCE)) {

                        if (checkPosition == 1) {
                            refQuery = " and " + queryData.get(BIBLIOGRAPHY_REFERENCE).get("Outer");
                        } else {
                            if (previousOperator.equalsIgnoreCase("not"))
                                refQuery = " and not " + queryData.get(BIBLIOGRAPHY_REFERENCE).get("Outer");
                            else
                                refQuery = " and " + queryData.get(BIBLIOGRAPHY_REFERENCE).get("Outer");
                        }

                    } else {
                        previousOperator = innerSearchCountInputDTO.getOperator();
                        checkPosition++;
                    }
                }

                if (onlyBibliography) {
                    if (!queryData.get(BIBLIOGRAPHY_REFERENCE).get("Outer").isEmpty() )
                        // innerCombination = innerCombination + " " + queryData.get(BIBLIOGRAPHY_REFERENCE).get("Outer");
                        refInnerQuery = queryData.get(BIBLIOGRAPHY_REFERENCE).get("Outer");
                }
            }

            /* :: Activity Mechanism Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_MECHANISM)) {

                /**
                 * Activity Mechanism Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousAMOperator = StringUtils.EMPTY;

                onlyActivityMechanism = activityMechanismComponent.checkActivityMechanismCombinationStatus(searchCountInputDTOList);

                if (onlyActivityMechanism) {
                    if (queryData.get(ACTIVITY_MECHANISM).get("orstatus").equalsIgnoreCase("OR")) {
                        activityMechanismInnerQuery = queryData.get(ACTIVITY_MECHANISM).get("Outer");
                    } else /*if (queryData.get(ACTIVITY_MECHANISM).get("orstatus").equalsIgnoreCase("AND")
                            || queryData.get(ACTIVITY_MECHANISM).get("orstatus").equalsIgnoreCase("ANDOR"))*/ {
                        activityMechanismInnerQuery = "str_id in (select str_id from target_search.str_id_based where " + queryData.get(ACTIVITY_MECHANISM).get("Inner") + " ) and " + queryData.get(ACTIVITY_MECHANISM).get("Outer") + "";
                    }
                } else {

                    int checkAMPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_MECHANISM)) {

                            if (checkAMPosition == 1) {
                                innerCombination = queryData.get(ACTIVITY_MECHANISM).get("Inner");
                            } else {
                                if (previousAMOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(ACTIVITY_MECHANISM).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(ACTIVITY_MECHANISM).get("Inner");
                            }

                        } else {
                            previousAMOperator = innerSearchCountInputDTO.getOperator();
                            checkAMPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(ACTIVITY_MECHANISM).get("Outer")) && !queryData.get(ACTIVITY_MECHANISM).get("Outer").isEmpty()) {
                    activityMechanishOuterFound = Boolean.TRUE;
                }

                if (!previousAMOperator.equalsIgnoreCase("not")) {
                        if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !targetTreeOuterFound && !indicationOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound) {
                            outerCombination = outerCombination + " ( " + queryData.get(ACTIVITY_MECHANISM).get("Outer") + " ) ";
                        } else {
                            outerCombination = outerCombination + " or ( " + queryData.get(ACTIVITY_MECHANISM).get("Outer") + " ) ";
                        }
                }
            }

            /* :: Activity Types Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_TYPES)) {

                /**
                 * Activity Types Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousATOperator = StringUtils.EMPTY;

                onlyActivityTypes = activityMechanismComponent.checkActivityMechanismCombinationStatus(searchCountInputDTOList);

                if (onlyActivityTypes) {
                    if (queryData.get(ACTIVITY_TYPES).get("orstatus").equalsIgnoreCase("OR")) {
                        activityTypesInnerQuery = queryData.get(ACTIVITY_TYPES).get("Outer");
                    } else /*if (queryData.get(ACTIVITY_TYPES).get("orstatus").equalsIgnoreCase("AND")
                            || queryData.get(ACTIVITY_TYPES).get("orstatus").equalsIgnoreCase("ANDOR"))*/ {
                        activityTypesInnerQuery = "str_id in (select str_id from target_search.str_id_based where " + queryData.get(ACTIVITY_TYPES).get("Inner") + " ) and " + queryData.get(ACTIVITY_TYPES).get("Outer") + "";
                    }
                } else {

                    int checkATPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_TYPES)) {

                            if (checkATPosition == 1) {
                                innerCombination = queryData.get(ACTIVITY_TYPES).get("Inner");
                            } else {
                                if (previousATOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(ACTIVITY_TYPES).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(ACTIVITY_TYPES).get("Inner");
                            }

                        } else {
                            previousATOperator = innerSearchCountInputDTO.getOperator();
                            checkATPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(ACTIVITY_TYPES).get("Outer")) && !queryData.get(ACTIVITY_TYPES).get("Outer").isEmpty()) {
                    activityTypeOuterFound = Boolean.TRUE;
                }

                if (!previousATOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityMechanishOuterFound && !targetTreeOuterFound && !indicationOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(ACTIVITY_TYPES).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(ACTIVITY_TYPES).get("Outer") + " ) ";
                    }
                }

            }

            /* :: Indication Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(INDICATION)) {

                /**
                 * Indication Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousIndicationOperator = StringUtils.EMPTY;

                onlyIndication = activityMechanismComponent.checkActivityMechanismCombinationStatus(searchCountInputDTOList);

                if (onlyIndication) {
                    if (queryData.get(INDICATION).get("orstatus").equalsIgnoreCase("OR")) {
                        // indicationInnerQuery = queryData.get(INDICATION).get("Outer");
                        indicationInnerQuery = "gvk_id in (select gvk_id from target_search.therapeutic_area_agg where "+queryData.get(INDICATION).get("Outer")+" )";
                    } else {
                        indicationInnerQuery = "gvk_id in (select gvk_id from target_search.therapeutic_area_agg where " + queryData.get(INDICATION).get("Inner") + " ) and " + queryData.get(INDICATION).get("Outer") + "";
                    }
                } else {

                    int checkATPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(INDICATION)) {

                            if (checkATPosition == 1) {
                                innerCombination = queryData.get(INDICATION).get("Inner");
                            } else {
                                if (previousIndicationOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(INDICATION).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(INDICATION).get("Inner");
                            }

                        } else {
                            previousIndicationOperator = innerSearchCountInputDTO.getOperator();
                            checkATPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(INDICATION).get("Outer")) && !queryData.get(INDICATION).get("Outer").isEmpty()) {
                    indicationOuterFound = Boolean.TRUE;
                }

                if (!previousIndicationOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityMechanishOuterFound && !targetTreeOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound && !activityTypeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(INDICATION).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(INDICATION).get("Outer") + " ) ";
                    }
                }

            }

            /* :: Target Treeview Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TARGET)) {


                /**
                 * Target Treeview Query combinations
                 */

                onlyIndicationTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousTTVOperator = StringUtils.EMPTY;

                onlyTargetTree = targetTreeviewComponent.checkTargetTreeviewCombinationStatus(searchCountInputDTOList);

                if (onlyTargetTree) {
                    targetTreeInnerQuery = queryData.get(TREEVIEW_TARGET).get("orstatus");
                } else {

                    int checkTTVPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TARGET)) {

                            if (checkTTVPosition == 1) {
                                innerCombination = queryData.get(TREEVIEW_TARGET).get("Inner");
                            } else {
                                if (previousTTVOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(TREEVIEW_TARGET).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(TREEVIEW_TARGET).get("Inner");
                            }

                        } else {
                            previousTTVOperator = innerSearchCountInputDTO.getOperator();
                            checkTTVPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(TREEVIEW_TARGET).get("Outer")) && !queryData.get(TREEVIEW_TARGET).get("Outer").isEmpty()) {
                    targetTreeOuterFound = Boolean.TRUE;
                }

                if (!previousTTVOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !activityMechanishOuterFound && !indicationOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(TREEVIEW_TARGET).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(TREEVIEW_TARGET).get("Outer") + " ) ";
                    }
                }

            }

            /* :: Indication Treeview Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_INDICATION)) {


                /**
                 * Indication Treeview Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousITVOperator = StringUtils.EMPTY;

                onlyIndicationTree = indicationTreeviewComponent.checkIndicationTreeviewCombinationStatus(searchCountInputDTOList);

                if (onlyIndicationTree) {
                    indicationTreeInnerQuery = queryData.get(TREEVIEW_INDICATION).get("orstatus");
                } else {

                    int checkITVPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_INDICATION)) {

                            if (checkITVPosition == 1) {
                                innerCombination = queryData.get(TREEVIEW_INDICATION).get("Inner");
                            } else {
                                if (previousITVOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(TREEVIEW_INDICATION).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(TREEVIEW_INDICATION).get("Inner");
                            }

                        } else {
                            previousITVOperator = innerSearchCountInputDTO.getOperator();
                            checkITVPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(TREEVIEW_INDICATION).get("Outer")) && !queryData.get(TREEVIEW_INDICATION).get("Outer").isEmpty()) {
                    indicationTreeOuterFound = Boolean.TRUE;
                }

                if (!previousITVOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !activityMechanishOuterFound && !indicationOuterFound && !targetTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(TREEVIEW_INDICATION).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(TREEVIEW_INDICATION).get("Outer") + " ) ";
                    }
                }

            }

            /* :: Adme Treeview Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_ADME)) {


                /**
                 * Adme Treeview Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousATOperator = StringUtils.EMPTY;

                onlyAdmeTree = admeTreeviewComponent.checkAdmeTreeviewCombinationStatus(searchCountInputDTOList);

                if (onlyAdmeTree) {
                    admeTreeInnerQuery = queryData.get(TREEVIEW_ADME).get("orstatus");
                } else {

                    int checkATPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_ADME)) {

                            if (checkATPosition == 1) {
                                innerCombination = queryData.get(TREEVIEW_ADME).get("Inner");
                            } else {
                                if (previousATOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(TREEVIEW_ADME).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(TREEVIEW_ADME).get("Inner");
                            }

                        } else {
                            previousATOperator = innerSearchCountInputDTO.getOperator();
                            checkATPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(TREEVIEW_ADME).get("Outer")) && !queryData.get(TREEVIEW_ADME).get("Outer").isEmpty()) {
                    admeTreeOuterFound = Boolean.TRUE;
                }

                if (!previousATOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !activityMechanishOuterFound && !indicationOuterFound && !targetTreeOuterFound && !indicationTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(TREEVIEW_ADME).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(TREEVIEW_ADME).get("Outer") + " ) ";
                    }
                }

            }

            /* :: Toxicity Treeview Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TOXICITY)) {


                /**
                 * Toxicity Treeview Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousTTOperator = StringUtils.EMPTY;

                onlyToxicityTree = toxicityTreeviewComponent.checkToxicityTreeviewCombinationStatus(searchCountInputDTOList);

                if (onlyToxicityTree) {
                    toxicityTreeInnerQuery = queryData.get(TREEVIEW_TOXICITY).get("orstatus");
                } else {

                    int checkTTPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TOXICITY)) {

                            if (checkTTPosition == 1) {
                                innerCombination = queryData.get(TREEVIEW_TOXICITY).get("Inner");
                            } else {
                                if (previousTTOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(TREEVIEW_TOXICITY).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(TREEVIEW_TOXICITY).get("Inner");
                            }

                        } else {
                            previousTTOperator = innerSearchCountInputDTO.getOperator();
                            checkTTPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(TREEVIEW_TOXICITY).get("Outer")) && !queryData.get(TREEVIEW_TOXICITY).get("Outer").isEmpty()) {
                    toxicityTreeOuterFound = Boolean.TRUE;
                }

                if (!previousTTOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !activityMechanishOuterFound && !indicationOuterFound && !targetTreeOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !taxonomyTreeOuterFound && !endpointTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(TREEVIEW_TOXICITY).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(TREEVIEW_TOXICITY).get("Outer") + " ) ";
                    }
                }

            }

            /* :: Taxonomy Treeview Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TAXONOMY)) {


                /**
                 * Toxicity Treeview Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousTATOperator = StringUtils.EMPTY;

                onlyTaxonomyTree = taxonomyTreeviewComponent.checkTaxonomyTreeviewCombinationStatus(searchCountInputDTOList);

                if (onlyTaxonomyTree) {
                    taxonomyTreeInnerQuery = queryData.get(TREEVIEW_TAXONOMY).get("orstatus");
                } else {

                    int checkTOTPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TAXONOMY)) {

                            if (checkTOTPosition == 1) {
                                innerCombination = queryData.get(TREEVIEW_TAXONOMY).get("Inner");
                            } else {
                                if (previousTATOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(TREEVIEW_TAXONOMY).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(TREEVIEW_TAXONOMY).get("Inner");
                            }

                        } else {
                            previousTATOperator = innerSearchCountInputDTO.getOperator();
                            checkTOTPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(TREEVIEW_TAXONOMY).get("Outer")) && !queryData.get(TREEVIEW_TAXONOMY).get("Outer").isEmpty()) {
                    taxonomyTreeOuterFound = Boolean.TRUE;
                }

                if (!previousTATOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !activityMechanishOuterFound && !indicationOuterFound && !targetTreeOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !endpointTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(TREEVIEW_TAXONOMY).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(TREEVIEW_TAXONOMY).get("Outer") + " ) ";
                    }
                }

            }




            /* :: Activity Endpoint Treeview Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_ENDPOINT)) {


                /**
                 * Toxicity Treeview Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousAETOperator = StringUtils.EMPTY;

                onlyEndpointTree = endpointTreeviewComponent.checkEndpointTreeviewCombinationStatus(searchCountInputDTOList);

                if (onlyEndpointTree) {
                    endpointTreeInnerQuery = queryData.get(TREEVIEW_ENDPOINT).get("orstatus");
                } else {

                    int checkAETPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_ENDPOINT)) {

                            if (checkAETPosition == 1) {
                                innerCombination = queryData.get(TREEVIEW_ENDPOINT).get("Inner");
                            } else {
                                if (previousAETOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(TREEVIEW_ENDPOINT).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(TREEVIEW_ENDPOINT).get("Inner");
                            }

                        } else {
                            previousAETOperator = innerSearchCountInputDTO.getOperator();
                            checkAETPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }

                if(Objects.nonNull(queryData.get(TREEVIEW_ENDPOINT).get("Outer")) && !queryData.get(TREEVIEW_ENDPOINT).get("Outer").isEmpty()) {
                    endpointTreeOuterFound = Boolean.TRUE;
                }

                if (!previousAETOperator.equalsIgnoreCase("not")) {
                    if (!targetOuterFound && !assayOuterFound && !sourceOuterFound && !activityTypeOuterFound && !activityMechanishOuterFound && !indicationOuterFound && !targetTreeOuterFound && !indicationTreeOuterFound && !admeTreeOuterFound && !toxicityTreeOuterFound && !taxonomyTreeOuterFound) {
                        outerCombination = outerCombination + " ( " + queryData.get(TREEVIEW_ENDPOINT).get("Outer") + " ) ";
                    } else {
                        outerCombination = outerCombination + " or ( " + queryData.get(TREEVIEW_ENDPOINT).get("Outer") + " ) ";
                    }
                }

            }




            /* :: Compound Category Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(COMPOUND_CATEGORIES)) {

                /**
                 * Compound Category Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyClinicalPhases = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousCCOperator = StringUtils.EMPTY;

                onlyCompoundCategories = compoundCategoriesComponent.checkCompoundCategoriesCombinationStatus(searchCountInputDTOList);

                if (onlyCompoundCategories) {
                    compoundCategoriesInnerQuery = queryData.get(COMPOUND_CATEGORIES).get("orstatus");
                } else {

                    int checkCCPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(COMPOUND_CATEGORIES)) {

                            if (checkCCPosition == 1) {
                                innerCombination = queryData.get(COMPOUND_CATEGORIES).get("Inner");
                            } else {
                                if (previousCCOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(COMPOUND_CATEGORIES).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(COMPOUND_CATEGORIES).get("Inner");
                            }

                        } else {
                            previousCCOperator = innerSearchCountInputDTO.getOperator();
                            checkCCPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }
            }

            /* :: Compound Phases Search :: */
            if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(CLINICAL_PHASES)) {

                /**
                 * Compound Phases Query combinations
                 */

                onlyTargetTree = Boolean.FALSE;
                onlyAdmeTree = Boolean.FALSE;
                onlyIndicationTree = Boolean.FALSE;
                onlyToxicityTree = Boolean.FALSE;
                onlyTaxonomyTree = Boolean.FALSE;
                onlyEndpointTree = Boolean.FALSE;
                onlyActivityMechanism = Boolean.FALSE;
                onlyActivityTypes = Boolean.FALSE;
                onlyBibliography = Boolean.FALSE;
                onlyIndication = Boolean.FALSE;
                onlyStructure = Boolean.FALSE;
                onlyCompoundCategories = Boolean.FALSE;
                onlySource = Boolean.FALSE;

                String previousCPOperator = StringUtils.EMPTY;

                onlyClinicalPhases = clinicalPhasesComponent.checkClinicalPhasesCombinationStatus(searchCountInputDTOList);

                if (onlyClinicalPhases) {
                    clinicalPhasesInnerQuery = queryData.get(CLINICAL_PHASES).get("orstatus");
                } else {

                    int checkCPPosition = 1;

                    for (SearchCountInputDTO innerSearchCountInputDTO : searchCountInputDTOList) {
                        if (innerSearchCountInputDTO.getSourceOption().equalsIgnoreCase(CLINICAL_PHASES)) {

                            if (checkCPPosition == 1) {
                                innerCombination = queryData.get(CLINICAL_PHASES).get("Inner");
                            } else {
                                if (previousCPOperator.equalsIgnoreCase("not"))
                                    innerCombination = innerCombination + " and not " + queryData.get(CLINICAL_PHASES).get("Inner");
                                else
                                    innerCombination = innerCombination + " and " + queryData.get(CLINICAL_PHASES).get("Inner");
                            }

                        } else {
                            previousCPOperator = innerSearchCountInputDTO.getOperator();
                            checkCPPosition++;
                        }
                    }

                }

                if (searchCountInputDTO.getOperator().equalsIgnoreCase("OR")) {
                    innerCombination = innerCombination + " or ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("AND")) {
                    innerCombination = innerCombination + " and ";
                } else if (searchCountInputDTO.getOperator().equalsIgnoreCase("NOT")) {
                    innerCombination = innerCombination + " and not ";
                }
            }

        }

        String finalCombinedQuery = "( select str_id from target_search.str_id_based ";

        if (!onlyBibliography) {
            if (!innerCombination.isEmpty()) {
                finalCombinedQuery = " str_id in " + finalCombinedQuery + "where  " + innerCombination + " )";
            }
            if (!outerCombination.isEmpty()) {
                finalCombinedQuery = " str_id in " + finalCombinedQuery + " and ( " + outerCombination + " )";
            }
        } else {
            finalCombinedQuery = refInnerQuery;
        }

        if (!onlyBibliography) {
            finalCombinedQuery = finalCombinedQuery + refQuery;
            finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+\\)\\s+and", " ) and");
        }

        // Activity Mechanism
        if(onlyActivityMechanism) {
            finalCombinedQuery = activityMechanismInnerQuery;
        }

        // Activity Mechanism
        if(onlyActivityTypes) {
            finalCombinedQuery = activityTypesInnerQuery;
        }

        // Target Treeview
        if(onlyTargetTree) {
            finalCombinedQuery = targetTreeInnerQuery;
        }

        // Adme Treeview
        if(onlyAdmeTree) {
            finalCombinedQuery = admeTreeInnerQuery;
        }

        // Toxicity Treeview
        if(onlyToxicityTree) {
            finalCombinedQuery = toxicityTreeInnerQuery;
        }

        // Taxonomy Treeview
        if(onlyTaxonomyTree) {
            finalCombinedQuery = taxonomyTreeInnerQuery;
        }

        // Activity Endpoint Treeview
        if(onlyEndpointTree) {
            finalCombinedQuery = endpointTreeInnerQuery;
        }

        // Indication Treeview
        if(onlyIndicationTree) {
            finalCombinedQuery = indicationTreeInnerQuery;
        }

        // Indication
        if (onlyIndication) {
            finalCombinedQuery = indicationInnerQuery;
        }

        // Compound Categories
        if (onlyCompoundCategories) {
            finalCombinedQuery = compoundCategoriesInnerQuery;
        }

        // Clinical Phases
        if (onlyClinicalPhases) {
            finalCombinedQuery = clinicalPhasesInnerQuery;
        }

        // Source Phases
        if (onlySource) {
            finalCombinedQuery = sourceInnerQuery;
        }

        // If only Target Simple search Selection happens
        finalCombinedQuery = finalCombinedQuery.replaceAll("str_id\\s+in\\s+str_id\\s+in", " str_id in ");

        // For Activity Mechanism Combinations
        /*finalCombinedQuery = finalCombinedQuery.replaceAll("where\\s+and", "where");
        finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+and", "and");
        finalCombinedQuery = finalCombinedQuery.replaceAll("varchar\\[\\]\\)\\s+\\)\\s+stdname_id", "varchar[])) or  stdname_id");
        finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+not\\s+and\\s+not", "and not");
        finalCombinedQuery = finalCombinedQuery.replaceAll("and\\s+not\\s+\\)", " ) ");*/

        finalCombinedQuery = stringUtility.removeUnhandledQueryCombinations(finalCombinedQuery);

        System.out.println("====finalCombinedQuery=====> " + finalCombinedQuery);

        return finalCombinedQuery;
    }

    /**
     * @param searchCountInputDTOList
     * @param userJdbc
     * @return
     */
    private Map<String, Map<String, String>> preparedQueryForSearch(List<SearchCountInputDTO> searchCountInputDTOList, UserJdbc userJdbc) {

        Map<String, String> targetOrgQuery = new HashMap<>();
        Map<String, String> assayTypeOrgQuery = new HashMap<>();
        Map<String, String> structureOrgQuery = new HashMap<>();
        Map<String, String> bibliographyOrgQuery = new HashMap<>();
        Map<String, String> activityMechanismOrgQuery = new HashMap<>();
        Map<String, String> activityTypesOrgQuery = new HashMap<>();
        Map<String, String> indicationOrgQuery = new HashMap<>();
        Map<String, String> targetTreeviewOrgQuery = new HashMap<>();
        Map<String, String> indicationTreeviewOrgQuery = new HashMap<>();
        Map<String, String> admeTreeviewOrgQuery = new HashMap<>();
        Map<String, String> toxicityTreeviewOrgQuery = new HashMap<>();
        Map<String, String> taxonomyTreeviewOrgQuery = new HashMap<>();
        Map<String, String> endpointTreeviewOrgQuery = new HashMap<>();
        Map<String, String> compoundCategoriesOrgQuery = new HashMap<>();
        Map<String, String> clinicalPhasesOrgQuery = new HashMap<>();
        Map<String, String> sourceOrgQuery = new HashMap<>();

        Map<String, Map<String, String>> queriesData = new HashMap<>();

        for (SearchCountInputDTO searchCountInputDTO : searchCountInputDTOList) {
            if (Objects.nonNull(searchCountInputDTO.getSimpleSearch())) {
                if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TARGET)) {
                    // For Targets
                    List<TargetSynonymsDTO> targetSynonymsDTOList = searchCountInputDTO.getSimpleSearch().getTargetSynonymsDTOList();
                    List<BigDecimal> andList = new ArrayList<>();
                    List<BigDecimal> orList = new ArrayList<>();
                    List<BigDecimal> notList = new ArrayList<>();
                    for (TargetSynonymsDTO targetSynonymsDTO : targetSynonymsDTOList) {
                        if (targetSynonymsDTO.getOperator().equals("&")) {
                            andList.add(new BigDecimal(targetSynonymsDTO.getStdnameId()));
                        } else if (targetSynonymsDTO.getOperator().equals("|")) {
                            orList.add(new BigDecimal(targetSynonymsDTO.getStdnameId()));
                        } else if (targetSynonymsDTO.getOperator().equals("!")) {
                            notList.add(new BigDecimal(targetSynonymsDTO.getStdnameId()));
                        }
                    }

                    orList.addAll(andList);

                    targetOrgQuery = targetComponent.prepareTargetQuery(andList, orList, notList, StringUtils.EMPTY);


                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ASSAY)) {
                    // For Assay Type
                    List<OntoAssayTypeDTO> ontoAssayTypeDTOList = searchCountInputDTO.getSimpleSearch().getOntoAssayTypeDTOList();
                    List<String> andList = new ArrayList<>();
                    List<String> orList = new ArrayList<>();
                    List<String> notList = new ArrayList<>();
                    Boolean isAssayQuery = false;
                    for (OntoAssayTypeDTO ontoAssayTypeDTO : ontoAssayTypeDTOList) {
                        if (ontoAssayTypeDTO.getOperator().equals("&")) {
                            andList.add(ontoAssayTypeDTO.getAssayType());
                        } else if (ontoAssayTypeDTO.getOperator().equals("|")) {
                            orList.add(ontoAssayTypeDTO.getAssayType());
                        } else if (ontoAssayTypeDTO.getOperator().equals("!")) {
                            notList.add(ontoAssayTypeDTO.getAssayType());
                        }
                    }

                    orList.addAll(andList);
                    assayTypeOrgQuery = assayComponent.prepareAssayQuery(andList, orList, notList, isAssayQuery);


                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(STRUCTURE)) {
                    // For Structure
                    List<CompoundSynonymsDTO> compoundSynonymsDTOList = searchCountInputDTO.getSimpleSearch().getCompoundSynonymsDTOList();
                    List<BigDecimal> structureIds = new ArrayList<>();
                    for (CompoundSynonymsDTO compoundSynonymsDTO : compoundSynonymsDTOList) {
                        structureIds.add(new BigDecimal(compoundSynonymsDTO.getStrId()));
                    }

                    structureOrgQuery = structureComponent.prepareStructureQuery(structureIds);
                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(BIBLIOGRAPHY_REFERENCE)) {
                    // For Bibliography
                    List<ReferenceMasterDTO> referenceMasterDTOList = searchCountInputDTO.getSimpleSearch().getBibliographyReferenceMasterDToList();
                    List<BigDecimal> referenceIds = new ArrayList<>();
                    for (ReferenceMasterDTO referenceMasterDTO : referenceMasterDTOList) {
                        referenceIds.add(new BigDecimal(referenceMasterDTO.getRefId()));
                    }

                    bibliographyOrgQuery = bibliographyComponent.getSimpleSearchQueryCombination(referenceIds);

                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_MECHANISM)) {

                    // For Activity Mechanism
                    List<ListActivityMechanismDTO> activityMechanismDTOs = searchCountInputDTO.getSimpleSearch().getActivityMechanismDTOList();

                    activityMechanismOrgQuery = activityMechanismComponent.getSimpleSearchQueryCombination(activityMechanismDTOs);

                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_TYPES)) {

                    // For Activity Types
                    List<ListActivityTypesDTO> activityTypesDTOs = searchCountInputDTO.getSimpleSearch().getActivityTypesDTOList();

                    activityTypesOrgQuery = activityTypesComponent.getSimpleSearchQueryCombination(activityTypesDTOs);

                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(INDICATION)) {

                    // For Indication
                    List<ListTherapeuticUseDTO> therapeuticUseDTOList = searchCountInputDTO.getSimpleSearch().getTherapeuticUseDTOList();

                    indicationOrgQuery = indicationComponent.getSimpleSearchQueryCombination(therapeuticUseDTOList);
                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(COMPOUND_CATEGORIES)) {

                    // For Component Categories
                    compoundCategoriesOrgQuery = compoundCategoriesComponent.prepareCompoundCategoriesSimpleQuery(searchCountInputDTO.getSimpleSearch().getCompoundCategoriesDTOList());

                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(CLINICAL_PHASES)) {

                    // For Component Phases
                    clinicalPhasesOrgQuery = clinicalPhasesComponent.prepareClinicalPhasesSimpleQuery(searchCountInputDTO.getSimpleSearch().getClinicalPhasesDTOList());

                } else if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(SOURCE)) {

                    // For Source Search
                    sourceOrgQuery = sourceComponent.prepareSourceSimpleQuery(searchCountInputDTO.getSimpleSearch().getSourceSynonymsDTOList());
                }
            } else if (Objects.nonNull(searchCountInputDTO.getAdvancedSearch())) {

                if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(TARGET)) {

                    // Implementing Advanced Search for Target
                    targetOrgQuery = targetComponent.prepareTargetAdvancedSearchQuery(searchCountInputDTO, userJdbc);
                }

                if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(STRUCTURE)) {

                    // Implementing Advanced Search for Structure
                    structureOrgQuery = structureComponent.prepareStructureAdvancedSearchQuery(searchCountInputDTO, userJdbc);
                }

                if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(BIBLIOGRAPHY_REFERENCE)) {

                    // Implementing Advanced Search for Bibliography
                    bibliographyOrgQuery = bibliographyComponent.getAdvancedSearchQueryCombination(searchCountInputDTO.getAdvancedSearch().getBibliographyAdvSearchDTO(), userJdbc);
                }

                if (searchCountInputDTO.getSourceOption().equalsIgnoreCase(ACTIVITY_TYPES)) {

                    // Implementing Advanced Search for Activity Type
                    activityTypesOrgQuery = activityTypesComponent.getAdvancedSearchQueryCombination(searchCountInputDTO.getAdvancedSearch().getActivityTypeAdvSearchDTO(), userJdbc);
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(INDICATION)) {

                    // Implementing Advanced Search for Indication
                    indicationOrgQuery = indicationComponent.getAdvancedSearchQueryCombination(searchCountInputDTO.getAdvancedSearch().getIndicationAdvSearchDTO(), userJdbc);
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(SOURCE)) {

                    // Implementing Advanced Search for Source
                    sourceOrgQuery = sourceComponent.prepareSourceAdvancedQuery(searchCountInputDTO.getAdvancedSearch().getSourceAdvSearchDTO());
                }
            } else if(Objects.nonNull(searchCountInputDTO.getTreeSearch())) {

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TARGET)) {

                    // Implementing Treeview query for Target
                    targetTreeviewOrgQuery = targetTreeviewComponent.getSimpleSearchQueryCombination(searchCountInputDTO.getTreeSearch().getTargetTreeviewDTO());
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_INDICATION)) {

                    // Implementing Treeview query for Indication
                    indicationTreeviewOrgQuery = indicationTreeviewComponent.getSimpleSearchQueryCombination(searchCountInputDTO.getTreeSearch().getIndicationTreeviewDTO());
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_ADME)) {

                    // Implementing Treeview query for Adme
                    admeTreeviewOrgQuery = admeTreeviewComponent.getSimpleSearchQueryCombination(searchCountInputDTO.getTreeSearch().getAdmeTreeviewDTO());
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TOXICITY)) {

                    // Implementing Treeview query for Toxicity
                    toxicityTreeviewOrgQuery = toxicityTreeviewComponent.getSimpleSearchQueryCombination(searchCountInputDTO.getTreeSearch().getToxicityTreeviewDTO());
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_TAXONOMY)) {

                    // Implementing Treeview query for Taxonomy
                    taxonomyTreeviewOrgQuery = taxonomyTreeviewComponent.getSimpleSearchQueryCombination(searchCountInputDTO.getTreeSearch().getTaxonomyTreeviewDTO());
                }

                if(searchCountInputDTO.getSourceOption().equalsIgnoreCase(TREEVIEW_ENDPOINT)) {

                    // Implementing Treeview query for Activity Endpoint
                    endpointTreeviewOrgQuery = endpointTreeviewComponent.getSimpleSearchQueryCombination(searchCountInputDTO.getTreeSearch().getEndpointTreeviewDTO());
                }
            }
        }

        queriesData.put(TARGET, targetOrgQuery);
        queriesData.put(ASSAY, assayTypeOrgQuery);
        queriesData.put(STRUCTURE, structureOrgQuery);
        queriesData.put(BIBLIOGRAPHY_REFERENCE, bibliographyOrgQuery);
        queriesData.put(ACTIVITY_MECHANISM, activityMechanismOrgQuery);
        queriesData.put(ACTIVITY_TYPES, activityTypesOrgQuery);
        queriesData.put(TREEVIEW_TARGET, targetTreeviewOrgQuery);
        queriesData.put(TREEVIEW_INDICATION, indicationTreeviewOrgQuery);
        queriesData.put(TREEVIEW_ADME, admeTreeviewOrgQuery);
        queriesData.put(TREEVIEW_TOXICITY, toxicityTreeviewOrgQuery);
        queriesData.put(TREEVIEW_TAXONOMY, taxonomyTreeviewOrgQuery);
        queriesData.put(TREEVIEW_ENDPOINT, endpointTreeviewOrgQuery);
        queriesData.put(INDICATION, indicationOrgQuery);
        queriesData.put(COMPOUND_CATEGORIES, compoundCategoriesOrgQuery);
        queriesData.put(CLINICAL_PHASES, clinicalPhasesOrgQuery);
        queriesData.put(SOURCE, sourceOrgQuery);

        return queriesData;
    }
}

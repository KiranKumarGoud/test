package com.excelra.mvc.repository;

import com.excelra.mvc.model.search.VisualChartData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This is AllMappingIds Component which is used in AllMappingIdsDAO Layer, the common methods are defined in Component for code reuseability.
 *
 * @author venkateswarlu.s
 */
@Component
public class AllMappingIdsComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllMappingIdsComponent.class);

    /**
     * Preparing Classification Filtered Query based on VisualChartData clicked Ids
     *
     * @param visualChartDataList
     * @return
     */
    public String getClassificationFilterQuery(List<VisualChartData> visualChartDataList) {

        List<Integer> visualChartDataIds = new ArrayList<>();

        for (VisualChartData visualChartData : visualChartDataList) {
            visualChartDataIds.add(Integer.parseInt(visualChartData.getId()));
        }

        String classificationQuery = " stdname_id in ( select s.stdname_id from target_search.target_ontology_all_childs tc left outer join " +
                "target_search.standard_name_master s on (s.target_ontology_id = tc.child_target_ontology_id) where tc.target_ontology_id in ("+StringUtils.join(visualChartDataIds, ",")+") )";

        return classificationQuery;
    }

    /**
     * Preparing Bibliography Filtered Query based on VisualChartData clicked Ids
     *
     * @param visualChartDataList
     * @return
     */
    public String getBibliographyFilterQuery(List<VisualChartData> visualChartDataList) {

        List<Integer> visualChartDataIds = new ArrayList<>();

        for (VisualChartData visualChartData : visualChartDataList) {
            visualChartDataIds.add(Integer.parseInt(visualChartData.getId()));
        }

        String bibliographyQuery = " ref_id in (select br.ref_id from target_search.bibliograph_ontology_all_childs bc inner join " +
                "target_search.bilbiograph_reference_map br on (br.bibliograph_ontology_id = bc.child_bibliograph_ontology_id) where bc.bibliograph_ontology_id in ("+StringUtils.join(visualChartDataIds, ",")+") )";

        return bibliographyQuery;
    }

    /**
     * Preparing Yearwise Filtered Query based on VisualChartData clicked Ids
     *
     * @param visualChartDataList
     * @return
     */
    public String getYearwiseFilterQuery(List<VisualChartData> visualChartDataList) {

        List<Integer> visualChartDataIds = new ArrayList<>();

        for (VisualChartData visualChartData : visualChartDataList) {
            visualChartDataIds.add(Integer.parseInt(visualChartData.getId()));
        }

        String yearWiseQuery = " ref_id in (select br.ref_id from target_search.ref_year_ontology_all_childs bc inner join " +
                "target_search.ref_year_reference_map br on (br.ref_year_ontology_id = bc.child_ref_year_ontology_id) where bc.ref_year_ontology_id in ( "+StringUtils.join(visualChartDataIds, ",")+" ) )";

        return yearWiseQuery;
    }

    /**
     * Preparing Indication Filtered Query based on VisualChartData clicked Ids
     *
     * @param visualChartDataList
     * @return
     */
    public String getIndicationFilterQuery(List<VisualChartData> visualChartDataList) {

        List<String> visualChartDataIds = new ArrayList<>();

        for (VisualChartData visualChartData : visualChartDataList) {
            visualChartDataIds.add(visualChartData.getId());
        }

        String indicationQuery = " gvk_id in ( select gvk_id from target_search.therapeutic_area ta where ta.icd10_code in " +
                "(select child_icd10_code from target_search.indication_ontology_all_childs ioac where ioac.icd10_code in ("+visualChartDataIds.stream().collect(Collectors.joining("','", "'", "'"))+")) )";

        return indicationQuery;
    }

    /**
     * Preparing Taxonomy Filtered Query based on VisualChartData clicked Ids
     *
     * @param visualChartDataList
     * @return
     */
    public String getTaxonomyFilterQuery(List<VisualChartData> visualChartDataList) {

        List<Integer> visualChartDataIds = new ArrayList<>();

        for (VisualChartData visualChartData : visualChartDataList) {
            visualChartDataIds.add(Integer.parseInt(visualChartData.getId()));
        }

        String taxonomyQuery = " source_id in (select s.source_id from target_search.taxonomy_all_childs tc left outer join " +
                "target_search.source_classification_master s on (s.tax_id = tc.child_tax_id) where tc.tax_id in ("+StringUtils.join(visualChartDataIds, ",")+") )";

        return taxonomyQuery;
    }
}

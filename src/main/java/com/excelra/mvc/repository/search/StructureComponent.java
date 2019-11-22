package com.excelra.mvc.repository.search;

import com.excelra.mvc.model.CompoundSynonymsDTO;
import com.excelra.mvc.model.ListCasNosDTO;
import com.excelra.mvc.model.search.SearchCountInputDTO;
import com.excelra.mvc.model.search.Structure.PropertySearch;
import com.excelra.mvc.model.search.Structure.PropertyValues;
import com.excelra.mvc.model.search.StructureAdvSearchDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.utility.StringUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Structure Advanced search Component
 *
 * @author venkateswarlu.s
 */
@Component
public class StructureComponent {

    private static String DESALTED = "Desalted";
    private static String SUB_STRUCTURE = "Substructure";
    private static String EXACT = "Exact";
    private static String SIMILARITY = "Similarity";
    private static String FRAMEWORK_1 = "Framework1";
    private static String FRAMEWORK_2 = "Framework2";
    private static String SKELETON = "Skeleton";
    private static String COMPOUND_NAME = "COMPOUND_NAME";
    private static String COMPOUND = "Compound";
    private static String CAS_NO = "CAS_NO";
    private static String CASNO = "CasNo";
    private static String GVK_ID = "GVK_ID";
    private static String GVKID = "GvkId";
    private static String STR_ID = "STR_ID";
    private static String STRID = "StrId";
    private static String DRAW = "Draw";
    private static String SMILES = "Smiles";
    private static String INCHIKEY = "Inchikey";

    private String MOL_WEIGHT = "mol_weight";
    private String HB_DONORS = "hb_donors";
    private String HB_ACCEPTOR = "hb_acceptor";
    private String C_LOG_P = "clogp";
    private String ROT_BONDS = "rot_bonds";
    private String PSA = "psa";
    private String PKA_VALUE = "pka_value";
    private String LOG_D_CALC = "logd_calc";
    private String SOLUBILITY = "solubility";
    private String PKB = "pkb";

    private String INNER = "Inner";
    private String OUTER = "Outer";
    private String SEARCH_TYPE = "SearchType";
    private String IS_CASNO = "isCasNo";
    private String IS_COMPOUNDNAME = "isCompoundName";
    private String IS_GVKID = "isGvkId";
    private String IS_STRID = "isStrId";
    private String STRUCTURE = "Structure";

    private static final String TERM_SEARCH_COMPOUND_NAME_INNER_QUERY = " str_id in( select str_id from structure_search.compound_synonyms where";

    private static final String TERM_SEARCH_CAS_NO_INNER_QUERY = " str_id in( select str_id from structure_search.list_cas_no where ";

    private static final String TERM_SEARCH_GVK_ID_INNER_QUERY = " str_id in( select str_id from structure_search.structure_details where";

    private static final String TEARM_SEARCH_STR_ID_INNER_QUERY = "";

    @Value("${target.advsearch.pdbids.in.query}")
    private String targetAdvSearchPdbIdsInQuery;

    @Autowired
    private StringUtility stringUtility;

    /**
     * To Prepare Structure Advanced Search Query combinations.
     *
     * @param searchCountInputDTO
     * @param userJdbc
     * @return
     */
    public Map<String, String> prepareStructureAdvancedSearchQuery(SearchCountInputDTO searchCountInputDTO, UserJdbc userJdbc) {

        Map<String, String> structureQuery = new HashMap<>();

        StructureAdvSearchDTO structureAdvSearchDTO = searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO();

        String strCategory = StringUtils.EMPTY;
        String strFeature = StringUtils.EMPTY;
        String operator = StringUtils.EMPTY;


        String combinedQuery = StringUtils.EMPTY;

        if(Objects.nonNull(structureAdvSearchDTO.getChemistrySearch())) {

            // Actual String to search
            String conditionStrValue = StringUtils.EMPTY;
            String conditionStrArray = StringUtils.EMPTY;
            String conditionStrFileArray = StringUtils.EMPTY;
            if(Objects.nonNull(structureAdvSearchDTO.getChemistrySearch().getStrDraw())) {
                conditionStrValue = structureAdvSearchDTO.getChemistrySearch().getStrDraw();
            }
             if(Objects.nonNull(structureAdvSearchDTO.getChemistrySearch().getStrComboData())) {
                conditionStrArray = String.join(",", structureAdvSearchDTO.getChemistrySearch().getStrComboData().stream()
                        .map(name -> ("'" + name + "'")).collect(Collectors.toList()));
                // conditionStrValue = structureAdvSearchDTO.getChemistrySearch().getStrComboData();
            }if(Objects.nonNull(structureAdvSearchDTO.getChemistrySearch().getStrComboFileData())) {
                conditionStrFileArray= String.join(",", structureAdvSearchDTO.getChemistrySearch().getStrComboFileData().stream()
                        .map(name -> ("'" + name + "'")).collect(Collectors.toList()));
                        // conditionStrValue = structureAdvSearchDTO.getChemistrySearch().getStrComboFileData();
            }

            // Feature Options
            String strFeatureOptions = StringUtils.join(structureAdvSearchDTO.getChemistrySearch().getStrFeatureOptions(), "', '");
            if(!strFeatureOptions.isEmpty()) strFeatureOptions = "('" + strFeatureOptions.toUpperCase() + "')";

            strCategory = structureAdvSearchDTO.getChemistrySearch().getStrCategory();
            strFeature = structureAdvSearchDTO.getChemistrySearch().getStrFeature();

            if(structureAdvSearchDTO.getChemistrySearch().getStrReadonly().equalsIgnoreCase(DRAW)) {

                /**
                 * This Condition is only for Draw Option
                 */

                if(strFeature.equalsIgnoreCase(DESALTED)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {
                        operator = " |<| ";
                        combinedQuery = "select str_id from structure_search.search_structures where '" + conditionStrValue + "' "
                                + operator + " SMILES_MOL and structure_type IN " + strFeatureOptions + ")";
                    } else if(strCategory.equalsIgnoreCase(EXACT)) {
                        operator = " |=| ";
                        combinedQuery = "select str_id from structure_search.search_structures where '" + conditionStrValue + "' "
                                + operator + " SMILES_MOL and structure_type IN " + strFeatureOptions + ")";
                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {

                        combinedQuery = "select str_id from (select str_id, smiles_mol |~| '"+conditionStrValue+"' " +
                                "sim_per, structure_type from structure_search.search_structures where row('"+conditionStrValue+"', "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|<~| smiles_mol) str_smile where sim_per between "
                                +structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+" and structure_type IN "+strFeatureOptions + ")";
                    }

                } else if(strFeature.equalsIgnoreCase(FRAMEWORK_1)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {

                        combinedQuery = "select str_id from structure_search.search_structures where framework1_id in (select framework1_id from structure_search.framework1_master where '"+conditionStrValue+"' " +
                                "|<| smiles_mol ) and structure_type IN "+strFeatureOptions + ")";

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {

                        combinedQuery = "select str_id from structure_search.search_structures where framework1_id in (select framework1_id from structure_search.framework1_master where '"+conditionStrValue+"' " +
                                "|=| smiles_mol ) and structure_type IN "+strFeatureOptions + ")";

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {

                        combinedQuery = "select str_id from structure_search.search_structures where framework1_id in ( select framework1_id from (select framework1_id, smiles_mol |~| '"+conditionStrValue+"' " +
                                "sim_per, structure_type from structure_search.framework1_master where row ('"+conditionStrValue+"', "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol) " +
                                "str_smile where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+") and structure_type IN "+strFeatureOptions + ")";

                    }

                } else if(strFeature.equalsIgnoreCase(FRAMEWORK_2)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {

                        combinedQuery = "select str_id from structure_search.search_structures where FRAMEWORK2_ID in (select FRAMEWORK2_ID from structure_search.framework2_master where '"+conditionStrValue+"' " +
                                "|<| smiles_mol) and structure_type IN "+strFeatureOptions + ")";

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {

                        combinedQuery = "select str_id from structure_search.search_structures where FRAMEWORK2_ID in (select FRAMEWORK2_ID from structure_search.framework2_master where '"+conditionStrValue+"' " +
                                "|=| smiles_mol) and structure_type IN "+strFeatureOptions + ")";

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {

                        combinedQuery = "select str_id from structure_search.search_structures where FRAMEWORK2_ID in (select FRAMEWORK2_ID from (select FRAMEWORK2_ID, smiles_mol |~| '"+conditionStrValue+"' " +
                                "sim_per, structure_type from structure_search.framework2_master where row ('"+conditionStrValue+"', "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol) " +
                                "str_smile  where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+")  and structure_type IN "+strFeatureOptions + ")";

                    }

                } else if(strFeature.equalsIgnoreCase(SKELETON)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {

                        combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in(select skeleton_id from structure_search.skeleton_master where '"+conditionStrValue+"' " +
                                "|<| smiles_mol) and structure_type IN " + strFeatureOptions + ")";

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {

                        combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in(select skeleton_id from structure_search.skeleton_master where '"+conditionStrValue+"' " +
                                "|=| smiles_mol) and structure_type IN " + strFeatureOptions + ")";

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {

                        combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in (select skeleton_id from (select skeleton_id, smiles_mol |~| '"+conditionStrValue+"' " +
                                "sim_per, structure_type from structure_search.skeleton_master where row ('C1=CC=C(C=C1)C1=C(C=CC=C1)C1=CC=CC=C1', '"+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+"') :: sim_filter|~>| smiles_mol) " +
                                "str_smile where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+") and structure_type IN "+strFeatureOptions + ")";

                    }

                }

            } if(structureAdvSearchDTO.getChemistrySearch().getStrReadonly().equalsIgnoreCase(SMILES)) {

                /**
                 * This Condition is only for Smiles and Smarts Options
                 */

                int index = 0;
                String strComboDataQuery = StringUtils.EMPTY;
                String strComboFileDataArray = StringUtils.EMPTY;
                for(String strComboData : structureAdvSearchDTO.getChemistrySearch().getStrComboData()) {
                    if(index == 0) {
                        strComboDataQuery = " select '"+strComboData+"'::molecule('sample') smiles ";
                        index++;
                    } else {
                        strComboDataQuery = strComboDataQuery + " union all select '"+strComboData+"'::molecule('sample') ";
                    }
                }

                for(String strComboFileData : structureAdvSearchDTO.getChemistrySearch().getStrComboFileData()) {
                    if(index == 0) {
                        //adding the line for the for the file upload
                        strComboFileDataArray = " select '"+strComboFileData+"'::molecule('sample') smiles ";
                        index++;
                    } else {
                        strComboFileDataArray = strComboFileDataArray + " union all select '"+strComboFileData+"'::molecule('sample') " ;
                    }
                }

                if(strFeature.equalsIgnoreCase(DESALTED)) {
                    /***
                     * adding the code for the file upload into the smiles/smart option
                     * for the disalted , framwork1 ,framwork2 , skelton
                     * added by priyanka
                     */

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = " select STR_ID from structure_search.search_structures ss, ( "+strComboDataQuery+" ) s where smiles |<| smiles_mol " + "and structure_type in" + strFeatureOptions + ")";
                        }
                        if(!strComboFileDataArray.isEmpty() ){
                            combinedQuery = " select STR_ID from structure_search.search_structures ss, ( "+strComboFileDataArray+" ) s where smiles |<| smiles_mol " + "and structure_type in" + strFeatureOptions + ")";
                            System.out.println("lets check default category" + combinedQuery);

                        }

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = " select STR_ID from structure_search.search_structures ss, ( "+strComboDataQuery+" ) s where smiles |=| smiles_mol " + "and structure_type in" + strFeatureOptions + ")";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = " select STR_ID from structure_search.search_structures ss, ( "+strComboFileDataArray+" ) s where smiles |=| smiles_mol " + "and structure_type in" + strFeatureOptions + ")";
                        }

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = " select str_id from (select str_id, smiles_mol |~| smiles sim_per, structure_type from structure_search.search_structures ss, " +
                                    "( "+strComboDataQuery+" ) s where row(smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol ) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue() + "and structure_type in" + strFeatureOptions + ")" ;

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = " select str_id from (select str_id, smiles_mol |~| smiles sim_per, structure_type from structure_search.search_structures ss, " +
                                    "( "+strComboFileDataArray+" ) s where row(smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol ) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue() + "and structure_type in" + strFeatureOptions + ")" ;

                        }

                    }

                } else if(strFeature.equalsIgnoreCase(FRAMEWORK_1)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where framework1_id in (select framework1_id from structure_search.framework1_master fm , " +
                                    "( "+strComboDataQuery+" ) s where s.smiles |<|  smiles_mol )";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where framework1_id in (select framework1_id from structure_search.framework1_master fm , " +
                                    "( "+strComboFileDataArray+" ) s where s.smiles |<|  smiles_mol )";

                        }

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where framework1_id in(select framework1_id from structure_search.framework1_master fm , " +
                                    "( "+strComboDataQuery+" ) s where s.smiles |=| smiles_mol ) and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where framework1_id in(select framework1_id from structure_search.framework1_master fm , " +
                                    "( "+strComboFileDataArray+" ) s where s.smiles |=| smiles_mol ) and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where framework1_id in ( select framework1_id from (select framework1_id, smiles_mol |~| s.smiles sim_per, structure_type " +
                                    "from structure_search.framework1_master, ( "+strComboDataQuery+" ) s where row(s.smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol ) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+" ) and structure_type IN("+strFeatureOptions+")" + ")";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where framework1_id in ( select framework1_id from (select framework1_id, smiles_mol |~| s.smiles sim_per, structure_type " +
                                    "from structure_search.framework1_master, ( "+strComboFileDataArray+" ) s where row(s.smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol ) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+" ) and structure_type IN("+strFeatureOptions+")" + ")";

                        }

                    }

                } else if(strFeature.equalsIgnoreCase(FRAMEWORK_2)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where framework2_id in (select framework2_id from structure_search.framework2_master fm, " +
                                    "( "+strComboDataQuery+" ) s where s.smiles |<|  smiles_mol ) and structure_type IN( "+strFeatureOptions+" )" + ")";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where framework2_id in (select framework2_id from structure_search.framework2_master fm, " +
                                    "( "+strComboFileDataArray+" ) s where s.smiles |<|  smiles_mol ) and structure_type IN( "+strFeatureOptions+" )" + ")";

                        }

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where framework2_id in(select framework2_id from structure_search.framework2_master fm , " +
                                    "( "+strComboDataQuery+" ) s where s.smiles |=| smiles_mol ) and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        } if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where framework2_id in(select framework2_id from structure_search.framework2_master fm , " +
                                    "( "+strComboFileDataArray+" ) s where s.smiles |=| smiles_mol ) and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where framework2_id in (select framework2_id from (select framework2_id, smiles_mol |~| s.smiles sim_per, structure_type from structure_search.framework2_master, " +
                                    "( "+strComboDataQuery+" ) s where row(s.smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+") and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where framework2_id in (select framework2_id from (select framework2_id, smiles_mol |~| s.smiles sim_per, structure_type from structure_search.framework2_master, " +
                                    "( "+strComboFileDataArray+" ) s where row(s.smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+") and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }
                    }

                } else if(strFeature.equalsIgnoreCase(SKELETON)) {

                    if(strCategory.equalsIgnoreCase(SUB_STRUCTURE)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in (select skeleton_id from structure_search.SKELETON_MASTER fm , " +
                                    "( "+strComboDataQuery+" ) s where s.smiles |<| smiles_mol) and structure_type IN ( "+strFeatureOptions+" )" + ")";
                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in (select skeleton_id from structure_search.SKELETON_MASTER fm , " +
                                    "( "+strComboFileDataArray+" ) s where s.smiles |<| smiles_mol) and structure_type IN ( "+strFeatureOptions+" )" + ")";
                        }

                    } else if(strCategory.equalsIgnoreCase(EXACT)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in (select skeleton_id from structure_search.skeleton_master fm , " +
                                    "( "+strComboDataQuery+" ) s where s.smiles |=| smiles_mol) and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }if(!strComboFileDataArray.isEmpty()) {
                            combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in (select skeleton_id from structure_search.skeleton_master fm , " +
                                    "( " + strComboFileDataArray + " ) s where s.smiles |=| smiles_mol) and structure_type IN ( " + strFeatureOptions + " )" + ")";
                        }

                    } else if(strCategory.equalsIgnoreCase(SIMILARITY)) {
                        if(!strComboDataQuery.isEmpty() ) {
                            combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in ( select skeleton_id from (select FRAMEWORK2_ID, smiles_mol |~| s.smiles sim_per, structure_type from structure_search.skeleton_master, " +
                                    "( "+strComboDataQuery+" ) s where row(s.smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+") and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }if(!strComboFileDataArray.isEmpty()){
                            combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in ( select skeleton_id from (select FRAMEWORK2_ID, smiles_mol |~| s.smiles sim_per, structure_type from structure_search.skeleton_master, " +
                                    "( "+strComboFileDataArray+" ) s where row(s.smiles, "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+") :: sim_filter|~>| smiles_mol) str_smile " +
                                    "where sim_per between "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMinValue()+" and "+structureAdvSearchDTO.getChemistrySearch().getStrSimilarityMaxValue()+") and structure_type IN ( "+strFeatureOptions+" )" + ")";

                        }

                    }

                }


            } else if(structureAdvSearchDTO.getChemistrySearch().getStrReadonly().equalsIgnoreCase(INCHIKEY)) {

                /**
                 * This Condition is only for Inchi and InchiKey Options.
                 */

                if(strFeature.equalsIgnoreCase(DESALTED)) {
                    //implementaion for file upload
                    if(!conditionStrArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures " +
                                "where inchi_key in (" + conditionStrArray + ") " +
                                "and structure_type IN " + strFeatureOptions + ")";
                    } if(!conditionStrFileArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures " +
                                "where inchi_key in (" + conditionStrFileArray+ ") " +
                                "and structure_type IN " + strFeatureOptions + ")";

                    }

                } else if(strFeature.equalsIgnoreCase(FRAMEWORK_1)) {
                    if(!conditionStrArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures where framework1_id in ( " +
                                "select framework1_id from structure_search.framework1_master " +
                                "where inchi_key in (" + conditionStrArray + ")) " +
                                "and structure_type IN " + strFeatureOptions + ")";
                    }if(!conditionStrFileArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures where framework1_id in ( " +
                                "select framework1_id from structure_search.framework1_master " +
                                "where inchi_key in (" + conditionStrFileArray + ")) " +
                                "and structure_type IN " + strFeatureOptions + ")";
                    }

                } else if(strFeature.equalsIgnoreCase(FRAMEWORK_2)) {

                    if(!conditionStrArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures where framework2_id in( " +
                                "select framework2_id from structure_search.framework2_master " +
                                "where inchi_key in (" + conditionStrArray + ")) " +
                                "and structure_type IN " + strFeatureOptions + ")";
                    }if(!conditionStrFileArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures where framework2_id in( " +
                                "select framework2_id from structure_search.framework2_master " +
                                "where inchi_key in (" + conditionStrFileArray + ")) " +
                                "and structure_type IN " + strFeatureOptions + ")";
                    }

                } else if(strFeature.equalsIgnoreCase(SKELETON)) {
                    if(!conditionStrArray.isEmpty() ) {
                        combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in( " +
                                "select skeleton_id from structure_search.skeleton_master " +
                                "where inchi_key in (" + conditionStrArray + ")) " +
                                "and structure_type IN " + strFeatureOptions + ")";
                    }if(!conditionStrFileArray.isEmpty() ){
                        combinedQuery = "select str_id from structure_search.search_structures where skeleton_id in( " +
                                "select skeleton_id from structure_search.skeleton_master " +
                                "where inchi_key in (" + conditionStrFileArray + ")) " +
                                "and structure_type IN " + strFeatureOptions + ")";

                    }

                }

            }

            /**
             *
             * This Fix is because of Structure related Postgresql Advanced queries are not able to execute and
             * getting error/exception, so executing these queries as normal Jdbc connection and get the results and
             * plase as regular process.
             * -- Org Parameter is "combinedQuery", replace with Response "strResultsInString"
             */
            /*List<BigDecimal> strResults = structureQueryResult.getStrIdsForStructureSubQuery(combinedQuery);
            String strResultsInString = StringUtils.EMPTY;
            if(!strResults.isEmpty()) strResultsInString = StringUtils.join(strResults, ", ");*/

            structureQuery.put(INNER, StringUtils.EMPTY);
            /*structureQuery.put(OUTER, strResultsInString);*/
            structureQuery.put(OUTER, combinedQuery);
            structureQuery.put(SEARCH_TYPE, STRUCTURE);
            structureQuery.put(IS_CASNO, "false");
            structureQuery.put(IS_COMPOUNDNAME, "false");
            structureQuery.put(IS_GVKID, "false");
            structureQuery.put(IS_STRID, "false");

        } else if(Objects.nonNull(structureAdvSearchDTO.getTermSearch())) {

            String inQueryValues = StringUtils.EMPTY;
            String mainQuery = StringUtils.EMPTY;
            List<String> andList = new ArrayList<>();
            List<String> orList = new ArrayList<>();
            List<String> notList = new ArrayList<>();

            if (searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrReadonly().equalsIgnoreCase(COMPOUND_NAME)) {

                if (!searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData().isEmpty()) {

                    List<String> compoundNameFileData = Collections.singletonList(StringUtils.join(searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData(), "','"));
                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(compoundNameFileData, COMPOUND);
                    structureQuery = preparedAdvancedStrcutureQuery(compoundNameFileData, COMPOUND);

                } else {

                    List<CompoundSynonymsDTO> structureAdvSearchInputDTOList = searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getCompoundSynonymsDTOList();

                    for (CompoundSynonymsDTO compoundSynonymsDTO : structureAdvSearchInputDTOList) {
                        if (compoundSynonymsDTO.getOperator().equals("&")) {
                            andList.add(compoundSynonymsDTO.getCompoundSynonym());
                        } else if (compoundSynonymsDTO.getOperator().equals("|")) {
                            orList.add(compoundSynonymsDTO.getCompoundSynonym());
                        } else if (compoundSynonymsDTO.getOperator().equals("!")) {
                            notList.add(compoundSynonymsDTO.getCompoundSynonym());
                        }
                    }
                    andList.addAll(orList);

                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(orList, COMPOUND);
                    structureQuery = preparedAdvancedStrcutureQuery(orList, COMPOUND);

                }
            } else if (searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrReadonly().equalsIgnoreCase(CAS_NO)) {

                if (!searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData().isEmpty()) {
                    List<String> casNoFileData = Collections.singletonList(StringUtils.join(searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData(), "','"));
                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(casNoFileData, CASNO);
                    structureQuery = preparedAdvancedStrcutureQuery(casNoFileData, CASNO);

                } else {

                    List<ListCasNosDTO> structureAdvSearchInputDTOList = searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getListCasNosDTOList();

                    for (ListCasNosDTO listCasNosDTO : structureAdvSearchInputDTOList) {
                        if (listCasNosDTO.getOperator().equals("&")) {
                            andList.add(listCasNosDTO.getCasNo());
                        } else if (listCasNosDTO.getOperator().equals("|")) {
                            orList.add(listCasNosDTO.getCasNo());
                        } else if (listCasNosDTO.getOperator().equals("!")) {
                            notList.add(listCasNosDTO.getCasNo());
                        }
                    }
                    andList.addAll(orList);

                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(andList, CASNO);
                    structureQuery = preparedAdvancedStrcutureQuery(andList, CASNO);

                }

            } else if (searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrReadonly().equalsIgnoreCase(GVK_ID)) {

                if (!searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData().isEmpty()) {
                    List<String> gvkIdFiledata = Collections.singletonList(StringUtils.join(searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData(), "','"));
                    System.out.println("test the fileData for the gvk ids  : :  :" + gvkIdFiledata);
                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(gvkIdFiledata, GVKID);
                    structureQuery = preparedAdvancedStrcutureQuery(gvkIdFiledata, GVKID);

                } else {

                    List<String> gvkIdData = Collections.singletonList(StringUtils.join(searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboData(), "','"));

                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(gvkIdData, GVKID);
                    structureQuery = preparedAdvancedStrcutureQuery(gvkIdData, GVKID);

                }
            } else if (searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrReadonly().equalsIgnoreCase(STR_ID)) {

                if (!searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData().isEmpty()) {
                    List<String> strIdFileData = Collections.singletonList(StringUtils.join(searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboFileData(), "','"));
                    System.out.println("test the fileData for the str ids  : :  :" + strIdFileData);
                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(strIdFileData, STRID);
                    structureQuery = preparedAdvancedStrcutureQuery(strIdFileData, STRID);

                } else {

                    List<String> strIdData = Collections.singletonList(StringUtils.join(searchCountInputDTO.getAdvancedSearch().getStructureAdvSearchDTO().getTermSearch().getStrComboData(), "','"));

                    // structureQuery = allMappingIdsComponent.preparedAdvancedStrcutureQuery_version2(strIdData, STRID);
                    structureQuery = preparedAdvancedStrcutureQuery(strIdData, STRID);

                }
            }

        } else if(Objects.nonNull(structureAdvSearchDTO.getPropertySearch())) {

            PropertySearch propertySearch = structureAdvSearchDTO.getPropertySearch();
            String propertyQuery = " and ";

            propertyQuery = "select str_id from structure_search.structural_properties where ";

            if(Objects.nonNull(propertySearch.getStructuralProperties()) && !propertySearch.getStructuralProperties().isEmpty()) {

                String firstKey = propertySearch.getStructuralProperties().keySet().stream().findFirst().get();

                for (Map.Entry<String, PropertyValues> entry : propertySearch.getStructuralProperties().entrySet()) {

                    PropertyValues propertyValues = entry.getValue();

                    if(entry.getKey().equalsIgnoreCase(MOL_WEIGHT)  ) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(MOL_WEIGHT) && (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null )) {
                            propertyQuery = propertyQuery + andAppend + " mol_weight between " + propertyValues.getMinValue() + " and " + propertyValues.getMaxValue() + "" + " and ";
                        }

                    } else if(entry.getKey().equalsIgnoreCase(HB_DONORS) && (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null)) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(HB_DONORS) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null)) {
                            propertyQuery = propertyQuery + andAppend + " hb_donors between " + propertyValues.getMinValue() + " and " + propertyValues.getMaxValue() + "" + " and ";
                        }

                    } else if(entry.getKey().equalsIgnoreCase(HB_ACCEPTOR)) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(HB_ACCEPTOR) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null)) {
                             propertyQuery = propertyQuery + andAppend + " hb_acceptor between " + propertyValues.getMinValue() + " and " + propertyValues.getMaxValue() + "" + " and ";
                        }

                    } else if(entry.getKey().equalsIgnoreCase(C_LOG_P)) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(C_LOG_P) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null)) {
                            propertyQuery = propertyQuery + andAppend + " clogp between " + propertyValues.getMinValue() + " and " + propertyValues.getMaxValue() + "" + " and ";
                        }

                    } else if(entry.getKey().equalsIgnoreCase(ROT_BONDS) ) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(ROT_BONDS) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue()  != null)) {
                             propertyQuery = propertyQuery + andAppend + " rot_bonds between " + propertyValues.getMinValue() + " and " + propertyValues.getMaxValue() + "" + " and ";
                        }

                    } else if(entry.getKey().equalsIgnoreCase(PSA) ) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(PSA) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null)) {
                            propertyQuery = propertyQuery + andAppend + " psa between " + propertyValues.getMinValue() + " and " + propertyValues.getMaxValue() + "" + " and ";
                        }

                    }
                }


            } else if(!propertySearch.getPhysicoChemicalProperties().isEmpty()) {

                String firstKey = propertySearch.getPhysicoChemicalProperties().keySet().stream().findFirst().get();

                for (Map.Entry<String, PropertyValues> entry : propertySearch.getPhysicoChemicalProperties().entrySet()) {

                    PropertyValues propertyValues = entry.getValue();

                    if(entry.getKey().equalsIgnoreCase(PKA_VALUE) ) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(PKA_VALUE) && (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null )){
                            propertyQuery = propertyQuery  + " pka_value between "+propertyValues.getMinValue()+" and "+propertyValues.getMaxValue()+"" + " and ";

                        }

                    } else if(entry.getKey().equalsIgnoreCase(LOG_D_CALC)) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;

                        if(firstKey.equalsIgnoreCase(LOG_D_CALC) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null )) {
                            propertyQuery = propertyQuery  + " logd_calc between "+propertyValues.getMinValue()+" and "+propertyValues.getMaxValue()+"" + " and " ;


                        }
                    } else if(entry.getKey().equalsIgnoreCase(SOLUBILITY)) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(SOLUBILITY) || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null) )  {
                            propertyQuery = propertyQuery + " solubility between "+propertyValues.getMinValue()+" and "+propertyValues.getMaxValue()+"" + " and ";

                        }
                    } else if(entry.getKey().equalsIgnoreCase(PKB) ) {

                        String andAppend = " and ";
                        andAppend = StringUtils.EMPTY;
                        if(firstKey.equalsIgnoreCase(PKB)  || (propertyValues.getMaxValue() != null && propertyValues.getMinValue() != null) ){
                            propertyQuery = propertyQuery + " pkb_value between "+propertyValues.getMinValue()+" and "+propertyValues.getMaxValue()+"" + " and "  ;

                        }

                    }
                }
            }

            propertyQuery = propertyQuery.substring(0,propertyQuery.lastIndexOf("and")) + ")";
            System.out.println("ok lets test" + propertyQuery);
            structureQuery.put(INNER, StringUtils.EMPTY);
            structureQuery.put(OUTER, propertyQuery);
            structureQuery.put(SEARCH_TYPE, STRUCTURE);
            structureQuery.put(IS_CASNO, "false");
            structureQuery.put(IS_COMPOUNDNAME, "false");
            structureQuery.put(IS_GVKID, "false");
            structureQuery.put(IS_STRID, "false");

        }


        return structureQuery;
    }

    /**
     * Structure Advanced options combinations preparation.
     *
     * @param combinedQuery
     * @param queryData
     * @return
     */
    public String structureCombinedQueryPreparation(String combinedQuery, Map<String, Map<String, String>> queryData) {

        if ((Objects.nonNull(queryData.get(STRUCTURE)) && Objects.nonNull(queryData.get(STRUCTURE).get("Outer")) && !queryData.get(STRUCTURE).get("Outer").isEmpty())) {
            if (queryData.get(STRUCTURE).get("SearchType").equalsIgnoreCase("Structure")) {

                if (Objects.nonNull(queryData.get(STRUCTURE).get("Outer")) && !queryData.get(STRUCTURE).get("Outer").isEmpty()){

                    combinedQuery = combinedQuery + "  str_id In (" + queryData.get(STRUCTURE).get("Outer") ;
                    System.out.print("test else" + combinedQuery);

                }

            } else if (queryData.get(STRUCTURE).get("SearchType").equalsIgnoreCase("Term")) {
                if (!queryData.get(STRUCTURE).get("Outer").isEmpty()) {
                    if (queryData.get("Structure").get("isCompoundName").equalsIgnoreCase("true") && queryData.get("Structure")
                            .get("isCasNo").equalsIgnoreCase("false") && queryData.get("Structure").get("isGvkId").equalsIgnoreCase("false")
                            && queryData.get("Structure").get("isStrId").equalsIgnoreCase("false")) {
                        combinedQuery =  combinedQuery  + TERM_SEARCH_COMPOUND_NAME_INNER_QUERY  +  queryData.get("Structure").get("Outer");
                    } else if (queryData.get("Structure").get("isCasNo").equalsIgnoreCase("true") && queryData.get("Structure")
                            .get("isCompoundName").equalsIgnoreCase("false") && queryData.get("Structure").get("isGvkId").equalsIgnoreCase("false")
                            && queryData.get("Structure").get("isStrId").equalsIgnoreCase("false")) {
                        combinedQuery = combinedQuery + TERM_SEARCH_CAS_NO_INNER_QUERY + queryData.get("Structure").get("Outer");
                    } else if (queryData.get("Structure").get("isCasNo").equalsIgnoreCase("false") && queryData.get("Structure")
                            .get("isCompoundName").equalsIgnoreCase("false") && queryData.get("Structure").get("isGvkId").equalsIgnoreCase("true")
                            && queryData.get("Structure").get("isStrId").equalsIgnoreCase("false")) {
                        combinedQuery = combinedQuery + TERM_SEARCH_GVK_ID_INNER_QUERY + queryData.get("Structure").get("Outer");
                    } else if (queryData.get("Structure").get("isCasNo").equalsIgnoreCase("false") && queryData.get("Structure")
                            .get("isCompoundName").equalsIgnoreCase("false") && queryData.get("Structure").get("isGvkId").equalsIgnoreCase("false")
                            && queryData.get("Structure").get("isStrId").equalsIgnoreCase("true")) {
                        combinedQuery = combinedQuery + TEARM_SEARCH_STR_ID_INNER_QUERY + queryData.get("Structure").get("Outer");
                    }
                }
            }
        } else {
            if (Objects.nonNull(queryData.get(STRUCTURE).get("Inner")) && !queryData.get(STRUCTURE).get("Inner").isEmpty()){
                //as per the final query changed made the changes in this
                if (combinedQuery.contains("AND") || combinedQuery.contains("AND NOT")) {


                    String queryManupulatedData = combinedQuery.substring(0, combinedQuery.lastIndexOf("AND"));

                    combinedQuery = combinedQuery.trim();


                    String[] wordList = combinedQuery.split("\\s+(AND|OR)\\s*");
                    if (combinedQuery.contains("ANDor")) {

                        combinedQuery = combinedQuery.substring(0, combinedQuery.lastIndexOf("AND")).replace("ANDor", "or");
                    } else if (combinedQuery.contains("AND NOTor")) {
                        combinedQuery = combinedQuery.substring(0, combinedQuery.lastIndexOf("AND")).replace("AND NOTor", "or");

                        // combinedQuery = queryManupulatedData  + "and" + queryData.get(STRUCTURE).get("Inner").replace("and str_id in (  )" , "");
                    }else {
                        if(combinedQuery.contains("or") || !combinedQuery.contains("AND") ){
                            combinedQuery = combinedQuery.substring(0, combinedQuery.lastIndexOf("AND")).replace("or","and") ;
                            System.out.println("checking for its connecting or not" + combinedQuery);
                        }else if(combinedQuery.contains("AND NOT")){
                            if(combinedQuery.contains("AND")  ) {
                                //now at 4th july added
                                // combinedQuery = combinedQuery.substring(0, combinedQuery.lastIndexOf("AND"));//.replace("AND" , "AND NOT").replace("AND NOT NOT" , "AND NOT") ;//+  + queryData.get(STRUCTURE).get("Inner");
                                combinedQuery = combinedQuery + queryData.get(STRUCTURE).get("Inner");
                                System.out.println("nai aaya" + combinedQuery);
                            }
                        }else{
                            combinedQuery = combinedQuery.substring(0, combinedQuery.lastIndexOf("AND")) + "and" + queryData.get(STRUCTURE).get("Inner");
                            System.out.println("priyanka or or check" + combinedQuery);

                        }

                    }
                }else {
                    combinedQuery = combinedQuery + queryData.get(STRUCTURE).get("Inner") ;//+ queryData.get(ASSAY).get("Outer");
                    System.out.println("rkh do" + combinedQuery);

                }
            }
        }

        return combinedQuery;
    }


    /**
     * prepare advanced search query for Structure category, it will consider different options to construct the query.
     *
     * @param dataList
     * @param option
     * @return
     */
    public Map<String, String> preparedAdvancedStrcutureQuery(List<String> dataList, String option) {

        String isCompoundName = "false";
        String isCasNo = "false";
        String isGvkId = "false";
        String isStrId = "false";

        String structureTypeOrgQuery1 = StringUtils.EMPTY;
        String structureTypeOrgQuery2 = StringUtils.EMPTY;

        Map<String, String> structureQueries = new HashMap<>();

        if (dataList.size() > 0) {
            structureTypeOrgQuery1 = "";

            if (option.equalsIgnoreCase("Compound")) {
                structureTypeOrgQuery2 = " compound_synonym in (" + stringUtility.prepareStringForCompoundName(dataList) + ")) ";
                isCompoundName = "true";
            } else if (option.equalsIgnoreCase("CasNo")) {
                structureTypeOrgQuery2 = " cas_no in (" + stringUtility.prepareCasNoString(dataList) + ")) ";
                isCasNo = "true";
            } else if (option.equalsIgnoreCase("GvkId")) {
                structureTypeOrgQuery2 = " gvk_id in (" + stringUtility.prepareString(dataList) + ")) ";
                isGvkId = "true";
            } else if (option.equalsIgnoreCase("StrId")) {
                structureTypeOrgQuery2 = " str_id in (" + stringUtility.prepareString(dataList) + ") ";
                isStrId = "true";
            }
        }

        structureQueries.put("Inner", structureTypeOrgQuery1);
        structureQueries.put("Outer", structureTypeOrgQuery2);
        structureQueries.put("isCompoundName", isCompoundName);
        structureQueries.put("SearchType", "Term");
        structureQueries.put("isCasNo", isCasNo);
        structureQueries.put("isGvkId", isGvkId);
        structureQueries.put("isStrId", isStrId);
        structureQueries.put("searchType", "Term");

        return structureQueries;
    }

    /**
     * Structure field Simple Search query preparation based on Single Combinations.
     *
     * @param strIdList
     * @return
     */
    public Map<String, String> prepareStructureQuery(List<BigDecimal> strIdList) {

        String structureInnerQuery = StringUtils.EMPTY;
        String structureOuterQuery = StringUtils.EMPTY;

        Map<String, String> structureQueries = new HashMap<>();

        if (strIdList.size() > 0)
            structureInnerQuery = " ( str_id in ( " + StringUtils.join(strIdList, ',') + " ) ) ";

        structureQueries.put("Inner", structureInnerQuery);
        structureQueries.put("Outer", structureOuterQuery);
        structureQueries.put("psw", StringUtils.EMPTY);
        structureQueries.put("orstatus", "true");
        structureQueries.put("isStructure", "true");

        return structureQueries;
    }
}

package com.excelra.mvc.util;

import chemaxon.formats.MolExporter;
import chemaxon.formats.MolFormatException;
import chemaxon.license.Base64;
import chemaxon.marvin.io.formats.mdl.MolExport;
import chemaxon.struc.Molecule;
import com.excelra.mvc.model.Mail.Mail;
import com.excelra.mvc.model.tabularview.ActivityTabDTO;
import com.excelra.mvc.model.tabularview.AssayTabDTO;
import com.excelra.mvc.model.tabularview.ReferenceTabDTO;
import com.excelra.mvc.model.tabularview.StructureDetailsTabDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.css.CSSPrimitiveValue;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/***
 * <p>
 *     the util class named as WriteDataToCSV
 *     for write th data to csv file with the headers and values
 *     of Activity,Assay,Reference,Strcuture
 * </p>
 * @author priyanka.veidhey
 */
public class WriteDataToCSV {
    /**
     * @param writer
     * @param activityTabList
     * @param assayTabList
     * @param referenceTabList
     * @param structureTabList
     * @param //userJdbcObject , UserJdbc userJdbcObject
     * <p>
     * method is used for All the tab Activity,Assay,Strcuture,Referenece
     * </p>
     */

    private static final String[] emailList = {"priyanka.veidhey@excelra.com"};
    private static final String[] emailListcc = {"shreekanth.gummadi@excelra.com,deepsingh.chouhan@excelra.com", "suresh.velishala@Excelra.com" ,"gopalk.athota@excelra.com","amarnath.nelaturi@Excelra.com"};

    public static void writeDataToCsvUsingStringArray(PrintWriter writer, List<ActivityTabDTO> activityTabList, List<AssayTabDTO> assayTabList, List<ReferenceTabDTO> referenceTabList, List<StructureDetailsTabDTO> structureTabList) {
        String[] CSV_ACTIVITY_TITLE = {"Activity Details"};

        String[] CSV_ASSAY_TITLE = {"Assay Details"};

        String[] CSV_REFERENCE_TITLE = {"Reference Details"};

        String[] CSV_STRUCTURE_TITLE = {"Structure Details"};

        String[] CSV_ACTIVITY_NEWLINE = {""};

        String[] CSV_ACTIVITY_HEADER = {"ACT_ID", "ACTIVITY_TYPE", "STD_ACTIVITY_TYPE", "ACTIVITY_UOM", "STANDARD_UOM", "ACTIVITY_PREFIX", "STD_ACT_PREFIX", "ACTIVITY_VALUE", "SD", "ACTIVITY_REMARKS", "MICRO_MOLARVALUE", "ASSAY_TYPE", "ENZYME_CELL_ASSAY", "COMMON_NAME", "ACTIVITY_MECHANISM", "SOURCE", "CELLS_CELLLINE_ORGAN", "MEASURED", "ROA", "ASSAY_METHOD_NAME", "DOSE", "DOSE_UOM", "DOSE_PREFIX", "ACTIVITY", "PARAMETER", "REFERENCE"};

        String[] CSV_ASSAY_HEADER = {"ASSAY_ID", "PROTEIN", "STANDARD_NAME", "MUTANT", "WILD_TYPE", "NEW_RESIDUE", "OLD_RESIDUE", "OPERATION", "ASSAY_POSITION", "TARGET_PROFILE", "P_S_M", "BUFFER", "BUFFER_CONC", "BUFFER_PREFIX", "BUFFER_UOM", "SUBSTRATE", "SUBSTRATE_CONC", "SUBSTRATE_PREFIX", "SUBSTRATE_UOM", "RADIO_LIGAND", "RADIO_LIGAND_PREFIX", "RADIO_LIGAND_UOM", "RADIO_LIGAND_CONC", "CO_ADMINISTERED", "CO_ADMINISTERED_PREFIX", "CO_ADMINISTERED_UOM", "CO_ADMINISTERED_VALUE", "IN_PRESENCE_OF", "IN_PRESENCE_OF_CONC", "IN_PRESENCE_OF_PREFIX", "IN_PRESENCE_OF_UOM", "INCUBATION_TIME", "INCUBATION_PREFIX", "INCUBATION_UOM", "TEMPERATURE", "TEMPERATURE_PREFIX", "TEMPERATURE_UOM", "PH_PREFIX", "PH"};

        String[] CSV_REFERENCE_HEADER = {"REF_ID", "REF_TYPE", "JOURNAL_PATENT_NAME", "REFERENCE", "YEAR", "VOLUME", "ISSUE", "START_PAGE", "END_PAGE", "DOI", "PUBMED_ID", "PATENT_NO", "APPLICATION_TYPE", "ISSN_NO", "TITLE", "ABSTRACT", "AUTHORS", "COMPANY_NAMES", "COMPANY_ADDRESSES"};

        String[] CSV_STRUCTURE_HEADER = {"GVK_ID", "COMPOUND_NAMES", "SUB_SMILES", "INCHIKEY", "ORIGINATOR", "DERIVATIVES", "BIO_ASSAY", "PRIMARY_TARGETS", "MECHANISMS", "COMPOUND_STATUS", "MOL_FORMULA", "MOL_WEIGHT"};


        try (
                CSVWriter csvWriter = new CSVWriter(writer,
                        '\t',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_ACTIVITY_HEADER);

            if (Objects.nonNull(activityTabList)) {
                for (ActivityTabDTO activityTabDataReturn : activityTabList) {
                    String[] activityData = new String[]{
                            activityTabDataReturn.getActId().toString(),
                            activityTabDataReturn.getActivityType(),
                            activityTabDataReturn.getStdActivityType(),
                            activityTabDataReturn.getActivityUom(),
                            activityTabDataReturn.getStandardUom(),
                            activityTabDataReturn.getActivityPrefix(),
                            activityTabDataReturn.getStdActPrefix(),
                            activityTabDataReturn.getActivityValue() + "",
                            activityTabDataReturn.getSd().toString(),
                            activityTabDataReturn.getActivityRemarks(),
                            activityTabDataReturn.getMicroMolarvalue().toString(),
                            activityTabDataReturn.getAssayType(),
                            activityTabDataReturn.getEnzymeCellAssay(),
                            activityTabDataReturn.getCommonName(),
                            activityTabDataReturn.getActivityMechanism(),
                            activityTabDataReturn.getSource(),
                            activityTabDataReturn.getCellsCelllineOrgan(),
                            activityTabDataReturn.getMeasured(),
                            activityTabDataReturn.getRoa(),
                            activityTabDataReturn.getAssayMethodName(),
                            activityTabDataReturn.getDose(),
                            activityTabDataReturn.getDoseUom(),
                            activityTabDataReturn.getDosePrefix(),
                            activityTabDataReturn.getActivity(),
                            activityTabDataReturn.getParameter(),
                            activityTabDataReturn.getReference().toString(),

                    };

                    csvWriter.writeNext(activityData);

                }
                writer.println();

            }

            csvWriter.writeNext(CSV_ASSAY_HEADER);
            for (AssayTabDTO assayTabDataReturn : assayTabList) {
                String[] assayData = new String[]{
                        assayTabDataReturn.getAssayId() + "",
                        assayTabDataReturn.getProtein() + "",
                        assayTabDataReturn.getStandardName() + "",
                        assayTabDataReturn.getMutant() + "",
                        assayTabDataReturn.getWildType() + "",
                        assayTabDataReturn.getNewResidue() + "",
                        assayTabDataReturn.getOldResidue() + "",
                        assayTabDataReturn.getOperation() + "",
                        assayTabDataReturn.getAssayPosition() + "",
                        assayTabDataReturn.getTargetProfile() + "",
                        assayTabDataReturn.getP_s_m() + "",
                        assayTabDataReturn.getBuffer() + "",
                        assayTabDataReturn.getBufferConc() + "",
                        assayTabDataReturn.getBufferPrefix() + "",
                        assayTabDataReturn.getBufferUom() + "",
                        assayTabDataReturn.getSubstrate() + "",
                        assayTabDataReturn.getSubstrateConc() + "",
                        assayTabDataReturn.getSubstratePrefix() + "",
                        assayTabDataReturn.getSubstrateUom() + "",
                        assayTabDataReturn.getRadioLigand() + "",
                        assayTabDataReturn.getRadioLigandPrefix() + "",
                        assayTabDataReturn.getRadioLigandUom() + "",
                        assayTabDataReturn.getRadioLigandConc() + "",
                        assayTabDataReturn.getCoAdministered() + "",
                        assayTabDataReturn.getCoAdministeredPrefix() + "",
                        assayTabDataReturn.getCoAdministeredUom() + "",
                        assayTabDataReturn.getCoAdministeredValue() + "",
                        assayTabDataReturn.getInPresenceOf() + "",
                        assayTabDataReturn.getInPresenceOfConc() + "",
                        assayTabDataReturn.getInPresenceOfPrefix() + "",
                        assayTabDataReturn.getInPresenceOfUom() + "",
                        assayTabDataReturn.getIncubationTime() + "",
                        assayTabDataReturn.getIncubationPrefix() + "",
                        assayTabDataReturn.getIncubationUom() + "",
                        assayTabDataReturn.getTemperature() + "",
                        assayTabDataReturn.getTemperaturePrefix() + "",
                        assayTabDataReturn.getTemperatureUom() + "",
                        assayTabDataReturn.getPhPrefix() + "",
                        assayTabDataReturn.getPh() + ""

                };

                csvWriter.writeNext(assayData);
                writer.println();
            }
            csvWriter.writeNext(CSV_REFERENCE_HEADER);

            for (ReferenceTabDTO referenceTabDataReturn : referenceTabList) {
                String[] referenceData = new String[]{
                        referenceTabDataReturn.getRefId() + "",
                        referenceTabDataReturn.getRefType() + "",
                        referenceTabDataReturn.getJournalPatentName(),
                        referenceTabDataReturn.getReference(),
                        referenceTabDataReturn.getYear() + "",
                        referenceTabDataReturn.getVolume() + "",
                        referenceTabDataReturn.getIssue() + "",
                        referenceTabDataReturn.getStartPage() + "",
                        referenceTabDataReturn.getEndPage() + "",
                        referenceTabDataReturn.getDoi() + "",
                        referenceTabDataReturn.getPubmedId() + "",
                        referenceTabDataReturn.getPatentNo() + "",
                        referenceTabDataReturn.getApplicationType() + "",
                        referenceTabDataReturn.getIssnNo() + "",
                        referenceTabDataReturn.getTitle() + "",
                        referenceTabDataReturn.getAbstrac(),
                        referenceTabDataReturn.getAuthors() + "",
                        referenceTabDataReturn.getCompanyNames() + "",
                        referenceTabDataReturn.getCompanyAddresses() + ""

                };
                csvWriter.writeNext(referenceData);
            }
            writer.println();

            csvWriter.writeNext(CSV_STRUCTURE_HEADER);
            for (StructureDetailsTabDTO structureTabDataReturn : structureTabList) {
                String[] structureData = new String[]{
                        structureTabDataReturn.getGvkId() + "",
                        structureTabDataReturn.getCompoundNames() + "",
                        structureTabDataReturn.getSubSmiles() + "",
                        structureTabDataReturn.getInchiKey() + "",
                        structureTabDataReturn.getOriginator() + "",
                        structureTabDataReturn.getDerivatives() + "",
                        structureTabDataReturn.getBioAssay() + "",
                        structureTabDataReturn.getPrimaryTargets() + "",
                        structureTabDataReturn.getMechanisms() + "",
                        structureTabDataReturn.getCompoundStatus() + "",
                        structureTabDataReturn.getMolFormula() + "",
                        structureTabDataReturn.getMolWeight() + ""
                };
                csvWriter.writeNext(structureData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /***
     * <p>
     *     method is for to preapare only the activitydata
     * </p>
     * @param writer
     * @param activityTabList
     */

    public static void writeDataToCsvUsingStringArrayForActivity(PrintWriter writer, List<ActivityTabDTO> activityTabList, List<AssayTabDTO> assayTabDTOList, List<ReferenceTabDTO> referenceTabDTOList, List<StructureDetailsTabDTO> structureDetailsTabDTOList) {

        String[] CSV_ACTIVITY_TITLE = {"Activity Details"};
        String[] CSV_ACTIVITY_NEWLINE = {""};

        String[] CSV_ACTIVITY_HEADER = {"ACT_ID", "ASSAY_ID", "REF_ID", "GVK_ID", "ACTIVITY_TYPE", "STD_ACTIVITY_TYPE", "ACTIVITY_UOM", "STANDARD_UOM", "ACTIVITY_PREFIX", "STD_ACT_PREFIX", "ACTIVITY_VALUE", "SD", "ACTIVITY_REMARKS", "MICRO_MOLARVALUE", "ASSAY_TYPE", "ENZYME_CELL_ASSAY", "COMMON_NAME", "ACTIVITY_MECHANISM", "SOURCE", "CELLS_CELLLINE_ORGAN", "MEASURED", "ROA", "ASSAY_METHOD_NAME", "DOSE", "DOSE_UOM", "DOSE_PREFIX", "ACTIVITY", "PARAMETER", "REFERENCE"};

        try (
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_ACTIVITY_TITLE);
            csvWriter.writeNext(CSV_ACTIVITY_HEADER);
            if (Objects.nonNull(activityTabList)) {
                for (ActivityTabDTO activityTabDataReturn : activityTabList) {
                    for (AssayTabDTO assayTabDTO : assayTabDTOList) {
                        for (ReferenceTabDTO referenceTabDTO : referenceTabDTOList) {
                            for (StructureDetailsTabDTO reStructureDetailsTabDTO : structureDetailsTabDTOList) {
                                String[] activityData = new String[]{
                                        activityTabDataReturn.getActId() + "",
                                        assayTabDTO.getAssayId() + "",
                                        referenceTabDTO.getRefId() + "",
                                        reStructureDetailsTabDTO.getGvkId() + "",
                                        activityTabDataReturn.getActivityType(),
                                        activityTabDataReturn.getStdActivityType() + "",
                                        activityTabDataReturn.getActivityUom() + "",
                                        activityTabDataReturn.getStandardUom() + "",
                                        activityTabDataReturn.getActivityPrefix() + "",
                                        activityTabDataReturn.getStdActPrefix() + "",
                                        activityTabDataReturn.getActivityValue() + "",
                                        activityTabDataReturn.getSd() + "",
                                        activityTabDataReturn.getActivityRemarks() + "",
                                        activityTabDataReturn.getMicroMolarvalue() + "",
                                        activityTabDataReturn.getAssayType() + "",
                                        activityTabDataReturn.getEnzymeCellAssay() + "",
                                        activityTabDataReturn.getCommonName() + "",
                                        activityTabDataReturn.getActivityMechanism() + "",
                                        activityTabDataReturn.getSource() + "",
                                        activityTabDataReturn.getCellsCelllineOrgan() + "",
                                        activityTabDataReturn.getMeasured() + "",
                                        activityTabDataReturn.getRoa() + "",
                                        activityTabDataReturn.getAssayMethodName() + "",
                                        activityTabDataReturn.getDose() + "",
                                        activityTabDataReturn.getDoseUom() + "",
                                        activityTabDataReturn.getDosePrefix() + "",
                                        activityTabDataReturn.getActivity() + "",
                                        activityTabDataReturn.getParameter() + "",
                                        activityTabDataReturn.getReference() + "",

                                };

                                csvWriter.writeNext(activityData);
                            }
                        }
                    }
                }
                csvWriter.writeNext(CSV_ACTIVITY_NEWLINE);
                csvWriter.writeNext(CSV_ACTIVITY_NEWLINE);
            }


        } catch (Exception e) {
            System.out.println("Exception occured into the ActivityTab Data Export" + e.getMessage());
            e.printStackTrace();
        }

    }

    /****
     * <p>
     *      method is for to preapare only the activitydata
     * </p>
     * @param writer
     * @param assayTabList
     */

    public static void writeDataToCsvUsingStringArrayForAssay(PrintWriter writer, List<AssayTabDTO> assayTabList, List<ReferenceTabDTO> referenceTabDTOList, List<StructureDetailsTabDTO> structureDetailsTabDTOList) {

        String[] CSV_ASSAY_TITLE = {"Assay Details"};

        String[] CSV_ASSAY_HEADER = {"ASSAY_ID", "REF_ID", "GVK_ID", "PROTEIN", "STANDARD_NAME", "MUTANT", "WILD_TYPE", "NEW_RESIDUE", "OLD_RESIDUE", "OPERATION", "ASSAY_POSITION", "TARGET_PROFILE", "P_S_M", "BUFFER", "BUFFER_CONC", "BUFFER_PREFIX", "BUFFER_UOM", "SUBSTRATE", "SUBSTRATE_CONC", "SUBSTRATE_PREFIX", "SUBSTRATE_UOM", "RADIO_LIGAND", "RADIO_LIGAND_PREFIX", "RADIO_LIGAND_UOM", "RADIO_LIGAND_CONC", "CO_ADMINISTERED", "CO_ADMINISTERED_PREFIX", "CO_ADMINISTERED_UOM", "CO_ADMINISTERED_VALUE", "IN_PRESENCE_OF", "IN_PRESENCE_OF_CONC", "IN_PRESENCE_OF_PREFIX", "IN_PRESENCE_OF_UOM", "INCUBATION_TIME", "INCUBATION_PREFIX", "INCUBATION_UOM", "TEMPERATURE", "TEMPERATURE_PREFIX", "TEMPERATURE_UOM", "PH_PREFIX", "PH"};

        try (
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_ASSAY_TITLE);
            csvWriter.writeNext(CSV_ASSAY_HEADER);
            for (AssayTabDTO assayTabDataReturn : assayTabList) {
                for (ReferenceTabDTO referenceTabDTO : referenceTabDTOList) {
                    for (StructureDetailsTabDTO structureDetailsTabDTO : structureDetailsTabDTOList) {
                        String[] assayData = new String[]{
                                assayTabDataReturn.getAssayId() + "",
                                referenceTabDTO.getRefId() + "",
                                structureDetailsTabDTO.getGvkId() + "",
                                assayTabDataReturn.getProtein() + "",
                                assayTabDataReturn.getStandardName() + "",
                                assayTabDataReturn.getMutant() + "",
                                assayTabDataReturn.getWildType() + "",
                                assayTabDataReturn.getNewResidue() + "",
                                assayTabDataReturn.getOldResidue() + "",
                                assayTabDataReturn.getOperation() + "",
                                assayTabDataReturn.getAssayPosition() + "",
                                assayTabDataReturn.getTargetProfile() + "",
                                assayTabDataReturn.getP_s_m() + "",
                                assayTabDataReturn.getBuffer() + "",
                                assayTabDataReturn.getBufferConc() + "",
                                assayTabDataReturn.getBufferPrefix() + "",
                                assayTabDataReturn.getBufferUom() + "",
                                assayTabDataReturn.getSubstrate() + "",
                                assayTabDataReturn.getSubstrateConc() + "",
                                assayTabDataReturn.getSubstratePrefix() + "",
                                assayTabDataReturn.getSubstrateUom() + "",
                                assayTabDataReturn.getRadioLigand() + "",
                                assayTabDataReturn.getRadioLigandPrefix() + "",
                                assayTabDataReturn.getRadioLigandUom() + "",
                                assayTabDataReturn.getRadioLigandConc() + "",
                                assayTabDataReturn.getCoAdministered() + "",
                                assayTabDataReturn.getCoAdministeredPrefix() + "",
                                assayTabDataReturn.getCoAdministeredUom() + "",
                                assayTabDataReturn.getCoAdministeredValue() + "",
                                assayTabDataReturn.getInPresenceOf() + "",
                                assayTabDataReturn.getInPresenceOfConc() + "",
                                assayTabDataReturn.getInPresenceOfPrefix() + "",
                                assayTabDataReturn.getInPresenceOfUom() + "",
                                assayTabDataReturn.getIncubationTime() + "",
                                assayTabDataReturn.getIncubationPrefix() + "",
                                assayTabDataReturn.getIncubationUom() + "",
                                assayTabDataReturn.getTemperature() + "",
                                assayTabDataReturn.getTemperaturePrefix() + "",
                                assayTabDataReturn.getTemperatureUom() + "",
                                assayTabDataReturn.getPhPrefix() + "",
                                assayTabDataReturn.getPh() + ""

                        };

                        csvWriter.writeNext(assayData);
                    }
                }

            }


        } catch (Exception e) {
            System.out.println("Exception occured into the AssayTab Data Export" + e.getMessage());
            e.printStackTrace();
        }

    }

    /***
     * <p>
     *     method is for to preapare only the Structure
     * </p>
     * @param writer
     * @param structureTabList
     */

    public static void writeDataToCsvUsingStringArrayForStructure(PrintWriter writer, List<StructureDetailsTabDTO> structureTabList, List<ReferenceTabDTO> referenceTabDTOList) {

        String[] CSV_STRUCTURE_TITLE = {"Structure Details"};

        String[] CSV_STRUCTURE_HEADER = {"GVK_ID", "REF_ID", "COMPOUND_NAMES", "SUB_SMILES", "INCHIKEY", "ORIGINATOR", "DERIVATIVES", "BIO_ASSAY", "PRIMARY_TARGETS", "MECHANISMS", "COMPOUND_STATUS", "MOL_FORMULA", "MOL_WEIGHT"};

        try (
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_STRUCTURE_TITLE);
            csvWriter.writeNext(CSV_STRUCTURE_HEADER);
            for (StructureDetailsTabDTO structureTabDataReturn : structureTabList) {

                for (ReferenceTabDTO referenceTabDTO : referenceTabDTOList) {
                    String[] structureData = new String[]{
                            structureTabDataReturn.getGvkId() + "",
                            referenceTabDTO.getRefId() + "",
                            structureTabDataReturn.getCompoundNames() + "",
                            structureTabDataReturn.getSubSmiles() + "",
                            structureTabDataReturn.getInchiKey() + "",
                            structureTabDataReturn.getOriginator() + "",
                            structureTabDataReturn.getDerivatives() + "",
                            structureTabDataReturn.getBioAssay() + "",
                            structureTabDataReturn.getPrimaryTargets() + "",
                            structureTabDataReturn.getMechanisms() + "",
                            structureTabDataReturn.getCompoundStatus() + "",
                            structureTabDataReturn.getMolFormula() + "",
                            structureTabDataReturn.getMolWeight() + ""
                    };
                    csvWriter.writeNext(structureData);
                }

            }


        } catch (Exception e) {
            System.out.println("Exception occured into the StructureTab Data Export" + e.getMessage());
            e.printStackTrace();
        }

    }


    /****
     * <p>
     *      method is for to prepare only the Reference Data
     * </p>
     * @param writer
     * @param referenceTabList
     */

    public static void writeDataToCsvUsingStringArrayForReference(PrintWriter writer, List<ReferenceTabDTO> referenceTabList) {

        String[] CSV_REFERENCE_TITLE = {"Reference Details"};

        String[] CSV_REFERENCE_HEADER = {"REF_ID", "REF_TYPE", "JOURNAL_PATENT_NAME", "REFERENCE", "YEAR", "VOLUME", "ISSUE", "START_PAGE", "END_PAGE", "DOI", "PUBMED_ID", "PATENT_NO", "APPLICATION_TYPE", "ISSN_NO", "TITLE", "ABSTRACT", "AUTHORS", "COMPANY_NAMES", "COMPANY_ADDRESSES"};
        try (
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_REFERENCE_TITLE);
            csvWriter.writeNext(CSV_REFERENCE_HEADER);

            for (ReferenceTabDTO referenceTabDataReturn : referenceTabList) {
                String[] referenceData = new String[]{
                        referenceTabDataReturn.getRefId() + "",
                        referenceTabDataReturn.getRefType() + "",
                        referenceTabDataReturn.getJournalPatentName(),
                        referenceTabDataReturn.getReference(),
                        referenceTabDataReturn.getYear() + "",
                        referenceTabDataReturn.getVolume() + "",
                        referenceTabDataReturn.getIssue() + "",
                        referenceTabDataReturn.getStartPage() + "",
                        referenceTabDataReturn.getEndPage() + "",
                        referenceTabDataReturn.getDoi() + "",
                        referenceTabDataReturn.getPubmedId() + "",
                        referenceTabDataReturn.getPatentNo() + "",
                        referenceTabDataReturn.getApplicationType() + "",
                        referenceTabDataReturn.getIssnNo() + "",
                        referenceTabDataReturn.getTitle() + "",
                        referenceTabDataReturn.getAbstrac(),
                        referenceTabDataReturn.getAuthors() + "",
                        referenceTabDataReturn.getCompanyNames() + "",
                        referenceTabDataReturn.getCompanyAddresses() + ""

                };
                csvWriter.writeNext(referenceData);
            }

        } catch (Exception e) {
            System.out.println("Exception occured into the ReferenceTab Data Export" + e.getMessage());
            e.printStackTrace();
        }

    }


    /***
     * <p>
     *     method is for the prepare the single export the Single
     *     StructureTab
     * </p>
     */

    public static void writeDtaToSdf(PrintWriter writer, List<StructureDetailsTabDTO> structureDetailsTabDTOList, List<ReferenceTabDTO> referenceTabDTOList, List<AssayTabDTO> assayTabDTOList, List<ActivityTabDTO> activityTabDTOList) {

        try {
            Mail mail = new Mail();
            //molecule create
            Molecule molecule = new Molecule();

            MolExporter molExporter = null;

            StringBuffer stringBuffer = new StringBuffer();

            String s1 = "";

            for (StructureDetailsTabDTO structureTabDataReturn : structureDetailsTabDTOList) {
                chemaxon.util.MolHandler mol = new chemaxon.util.MolHandler(structureTabDataReturn.getSubSmiles());
                System.out.println("inside the for loop of the for the check the subsmiles");
                mol.aromatize();
                molecule = mol.getMolecule();

                s1 = molExporter.exportToFormat(molecule, "mol");

                stringBuffer.append(s1);
                stringBuffer.append(">  <" + "SUB_SMILES" + ">" + "\n");
                stringBuffer.append(structureTabDataReturn.getSubSmiles() + "\n\n");
                stringBuffer.append(">  <" + "GVK_ID" + ">" + "\n");
                stringBuffer.append(structureTabDataReturn.getGvkId() + "\n\n");

                for (ReferenceTabDTO referenceTabDTO : referenceTabDTOList) {
                    stringBuffer.append(">  <" + "journal_patent_name" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getJournalPatentName() + "\n\n");
                    stringBuffer.append(">  <" + "year" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getYear() + "\n\n");
                    stringBuffer.append(">  <" + "volume" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getVolume() + "\n\n");
                    stringBuffer.append(">  <" + "issue" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getIssue() + "\n\n");
                    stringBuffer.append(">  <" + "start_page" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getStartPage() + "\n\n");
                    stringBuffer.append(">  <" + "end_page" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getEndPage() + "\n\n");
                    stringBuffer.append(">  <" + "reference" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getReference() + "\n\n");
                    stringBuffer.append(">  <" + "pubmed_id" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getPubmedId() + "\n\n");
                    stringBuffer.append(">  <" + "issn_no" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getIssnNo() + "\n\n");
                    stringBuffer.append(">  <" + "title" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getTitle() + "\n\n");
                    stringBuffer.append(">  <" + "company_name" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getCompanyNames() + "\n\n");
                    stringBuffer.append(">  <" + "company_address" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getCompanyAddresses() + "\n\n");
                    stringBuffer.append(">  <" + "authors" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getAuthors() + "\n\n");
                    stringBuffer.append(">  <" + "ref_type" + ">" + "\n");
                    stringBuffer.append(referenceTabDTO.getRefType() + "\n\n");
                    System.out.println("test the of the reference tab data" + stringBuffer.toString());

                }
                for (AssayTabDTO assayTabDTO : assayTabDTOList) {
                    stringBuffer.append(">  <" + "wild_type" + ">" + "\n");
                    stringBuffer.append(assayTabDTO.getWildType() + "\n\n");
                    stringBuffer.append(">  <" + "mutant" + ">" + "\n");
                    stringBuffer.append(assayTabDTO.getMutant() + "\n\n");
                    System.out.println("test the of the Assay tab data" + stringBuffer.toString());

                }

                for (ActivityTabDTO activityTabDTO : activityTabDTOList) {
                    stringBuffer.append(">  <" + "common_name" + ">" + "\n");
                    stringBuffer.append(activityTabDTO.getCommonName() + "\n\n");
                    stringBuffer.append(">  <" + "source" + ">" + "\n");
                    stringBuffer.append(activityTabDTO.getSource() + "\n\n");
                    stringBuffer.append(">  <" + "std_activity_type" + ">" + "\n");
                    stringBuffer.append(activityTabDTO.getStdActivityType() + "\n\n");
                    stringBuffer.append(">  <" + "standard_uom" + ">" + "\n");
                    stringBuffer.append(activityTabDTO.getStandardUom() + "\n\n");
                    stringBuffer.append(">  <" + "std_act_prefix" + ">" + "\n");
                    stringBuffer.append(activityTabDTO.getStdActPrefix() + "\n\n");
                    System.out.println("test the of the Activity tab data" + stringBuffer.toString());

                }
                stringBuffer.append("$$$$\n");

            }
            writer.write(stringBuffer.toString());
            mail.sendMail(emailList, emailListcc, "test mail from gostardbNext For Download notification ", "gostarNext is an online application , data export notification for completion will implements soon... Just an emial test");
            System.out.println("Sucessfully Sent mail to All Users");

        } catch (MolFormatException ex) {
            System.out.println("Exception occure while writing the conetnet into the sdf file" + ex.getMessage());

        } catch (Exception e) {
            System.out.println("Exception occure while writing the conetnet into the sdf file" + e.getMessage());
        }
    }

    /****
     *
     * @param writer
     * @param structureDetailsTabDTOList
     * @throws MolFormatException
     * @throws IOException
     */

    public static void convertToPng(PrintWriter writer) throws MolFormatException, IOException {

        MolExport molExport = new MolExport();
        String smile = "COC(=O)C1=CC(=C(Cl)C=C1S(=O)(=O)C1=CC=CC=C1)S(N)(=O)=O";
        chemaxon.util.MolHandler mh1 = new chemaxon.util.MolHandler(smile);
        mh1.aromatize();
        chemaxon.struc.Molecule mol = mh1.getMolecule();
        mol.clean(2, null);
        mol.dearomatize();
        //mol.implicitizeHydrogens(0);
        byte[] s1 = mol.toBinFormat("png:w250,h250,b32");

        writer.write(Base64.encodeToString(s1));
        System.out.println("ohho" + s1);


    }

    /***
     * <p>
     *     method is used for to generate the image of smiles
     * </p>
     * @param printWriter
     * @param structureDetailsTabDTOList
     */
    public static void generateImagesForStrcuture(PrintWriter printWriter, List<StructureDetailsTabDTO> structureDetailsTabDTOList) throws MolFormatException, IOException {


        for (StructureDetailsTabDTO structureTabDataReturn : structureDetailsTabDTOList) {

            /*  if (!structureTabDataReturn.getSubSmiles.isEmpty() ){*/
            chemaxon.util.MolHandler mh1 = new chemaxon.util.MolHandler(structureTabDataReturn.getSubSmiles());
            System.out.println("file mol check" + mh1);
            mh1.aromatize();
            chemaxon.struc.Molecule mol = mh1.getMolecule();
            mol.clean(2, null);
            mol.dearomatize();
            byte[] s1 = mol.toBinFormat("png:w250,h250,b32");

            printWriter.write(Base64.encodeToString(s1));
            System.out.println("generate the image check" + s1);
            /* }*/
        }

    }

    /****
     * <p>
     *     method is for the convert a csv file into the the tsv fomat
     * </p>
     * @param printWriter
     * @param referenceTabDTOList
     */
    public static void writeDataToFlatFileUsingStringArrayForReference(PrintWriter printWriter, String directory) {

        try {

            String home = System.getProperty("user.home");
            System.out.println("file path test" + home);

            String dirPath = home + "\\Downloads\\";

            File directoryFile = new File(dirPath);

            File[] fList = directoryFile.listFiles();

            String[] row = null;
            for (File file : fList) {
                if (file.isFile()) {
                    if (file.getName().startsWith("export") && file.getName().endsWith(".csv")) {
                        System.out.println("all file names" + file.getName());

                        CSVReader csvReader = new CSVReader(new FileReader(dirPath + file.getName()));

                        while ((row = csvReader.readNext()) != null) {

                            for (String cell : row) {

                                printWriter.write(cell.replaceAll("Activity Details", "").replaceAll("Assay Details", "").replaceAll("Structure Details", "").replaceAll("Reference Details", "") + "\t");
                            }
                            printWriter.println();
                            System.out.println();
                        }
                        csvReader.close();
                    }
                }

            }

        } catch (FileNotFoundException exception) {
            System.out.println("Exception occured in FlatFile Service File Not Found " + exception.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occured in FlatFile Service " + e.getMessage());
        }

    }

    /****
     *
     * @param mail
     * @return
     */
    public String[] getMailArray(String mail) {

        System.out.println("getMailArray is called...");
        String[] emailList = null;


        ResourceBundle rb = ResourceBundle.getBundle("GVK_MAILS");

        String toEmail = rb.getString(mail);


        if (toEmail.equals("")) {
            emailList = new String[0];
        } else {
            if (toEmail.indexOf(",") > 0) {

                emailList = toEmail.split(",");
            } else
                emailList = new String[]{toEmail};
        }
        for (String s : emailList) {
            System.out.println("in getMailArray s mails are");
        }
        return emailList;
    }

}


package com.excelra.mvc.util;

import com.excelra.mvc.model.tabularview.ActivityTabDTO;
import com.excelra.mvc.model.tabularview.AssayTabDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/****
 * <p>
 *
 *   the util class named as ExcelGenerator
 *    for write th data to excel file with the headers and values
 *   of Activity,Assay,Reference,Strcuture
 * </p>
 * @author priyanka.veidhey
 */

public class ExcelGenerator {


    public static ByteArrayInputStream ActivityTabDataToExcel(List<ActivityTabDTO> activityTabDTOList , List<AssayTabDTO> assayTabDTOList) throws IOException {
        // String[] COLUMNs = {"Id", "Name", "Address", "Age"};

       String[] EXCEL_ACTIVITY_COLUMNS = {"ACT_ID", "ACTIVITY_TYPE", "STD_ACTIVITY_TYPE", "ACTIVITY_UOM", "STANDARD_UOM", "ACTIVITY_PREFIX", "STD_ACT_PREFIX", "ACTIVITY_VALUE", "SD", "ACTIVITY_REMARKS", "MICRO_MOLARVALUE", "ASSAY_TYPE", "ENZYME_CELL_ASSAY", "COMMON_NAME", "ACTIVITY_MECHANISM", "SOURCE", "CELLS_CELLLINE_ORGAN", "MEASURED", "ROA", "ASSAY_METHOD_NAME", "DOSE", "DOSE_UOM", "DOSE_PREFIX", "ACTIVITY", "PARAMETER", "REFERENCE"};

       String[] EXCEL_ASSAY_HEADER = {"ASSAY_ID", "PROTEIN", "STANDARD_NAME", "MUTANT", "WILD_TYPE", "NEW_RESIDUE", "OLD_RESIDUE", "OPERATION", "ASSAY_POSITION", "TARGET_PROFILE", "P_S_M", "BUFFER", "BUFFER_CONC", "BUFFER_PREFIX", "BUFFER_UOM", "SUBSTRATE", "SUBSTRATE_CONC", "SUBSTRATE_PREFIX", "SUBSTRATE_UOM", "RADIO_LIGAND", "RADIO_LIGAND_PREFIX", "RADIO_LIGAND_UOM", "RADIO_LIGAND_CONC", "CO_ADMINISTERED", "CO_ADMINISTERED_PREFIX", "CO_ADMINISTERED_UOM", "CO_ADMINISTERED_VALUE", "IN_PRESENCE_OF", "IN_PRESENCE_OF_CONC", "IN_PRESENCE_OF_PREFIX", "IN_PRESENCE_OF_UOM", "INCUBATION_TIME", "INCUBATION_PREFIX", "INCUBATION_UOM", "TEMPERATURE", "TEMPERATURE_PREFIX", "TEMPERATURE_UOM", "PH_PREFIX", "PH"};

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {

            ZipOutputStream zipOut = new ZipOutputStream(out);
            ZipEntry   e = new ZipEntry("check.xlsx");

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Activity");

            Font headerFont = workbook.createFont();

            headerFont.setBold(true);

            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();

            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < EXCEL_ACTIVITY_COLUMNS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(EXCEL_ACTIVITY_COLUMNS[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Headers
            CellStyle ageCellStyle = workbook.createCellStyle();

            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            if (Objects.nonNull(activityTabDTOList)) {
                for (ActivityTabDTO activityTabDTO : activityTabDTOList) {

                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(activityTabDTO.getActId());
                    row.createCell(1).setCellValue(activityTabDTO.getActivityType());
                    row.createCell(2).setCellValue(activityTabDTO.getStdActivityType());
                    row.createCell(3).setCellValue(activityTabDTO.getActivityUom());
                    row.createCell(4).setCellValue(activityTabDTO.getStandardUom());
                    row.createCell(5).setCellValue(activityTabDTO.getActivityPrefix());
                    row.createCell(6).setCellValue(activityTabDTO.getStdActPrefix());
                    row.createCell(7).setCellValue(activityTabDTO.getActivityValue());
                    row.createCell(8).setCellValue(activityTabDTO.getSd().longValue());
                    row.createCell(9).setCellValue(activityTabDTO.getActivityRemarks());
                    row.createCell(10).setCellValue(activityTabDTO.getMicroMolarvalue().longValue());
                    row.createCell(11).setCellValue(activityTabDTO.getAssayType());
                    row.createCell(12).setCellValue(activityTabDTO.getEnzymeCellAssay());
                    row.createCell(13).setCellValue(activityTabDTO.getCommonName());
                    row.createCell(14).setCellValue(activityTabDTO.getActivityMechanism());
                    row.createCell(15).setCellValue(activityTabDTO.getSource());
                    row.createCell(15).setCellValue(activityTabDTO.getSource());
                    row.createCell(16).setCellValue(activityTabDTO.getCellsCelllineOrgan());
                    row.createCell(17).setCellValue(activityTabDTO.getMeasured());
                    row.createCell(18).setCellValue(activityTabDTO.getRoa());
                    row.createCell(19).setCellValue(activityTabDTO.getAssayMethodName());
                    row.createCell(20).setCellValue(activityTabDTO.getDose());
                    row.createCell(21).setCellValue(activityTabDTO.getDoseUom());
                    row.createCell(22).setCellValue(activityTabDTO.getDosePrefix());
                    row.createCell(23).setCellValue(activityTabDTO.getActivity());
                    row.createCell(24).setCellValue(activityTabDTO.getParameter());
                    row.createCell(25).setCellValue(activityTabDTO.getReference());

                    Cell ageCell = row.createCell(25);
                    ageCell.setCellValue(activityTabDTO.getReference());
                    System.out.println("cell data is  :::: " + ageCell);
                    ageCell.setCellStyle(ageCellStyle);
                }
            }
           /* if (Objects.nonNull(assayTabDTOList)) {
                for (AssayTabDTO assayTabDTO : assayTabDTOList) {
                    System.out.println("inside the AssayTAb data check" + assayTabDTO);
                    Row row1 = sheet.createRow(rowIdx++);
                    row1.createCell(0).setCellValue(assayTabDTO.getAssayId());
                    row1.createCell(1).setCellValue(assayTabDTO.getProtein());
                    Cell ageCell = row1.createCell(1);
                    ageCell.setCellValue(assayTabDTO.getProtein());
                    System.out.println("cell data is for the Assay tab  :::: " + ageCell);
                    ageCell.setCellStyle(ageCellStyle);

                }
            }*/
            workbook.write(out);

            //converting the output data to byte
            System.out.println("workbook data is :::: " + workbook);

            return new ByteArrayInputStream(Base64.encodeBase64(out.toByteArray()));
        }
    }

}

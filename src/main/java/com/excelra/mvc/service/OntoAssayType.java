package com.excelra.mvc.service;

import com.excelra.mvc.model.OntoAssayTypeDTO;
import com.excelra.mvc.model.tabularview.AssayTabDTO;

import java.util.List;

/**
 * <p>
 *  Onto assay type service
 * <p>
 *
 * @author venkateswarlu.s
 */
public interface OntoAssayType {
    List<OntoAssayTypeDTO> findByEnddataPointLeaf(boolean enddataPointLeaf);

    List<OntoAssayTypeDTO> fetchAll();

    List<AssayTabDTO> findAssayTabByAssayIdsToExport(List<Long> assayIdList);

    List<AssayTabDTO> findAssayTabByAssayIdsToExportFromTemp();
}

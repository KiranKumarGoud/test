package com.excelra.mvc.service.tabularview;

import com.excelra.mvc.model.tabularview.*;
import com.excelra.mvc.model.userjdbc.UserJdbc;

import java.util.List;

/**
 * Skeleton Structure Tabularview Service Interface
 *
 * @author Venkat Salagrama
 */
public interface ISkeletonTab {

    List<SkeletonTabDTO> findSkeletonTabFilterFieldByGvkIds(TabularviewFilterRequest tabularviewFilterRequest, UserJdbc userJdbcObject, String currentSessionUserToken);

    SkeletonTabPageData findSkeletonTabData(TabularviewRequest tabularviewRequest, UserJdbc userJdbc, String currentSessionUserToken);

    List<SkeletonTabDTO> findSkeletonTabDataForExport(TabularviewExportRequest tabularviewExportRequest, UserJdbc userJdbc, String currentSessionUserToken);

}

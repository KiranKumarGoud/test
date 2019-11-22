package com.excelra.mvc.model.tabularview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TabularviewExportRequest implements Serializable {

    private Map<String, List<Long>> selectedTabIds;

    private Map<String, List<Long>> unSelectedTabIds;

    public Map<String, List<Long>> getSelectedTabIds() {
        return selectedTabIds;
    }

    public void setSelectedTabIds(Map<String, List<Long>> selectedTabIds) {
        this.selectedTabIds = selectedTabIds;
    }

    public Map<String, List<Long>> getUnSelectedTabIds() {
        return unSelectedTabIds;
    }

    public void setUnSelectedTabIds(Map<String, List<Long>> unSelectedTabIds) {
        this.unSelectedTabIds = unSelectedTabIds;
    }
}

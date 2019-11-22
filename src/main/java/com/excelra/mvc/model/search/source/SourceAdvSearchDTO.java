package com.excelra.mvc.model.search.source;

import com.excelra.mvc.model.source.ListSearchDTO;
import com.excelra.mvc.model.source.StrainSearchDTO;

import java.io.Serializable;
import java.util.List;

public class SourceAdvSearchDTO implements Serializable {

    private ListSearchDTO listSearchDTO;

    private List<StrainSearchDTO> strainSearchDTOList;

    public ListSearchDTO getListSearchDTO() {
        return listSearchDTO;
    }

    public void setListSearchDTO(ListSearchDTO listSearchDTO) {
        this.listSearchDTO = listSearchDTO;
    }

    public List<StrainSearchDTO> getStrainSearchDTOList() {
        return strainSearchDTOList;
    }

    public void setStrainSearchDTOList(List<StrainSearchDTO> strainSearchDTOList) {
        this.strainSearchDTOList = strainSearchDTOList;
    }
}

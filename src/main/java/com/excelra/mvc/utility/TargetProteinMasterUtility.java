package com.excelra.mvc.utility;

import com.excelra.mvc.entity.TargetProteinMaster;
import com.excelra.mvc.model.TargetProteinMasterDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  Target Protein Master utility.
 * <p>
 *
 * @author venkateswarlu.s
 */
@Component
public class TargetProteinMasterUtility {

    public static final String LOCUS_OPTION = "enterez";

    public static final String UNIPROT_OPTION = "upprot";

    public static final String PDB_OPTION = "pdb";

    public static final String OFFICALNAME_OPTION = "office";

    public static final String COMMON_OPTION = "common";

    /**
     *
     * @param targetProteinMasterList
     * @return
     */
    public List<TargetProteinMasterDTO> convertEntityToDto(List<TargetProteinMaster> targetProteinMasterList) {

        List<TargetProteinMasterDTO> targetProteinMasterDtoList = new ArrayList<>();

        if(Objects.nonNull(targetProteinMasterList)) {
            for (TargetProteinMaster targetProteinMaster : targetProteinMasterList) {

                TargetProteinMasterDTO targetProteinMasterDto = new TargetProteinMasterDTO();

                targetProteinMasterDto.setStdNameId(targetProteinMaster.getStdNameId());
                targetProteinMasterDto.setCommonName(targetProteinMaster.getCommonName());
                targetProteinMasterDto.setFlag(targetProteinMaster.getFlag());
                targetProteinMasterDto.setFlagNew(targetProteinMaster.getFlagNew());
                targetProteinMasterDto.setLocusId(targetProteinMaster.getLocusId());
                targetProteinMasterDto.setMultipleLoci(targetProteinMaster.getMultipleLoci());
                targetProteinMasterDto.setOfficialName(targetProteinMaster.getOfficialName());
                targetProteinMasterDto.setPdbId(targetProteinMaster.getPdbId());
                targetProteinMasterDto.setSciSourceName(targetProteinMaster.getSciSourceName());
                targetProteinMasterDto.setSource(targetProteinMaster.getSource());
                targetProteinMasterDto.setStandardName(targetProteinMaster.getStandardName());
                targetProteinMasterDto.setTargetId(targetProteinMaster.getTargetId());
                targetProteinMasterDto.setUniprotId(targetProteinMaster.getUniprotId());

                targetProteinMasterDto.setLabel(targetProteinMaster.getCommonName());
                targetProteinMasterDto.setValue(targetProteinMaster.getCommonName());
                targetProteinMasterDto.setOperator("|");

                targetProteinMasterDtoList.add(targetProteinMasterDto);
            }
        }

        return targetProteinMasterDtoList;
    }


    /**
     *
     * @param targetProteinMasterList
     * @return
     */
    public List<TargetProteinMasterDTO> convertEntityToDtoForOptionAndValue(List<TargetProteinMaster> targetProteinMasterList, String option) {

        List<TargetProteinMasterDTO> targetProteinMasterDtoList = new ArrayList<>();

        if(Objects.nonNull(targetProteinMasterList)) {
            for (TargetProteinMaster targetProteinMaster : targetProteinMasterList) {

                TargetProteinMasterDTO targetProteinMasterDto = new TargetProteinMasterDTO();

                targetProteinMasterDto.setStdNameId(targetProteinMaster.getStdNameId());
                targetProteinMasterDto.setCommonName(targetProteinMaster.getCommonName());
                targetProteinMasterDto.setFlag(targetProteinMaster.getFlag());
                targetProteinMasterDto.setFlagNew(targetProteinMaster.getFlagNew());
                targetProteinMasterDto.setLocusId(targetProteinMaster.getLocusId());
                targetProteinMasterDto.setMultipleLoci(targetProteinMaster.getMultipleLoci());
                targetProteinMasterDto.setOfficialName(targetProteinMaster.getOfficialName());
                targetProteinMasterDto.setPdbId(targetProteinMaster.getPdbId());
                targetProteinMasterDto.setSciSourceName(targetProteinMaster.getSciSourceName());
                targetProteinMasterDto.setSource(targetProteinMaster.getSource());
                targetProteinMasterDto.setStandardName(targetProteinMaster.getStandardName());
                targetProteinMasterDto.setTargetId(targetProteinMaster.getTargetId());
                targetProteinMasterDto.setUniprotId(targetProteinMaster.getUniprotId());

                if(option.equalsIgnoreCase(LOCUS_OPTION)) {

                    targetProteinMasterDto.setLabel((Objects.nonNull(targetProteinMaster.getLocusId())) ? targetProteinMaster.getLocusId().toString() : null);
                    targetProteinMasterDto.setValue((Objects.nonNull(targetProteinMaster.getLocusId())) ? targetProteinMaster.getLocusId().toString() : null);

                } else if(option.equalsIgnoreCase(UNIPROT_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMaster.getUniprotId());
                    targetProteinMasterDto.setValue(targetProteinMaster.getUniprotId());

                } else if(option.equalsIgnoreCase(PDB_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMaster.getPdbId());
                    targetProteinMasterDto.setValue(targetProteinMaster.getPdbId());

                } else if(option.equalsIgnoreCase(OFFICALNAME_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMaster.getOfficialName());
                    targetProteinMasterDto.setValue(targetProteinMaster.getOfficialName());

                } else if(option.equalsIgnoreCase(COMMON_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMaster.getCommonName());
                    targetProteinMasterDto.setValue(targetProteinMaster.getCommonName());

                }

                targetProteinMasterDto.setOperator("|");

                targetProteinMasterDtoList.add(targetProteinMasterDto);
            }
        }

        return targetProteinMasterDtoList;
    }

    /**
     *
     * @param targetProteinMasterDtoList
     * @param option
     * @return
     */
    public List<TargetProteinMasterDTO> updateDtoWithLabelAndValue(List<TargetProteinMasterDTO> targetProteinMasterDtoList, String option) {

        List<TargetProteinMasterDTO> targetProteinMasterDtoListUpdated = new ArrayList<>();

        if(Objects.nonNull(targetProteinMasterDtoList)) {
            for (TargetProteinMasterDTO targetProteinMasterDto : targetProteinMasterDtoList) {
                if(option.equalsIgnoreCase(LOCUS_OPTION)) {

                    targetProteinMasterDto.setLabel((Objects.nonNull(targetProteinMasterDto.getLocusId())) ? targetProteinMasterDto.getLocusId().toString() : null);
                    targetProteinMasterDto.setValue((Objects.nonNull(targetProteinMasterDto.getLocusId())) ? targetProteinMasterDto.getLocusId().toString() : null);

                } else if(option.equalsIgnoreCase(UNIPROT_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMasterDto.getUniprotId());
                    targetProteinMasterDto.setValue(targetProteinMasterDto.getUniprotId());

                } else if(option.equalsIgnoreCase(PDB_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMasterDto.getPdbId());
                    targetProteinMasterDto.setValue(targetProteinMasterDto.getPdbId());

                } else if(option.equalsIgnoreCase(OFFICALNAME_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMasterDto.getOfficialName());
                    targetProteinMasterDto.setValue(targetProteinMasterDto.getOfficialName());

                } else if(option.equalsIgnoreCase(COMMON_OPTION)) {

                    targetProteinMasterDto.setLabel(targetProteinMasterDto.getCommonName());
                    targetProteinMasterDto.setValue(targetProteinMasterDto.getCommonName());

                }

                targetProteinMasterDtoListUpdated.add(targetProteinMasterDto);
            }
        }

        return targetProteinMasterDtoListUpdated;
    }
}

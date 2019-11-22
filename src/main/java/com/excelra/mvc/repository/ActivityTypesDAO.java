package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ActivityLabelValueDTO;
import com.excelra.mvc.model.ListActivityTypesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * This is AllMappingIds DAO layer for Transactional Queries process.
 *
 * @author venkateswarlu.s
 */
@Repository
public class ActivityTypesDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTypesDAO.class);

    @Value("${activity.type.list.query}")
    private String activityTypeListQuery;

    @Value("${activity.type.advancedsearch.list.query}")
    private String activityTypeAdvancedSearchListQuery;

    @Value("${activity.uom.advancedsearch.list.query}")
    private String activityUomAdvancedSearchListQuery;

    @Value("${activity.prefix.advancedsearch.list.query}")
    private String activityPrefixAdvancedSearchListQuery;

    @Value("${activity.prefix.advancedsearch.operators.list}")
    private String activityPrefixAdvancedSearchOperators;
    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    public List<ListActivityTypesDTO> fetchActivityTypeContaining(String activityType, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchActivityTypeContaining - Query - {} ", activityTypeListQuery);

            List<ListActivityTypesDTO> listActivityTypesDTOs = userJdbc.getJdbcTemplate().query(activityTypeListQuery, new String[]{activityType.toLowerCase(), activityType.toLowerCase(), activityType.toLowerCase()}, new BeanPropertyRowMapper<>(ListActivityTypesDTO.class));

            List<ListActivityTypesDTO> listActivityTypesDuplicatesRemoveList = listActivityTypesDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ListActivityTypesDTO::getActivityType))),
                            ArrayList::new));

            return listActivityTypesDuplicatesRemoveList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchActivityTypeContaining service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchActivityTypeContaining service method, error is {} ", e);
        }

    }

    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    public List<ActivityLabelValueDTO> fetchActivityTypeLabelValue(String activityType, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchActivityTypeLabelValue - Query - {} ", activityTypeListQuery);

            return activityLabelValueDtoWithoutDuplicates(activityTypeAdvancedSearchListQuery, activityType, userJdbc);
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchActivityTypeLabelValue service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchActivityTypeLabelValue service method, error is {} ", e);
        }

    }

    /**
     *
     * @param activityType
     * @param userJdbc
     * @return
     */
    public List<ActivityLabelValueDTO> fetchActivityUomLabelValue(String activityType, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchActivityUomLabelValue - Query - {} ", activityUomAdvancedSearchListQuery);

            return activityLabelValueDtoWithoutDuplicates(activityUomAdvancedSearchListQuery, activityType, userJdbc);
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchActivityUomLabelValue service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchActivityUomLabelValue service method, error is {} ", e);
        }

    }

    /**
     * 
     * @param activityType
     * @param userJdbc
     * @return
     */
    public List<ActivityLabelValueDTO> fetchActivityPrefixLabelValue(String activityType, UserJdbc userJdbc) {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchActivityPrefixLabelValue - Query - {} ", activityPrefixAdvancedSearchListQuery);

            return activityLabelValueDtoWithoutDuplicates(activityPrefixAdvancedSearchListQuery, activityType, userJdbc);
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchActivityPrefixLabelValue service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchActivityPrefixLabelValue service method, error is {} ", e);
        }

    }

    /**
     *
     * @return
     */
    public List<ActivityLabelValueDTO> fetchActivityPrefixValues() {

        try {
            LOGGER.info(" :: fetchActivityPrefixValues - Query  ");

            List<ActivityLabelValueDTO> activityLabelValueDTOList = new ArrayList<>();

            String[] operators = activityPrefixAdvancedSearchOperators.split(",");
            for(String operator : operators) {
                ActivityLabelValueDTO activityLabelValueDTO = new ActivityLabelValueDTO();
                activityLabelValueDTO.setLabel(operator.trim());
                activityLabelValueDTO.setValue(operator.trim());

                activityLabelValueDTOList.add(activityLabelValueDTO);
            }

            return activityLabelValueDTOList;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchActivityPrefixValues service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchActivityPrefixValues service method, error is {} ", e);
        }

    }


    /**
     *
     * @param query
     * @param activityType
     * @param userJdbc
     * @return
     */
    private List<ActivityLabelValueDTO> activityLabelValueDtoWithoutDuplicates(String query, String activityType, UserJdbc userJdbc) {
        List<ActivityLabelValueDTO> activityLabelValueDTOs = userJdbc.getJdbcTemplate().query(query, new String[]{ activityType }, new BeanPropertyRowMapper<>(ActivityLabelValueDTO.class));

        List<ActivityLabelValueDTO> activityLabelValueDuplicatesRemoveList = activityLabelValueDTOs.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ActivityLabelValueDTO::getLabel))),
                        ArrayList::new));

        return activityLabelValueDuplicatesRemoveList;
    }

}

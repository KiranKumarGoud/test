package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ListActivityMechanismDTO;
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
 *
 * @author venkateswarlu.s
 */
@Repository
public class ActivityMechanismDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityMechanismDAO.class);

    @Value("${activity.mechanism.list.query}")
    private String activityMechanismListQuery;

    /**
     *
     * @param userJdbc
     * @return
     */
    public List<ListActivityMechanismDTO> fetchActivityMechanism(UserJdbc userJdbc) {
        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchActivityMechanism - Query - {} ", activityMechanismListQuery);

            List<ListActivityMechanismDTO> listActivityMechanismDTOs = userJdbc.getJdbcTemplate().query(activityMechanismListQuery, new BeanPropertyRowMapper<>(ListActivityMechanismDTO.class));

            List<ListActivityMechanismDTO> removedDuplicatesFromActivityMechanismList = listActivityMechanismDTOs.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ListActivityMechanismDTO::getActivityMechanism))),
                            ArrayList::new));

            return removedDuplicatesFromActivityMechanismList;
        } catch (Exception e) {
            LOGGER.error(" Error while processing fetchActivityMechanism service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchActivityMechanism service method, error is {} ", e);
        }
    }
}

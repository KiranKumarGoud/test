package com.excelra.mvc.repository;

import com.excelra.mvc.exception.GoStarSqlException;
import com.excelra.mvc.model.ListCompoundCategoriesDTO;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CompoundCategoriesDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompoundCategoriesDAO.class);

    @Value("${compound.category.list.query}")
    private String compoundCategoryListQuery;

    /**
     *
     * @param userJdbc
     * @return
     * @throws GoStarSqlException
     */
    @Transactional
    public List<ListCompoundCategoriesDTO> fetchCompoundCategoriesList(UserJdbc userJdbc) throws GoStarSqlException {

        try {
            // Return Empty data if UserJdbc Object is null.
            if (Objects.isNull(userJdbc)) return new ArrayList<>();

            LOGGER.info(" :: fetchCompoundCategoriesList - Query - {} ", compoundCategoryListQuery);

            List<ListCompoundCategoriesDTO> listCompoundCategoriesDTOS = userJdbc.getJdbcTemplate().query(compoundCategoryListQuery, new BeanPropertyRowMapper<>(ListCompoundCategoriesDTO.class));

            return listCompoundCategoriesDTOS;
        } catch(Exception e) {
            LOGGER.error(" Error while processing fetchCompoundCategoriesList service method, error is {} ", e.getMessage());
            throw new GoStarSqlException(" Error while processing fetchCompoundCategoriesList service method, error is {} ", e);
        }
    }
}

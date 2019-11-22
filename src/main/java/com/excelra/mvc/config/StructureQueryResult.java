package com.excelra.mvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This to execute Structure special queries and result to return for Count and Search
 *
 * @author venkateswarlu.s
 */

@Component
public class StructureQueryResult {

    @Value("${spring.datasource.url}")
    public String JDBC_URL;

    @Value("${spring.datasource.username}")
    public String USERNAME;

    @Value("${spring.datasource.password}")
    public String PASSWORD;

    @Value("${spring.datasource.driver-class-name}")
    public String DRIVER;

    static Connection con = null;

    public List<BigDecimal> getStrIdsForStructureSubQuery(String query) {

        List<BigDecimal> strResults = new ArrayList<>();

        try {
            String[] jdbcUrl = JDBC_URL.split("\\?");

            Class.forName(DRIVER);
            con = DriverManager.getConnection(jdbcUrl[0],USERNAME, PASSWORD);

            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt2.executeQuery(query);

            while(rs.next()) {
                strResults.add(rs.getBigDecimal(1));
            }
        } catch (Exception e) {
            // e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }

        return strResults;
    }
}

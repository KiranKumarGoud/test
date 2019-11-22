package com.excelra.mvc.config;

import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Component
public class CustomJdbcConnection {

    @Value("${spring.datasource.url}")
    public String JDBC_URL;

    @Value("${spring.datasource.username}")
    public String USERNAME;

    @Value("${spring.datasource.password}")
    public String PASSWORD;

    @Value("${spring.datasource.driver-class-name}")
    public String DRIVER;

    public JdbcTemplate getJdbcTemplateConnection() {
        // DataSource source = getDataSource();

        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(DRIVER);
        source.setUrl(JDBC_URL);
        source.setUsername(USERNAME);
        source.setPassword(PASSWORD);

        JdbcTemplate template = new JdbcTemplate(source);
        return template;
    }

    /**
     * Returns a DataSource object for connection to the database.
     *
     * @return a DataSource.
     */
    private DataSource getDataSource() {
        // Creates a new instance of DriverManagerDataSource and sets
        // the required parameters such as the Jdbc Driver class,
        // Jdbc URL, database user name and password.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public UserJdbc getUserJdbcObject(HttpSession httpSession, String userSessionId) {

        System.out.println("----> =====> "+userSessionId);

        UserJdbc userJdbc = (UserJdbc) httpSession.getAttribute(userSessionId);

        return userJdbc;
    }
}

package com.excelra.mvc.config;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excelra.mvc.model.userjdbc.UserJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String currentSession = request.getParameter("currentSession");

        if(authentication != null) {
            LOGGER.info(" :: LogoutSuccessHandler :: Logout is doing for the User {} and his session {} ", authentication.getName(), currentSession);

            UserJdbc userJdbc = (UserJdbc) request.getSession().getAttribute(currentSession);
            if(Objects.nonNull(userJdbc)) {
                try {
                    userJdbc.getJdbcTemplate().getDataSource().getConnection().close();
                    System.out.println("JdbcConnection is closed successfully for the session "+currentSession);
                } catch(Exception e) {
                    System.out.println("JdbcConnection is not closed successfully for the session "+currentSession+ " Error is "+e.getMessage());
                }
            }
        }

        request.getSession().invalidate();

        //perform other required operation
        String URL = request.getContextPath() + "/login";
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(URL);
    }
}
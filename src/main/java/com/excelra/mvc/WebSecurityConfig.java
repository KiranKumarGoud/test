package com.excelra.mvc;

import com.excelra.mvc.config.AjaxAwareAuthenticationEntryPoint;
import com.excelra.mvc.config.LogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.CacheControl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Value("${user.max.sessions.count}")
    private String userMaxSessions;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()

                .antMatchers("/welcome/**").access("hasRole('ROLE_EVALUATEUSER') or hasRole('ROLE_PARTIALUSER') or hasRole('ROLE_FULLUSER')")
                .antMatchers("/visualization/**").access("hasRole('ROLE_EVALUATEUSER') or hasRole('ROLE_PARTIALUSER') or hasRole('ROLE_FULLUSER')")
                .antMatchers("/tabularview/**").access("hasRole('ROLE_PARTIALUSER') or hasRole('ROLE_FULLUSER')")
                .antMatchers("/security/tabularview/export/**").access("hasRole('ROLE_FULLUSER')")

                .antMatchers("/resources/**", "/assets/**", "/registration", "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers()
                .defaultsDisabled()
                .frameOptions()
                .sameOrigin().disable()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                .defaultSuccessUrl("/welcome", true)
                /*.and()
            .logout()
                .permitAll()*/;

        http
          .exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"));

        http.logout().
                logoutUrl("/appLogout").
                logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        http
                .sessionManagement()
                .maximumSessions(Integer.parseInt(userMaxSessions))
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll();
        /*http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);*/
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(false);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for CSS and JS
       /* registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/SmileImages/", "D:/SmileImages/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());*/

        // Register resource handler for images
        registry.addResourceHandler("/SmileImages/**").addResourceLocations("/webapp/SmileImages/");
               /* .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic()*/
    }

}
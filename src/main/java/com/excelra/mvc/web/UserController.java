package com.excelra.mvc.web;

import com.excelra.mvc.config.CustomJdbcConnection;
import com.excelra.mvc.config.UserTokenGeneration;
import com.excelra.mvc.entity.User;
import com.excelra.mvc.model.tabularview.TempTableData;
import com.excelra.mvc.model.tabularview.TempTableDataForSession;
import com.excelra.mvc.model.userjdbc.UserJdbc;
import com.excelra.mvc.service.SecurityService;
import com.excelra.mvc.service.UserService;
import com.excelra.mvc.service.tabularview.IAllTab;
import com.excelra.mvc.validator.UserValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CustomJdbcConnection customJdbcConnection;

    @Autowired
    private UserTokenGeneration userTokenGeneration;

    @Autowired
    private IAllTab iAllTabService;

    @Value("${microservices.execution.status}")
    private String microServicesExecutionStatus;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new User());

        return "pages/login/login";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    /**
     * Registeration entry point
     *
     * @param userForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    /**
     * Login check entry point
     *
     * @param loginForm
     * @param bindingResult
     * @param httpSession
     * @return
     */
    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") User loginForm, BindingResult bindingResult, HttpSession httpSession) {

        securityService.autoLogin(loginForm.getUsername(), loginForm.getPassword());

        httpSession.setAttribute("username", loginForm.getUsername());
        return "redirect:/welcome";
    }

    /**
     * Search / welcome page entry point
     *
     * @param model
     * @param httpSession
     * @param response
     * @return
     */
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model, HttpSession httpSession, HttpServletResponse response) {

        /**
         * To Get the logged user credentials to generate token for Microservices internally
         */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Object credentials = auth.getCredentials();

        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        String userSessionId = httpSession.getId();
        JdbcTemplate jdbcTemplate = customJdbcConnection.getJdbcTemplateConnection();

        UserJdbc userJdbc = new UserJdbc();
        userJdbc.setUserSessionId(userSessionId);
        userJdbc.setJdbcTemplate(jdbcTemplate);

        if(microServicesExecutionStatus.equalsIgnoreCase("true")) {
            if(Objects.nonNull(username) && Objects.nonNull(credentials)) {
                String userToken = userTokenGeneration.getUserTokenFromService(username, credentials.toString());
                model.addAttribute("usertoken", userToken);
                httpSession.setAttribute(userSessionId+"_token", userToken);
            }
        }

        httpSession.setAttribute(userSessionId, userJdbc);

        model.addAttribute("usersession", userSessionId);
        model.addAttribute("userroles", StringUtils.join(getUserRoles(auth), ","));
        model.addAttribute("username", auth.getName());

        return "pages/search/search";
    }

    /**
     * Visualization entry point
     *
     * @param model
     * @param httpSession
     * @param userSessionId
     * @return
     */
    @GetMapping("/visualization/{userSessionId}")
    public String visualization(Model model, HttpSession httpSession, @PathVariable String userSessionId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("usersession", userSessionId);
        model.addAttribute("userroles", StringUtils.join(getUserRoles(auth), ","));
        model.addAttribute("username", auth.getName());

        return "pages/visualization/visualization";
    }

    /**
     * Tabular view entry point
     * @param model
     * @param httpSession
     * @param userSessionId
     * @return
     */
    @GetMapping("/tabularview/{userSessionId}")
    public String tabularview(Model model, HttpSession httpSession, @PathVariable String userSessionId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("usersession", userSessionId);
        model.addAttribute("userroles", StringUtils.join(getUserRoles(auth), ","));
        model.addAttribute("username", auth.getName());

        return "pages/tabularview/tabularview";
    }

    /**
     * HeatMap Analyzer entry point
     * @param model
     * @param httpSession
     * @param userSessionId
     * @return
     */
    @GetMapping("/heatmap/{userSessionId}")
    public String heatMapAnalyzer(Model model, HttpSession httpSession, @PathVariable String userSessionId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("usersession", userSessionId);
        model.addAttribute("userroles", StringUtils.join(getUserRoles(auth), ","));
        model.addAttribute("username", auth.getName());

        return "pages/analyzers/heatmapanalyzer";
    }
    
    /**
     * MolecularPairQuery Analyzer entry point
     * @param model
     * @param httpSession
     * @param userSessionId
     * @return
     */
    @GetMapping("/moleularanalyzerdata/{userSessionId}")
    public String molecularQueryAnalyzer(Model model, HttpSession httpSession, @PathVariable String userSessionId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("usersession", userSessionId);
        model.addAttribute("userroles", StringUtils.join(getUserRoles(auth), ","));
        model.addAttribute("username", auth.getName());

        return "pages/analyzers/molecularpairquery";
    }

    /**
     * for 403 access denied page
     * @param user
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }

    /**
     * Fetch the User roles
     * @param auth
     * @return
     */
    private List<String> getUserRoles(Authentication auth) {

        List<String> userRoles = new ArrayList<>();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            userRoles.add(authority.getAuthority());
        }

        return userRoles;
    }

    /**
     * Downloads entry point
     * @param model
     * @param httpSession
     * @param userSessionId
     * @return
     */
    @GetMapping("/downloads/{userSessionId}")
    public String downloads(Model model, HttpSession httpSession, @PathVariable String userSessionId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("usersession", userSessionId);
        model.addAttribute("userroles", StringUtils.join(getUserRoles(auth), ","));
        model.addAttribute("username", auth.getName());

        return "pages/export/downloads";
    }

}

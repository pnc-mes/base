package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by test on 2017/6/27.
 */
@Controller
@Scope("prototype")
public class ConstController {
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String UserLoginInfo(){
        return "login";
    }

    @RequestMapping(value = "/Main", method = RequestMethod.GET)
    public String Main(){
        return "main";
    }

    @RequestMapping(value = "/WelcomePG", method = RequestMethod.GET)
    public String WelcomePG(){
        return "welcome";
    }

    @RequestMapping(value = "/error_404", method = RequestMethod.GET)
    public String Error_404() {return "error/404";}

}

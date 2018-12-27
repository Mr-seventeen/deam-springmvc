package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

/**
 * Demo class
 *
 * @author qixiujuan
 * @date 2018/12/24
 */
@Controller
public class HelloController  {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = {"/","index"},method = {RequestMethod.GET})
    public String index(Model model){
        logger.info("======index method=======");
        model.addAttribute("msg","go go go");
        return "index.jsp";
    }




}

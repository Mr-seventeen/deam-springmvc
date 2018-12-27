package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Demo class
 *
 * @author qixiujuan
 * @date 2
 */
@Controller
public class TestEnvironmentAwareController implements EnvironmentAware {

    private final Logger logger = LoggerFactory.getLogger(TestEnvironmentAwareController.class);

    private Environment environment = null;

    @RequestMapping(value = { "/test-awarex", }, method = { RequestMethod.GET })
    public String testawarex(Model model) throws Exception {
        logger.info("==========this is TestEnvironmentAwareController--> setEnvironment=========");
        model.addAttribute("msg", "Go Go Go!");
        return "go.jsp";
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

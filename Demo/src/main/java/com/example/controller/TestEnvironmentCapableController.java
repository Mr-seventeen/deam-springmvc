package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Demo class
 *
 * @author qixiujuan
 * @date
 */
@Controller
public class TestEnvironmentCapableController implements EnvironmentCapable {
    private final Logger logger = LoggerFactory.getLogger(TestEnvironmentCapableController.class);

    @RequestMapping(value = { "/test-capable", }, method = { RequestMethod.GET })
    public String testcapable(Model model) throws Exception {
        logger.info("==========this is TestEnvironmentAwareController--> setEnvironment=========");
        // 这里设置断点
        model.addAttribute("msg", "Go Go Go!");
        return "go.jsp";
    }
    @Override
    public Environment getEnvironment() {
        return null;
    }
}

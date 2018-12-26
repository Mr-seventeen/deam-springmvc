package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;

/**
 * Demo class
 *
 * @author qixiujuan
 * @date 2016/10/31
 */
public class HelloWebDispatcherServlet extends DispatcherServlet {

    private static final Logger logger = LoggerFactory.getLogger(HelloWebDispatcherServlet.class);

    @Override
    public void initBeanWrapper(BeanWrapper bw) {
        logger.info("HelloWebDispatcherServlet --> initBeanWrapper" + bw.getWrappedClass().getCanonicalName());
    }

    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void postProcessWebApplicationContext(ConfigurableWebApplicationContext wac){

    }

}

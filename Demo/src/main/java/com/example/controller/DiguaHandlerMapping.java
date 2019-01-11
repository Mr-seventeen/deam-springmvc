package com.example.controller;

import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: qixiujuan
 * @Describe DiguaHandlerMapping
 * @Date: 2019/1/8
 * @Modified By：
 */
public class DiguaHandlerMapping implements HandlerMapping {

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {

        String url = request.getRequestURI().toString();
        String method = request.getMethod();
        if(url.startsWith("todo")) {
            if(method.equalsIgnoreCase("GET")){

            } else if(method.equalsIgnoreCase("POST")){

            } else {

            }
        } else {

        }
        return null;
    }
}

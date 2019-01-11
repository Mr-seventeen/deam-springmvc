package com.bk.impl;

import com.bk.service.IDemoService;
import com.kooup.business.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qixiujuan
 * @Describe DemoServiceImpl
 * @Date: 2019/1/2
 * @Modified By：
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Resource
    private IUserService userService;

    @Override
    public List<String> getAllDemo() {
        int result = userService.updateEEOMobile(103954,"18633491008","18010121276","1");
        System.out.println("DemoServiceImpl.getAllDemo result is " + result);
        return null;
    }
}

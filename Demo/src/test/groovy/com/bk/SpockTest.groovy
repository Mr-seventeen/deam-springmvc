package com.bk

import com.bk.impl.DemoServiceImpl
import spock.lang.Shared
import spock.lang.Specification

class SpockTest extends Specification{

    @Shared
    List<String> productList
    DemoServiceImpl demoService
    def setup() {
        demoService = new DemoServiceImpl()
    }

    def setupSpec() {
        productList = new ArrayList<>()
        productList.add("1")
    }

    def "demoTest_demo测试方法"(){
        reportInfo"demo测试方法"


    }

    def "mainTest_第一个测试方法"() {
        reportInfo"测试方法-xj.q"

        given: "提供什么东西"
//        demoService.getAllDemo() >>

        expect:
        x + y * z < f

        where:
        x || y || z || f
        1 || 1 || 4 || 0
        2 || 2 || 2 || 5
        3 || 3 || 9 || 1
        4 || 4 || 1 || 10

    }


}

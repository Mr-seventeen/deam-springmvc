package com.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author: qixiujuan
 * @date: 2019/5/8
 */
@EnableRetry
@SpringBootApplication
public class SpringbootRetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class);
    }
}

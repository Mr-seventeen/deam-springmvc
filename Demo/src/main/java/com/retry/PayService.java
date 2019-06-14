package com.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author: qixiujuan
 * @date: 2019/5/8
 */
@Service
public class PayService {

    /**
     * logger for PayService
     */
    private static final Logger logger = LoggerFactory.getLogger(PayService.class);

    private final int totalNum = 10000;

    /**
     * @param num
     * @return
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public int minGoodsnum(int num){
        logger.info("减库存开始|{}",LocalTime.now());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("illegal");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("数量不对");
        }
        logger.info("减库存执行结束" + LocalTime.now());
        return totalNum - num;
    }

   /* @Recover
    public int recover(Exception e) {
        logger.warn("减库存失败！！！" + LocalTime.now());
        return totalNum;
    }*/
}

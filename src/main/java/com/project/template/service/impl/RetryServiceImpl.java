package com.project.template.service.impl;

import com.project.template.service.IRetryService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * spring 重试框架测试
 *
 * @author suibin.wu
 */
@Service
public class RetryServiceImpl implements IRetryService {

    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public void testRetry() {
        System.out.println(System.currentTimeMillis() + ":进行了方法调用");
        throw new RuntimeException("spring重试框架测试异常。。。");
    }


    /**
     * 重试异常兜底策略
     *
     * 注意：只有在入参与发生异常匹配时才会执行该补偿方法
     * @param ex 异常
     */
    @Recover
    public void recover(Exception ex) {
        System.out.println(ex.getMessage());
    }
}

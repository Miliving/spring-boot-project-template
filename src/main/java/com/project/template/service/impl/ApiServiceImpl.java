package com.project.template.service.impl;

import com.project.template.db.mapper.TestMapper;
import com.project.template.db.model.Test;
import com.project.template.service.IApiService;
import com.project.template.utils.HttpUtilV1;
import com.project.template.utils.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * The type Api service.
 *
 * @author suibin.wu
 */
@Service
@Slf4j
public class ApiServiceImpl implements IApiService {

    @Resource
    private TestMapper testMapper;

    @Resource
    private HttpUtilV1 httpUtilV1;

    @Resource
    private TaskUtil taskUtil;


    /**
     * Test.
     *
     * @param name the name
     */
    @Override
    public Test findByName(String name) {
        Test test = testMapper.findByName(name);
        log.info("日期：{}，创建时间：{}", test.getFDate(), test.getFCreateTime());
        return test;
    }

    @Override
    @Async("poolExecutor")
    public void queryOperator(String mobile) {
        try {
            Thread.sleep(5000);
            String url = "http://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=" + mobile;
            String result = httpUtilV1.doGet(url, null);
            Future<String> future = taskUtil.asyncTask01();
            String futureResult = future.get();

            log.info(result);
            log.info(futureResult);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}

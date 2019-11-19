package com.project.template.service.impl;

import com.github.pagehelper.Page;
import com.project.template.db.mapper.TestMapper;
import com.project.template.db.model.Test;
import com.project.template.model.ResultModel;
import com.project.template.pojo.req.RequestVO;
import com.project.template.service.IApiService;
import com.project.template.utils.EmailUtil;
import com.project.template.utils.HttpUtilV1;
import com.project.template.utils.RedisUtil;
import com.project.template.utils.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private EmailUtil emailUtil;


    /**
     * Test.
     *
     * @param name the name
     */
    @Override
    public Test findByName(String name) {
        log.info("姓名：{}，开始执行业务逻辑...", name);
        Test test = testMapper.findByName(name);
        redisUtil.set(test.getFMobile(), test.getFName(), 150);
        log.info("日期：{}，创建时间：{}", test.getFDate(), test.getFCreateTime());
        log.info("Redis key = {},value = {}", test.getFMobile(), redisUtil.get(test.getFMobile()));
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


    /**
     * Gets users.
     *
     * @return the users
     */
    @Override
    public Page<Test> getUsers(String name) {
        return testMapper.getUsers(name);
    }


    /**
     * Params verify result model.
     *
     * @param params the params
     * @return the result model
     */
    @Override
    public ResultModel<Object> paramsVerify(RequestVO params) {
        ResultModel<Object> result = new ResultModel<>();
        Test test = testMapper.findByNameAndMobile(params.getName(), params.getMobile());
        if (null == test) {
            return result.doFail("数据不存在");
        } else {
            return result.setData(test);
        }
    }


    /**
     * 邮件发送功能
     */
    @Override
    public void sendEmail() {
        emailUtil.sendSimpleMail("xxx@163.com", "emailTest", "Hello spring Email");
    }
}

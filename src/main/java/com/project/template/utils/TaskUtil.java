package com.project.template.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 异步任务工具类
 *
 * @author Miliving
 */
@Component
@Slf4j
public class TaskUtil {

    @Resource
    private HttpUtilV1 httpUtilV1;

    @Async("poolExecutor")
    public Future<String> asyncTask01() {
        try {
            String url = "http://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=18177416423";
            String result = httpUtilV1.doGet(url, null);
            Thread.sleep(15000);
            return new AsyncResult<>(result);
        } catch (InterruptedException ex) {
            log.error(ex.getMessage());
            return new AsyncResult<>(null);
        }
    }
}

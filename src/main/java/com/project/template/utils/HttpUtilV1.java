package com.project.template.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Http请求的方案一：基于 spring 的 RestTemplate 包
 *
 * @author suibin.wu
 */
@Slf4j
@Component
public class HttpUtilV1 {

    /**
     * The constant FAIL.
     */
    private static final String FAIL = "FAIL";

    /**
     * 以Json参数形式发送POST请求
     *
     * @param params 参数
     * @param url    地址
     * @return 响应 string
     */
    public String doPostWithJson(Map<String, Object> params, String url) {
        // 参数转json串
        String jsonParams = JSON.toJSONString(params);

        // 请求头设置
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonParams, headers);

        // 构造发送器
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(30000);
        requestFactory.setReadTimeout(30000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // 发送请求
        log.info("请求地址：{} 参数：{}", url, jsonParams);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return afterRequestProcess(response);
    }

    /**
     * get请求封装
     *
     * @param url    地址 示例：www.abc.com?name=Json&age=18
     * @param params 参数
     * @return string
     **/
    public String doGet(String url, Map<String, Object> params) {
        log.info("发送请求：{} 参数：{}", url, params);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(30000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<String> response;
        if (null == params) {
            response = restTemplate.getForEntity(url, String.class);
        } else {
            response = restTemplate.getForEntity(url, String.class, params);
        }

        return afterRequestProcess(response);
    }

    /**
     * 请求结果处理
     *
     * @param response 响应实体对象
     * @return String
     */
    private String afterRequestProcess(ResponseEntity<String> response) {
        Integer code = response.getStatusCode().value();
        if (!code.equals(HttpStatus.OK.value())) {
            log.error("请求失败：{}", response.toString());
            return HttpUtilV1.FAIL;
        }
        log.info("请求响应：{}", response.getBody());
        return response.getBody();
    }
}

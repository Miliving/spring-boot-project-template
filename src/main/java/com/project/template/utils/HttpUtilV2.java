package com.project.template.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Http请求方案二：基于Apache http components 包
 *
 * @author suibin.wu
 */
@Slf4j
@Component
public class HttpUtilV2 {

    /**
     * 编码格式:发送编码格式统一用UTF-8
     */
    private static final String ENCODING = "UTF-8";


    /**
     * 设置连接超时时间，单位毫秒。
     */
    private static final int CONNECT_TIMEOUT = 10 * 1000;


    /**
     * 请求获取数据的超时时间(即响应时间)，单位毫秒。
     */
    private static final int SOCKET_TIMEOUT = 10 * 1000;


    /**
     * 发送get请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    public static HttpClientResult doGet(String url) throws Exception {
        return doGet(url, null, null);
    }


    /**
     * 发送get请求；带请求参数(默认json格式传输数据)
     *
     * @param url    请求地址
     * @param params 请求参数集合
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    public static HttpClientResult doGet(String url, Map<String, String> params) throws Exception {
        Map<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "application/json");
        return doGet(url, headers, params);
    }


    /**
     * 发送get请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    private static HttpClientResult doGet(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        log.info("【请求地址】:{}", url);
        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }

        // 创建http对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // http配置项
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
        httpGet.setConfig(requestConfig);

        // 设置请求头
        assembleHeaders(headers, httpGet);

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        try {
            // 执行请求并获得响应结果
            return getHttpClientResult(httpResponse);
        } finally {
            // 释放资源
            release(httpResponse, httpClient);
        }
    }


    /**
     * 发送post请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    public static HttpClientResult doPost(String url) throws Exception {
        return doPost(url, null, null);
    }


    /**
     * 发送post请求；带请求参数(默认json格式传输数据)
     *
     * @param url    请求地址
     * @param params 参数集合
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    public static HttpClientResult doPost(String url, Map<String, Object> params) throws Exception {
        Map<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "application/json");
        return doPost(url, headers, params);
    }


    /**
     * 发送post请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    private static HttpClientResult doPost(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        log.info("【请求地址】:{}", url);
        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建http对象
        HttpPost httpPost = new HttpPost(url);

        // http配置项
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
        httpPost.setConfig(requestConfig);

        // 设置请求头
        assembleHeaders(headers, httpPost);

        // 封装请求参数
        assembleParams(params, httpPost);

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        try {
            // 执行请求并获得响应结果
            return getHttpClientResult(httpResponse);
        } finally {
            // 释放资源
            release(httpResponse, httpClient);
        }
    }


    /**
     * 封装请求头
     *
     * @param params     参数
     * @param httpMethod 方式
     */
    private static void assembleHeaders(Map<String, String> params, HttpRequestBase httpMethod) {
        // 封装请求头
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                // 设置到请求头到HttpRequestBase对象中
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * 封装请求参数
     *
     * @param params     参数
     * @param httpMethod 方式
     */
    private static void assembleParams(Map<String, Object> params, HttpEntityEnclosingRequestBase httpMethod) {
        // 封装请求参数
        if (params != null) {
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
            requestEntity.setContentEncoding("UTF-8");
            requestEntity.setContentType("application/json");
            // 设置到请求的http对象中
            httpMethod.setEntity(requestEntity);
        }
    }


    /**
     * 获得响应结果
     *
     * @param httpResponse httpResponse
     * @return HttpClientResult http client result
     * @throws Exception throw
     */
    private static HttpClientResult getHttpClientResult(CloseableHttpResponse httpResponse) throws Exception {
        // 获取返回结果
        if (null != httpResponse && null != httpResponse.getStatusLine()) {
            String content = "";
            if (null != httpResponse.getEntity()) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
                log.info("【响应参数】:{}", content);
            }
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
        }
        return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }


    /**
     * 释放资源
     *
     * @param httpResponse the http response
     * @param httpClient   the http client
     * @throws IOException the io exception
     */
    private static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
        if (null != httpResponse) {
            try {
                httpResponse.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}



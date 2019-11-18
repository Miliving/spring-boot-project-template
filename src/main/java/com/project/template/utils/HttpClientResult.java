package com.project.template.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 封装httpClient响应结果
 *
 * @author JourWon
 * @date Created on 2018年4月19日
 */
@Data
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = 2168152194164783950L;

    /**
     * 响应状态码
     */
    private Integer code;


    /**
     * 响应数据
     */
    private String content;


    /**
     * Instantiates a new Http client result.
     */
    public HttpClientResult() {
    }


    /**
     * Instantiates a new Http client result.
     *
     * @param code the code
     */
    public HttpClientResult(int code) {
        this.code = code;
    }


    /**
     * Instantiates a new Http client result.
     *
     * @param content the content
     */
    public HttpClientResult(String content) {
        this.content = content;
    }


    /**
     * Instantiates a new Http client result.
     *
     * @param code    the code
     * @param content the content
     */
    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }


    /**
     * Is request success boolean.
     *
     * @return the boolean
     */
    public Boolean isRequestSuccess() {
        return this.code.equals(HttpStatus.SC_OK);
    }


    /**
     * Get msg string.
     *
     * @return the string
     */
    public String getMsg(){
        return JSONObject.parseObject(content).getString("msg");
    }


    /**
     * Get data json object.
     *
     * @return the json object
     */
    public JSONObject getData(){
        return JSONObject.parseObject(content).getJSONObject("data");
    }
}

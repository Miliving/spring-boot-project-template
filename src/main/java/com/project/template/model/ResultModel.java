package com.project.template.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 处理结果模型类
 *
 * @param <T> the type parameter
 * @author suibin.wu
 */
@Data
@Accessors(chain = true)
public class ResultModel<T> {

    private Integer code;

    private String message;

    private T data;


    /**
     * Do fail result model.
     *
     * @param message the message
     * @return the result model
     */
    public ResultModel<T> doFail(String message) {
        this.setCode(-1);
        this.setMessage(message);
        this.data = null;
        return this;
    }


    /**
     * Do success result model.
     *
     * @param data the data
     * @return the result model
     */
    public ResultModel<T> doSuccess(T data) {
        this.setCode(1);
        this.setMessage("处理成功");
        this.data = data;
        return this;
    }

}

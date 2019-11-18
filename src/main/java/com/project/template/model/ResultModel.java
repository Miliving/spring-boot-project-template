package com.project.template.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 处理结果模型类
 *
 * @author suibin.wu
 */
@Data
@Accessors(chain = true)
public class ResultModel <T> {

    private Integer errorCode;

    private String errorMessage;

    private T data;
}

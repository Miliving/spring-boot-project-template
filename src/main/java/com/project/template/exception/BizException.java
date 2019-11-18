package com.project.template.exception;

import com.project.template.enums.BizErrorCodeEnum;
import lombok.Getter;

/**
 * The type Vendor exception.
 *
 * @author suibin.wu
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 错误码枚举
     */
    private BizErrorCodeEnum bizErrorCodeEnum;


    /**
     * 附加提示信息
     */
    private String extraInfo;


    /**
     * Instantiates a new Vendor exception.
     *
     * @param resultEnum the result enum
     */
    public BizException(BizErrorCodeEnum resultEnum) {
        this(resultEnum, null);
    }


    /**
     * Instantiates a new Vendor exception.
     *
     * @param resultEnum the result enum
     * @param extraInfo  the extra info
     */
    public BizException(BizErrorCodeEnum resultEnum, String extraInfo) {
        this.bizErrorCodeEnum = resultEnum;
        this.extraInfo = extraInfo;
    }
}

package com.project.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Result code enum.
 *
 * @author suibin.wu
 */
@Getter
@AllArgsConstructor
public enum BizErrorCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS_CODE(1, "操作成功"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(999999, "系统错误"),

    /**
     * 未知接口名
     */
    UNKNOWN_INTERFACE(100001, "未知接口名"),


    /**
     * 验签失败
     */
    VERIFY_FAIL(100002, "验签失败");


    private Integer code;

    private String message;

    public static BizErrorCodeEnum getByCode(Integer code) {
        for (BizErrorCodeEnum item : BizErrorCodeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}

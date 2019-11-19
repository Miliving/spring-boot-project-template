package com.project.template.utils;


import com.project.template.model.ResultModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suibin.wu
 */
public class ValidateMsgUtil {

    /**
     * 参数错误提取
     *
     * @param bindingResult 结果
     * @param <T>           泛型参数
     * @return ResultModel<T>
     */
    public static <T> ResultModel<T> invalidResult(BindingResult bindingResult) {
        ResultModel<T> result = new ResultModel<>();
        result.doFail("参数错误：" + msgConverter(bindingResult.getAllErrors()));
        return result;
    }


    /**
     * 参数错误归集
     *
     * @param errors 错误
     * @return List<String>
     */
    private static List<String> msgConverter(List<ObjectError> errors) {
        List<String> errorMsgList = new ArrayList<>();
        for (ObjectError error : errors) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return errorMsgList;
    }

}

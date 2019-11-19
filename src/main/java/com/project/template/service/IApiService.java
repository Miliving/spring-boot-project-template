package com.project.template.service;

import com.github.pagehelper.Page;
import com.project.template.db.model.Test;
import com.project.template.model.ResultModel;
import com.project.template.pojo.req.RequestVO;

/**
 * The interface Api service.
 *
 * @author suibin.wu
 */
public interface IApiService {


    /**
     * Find by name test.
     *
     * @param name the name
     * @return the test
     */
    Test findByName(String name);


    /**
     * Query operator string.
     *
     * @param mobile the mobile
     */
    void queryOperator(String mobile);


    /**
     * Gets users.
     *
     * @param name name
     * @return Page<Test>  users
     */
    Page<Test> getUsers(String name);


    /**
     * Params verify result model.
     *
     * @param params the params
     * @return the result model
     */
    ResultModel<Object> paramsVerify(RequestVO params);


    /**
     * Send email.
     */
    void sendEmail();
}

package com.project.template.service;

import com.project.template.db.model.Test;

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
     * @return the string
     */
    void queryOperator(String mobile);
}

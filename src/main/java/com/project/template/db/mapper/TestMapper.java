package com.project.template.db.mapper;

import com.project.template.db.model.Test;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Test mapper.
 *
 * @author suibin.wu
 */
public interface TestMapper {

    /**
     * Select by primary key test.
     *
     * @param fId the f id
     * @return the test
     */
    Test selectByPrimaryKey(Integer fId);


    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(Test record);


    /**
     * Update int.
     *
     * @param record the record
     * @return the int
     */
    int update(Test record);


    /**
     * Find by name test.
     *
     * @param name the name
     * @return the test
     */
    Test findByName(@Param("name") String name);
}
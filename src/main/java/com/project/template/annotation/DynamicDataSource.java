package com.project.template.annotation;

import java.lang.annotation.*;


/**
 * 多数据源切换配置
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {

    /**
     * Name string.
     *
     * @return the string
     */
    String name();
}

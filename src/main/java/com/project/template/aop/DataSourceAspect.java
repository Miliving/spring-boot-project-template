package com.project.template.aop;

import com.project.template.annotation.MultiplyDataSource;
import com.project.template.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * The type Data source aspect.
 *
 * @author suibin.wu
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect implements Ordered {


    /**
     * Data source point cut.
     */
    @Pointcut("@annotation(com.project.template.annotation.MultiplyDataSource)")
    public void dataSourcePointCut() {

    }


    /**
     * Around object.
     *
     * @param point the point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        MultiplyDataSource multiplyDataSource = method.getAnnotation(MultiplyDataSource.class);
        if (null == multiplyDataSource) {
            DynamicDataSource.setDataSource(DataSourceType.MASTER.getName());
        } else {
            DynamicDataSource.setDataSource(multiplyDataSource.name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
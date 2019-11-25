package com.project.template.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Dynamic data source.
 *
 * @author suibin.wu
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();


    /**
     * Instantiates a new Dynamic data source.
     *
     * @param defaultTargetDataSource the default target data source
     * @param targetDataSources       the target data sources
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }


    /**
     * Sets data source.
     *
     * @param dataSource the data source
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }


    /**
     * Gets data source.
     *
     * @return the data source
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }


    /**
     * Clear data source.
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
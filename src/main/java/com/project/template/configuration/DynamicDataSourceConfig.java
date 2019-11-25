package com.project.template.configuration;

import com.project.template.aop.DynamicDataSource;
import com.project.template.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Dynamic data source config.
 *
 * @author suibin.wu
 */
@Configuration
public class DynamicDataSourceConfig {


    /**
     * Master data source data source.
     *
     * @return the data source
     */
    @Bean(name = "master")
    @Qualifier("masterDataSource")
    @ConfigurationProperties(prefix = "master.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * Slave data source data source.
     *
     * @return the data source
     */
    @Bean(name = "slave")
    @Qualifier("slaveDataSource")
    @ConfigurationProperties(prefix = "slave.datasource")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * Data source dynamic data source.
     *
     * @param master the master
     * @param slave  the slave
     * @return the dynamic data source
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource master, DataSource slave) {
        Map<String, DataSource> targetDataSources = new HashMap<>(1);
        targetDataSources.put(DataSourceType.MASTER.getName(), master);
        targetDataSources.put(DataSourceType.SLAVE.getName(), slave);
        return new DynamicDataSource(master, targetDataSources);
    }


    /**
     * Transaction manager data source transaction manager.
     *
     * @param dataSource the data source
     * @return the data source transaction manager
     * @throws Exception the exception
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}

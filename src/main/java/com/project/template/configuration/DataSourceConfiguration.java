package com.project.template.configuration;

import com.project.template.aop.DataSourceRoute;
import com.project.template.enums.DataSourceType;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DataSourceConfiguration {


    /**
     * Master data source data source.
     *
     * @return the data source
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * Slave data source data source.
     *
     * @return the data source
     */
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
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
    public DataSourceRoute dataSource(DataSource master, DataSource slave) {
        Map<String, DataSource> targetDataSources = new HashMap<>(1);
        targetDataSources.put(DataSourceType.MASTER.getName(), master);
        targetDataSources.put(DataSourceType.SLAVE.getName(), slave);
        return new DataSourceRoute(master, targetDataSources);
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


    /**
     * 显示数据库连接池信息
     *
     * @param dataSource dataSource
     */
    public static void logDS(DataSource dataSource) {
        HikariDataSource hds = (HikariDataSource) dataSource;
        String info = "\n\n\tHikariCP连接池配置\n\t连接池名称：" +
                hds.getPoolName() +
                "\n\t最小空闲连接数：" +
                hds.getMinimumIdle() +
                "\n\t最大连接数：" +
                hds.getMaximumPoolSize() +
                "\n\t连接超时时间：" +
                hds.getConnectionTimeout() +
                "ms\n\t空闲连接超时时间：" +
                hds.getIdleTimeout() +
                "ms\n\t连接最长生命周期：" +
                hds.getMaxLifetime() +
                "ms\n";
        log.info(info);
    }

}

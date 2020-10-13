package com.gql.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author gql
 * @date 2020/7/28
 **/
@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource getDatasource(){
        return new DruidDataSource();
    }
}

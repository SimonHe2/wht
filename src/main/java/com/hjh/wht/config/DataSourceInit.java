package com.hjh.wht.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages="com.hjh.wht.repository.dao",sqlSessionFactoryRef = "sessionFactory")
public class DataSourceInit {

    @Autowired
    private DataSourceConfig dsConfig;

    @Bean(name="dataSource")
    public DruidDataSource initDataSource(){
        DruidDataSource ds=new DruidDataSource();
        ds.setUrl(dsConfig.getUrl());
        ds.setUsername(dsConfig.getUserName());
        ds.setPassword(dsConfig.getPassword());
        return ds;
    }

    @Bean(name="sessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(initDataSource());
        //PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        Resource[] mapperResources=new Resource[]{new ClassPathResource("classpath:/mybatis/mapper/*Mapper.xml")};
        //Resource configResource=new ClassPathResource("classpath:mconfig.xml");
        PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(mapperResources);
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mconfig.xml"));
        return sqlSessionFactoryBean;
    }
}

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
    public SqlSessionFactoryBean initSqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(initDataSource());

        //使用classPathResource不能使用classpath:指定路径，因为当前加载类的classLoader执行相对或者绝对地址查找资源。
//        Resource[] mapperResources=new Resource[]{new ClassPathResource("/mybatis/mapper/UserQuestionPoMapper.xml")};
//        Resource configResource=new ClassPathResource("mconfig.xml");
//        sqlSessionFactoryBean.setMapperLocations(mapperResources);
//        sqlSessionFactoryBean.setConfigLocation(configResource);

        //使用PathMatchingResourcePatternResolver可以从所有jar中找资源，并且支持文件模糊匹配
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mybatis/mapper/*Mapper.xml"));


        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mconfig.xml"));

        return sqlSessionFactoryBean;
    }
}

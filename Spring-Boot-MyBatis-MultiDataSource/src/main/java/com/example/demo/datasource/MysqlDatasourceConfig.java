package com.example.demo.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MysqlDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDatasourceConfig {
    //mysqldao扫描路径
    static final String PACKAGE = "com.example.demo.mysqldao";
    //mybatis mapper扫描路径
    static final String MAPPER_LOCATION = "classpath:/mapper/mysql/MysqlStudentMapper.xml";

    //数据源
    @Primary   //表示该bean在多个bean候选，该bean优先
    @Bean(name = "mysqlDatasource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource mysqlDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    //事务管理
    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(){
        return new DataSourceTransactionManager(mysqlDataSource());
    }
    //sqlSession工厂
    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResource(MysqlDatasourceConfig.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }
}


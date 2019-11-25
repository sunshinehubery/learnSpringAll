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
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {
    //mysqldao扫描路径
    static final String PACKAGE = "com.example.demo.oracledao";
    //mybatis mapper扫描路径
    static final String MAPPER_LOCATION = "classpath:mapper/oracle/OracleStudentMapper.xml";

    //数据源
    @Bean(name = "oracleDatasource")
    @ConfigurationProperties("spring.datasource.druid.oracle")
    public DataSource oracleDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    //事务管理
    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager(){
        return new DataSourceTransactionManager(oracleDataSource());
    }

    //sqlSession工厂
    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}

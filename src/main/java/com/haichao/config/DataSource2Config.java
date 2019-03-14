package com.haichao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>
 *     数据源配置
 * </p>
 * @Author yusj
 * @Date 2019/3/14
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.haichao.dao.order",
        sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSource2Config {

    /**
     * 数据源
     * @return
     */
    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.spring2")
    //注意这里  多个数据源配置，只能有一个是primary
    //@Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * SqlSessionFactory  <- test1DataSource
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "test2SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/order/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "test2TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "test2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

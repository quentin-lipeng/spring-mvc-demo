package org.quentin.web.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author:quentin
 * @create: 2022-09-30 22:13
 * @Description: mybatis config
 */
@MapperScan(basePackages = "org.quentin.web.mapper")
@ComponentScan("org.quentin.web.mapper")
@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(
            DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // 因为使用形参的方法才能使Value生效 但使用此方法就必须通过bean的方式DI
    @Bean
    public DataSource dataSource(
            @Value("${datasource.driverClassName}") String driverClass,
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password
    ) {
        System.out.println("source = " + driverClass);
        return new PooledDataSource(driverClass, url, username, password);
    }

}

package org.quentin.web.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>todo 了解@Profile的用法
 * <p>未配置事物时 会默认启用{@link SpringManagedTransaction}
 * <p>可以通过注册{@link MapperScannerConfigurer}来进行mapper的注册
 * 相当于@ComponentScan
 *
 * @author:quentin
 * @create: 2022-09-30 22:13
 * @Description: mybatis config
 */
@MapperScan(basePackages = "org.quentin.web.mapper")
// 有了MapperScan ComponentScan可以不使用 但是idea会报错
@ComponentScan({"org.quentin.web.mapper"})
// 相当于lite mode
@Configuration(proxyBeanMethods = false)
public class MybatisConfig {

    /**
     * 可用于扫描mapper 相当于{@link MapperScan @MapperScan}的替换
     */
    /*@Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("org.quentin.web.mapper");
        return configurer;
    }*/
    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 交由容器管理事物 会开启ManagedTransaction
//        factoryBean.setTransactionFactory(new ManagedTransactionFactory());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
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
        System.out.println("datasource.driverClassName = " + driverClass);
        return new PooledDataSource(driverClass, url, username, password);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("jdbc.properties"));
        return placeholderConfigurer;
    }

}

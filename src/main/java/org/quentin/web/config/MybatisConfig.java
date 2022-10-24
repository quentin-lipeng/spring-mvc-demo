/**
 * @author:quentin
 * @create: 2022-09-30 22:13
 * @Description: mybatis config
 */
package org.quentin.web.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.quentin.web.mapper.AccountMapper;
import org.quentin.web.mapper.WebResourceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/*
暂时还没搞清楚这两个的作用
因为使用了SqlSessionFactory进行mapper的注册
下面的mapperScan对【no XML配置】 没有作用
 */
@MapperScan("org.quentin.web.mapper")
//@PropertySource("classpath:jdbc.properties")
@Configuration
public class MybatisConfig {
    // 因为shiro配置需要使用LifecycleBeanPostProcessor 但配置其bean会造成@Value失效
    //@Value annotation does not work with static fields.
//    @Value("${datasource.url}")
//    private String url;
//    @Value("${datasource.username}")
//    private String user;
//    @Value("${datasource.password}")
//    private String password;
//    @Value("${datasource.driverClassName}")
//    private String driverClass;

    @Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(this.dataSource);
        // 为什么突然可以不使用用以下方法未知 大概原因是使用了MapperScan
//        Configuration configuration = new Configuration();
//        configuration.addMapper(AccountMapper.class);
//        configuration.addMapper(WebResourceMapper.class);
//        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource);
    }

    @Bean
    public DataSource dataSource(
            @Value("${datasource.driverClassName}") String driverClass,
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password
    ) {
        System.out.println("source = " + driverClass);
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}

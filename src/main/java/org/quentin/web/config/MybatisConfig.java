/**
 * @author:quentin
 * @create: 2022-09-30 22:13
 * @Description: mybatis config
 */
package org.quentin.web.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.quentin.web.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/*
暂时还没搞清楚这两个的作用
因为使用了SqlSessionFactory进行mapper的注册
下面的mapperScan对【no XML配置】 没有作用
 */
//@ComponentScan(basePackages = {"org.quentin.web.user"})
//@MapperScan("org.quentin.web.user.mapper")
@PropertySource("classpath:jdbc.properties")
public class MybatisConfig {
    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String user;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClass;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        Configuration configuration = new Configuration();
        configuration.addMapper(AccountMapper.class);
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    private DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

}

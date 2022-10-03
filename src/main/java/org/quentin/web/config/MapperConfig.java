/**
 * @author:quentin
 * @create: 2022-10-01 11:11
 * @Description:
 */
package org.quentin.web.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.quentin.web.mapper.AccountMapper;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class MapperConfig {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public AccountMapper accMapper() {
        return new SqlSessionTemplate(this.sqlSessionFactory).getMapper(AccountMapper.class);
    }
}

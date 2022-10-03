package org.quentin.web.mapper;

import org.apache.ibatis.annotations.*;
import org.quentin.web.pojo.Account;

/**
 * @author:quentin
 * @create: 2022-09-30 22:26
 * @Description: account mybatis mapper
 */
public interface AccountMapper {
    // 映射pojo对应的数据库属性
    @Results(id = "accountMap",
            value = {
                    @Result(property = "accountId", column = "account_id", id = true),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
            })

    @ResultMap(value = "accountMap")
    @Select("SELECT * FROM account WHERE account_id = #{accId}")
    Account getUser(@Param("accId") String accId);

    @ResultMap(value = "accountMap")
    @Select("SELECT * FROM account WHERE username = #{username}")
    Account getUserByName(@Param("username") String username);
}

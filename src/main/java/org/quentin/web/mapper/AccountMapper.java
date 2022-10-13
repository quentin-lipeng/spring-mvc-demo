package org.quentin.web.mapper;

import org.apache.ibatis.annotations.*;
import org.quentin.web.user.pojo.UserAccount;

/**
 * @author:quentin
 * @create: 2022-09-30 22:26
 * @Description: mybatis account mapper
 */
public interface AccountMapper {
    // 映射pojo对应的数据库属性
    @Results(id = "accountMap",
            value = {
                    @Result(property = "accountId", column = "account_id", id = true),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "salt", column = "salt"),
            })

//    @ResultMap(value = "accountMap")
    @Select("SELECT * FROM user_account WHERE account_id = #{accId}")
    UserAccount getUser(@Param("accId") String accId);

    @ResultMap(value = "accountMap")
    @Select("SELECT * FROM user_account WHERE username = #{username}")
    UserAccount getUserByName(@Param("username") String username);

    @Select("SELECT COUNT(username) FROM user_account where username = #{username}")
    int countAccountByName(@Param("username") String username);

    @Insert("INSERT INTO user_account(account_id,username,password,salt) VALUES (#{account_id},#{username},#{password},#{salt})")
    int insertAccount(@Param("account_id") String account_id,
                      @Param("username") String username,
                      @Param("password") String password,
                      @Param("salt") String salt);
}

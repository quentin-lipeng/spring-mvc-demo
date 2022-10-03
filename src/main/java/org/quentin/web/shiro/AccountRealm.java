/**
 * @author:quentin
 * @create: 2022-10-03 12:55
 * @Description:
 */
package org.quentin.web.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.quentin.web.mapper.AccountMapper;
import org.quentin.web.user.pojo.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class AccountRealm extends AuthorizingRealm {

    public static final Logger logger = LoggerFactory.getLogger(AccountRealm.class);

    @Resource
    private AccountMapper accountMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        logger.info("uptoken username = " + upToken.getUsername());
        logger.info("uptoken password = " + String.valueOf(upToken.getPassword()));
        // Null username is invalid
        if (upToken.getUsername() == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        Account account = accountMapper.getUserByName(upToken.getUsername());
        return new SimpleAuthenticationInfo(account.getUsername(), account.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

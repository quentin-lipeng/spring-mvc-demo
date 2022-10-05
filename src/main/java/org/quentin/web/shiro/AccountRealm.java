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
import org.apache.shiro.util.ByteSource;
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
        // Null username is invalid
        if (upToken.getUsername() == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        Account account = accountMapper.getUserByName(upToken.getUsername());
        if (account != null) {
            return new SimpleAuthenticationInfo(
                    upToken.getPrincipal(), account.getPassword(), ByteSource.Util.bytes(account.getSalt()), this.getName());
        } else {
            throw new UnknownAccountException();
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo === ");
        return null;
    }
}

/**
 * @author:quentin
 * @create: 2022-10-13 15:33
 * @Description:
 */
package org.quentin.web.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.quentin.web.user.pojo.UserAccount;

public class MyNamePassToken extends UsernamePasswordToken {
    public MyNamePassToken(UserAccount account) {
        super(account.getUsername(), account.getPassword());
    }
}

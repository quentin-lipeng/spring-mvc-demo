/**
 * @author:quentin
 * @create: 2022-09-30 22:44
 * @Description:
 */
package org.quentin.web.service;

import org.quentin.web.user.pojo.UserAccount;

public interface AccountService {
    public UserAccount getAccByAccId(String accId);

    public UserAccount getAccByAccName(String username);

    public boolean login(UserAccount ac);

    public boolean existAccount(String username);

    public boolean registerAccount(UserAccount account);
}

/**
 * @author:quentin
 * @create: 2022-09-30 22:44
 * @Description:
 */
package org.quentin.web.service;

import org.quentin.web.user.pojo.Account;

public interface AccountService {
    public Account getAccByAccId(String accId);

    public Account getAccByAccName(String username);

    public boolean login(Account ac);

    public boolean existAccount(String username);

    public boolean registerAccount(Account account);
}

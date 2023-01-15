package org.quentin.web.service.impl;

import org.quentin.web.mapper.AccountMapper;
import org.quentin.web.dto.UserAccount;
import org.quentin.web.service.AccountService;
import org.quentin.web.utils.MyBASE64;
import org.quentin.web.utils.MyEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author:quentin
 * @create: 2022-09-30 22:45
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accMapper = accountMapper;
    }

    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public UserAccount getAccByAccId(String accId) {
        return accMapper.getUser(accId);
    }

    @Override
    public UserAccount getAccByAccName(String username) {
        return accMapper.getUserByName(username);
    }

    @Override
    public boolean login(UserAccount ac) {
        return true;
    }

    @Override
    public boolean existAccount(String username) {
        return accMapper.countAccountByName(username) > 0;
    }

    @Override
    public boolean registerAccount(UserAccount account) {
        String salt = MyEncrypt.getSalt();
        String encryptPass = MyEncrypt.md5(account.getPassword(), salt);
        String userId = MyBASE64.encryptBASE();
        int retInt = accMapper.insertAccount(
                userId, account.getUsername(), encryptPass, salt);
        return retInt > 0;
    }
}

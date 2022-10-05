package org.quentin.web.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.quentin.web.mapper.AccountMapper;
import org.quentin.web.user.pojo.Account;
import org.quentin.web.service.AccountService;
import org.quentin.web.utils.MyBASE64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author:quentin
 * @create: 2022-09-30 22:45
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accMapper;

    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public Account getAccByAccId(String accId) {
        return accMapper.getUser(accId);
    }

    @Override
    public Account getAccByAccName(String username) {
        return accMapper.getUserByName(username);
    }

    @Override
    public boolean login(Account ac) {
        return true;
    }

    @Override
    public boolean existAccount(String username) {
        int userCount = accMapper.countAccountByName(username);
        return userCount > 0;
    }

    @Override
    public boolean registerAccount(Account account) {
        String base = MyBASE64.encryptBASE();
        int retInt = accMapper.insertAccount(
                base, account.getUsername(), account.getPassword());
        logger.info("retInt = " + retInt);
        return retInt > 0;
    }
}

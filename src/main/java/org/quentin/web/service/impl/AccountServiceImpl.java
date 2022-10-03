package org.quentin.web.service.impl;

import org.quentin.web.mapper.AccountMapper;
import org.quentin.web.user.pojo.Account;
import org.quentin.web.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:quentin
 * @create: 2022-09-30 22:45
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accMapper;

    @Override
    public Account getAccByAccId(String accId) {
        return accMapper.getUser(accId);
    }

    @Override
    public Account getAccByAccName(String username) {
        return accMapper.getUserByName(username);
    }
}

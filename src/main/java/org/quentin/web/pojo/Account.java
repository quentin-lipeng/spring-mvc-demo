package org.quentin.web.pojo;

/**
 * @author:quentin
 * @create: 2022-09-30 22:26
 * @Description:
 */
public class Account {
    private String username;
    private String password;
    private String accountId;

    public Account() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}

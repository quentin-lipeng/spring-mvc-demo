package org.quentin.web.user.pojo;

/**
 * @author:quentin
 * @create: 2022-09-30 22:26
 * @Description:
 */
public class UserAccount {
    private String username;
    private String password;
    private String accountId;
    private String salt;

    public UserAccount() {
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountId='" + accountId + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}

/**
 * @author:quentin
 * @create: 2022-10-03 11:03
 * @Description: user account resource with id/uuid
 */
package org.quentin.web.user.pojo;

public class UserAccRole {
    private int acId;
    private String accountId;
    private int roleId;

    public UserAccRole() {
    }

    public int getAcId() {
        return acId;
    }

    public void setAcId(int acId) {
        this.acId = acId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserAccRole{" +
                "acId=" + acId +
                ", accountId='" + accountId + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}

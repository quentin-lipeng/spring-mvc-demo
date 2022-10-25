/**
 * @author:quentin
 * @create: 2022-10-03 10:58
 * @Description: user role pojo
 */
package org.quentin.web.dto;

public class UserRole {
    private int roleId;
    private String roleName;
    private String roleInfo;

    public UserRole() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleInfo='" + roleInfo + '\'' +
                '}';
    }
}

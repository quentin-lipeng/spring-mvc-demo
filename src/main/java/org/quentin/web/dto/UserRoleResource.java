/**
 * @author:quentin
 * @create: 2022-10-03 11:01
 * @Description: user role resource with id
 */
package org.quentin.web.dto;

public class UserRoleResource {
    private int reId;
    private int roleId;
    private int resId;

    public UserRoleResource() {
    }

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "UserRoleResource{" +
                "reId=" + reId +
                ", roleId=" + roleId +
                ", resId=" + resId +
                '}';
    }
}

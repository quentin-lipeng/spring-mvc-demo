/**
 * @author:quentin
 * @create: 2022-10-03 10:59
 * @Description: user resource
 */
package org.quentin.web.dto;

public class UserResource {
    private int resId;
    private String resourceName;
    private String resourceInfo;

    public UserResource() {
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceInfo() {
        return resourceInfo;
    }

    public void setResourceInfo(String resourceInfo) {
        this.resourceInfo = resourceInfo;
    }

    @Override
    public String toString() {
        return "UserResource{" +
                "resId=" + resId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceInfo='" + resourceInfo + '\'' +
                '}';
    }
}

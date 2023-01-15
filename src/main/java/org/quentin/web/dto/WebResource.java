package org.quentin.web.dto;

/**
 * @author:quentin
 * @create: 2022-10-10 20:56
 * @Description: 网页资源
 */
public class WebResource {

    private String resourceId;
    private String resourceName;
    private String resourceInfo;

    public WebResource() {
    }

    public WebResource(String resourceName, String resourceInfo) {
        this.resourceName = resourceName;
        this.resourceInfo = resourceInfo;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
        return "WebResource{" +
                "resourceId='" + resourceId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceInfo='" + resourceInfo + '\'' +
                '}';
    }
}
/**
 * @author:quentin
 * @create: 2022-10-10 21:11
 * @Description:
 */
package org.quentin.web.mapper;

import org.apache.ibatis.annotations.*;
import org.quentin.web.mapper.builder.WebResourceBuilder;
import org.quentin.web.pojo.WebResource;

import java.util.ArrayList;

public interface WebResourceMapper {
    // TODO 后面可能不需要没有id的webResource

    // 如果其他方法也需要使用此Results是 可使用ResultMap("webResourceWithNoIdMap")
    // 但前提是注解放在方法上 这样相当于注册了此map
    @Results(id = "webResourceWithNoIdMap",
            value = {
                    @Result(property = "resourceName", column = "resource_name"),
                    @Result(property = "resourceInfo", column = "resource_info"),
            })
    @SelectProvider(type = WebResourceBuilder.class, method = "buildResourceList")
    ArrayList<WebResource.WithNoId> webResourceList();

    @Results(id = "webResourceWithIdMap",
            value = {
                    @Result(property = "resourceId", column = "res_id", id = true),
                    @Result(property = "resourceName", column = "resource_name"),
                    @Result(property = "resourceInfo", column = "resource_info"),
            })
    @Select("SELECT A.res_id, A.resource_name, A.resource_info FROM user_resource as A WHERE res_id = #{id}")
    WebResource resourceById(@Param("id") Integer id);
}

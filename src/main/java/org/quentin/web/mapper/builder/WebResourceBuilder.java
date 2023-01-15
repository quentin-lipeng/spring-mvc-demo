package org.quentin.web.mapper.builder;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

/**
 * 实现了ProviderMethodResolver接口后@SelectProvider 就不用配置method属性了
 *
 * @author:quentin
 * @create: 2022-10-10 21:32
 * @Description:
 */
public class WebResourceBuilder implements ProviderMethodResolver {
    private static final String TABLE = "user_resource";

    public static String webResourceList() {
        return new SQL() {{
            SELECT("A.res_id, A.resource_name, A.resource_info");
            FROM(TABLE + " as A");
        }}.toString();
    }
}

/**
 * @author:quentin
 * @create: 2022-10-10 21:32
 * @Description:
 */
package org.quentin.web.mapper.builder;

import org.apache.ibatis.jdbc.SQL;

public class WebResourceBuilder {
    private static final String TABLE = "user_resource";

    public static String buildResourceList() {
        return new SQL() {{
            SELECT("A.res_id, A.resource_name, A.resource_info");
            FROM(TABLE + " as A");
        }}.toString();
    }
}

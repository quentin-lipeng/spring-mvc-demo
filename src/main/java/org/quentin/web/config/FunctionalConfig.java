/**
 * @author:quentin
 * @create: 2022-10-15 16:19
 * @Description:
 */
package org.quentin.web.config;

import org.quentin.web.functional.UserAccHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.*;

// 用于Functional endpoint使用 仅做了解
//@Configuration
public class FunctionalConfig {
    @Bean
    public RouterFunction<ServerResponse> route() {
        UserAccHandler handler = new UserAccHandler();
        return RouterFunctions.route()
                .GET("/user-acc", handler::getUserAcc)
                .build();
    }
}

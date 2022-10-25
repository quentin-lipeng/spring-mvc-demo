/**
 * @author:quentin
 * @create: 2022-10-15 16:17
 * @Description:
 */
package org.quentin.web.functional;

import org.quentin.web.dto.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

// 仅做了解
public class UserAccHandler {
    public static final Logger LOG = LoggerFactory.getLogger(UserAccHandler.class);

    public ServerResponse getUserAcc(ServerRequest serverRequest)
            throws ServletException, IOException {
        Optional<String> name = serverRequest.param("name");
        System.out.println(name);
        UserAccount account = new UserAccount();
        account.setUsername("mike");
        account.setPassword("pass");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(account);
    }
}

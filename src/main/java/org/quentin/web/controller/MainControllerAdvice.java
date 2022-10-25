
package org.quentin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 在次注解类下定义的@ExceptionHandler是全局可用的 覆盖所有@Controller
 * @author:quentin
 * @create: 2022-10-12 20:33
 * @Description:
 */
@ControllerAdvice
public class MainControllerAdvice {
    public static final Logger LOG = LoggerFactory.getLogger(MainControllerAdvice.class);

    /**
     * 所有Exception类型见 DefaultHandlerExceptionResolver
     * @author quentin
     * @date 2022/10/25
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> methodNotAllowHandle(
            HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.ok("method not allow");
    }

    /**
     * 如果想使用NoHandlerFoundException 需要添加 appServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
     * @author quentin
     * @date 2022/10/25
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> notFoundHandle(
            NoHandlerFoundException ne) {
        LOG.info("e getMessage = " + ne.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
}

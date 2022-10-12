/**
 * @author:quentin
 * @create: 2022-10-12 20:33
 * @Description:
 */
package org.quentin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

// 在次注解类下定义的@ExceptionHandler是全局可用的 覆盖所有@Controller
@ControllerAdvice
public class MainControllerAdvice {
    public static final Logger LOG = LoggerFactory.getLogger(MainControllerAdvice.class);


//     所有Exception类型见 DefaultHandlerExceptionResolver
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handle(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.ok("method not allow");
    }

    // 如果想使用NoHandlerFoundException 需要添加 appServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handle1(NoHandlerFoundException ne) {
        LOG.info("e getMessage = " + ne.getMessage());
        return ResponseEntity.ok("not found");
    }
}

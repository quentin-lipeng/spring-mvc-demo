package org.quentin.web.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author quentin
 */

@Aspect
public class LogAspect {

    /**
     * @author quentin
     * @date 2022/11/2
     */
    @Pointcut("execution(* org.quentin.web.controller.*.*(..))")
    public void log1() {
    }

    @Pointcut("execution(* org.quentin.web.controller.WebResourceController.*(..))")
    public void returnLog(){
    }

}

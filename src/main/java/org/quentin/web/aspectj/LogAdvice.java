package org.quentin.web.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * advice ordering from highest to lowest: @Around, @Before, @After, @AfterReturning, @AfterThrowing
 * @author quentin
 */
@Aspect
@Component
public class LogAdvice {
    public static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);

    @Before(value = "org.quentin.web.aspectj.LogAspect.log1()")
    public void beforeLog() {
        LOGGER.info("--- log before ---");
    }

    @AfterReturning(pointcut = "org.quentin.web.aspectj.LogAspect.returnLog()", returning = "retVal")
    public void afterLog(Object retVal) {
        LOGGER.info("return value = " + retVal.getClass());
    }


}

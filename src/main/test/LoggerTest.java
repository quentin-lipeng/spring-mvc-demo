import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:quentin
 * @create: 2022-10-01 23:25
 * @Description: logback test
 */
public class LoggerTest {
    public static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void logBackTest(){

        logger.info("info");
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        // print logback's internal status
//        StatusPrinter.print(lc);
    }
}

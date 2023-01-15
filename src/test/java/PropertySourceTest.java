import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quentin.web.config.MybatisConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author quentin
 */
@ContextConfiguration(classes = {MybatisConfig.class})
// todo 此注解很重要 了解此注解用处
// Instructs JUnit Jupiter to extend the test with Spring support.
@ExtendWith(SpringExtension.class)
@TestPropertySource({"/jdbc.properties"})
public class PropertySourceTest {
    @Value("${datasource.username}")
    private String name;

    @Test
    public void propertyTest() {
        System.out.println(name);
    }

}

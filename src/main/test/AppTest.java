import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * @author:quentin
 * @create: 2022-10-03 23:17
 * @Description:
 */

public class AppTest {
    @Test
    public void appTest() {
        FileSystemResource resource = new FileSystemResource("src/main/resources/jdbc.properties");
        System.out.println(resource);
//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
//        String[] beanDefinitionNames = app.getBeanDefinitionNames();
//        for (String bean : beanDefinitionNames) {
//            System.out.println(bean);
//        }
    }
}

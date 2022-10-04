import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author:quentin
 * @create: 2022-10-03 23:17
 * @Description:
 */

public class AppTest {
    @Test
    public void appTest() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for (String bean : beanDefinitionNames) {
            System.out.println(bean);
        }
    }
}

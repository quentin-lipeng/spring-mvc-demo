import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author:quentin
 * @create: 2022-10-03 23:17
 * @Description:
 */
public class AppTest {
    @Test
    public void appTest() {
        int i;
        /*
        其中 getResourceAsStream返回的是BufferedInputStream类型的对象
        其中就实现了available() 如果其抽象类InputStream的子类未实现available()方法
        就会返回0 也就是InputStream默认方法返回的值
         */
        try (InputStream resource = this.getClass().getResourceAsStream("jdbc.properties")) {
            assert resource != null;
            System.out.println("read before : " + resource.available());
            while ((i = resource.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("\nread after : " + resource.available());
        } catch (IOException exception) {
            System.out.println("error");
        }
//        FileSystemResource resource = new FileSystemResource("src/main/resources/jdbc.properties");
//        System.out.println(resource);

//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
//        String[] beanDefinitionNames = app.getBeanDefinitionNames();
//        for (String bean : beanDefinitionNames) {
//            System.out.println(bean);
//        }
    }
}

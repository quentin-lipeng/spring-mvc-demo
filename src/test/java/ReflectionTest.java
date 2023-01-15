import org.junit.jupiter.api.Test;
import org.quentin.web.dto.UserAccount;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author quentin
 */
public class ReflectionTest {
    @Test
    public void reflectTest() {
        UserAccount account = new UserAccount();
        // 通常用于解决依赖注入的问题 比如使用Autowired注入的成员进行赋值
        // 换一种思路 使用构造器进行成员注入的方法可能就可以避免使用反射注入
        // <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#unit-testing-utilities">链接</a>
        // 解释了ReflectionTestUtils的作用，也就是存在的意义
        ReflectionTestUtils.setField(account, "username", "root");
        Object field = ReflectionTestUtils.getField(account, UserAccount.class, "username");
        System.out.println(field);
    }

}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quentin.web.config.MybatisConfig;
import org.quentin.web.config.SpringBeanConfig;
import org.quentin.web.config.WebMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.context.WebApplicationContext;

/**
 * 可用于加载xml配置 classes属性用于加载java配置类
 * {@link ContextConfiguration @ContextConfiguration}
 * 此注解用于注入ApplicationContext
 * {@link SpringJUnitConfig @SpringJUnitConfig}
 * SpringJUnitWebConfig中含有此注解
 * {@link ExtendWith @ExtendWith(SpringExtension.class)}
 * 此注解用于junit4(暂不使用了): @RunWith(SpringJUnit4ClassRunner.class)
 *
 * @author:quentin
 * @create: 2022-10-09 15:39
 */
//此注解用于注入WebApplicationContext
//此注解中含有@ContextConfiguration
@SpringJUnitWebConfig(classes =
        {WebMvcConfig.class, SpringBeanConfig.class, MybatisConfig.class}
)
public class ResourceTest {

    // 如果需要注入此类需要使用到@WebAppConfiguration注解
    // SpringJUnitWebConfig就含有此注解
    @Autowired
    WebApplicationContext wac;

    @Test
    public void resTest() {
        ;
    }

}

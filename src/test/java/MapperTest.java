import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quentin.web.config.MybatisConfig;
import org.quentin.web.config.WebMvcConfig;
import org.quentin.web.dto.UserAccount;
import org.quentin.web.mapper.AccountMapper;
import org.quentin.web.mapper.WebResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author quentin
 */
@ContextConfiguration(classes = {MybatisConfig.class})
@ExtendWith(SpringExtension.class)
// 因为项目不是使用PropertySource进行配置文件的导入 所以需要使用此方法进行导入
@TestPropertySource({"/jdbc.properties"})
public class MapperTest {

    @Autowired
    private WebResourceMapper webResourceMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void tempTest() {
        System.out.println(webResourceMapper.webResourceList());
    }

    @Test
    public void accountTest(){
        UserAccount user = accountMapper.getUser("MTY2NDk0ODgyNTIxMDU0NTI=");
        Assertions.assertNotNull(user);
    }
}

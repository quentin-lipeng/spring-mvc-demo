import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quentin.web.config.WebMvcConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author:quentin
 * @create: 2022-10-16 09:12
 * @Description:
 */

@SpringJUnitWebConfig({WebMvcConfig.class})
public class MockWebTest {
    MockMvc mockMvc;

    @BeforeEach
    public void init(WebApplicationContext wac){
        System.out.println(wac);
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testMock(){
        System.out.println("done!");
    }
}

import org.junit.jupiter.api.Test;
import org.quentin.web.config.MybatisConfig;
import org.quentin.web.config.SpringBeanConfig;
import org.quentin.web.config.WebMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author quentin
 */
@SpringJUnitWebConfig(classes = {WebMvcConfig.class, MybatisConfig.class, SpringBeanConfig.class})
public class WebMockTest {
    @Autowired
    WebApplicationContext wac; // cached

    @Autowired
    MockServletContext servletContext; // cached

    @Autowired
    MockHttpSession session;

    @Autowired
    MockHttpServletRequest request;

//    @Autowired
//    MockHttpServletResponse response;

//    @Autowired
//    ServletWebRequest webRequest;

    @Test
    public void tempTest(){
        System.out.println(request);
    }
}

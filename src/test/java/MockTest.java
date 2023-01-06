import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quentin.web.config.MainWebAppInitializer;
import org.quentin.web.config.WebMvcConfig;
import org.quentin.web.controller.AuthController;
import org.quentin.web.controller.IndexController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * @author:quentin
 * @create: 2022-10-09 19:31
 * @Description:
 */

// TODO 进一步完善mvc部分的mock测试
@SpringJUnitWebConfig({WebMvcConfig.class})
public class MockTest {
    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        // 如果使用此方法需要在WebMvcConfig中扫描注解 在MainWebAppInit扫描没有用
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new AuthController()).build();
    }

    @Test
    public void indexMock() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void authMock() throws Exception {
        mockMvc.perform(post("/auth/get-mes/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}

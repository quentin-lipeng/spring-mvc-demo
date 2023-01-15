import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quentin.web.config.MybatisConfig;
import org.quentin.web.config.SpringBeanConfig;
import org.quentin.web.config.WebMvcConfig;
import org.quentin.web.controller.WebResourceController;
import org.quentin.web.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author quentin
 */
public class MockMvcTest extends ResourceTest{
    MockMvc mockMvc;

    @Autowired
    private ResourceService resourceService;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders
//                .standaloneSetup(new WebResourceController(resourceService))
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void controllerTest() {
        System.out.println(mockMvc);
    }

    @Test
    public void resourceList() throws Exception {
        mockMvc.perform(
                get("/resource/list")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON)
        );

    }
}

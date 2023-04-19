import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quentin.web.config.MybatisConfig;
import org.quentin.web.config.ShiroConfig;
import org.quentin.web.config.SpringBeanConfig;
import org.quentin.web.config.WebMvcConfig;
import org.quentin.web.controller.AuthController;
import org.quentin.web.service.AccountService;
import org.quentin.web.validator.UserAccValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author quentin
 */
@SpringJUnitWebConfig(
        classes = {
                WebMvcConfig.class,
                MybatisConfig.class,
                SpringBeanConfig.class,
        }
)
public class AuthControllerTest {

    @Autowired
    AccountService accountService;
    @Autowired
    WebApplicationContext wac;
    WebTestClient client;

    @BeforeEach
    public void prepareController() {
        client =
                MockMvcWebTestClient.bindToController(
                                new AuthController(accountService))
                        .build();
//        client = MockMvcWebTestClient.bindToApplicationContext(this.wac).build();
    }

    @Test
    public void authTest() {
        Assertions.assertNotNull(client);
    }

    @Test
    public void getMes() {
        client.get().uri("/auth/get-mes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectAll(
                        responseSpec -> responseSpec.expectStatus().isOk(),
                        responseSpec -> responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    public void loginTest() {
    }

}

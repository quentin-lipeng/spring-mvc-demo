import org.junit.jupiter.api.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author:quentin
 * @create: 2022-10-09 15:25
 * @Description:
 */

public class ClientTest {
    @Test
    public void cliTest(){
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String result = restTemplate.getForObject(
                "http://localhost:8080/{auth}/login", String.class, "auth");
        System.out.println(result);
    }
}

import org.junit.jupiter.api.Test;
import org.quentin.web.controller.AuthController;
import org.quentin.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author quentin
 */
public class AccountServiceTest extends ResourceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void demo() {
        System.out.println(accountService);
    }
}

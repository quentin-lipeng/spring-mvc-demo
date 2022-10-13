import org.junit.Test;
import org.quentin.web.user.pojo.UserAccount;
import org.quentin.web.validator.UserAccValidator;
import org.springframework.beans.PropertyValue;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * @author:quentin
 * @create: 2022-10-13 21:03
 * @Description:
 */

public class ValidatorTest {
    @Test
    public void validTest() {
        UserAccount target = new UserAccount();
        target.setUsername("admin");
        DataBinder binder = new DataBinder(target);
        binder.setValidator(new UserAccValidator());

        // validate the target object
        binder.validate();

        // get BindingResult that includes any validation errors
        BindingResult results = binder.getBindingResult();

        System.out.println(results);
        System.out.println(results.hasErrors());
    }
}

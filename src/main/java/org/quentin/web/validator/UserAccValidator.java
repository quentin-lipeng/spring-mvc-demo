/**
 * @author:quentin
 * @create: 2022-10-13 17:21
 * @Description:
 */
package org.quentin.web.validator;

import org.quentin.web.user.pojo.UserAccount;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserAccValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserAccount.class.equals(clazz);
    }

    /**
     * 使用@Validated可以验证对象
     * 也可以使用DataBinder 见 spring core 3.7.3
     * @param target 验证的目标对象
     * @param errors 所有错误都通过此对象抛出
     * @author quentin
     * @date 2022/10/13
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "username.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
    }
}

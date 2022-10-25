package org.quentin.web.utils;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @see SimpleKeyGenerator
 * @author quentin
 */
public class MyCacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        System.out.println(
                "target = " + target + "\nmethod = " + method + "\nparams = " + Arrays.toString(params));
        return params[0];
    }
}

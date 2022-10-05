
package org.quentin.web.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author:quentin
 * @create: 2022-10-05 12:48
 * @Description: 加密方法 目前拥有MD5加密
 */
public class MyEncrypt {

    public static String md5(String password, String salt) {
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt);
        return simpleHash.toHex();
    }

    /**
     * @return 返回shiro的随机生成作为salt
     * @author quentin
     * @date 2022/10/5
     */
    public static String getSalt() {
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        ByteSource nextByteSource = generator.nextBytes();
        return nextByteSource.toString();
    }
}

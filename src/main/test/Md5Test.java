import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.quentin.web.utils.MyEncrypt;

/**
 * @author:quentin
 * @create: 2022-10-05 13:12
 * @Description:
 */

public class Md5Test {
    /**
     * 这个测试展示了shiro的加密后登录的基本流程
     * @return
     * @author quentin
     * @date 2022/10/5
     */
    @Test
    public void md5Test() {

        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        ByteSource nextByteSource = generator.nextBytes();
        System.out.println(nextByteSource.toString());
        System.out.println(nextByteSource.toHex());
        String pass = "mike";
        // 1.使用工具类加密密码
        String hashPass = new SimpleHash("MD5", pass, "").toHex();
        System.out.println(hashPass);

        // 2.创建密码匹配器，注入自定义realm中
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        // 3.调用主体登录，将调用匹配方法。
        UsernamePasswordToken token = new UsernamePasswordToken("mike", pass);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                token.getPrincipal(), hashPass, ByteSource.Util.bytes(""), "A");
        boolean OK = credentialsMatcher.doCredentialsMatch(token, info);
        System.out.println(OK);
    }
}

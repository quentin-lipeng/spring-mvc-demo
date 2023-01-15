import org.junit.jupiter.api.Test;
import org.quentin.web.utils.MyBASE64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.rmi.server.UID;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author:quentin
 * @create: 2022-09-30 22:40
 * @Description:
 */

public class UniqueIdTest {
    @Test
    public void uuidTest() throws IOException {
//        for (int i = 0; i < 10; i++) {
//            String s = MyBASE64.encryptBASE();
//            System.out.println(s);
//            System.out.println(MyBASE64.decryptBASE(s));
//        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts);
    }
}

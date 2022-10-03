import org.junit.Test;

import java.util.UUID;

/**
 * @author:quentin
 * @create: 2022-09-30 22:40
 * @Description:
 */

public class UUidTest {
    @Test
    public void uuidTest(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }
}

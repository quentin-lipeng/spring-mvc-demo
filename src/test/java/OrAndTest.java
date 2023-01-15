import org.junit.jupiter.api.Test;

/**
 * @author:quentin
 * @create: 2022-10-05 16:24
 * @Description:
 */

public class OrAndTest {
    public boolean a(String a) {
        System.out.println("a");
        return "a".equals(a);
    }

    public boolean b(String b) {
        System.out.println("b");
        return "b".equals(b);
    }

    @Test
    public void andOr() {
        // & 为不短路 也就是前一个条件不满足 也会执行后面的
        // && 为短路 只要前面条件不满足 就不会执行后面的
        if (a("c") && b("b")){
            System.out.println("c");
        }
    }
}

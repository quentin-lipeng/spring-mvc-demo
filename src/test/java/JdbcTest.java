import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * todo 使用@ActiveProfiles 了解一下(需要mysql时才需要用到profile 内嵌使用的数据库不涉及profile)
 *
 * @author quentin
 */
public class JdbcTest {

    private JdbcTemplate jdbcTemplate;
    private EmbeddedDatabase database;

    @BeforeEach
    public void prepareJdbc() {
        database = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(database);
    }

    @AfterEach
    public void afterTest() {
        /*
        不知道具体原因 调用shutdown方法后连接仍未关闭
        因为关闭需要获取一个连接 所以在关闭后会有debug的log:
        DEBUG o.s.j.d.SimpleDriverDataSource - Creating new JDBC Driver Connection to
         */
        database.shutdown();
    }

    @Test
    public void testJdbcTemplate() {
        System.out.println(jdbcTemplate);
    }

    @Test
    public void testEmbeddedDatabase() {
        System.out.println(database);
    }

    @Test
    public void testValue() {
    }
}

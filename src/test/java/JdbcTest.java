import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

/**
 * @author quentin
 */
public class JdbcTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void prepareJdbc(){
        jdbcTemplate = new JdbcTemplate(dataSource(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/mvc_demo?serverTimezone=UTC&characterEncoding=UTF-8",
                "root",
                "000327"
        ));
    }

    @Test
    public void testJdbcTemplate() {
        int userAccount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "user_account");
        System.out.println(userAccount);
    }

    @Test
    public void testValue(){
    }

    public DataSource dataSource(
            String driverClass, String url, String username, String password) {
        return new PooledDataSource(driverClass, url, username, password);
    }
}

package demoTest;

import demo.dao.OfficeDao;
import demo.dao.SessionHolder;
import demo.models.Office;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by _dani on 03.07.2016.
 */
public class OfficeDaoTest {

    private static final java.lang.String DB_URL = "jdbc:mysql://localhost/new_db";
    private static final String USER = "root";
    private static final String PASS = "toor";

    @Test
    public void testConnection() throws SQLException {
        Connection conn = null;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(conn);

        OfficeDao officeDao = new OfficeDao(new SessionHolder());
        List<Office> all = officeDao.getAll();
        Assert.assertTrue(all.size() > 0);
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i).getCity());
        }
        conn.close();
    }

}
package demoTest;

import org.junit.*;

import java.sql.*;

/**
 * Created by _dani on 26.06.2016.
 */
public class AppTest {

    private static final String DB_URL = "jdbc:mysql://localhost/new_db";
    private static final String USER = "root";
    private static final String PASS = "toor";

    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    @BeforeClass
    public static void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() throws ClassNotFoundException, SQLException {
        Assert.assertNotNull("qwwwwwwwwwwwwwe", conn);
        resultSet = statement.executeQuery("SELECT * FROM orders;");
        while (resultSet.next()) {
            int qty = resultSet.getInt("QTY");
            System.out.println("QTY = " + qty);
        }
    }
    @Test
    public void testOne() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM orders;");
        resultSet.next();
        int expected = 7;
        int actual = resultSet.getInt("QTY");
        Assert.assertEquals("ะะต 7", expected, actual);
    }

    @AfterClass
    public static void end() {
        try {
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
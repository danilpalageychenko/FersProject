package demo.dao;

import demo.models.Office;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfficeDao {

    private static final String DB_URL = "jdbc:mysql://localhost/new_db";
    private static final String USER = "root";
    private static final String PASS = "toor";
    private static Connection conn = null;
    private static Statement statement = null;

    public OfficeDao(Connection conn) {
        this.conn = conn;
    }

    public boolean save(Office office) throws SQLException {
        String sql = String.format("insert into OFFICES(OFFICE,CITY,REGION) values(%d,'%s','%s')",
                office.getId(), office.getCity(), office.getRegion());
        return executeSql(sql);
    }

    public boolean update(Office office) throws SQLException {
        String sql = String.format("update OFFICES set CITY='%s', REGION='%s' where OFFICE=%d",
                office.getCity(), office.getRegion(), office.getId());
        return executeSql(sql);
    }

    public boolean delete(Office office) throws SQLException {
        String sql = String.format("delete from OFFICES where OFFICE=%s",
                office.getId());
        return executeSql(sql);
    }

    public Office getById(int id) throws SQLException {
        String sql = String.format("select OFFICE,CITY,REGION from OFFICES where OFFICE=%d",
                id);
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Office office = null;
            if (resultSet.next()) {
                office = new Office();
                office.setId(resultSet.getInt("OFFICE"));
                office.setCity(resultSet.getString("CITY"));
                office.setRegion(resultSet.getString("REGION"));
            }
            resultSet.close();
            return office;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Office> getAll() throws SQLException {
        String sql = "select OFFICE,CITY,REGION from OFFICES";
        List<Office> offices = new ArrayList<Office>();
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Office office = new Office();
                office.setId(resultSet.getInt("OFFICE"));
                office.setCity(resultSet.getString("CITY"));
                office.setRegion(resultSet.getString("REGION"));
                offices.add(office);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return offices;
    }

    private boolean executeSql(String sql) throws SQLException {
        int count = 0;
        try {
            statement = conn.createStatement();
            count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count == 1;
    }
}

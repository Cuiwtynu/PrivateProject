package ConnectionsDatebases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dbutils {
    private static String driverClassName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/carspecials";
    private static String user = "root";
    private static String password = "123456";

    public static Connection Connect() {
        Connection connect = null;
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("类加载失败");
            e.printStackTrace();
        }
        try {
            connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return connect;
    }

    public static PreparedStatement getPrepareStatement(Connection connection, String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con = null;
            }
        }
    }

    public static void closePreparestatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                preparedStatement = null;
            }
        }
    }
}

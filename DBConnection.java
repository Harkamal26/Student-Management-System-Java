import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb",
                "root",
                "Ht@6284475013"
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: GAGAWA AKO DB.PROPERTIES PARA WALANG PROBLEMA DITO KASI IBA IBA TAYO NG USER TAS PASSWORD 👍
// EDIT NYO NALANG 👍
public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/ticketing_system";
    private static final String user = "root";
    private static final String password = "admin";

    public static Connection connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}

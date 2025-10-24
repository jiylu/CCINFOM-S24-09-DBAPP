package db;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * gawa ka ng file sa root folder, tawaging mong "db.properties"
 * tapos, ilagay mo to:
 * db.url=jdbc:mysql://localhost:3306/ticketing_system
 * db.user=(username mo, yung sa workbench)
 * db.password=(password mo sa workbench)
 * 
 * example:
 * db.url=jdbc:mysql://localhost:3306/ticketing_system
 * db.user=admin
 * db.password=root
 */
public class DBConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    static {
        try (InputStream input = DBConnection.class.getResourceAsStream("/db.properties")){
            Properties p = new Properties();
            p.load(input);
            URL = p.getProperty("db.url");
            USER = p.getProperty("db.user");
            PASSWORD = p.getProperty("db.password");
        } catch (Exception e){
            throw new RuntimeException("Failed to load database configuration.");
        }
    }
    
    public static Connection connect(){
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to db");
            return conn;
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db");
        }
    }
}

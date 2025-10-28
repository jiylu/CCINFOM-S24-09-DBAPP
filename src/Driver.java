import controllers.MainController;
import db.DBConnection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import view.Frame;

class Driver {
    public static void main (String[] args){
        Connection conn = DBConnection.connect();
        setupDB(conn);
        Frame frame = new Frame();
        MainController mainController = new MainController(conn, frame);
    }

    // runs the schema
    private static void setupDB(Connection conn){
        try {
            String schema = Files.readString(Paths.get("dbapp.sql"));
            String[] queries = schema.split(";");
            Statement stmt = conn.createStatement();

            for (String query : queries){
                query = query.trim();
                if (!query.isEmpty()){
                    stmt.execute(query);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
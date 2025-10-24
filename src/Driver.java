import db.DBConnection;
import java.sql.Connection;
import view.Frame;
class Driver {

    public static void main (String[] args){
        Connection conn = DBConnection.connect();
        Frame frame = new Frame();
    }
}
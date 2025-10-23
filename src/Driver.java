import db.DBConnection;
import view.Frame;
class Driver {

    public static void main (String[] args){
        try {
            DBConnection.connect();
            System.out.println("Connected to db");
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db");
        }

        Frame frame = new Frame();
    }
}
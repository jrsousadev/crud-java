package src.br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    // User name MySQL
    private static final String USERNAME = "root";

    // Password BD
    private static final String PASSWORD = "";

    // Path, Port and Name BD
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    /*
    * Connection BD
    */

    public static Connection createConnectionToMySQL() throws Exception {

        // Class load in JVM
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws Exception {
        // Recover connection BD
        Connection con = createConnectionToMySQL();

        // Testing connection is null ?
        if(con != null) {
            System.out.println("Connection Running!");
            con.close();
        }
    }

}

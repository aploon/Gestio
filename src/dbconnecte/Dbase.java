package dbconnecte;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbase {
    
    private static String url = "jdbc:mysql://localhost/gestio";
    private static String user = "root";
    private static String password = "";
    private static Connection connect;

    public static Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
            return connect;
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée; Erreur : " + e);
        }
        return null;
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 *  @author Ghofrane
 */
public class MyDB {

    public static Object getConnexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    String url = "jdbc:mysql://localhost:3306/fixit";
    String login = "root";
    String password = "";
    private Connection connection;
    private static MyDB insatance = null;

    private MyDB() {
        try {
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MyDB getInsatance() {
        if (insatance == null) {
            insatance = new MyDB();
        }
        return insatance;
    }

    public PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
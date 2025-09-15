
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Connection getConnection(){
        try {
            String myBD = "jdbc:mysql://localhost:3306/biblioteca?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "");
            return con;
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
}

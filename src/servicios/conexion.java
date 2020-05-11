/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

/**
 *
 * @author Victor
 */
import java.sql.*;

public class conexion {
   public static Connection conn = null;
   public static Connection obtener() throws SQLException, ClassNotFoundException {
      if (conn == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bancoRMI", "root", "");
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      
      return conn;
   }
   public static void cerrar() throws SQLException {
      if (conn != null) {
         conn.close();
      }
   }
}

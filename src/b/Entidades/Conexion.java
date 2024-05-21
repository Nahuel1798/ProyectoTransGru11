package b.Entidades;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Nahue
 */
public class Conexion {
    private static String CLASS = "org.mariadb.jdbc.Driver";
    private static String url="jdbc:mariadb://localhost/siglo21university";
    private static String usuario="root";
    private static String password="";


    private static Conexion conexion=null;

     private Conexion() {
        try {
            //1)Cargar Drivers.
            Class.forName(CLASS);

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error al cargar Driver");
        }
    }
    public static Connection getConexion() {
        Connection con=null;
      if(conexion == null){

           conexion= new Conexion();
        }
        try {
            //2)Establecer la Conexion.
            con = (Connection) DriverManager.getConnection(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + usuario + "&password=" + password);

        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion ");
        }

        return con;
    }
}

package c.AccesoDatos;

import b.Entidades.Conexion;
import b.Entidades.alumno;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Nahue
 */
public class alumnoData {
    private Connection con = null;
    
    public alumnoData() {
        con = (Connection) Conexion.getConexion();
    }
    
    public void guardarAlumno(alumno alumnos){
        String sql= "INSERT INTO alumno(Dni,Apellido,Nombre,fechaNacimiento,Estado VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumnos.getDni());
            ps.setString(2, alumnos.getApellido());
            ps.setString(3, alumnos.getNombre());
            ps.setDate(4, Date.valueOf(alumnos.getFechaNacimiento()));//localDate a Date
            ps.setBoolean(5, alumnos.isEstado()); // if reducido
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumnos.setIdAlumno(rs.getInt("idAlumno"));
            JOptionPane.showMessageDialog(null, "Alumno añadido con exito."); 
            }
            ps.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno"+ex.getMessage()); 
        }
    }
    
}

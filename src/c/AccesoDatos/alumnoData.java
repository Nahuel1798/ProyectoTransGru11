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

    public void guardarAlumno(alumno alumnos) {
        String sql = "INSERT INTO alumno(Dni,Apellido,Nombre,fechaNacimiento,Estado VALUES (?,?,?,?,?)";
        try {
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno" + ex.getMessage());
        }
    }

    public alumno buscarAlumnoPorDni(int dni) {
        alumno alumnos = null;
        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE dni=? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumnos = new alumno();
                alumnos.setIdAlumno(rs.getInt("idAlumno"));
                alumnos.setDni(rs.getInt("dni"));
                alumnos.setApellido(rs.getString("apellido"));
                alumnos.setNombre(rs.getString("nombre"));
                alumnos.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumnos.setEstado(true);

            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno " + ex.getMessage());
        }
        return alumnos;

    }

}

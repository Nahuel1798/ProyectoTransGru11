package c.AccesoDatos;

import b.Entidades.Conexion;
import b.Entidades.alumno;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
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
        String sql = "INSERT INTO alumno(idAlumno,Dni,Apellido,Nombre,fechaNacimiento,Estado) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumnos.getIdAlumno());
            ps.setInt(2, alumnos.getDni());
            ps.setString(3, alumnos.getApellido());
            ps.setString(4, alumnos.getNombre());
            ps.setDate(5, Date.valueOf(alumnos.getFechaNacimiento()));//localDate a Date
            ps.setBoolean(6, alumnos.isEstado()); // if reducido
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

    public List<alumno> listarAlumnos() {

        List<alumno> alumnos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM alumno WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            alumno Alumnos;
            while (rs.next()) {
                Alumnos = new alumno(); 

                Alumnos.setIdAlumno(rs.getInt("idAlumno"));
                Alumnos.setDni(rs.getInt("dni"));
                Alumnos.setApellido(rs.getString("apellido"));
                Alumnos.setNombre(rs.getString("nombre"));
                Alumnos.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                Alumnos.setEstado(rs.getBoolean("estado"));
                alumnos.add(Alumnos);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno " + ex.getMessage());
        }
        return alumnos;
    }
    
    public void modificarAlumno(alumno alum){
        String sql = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento = ? WHERE idAlumno = ?";
        PreparedStatement ps= null;
        
        try{
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, alum.getDni());
            ps.setString(2, alum.getApellido());
            ps.setString(3, alum.getNombre());
            ps.setDate(4, Date.valueOf(alum.getFechaNacimiento()));
            ps.setInt(5, alum.getIdAlumno());
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente");
                
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no existe");
            }
            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno " + ex.getMessage());
        }
        
    }
    
    public boolean eliminarAlumno(int id){
        boolean resultado = true;
        try {
            String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            
            if (fila == 1){
                JOptionPane.showMessageDialog(null, "Se elimino el alumno");
            }
            ps.close();
        } catch (SQLException e) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
        }
        return resultado;
        
    }
    
        public alumno buscarAlumno(int id) {
        alumno alumnos = null;
        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE idAlumno = ? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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




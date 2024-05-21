package c.AccesoDatos;

import b.Entidades.Conexion;
import b.Entidades.materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class materiaData {
    
    private Connection con = null;

    public materiaData() {
        con = (Connection) Conexion.getConexion();
    }
    
    public void guardarMateria(materia materias){
        String sql = "INSERT INTO materia(idMateria, Nombre, Año, Estado) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, materias.getIdMateria());
            ps.setString(2, materias.getNombre());
            ps.setInt(3, materias.getAño());       
            ps.setBoolean(4, materias.isEstado()); 
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materias.setIdMateria(rs.getInt("idMateria"));
                JOptionPane.showMessageDialog(null, "Materia añadida con exito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia" + ex.getMessage());
        }
    }
    
    
    public materia buscarMateria(int id){
        materia materias = null;
        String sql = "SELECT idMateria, Nombre, Año, Estado FROM materia WHERE idMateria = ? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                materias = new materia();
                materias.setIdMateria(rs.getInt("idMateria"));
                materias.setNombre(rs.getString("Nombre"));
                materias.setAño(rs.getInt("Año"));
                materias.setEstado(true);

            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia " + ex.getMessage());
        }
        return materias;
        
    }
    
    public void modificarMateria(materia materias){
        
        String sql = "UPDATE materia SET Nombre = ? , Año = ? WHERE idMateria = ?";
        PreparedStatement ps= null;
        
        try{
            
            ps = con.prepareStatement(sql);
            ps.setString(1, materias.getNombre());
            ps.setInt(2, materias.getAño());
            ps.setInt(3, materias.getIdMateria());
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Materia modificada exitosamente");
                
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }
            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia " + ex.getMessage());
        }
        
    }
    
    public void eliminarMateria(int id){
        
        try {
            String sql = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            
            if (fila == 1){
                JOptionPane.showMessageDialog(null, "Se elimino la materia");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
        }
        
    }
    
    public List<materia> listarMaterias() {

        List<materia> materias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM materia WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            materia Materias;
            while (rs.next()) {
                Materias = new materia(); 

                Materias.setIdMateria(rs.getInt("idMateria"));
                Materias.setNombre(rs.getString("Nombre"));
                Materias.setAño(rs.getInt("Año"));
                Materias.setEstado(rs.getBoolean("Estado"));
                materias.add(Materias);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia " + ex.getMessage());
        }
        return materias;
    }
    
}

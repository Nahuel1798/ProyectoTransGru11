package c.AccesoDatos;

import b.Entidades.materia;
import b.Entidades.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;

public class materiaData {

    private Conexion con = null;

    public materiaData() {
        con = (Conexion) Conexion.getConexion();
    }

    public void guardarMateria(materia Materia) {
        String sql = "INSERT INTO materia (nombre, año, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Materia.getNombre());
            ps.setInt(2, Materia.getAño());
            ps.setBoolean(3, Materia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Materia.setIdMateria(rs.getInt("idMateria"));
                JOptionPane.showMessageDialog(null, "Materia añadida con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia" + ex.getMessage());
        }
    }

}

package c.AccesoDatos;

import b.Entidades.Conexion;
import b.Entidades.alumno;
import b.Entidades.inscripcion;
import b.Entidades.materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

public class inscripcionData {

    private Connection con = null;
    private materiaData md = new materiaData();
    private alumnoData ad = new alumnoData();

    public inscripcionData() {
        con = (Connection) Conexion.getConexion();
    }

        public void guardarInscripcion(inscripcion insc) {

            String sql = "INSERT INTO inscripcion(idIncripto,idAlumno,idMateria,Nota) VALUES (?,?,?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, insc.getIdIncripto());
                ps.setInt(2, insc.getAlumno().getIdAlumno());
                ps.setInt(3, insc.getMateria().getIdMateria());
                ps.setInt(4, insc.getNota());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    insc.setIdIncripto(rs.getInt("idIncripto"));
                    JOptionPane.showMessageDialog(null, "Inscripcion añadida con exito.");
                }
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
            }


    }

        public void actualizarNota(int idAlumno, int idMateria, int Nota) {

            String sql = "UPDATE inscripcion SET Nota = ?  WHERE idAlumno = ? and idMateria = ?";
            PreparedStatement ps = null;

            try {

                ps = con.prepareStatement(sql);
                ps.setInt(1, Nota);
                ps.setInt(2, idAlumno);
                ps.setInt(3, idMateria);   
                int exito = ps.executeUpdate();

                if (exito == 1) {
                    JOptionPane.showMessageDialog(null, "Nota modificada exitosamente");

                } else {
                    JOptionPane.showMessageDialog(null, "La materia o el alumno no existe");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + ex.getMessage());
            }
        }

        public void borrarInscripcionMA(int idAlumno, int idMateria){
        
        try {
            String sql = "DELETE FROM inscripcion WHERE idAlumno = ? and idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int fila = ps.executeUpdate();
            
            if (fila == 1){
                JOptionPane.showMessageDialog(null, "Se elimino la inscripcion");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
  
    }

    
        public List<inscripcion> obtenerInscripciones() {

        ArrayList<inscripcion> cursadas = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM inscripcion";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {              
                
                inscripcion insc = new inscripcion(); 
                
                insc.setIdIncripto(rs.getInt("idIncripto"));
                
                alumno alum = ad.buscarAlumno(rs.getInt("idAlumno"));
                materia mat = md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alum);
                insc.setMateria(mat);
                insc.setNota(rs.getInt("Nota"));
                cursadas.add(insc);
  
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia " + ex.getMessage());
        }
        return cursadas;
    }
        
        
        public List<inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {

        ArrayList<inscripcion> cursadas = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {              
                
                inscripcion insc = new inscripcion(); 
                
                insc.setIdIncripto(rs.getInt("idIncripto"));
                
                alumno alum = ad.buscarAlumno(rs.getInt("idAlumno"));
                materia mat = md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alum);
                insc.setMateria(mat);
                insc.setNota(rs.getInt("Nota"));
                cursadas.add(insc);
  
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia " + ex.getMessage());
        }
        return cursadas;
    }
    
     
        public List<materia> obtenerMateriasCursadas(int idAlumno){
            
            ArrayList<materia> materias = new ArrayList<>();
            
            String sql = "SELECT inscripcion.idMateria, Nombre, Año FROM inscripcion,"
                             +" materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ?";
                                
            try{
                
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, idAlumno);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
                    materia mat = new materia();
                    
                    mat.setIdMateria(rs.getInt("idMateria"));
                    mat.setNombre(rs.getString("Nombre"));
                    mat.setAño(rs.getInt("Año"));
                    materias.add(mat);
               
                }
                ps.close();
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia " + ex.getMessage());
            }
            
          return materias;             
            
        } 
        
         
        public List<materia> obtenerMateriasNOCursadas(int idAlumno){
         
            ArrayList<materia> materias = new ArrayList<>();
            
            String sql = "SELECT *  FROM materia WHERE estado = 1 AND" 
                    + "idMateria not in (SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";
            
            try{
                
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, idAlumno);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
                    materia mat = new materia();
                    
                    mat.setIdMateria(rs.getInt("idMateria"));
                    mat.setNombre(rs.getString("Nombre"));
                    mat.setAño(rs.getInt("Año"));
                    materias.add(mat);
               
                }
                ps.close();
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia " + ex.getMessage());
            }
            
            return materias; 
        }
        
        public List<alumno> obtenerAlumnosXMateria(int idMateria){
            
            ArrayList<alumno> alumnosMateria = new ArrayList<>();
            
            String sql = "SELECT a.idAlumno, Dni, Nombre, Apellido, fechaNacimiento, Estado " +
                    " FROM inscripcion i, alumno a  WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.Estado = 1";            
            
            try{
                
                PreparedStatement ps = con.prepareCall(sql);
                ps.setInt(1, idMateria);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
                    alumno alum = new alumno();
                    
                    alum.setIdAlumno(rs.getInt("idAlumno"));
                    alum.setApellido(rs.getString("Apellido"));
                    alum.setNombre(rs.getString("Nombre"));
                    alum.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                    alum.setEstado(rs.getBoolean("Estado"));
                    alumnosMateria.add(alum);
                    
               
                }
                ps.close();
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumnos " + ex.getMessage());
            }
            
            return alumnosMateria;
            
        }
        
        
}

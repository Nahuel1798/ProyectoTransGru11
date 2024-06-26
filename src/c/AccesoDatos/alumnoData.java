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

    private Connection connection;

    public alumnoData() {
        this.connection = Conexion.getConexion();
    }

    public boolean guardarAlumno(alumno alumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la sentencia SQL
            String sql;
            if (alumno.getIdAlumno() == -1) {
                sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";      // El id se establece por codigo
            } else {
                sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado, idAlumno) VALUES (?, ?, ?, ?, ?, ?)";     // El id es establecido por el gestor de BD
            }

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());

            // Si idAlumno == -1, entonces se asigna automaticamente
            if (alumno.getIdAlumno() != -1) {
                ps.setInt(6, alumno.getIdAlumno());
            }

            // Ejecutar la sentencia SQL
            int filas = ps.executeUpdate();

            // Comunicar resultado por consola
            if (filas > 0) {
                System.out.println("Alumno agregado");
            } else {
                result = false;
                System.out.println("No se pudo agregar al alumno");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            int errorCode = e.getErrorCode();
            if (errorCode == 1062) {    // Alumno repetido
                System.out.println("[Error " + errorCode + "] (Alumno repetido)");
            } else {
                System.out.println("[Error " + errorCode + "]");
            }
            e.printStackTrace();

        }
        return result;

    }

    public alumno buscarAlumno(int idAlumno) {
        alumno alumno = null;
        try {
            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno WHERE idAlumno=?;";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);

            // Ejecutar sentencia SQL
            ResultSet rs = ps.executeQuery();

            /* Si se encontro al alumno, crear nuevo objeto de tipo Alumno con los datos obtenidos 
               y, en todo caso, comunicar el resultado por consola */
            if (rs.next()) {
                alumno = new alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));

                System.out.println("Se encontró al alumno");
            } else {
                System.out.println("No se encontró al alumno");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }

        return alumno;
    }

    public alumno buscarAlumnoSegunEstado(int idAlumno, boolean estado) {
        alumno alumno = null;
        try {
            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno WHERE idAlumno=? AND estado=?;";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setBoolean(2, estado);
            // Ejecutar sentencia SQL
            ResultSet rs = ps.executeQuery();

            /* Si se encontró al alumno, crear nuevo objeto de tipo Alumno con los datos obtenidos 
               y, en todo caso, comunicar el resultado por consola */
            if (rs.next()) {
                alumno = new alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(estado);

                System.out.println("Se encontró al alumno");
            } else {
                System.out.println("No se encontró al alumno");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] ");
            e.printStackTrace();
        }

        return alumno;
    }    

    public alumno buscarAlumnoPorDni(int dni) {
        /*
         * El proposito de esta funcion es poder buscarAlumnos con dni, como
         * idAlumno y dni son enteros la funcion no se puede sobrecargar.
         */
        alumno alumno = null;

        try {
            // Preparar la estructura de la sentencia SQL
            String sql = "SELECT * FROM alumno WHERE dni=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dni);

            // Ejecutar la sentencia SQL
            ResultSet rs = ps.executeQuery();

            /* Si se encontro al alumno, crear nuevo objeto de tipo Alumno con los datos obtenidos 
               y, en todo caso, comunicar el resultado por consola */
            if (rs.next()) {
                alumno = new alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));

                System.out.println("Se encontró al alumno");
            } else {
                System.out.println("No se encontró al alumno");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] ");
            e.printStackTrace();
        }

        return alumno;
    }

    public alumno buscarAlumnoPorDniSegunEstado(int dni, boolean estado) {
        /*
         * El proposito de esta funcion es poder buscarAlumnos con dni, como
         * idAlumno y dni son enteros la funcion no se puede sobrecargar.
         */
        alumno alumno = null;

        try {
            // Preparar la estructura de la sentencia SQL
            String sql = "SELECT * FROM alumno WHERE dni=? AND estado=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dni);

            // Ejecutar la sentencia SQL
            ResultSet rs = ps.executeQuery();

            /* Si se encontro al alumno, crear nuevo objeto de tipo Alumno con los datos obtenidos 
               y, en todo caso, comunicar el resultado por consola */
            if (rs.next()) {
                alumno = new alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(dni);
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(estado);
                System.out.println("Se encontró al alumno");
            } else {
                System.out.println("No se encontró al alumno");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] ");
            e.printStackTrace();
        }

        return alumno;
    }    

    public List<alumno> listarAlumnos() {
        List<alumno> alumnos = new ArrayList();

        try {
            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno;";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);

            // Ejecutar sentencia SQL
            ResultSet rs = ps.executeQuery();

            // Si se encontro uno o mas alumnos, crear objetos de tipo Alumno con los datos obtenidos y añadirlos a 'alumnos'
            alumno alumno;
            while (rs.next()) {
                alumno = new alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }                        

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "] ");
            e.printStackTrace();
        }

        return alumnos;
    }

    public List<alumno> listarAlumnosSegunEstado(boolean estado) {
        List<alumno> Alumnos = new ArrayList();

        try {
            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno WHERE estado=?;";

            // Prepared Statement            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, estado);

            // Ejecutar sentencia SQL
            ResultSet rs = ps.executeQuery();

            // Si se encontro uno o mas alumnos, crear objetos de tipo Alumno con los datos obtenidos y añadirlos a 'alumnos'
            alumno alumnos;
            while (rs.next()) {
                alumnos = new alumno();
                alumnos.setIdAlumno(rs.getInt("idAlumno"));
                alumnos.setDni(rs.getInt("dni"));
                alumnos.setApellido(rs.getString("apellido"));
                alumnos.setNombre(rs.getString("nombre"));
                alumnos.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumnos.setEstado(estado);
                Alumnos.add(alumnos);
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }

        return Alumnos;
    }    

    public boolean modificarAlumno(alumno alumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la sentencia SQL
            String sql = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?, estado=? WHERE idAlumno=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());

            // Ejecutar sentencia SQL
            int filas = ps.executeUpdate();

            // Comunicar resultado por consola
            if (filas > 0) { 
                System.out.println("Alumno modificado");
            } else { 
                result = false;
                System.out.println("No se pudo modificar al alumno indicado");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }

        return result;
    }

    // Borrado lógico
    public boolean eliminarAlumno(int idAlumno) {
        boolean result = true;

        try {
            // Preparar la estructura de la sentencia SQL
            String sql = "UPDATE alumno SET estado=false WHERE idAlumno=?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idAlumno);

            // Ejecutar la sentencia SQL
            int filas = ps.executeUpdate();

            // Comunicar resultado por consola
            if (filas > 0) { 
                System.out.println("Alumno dado de baja");
            } else { 
                result = false;
                System.out.println("No se pudo dar de baja al alumno");
            }

            // Cerrar el preparedStatement
            ps.close();

        } catch (SQLException e) {
            result = false;
            System.out.println("[Error " + e.getErrorCode() + "]");
            e.printStackTrace();
        }

        return result;
    }
}




package a.Entregas;

import b.Entidades.Conexion;
import b.Entidades.alumno;
import c.AccesoDatos.alumnoData;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;


/**
 *
 * @author Nahue
 */
public class Main {

    public static void main(String[] args) {
        // Establecer la conexion
//        Connection con = (Connection) Conexion.getConexion();
        
        
        //Creando alumnoData
        alumnoData alumno = new alumnoData();
        
        //Creando alumnos
        alumno alumno1 = new alumno(1,42207510,"Nahuel","Stampanoni",LocalDate.of(2000, Month.MARCH, 15),true);
        alumno alumno2 = new alumno(2,42127510,"Diego","Perri",LocalDate.of(1930, Month.APRIL, 20),true);
        alumno alumno3 = new alumno(3,41207900,"Nicolas","Lucero",LocalDate.of(1935, Month.FEBRUARY, 5),true);
        
        alumno alumnos[] = new alumno[]{alumno1,alumno2,alumno3};
        
        //Guardando alumnos
        System.out.println("Guardar alumno");
        for(alumno alum : alumnos){
            alumno.guardarAlumno(alum);
        }
        
        //Buscando alumno por DNI
        int encontrado = 42207510;          
        alumno alumnoEncontrado = alumno.buscarAlumnoPorDni(encontrado);              
        if(alumnoEncontrado != null){
            System.out.println("El alumno encontrado es: " + alumnoEncontrado);
        }else{
            System.out.println("El alumno no se ha encontrado");
        }   
        
        //Listar alumnos       
        List<alumno> lista = alumno.listarAlumnos();    
        for(alumno alum : lista){
            System.out.println(alum);
            System.out.println("");
        }
        
        //Modificando alumno
        int alumnoAModificar = 2;   
        alumno alumnoModificado;        
        alumnoModificado = new alumno(alumnoAModificar,32128510, "Gaspar", "Pascual", LocalDate.of(1945, Month.SEPTEMBER, 25),true);        
        alumno.modificarAlumno(alumnoModificado);
        
        
        //Eliminar un alumno
        int alumnoAEliminar = 3;                         
        alumno.eliminarAlumno(alumnoAEliminar);
               
    }
     
}

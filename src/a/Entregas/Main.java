package a.Entregas;

import b.Entidades.Conexion;
import b.Entidades.alumno;
import b.Entidades.inscripcion;
import b.Entidades.materia;
import c.AccesoDatos.alumnoData;
import c.AccesoDatos.inscripcionData;
import c.AccesoDatos.materiaData;
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

        //Establecer la conexion
        //Connection con = (Connection) Conexion.getConexion();
        
        //Creando alumnoData (Anda todo ok)
        alumnoData alumno = new alumnoData();

        //Creando alumnos (Anda todo ok)
//        alumno alumno1 = new alumno(1,42207510,"Nahuel","Stampanoni",LocalDate.of(2000, Month.MARCH, 15),true);
//        alumno alumno2 = new alumno(2,42127510,"Diego","Perri",LocalDate.of(1930, Month.APRIL, 20),true);
//        alumno alumno3 = new alumno(3,41207900,"Nicolas","Lucero",LocalDate.of(1935, Month.FEBRUARY, 5),true);
//        alumno alumnos[] = new alumno[]{alumno1,alumno2,alumno3};

        //Guardando alumnos (Anda todo ok)
//        System.out.println("Guardar alumno");
//        for(alumno alum : alumnos){
//            alumno.guardarAlumno(alum);
//        }

        //Buscando alumno por DNI (Anda todo ok)
//        int encontrado = 42207510;          
//        alumno alumnoEncontrado = alumno.buscarAlumnoPorDni(encontrado);              
//        if(alumnoEncontrado != null){
//            System.out.println("El alumno encontrado es: " + alumnoEncontrado);
//        }else{
//            System.out.println("El alumno no se ha encontrado");
//        }  

        //Listar alumnos  (Anda todo ok)      
//        List<alumno> lista = alumno.listarAlumnos();    
//        for(alumno alum : lista){
//            System.out.println(alum);
//            System.out.println("");
//        }

        //Modificando alumno (Anda todo ok)
//        int alumnoAModificar = 2;   
//        alumno alumnoModificado;        
//        alumnoModificado = new alumno(alumnoAModificar,32128510, "Gaspar", "Pascual", LocalDate.of(1945, Month.SEPTEMBER, 25),true);        
//        alumno.modificarAlumno(alumnoModificado);

        //Eliminar un alumno (Anda todo ok)
//        int alumnoAEliminar = 3;                         
//        alumno.eliminarAlumno(alumnoAEliminar);

        //Creando materiaData (Anda todo ok)
        materiaData materia = new materiaData();

        //Creando materias (Anda todo ok)
//        materia materia1 = new materia(1,"Ciencias Naturales",2020,true);
//        materia materia2 = new materia(2,"Ingenieria en Software",2012,true);
//        materia materia3 = new materia(3,"Matematicas 1",2018,true);
//        materia materia4 = new materia(4,"Programacion 2",2016,false);
//        materia materia5 = new materia(5,"Programacion 1",2021,true);
//        
//        
//        materia materias[] = new materia[]{materia1,materia2,materia3,materia4,materia5};

        //Guardando materias (Anda todo ok)
//        System.out.println("Guardar materia");
//        for(materia mate : materias){
//            materia.guardarMateria(mate);
//        }

        //Buscando materia por id (Anda todo ok)
//        int encontrada = 2;          
//        materia materiaEncontrada = materia.buscarMateria(encontrada);              
//        if(materiaEncontrada != null){
//            System.out.println("La materia encontrada es: " + materiaEncontrada);
//        }else{
//            System.out.println("La materia no se ha encontrado");
//        } 

        //Listar materias  (Estado = true) (Anda todo ok)   
//          List<materia> lista = materia.listarMaterias();    
//             for(materia mate : lista){
//                System.out.println(mate);
//                 System.out.println("");
//            }

        //Modificando materia (Anda todo ok)
//       int materiaAModificar = 3;   
//       materia materiaModificada;        
//       materiaModificada = new materia(materiaAModificar, "Matematica Discreta",2019,true);        
//       materia.modificarMateria(materiaModificada);

        //Eliminar una materia (Anda todo ok)
//          int materiaAEliminar = 1;                         
//          materia.eliminarMateria(materiaAEliminar);

        //Creando inscripcionData (Anda todo Ok)
        inscripcionData inscData = new inscripcionData();

        
//            alumno stampa = alumno.buscarAlumnoPorDni(42207510);
//            materia mate = materia.buscarMateria(2);
//            inscripcion insc = new inscripcion(1,stampa,mate,8);
            
        //Guardando Inscripcion (Anda todo ok)
//            inscData.guardarInscripcion(insc);

        //Actualizando Nota (Anda todo ok)
        //    inscData.actualizarNota(1, 2, 9);
        
        //Borrando inscripcion (Anda todo ok)
        //    inscData.borrarInscripcionMA(1, 2);
        
        //Listando las inscripciones  (Anda todo ok)
//        for(inscripcion inscripciones : inscData.obtenerInscripciones()){
//            
//            System.out.println("id: " + inscripciones.getIdIncripto());
//            System.out.println("Apellido: " + inscripciones.getAlumno().getNombre()); //Pido el nombre porque esta al reves (donde esta el nombre puse el apellido xd)
//            System.out.println("Materia: " + inscripciones.getMateria().getNombre());
//            
//        }


        for(materia materias:inscData.obtenerMateriasNOCursadas(1)){
            System.out.println("nombre " + materias.getNombre());
        }
        
        
        
        
        
        
        
    }

}

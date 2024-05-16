
package b.Entidades;

import java.time.LocalDate;

/**
 *
 * @author Nahue
 */
public class alumno {
    private int idAlumno;
    private int Dni;
    private String Apellido;
    private String Nombre;
    private LocalDate fechaNacimiento;
    private boolean Estado;

    
    

    
    public alumno(int idAlumno, int Dni, String Apellido, String Nombre, LocalDate fechaNacimiento, boolean Estado) {
        this.idAlumno = idAlumno;
        this.Dni = Dni;
        this.Apellido = Apellido;
        this.Nombre = Nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.Estado = Estado;
    }
    
     public alumno(int Dni, String Apellido, String Nombre, LocalDate fechaNacimiento, boolean Estado) {    
        this.Dni = Dni;
        this.Apellido = Apellido;
        this.Nombre = Nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.Estado = Estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getDni() {
        return Dni;
    }

    public void setDni(int Dni) {
        this.Dni = Dni;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    
}

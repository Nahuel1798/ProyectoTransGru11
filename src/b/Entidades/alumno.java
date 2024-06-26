
package b.Entidades;

import java.time.LocalDate;

public class alumno {

    private int idAlumno;
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private boolean estado;

    public alumno() {
    }

    public alumno(int dni, String nombre, String apellido, LocalDate fechaNacimiento, boolean estado) {
        this.idAlumno = -1;     // Luego se utilizara para denotar que el verdadero id debe ser establecido por el gestor de BD
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public alumno(int idAlumno, int dni, String nombre, String apellido, LocalDate fechaNacimiento, boolean estado) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Se va a utilizar en la muestra de datos en las vistas
    @Override
    public String toString() {
        return dni + ", " + nombre + ", " + apellido;
    }
    
    // Se va a utilizar para la muestra de datos por consola
    public String mostrarAlumno(){
        return "Alumno{" + "idAlumno=" + idAlumno + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + '}';
    }
    
    
}

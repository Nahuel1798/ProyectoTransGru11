
package b.Entidades;

/**
 *
 * @author Nahue
 */
public class inscripcion {
    private int idIncripcion;
    private String Nombre;
    private int Año;
    private boolean Estado;

    public inscripcion() {
    }

    public inscripcion(String Nombre, int Año, boolean Estado) {
        this.Nombre = Nombre;
        this.Año = Año;
        this.Estado = Estado;
    }

    public inscripcion(int idIncripcion, String Nombre, int Año, boolean Estado) {
        this.idIncripcion = idIncripcion;
        this.Nombre = Nombre;
        this.Año = Año;
        this.Estado = Estado;
    }

    public int getIdIncripcion() {
        return idIncripcion;
    }

    public void setIdIncripcion(int idIncripcion) {
        this.idIncripcion = idIncripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    
}

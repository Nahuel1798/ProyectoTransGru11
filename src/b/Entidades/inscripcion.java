
package b.Entidades;

/**
 *
 * @author Nahue
 */
public class inscripcion {
    private int idMateria;
    private String Nombre;
    private int Año;
    private boolean Estado;

    public inscripcion(int idMateria, String Nombre, int Año, boolean Estado) {
        this.idMateria = idMateria;
        this.Nombre = Nombre;
        this.Año = Año;
        this.Estado = Estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
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

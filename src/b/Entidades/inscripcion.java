
package b.Entidades;

/**
 *
 * @author Nahue
 */
public class inscripcion {
    private int idIncripto;
    private alumno alumno;
    private materia materia;
    private int Nota;

    public inscripcion() {
    }

    public inscripcion(int idIncripto, alumno alumno, materia materia, int nota) {
        this.idIncripto = idIncripto;
        this.alumno = alumno;
        this.materia = materia;
        this.Nota = nota;
    }

    public inscripcion(alumno alumno, materia materia, int nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.Nota = nota;
    }

    public int getIdIncripto() {
        return idIncripto;
    }

    public void setIdIncripto(int idIncripto) {
        this.idIncripto = idIncripto;
    }

    public alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(alumno alumno) {
        this.alumno = alumno;
    }

    public materia getMateria() {
        return materia;
    }

    public void setMateria(materia materia) {
        this.materia = materia;
    }

    public int getNota() {
        return Nota;
    }

    public void setNota(int nota) {
        this.Nota = nota;
    }

    @Override
    public String toString() {
        return "idIncripto: " + idIncripto + " idAlumno: " + alumno + " idMateria: " + materia + " Nota: " + Nota;
    }
        
}

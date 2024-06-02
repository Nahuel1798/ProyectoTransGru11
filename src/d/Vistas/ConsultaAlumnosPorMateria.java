package d.Vistas;

import b.Entidades.alumno;
import b.Entidades.materia;
import c.AccesoDatos.inscripcionData;
import c.AccesoDatos.materiaData;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Grupo-3
 */
public class ConsultaAlumnosPorMateria extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fil, int col) {
            return false;
        }
    };
    private final materiaData materiaData;
    private final inscripcionData inscripcionData;

    /**
     * Creates new form ConsultaAlumnosMateria
     */
    public ConsultaAlumnosPorMateria(materiaData materiaData, inscripcionData inscripcionData) {
        initComponents();
        this.modelo = (DefaultTableModel) jtAlumPorMat.getModel();
        this.materiaData = materiaData;
        this.inscripcionData = inscripcionData;
        armarCabeceraTabla();
    }

    private void armarCabeceraTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");
        jtAlumPorMat.setModel(modelo);
    }

    private void configComboBox() {

        // Eliminar todos los items de 'jcbMatSeleccion'
        jcbMatSeleccion.removeAllItems();

        // Obtener todas las materias
        List<materia> listaMaterias = materiaData.listarMateriasSegunEstado(true);

        if (listaMaterias.isEmpty()) {    // Si no hay materias 

            // Advertir al usuario
            JOptionPane.showMessageDialog(this, "No hay ninguna materia.", "Advertencia", JOptionPane.WARNING_MESSAGE);

            jcbMatSeleccion.setSelectedIndex(-1);     // Se requiere para provocar la ejecución de 'jcbMatSeleccionActionPerformed()' cuando se ejecuta este método por primera vez luego de crear 'ConsultaAlumnosPorMateria'

        } else {    // Si hay al menos una materia

            // Se cargan las materias en 'jcbMatSeleccion'
            for (materia materia : listaMaterias) {
                jcbMatSeleccion.addItem(materia);
            }
        }
    }

    private void adminConsAlumPorMat() {

        // Borrar las filas de 'jtAlumPorMat'
        modelo.setRowCount(0);

        if (jcbMatSeleccion.getSelectedIndex() != -1) {     // Existe al menos una materia (y, por ende, jcbMatSeleccion no está vacío)

            // Obtener el item seleccionado en 'jcbMatSeleccion' y obtener los alumnos que están vinculados a la misma mediante inscripciones
            materia materia = (materia) jcbMatSeleccion.getSelectedItem();
            List<alumno> listaAlumnos = inscripcionData.obtenerAlumnoXMateria(materia.getIdMateria());

            if (listaAlumnos.isEmpty()) {   // Si ningún alumno está inscripto en la materia

                // Advertir al usuario
                JOptionPane.showMessageDialog(this, "No hay alumnos inscriptos en esta materia.", "Advertencia", JOptionPane.WARNING_MESSAGE);

            } else {    // Si al menos un alumno está incripto en la materia

                // Mostrar los alumnos en 'jtAlumPorMat' 
                for (alumno alum : listaAlumnos) {
                    modelo.addRow(new Object[]{alum.getIdAlumno(), alum.getDni(), alum.getApellido(), alum.getNombre()});
                }
            }
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitulo = new javax.swing.JLabel();
        jlMatSeleccion = new javax.swing.JLabel();
        jcbMatSeleccion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAlumPorMat = new javax.swing.JTable();
        jbSalir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Alumnos por Materia");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jlTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitulo.setText("Listado de Alumnos por Materia");

        jlMatSeleccion.setText("Materia Seleccionada:");

        jcbMatSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMatSeleccionActionPerformed(evt);
            }
        });

        jtAlumPorMat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtAlumPorMat);

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jlMatSeleccion)
                        .addGap(18, 18, 18)
                        .addComponent(jcbMatSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbSalir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMatSeleccion)
                    .addComponent(jcbMatSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbSalir)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        configComboBox();
    }//GEN-LAST:event_formComponentShown

    private void jcbMatSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMatSeleccionActionPerformed
        adminConsAlumPorMat();
    }//GEN-LAST:event_jcbMatSeleccionActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.hide();
    }//GEN-LAST:event_jbSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<materia> jcbMatSeleccion;
    private javax.swing.JLabel jlMatSeleccion;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTable jtAlumPorMat;
    // End of variables declaration//GEN-END:variables
}

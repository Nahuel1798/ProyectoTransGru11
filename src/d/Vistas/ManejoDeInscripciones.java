/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package d.Vistas;

import b.Entidades.alumno;
import b.Entidades.inscripcion;
import b.Entidades.materia;
import c.AccesoDatos.alumnoData;
import c.AccesoDatos.inscripcionData;
import c.AccesoDatos.materiaData;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ManejoDeInscripciones extends javax.swing.JInternalFrame {
    
    private inscripcionData InscripcionData;
    private materiaData matData;
    private alumnoData alumData;
    private DefaultTableModel Boquita = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }
    };
    
    /**
     * Creates new form Inscripcion
     */
    public ManejoDeInscripciones(inscripcionData InscripcionData, materiaData matData, alumnoData alumData) {
        initComponents();
        this.InscripcionData = InscripcionData;
        this.alumData = alumData;
        this.matData = matData;
        armarCabecera();
    }
    private void armarCabecera(){
        Boquita.addColumn("ID");
        Boquita.addColumn("Nombre");
        Boquita.addColumn("Año");
        jtTabla.setModel(Boquita);
    }
    
    private void configurarCombo(){
        List<alumno> listaAlumno = alumData.listarAlumnos();
        jcCombo.removeAllItems();
        if (listaAlumno.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alumnos a los cuales inscribir o desinscribir en materias.");
            jcCombo.setSelectedIndex(-1);
        }else{
//            for (alumno alum : listaAlumno) {
//                jcCombo.addItem(alum);
//            }
        }
    }
    public void adminDesincipcion(){
        Boquita.setRowCount(0);
        if (jcCombo.getSelectedIndex() != -1) {
            alumno alum = (alumno) jcCombo.getSelectedItem();
            List<materia> listaMateria = InscripcionData.obtenerMateriasCursadas(alum.getIdAlumno());
            
            if (listaMateria.isEmpty()) {
                jbAnularIns.setEnabled(false);
            }else{
                for (materia mat : listaMateria) {
                Boquita.addRow(new Object[]{mat.getIdMateria(),mat.getNombre(),mat.getAño()});
            }
            
            jbAnularIns.setEnabled(isIcon);
        }   
        }else{
            jbAnularIns.setEnabled(false);
        }
        jbIns.setEnabled(false);
    }
    
    private void adminInscripcion(){
        Boquita.setRowCount(0);
        
        if (jcCombo.getSelectedIndex() != -1) {
            alumno alum = (alumno) jcCombo.getSelectedItem();
            List<materia> listaMatNoCursada = InscripcionData.obtenerMateriasNOCursadas(alum.getIdAlumno());
            if (listaMatNoCursada.isEmpty()) {
                jbIns.setEnabled(false);
            }else{
                for (materia mat: listaMatNoCursada) {
                    Boquita.addRow(new Object[]{mat.getIdMateria(),mat.getNombre(),mat.getAño()});
                }
                jbIns.setEnabled(true);
            }
        }else{
            jbIns.setEnabled(false);
        }
        jbAnularIns.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jrMatIns = new javax.swing.JRadioButton();
        jrMatNoIns = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabla = new javax.swing.JTable();
        jbIns = new javax.swing.JButton();
        jbAnularIns = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("INSCRIPCION");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel1.setText("Inscripcion");

        jLabel6.setText("Seleccione un alumno:");

        jcCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcComboActionPerformed(evt);
            }
        });

        jLabel7.setText("Listado de Materias");

        jrMatIns.setText("Materias inscriptas");

        jrMatNoIns.setText("Materias no inscriptas");

        jtTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtTabla);

        jbIns.setText("Inscribir");
        jbIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsActionPerformed(evt);
            }
        });

        jbAnularIns.setText("Anular Inscripcion");
        jbAnularIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularInsActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jrMatIns)
                                        .addGap(86, 86, 86)
                                        .addComponent(jrMatNoIns))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(35, 35, 35)
                                        .addComponent(jcCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jbIns)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbAnularIns)
                .addGap(48, 48, 48)
                .addComponent(jbSalir)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrMatIns)
                    .addComponent(jrMatNoIns))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbIns)
                    .addComponent(jbAnularIns)
                    .addComponent(jbSalir))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcComboActionPerformed
        // TODO add your handling code here:
        
        if (jrMatIns.isSelected()) {
            adminDesincipcion();
        }else{
            adminInscripcion();
        }
    }//GEN-LAST:event_jcComboActionPerformed

    private void jbInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsActionPerformed
        // TODO add your handling code here:
        alumno alum = (alumno) jcCombo.getSelectedItem();
        
        switch (jtTabla.getSelectedRowCount()){
            case 0:
                JOptionPane.showMessageDialog(this, "Para inscribir al alumno en una materia primero debe seleccionar la misma en la lista, haciendole click");
                break;
            case 1:
                int filaSelec = jtTabla.getSelectedRow();
                int idMat = (int) jtTabla.getValueAt(filaSelec, 0);
                String nombreMat = (String) jtTabla.getValueAt(filaSelec, 1);
                int anio = (int) jtTabla.getValueAt(filaSelec, 2);
                materia mat = new materia(idMat,nombreMat,anio,true);
                
                inscripcion inscrip = new inscripcion(alum,mat,0);
                
                if (InscripcionData.guardarInscripcion(inscrip)) {
                    adminInscripcion();
                    JOptionPane.showMessageDialog(this, "se he inscripto al alumno ");
                }else{
                    JOptionPane.showMessageDialog(this, "no se he inscripto al alumno ");
                }
                break;
                
            default:
                JOptionPane.showMessageDialog(this, "debe seleccionar una sola materia de la lista ");
        }
    }//GEN-LAST:event_jbInsActionPerformed

    private void jbAnularInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularInsActionPerformed
        // TODO add your handling code here:
        alumno alum=(alumno) jcCombo.getSelectedItem();
        
        switch(jtTabla.getSelectedRowCount()){
            case 0:
                JOptionPane.showMessageDialog(this, "selecciones bien las filas");
                break;
            case 1:
                int filaSelec = jtTabla.getSelectedRow();
                String nombreMat = (String) jtTabla.getValueAt(filaSelec, 1);
                int idMat = (int) jtTabla.getValueAt(filaSelec, 0);
                
                if (InscripcionData.borrarInscripcionMA(alum.getIdAlumno(), idMat)) {
                    adminInscripcion();
                    JOptionPane.showMessageDialog(this, "se ha borrado la inscripcion del alumno");      
                }else{
                    JOptionPane.showMessageDialog(this, "no se ha podido borrar la inscripcion del alumno"); 
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "debe seleccionar solo una materia de la lista"); 
        }
    }//GEN-LAST:event_jbAnularInsActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        this.hide();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        configurarCombo();
        List<materia> listaMateria = matData.listarMaterias();
        if (listaMateria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay materia en la cuales inscribir o desincribir alumnos");
            System.out.println("no hay materias en la cuales inscribir o desinscribir alumnos");
        }
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAnularIns;
    private javax.swing.JButton jbIns;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<String> jcCombo;
    private javax.swing.JRadioButton jrMatIns;
    private javax.swing.JRadioButton jrMatNoIns;
    private javax.swing.JTable jtTabla;
    // End of variables declaration//GEN-END:variables
}

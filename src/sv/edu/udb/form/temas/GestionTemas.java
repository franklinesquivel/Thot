/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.form.temas;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.library.Tema;
import sv.edu.udb.models.Tema_Model;
import sv.edu.udb.validacion.Validacion;
/**
 *
 * @author Pazzuelo02
 */
public class GestionTemas extends javax.swing.JInternalFrame {
    private DefaultTableModel model = null;
    private List<Tema> temas = new ArrayList<Tema>();
    private int idTemaSeleccionado = -1;
    /**
     * Creates new form GestionTemas
     */
    public GestionTemas() {
        initComponents();
        inicializarComponente();
        cargarTemas();
    }
    private void inicializarComponente(){
        txtDescripcion.setEnabled(false);
        txtDescripcion.setText("");
        btnEliminarTema.setEnabled(false);
        btnModificarTema.setEnabled(false);
        idTemaSeleccionado = -1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBusqueda = new javax.swing.JTextField();
        lblPatron = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTemas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnEliminarTema = new javax.swing.JButton();
        btnModificarTema = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Gestión de Temas");

        txtBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        lblPatron.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblPatron.setText("Patrón a buscar");

        jtblTemas.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblTemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTemasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTemas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestión de Temas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12))); // NOI18N

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDescripcion.setText("Descripción");

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        btnEliminarTema.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnEliminarTema.setText("Eliminar");
        btnEliminarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTemaActionPerformed(evt);
            }
        });

        btnModificarTema.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnModificarTema.setText("Modificar");
        btnModificarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(btnModificarTema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarTema)
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblDescripcion)
                .addGap(18, 18, 18)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarTema)
                    .addComponent(btnEliminarTema))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblPatron)
                .addGap(40, 40, 40)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPatron))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarTemas(){
        Object[][] datos = null;
        String[] columns = {"Descripción"};
        model = new DefaultTableModel(datos,columns);
        for(Tema _t: temas){
            Object[] newline = {_t.getDescripcion()};
            model.addRow(newline);
        }
        jtblTemas.setModel(model);
    }
    private void btnModificarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTemaActionPerformed
        if(idTemaSeleccionado > -1){
               modificarTema();
               inicializarComponente();
               temas = Tema_Model.obtenerTemas();
               cargarTemas();
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un tema","Gestión de Temas",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarTemaActionPerformed
    
    private void modificarTema(){
        try{
            if(validarDatos() && txtDescripcion.getText().length() > 0 && !(txtDescripcion.getText().isEmpty()) && !(txtDescripcion.getText().equals(" "))){
                if(Tema_Model.modificar(new Tema(idTemaSeleccionado,txtDescripcion.getText()))){
                JOptionPane.showMessageDialog(null,"Tema modificado con éxito", "Gestión de Temas",JOptionPane.INFORMATION_MESSAGE);
                txtBusqueda.setText("");
                }else{
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error","Gestión de Temas",JOptionPane.ERROR_MESSAGE);
                }
            }   
        }catch(Exception ex){
            Logger.getLogger(GestionTemas.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void btnEliminarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTemaActionPerformed
        if(idTemaSeleccionado > -1){
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas eliminar este tema?","Gestión de Temas",JOptionPane.WARNING_MESSAGE);
            if(confirmar == JOptionPane.OK_OPTION){
                if(Tema_Model.eliminar(new Tema(idTemaSeleccionado,txtDescripcion.getText()))){
                
                    inicializarComponente();
                    temas = Tema_Model.obtenerTemas();
                    cargarTemas();
                    JOptionPane.showMessageDialog(null, "Tema eliminado con éxito","Gestión de Temas",JOptionPane.INFORMATION_MESSAGE);
                    txtBusqueda.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el tema","Gestión de Temas",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un tema","Gestión de Temas",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarTemaActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
       temas = Tema_Model.buscarTemas(txtBusqueda.getText());
        if(txtBusqueda.getText().length() == 1){
           temas = Tema_Model.obtenerTemas();
       }
        cargarTemas();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    
    private void jtblTemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTemasMouseClicked
        if(temas.size() > 0){
            int fila = jtblTemas.rowAtPoint(evt.getPoint());
            if(fila > -1){
                idTemaSeleccionado = temas.get(fila).getIdTema();
                txtDescripcion.setText(temas.get(fila).getDescripcion());
                txtDescripcion.setEnabled(true);
                btnEliminarTema.setEnabled(true);
               btnModificarTema.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jtblTemasMouseClicked
    private boolean validarDatos(){
        
        if(Validacion.validar("^[A-Z][a-z ,.'-]+$", txtDescripcion.getText(), "No puede ingresar numeros o simbolos!", "Agregar Tema")){
            return true;
        }
        return false;    
    }
    
    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        /*if (!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_COMMA) && !(evt.getKeyChar() == KeyEvent.VK_SPACE) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
      evt.consume();
        }*/
    }//GEN-LAST:event_txtDescripcionKeyTyped
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarTema;
    private javax.swing.JButton btnModificarTema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblTemas;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblPatron;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}

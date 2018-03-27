/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.forms.imprenta;

import com.sun.glass.events.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.validacion.Validacion;
import javax.swing.JOptionPane;
import sv.edu.udb.models.Imprenta_Model;
import sv.edu.udb.library.Imprenta;
/**
 *
 * @author Pazzuelo02
 */
public class AgregarImprenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form AgregarImprenta
     */
    public AgregarImprenta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblDirección = new javax.swing.JLabel();
        btnAgregarImprenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Registro de Imprentas");

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblNombre.setText("Nombre");

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lblDirección.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDirección.setText("Dirección");

        btnAgregarImprenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarImprenta.setText("Agregar Imprenta");
        btnAgregarImprenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImprentaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Imprentas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDirección)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDireccion)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnAgregarImprenta, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDirección))
                .addGap(35, 35, 35)
                .addComponent(btnAgregarImprenta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        /*if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE) && !(evt.getKeyChar() == KeyEvent.VK_BACKSPACE)){
            evt.consume();
        }*/
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        /*if(!Character.isLetter(evt.getKeyChar()) && !Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_MINUS) && !(evt.getKeyChar() == KeyEvent.VK_NUMBER_SIGN) && !(evt.getKeyChar() == KeyEvent.VK_PERIOD) && !(evt.getKeyChar() == KeyEvent.VK_SPACE) && !(evt.getKeyChar() == KeyEvent.VK_BACKSPACE)){
            evt.consume();
        }*/
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void btnAgregarImprentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImprentaActionPerformed
        try{
            if(validarDatos()){
            if(txtNombre.getText().length() > 0 && !(txtNombre.getText().isEmpty()) && !(txtNombre.getText().equals(" "))){
                if(txtDireccion.getText().length() > 0 && !(txtDireccion.getText().isEmpty()) && !(txtDireccion.getText().equals(" "))){
                    if(Imprenta_Model.insertar(new Imprenta(txtNombre.getText(),txtDireccion.getText()))){
                        JOptionPane.showMessageDialog(null, "Imprenta ingresada correctamente","Agregar Imprenta",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }      
                }
            } 
        }catch(Exception ex){
            Logger.getLogger(AgregarImprenta.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_btnAgregarImprentaActionPerformed
    private boolean validarDatos(){
        if(Validacion.validar("^[A-Za-z áéíóúñÑhH]$",txtNombre.getText(),"Ingrese un nombre válido","Agregar Imprenta")){
            if(Validacion.validar("^[A-Za-z áéíóúñÑhH,-#']$",txtDireccion.getText(),"Algunos caracteres no estan permitidos, revise su direccion","Agregar Imprenta")){
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarImprenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDirección;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

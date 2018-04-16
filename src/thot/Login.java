/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thot;

import javax.swing.JOptionPane;
import sv.edu.udb.libreria.Email;
import sv.edu.udb.libreria.Encriptar;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.modelos.Usuario_Model;
import sv.edu.udb.validacion.Validacion;

/**
 *
 * @author Diego Lemus
 */
public class Login extends javax.swing.JFrame {
    protected static String typeU;
    /**
     * Creates new form Login
     */
    public Login() {
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

        txtPass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnPass = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Contraseña");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Correo Electrónico");

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIniciar.setText("Iniciar Sesión");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Thot");

        btnPass.setText("He olvidado mi contraseña");
        btnPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPass)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(177, 177, 177))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPass)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        char[] arrayC = txtPass.getPassword();
        String passwor = new String(arrayC);
        if(validarCorreo()){
            if(iniciarSesion(txtCorreo.getText(), passwor)){
                ThotMDI thot = new ThotMDI(typeU);
                thot.setVisible(true);
                Login.this.setVisible(false);
            }
        }
    }//GEN-LAST:event_btnIniciarActionPerformed
    public static boolean iniciarSesion(String correo,String pass){
        Usuario u = Usuario_Model.buscarUsuario(correo, pass);
        if(u != null){
            typeU = u.getTipoUsuario();
            JOptionPane.showMessageDialog(null, "Bienvenido a Thot!!","[Thot] - Iniciar Sesión",JOptionPane.INFORMATION_MESSAGE);
            return true;            
        }else{
            JOptionPane.showMessageDialog(null, "Las credenciales ingresadas no son correctas","Inicio de Sesión",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean enviarCorreo(String correo, String contrasenna) {
        String mensaje = "<h5>Contraseña: </h5>" + contrasenna;
        Email email = new Email(correo);
        return email.enviar(mensaje, "[Thot] - Recuperación de Contraseña");
    }
    private boolean validarCorreo(){
        if(Validacion.validar("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", txtCorreo.getText(), "Correo Electrónico no válido", "Registro Usuario")){
            return true;
        }else{
        return false;
        }
    }
    
    private boolean validarCorreo(String _e){
        if(Validacion.validar("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", _e, "Correo Electrónico no válido!", "[Thot] - Iniciar Sesión")){
            return true;
        }else{
            return false;
        }
    }
    private void btnPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPassActionPerformed
        boolean f = true;
        String _e = "";

        do {
            _e = JOptionPane.showInputDialog(this, "Ingresa tu correo", "[Thot] - Recuperar de contraseña", JOptionPane.INFORMATION_MESSAGE);
            if(!validarCorreo(_e)){
                f = true;
            }else{
                f = false;
            }
        } while (f);

        Usuario u = Usuario_Model.obtenerUsuarioCorreo(_e);
        if(u != null){
            String pass = Encriptar.desencriptar(u.getPassword());
            if (enviarCorreo(_e, pass)) {
                JOptionPane.showMessageDialog(null, "El correo con la contraseña ha sido enviado éxitosamente", "[Thot] - Recuperar de contraseña", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ha ocurrido un error al enviar el correo", "[Thot] - Recuperar de contraseña", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "El correo ingresado no está registrado en la base de datos!", "[Thot] - Recuperar de contraseña", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPassActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}

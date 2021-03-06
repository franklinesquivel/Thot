/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.form.usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sv.edu.udb.connection.Email;
import sv.edu.udb.libreria.Encriptar;
import sv.edu.udb.libreria.TipoUsuario;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.controladores.TipoUsuario_Controller;
import sv.edu.udb.controladores.Usuario_Controller;
import sv.edu.udb.validacion.Validacion;
/**
 *
 * @author Leonardo
 */
public class AgregarUsuario extends javax.swing.JInternalFrame {
    private List<TipoUsuario> tipos = new ArrayList<TipoUsuario>();
    /**
     * Creates new form AgregarUsuario
     */
    public AgregarUsuario() {
        initComponents();
        iniciarCampos();
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
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JFormattedTextField();
        btnRegistrar = new javax.swing.JButton();
        lblTipo = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("[Thot] - Registro de Usuarios");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sv/edu/udb/data/thot.jpg"))); // NOI18N

        lblNombre.setText("Nombres");

        lblApellido.setText("Apellidos");

        lblCorreo.setText("Correo Electrónico");

        jLabel4.setText("Fecha Nacimiento");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        lblTipo.setText("Tipo de Usuario");

        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCorreo)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblNombre)
                            .addGap(18, 18, 18)
                            .addComponent(txtNombre))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblApellido)
                            .addGap(18, 18, 18)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addGap(158, 158, 158))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(btnRegistrar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Registrar usuario
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if(validarCampos()){
                String tipo = "";
                for(TipoUsuario t : tipos){
                    if(t.getNombre().equals(cmbTipoUsuario.getSelectedItem().toString())){
                        tipo = String.valueOf(t.getId());
                        break;
                    }
                }

                String password = Encriptar.encriptar(Usuario.crearContransenna());
                String username = Usuario.crearNombreUsuario(tipo, Usuario_Controller.obtenerNumUsuario(tipo));
                String idUsuario = username;
                String nombre = txtNombre.getText(), 
                    apellido = txtApellido.getText(),
                    correo = txtCorreo.getText(),
                    tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();
                DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
                Date fechaNacimiento = ft.parse(txtFechaNacimiento.getText());
                //Este es el de Leo :v
                if(compararFecha(fechaNacimiento)){//Comparamos que la fehca ingresada no sea mayor a la actual
                    if(Usuario_Controller.verificarCorreo(correo)){
                        if(Usuario_Controller.insertar(new Usuario(idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, true, tipo))){
                            if(enviarCorreo(correo, Encriptar.desencriptar(password))){
                                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.\n ", "Registro de Usuario", JOptionPane.INFORMATION_MESSAGE); 
                                iniciarCampos(); //Se reinician campos
                            }else{
                                JOptionPane.showMessageDialog(null, "ha ocurrido un error", "Registro de Usuario", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "ha ocurrido un error", "Registro de Usuario", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El correo ingresado ya existe!", "Registro de Usuario", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AgregarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    //Carga los tipos de usuario en el cmb
    private void cargarTipoUsuario(){
        cmbTipoUsuario.removeAllItems(); //Remover Items
        tipos = TipoUsuario_Controller.obtenerTiposUsuarios();
        for(TipoUsuario t : tipos){
            cmbTipoUsuario.addItem(t.getNombre());
        }
    }
    
    private boolean enviarCorreo(String correo, String contrasenna){
        String mensaje  = "<h5>Contraseña: </h5>"+contrasenna;
        Email email = new Email(correo);
        return email.enviar(mensaje, "Registro de Usuario");
    }
    
    private void iniciarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtFechaNacimiento.setText("");
        cargarTipoUsuario();
    }
    
    private boolean validarCampos(){
        if(Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú]*$", txtNombre.getText(), "Ingresar un nombre válido!", "Agregar Usuario")
           && Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú]*$", txtApellido.getText(), "Ingresar un apellido válido!", "Agregar Usuario")
           && Validacion.validar("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", txtCorreo.getText(), "Correo Electrónico no válido", "Agregar Usuario")
           && Validacion.validar("^1[0-9]{3}-[0-9]{1,2}-[0-9]{1,2}$", txtFechaNacimiento.getText(), "Fecha Inválida (yyyy-MM-dd)", "Agregar Usuario")){
            return true;
        }
        return false;
    }
    
    private boolean compararFecha(Date fecha){
        Date fechaActual = new Date();
        if(fecha.compareTo(fechaActual) < 0){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Ingresar una fecha válida", "Registro de Usuario", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JFormattedTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

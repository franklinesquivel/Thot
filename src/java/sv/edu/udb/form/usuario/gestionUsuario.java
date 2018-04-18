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
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.libreria.TipoUsuario;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.modelos.TipoUsuario_Model;
import sv.edu.udb.modelos.Usuario_Model;
import sv.edu.udb.validacion.Validacion;

/**
 *
 * @author Leonardo
 */
public class gestionUsuario extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo = null;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<TipoUsuario> tipos = new ArrayList<TipoUsuario>();
    private String idUsuarioSeleccionado = "";
    
    /**
     * Creates new form gestionUsuario
     */
    public gestionUsuario() {
        initComponents();
        inicializarComponente();
        cargarUsuarios();
    }
    
    
    
    private void cargarUsuarios(){
        Object[][] datos = null;
        String[] columns = {"Nombre", "Apellido", "Fecha de Nacimiento", "Correo", "Username", "estado", "Tipo de Usuario"};
        modelo = new DefaultTableModel(datos, columns);
        
        for(Usuario _u : usuarios){
            String estado = (_u.isEstado()) ? "Activo" : "Pasivo";
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(_u.getFechaNacimiento());
            
            Object[] nuevaLinea = {_u.getNombre(), _u.getApellido(), date, _u.getCorreo(), _u.getUsername(), estado, _u.getTipoUsuario()};
            modelo.addRow(nuevaLinea);
        }       
        jtblUsuarios.setModel(modelo);
    }
    
    private void inicializarComponente(){
        usuarios = Usuario_Model.obtenerUsuarios(); //Obtenemos el listado de usuario
        txtNombreUsuario.setEnabled(false);
        cmbTipoUsuario.setEnabled(false);
        
        //Cargamos los tipos de usuario
        cmbTipoUsuario.removeAllItems(); //Remover Items
        tipos = TipoUsuario_Model.obtenerTiposUsuarios();
        for(TipoUsuario t : tipos){
            cmbTipoUsuario.addItem(t.getNombre());
        }
        
        //Ponemos en blanco los campos
        txtNombreUsuario.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtFechaNacimiento.setText("");        
        cmbEstado.setSelectedIndex(0);
        idUsuarioSeleccionado = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUsuarios = new javax.swing.JTable();
        lblFiltro = new javax.swing.JLabel();
        cmbBuscador = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtNombreUsuario = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();
        lblTipoUsuario = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setTitle("[Thot] - Gestión de Usuario");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sv/edu/udb/data/thot.jpg"))); // NOI18N

        jtblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblUsuariosMouseClicked(evt);
            }
        });
        jtblUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtblUsuariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtblUsuarios);

        lblFiltro.setText("Filtro");

        cmbBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Correo Electrónico", "Nombre de Usuario" }));

        jLabel1.setText("Patrón a buscar");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestión de Usuario"));

        lblUsername.setText("Nombre de Usuario");

        lblNombre.setText("Nombre");

        lblApellido.setText("Apellido");

        lblCorreo.setText("Correo");

        lblFechaNacimiento.setText("Fecha de Nacimiento");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Pasivo", " " }));

        lblEstado.setText("Estado");

        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblTipoUsuario.setText("Tipo de Usuario");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTipoUsuario)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaNacimiento)
                                    .addComponent(lblEstado))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbEstado, 0, 138, Short.MAX_VALUE)
                                    .addComponent(txtFechaNacimiento)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApellido)
                                    .addComponent(lblCorreo)
                                    .addComponent(lblNombre))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido)
                                    .addComponent(txtCorreo)
                                    .addComponent(txtNombre)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUsername)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreUsuario))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(btnEliminar)
                        .addGap(30, 30, 30)
                        .addComponent(btnModificar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoUsuario)
                    .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addComponent(lblFiltro))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbBuscador, 0, 167, Short.MAX_VALUE)
                            .addComponent(txtBuscar))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltro)
                    .addComponent(cmbBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Pone datos en campo
    private void jtblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblUsuariosMouseClicked
        if(usuarios.size() > 0){//Si el array no esta vacío
            int fila = jtblUsuarios.rowAtPoint(evt.getPoint());
            if(fila > -1){
                idUsuarioSeleccionado = usuarios.get(fila).getIdUsuario(); //Añadimos el id del usuario seleccionado
                txtNombreUsuario.setText(usuarios.get(fila).getUsername());
                txtNombre.setText(usuarios.get(fila).getNombre());
                txtApellido.setText(usuarios.get(fila).getApellido());
                txtCorreo.setText(usuarios.get(fila).getCorreo());
                
                //Formatea fecha
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(usuarios.get(fila).getFechaNacimiento().getTime());
                txtFechaNacimiento.setText(date);
                
                if(usuarios.get(fila).isEstado()){ //Activo
                    cmbEstado.setSelectedIndex(0);
                }else{ //Pasivo
                    cmbEstado.setSelectedIndex(1);
                }
                
                //Seleccionamos el tipo de usuario
                for(TipoUsuario t: tipos){
                    if(String.valueOf(t.getId()).equals(usuarios.get(fila).getTipoUsuario())){
                        cmbTipoUsuario.removeAllItems(); //Remover Items
                        cmbTipoUsuario.addItem(t.getNombre());
                    }
                }
            }
        }
    }//GEN-LAST:event_jtblUsuariosMouseClicked

    //Modificar Usuario
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(idUsuarioSeleccionado.length() > 0){
            if(validarCampos()){
                if(Usuario_Model.verificarCorreo(txtCorreo.getText(), idUsuarioSeleccionado)){
                    if(modificarUsuario()){
                        inicializarComponente();
                        cargarUsuarios();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "El correo ingresado ya existe!", "Registro de Usuario", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un usuario", "Gestión de Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private boolean modificarUsuario(){
        try {
            boolean estado = (cmbEstado.getSelectedIndex() == 0) ? true : false;
            String nombre = txtNombre.getText(), 
                apellido = txtApellido.getText(),
                correo = txtCorreo.getText();
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
            Date fechaNacimiento = ft.parse(txtFechaNacimiento.getText());
            if(compararFecha(fechaNacimiento)){
                if(Usuario_Model.modificarUsuario(new Usuario(idUsuarioSeleccionado, nombre, apellido, correo, fechaNacimiento, estado))){
                    JOptionPane.showMessageDialog(null, "Usuario modificado correctamente", "Gestión de Usuario", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "ha ocurrido un error", "Gestión de Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AgregarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //Eliminar Usuario
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(idUsuarioSeleccionado.length() > 0){
            int respuesta = JOptionPane.showConfirmDialog(null,"¿Estas seguro eliminar este usuario?", "Gestión de Usuario", JOptionPane.WARNING_MESSAGE);
            if(respuesta == JOptionPane.OK_OPTION){ //Eliminar
                
                if(Usuario_Model.eliminarUsuario(String.valueOf(idUsuarioSeleccionado))){
                    inicializarComponente();
                    cargarUsuarios();
                    JOptionPane.showMessageDialog(null, "Usuario elimnado correctamente", "Gestión de Usuario", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Error en el proceso de eliminación", "Gestión de Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un usuario", "Gestión de Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    
    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        
    }//GEN-LAST:event_txtBuscarKeyPressed
    
    //Buscador
    private void jtblUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblUsuariosKeyReleased
        
    }//GEN-LAST:event_jtblUsuariosKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(cmbBuscador.getSelectedItem().toString().equals("Nombre")){ //Buscar por nombre
            usuarios = Usuario_Model.BuscarUsuarios("nombre", txtBuscar.getText());
        }else if(cmbBuscador.getSelectedItem().toString().equals("Apellido")){//Buscar por apellido
            usuarios = Usuario_Model.BuscarUsuarios("apellido", txtBuscar.getText());
        }else if(cmbBuscador.getSelectedItem().toString().equals("Correo Electrónico")){ //Buscar por Correo
            usuarios = Usuario_Model.BuscarUsuarios("correo", txtBuscar.getText());
        }else if(cmbBuscador.getSelectedItem().toString().equals("Nombre de Usuario")){//Buscar por nombre
            usuarios = Usuario_Model.BuscarUsuarios("username", txtBuscar.getText());
        }
        
        if(txtBuscar.getText().length() == 1){
            usuarios = Usuario_Model.obtenerUsuarios(); //Obtenemos el listado de usuario (todos)
        }
        cargarUsuarios();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private boolean validarCampos(){
        if(Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú]*$", txtNombre.getText(), "Ingresar un nombre válido!", "Agregar Usuario")
           && Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú]*$", txtApellido.getText(), "Ingresar un apellido válido!", "Agregar Usuario")
           && Validacion.validar("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", txtCorreo.getText(), "Correo Electrónico no válido", "Registro Usuario")
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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbBuscador;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblUsuarios;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}

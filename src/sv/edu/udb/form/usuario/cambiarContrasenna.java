/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.form.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.library.Encriptar;
import sv.edu.udb.library.TipoUsuario;
import sv.edu.udb.library.Usuario;
import sv.edu.udb.models.Usuario_Model;
import sv.edu.udb.validacion.Validacion;

/**
 *
 * @author Leonardo
 */
public class cambiarContrasenna extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo = null;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<TipoUsuario> tipos = new ArrayList<TipoUsuario>();
    private int idUsuarioSeleccionado;
    
    /**
     * Creates new form cambiarContrasenna
     */
    public cambiarContrasenna() {
        initComponents();
        txtNombreUsuario.setEnabled(false);
        inicializarComponentes();
        cargarUsuarios();
    }
    
    private void cargarUsuarios(){
        Object[][] datos = null;
        String[] columns = {"Nombre", "Apellido", "Fecha de Nacimiento", "Correo", "Username", "estado", "Tipo de Usuario"};
        modelo = new DefaultTableModel(datos, columns);
        
        for(Usuario _u : usuarios){
            String estado = (_u.isEstado()) ? "Activo" : "Pasivo";
            Object[] nuevaLinea = {_u.getNombre(), _u.getApellido(), _u.getFechaNacimiento(), _u.getCorreo(), _u.getUsername(), estado, _u.getTipoUsuario()};
            modelo.addRow(nuevaLinea);
        }       
        jtblUsuarios.setModel(modelo);
    }
    
    private void inicializarComponentes(){
        txtNombreUsuario.setText("");
        idUsuarioSeleccionado = -1;
        txtContrasenna.setText("");
        
        usuarios = Usuario_Model.obtenerUsuarios(); //Cargamos TODOS los usuarios
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblFiltro = new javax.swing.JLabel();
        cmbFiltro = new javax.swing.JComboBox<>();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUsuarios = new javax.swing.JTable();
        lblNombreUsuario = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        lblContrasenna = new javax.swing.JLabel();
        txtContrasenna = new javax.swing.JTextField();
        btnProcesar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        lblFiltro.setText("Filtro");

        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Correo Electrónico", "Nombre de Usuario", " " }));

        lblBuscar.setText("Patrón de busqueda");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

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
        jScrollPane1.setViewportView(jtblUsuarios);

        lblNombreUsuario.setText("Nombre de Usuario");

        lblContrasenna.setText("Nueva Contraseña");

        btnProcesar.setText("jButton1");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblContrasenna)
                        .addGap(18, 18, 18)
                        .addComponent(txtContrasenna))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombreUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnProcesar)
                        .addGap(277, 277, 277))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiltro))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbFiltro, 0, 128, Short.MAX_VALUE)
                            .addComponent(txtBuscar))
                        .addGap(179, 179, 179))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltro)
                    .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContrasenna)
                    .addComponent(txtContrasenna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnProcesar)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Txt Buscador
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(cmbFiltro.getSelectedItem().toString().equals("Nombre")){ //Buscar por nombre
            usuarios = Usuario_Model.BuscarUsuarios("nombre", txtBuscar.getText());
        }else if(cmbFiltro.getSelectedItem().toString().equals("Apellido")){//Buscar por apellido
            usuarios = Usuario_Model.BuscarUsuarios("apellido", txtBuscar.getText());
        }else if(cmbFiltro.getSelectedItem().toString().equals("Correo Electrónico")){ //Buscar por Correo
            usuarios = Usuario_Model.BuscarUsuarios("correo", txtBuscar.getText());
        }else if(cmbFiltro.getSelectedItem().toString().equals("Nombre de Usuario")){//Buscar por nombre
            usuarios = Usuario_Model.BuscarUsuarios("username", txtBuscar.getText());
        }
        
        if(txtBuscar.getText().length() == 1){
            usuarios = Usuario_Model.obtenerUsuarios(); //Obtenemos el listado de usuario (todos)
        }
        cargarUsuarios();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        if(idUsuarioSeleccionado > -1){
            if(validarCampos()){
                if(Usuario_Model.modificarContrasenna(new Usuario(idUsuarioSeleccionado, Encriptar.encriptar(txtContrasenna.getText())))){
                    JOptionPane.showMessageDialog(null, "Contraseña modificada!", "Cambiar Contraseña", JOptionPane.INFORMATION_MESSAGE);
                    inicializarComponentes();
                    cargarUsuarios();
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error!", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un usuario", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void jtblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblUsuariosMouseClicked
        if(usuarios.size() > 0){
            int fila = jtblUsuarios.rowAtPoint(evt.getPoint());
            if(fila > -1){
                idUsuarioSeleccionado = fila;
                txtNombreUsuario.setText(usuarios.get(fila).getUsername());
            }
        }
    }//GEN-LAST:event_jtblUsuariosMouseClicked

    private boolean validarCampos(){
        if(Validacion.validar("^[a-zA-Z0-9]+$", txtContrasenna.getText(), "Ingresar solamente caracteres alfanúmericos!", "Cambiar Contraseña")){
            if(txtContrasenna.getText().length() >= 8){
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Ingresar una cadena mayor o igual a 8 caracteres!", "Cambiar Contraseña", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblUsuarios;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContrasenna;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContrasenna;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}

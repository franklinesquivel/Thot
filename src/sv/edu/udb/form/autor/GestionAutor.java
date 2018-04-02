/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.form.autor;

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
import sv.edu.udb.library.Autor;
import sv.edu.udb.library.Pais;
import sv.edu.udb.models.Autor_Model;
import sv.edu.udb.models.Pais_Model;
import sv.edu.udb.validacion.Validacion;

/**
 *
 * @author jasso
 */
public class GestionAutor extends javax.swing.JInternalFrame {

    private List<Pais> items = new ArrayList<Pais>();

    private DefaultTableModel modelo = null;
    private List<Autor> autores = new ArrayList<Autor>();
    private String idAutorSeleccionado;

    /**
     * Creates new form GestionAutor
     */
    public GestionAutor() {
        initComponents();
        inicializarComponente();
        cargarAutores();

    }

    private void cargarAutores() {
        Object[][] datos = null;
        String[] columns = {"ID", "Nombres", "Apellidos", "Fecha de Nacimiento", "Pais"};
        modelo = new DefaultTableModel(datos, columns);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (Autor _a : autores) {
            Object[] nuevaLinea = {_a.getIdAutor(), _a.getNombres(), _a.getApellidos(), format.format(_a.getFechaNac()), _a.getPais()};
            modelo.addRow(nuevaLinea);
        }
        jtDatos.setModel(modelo);
    }

    private void inicializarComponente() {
        autores = Autor_Model.obtenerAutores();
        jcbPais.setEnabled(false);

        //Cargamos los tipos de usuario
        jcbPais.removeAllItems(); //Remover Items
        items = Pais_Model.obtenerPaises();
        for (Pais p : items) {
            jcbPais.addItem(p.getNombre());
        }

        //Ponemos en blanco los campos
        txtNombre.setText("");
        txtApellido.setText("");
        txtFecha.setText("");
        jcbPais.setSelectedIndex(0);
    }

    private boolean modificarUsuario() {
        try {
            String nombre = txtNombre.getText(),
                    apellido = txtApellido.getText(),
                    pais = jcbPais.getSelectedItem().toString();
            Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(txtFecha.getText());

            String PaisString = "";
            for (Pais P : items) {
                if (P.getNombre().equals(jcbPais.getSelectedItem().toString())) {
                    PaisString = String.valueOf(P.getIdPais());
                    break;
                }
            }

            if (compararFecha(fechaNacimiento)) {
                if (Autor_Model.modificar(new Autor(idAutorSeleccionado, nombre, apellido, fechaNacimiento, PaisString))) {
                    JOptionPane.showMessageDialog(null, "Usuario modificado correctamente", "Gestión de Usuario", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "ha ocurrido un error", "Gestión de Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AgregarAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean validarCampos() {
        if (Validacion.validar("^[\\p{L} .'-]+$", txtNombre.getText(), "Ingresar un nombre válido!", "Agregar Usuario")
                && Validacion.validar("^[\\p{L} .'-]+", txtApellido.getText(), "Ingresar un apellido válido!", "Agregar Usuario")
                && Validacion.validar("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$", txtFecha.getText(), "Fecha Inválida (yyyy-MM-dd)", "Agregar Usuario")) {
            return true;
        }
        return false;
    }

    private boolean compararFecha(Date fecha) {
        Date fechaActual = new Date();
        if (fecha.compareTo(fechaActual) < 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar una fecha válida", "Registro de Usuario", JOptionPane.ERROR_MESSAGE);
        }
        return false;
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
        jtDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jcbPais = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jtDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "F. Nac", "Pais"
            }
        ));
        jtDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtDatos);

        lblNombre.setText("Nombre");

        lblApellido.setText("Apellido");

        lblFecha.setText("Fecha de nacimiento");

        lblPais.setText("País");

        jcbPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblApellido)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnModificar)
                        .addGap(62, 62, 62)
                        .addComponent(btnEliminar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPais)
                            .addComponent(lblFecha))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais)
                    .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (validarCampos()) {

            if (modificarUsuario()) {
                inicializarComponente();
                cargarAutores();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un autor", "Gestión de Autor", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void jtDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDatosMouseClicked
        if (autores.size() > 0) {
            int fila = jtDatos.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                idAutorSeleccionado = autores.get(fila).getIdAutor();
                txtNombre.setText(autores.get(fila).getNombres());
                txtApellido.setText(autores.get(fila).getApellidos());

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(autores.get(fila).getFechaNac());

                txtFecha.setText(date);
                jcbPais.setEnabled(true);

            }
        }
    }//GEN-LAST:event_jtDatosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (idAutorSeleccionado != null) {

            int respuesta = JOptionPane.showConfirmDialog(null, "¿Estas seguro eliminar este autor?", "Gestion de autor", JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.OK_OPTION) { //Eliminar

                if (Autor_Model.eliminar(new Autor(idAutorSeleccionado, "", "", new Date(), ""))) {
                    inicializarComponente();
                    cargarAutores();
                    JOptionPane.showMessageDialog(null, "Autor elimnado correctamente", "Gestión de Autor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error en el proceso de eliminación", "Gestión de Autor", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un Autor", "Gestión de Autor", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbPais;
    private javax.swing.JTable jtDatos;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPais;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

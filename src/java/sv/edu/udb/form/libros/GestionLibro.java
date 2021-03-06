/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.form.libros;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.libreria.Autor;
import sv.edu.udb.libreria.Categoria;
import sv.edu.udb.libreria.Imprenta;
import sv.edu.udb.libreria.Libro;
import sv.edu.udb.libreria.Tema;
import sv.edu.udb.controladores.Autor_Controller;
import sv.edu.udb.controladores.Categoria_Controller;
import sv.edu.udb.controladores.Imprenta_Controller;
import sv.edu.udb.controladores.Libro_Controller;
import sv.edu.udb.controladores.Tema_Controller;
import sv.edu.udb.validacion.Validacion;

/**
 *
 * @author Frank
 */
public class GestionLibro extends javax.swing.JInternalFrame {
    DefaultTableModel modelo = null;
    List<Libro> libros;
    String idLibroSeleccionado = null;
    boolean validData = true;
    boolean editFlag = false;
    File imgFile = null;
    
    List<Autor> _a;
    List<Categoria> _c;
    List<Tema> _t;
    List<Imprenta> _i;
    
    /**
     * Creates new form GestionLibro
     */
    public GestionLibro() {
        initComponents();
        initData();
        cargarDatos();
    }
    
    private void initData(){
        libros = Libro_Controller.obtenerLibros();
        _a = Autor_Controller.obtenerAutores("ORDER BY apellidos ASC");
        _c = Categoria_Controller.obtenerCategorias("ORDER BY nombre ASC");
        _t = Tema_Controller.obtenerTemas("ORDER BY descripcion ASC");
        _i = Imprenta_Controller.obtenerImprentas("ORDER BY nombre ASC");
        
        if (_a.size() > 0) {
            String[] auxAutores = new String[_a.size()];

            for (int i = 0; i < _a.size(); i++) {
                auxAutores[i] = _a.get(i).getApellidos().split(" ")[0] + ", " + _a.get(i).getNombres().split(" ")[0];
            }

            listAutores.setModel(new javax.swing.DefaultComboBoxModel<>(auxAutores));
        } else {
            validData = false;
            listAutores.setEnabled(false);
            listAutores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No hay autores registradas"}));
        }

        if (_c.size() > 0) {
            String[] auxCategorias = new String[_c.size() + 1];

            auxCategorias[0] = "Selecciona una categoría";
            for (int i = 0; i < _c.size(); i++) {
                auxCategorias[i + 1] = _c.get(i).getNombre();
            }

            cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(auxCategorias));
        } else {
            validData = false;
            cmbCategorias.setEnabled(false);
            cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No hay categorías registradas"}));
        }

        if (_t.size() > 0) {
            String[] auxTemas = new String[_t.size()];

            for (int i = 0; i < _t.size(); i++) {
                auxTemas[i] = _t.get(i).getDescripcion();
            }

            listTemas.setModel(new javax.swing.DefaultComboBoxModel<>(auxTemas));
        } else {
            validData = false;
            listTemas.setEnabled(false);
            listTemas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No hay temas registradas"}));
        }

        if (_i.size() > 0) {
            String[] auxImprentas = new String[_i.size() + 1];

            auxImprentas[0] = "Selecciona una imprenta";
            for (int i = 0; i < _i.size(); i++) {
                auxImprentas[i + 1] = _i.get(i).getNombre();
            }

            cmbImprentas.setModel(new javax.swing.DefaultComboBoxModel<>(auxImprentas));
        } else {
            validData = false;
            cmbImprentas.setEnabled(false);
            cmbImprentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No hay imprentas registradas"}));
        }
        
        editFlag = false;
        validData = true;
        imgFile = null;
        
        tblMain.setEnabled(true);
        tblMain.setRowSelectionAllowed(true);
        txtBuscar.setEnabled(true);
        cmbBuscador.setEnabled(true);
        
        txtTitulo.setText("");
        txtIsbn.setText("");
        txtDescripcion.setText("");
        txtNotas.setText("");
        txtEdicion.setText("");
        txtImagen.setText("[Opcional]");
        
        txtTitulo.setEnabled(false);
        txtIsbn.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtNotas.setEnabled(false);
        txtEdicion.setEnabled(false);
        txtImagen.setEnabled(false);
        cmbCategorias.setEnabled(false);
        cmbImprentas.setEnabled(false);
        listAutores.setEnabled(false);
        listTemas.setEnabled(false);
        btnImagen.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        lblImg.setIcon(null);
        
        pnlDataFrm.setEnabled(false);
        pnlTable.setEnabled(true);
        btnHabilitar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    private void cargarDatos(){
        Object[][] datos = null;
        String[] columns = {"Titulo", "ISBN", "Edición"};
        modelo = new DefaultTableModel(datos, columns);
        
        for(Libro _l : libros){
            Object[] nuevaLinea = {_l.getTitulo(), _l.getIsbn(), _l.getEdicion()};
            modelo.addRow(nuevaLinea);
        } 
        
        tblMain.setModel(modelo);
    }
    
    private void cargarLibro(Libro _l){
        _l = _l.cargarRelaciones(_l);
        int _sCate = 0, _sImp = 0;
        int[] _sAutores = new int[_l.getAutores().size()], _sTemas = new int[_l.getTemas().size()];
        
        for(Categoria c : _c){
            if(c.getIdCategoria() == _l.getCategoria().getIdCategoria()){
                break;
            }else{
                _sCate++;
            }
        }
        
        for(Imprenta i: _i){
            if(i.getIdImprenta().equals(_l.getImprenta().getIdImprenta())){
                break;
            }else{
                _sImp++;
            }
        }
        
        int x = 0;
        for(Autor a2 : _l.getAutores()){
            int y = 0;
            for (Autor a : _a) {
                if (a.getIdAutor().equals(a2.getIdAutor())) {
                    _sAutores[x++] = y ;
                }else{
                    y++;
                }
            }
        }
        
        x = 0;
        for(Tema t2 : _l.getTemas()){
            int y = 0;
            for(Tema t : _t){
                if(t.getIdTema() == t2.getIdTema()){
                    _sTemas[x++] = y;
                }else{
                    y++;
                }
            }
        }
        
        txtTitulo.setText(_l.getTitulo());
        txtIsbn.setText(_l.getIsbn());
        txtDescripcion.setText(_l.getDescripcion());
        txtNotas.setText(_l.getNotas());
        txtEdicion.setText(_l.getEdicion());
        txtImagen.setText("[Opcional]");
        cmbCategorias.setSelectedIndex(_sCate + 1);
        cmbImprentas.setSelectedIndex(_sImp + 1);
        listAutores.setSelectedIndices(_sAutores);
        listTemas.setSelectedIndices(_sTemas);
            
        try{
            String savePath = System.getProperty("user.dir") + "^web^images^libros";
            savePath = String.join(System.getProperty("file.separator"), savePath.split("\\^")) + System.getProperty("file.separator");
            
            Image image = null;
            URL url = new URL("file:///" + savePath + _l.getImagen());
            image = ImageIO.read(url);
            image = image.getScaledInstance(lblImg.getWidth() - 5, lblImg.getHeight() - 5, Image.SCALE_SMOOTH);
            lblImg.setIcon(new ImageIcon(image));
        } catch (IOException ex) {
            Logger.getLogger(GestionLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    private boolean saveImage(String name) {
        BufferedImage image;
        String savePath = System.getProperty("user.dir") + "^web^images^libros";
        savePath = String.join(System.getProperty("file.separator"), savePath.split("\\^"));

        try {
            URL url = new URL("file:///" + imgFile.getAbsolutePath());
            image = ImageIO.read(url);

            ImageIO.write(image, getFileExtension(imgFile), new File(savePath + System.getProperty("file.separator") + name + "." + getFileExtension(imgFile)));
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
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

        fileCImagen = new javax.swing.JFileChooser();
        pnlTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbBuscador = new javax.swing.JComboBox<>();
        lblFiltro = new javax.swing.JLabel();
        btnHabilitar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlDataFrm = new javax.swing.JPanel();
        txtTitulo = new javax.swing.JTextField();
        txtIsbn = new javax.swing.JTextField();
        txtEdicion = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        btnImagen = new javax.swing.JButton();
        txtImagen = new javax.swing.JTextField();
        cmbCategorias = new javax.swing.JComboBox<>();
        cmbImprentas = new javax.swing.JComboBox<>();
        lblTitulo = new javax.swing.JLabel();
        lblIsb = new javax.swing.JLabel();
        lblEdicion = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblNotas = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        listAutores = new javax.swing.JList<>();
        jScrollPane13 = new javax.swing.JScrollPane();
        listTemas = new javax.swing.JList<>();
        lblAutor = new javax.swing.JLabel();
        lblTemas = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();

        setClosable(true);
        setTitle("[Thot] - Gestión de Libro");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sv/edu/udb/data/thot.jpg"))); // NOI18N

        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMain);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Patrón a buscar");

        cmbBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Titulo", "ISBN", "Edición" }));

        lblFiltro.setText("Filtro");

        btnHabilitar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHabilitar.setText("Modificar");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createSequentialGroup()
                                .addComponent(lblFiltro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTableLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiltro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(56, 56, 56)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDataFrm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlDataFrm.setEnabled(false);

        txtTitulo.setText("titulo");

        txtIsbn.setEditable(false);
        txtIsbn.setText("isbn");

        txtEdicion.setText("edicion");

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setAutoscrolls(false);
        jScrollPane10.setViewportView(txtDescripcion);

        txtNotas.setColumns(20);
        txtNotas.setLineWrap(true);
        txtNotas.setRows(5);
        txtNotas.setAutoscrolls(false);
        jScrollPane11.setViewportView(txtNotas);

        btnImagen.setText("Seleccionar imagen");
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        txtImagen.setEditable(false);
        txtImagen.setText("[Opcional]");

        cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbImprentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblTitulo.setText("Título");

        lblIsb.setText("ISBN");

        lblEdicion.setText("Edición");

        lblDescripcion.setText("Descripción");

        lblNotas.setText("Notas");

        listAutores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane12.setViewportView(listAutores);

        listTemas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane13.setViewportView(listTemas);

        lblAutor.setText("Selecciona uno o más autores");

        lblTemas.setText("Selecciona uno o más temas");

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar.setText("Guardar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataFrmLayout = new javax.swing.GroupLayout(pnlDataFrm);
        pnlDataFrm.setLayout(pnlDataFrmLayout);
        pnlDataFrmLayout.setHorizontalGroup(
            pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDataFrmLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(470, 470, 470))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDataFrmLayout.createSequentialGroup()
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbCategorias, javax.swing.GroupLayout.Alignment.LEADING, 0, 238, Short.MAX_VALUE)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(37, 37, 37)
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIsb)
                            .addComponent(txtIsbn)
                            .addComponent(cmbImprentas, 0, 219, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEdicion)
                            .addComponent(txtEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addComponent(btnImagen)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtImagen)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDataFrmLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDataFrmLayout.createSequentialGroup()
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(lblAutor))
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(lblDescripcion)))
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(lblNotas))
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(lblTemas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)))
                .addContainerGap())
        );
        pnlDataFrmLayout.setVerticalGroup(
            pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDataFrmLayout.createSequentialGroup()
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImagen)
                            .addComponent(lblEdicion)
                            .addComponent(lblIsb)
                            .addComponent(lblTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbImprentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(90, 90, 90))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataFrmLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNotas)
                                    .addComponent(lblDescripcion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)))
                        .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addComponent(lblAutor)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDataFrmLayout.createSequentialGroup()
                                .addComponent(lblTemas)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnCancelar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDataFrm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDataFrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed

    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(cmbBuscador.getSelectedItem().toString().equals("Título")){
            libros = Libro_Controller.BuscarLibros("titulo", txtBuscar.getText());
        }else if(cmbBuscador.getSelectedItem().toString().equals("ISBN")){
            libros = Libro_Controller.BuscarLibros("isbn", txtBuscar.getText());
        }else if(cmbBuscador.getSelectedItem().toString().equals("Edición")){
            libros = Libro_Controller.BuscarLibros("edicion", txtBuscar.getText());
        }
        
        if(txtBuscar.getText().length() == 1){
            libros = Libro_Controller.obtenerLibros();
        }
        
        cargarDatos();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMainMouseClicked
        if(libros.size() > 0 && !editFlag){
            int fila = tblMain.rowAtPoint(evt.getPoint());
            if(fila > -1){
                idLibroSeleccionado = libros.get(fila).getIdLibro();
                cargarLibro(libros.get(fila));
                btnHabilitar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }else{
                btnHabilitar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tblMainMouseClicked

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        fileCImagen.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

        int returnVal = fileCImagen.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            imgFile = fileCImagen.getSelectedFile();

            txtImagen.setText(imgFile.getAbsolutePath());
        } else {
            imgFile = null;
        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        initData();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed
        editFlag = true;
        
        pnlTable.setEnabled(false);
        pnlDataFrm.setEnabled(true);
        btnHabilitar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        tblMain.setEnabled(false);
        tblMain.setRowSelectionAllowed(false);
        txtBuscar.setEnabled(false);
        cmbBuscador.setEnabled(false);
        
        txtTitulo.setEnabled(true);
        txtIsbn.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtNotas.setEnabled(true);
        txtEdicion.setEnabled(true);
        txtImagen.setEnabled(true);
        cmbCategorias.setEnabled(true);
        cmbImprentas.setEnabled(true);
        listAutores.setEnabled(true);
        listTemas.setEnabled(true);
        btnImagen.setEnabled(true);
        btnModificar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String img = "";
        if(imgFile != null){
            img = idLibroSeleccionado + "." + getFileExtension(imgFile);
            if(!saveImage(idLibroSeleccionado)){
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al subir la imagen :$", "[Thot] - Registro de Libro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            img = new Libro(idLibroSeleccionado, false).getImagen();
        }
        
        int[] idAutores = listAutores.getSelectedIndices();
        int[] idTemas = listTemas.getSelectedIndices();

        List<Autor> _auxA = new ArrayList();
        List<Tema> _auxT = new ArrayList();

        validData = idAutores.length > 0 && idTemas.length > 0;
        validData = cmbCategorias.getSelectedIndex() != -1 && cmbImprentas.getSelectedIndex() != -1;
        validData = cmbCategorias.getSelectedIndex() != -1 && cmbImprentas.getSelectedIndex() != -1;
        validData = cmbCategorias.getSelectedIndex() != 0 && cmbImprentas.getSelectedIndex() != 0;

        if (
            Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z áéíóú,0-9.]*$", txtTitulo.getText(), "Ingresa un título válido!", "[Thot] - Registro de Libro")
            && Validacion.validar("^ISBN \\d{3}-\\d-\\d{3}-\\d{5}-\\d$", txtIsbn.getText(), "Ingresa un ISBN válido!", "[Thot] - Registro de Libro")
            && Validacion.validar("^[0-9]*$", txtEdicion.getText(), "Ingresa una edición válida!", "[Thot] - Registro de Libro")
            && Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú,0-9.-]*$", txtDescripcion.getText(), "Ingresa una descripción válida!", "[Thot] - Registro de Libro")
            && Validacion.validar("^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú,0-9.-]*$", txtNotas.getText(), "Ingresa notas válidos!", "[Thot] - Registro de Libro")) {
            
            if (validData) {
                for (int i = 0; i < idAutores.length; i++) {
                    _auxA.add(_a.get(idAutores[i]));
                }

                for (int i = 0; i < idTemas.length; i++) {
                    _auxT.add(_t.get(idTemas[i]));
                }

                System.out.println(_auxA.toString());
                System.out.println(_auxT.toString());
                
                Libro _l = new Libro(
                        idLibroSeleccionado,
                        txtTitulo.getText().trim(),
                        txtIsbn.getText().trim(),
                        txtEdicion.getText().trim(),
                        txtDescripcion.getText().trim(),
                        txtNotas.getText().trim(),
                        img,
                        _i.get(cmbImprentas.getSelectedIndex() - 1),
                        _c.get(cmbCategorias.getSelectedIndex() - 1),
                        _auxA, _auxT
                );

                if (Libro_Controller.modificar(_l)) {
                    JOptionPane.showMessageDialog(this, "El libro ha sido modificado éxitosamente!", "[Thot] - Gestión de Libro", JOptionPane.DEFAULT_OPTION);
                    initData();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error al modificar el libro :$", "[Thot] - Gestión de Libro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingresa todos los datos requeridos!", "[Thot] - Gestión de Libro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int r = JOptionPane.showConfirmDialog(this, "Estás seguro que deseas eliminar este libro?", "[Thot] - Gestión de Libro", JOptionPane.INFORMATION_MESSAGE);
        
        if(r == JOptionPane.YES_OPTION){
            if (Libro_Controller.eliminar(new Libro(idLibroSeleccionado, false))) {
                JOptionPane.showMessageDialog(this, "El libro ha sido eliminado éxitosamente!", "[Thot] - Gestión de Libro", JOptionPane.DEFAULT_OPTION);
                initData();
                cargarDatos();
            }else{
                JOptionPane.showMessageDialog(this, "No se puede eliminar este libro porque posee reservaciones!", "[Thot] - Gestión de Libro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbBuscador;
    private javax.swing.JComboBox<String> cmbCategorias;
    private javax.swing.JComboBox<String> cmbImprentas;
    private javax.swing.JFileChooser fileCImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEdicion;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblIsb;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JLabel lblTemas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listAutores;
    private javax.swing.JList<String> listTemas;
    private javax.swing.JPanel pnlDataFrm;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTable tblMain;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEdicion;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextArea txtNotas;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}

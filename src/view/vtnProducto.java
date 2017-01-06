/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CategoriaDao;
import dao.ProductoDao;
import dao.ProveedorDao;
import dao.UnidadMedidaDao;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import mapeos.Categoria;
import mapeos.Producto;
import mapeos.Proveedor;
import mapeos.UnidadMedida;
import mapeos.Usuario;
import util.Validaciones;

/**
 *
 * @author Reynaldo
 */
public class vtnProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnProducto
     */
    private Producto ObjProducto = new Producto();//Objeto global
    public static String validaVentana;//variable que valida si la ventana ya se encuantra activa
    

    public vtnProducto() {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        cargarCategorias();//llenando la el combo que contiene a las categorias
        cargarProveedores();//cargando el combo que contiene los proveedoresno
        cargarUnidadesMedida();//cargando las unidades de medida
        listarDatos();
        bloquearCamposProducto();
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        setIcono();
    }

    //metodo que inserta el icono de usuario por defecto
    public void setIcono() {
        ImageIcon imagenUser = new ImageIcon(getClass().getResource("/images/producto3.png"));
        lbmProducto.setIcon(imagenUser);
    }

    //Metodo que carga los elementos
    public void cargarCategorias() {

        try {
            CategoriaDao catDao = new CategoriaDao();
            List<Categoria> lista = catDao.listarCategoria();
            if (lista.size() != 0) {
                for (int i = 0; i < lista.size(); i++) {
                    comboCategoria.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe tener por lo menos una categoria registrada..!! ", null, JOptionPane.ERROR_MESSAGE);
                comboCategoria.setEnabled(false);
                btnRegPro.setEnabled(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que carga la lista de proveedores
    public void cargarProveedores() {
        try {
            ProveedorDao provDao = new ProveedorDao();
            List<Proveedor> listaP = provDao.listarProveedores();
            if (listaP.size() != 0) {
                for (int i = 0; i < listaP.size(); i++) {
                    comboProv.addItem(listaP.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe tener por lo menos un proveedor registrado ", null, JOptionPane.ERROR_MESSAGE);
                comboCategoria.setEnabled(false);
                btnRegPro.setEnabled(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que carga la lista de Unidades de medida
    public void cargarUnidadesMedida() {
        try {
            UnidadMedidaDao provDao = new UnidadMedidaDao();
            List<UnidadMedida> listaP = provDao.listarUnidades();
            if (listaP.size() != 0) {
                for (int i = 0; i < listaP.size(); i++) {
                    comboUnidades.addItem(listaP.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe tener por menos una unidad de medida registrada", null, JOptionPane.ERROR_MESSAGE);
                comboUnidades.setEnabled(false);
                btnRegPro.setEnabled(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos() {
        txtNomPro.setText("");
        txtCodigoPro.setText("");
        txtDesPro.setText("");
        txtImgProducto.setText("");
        txtMarcaPro.setText("");
        txtPrecioPro.setText("0");
        txtStockMinimo.setValue(1);
        txtStockMaximo.setValue(1);
    }

    //Metodo que bloquea los campos de texto
    public void bloquearCamposProducto() {
        txtNomPro.setEnabled(false);
        txtCodigoPro.setEnabled(false);
        txtDesPro.setEnabled(false);
        txtImgProducto.setEnabled(false);
        txtMarcaPro.setEnabled(false);
        RacInac.setEnabled(false);
        RacPro.setEnabled(false);
        comboCategoria.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnRegPro.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        comboProv.setEnabled(false);
        comboUnidades.setEnabled(false);
        txtPrecioPro.setEnabled(false);
        //--------botones de accion-----
        btnEdicion.setEnabled(true);
        btnNuevo.setEnabled(true);
        //--------fin botones de accion---
        txtStockMinimo.setEnabled(false);
        txtStockMaximo.setEnabled(false);
    }

    //Metodo que habilita los campos de texto
    public void habilitarCamposProducto() {
        txtNomPro.setEnabled(true);
        txtCodigoPro.setEnabled(true);
        txtDesPro.setEnabled(true);
        txtImgProducto.setEnabled(true);
        txtMarcaPro.setEnabled(true);
        lbmProducto.setEnabled(true);
        RacInac.setEnabled(true);
        RacPro.setEnabled(true);
        comboCategoria.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnRegPro.setEnabled(true);
        comboProv.setEnabled(true);
        //txtPrecioPro.setEnabled(true);
        txtStockMinimo.setEnabled(true);
        txtStockMaximo.setEnabled(true);
        comboUnidades.setEnabled(true);
    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarDatos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProductos.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaProductos);
            //realizando la consulta para realizar el listado de los datos
            ProductoDao proDao = new ProductoDao();
            List<Object[]> lista = proDao.listarProductos2();
            Object[] fila = new Object[modelo.getColumnCount()];

            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i)[0];//id
                fila[1] = lista.get(i)[1];//nombre
                fila[2] = lista.get(i)[2];
                fila[3] = lista.get(i)[3];
                fila[4] = lista.get(i)[4];
                fila[5] = lista.get(i)[5];
                fila[6] = lista.get(i)[6];//categoria
                fila[7] = lista.get(i)[7];//proveedor

                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
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

        grupoProdc = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEdicion = new javax.swing.JToggleButton();
        jLabel14 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbmProducto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNomPro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesPro = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtMarcaPro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        RacPro = new javax.swing.JRadioButton();
        RacInac = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        comboProv = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        comboUnidades = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtPrecioPro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        txtStockMaximo = new javax.swing.JSpinner();
        txtImgProducto = new javax.swing.JTextField();
        btnRegPro = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setTitle("Gestión de productos");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCIÓN", "MARCA", "CODIGO", "ESTADO", "CATEGORIA", "PROVEEDOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProductosMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/modificar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEdicion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEdicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/habilitar.png"))); // NOI18N
        btnEdicion.setText("Habilitar Edición");
        btnEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdicionActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("LISTADO DE PRODUCTOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(btnEdicion)
                .addGap(33, 33, 33)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 192, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdicion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel9.setForeground(new java.awt.Color(204, 0, 51));
        jLabel9.setText("Click para seleccionar una imagen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Imagen:");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbmProducto.setForeground(new java.awt.Color(153, 153, 153));
        lbmProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbmProducto.setToolTipText("Click para seleccionar una imagen..");
        lbmProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbmProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbmProductoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        txtNomPro.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Descripcion:");

        txtDesPro.setColumns(20);
        txtDesPro.setRows(5);
        jScrollPane1.setViewportView(txtDesPro);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Marca:");

        txtMarcaPro.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Codigo:");

        txtCodigoPro.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Estado:");

        grupoProdc.add(RacPro);
        RacPro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RacPro.setText("Activo");

        grupoProdc.add(RacInac);
        RacInac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RacInac.setText("Inactivo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Categoria:");

        comboCategoria.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Proveedor:");

        comboProv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        comboProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProvActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Unidad de medida:");

        comboUnidades.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomPro))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(RacPro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(RacInac)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtMarcaPro)
                                    .addComponent(txtCodigoPro))))
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboCategoria, 0, 134, Short.MAX_VALUE)
                            .addComponent(comboProv, 0, 134, Short.MAX_VALUE)
                            .addComponent(comboUnidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel3)))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMarcaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RacPro)
                    .addComponent(jLabel6)
                    .addComponent(RacInac))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel5);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Precio:");

        txtPrecioPro.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtPrecioPro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioPro.setText("0.0");
        txtPrecioPro.setToolTipText("");
        txtPrecioPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Stock minimo:");

        txtStockMinimo.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtStockMinimo.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Stock maximo:");

        txtStockMaximo.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtStockMaximo.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        txtImgProducto.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(50, 50, 50)
                            .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtStockMaximo))))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtImgProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtStockMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116)
                .addComponent(txtImgProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Costos", jPanel6);

        btnRegPro.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnRegPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        btnRegPro.setText("Aceptar");
        btnRegPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegProActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 0, 51));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTIÓN DE PRODUCTOS");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/producto2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegPro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRegPro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int y = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?");
        try {
            if (y == JOptionPane.YES_OPTION) {
                ProductoDao pr = new ProductoDao();
                if (pr.eliminarProducto(this.getObjProducto())) {
                    JOptionPane.showMessageDialog(this, "Eliminacion correcta", null, JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    bloquearCamposProducto();
                    listarDatos();
                    setIcono();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hay productos asociados a esta categoria\npor lo que no se puede eliminar..!!", null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        bloquearCamposProducto();
        btnEdicion.setEnabled(true);
        setIcono();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegProActionPerformed
        // TODO add your handling code here:
        try {
            txtPrecioPro.setEnabled(false);//inhabilitando el campo precio puesto que este campo se llena al primer ingreso

            List<String> validar = new ArrayList<>();
            validar.add(txtCodigoPro.getText());
            validar.add(txtDesPro.getText());
            validar.add(txtMarcaPro.getText());
            validar.add(txtNomPro.getText());

            ProductoDao p = new ProductoDao();
            if (p.buscarCodigoRepetido(txtCodigoPro.getText()) == false)//validando si el codigo se encuantra registrado 
            {

                if (Validaciones.validarCampos(validar)) {
                    //recuperando la categoria
                    CategoriaDao catDao = new CategoriaDao();
                    Categoria cat = catDao.buscarCategoriaLista(comboCategoria.getSelectedItem().toString());
                //fin de la recuperacion de la categoria

                    //recuperando el proveedor
                    ProveedorDao provDao = new ProveedorDao();
                    Proveedor prov = provDao.buscarProveedor(comboProv.getSelectedItem().toString());
                //fin de la recuperacion del proveedor

                    //recuperando el la unidad de medida
                    UnidadMedidaDao unDao = new UnidadMedidaDao();
                    UnidadMedida unidad = unDao.buscarUnidadMedida(comboUnidades.getSelectedItem().toString());
                    //fin de la recuperacion de la unidad de medida

                    Producto pro = new Producto();

                    if (RacPro.isSelected()) {
                        pro.setEstado("Activo");
                    } else if (RacInac.isSelected()) {
                        pro.setEstado("Inactivo");
                    }

                    pro.setNombre(txtNomPro.getText());
                    pro.setDescripcion(txtDesPro.getText());
                    pro.setMarca(txtMarcaPro.getText());
                    pro.setPrecio(txtPrecioPro.getText());
                    pro.setCodigoproducto(txtCodigoPro.getText());

                    pro.setStockMinimo(Integer.parseInt(txtStockMinimo.getValue().toString()));
                    pro.setStockMaximo(Integer.parseInt(txtStockMaximo.getValue().toString()));

                    //insertando las referecnias a otras tablas
                    pro.setCategoria(cat);
                    pro.setProveedor(prov);
                    pro.setUnidadMedida(unidad);
                    //fin de la insercion de referencias a otras tabalas

                    ProductoDao proDao = new ProductoDao();

                    if (!txtImgProducto.getText().equals("")) {
                        File archivoImagen = new File(txtImgProducto.getText());//recuperando la url de la imagen
                        byte[] bytefile = new byte[(int) archivoImagen.length()];
                        FileInputStream fs = new FileInputStream(archivoImagen);
                        fs.read(bytefile);
                        fs.close();
                        pro.setImagen(bytefile);

                        if (Integer.parseInt(txtStockMinimo.getValue().toString()) < Integer.parseInt(txtStockMaximo.getValue().toString())) {
                            if (proDao.registarProducto(pro)) {
                                JOptionPane.showMessageDialog(this, "Registro de producto correcto..!!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                                limpiarCampos();
                                listarDatos();
                                bloquearCamposProducto();
                                limpiarCampos();
                                setIcono();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Tome en cuenta:\nEl Stock Minimo no puede ser mayor al Stock Maximo", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Seleccione una imagen", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "mensaje", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El codigo ingresado ya esta registrado..!!", "mensaje", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegProActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        habilitarCamposProducto();
        limpiarCampos();
        RacPro.setSelected(true);
        btnEdicion.setEnabled(false);
        setIcono();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tablaProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaProductos.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaProductos.getSelectedRow(), 0));
        ProductoDao proDao = new ProductoDao();
        try {
            Producto pro = proDao.buscarProducto(Integer.parseInt(dato));
            this.setObjProducto(pro);//insertando el objeto recuperado por la consulta
            txtNomPro.setText(pro.getNombre());
            txtDesPro.setText(pro.getDescripcion());
            txtMarcaPro.setText(pro.getMarca());
            txtPrecioPro.setText(pro.getPrecio());
            txtCodigoPro.setText(pro.getCodigoproducto());
            txtStockMinimo.setValue(pro.getStockMinimo());
            txtStockMaximo.setValue(pro.getStockMaximo());
            //lbmProducto.setText("");
            //recuperando la imagen y convirtiendo en ImageIco
            InputStream is = new ByteArrayInputStream(pro.getImagen());
            BufferedImage image = ImageIO.read(is);
            ImageIcon ico = new ImageIcon(image);
            ImageIcon icono = new ImageIcon(ico.getImage().getScaledInstance(lbmProducto.getWidth(), lbmProducto.getHeight(), Image.SCALE_DEFAULT));
            lbmProducto.setIcon(icono);
            //fin de la recuperacion y muetra de la imagen

            if (pro.getEstado().equals("Activo")) {
                RacPro.setSelected(true);
            } else if (pro.getEstado().equals("Inactivo")) {
                RacInac.setSelected(true);
            }
            //recuperando la categoria
            String tipo = String.valueOf(tm.getValueAt(tablaProductos.getSelectedRow(), 6));
            comboCategoria.setSelectedItem(tipo);
            //recuperando el proveedor
            String prv = String.valueOf(tm.getValueAt(tablaProductos.getSelectedRow(), 7));
            comboProv.setSelectedItem(prv);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaProductosMousePressed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        try 
        {
            List<String> validar = new ArrayList<>();
            validar.add(txtCodigoPro.getText());
            validar.add(txtDesPro.getText());
            //validar.add(txtImgProducto.getText());
            validar.add(txtMarcaPro.getText());
            validar.add(txtNomPro.getText());
            validar.add(txtPrecioPro.getText());

            ProductoDao p = new ProductoDao();

//            if (p.buscarCodigoRepetido(txtCodigoPro.getText()) == false)//validando si el codigo se encuantra registrado 
//            {

                if (Validaciones.validarCampos(validar)) {

                    Producto pro = this.getObjProducto();//recuperando el objeto recuperado desde la tabla
                    pro.setNombre(txtNomPro.getText());
                    pro.setDescripcion(txtDesPro.getText());
                    pro.setMarca(txtMarcaPro.getText());
                    pro.setPrecio(txtPrecioPro.getText());
                    pro.setCodigoproducto(txtCodigoPro.getText());

                    pro.setStockMinimo(Integer.parseInt(txtStockMinimo.getValue().toString()));
                    pro.setStockMaximo(Integer.parseInt(txtStockMaximo.getValue().toString()));

                    String estado = "";
                    if (RacPro.isSelected()) {
                        estado = "Activo";
                    } else if (RacInac.isSelected()) {
                        estado = "Inactivo";
                    }
                    pro.setEstado(estado);

                    //recuperando la categoria
                    CategoriaDao catDao = new CategoriaDao();
                    Categoria cat = catDao.buscarCategoriaLista(comboCategoria.getSelectedItem().toString());
                //fin de la recuperacion de la categoria

                    //recuperando el proveedor
                    ProveedorDao provDao = new ProveedorDao();
                    Proveedor prov = provDao.buscarProveedor(comboProv.getSelectedItem().toString());
                //fin de la recuperacion del proveedor

                    //recuperando el la unidad de medida
                    UnidadMedidaDao unDao = new UnidadMedidaDao();
                    UnidadMedida unidad = unDao.buscarUnidadMedida(comboUnidades.getSelectedItem().toString());
                    //fin de la recuperacion de la unidad de medida

                    pro.setCategoria(cat);//insertando la categoria al producto
                    pro.setProveedor(prov);//insertando el proveedor al producto
                    pro.setUnidadMedida(unidad);//insertando la unidad de medida

                    //Verificando si selecciono otra imagen
                    if (!txtImgProducto.getText().equals("")) {
                        File archivoImagen = new File(txtImgProducto.getText());//recuperando la url de la imagen
                        byte[] bytefile = new byte[(int) archivoImagen.length()];
                        FileInputStream fs = new FileInputStream(archivoImagen);
                        fs.read(bytefile);
                        fs.close();
                        pro.setImagen(bytefile);
                    }
                    //fin de la verificacion de la seleccion de la imagen

                    ProductoDao proDao = new ProductoDao();
                    if (proDao.actualizarProducto(pro)) {
                        JOptionPane.showMessageDialog(this, "Actualización correcta", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                        bloquearCamposProducto();
                        listarDatos();
                        setIcono();
                        btnEdicion.setText("Habilitar Edición");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
//            } else {
//                JOptionPane.showMessageDialog(this, "El codigo ingresado ya esta registrado..!!", "mensaje", JOptionPane.ERROR_MESSAGE);
//            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdicionActionPerformed
        // TODO add your handling code here:
        if (btnEdicion.isSelected()) {
            habilitarCamposProducto();
            btnNuevo.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnRegPro.setEnabled(false);
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnEdicion.setText("  Cancelar  ");
        } 
        else
        {
            limpiarCampos();
            bloquearCamposProducto();
            btnEdicion.setEnabled(true);
            tablaProductos.setEnabled(true);
            btnEdicion.setText("Habilitar Edición");
            setIcono();
        }
    }//GEN-LAST:event_btnEdicionActionPerformed

    private void comboProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProvActionPerformed

    private void lbmProductoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmProductoMousePressed
        // TODO add your handling code here:
        JFileChooser abrir = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        abrir.setFileFilter(filtroImagen);

        abrir.setDialogTitle("Seleccionar imagen..");
        int ventana = abrir.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = abrir.getSelectedFile();
            txtImgProducto.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtImgProducto.getText());//recuperando la URL de la imagen y convirtiendola en un objeto de tipo imagen
            foto = foto.getScaledInstance(lbmProducto.getWidth(), lbmProducto.getHeight(), Image.SCALE_DEFAULT);
            lbmProducto.setText("");
            lbmProducto.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_lbmProductoMousePressed

    private void txtPrecioProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyTyped
        // TODO add your handling code here:
        // TODO add your handling code here:
        char c = evt.getKeyChar();
//        if (c < '0' || c > '9') {
//            evt.consume();
//        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecioPro.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioProKeyTyped

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana=null;
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RacInac;
    private javax.swing.JRadioButton RacPro;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JToggleButton btnEdicion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegPro;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JComboBox comboProv;
    private javax.swing.JComboBox comboUnidades;
    private javax.swing.ButtonGroup grupoProdc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbmProducto;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextArea txtDesPro;
    private javax.swing.JTextField txtImgProducto;
    private javax.swing.JTextField txtMarcaPro;
    private javax.swing.JTextField txtNomPro;
    private javax.swing.JTextField txtPrecioPro;
    private javax.swing.JSpinner txtStockMaximo;
    private javax.swing.JSpinner txtStockMinimo;
    // End of variables declaration//GEN-END:variables

    public Producto getObjProducto() {
        return ObjProducto;
    }

    public void setObjProducto(Producto ObjProducto) {
        this.ObjProducto = ObjProducto;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.almacen;

import dao.AlmacenDao;
import dao.ComprasDao;
import dao.InventarioDao;
import dao.ProductoDao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import mapeos.Almacen;
import mapeos.Compra;
import mapeos.Fifo;
import mapeos.Inventario;
import util.ContadorPanelActividad;
import util.Operaciones;
import util.Validaciones;
import static view.almacen.vtnAjustes.validaVentana;
import static view.almacen.vtnIngresoalmacen.tablaIngresoAlmacen;
import static view.OC.vtnOD.txtNuOD;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class vtnInventarioInicial extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnInventarioInicial
     */
    public static String validaVentana;
    private int ingreso = 0;//bandera que contiene 

    public vtnInventarioInicial(int bandera) {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        ingreso = bandera;//variable que controla la invocacion de la ventana (VERIFICA SI SE INVOCO DESDE INGRESO ALMACEN O INVENTARIO INICIAL)
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        cargarAlmacen();
    }

    //constructor para instanciar la ventana solo desde el inventario inicial no
    //desde ingreso a almacen
    public vtnInventarioInicial() {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        cargarAlmacen();
    }

    //Metodo que carga los elementos del almacen
    public void cargarAlmacen() {
        try {
            AlmacenDao almDao = new AlmacenDao();
            List<Almacen> lista = almDao.listarAlmacen();
            if (lista.size() != 0) {
                for (int i = 0; i < lista.size(); i++) {
                    comboAlmacen.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe tener por lo menos un almacen registrado..!! ", null, JOptionPane.ERROR_MESSAGE);
                comboAlmacen.setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarCompras() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) vtnIngresoalmacen.tablaIngresoAlmacen.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(vtnIngresoalmacen.tablaIngresoAlmacen);
            //realizando la consulta para realizar el listado de los datos
            ComprasDao cmDao = new ComprasDao();
            List<Object[]> lista = cmDao.listarCompras();
            if (lista.size() > 0) {
                Object[] fila = new Object[modelo.getColumnCount()];
                TableColumnModel columnModel = vtnIngresoalmacen.tablaIngresoAlmacen.getColumnModel();
                boolean pivote = false;
                for (int i = 0; i < lista.size(); i++) {
                    if (Operaciones.verEstadoIngresoDetalleCompra((int) lista.get(i)[0])) {
                        //columnModel.getColumn(i).setPreferredWidth(20);
                        fila[0] = lista.get(i)[0];//numero de compra
                        // fila[1] = lista.get(i)[1];//fecha
                        //fila[2] = lista.get(i)[2];//fecha entrega
                        fila[1] = Operaciones.redondear(Double.parseDouble((String) lista.get(i)[3]));//precio total
                        fila[2] = lista.get(i)[7];//responsable
                        fila[3] = lista.get(i)[5];//estado
                        fila[4] = lista.get(i)[8];//tipo doc
                        fila[5] = lista.get(i)[9];//numero doc

                        modelo.addRow(fila);
                        pivote = true;
                    }
                }
                if (pivote == false) {
                    JOptionPane.showMessageDialog(null, "No tiene compras para adicionar al inventario", null, JOptionPane.WARNING_MESSAGE);
                    vtnIngresoalmacen.btnIngreso.setEnabled(false);
                    vtnIngresoalmacen.btnSalir.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No tiene compras para adicionar al inventario", null, JOptionPane.WARNING_MESSAGE);
                vtnIngresoalmacen.btnIngreso.setEnabled(false);
                vtnIngresoalmacen.btnSalir.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error---->" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que limpia los datos de la tabla entes de realizar otro listado
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

    //Metodo que limpia los campos de la ventana inventarios inicial
    public void limpiarCampos() {
        vtnIngresoalmacen.comboAlmacen.setSelectedIndex(0);
        vtnIngresoalmacen.txtProducto.setText("");
        vtnIngresoalmacen.txtCantidad.setText("");
        vtnIngresoalmacen.txtPrecio.setText("");
        vtnIngresoalmacen.txtSubTotal.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboAlmacen = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        btnSelProducto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtProductoInv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        SpCantidad = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboMetodo = new javax.swing.JComboBox();

        setTitle("Esteblecer inventario inicial");
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

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INVENTARIO INICIAL");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/OC.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Seleccione almacen:");

        comboAlmacen.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        comboAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "********" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Buscar Producto:");

        btnSelProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/busqueda.png"))); // NOI18N
        btnSelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelProductoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos inventario inicial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 15))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Producto:");

        txtProductoInv.setEditable(false);
        txtProductoInv.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Cantidad:");

        SpCantidad.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        SpCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 0, 100000, 1));
        SpCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                SpCantidadFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Precio:");

        txtPrecio.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setText("0.0");
        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioFocusLost(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Subtotal:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0.0");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/cancelar.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jButton2)
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtProductoInv, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel8)
                        .addGap(27, 27, 27)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal)))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProductoInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Metodo:");

        comboMetodo.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        comboMetodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*******", "PESP", "PP" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboAlmacen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(btnSelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        validaVentana = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelProductoActionPerformed
        // TODO add your handling code here:
        String varValicadion = vtnProductoInv.validaVentana;//recuperando el valor de la variable de validacion de la ventana
        if (varValicadion == null) {
            vtnProductoInv listaPro = new vtnProductoInv();
            vtnPrincipal.sysMDI.add(listaPro);
            listaPro.setClosable(true);//si se puede cerra la ventana
            listaPro.setTitle("Seleccione un producto");
            //listaPro.setLocationRelativeTo(null);
            listaPro.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "La ventana de adicionar productos ya esta activa..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelProductoActionPerformed

    private void SpCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SpCantidadFocusLost
        // TODO add your handling code here:
        Double total = Double.parseDouble(txtPrecio.getText()) * ((int) SpCantidad.getValue());
        txtTotal.setText(Operaciones.redondear(total).toString());
    }//GEN-LAST:event_SpCantidadFocusLost

    private void txtPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusLost
        // TODO add your handling code here:
        Double total = Double.parseDouble(txtPrecio.getText()) * ((int) SpCantidad.getValue());
        txtTotal.setText(Operaciones.redondear(total).toString());
    }//GEN-LAST:event_txtPrecioFocusLost

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
//        if (c < '0' || c > '9') {
//            evt.consume();
//        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecio.getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_txtPrecioKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Validando los campos
        List<String> validar = new ArrayList<>();
        validar.add(txtProductoInv.getText());
        validar.add(txtPrecio.getText());
        if (Validaciones.validarCampos(validar)) 
        {
            if (comboMetodo.getSelectedIndex() != 0) 
            {
                if (comboAlmacen.getSelectedIndex() != 0) 
                {
                    if (!txtPrecio.getText().equals("0.0")) 
                    {
                        try 
                        {
                            if (new InventarioDao().existeProducto(new AlmacenDao().buscarAlmacenId(comboAlmacen.getSelectedItem().toString()), new ProductoDao().buscarProductoByNombre(txtProductoInv.getText())) == null) 
                            {
                                AlmacenDao almDao = new AlmacenDao();
                                InventarioDao invDao = new InventarioDao();
                                Inventario inv = new Inventario();
                                //setenado los valores del inventarios
                                inv.setAlmacen_idalmacen(almDao.buscarAlmacenId(comboAlmacen.getSelectedItem().toString()));//ingreso idAlmacen
                                inv.setProducto_idproducto(new ProductoDao().buscarProductoByNombre(txtProductoInv.getText()));
                                inv.setStock(SpCantidad.getValue().toString());
                                if (comboMetodo.getSelectedItem().toString().equals("PESP")) {
                                    inv.setTipoInventario(1);//PESP=1
                                } else {
                                    inv.setTipoInventario(0);//PP=0
                                }
                                //generando el registro en el Kardex
                                Set<Fifo> items = new HashSet<>();

                                //Codigo de insercion del invetario inicial
                                Fifo fifo = new Fifo();
                                fifo.setDocumento("INVENTARIO INICIAL");
                                // fifo.getInventario().setIdinventario(0);admin 
                                fifo.setNdocumento(0);
                                fifo.setDetalle("Inventario inicial");
                                //insertando los valores de la compra
                                fifo.setCant_entrada((int) SpCantidad.getValue());
                                fifo.setPrecio_entrada(txtPrecio.getText());
                                Double total = Double.parseDouble(txtPrecio.getText()) * ((int) SpCantidad.getValue());
                                fifo.setP_total_entrada(Operaciones.redondear(total).toString());
                                //---------------------------------------------------------------
                                
                                //insertando los valores del saldo
                                fifo.setCant_saldo((int) SpCantidad.getValue());
                                fifo.setPrecio_saldo(txtPrecio.getText());
                                fifo.setP_total_saldo(Operaciones.redondear(total).toString());
                                //--------------------------------------------------------------

                                items.add(fifo);
                                inv.setFifos(items);
                                fifo.setInventario(inv);
                                if (invDao.registarInventario(inv)) 
                                {
                                    JOptionPane.showMessageDialog(this, "Inventario inicial realizado con exito..!!");
                                    
//                                //iniciando los campos de ingreso
//                                txtProductoInv.setText("");
//                                txtPrecio.setText("0.0");
//                                txtTotal.setText("");
//                                comboAlmacen.setSelectedIndex(0);
//                                SpCantidad.setValue(0);
//                                //fin de la inicializacion de los campos
                                    //limpiando la tabla detalle ingreso si se invoco
                                    //solo ingreso a almacen
                                    if (ingreso == 1) 
                                    {
                                        //LIMPIANDO LA TABLA DETALLE COMPRA
                                        ComprasDao cmDao = new ComprasDao();
                                        Compra cm = cmDao.buscarCompra(Integer.parseInt(String.valueOf(vtnIngresoalmacen.tablaIngresoAlmacen.getValueAt(vtnIngresoalmacen.tablaIngresoAlmacen.getSelectedRow(), 0))));
                                        List<Object[]> detalle = new ArrayList<>();//recuperando el listado de los datos
                                        detalle = cmDao.listarDetalleIngresoAlmacen(cm.getIdcompra());
                                        for (int i = 0; i < detalle.size(); ++i) 
                                        {
                                            if (vtnIngresoalmacen.tablaDetalleCompraAlmacen.getValueAt(vtnIngresoalmacen.tablaDetalleCompraAlmacen.getSelectedRow(), 0) == detalle.get(i)[1]) {
                                                //Actualizando el campo estado de la tabla detalleCompra
                                                Operaciones.actualizaEstadoDetalleCompra((int) detalle.get(i)[0]);

                                                //Operaciones.actualizaPrecioProducto(Integer.parseInt(String.valueOf(vtnIngresoalmacen.tablaDetalleCompraAlmacen.getValueAt(vtnIngresoalmacen.tablaDetalleCompraAlmacen.getSelectedRow(), 0))), String.valueOf(vtnIngresoalmacen.tablaDetalleCompraAlmacen.getValueAt(vtnIngresoalmacen.tablaDetalleCompraAlmacen.getSelectedRow(), 3)));
                                                cmDao.actualizarCompra(cm);//refrescando la transaccion con la BD
                                            }
                                        }
                                        ContadorPanelActividad.contadorIA();//actualizando el contador
                                        limpiarTabla(vtnIngresoalmacen.tablaDetalleCompraAlmacen);
                                        listarCompras();
                                        limpiarCampos();
                                    }
                                    //Actualizando el precio del producto con el precio de la ultima compra
                                    int idProd = new ProductoDao().buscarProductoByNombre(txtProductoInv.getText());
                                    //JOptionPane.showMessageDialog(this, "-->producto=="+idProd+"precio-->"+txtPrecio.getText());
                                    Operaciones.actualizaPrecioProducto(idProd, txtPrecio.getText());

                                    this.dispose();
                                    validaVentana = null;
                                } else {
                                    JOptionPane.showMessageDialog(this, "Error al registrar el inventario inicial", "Mensaje", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "El producto ya tiene esta en inventario\npor lo que no se puede generar un inventario inicial", "Mensaje", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error al generar el inventario inicial" + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "El precio no puede ser 0", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un almacen..", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione el metodo para el inventario..", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JSpinner SpCantidad;
    public static javax.swing.JButton btnSelProducto;
    public static javax.swing.JComboBox comboAlmacen;
    private javax.swing.JComboBox comboMetodo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtProductoInv;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

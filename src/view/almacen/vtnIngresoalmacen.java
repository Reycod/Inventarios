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
import mapeos.Detallecompra;
import mapeos.Fifo;
import mapeos.Inventario;
import mapeos.Producto;
import util.ContadorPanelActividad;
import util.Operaciones;
import static view.almacen.vtnInventarioInicial.SpCantidad;
import static view.almacen.vtnInventarioInicial.txtPrecio;
import static view.OC.vtnListarCompras.suma;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class vtnIngresoalmacen extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnIngresoalmacen
     */
    public static String validaVentana;

    public vtnIngresoalmacen() {
        initComponents();
        validaVentana = "X";//iniciando la validacion del JinternalFrameFrom
         /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //FIN DE LA ALINEACION DE LA VENTANA
        listarCompras();
        cargarAlmacen();
    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarCompras() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaIngresoAlmacen.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaIngresoAlmacen);
            //realizando la consulta para realizar el listado de los datos
            ComprasDao cmDao = new ComprasDao();
            List<Object[]> lista = cmDao.listarCompras();
            if (lista.size() > 0) {
                Object[] fila = new Object[modelo.getColumnCount()];
                TableColumnModel columnModel = tablaIngresoAlmacen.getColumnModel();
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
                    btnIngreso.setEnabled(false);
                    btnSalir.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No tiene compras para adicionar al inventario", null, JOptionPane.WARNING_MESSAGE);
                btnIngreso.setEnabled(false);
                btnSalir.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error---->" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos() {
        comboAlmacen.setSelectedIndex(0);
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtSubTotal.setText("");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaIngresoAlmacen = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboAlmacen = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnIngreso = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNuCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtProducto = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtNuDocRecep = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDocRecepcion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleCompraAlmacen = new javax.swing.JTable();

        setTitle("Ingreso almacen");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTADO DE COMPRAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 16))); // NOI18N

        tablaIngresoAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "# COMPRA", "PRECIO TOTAL", "RESPONSABLE", "ESTADO", "DOC. RECEPCIÓN", "# DOC."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaIngresoAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaIngresoAlmacenMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaIngresoAlmacen);
        if (tablaIngresoAlmacen.getColumnModel().getColumnCount() > 0) {
            tablaIngresoAlmacen.getColumnModel().getColumn(0).setMinWidth(80);
            tablaIngresoAlmacen.getColumnModel().getColumn(0).setPreferredWidth(80);
            tablaIngresoAlmacen.getColumnModel().getColumn(0).setMaxWidth(85);
            tablaIngresoAlmacen.getColumnModel().getColumn(1).setMinWidth(70);
            tablaIngresoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(70);
            tablaIngresoAlmacen.getColumnModel().getColumn(1).setMaxWidth(75);
            tablaIngresoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMACIÓN DE ENTRADA A INVENTARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 16))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Almacen destino:");

        comboAlmacen.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        comboAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--------------" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Responsable:");

        txtResponsable.setEditable(false);
        txtResponsable.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Producto:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Cantidad:");

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Precio unitario:");

        txtPrecio.setEditable(false);
        txtPrecio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnIngreso.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnIngreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        btnIngreso.setText("Guardar");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Número de OC:");

        txtNuCompra.setEditable(false);
        txtNuCompra.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("SubTotal:");

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txtProducto.setEditable(false);
        txtProducto.setColumns(20);
        txtProducto.setRows(5);
        jScrollPane3.setViewportView(txtProducto);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Documento:");

        txtNuDocRecep.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Número Doc:");

        txtDocRecepcion.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboAlmacen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNuCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtNuDocRecep))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSubTotal)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtResponsable))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtDocRecepcion))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtDocRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNuCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtNuDocRecep, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INGRESO A ALMACEN");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ingresoAlm2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DETALLE COMPRA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 16))); // NOI18N

        tablaDetalleCompraAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUCTO", "CANTIDAD", "PRECIO UNITARIO", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalleCompraAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaDetalleCompraAlmacenMousePressed(evt);
            }
        });
        tablaDetalleCompraAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaDetalleCompraAlmacenKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDetalleCompraAlmacen);
        if (tablaDetalleCompraAlmacen.getColumnModel().getColumnCount() > 0) {
            tablaDetalleCompraAlmacen.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaDetalleCompraAlmacen.getColumnModel().getColumn(1).setPreferredWidth(50);
            tablaDetalleCompraAlmacen.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablaDetalleCompraAlmacen.getColumnModel().getColumn(3).setPreferredWidth(10);
            tablaDetalleCompraAlmacen.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        validaVentana = null;
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tablaIngresoAlmacenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaIngresoAlmacenMousePressed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) tablaIngresoAlmacen.getModel();//creando el modelo pára llenar los datos al JTableje
            int idcompra = Integer.parseInt(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 0)));//recuperando el id de la compra
            ComprasDao cmDao = new ComprasDao();
            List<Object[]> detalle = cmDao.listarDetalle(idcompra);

            DefaultTableModel modelodetalle = (DefaultTableModel) tablaDetalleCompraAlmacen.getModel();//creando el modelo pára llenar los datos al JTableje
            Object[] fila = new Object[modelodetalle.getColumnCount()];
            limpiarTabla(tablaDetalleCompraAlmacen);
            for (int i = 0; i < detalle.size(); i++) {
                // JOptionPane.showMessageDialog(rootPane, "DIM-->" + i);
                fila[0] = detalle.get(i)[0];//id
                fila[1] = detalle.get(i)[1];//producto
                fila[2] = detalle.get(i)[2];//cantidad
                fila[3] = detalle.get(i)[3];//precio unitario
                fila[4] = Operaciones.redondear(Double.parseDouble((String) detalle.get(i)[2]) * Double.parseDouble((String) detalle.get(i)[3]));//subtotal
                // fila[5] = detalle.get(i)[4];//estado

                modelodetalle.addRow(fila);
            }

            //RECUPERANDO LA COMPRA DE LA FILA RECUPERADA
            //int dato = Integer.parseInt(String.valueOf(tablaListaCompras.getValueAt(tablaListaCompras.getSelectedRow(), 0)));
            //Compra c = cmDao.buscarCompra(dato);
            // this.setObjCompra(c);//insertando el objeto compra
            //-----------------------------
            limpiarCampos();
            txtNuCompra.setText(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 0)));
            txtResponsable.setText(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 2)));
            txtDocRecepcion.setText(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 4)));
            txtNuDocRecep.setText(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 5)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar el detalle de la compra " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaIngresoAlmacenMousePressed

    private void tablaDetalleCompraAlmacenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDetalleCompraAlmacenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDetalleCompraAlmacenKeyReleased

    private void tablaDetalleCompraAlmacenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleCompraAlmacenMousePressed
        // TODO add your handling code here:
        txtProducto.setText(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 1)));
        txtCantidad.setText(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 2)));
        txtPrecio.setText(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 3)));
        txtSubTotal.setText(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 4)));
    }//GEN-LAST:event_tablaDetalleCompraAlmacenMousePressed

    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoActionPerformed
        // TODO add your handling code here:
        try 
        {
            if (tablaIngresoAlmacen.getSelectedRows().length != 0) 
            {
                if (comboAlmacen.getSelectedIndex() != 0) 
                {
                    if (tablaDetalleCompraAlmacen.getSelectedRows().length != 0) 
                    {
                        AlmacenDao almDao = new AlmacenDao();
                        InventarioDao invDao = new InventarioDao();
                        //verificando si el producto ya esta en inventario y en el almacen correcto
                        if (invDao.existeProducto(almDao.buscarAlmacenId(comboAlmacen.getSelectedItem().toString()), (int) tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 0)) == null) 
                        {

                            int x = JOptionPane.showConfirmDialog(null, "¡El producto no tiene un inventario incial!\n¿Decea establecer un inventario inicial del producto ahora?");
                            if (x == JOptionPane.YES_OPTION) 
                            {
                                String varValicadion = vtnInventarioInicial.validaVentana;//recuperando el valor de la variable de validacion de la ventana
                                if (varValicadion == null) 
                                {
                                    vtnInventarioInicial invI = new vtnInventarioInicial(1);//creando una instalcia de la ventana e indicando el parametro de ubicacion de invocacion
                                    vtnPrincipal.sysMDI.add(invI);
                                    invI.setClosable(true);//si se puede cerra la ventana
                                    invI.setTitle("Seleccione un producto");
                                    //listaPro.setLocationRelativeTo(null);
                                    invI.setVisible(true);
                                    invI.SpCantidad.setEnabled(false);
                                    invI.txtPrecio.setEnabled(false);

                                    vtnInventarioInicial.btnSelProducto.setEnabled(false);
                                    vtnInventarioInicial.txtProductoInv.setText(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 1)));
                                    vtnInventarioInicial.SpCantidad.setValue(Integer.parseInt((String) tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 2)));
                                    vtnInventarioInicial.txtPrecio.setText(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 3)));
                                    vtnInventarioInicial.comboAlmacen.setSelectedIndex(comboAlmacen.getSelectedIndex());
                                    Double total = Double.parseDouble(vtnInventarioInicial.txtPrecio.getText()) * ((int) vtnInventarioInicial.SpCantidad.getValue());
                                    vtnInventarioInicial.txtTotal.setText(Operaciones.redondear(total).toString());

                                } 
                                else 
                                {
                                    JOptionPane.showMessageDialog(this, "La ventana Inventario inicial ya esta activa..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
                                }

                            } else if (x == JOptionPane.NO_OPTION) {
                                JOptionPane.showMessageDialog(null, "Para generar un inventario inicial siga los siguientes pasos:\n1.- Pestaña Almacen\n2.- Operaciones inventarios\n3.-Inventario inicial ");
                            }
//                            if (invDao.registarInventario(inv)) 
//                            {
//                                ComprasDao cmDao = new ComprasDao();
//                                Compra cm = cmDao.buscarCompra(Integer.parseInt(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 0))));
//                                List<Object[]> detalle = new ArrayList<>();//recuperando el listado de los datos
//                                detalle = cmDao.listarDetalleIngresoAlmacen(cm.getIdcompra());
//                                for (int i = 0; i < detalle.size(); ++i) 
//                                {
//                                    if (tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 0) == detalle.get(i)[1]) 
//                                    {
//                                        //Actualizando el campo estado de la tabla detalleCompra
//                                        Operaciones.actualizaEstadoDetalleCompra((int) detalle.get(i)[0]);
//                                        //Actualizando el precio del producto con el precio de la ultima compra
//                                        Operaciones.actualizaPrecioProducto(Integer.parseInt(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 0))),String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 3)));
//                                        cmDao.actualizarCompra(cm);//refrescando la transaccion con la BD
//                                    }
//                                }
//                                JOptionPane.showMessageDialog(this, "Ingreso de producto a almacen correcto");
//                                limpiarTabla(tablaDetalleCompraAlmacen);
//                                listarCompras();
//                                limpiarCampos();
//
//                            } else {
//                                JOptionPane.showMessageDialog(null, "No se pudo realizar la insercion de los datos..!", "Mensaje", JOptionPane.ERROR_MESSAGE);
//                            }
                        } 
                        else 
                        {
                            Inventario invActualizar = invDao.existeProducto(almDao.buscarAlmacenId(comboAlmacen.getSelectedItem().toString()), (int) tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 0));
                            int stockAc = Integer.parseInt(invActualizar.getStock()) + Integer.parseInt(txtCantidad.getText());
                            invActualizar.setStock(Integer.toString(stockAc));//ingresando el nuevo stock

                            //Adicionado la fila al Kardex de compra
                            Set<Fifo> items = new HashSet<>();
                            Fifo fifo = new Fifo();
                            fifo.setInventario(invActualizar);
                            fifo.setDetalle("Compra");
                            fifo.setCant_entrada(Integer.parseInt(txtCantidad.getText()));
                            fifo.setPrecio_entrada(txtPrecio.getText());
                            fifo.setP_total_entrada(txtSubTotal.getText());
                            
                            fifo.setDocumento(txtDocRecepcion.getText());
                            fifo.setNdocumento(Integer.parseInt(txtNuDocRecep.getText()));
                             
                            //recuperando el ultimo registro del Kardex
                            List<Object[]> ultimoReg = invDao.ultimoRegistroFIFO(invActualizar.getIdinventario());
                            if (ultimoReg.size() > 0) 
                            {
                                for (int i = 0; i < ultimoReg.size(); ++i) 
                                {
                                    int cantA = (Integer.parseInt(txtCantidad.getText()))+(Integer.parseInt(String.valueOf(ultimoReg.get(i)[0])));
                                    fifo.setCant_saldo(cantA);
                                    fifo.setPrecio_saldo(txtPrecio.getText());//precio saldo
                                    double precioA=Double.parseDouble(txtSubTotal.getText())+Double.parseDouble((String) ultimoReg.get(i)[2]);
                                    fifo.setP_total_saldo(String.valueOf(Operaciones.redondear(precioA)));
                                }
                            }
                            //---------------------------------------------------
                            items.add(fifo);
                            invActualizar.setFifos(items);
                            // fin de la adicion del producto al kardex
                            invDao.actualizarInventario(invActualizar);
                            //----------------
                            ComprasDao cmDao = new ComprasDao();
                            Compra cm = cmDao.buscarCompra(Integer.parseInt(String.valueOf(tablaIngresoAlmacen.getValueAt(tablaIngresoAlmacen.getSelectedRow(), 0))));
                            List<Object[]> detalle = new ArrayList<>();//recuperando el listado de los datos
                            detalle = cmDao.listarDetalleIngresoAlmacen(cm.getIdcompra());
                            for (int i = 0; i < detalle.size(); ++i) {
                                if (tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 0) == detalle.get(i)[1]) {
                                    //Actualizando el campo estado de la tabla detalleCompra
                                    Operaciones.actualizaEstadoDetalleCompra((int) detalle.get(i)[0]);
                                    //Actualizando el precio del producto con el precio de la ultima compra
                                    Operaciones.actualizaPrecioProducto(Integer.parseInt(String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 0))), String.valueOf(tablaDetalleCompraAlmacen.getValueAt(tablaDetalleCompraAlmacen.getSelectedRow(), 3)));
                                    cmDao.actualizarCompra(cm);//refrescando la transaccion con la BD
                                }
                            }
                            //----------------
                            JOptionPane.showMessageDialog(null, "Ingreso de producto a almacen correcto");
                            listarCompras();
                            limpiarCampos();
                            limpiarTabla(tablaDetalleCompraAlmacen);
                            listarCompras();
                            limpiarCampos();
                            ContadorPanelActividad.contadorIA();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un producto..!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un almacen..!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una compra..!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error---->" + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIngresoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnIngreso;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox comboAlmacen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tablaDetalleCompraAlmacen;
    public static javax.swing.JTable tablaIngresoAlmacen;
    public static javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDocRecepcion;
    private javax.swing.JTextField txtNuCompra;
    private javax.swing.JTextField txtNuDocRecep;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextArea txtProducto;
    private javax.swing.JTextField txtResponsable;
    public static javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}

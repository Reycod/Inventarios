/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.OC;

import com.mysql.jdbc.Connection;
import dao.ComprasDao;
import dao.ProductoDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import mapeos.Compra;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Conexion;
import util.ContadorPanelActividad;
import util.Numero_a_Letra;
import util.Operaciones;
import util.Validaciones;
import view.almacen.vtnRecepcionCompra;
import view.vtnPrincipal;
import static view.vtnPrincipal.sysMDI;

/**
 *
 * @author Reynaldo
 */
public class vtnListarCompras extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnListarCompras
     */
    public static String validaVentana;
    private Compra ObjCompra = new Compra();//Objeto global

    public vtnListarCompras() {
        initComponents();
        validaVentana = "x";//inicializando la variable de validacion con un valor cualquiera
        /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //FIN DE LA ALINEACION DE LA VENTANA
        listarComprasOC();
        this.tablaDetalleListaCompra.setEnabled(false);
    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarComprasOC() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaListaCompras.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaListaCompras);
            //realizando la consulta para realizar el listado de los datos
            ComprasDao cmDao = new ComprasDao();
            List<Object[]> lista = cmDao.listarComprasOC();
            if (lista.size() > 0) 
            {
                Object[] fila = new Object[modelo.getColumnCount()];
                TableColumnModel columnModel = tablaListaCompras.getColumnModel();
                for (int i = 0; i < lista.size(); i++) 
                {
                    columnModel.getColumn(i).setPreferredWidth(20);
                    fila[0] = lista.get(i)[0];//numero de compra
                    fila[1] = lista.get(i)[1];//fecha
                    fila[2] = lista.get(i)[2];//fecha entrega
                    fila[3] = lista.get(i)[3];//precio total
                    fila[4] = lista.get(i)[7];//responsable
                    fila[5] = lista.get(i)[5];//estado

                    modelo.addRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No tiene Ordenes de compra..!!", null, JOptionPane.WARNING_MESSAGE);
                btnRecibir.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnCompras.setEnabled(false);
                btnBuscar.setEnabled(false);
                btnBuscarFecha.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que limpia los datos de la tabla entes de realizar otro listado
    public static void limpiarTabla(JTable tabla) {
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

    //Metodo que realiza la suma de las columnas de los totales
    public static void suma(DefaultTableModel model) {
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for (int i = 0; i < model.getRowCount(); i++) {
            double numero = 0;
            try {
                //capturamos valor de celda
                numero = Double.parseDouble(model.getValueAt(i, 4).toString());
            } catch (NumberFormatException nfe) { //si existe un error se coloca 0 a la celda
                numero = 0;
                model.setValueAt(0, i, 1);
            }
            //se suma al total
            total += numero;
        }
        Numero_a_Letra numero = new Numero_a_Letra();

        String literal = Double.toString(total);

        LBLiteralListaCompra.setText(numero.Convertir(literal, true));//insertando el literal de la suma
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaCompras = new javax.swing.JTable();
        btnRecibir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtfecha1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtfecha2 = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtNumeroCompra = new javax.swing.JTextField();
        btnBuscarFecha = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleListaCompra = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCompras = new javax.swing.JButton();
        LBLiteralListaCompra = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Listado de compras");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTADO ORDENES DE COMPRA A PROCESAR");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/listaCompras.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tablaListaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° COMPRA", "FECHA", "FECHA ENTREGA", "PRECIO TOTAL", "RESPONSABLE", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaListaCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaListaComprasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaListaCompras);

        btnRecibir.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnRecibir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/recibir.png"))); // NOI18N
        btnRecibir.setText("Recibir Compra");
        btnRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/print.png"))); // NOI18N
        btnImprimir.setText("Imprimir OC");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Desde:");

        txtfecha1.setDateFormatString("yyyy-MM-dd");
        txtfecha1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Hasta:");

        txtfecha2.setDateFormatString("yyyy-MM-dd ");
        txtfecha2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtfecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtfecha2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addGap(21, 21, 21))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtfecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        txtNumeroCompra.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtNumeroCompra.setToolTipText("Ingrese el número de compra");

        btnBuscarFecha.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnBuscarFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/buscar.png"))); // NOI18N
        btnBuscarFecha.setText("Buscar");
        btnBuscarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFechaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ingrese número de compra");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtNumeroCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(45, 45, 45)))
                .addComponent(btnBuscarFecha)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tablaDetalleListaCompra.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaDetalleListaCompra);
        if (tablaDetalleListaCompra.getColumnModel().getColumnCount() > 0) {
            tablaDetalleListaCompra.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaDetalleListaCompra.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaDetalleListaCompra.getColumnModel().getColumn(3).setPreferredWidth(15);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("FILTRO DE DATOS:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("DETALLE COMPRA");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("COMPRAS");

        btnCompras.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/buscar.png"))); // NOI18N
        btnCompras.setText("Listar compras");
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });

        LBLiteralListaCompra.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Son:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/modificar.png"))); // NOI18N
        jButton1.setText("Modificar OC");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel6)))
                .addGap(272, 272, 272))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBLiteralListaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnRecibir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCompras)
                                .addGap(67, 67, 67))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LBLiteralListaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRecibir)
                        .addComponent(btnImprimir)
                        .addComponent(btnEliminar)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnBuscarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFechaActionPerformed
        // TODO add your handling code here:
        if (!txtNumeroCompra.getText().equals("")) {
            try {
                ComprasDao cmDao = new ComprasDao();
                Compra cm = cmDao.buscarCompra(Integer.parseInt(txtNumeroCompra.getText()));
                if (cm != null) {
                    DefaultTableModel modelo = (DefaultTableModel) this.tablaListaCompras.getModel();//creando el modela ára llenar los datos al JTableje
                    DefaultTableModel modelodetalle = (DefaultTableModel) this.tablaDetalleListaCompra.getModel();//creando el modela ára llenar los datos al JTableje

                    limpiarTabla(tablaListaCompras);
                    limpiarTabla(tablaDetalleListaCompra);
                    Object[] fila = new Object[modelo.getColumnCount()];

                    fila[0] = txtNumeroCompra.getText();//numero de compra
                    fila[1] = cm.getFecha();//fecha
                    fila[2] = cm.getFechaemtrega();//fecha entrega
                    fila[3] = cm.getPreciototal();//precio total
                    fila[4] = cm.getResponsable();//responsable
                    fila[5] = cm.getEstado();//estado
                    modelo.addRow(fila);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encuentra la OC ingresada..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
                    limpiarTabla(tablaListaCompras);
                    limpiarTabla(tablaDetalleListaCompra);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese el numero de compra a buscar..!!  ", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el numero de compra a buscar..!!  ", "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarFechaActionPerformed

    private void tablaListaComprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListaComprasMousePressed
        // TODO add your handling code here:
        try 
        {
            DefaultTableModel modelo = (DefaultTableModel) tablaListaCompras.getModel();//creando el modelo pára llenar los datos al JTableje
            int idcompra = Integer.parseInt(String.valueOf(tablaListaCompras.getValueAt(tablaListaCompras.getSelectedRow(), 0)));//recuperando el id de la compra
            ComprasDao cmDao = new ComprasDao();
            List<Object[]> detalle = cmDao.listarDetalle(idcompra);

            DefaultTableModel modelodetalle = (DefaultTableModel) tablaDetalleListaCompra.getModel();//creando el modelo pára llenar los datos al JTableje
            Object[] fila = new Object[modelodetalle.getColumnCount()];
            limpiarTabla(tablaDetalleListaCompra);
            for (int i = 0; i < detalle.size(); i++) {
                fila[0] = detalle.get(i)[0];//id
                fila[1] = detalle.get(i)[1];//producto
                fila[2] = detalle.get(i)[2];//cantidad
                fila[3] = Operaciones.redondear(Double.parseDouble((String) detalle.get(i)[3]));//precio unitario
                fila[4] = Operaciones.redondear(Double.parseDouble((String) detalle.get(i)[2]) * Double.parseDouble((String) detalle.get(i)[3]));//subtotal               
                modelodetalle.addRow(fila);
            }
            suma(modelodetalle);//insertando el literal

            //RECUPERANDO LA COMPRA DE LA FILA RECUPERADA
            int dato = Integer.parseInt(String.valueOf(tablaListaCompras.getValueAt(tablaListaCompras.getSelectedRow(), 0)));
            Compra c = cmDao.buscarCompra(idcompra);
            this.setObjCompra(c);//insertando el objeto compra
//            //-----------------------------
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar el detalle de la compra " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tablaListaComprasMousePressed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tablaListaCompras.getModel();//recuperando el modelo de la tabla
        DefaultTableModel modelodetalle = (DefaultTableModel) tablaDetalleListaCompra.getModel();//recuperando el modelo de la tabla

        if (tablaListaCompras.getSelectedRows().length != 0) {
            int y = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el registro de la compra?");
            try {
                if (y == JOptionPane.YES_OPTION) {
                    ComprasDao cmDao = new ComprasDao();
                    if (cmDao.eliminarCompra(cmDao.buscarCompra(Integer.parseInt(String.valueOf(tablaListaCompras.getValueAt(tablaListaCompras.getSelectedRow(), 0)))))) {
                        JOptionPane.showMessageDialog(this, "Eliminacion correcta", null, JOptionPane.INFORMATION_MESSAGE);
                        limpiarTabla(tablaListaCompras);
                        limpiarTabla(tablaDetalleListaCompra);
                        listarComprasOC();
                        ContadorPanelActividad.contadorOC();//actualizando el contador de actividad
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hay productos asociados a esta categoria\npor lo que no se puede eliminar..!!", null, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una compra a eliminar..!!", "Eliminar compra", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        // TODO add your handling code here:
        listarComprasOC();
    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (txtfecha1.getDate() != null && txtfecha2.getDate() != null) {
            try {
                ComprasDao cmDao = new ComprasDao();
                //JOptionPane.showMessageDialog(rootPane, "Sin elemntos"+Validaciones.formatoFecha(txtfecha1.getDate()));
                List<Object[]> lista = cmDao.BuscarCompraFecha(Validaciones.formatoFecha(txtfecha1.getDate()), Validaciones.formatoFecha(txtfecha2.getDate()));
                DefaultTableModel modelo = (DefaultTableModel) this.tablaListaCompras.getModel();//creando el modela ára llenar los datos al JTableje
                Object[] fila = new Object[modelo.getColumnCount()];
                TableColumnModel columnModel = tablaListaCompras.getColumnModel();
                limpiarTabla(tablaListaCompras);
                for (int i = 0; i < lista.size(); i++) {
                    columnModel.getColumn(i).setPreferredWidth(20);
                    fila[0] = lista.get(i)[0];//numero de compra
                    fila[1] = lista.get(i)[1];//fecha
                    fila[2] = lista.get(i)[2];//fecha entrega
                    fila[3] = lista.get(i)[3];//precio total
                    fila[4] = lista.get(i)[7];//responsable
                    fila[5] = lista.get(i)[5];//estado
                    modelo.addRow(fila);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "ERROR-->" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese los campos fecha para realizar\nla nusqueda", "Buscar compra", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        Connection conex = (Connection) Conexion.getConectar();
        try {
            if (tablaListaCompras.getSelectedRows().length != 0) {
                DefaultTableModel tm = (DefaultTableModel) tablaListaCompras.getModel();
                int idcompra = Integer.parseInt(String.valueOf(tm.getValueAt(tablaListaCompras.getSelectedRow(), 0)));

                Map parametro = new HashMap();
                parametro.put("numeroCompra", idcompra);//parametro numero de compra
                parametro.put("responsable_compra", "Reynaldo Rios");//Parametro responsable de la compra
                parametro.put("monto_literal", LBLiteralListaCompra.getText());//Parametro monto literal

                String rutaReporte = System.getProperty("user.dir") + "/src/reportes/OrdenCompra2.jasper";
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parametro, conex);
                JasperViewer view = new JasperViewer(print, false);
                //vtnPrincipal.sysMDI.add(view);
                view.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una Orden de Compra..!!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el reporte de compras" + ex.getMessage(), "Reporte compras", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirActionPerformed
        // TODO add your handling code here:
        try {
            if (tablaListaCompras.getSelectedRows().length != 0) {
                String varValidacion = vtnRecepcionCompra.validaVentana;
                if (varValidacion == null) 
                {
                    DefaultTableModel modelo = (DefaultTableModel) tablaListaCompras.getModel();//creando el modelo pára llenar los datos al JTableje
                    String idcompra = String.valueOf(tablaListaCompras.getValueAt(tablaListaCompras.getSelectedRow(), 0));//recuperando el id de la compra

                    vtnRecepcionCompra alm = new vtnRecepcionCompra(idcompra);
                    alm.setTitle("Recepcion de compra..");
                    alm.setResizable(false);//no es redimencionable
                    alm.setMaximizable(false);//no se puede maximizar
                    alm.setClosable(true);//si se puede cerra la ventana
                    alm.setIconifiable(false);
                    sysMDI.add(alm);
                    alm.setVisible(true);
                }
//                Compra cm = this.getObjCompra();
//                cm.setEstado("Procesado");
//                ComprasDao cmDao = new ComprasDao();
//                cmDao.actualizarCompra(cm);
//                JOptionPane.showMessageDialog(this, "Compra recibida y lista para ingreso al almacen..!!");
                //   listarComprasOC();
//                limpiarTabla(tablaDetalleListaCompra);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una compra..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRecibirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (tablaListaCompras.getSelectedRows().length != 0) {
            try {
                String varValidacion = vtnModificarOC.validaVentana;
                if (varValidacion == null) {

                    DefaultTableModel modelo = (DefaultTableModel) tablaListaCompras.getModel();//creando el modelo pára llenar los datos al JTableje
                    String idcompra = String.valueOf(tablaListaCompras.getValueAt(tablaListaCompras.getSelectedRow(), 0));//recuperando el id de la compra

                    ComprasDao ocdao = new ComprasDao();
                    Compra ocm = ocdao.buscarCompra(Integer.parseInt(idcompra));

                    vtnModificarOC modificar = new vtnModificarOC(this.getObjCompra());
                    modificar.setVisible(true);
                    modificar.setResizable(false);//no es redimencionable
                    modificar.setMaximizable(false);//no se puede maximizar
                    modificar.setClosable(true);//si se puede cerra la ventana
                    modificar.setIconifiable(false);
                    sysMDI.add(modificar);
                    modificar.setVisible(true);
                    validaVentana = null;
                    this.dispose();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "La ventana Mofificar OC ya esta activa..!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione la OC a modificar", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel LBLiteralListaCompra;
    public static javax.swing.JButton btnBuscar;
    public static javax.swing.JButton btnBuscarFecha;
    public static javax.swing.JButton btnCompras;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnRecibir;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tablaDetalleListaCompra;
    public static javax.swing.JTable tablaListaCompras;
    private javax.swing.JTextField txtNumeroCompra;
    private com.toedter.calendar.JDateChooser txtfecha1;
    private com.toedter.calendar.JDateChooser txtfecha2;
    // End of variables declaration//GEN-END:variables

    public Compra getObjCompra() {
        return ObjCompra;
    }

    public void setObjCompra(Compra ObjCompra) {
        this.ObjCompra = ObjCompra;
    }
}

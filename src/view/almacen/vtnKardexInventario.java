/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.almacen;

import com.mxrck.autocompleter.TextAutoCompleter;
import dao.AlmacenDao;
import dao.InventarioDao;
import dao.ProductoDao;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mapeos.Almacen;
import mapeos.Producto;
import util.Operaciones;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class vtnKardexInventario extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnKardexInventario
     */
    public static String validaVentana;

    public vtnKardexInventario() {
        initComponents();
        validaVentana = "X";//iniciando la validacion del JinternalFrameFrom
         /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //FIN DE LA ALINEACION DE LA VENTANA
        cargarAlmacen();
        crearAutocompletar();
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
    public void listarDatosKardex(int idAlmacen) {
        try 
        {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaKardexInventario.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaKardexInventario);
            //realizando la consulta para realizar el listado de los datos
            InventarioDao invDao = new InventarioDao();
            List<Object[]> lista = invDao.kardexInventarioAlmacen(idAlmacen);//obteniendo el listado del Kardex por Almacen
            Object[] fila = new Object[modelo.getColumnCount()];
            if (lista.size() > 0) 
            {
                for (int i = 0; i < lista.size(); i++) {
                    fila[0] = lista.get(i)[0];//Codigo producto
                    fila[1] = lista.get(i)[1];//nombre producto
                    fila[2] = lista.get(i)[2];//stock
                    fila[3] = lista.get(i)[3];//stock minimo
                    fila[4] = lista.get(i)[4];//stock maximo
//                    fila[5] = Double.parseDouble((String) lista.get(i)[5]);//precio
//                    Double subtotal = Double.parseDouble((String) lista.get(i)[2]) * Double.parseDouble((String) lista.get(i)[5]);//subtotal
//                    fila[6] = Operaciones.redondear(subtotal);
                    modelo.addRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El almacen selecconado no tiene productos..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos del Almacen " + e.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
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

    //Metodo que crea el campo de autocompletar para la busqueda
    public void crearAutocompletar() {
        try {
            TextAutoCompleter autocompletar = new TextAutoCompleter(txtbuscar);

            ProductoDao prDao = new ProductoDao();
            List<Producto> lista = prDao.listarProductos();

            for (int i = 0; i < lista.size(); i++) {
                autocompletar.addItem(lista.get(i).getNombre());
                //      autocompletar.addItem(lista.get(i).getCodigoproducto());
            }
        } catch (Exception ex) {
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaKardexInventario = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboAlmacen = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        lbmImagen = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KARDEX INVENTARIO ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kardexInventario.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(627, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaKardexInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "STOCK", "STOCK MINIMO", "STOCK MAXIMO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaKardexInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaKardexInventarioMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaKardexInventario);
        if (tablaKardexInventario.getColumnModel().getColumnCount() > 0) {
            tablaKardexInventario.getColumnModel().getColumn(0).setMinWidth(65);
            tablaKardexInventario.getColumnModel().getColumn(0).setPreferredWidth(65);
            tablaKardexInventario.getColumnModel().getColumn(0).setMaxWidth(70);
            tablaKardexInventario.getColumnModel().getColumn(1).setMinWidth(300);
            tablaKardexInventario.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaKardexInventario.getColumnModel().getColumn(1).setMaxWidth(400);
            tablaKardexInventario.getColumnModel().getColumn(2).setMinWidth(50);
            tablaKardexInventario.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablaKardexInventario.getColumnModel().getColumn(2).setMaxWidth(55);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Seleccione almacen:");

        comboAlmacen.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        comboAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "**************" }));
        comboAlmacen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboAlmacenItemStateChanged(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/print.png"))); // NOI18N
        jButton2.setText("Vista de impresión");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Buscar producto:");

        txtbuscar.setToolTipText("Escriba el codigo o el nombre del producto..");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/search_24.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/search_24.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtbuscar, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboAlmacen, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboAlmacen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtbuscar))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void comboAlmacenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAlmacenItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboAlmacenItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try 
        {
            if (comboAlmacen.getSelectedIndex() > 0) 
            {
                AlmacenDao almDao = new AlmacenDao();
                listarDatosKardex(almDao.buscarAlmacenId(comboAlmacen.getSelectedItem().toString()));

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un Almacen..!! ", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaKardexInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaKardexInventarioMousePressed
        // TODO add your handling code here:
        try {
            ProductoDao proDao = new ProductoDao();
            Producto pro = proDao.buscarProducto2((String) tablaKardexInventario.getValueAt(tablaKardexInventario.getSelectedRow(), 1));
            //recuperando la imagen y convirtiendo en ImageIco
            InputStream is = new ByteArrayInputStream(pro.getImagen());
            BufferedImage image = ImageIO.read(is);
            ImageIcon ico = new ImageIcon(image);
            ImageIcon icono = new ImageIcon(ico.getImage().getScaledInstance(lbmImagen.getWidth(), lbmImagen.getHeight(), Image.SCALE_DEFAULT));
            lbmImagen.setIcon(icono);
            //fin de la recuperacion y muetra de la imagen
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado " + ex.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_tablaKardexInventarioMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (comboAlmacen.getSelectedIndex()!=0) 
            {
                if (!txtbuscar.getText().equals("")) {
                    DefaultTableModel modelo = (DefaultTableModel) this.tablaKardexInventario.getModel();//creando el modela ára llenar los datos al JTableje
                    limpiarTabla(tablaKardexInventario);
                    //realizando la consulta para realizar el listado de los datos
                    InventarioDao invDao = new InventarioDao();
                    List<Object[]> lista = invDao.KardexProducto(new AlmacenDao().buscarAlmacenId(comboAlmacen.getSelectedItem().toString()), txtbuscar.getText());//obteniendo el listado del Kardex por Almacen
                    Object[] fila = new Object[modelo.getColumnCount()];
                    if (lista.size() > 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            fila[0] = lista.get(i)[0];//Codigo producto
                            fila[1] = lista.get(i)[1];//nombre producto
                            fila[2] = lista.get(i)[2];//stock
                            fila[3] = lista.get(i)[3];//stock minimo
                            fila[4] = lista.get(i)[4];//stock maximo
//                            fila[5] = Double.parseDouble((String) lista.get(i)[5]);//precio
//                            Double subtotal = Double.parseDouble((String) lista.get(i)[2]) * Double.parseDouble((String) lista.get(i)[5]);//subtotal
//                            fila[6] = Operaciones.redondear(subtotal);
                            modelo.addRow(fila);
                        }
                        txtbuscar.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "El producto no tiene existencias en el almacen seleccionado..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el nombre de un producto a buscar..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Seleccione un almacen para realizar una busqueda del producto..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos del Almacen " + e.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboAlmacen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbmImagen;
    private javax.swing.JTable tablaKardexInventario;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}

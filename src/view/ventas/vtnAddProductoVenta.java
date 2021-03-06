/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ventas;

import dao.AlmacenDao;
import dao.InventarioDao;
import dao.ProductoDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mapeos.Producto;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class vtnAddProductoVenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnAddProductoVenta
     */
    public static String validaVentana;// variable que valida que la ventana no se habra mas de una vez

    public vtnAddProductoVenta(int idAlm) {
        initComponents();
        validaVentana="x";
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        listarDatosKardex(idAlm);

    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarDatosKardex(int idAlmacen) 
    {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaAddProd.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaAddProd);
            //realizando la consulta para realizar el listado de los datos
            InventarioDao invDao = new InventarioDao();
            List<Object[]> lista = invDao.kardexInventarioAlmacen(idAlmacen);//obteniendo el listado del Kardex por Almacen
            Object[] fila = new Object[modelo.getColumnCount()];
            if (lista.size() > 0) {
                for (int i = 0; i < lista.size(); i++) {
                    fila[0] = lista.get(i)[5];//Idproducto
                    fila[1] = lista.get(i)[1];//nombre producto
                    fila[2] = lista.get(i)[2];//stock

                    modelo.addRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El almacen seleccionado no tiene productos..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar información del Almacen " + e.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }

    //metodo que realiza el limpiado de la tabla
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

    //Metodo que realiza el filtrado de datos por codigo y nombre
    private void actualizarBusqueda() 
    {
        try 
        {
            ProductoDao pro = new ProductoDao();

            ArrayList<Producto> result = null;
            List<Object[]> result2=null;
//            if (String.valueOf(cboParametroPro.getSelectedItem()).equalsIgnoreCase("Codigo")) 
//            {
//               // result = BDProducto.listarProductoPorCodigoEstado(txtBuscarPro.getText());
//
//            } else 
            if (String.valueOf(comboByProducto.getSelectedItem()).equalsIgnoreCase("Nombre")) 
            {
                AlmacenDao almDao=new AlmacenDao();
                result2 = pro.buscarProductoFiltroBYNombreByAlmacen(txtProd.getText(),almDao.buscarAlmacenId(vtnVentas.comboAlmacen.getSelectedItem().toString()));
                recargarTable2(result2);
            } 
            else 
            {
                result = (ArrayList<Producto>) pro.listarProductos();
                recargarTable(result);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el listado de los productos" + ex.getMessage()+"--a-a-", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //metodo que hace el refrescado de la tabla despues del filtro de datos 
    public void recargarTable(ArrayList<Producto> lista) 
    {
        DefaultTableModel modelo = (DefaultTableModel) this.tablaAddProd.getModel();//creando el modela ára llenar los datos al JTableje
        limpiarTabla(tablaAddProd);
        Object[] fila = new Object[modelo.getColumnCount()];
        if (lista.size() != 0) 
        {
            for (int i = 0; i < lista.size(); i++) 
            {
                fila[0] = lista.get(i).getIdproducto();//id
                fila[1] = lista.get(i).getNombre();//id
                //fila[2] = lista.get(i)[2];//id
                modelo.addRow(fila);
            }
        }
    }
    
     //metodo que hace el refrescado de la tabla despues del filtro de datos 
    public void recargarTable2(List<Object[]> lista) 
    {
            
        DefaultTableModel modelo = (DefaultTableModel) this.tablaAddProd.getModel();//creando el modela ára llenar los datos al JTableje
        limpiarTabla(tablaAddProd);
        Object[] fila = new Object[modelo.getColumnCount()];
        if (lista.size() != 0) 
        {
            for (int i = 0; i < lista.size(); i++) 
            {
                fila[0] = lista.get(i)[0];//id
                fila[1] = lista.get(i)[1];//Nombre
                fila[2] = lista.get(i)[2];//Stock
                modelo.addRow(fila);
            }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtProd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboByProducto = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAddProd = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();

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

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ADICIONAR PRODUCTO");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addproductos.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(19, 19, 19))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Producto:");

        txtProd.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Buscar por:");

        comboByProducto.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        comboByProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre" }));

        tablaAddProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "STOCK"
            }
        ));
        jScrollPane1.setViewportView(tablaAddProd);
        if (tablaAddProd.getColumnModel().getColumnCount() > 0) {
            tablaAddProd.getColumnModel().getColumn(0).setMinWidth(41);
            tablaAddProd.getColumnModel().getColumn(0).setPreferredWidth(40);
            tablaAddProd.getColumnModel().getColumn(0).setMaxWidth(40);
            tablaAddProd.getColumnModel().getColumn(2).setMinWidth(50);
            tablaAddProd.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablaAddProd.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Cantidad:");

        jSpinner1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboByProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(comboByProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void txtProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyReleased
        // TODO add your handling code here:
        actualizarBusqueda();
    }//GEN-LAST:event_txtProdKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboByProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tablaAddProd;
    private javax.swing.JTextField txtProd;
    // End of variables declaration//GEN-END:variables
}

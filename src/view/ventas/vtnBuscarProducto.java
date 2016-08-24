/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ventas;

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
public class vtnBuscarProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnBuscarProducto
     */
    public static String validaVentana;// variable que valida que la ventana no se habra mas de una vez

    public vtnBuscarProducto() {
        initComponents();
        validaVentana = "x";//inicializando la variable de validacion con un valor cualquiera
          /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        listarProductos();

    }

    //Metodo que realiza el listado de los productos 
    public void listarProductos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaAdicionarProd.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaAdicionarProd);
            //realizando la consulta para realizar el listado de los datos
            ProductoDao proDao = new ProductoDao();
            List<Object[]> lista = proDao.listarProductos2();
            Object[] fila = new Object[modelo.getColumnCount()];

            if (lista.size() != 0) {
                for (int i = 0; i < lista.size(); i++) {
                    fila[0] = lista.get(i)[0];//id
                    fila[1] = lista.get(i)[1];//id
                    //fila[2] = lista.get(i)[2];//id
                    modelo.addRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existen productos registrados", null, JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
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

    //Metodo que verifica si el item seleccionado esta repetido en la tabla de Combo Producto
    public boolean buscarRepetidos(int id) {
        DefaultTableModel modelo = (DefaultTableModel) vtnComboProducto.tablaComboProducto.getModel();//recuperando la tabla OC
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Integer.parseInt((String) vtnComboProducto.tablaComboProducto.getValueAt(i, 0)) == id) {
                return true;
            }
        }
        return false;
    }

    //metodo que hace el refrescado de la tabla despues del filtro de datos 
    public void recargarTable(ArrayList<Producto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) this.tablaAdicionarProd.getModel();//creando el modela ára llenar los datos al JTableje
        limpiarTabla(tablaAdicionarProd);
        Object[] fila = new Object[modelo.getColumnCount()];
        if (lista.size() != 0) {
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getIdproducto();//id
                fila[1] = lista.get(i).getNombre();//id
                //fila[2] = lista.get(i)[2];//id
                modelo.addRow(fila);
            }
        }
    }

    //Metodo que realiza el filtrado de datos por codigo y nombre
    private void actualizarBusqueda() {
        try 
        {
            ProductoDao pro = new ProductoDao();

            ArrayList<Producto> result = null;
//            if (String.valueOf(cboParametroPro.getSelectedItem()).equalsIgnoreCase("Codigo")) 
//            {
//               // result = BDProducto.listarProductoPorCodigoEstado(txtBuscarPro.getText());
//
//            } else 
            if (String.valueOf(cboParametroPro.getSelectedItem()).equalsIgnoreCase("Nombre")) 
            {
                result = (ArrayList<Producto>) pro.buscarProductoFiltro(txtNombreProd.getText());
            } else 
            {
                result = (ArrayList<Producto>) pro.listarProductos();
            }
            recargarTable(result);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error al actualizar el listado de los productos"+ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
        txtNombreProd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboParametroPro = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAdicionarProd = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        SPCantProd = new javax.swing.JSpinner();

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
                .addContainerGap(108, Short.MAX_VALUE))
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

        txtNombreProd.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtNombreProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProdKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Buscar:");

        cboParametroPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "**--**", "Nombre" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Producto:");

        tablaAdicionarProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE"
            }
        ));
        jScrollPane1.setViewportView(tablaAdicionarProd);
        if (tablaAdicionarProd.getColumnModel().getColumnCount() > 0) {
            tablaAdicionarProd.getColumnModel().getColumn(0).setMinWidth(50);
            tablaAdicionarProd.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaAdicionarProd.getColumnModel().getColumn(0).setMaxWidth(80);
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

        SPCantProd.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        SPCantProd.setModel(new javax.swing.SpinnerNumberModel(1, 1, 200, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreProd)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboParametroPro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SPCantProd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cboParametroPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5)
                    .addComponent(SPCantProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (tablaAdicionarProd.getSelectedRows().length != 0) {
                DefaultTableModel modelo = (DefaultTableModel) vtnComboProducto.tablaComboProducto.getModel();//Recuperando el modelo de la tabla donde se insertaran los valores
                //realizando la consulta para realizar el listado de los datos
                Object[] fila = new Object[modelo.getColumnCount()];
                int cant = Integer.parseInt(SPCantProd.getValue().toString());//recuperando la cantidad del producto seleccionado

                int idbuscar = Integer.parseInt(String.valueOf(tablaAdicionarProd.getValueAt(tablaAdicionarProd.getSelectedRow(), 0)));

                //JOptionPane.showMessageDialog(this, idbuscar);
                if (!buscarRepetidos(idbuscar)) {
                    fila[0] = String.valueOf(tablaAdicionarProd.getValueAt(tablaAdicionarProd.getSelectedRow(), 0));//ID PRODUCTO
                    fila[1] = String.valueOf(tablaAdicionarProd.getValueAt(tablaAdicionarProd.getSelectedRow(), 1));//Descripcion producto
                    fila[2] = cant;//cantidad

                    modelo.addRow(fila);//adicionando la fila a la tabla
                    this.dispose();
                    validaVentana = null;
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya fue añadido..!!", "Mensaje...", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto ", "Mensaje..", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNombreProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProdKeyReleased
        // TODO add your handling code here:
        actualizarBusqueda();
    }//GEN-LAST:event_txtNombreProdKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SPCantProd;
    private javax.swing.JComboBox cboParametroPro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAdicionarProd;
    private javax.swing.JTextField txtNombreProd;
    // End of variables declaration//GEN-END:variables
}

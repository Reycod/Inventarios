/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.almacen;

import dao.InventarioDao;
import dao.ProductoDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import util.Operaciones;
import util.tablakardex.CellRenderer;
import util.tablakardex.HeaderCellRenderer;
import static view.OC.vtnListaProOD.tablaAdicionarPro;
import view.OC.vtnOD;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class vtnProductoAjuste extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnProductoAjuste
     */
    public static String validaVentana;
    public static int bandera = 0;

    public vtnProductoAjuste(int alm) {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        listarDatosKardex(alm);
    }

    //Constructor para instanciar la ventana desde JFrame Kadex
    public vtnProductoAjuste(int alm, int var) {
        initComponents();
        bandera = var;//verificando desde donde se lo llamo
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        listarDatosKardex(alm);

    }
    //Metodo que le da el estilo a la tabla
    public void estiloTabla(JTable tabla) 
    {
        //color de los bordes de las celdas
        tabla.setGridColor(new java.awt.Color(214, 213, 208));
        //tamaño de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//columna fecha
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);//columna documento
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//Columna # documento
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);//Columna detalle 
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);//Columna detalle 
        
        //altura de filas
        tabla.setRowHeight(24);
//
        tabla.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer("num"));
        tabla.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer("text"));
        tabla.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer("text"));
        tabla.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer("num"));
        tabla.getColumnModel().getColumn(4).setCellRenderer(new CellRenderer("num"));
        
        JTableHeader jtableHeader = tabla.getTableHeader();
        jtableHeader.setDefaultRenderer(new HeaderCellRenderer());
        tabla.setTableHeader(jtableHeader);
        //fin de la creacion del formato del kardex

    }

    //Metodo que realiza la carga de los productos del almacen
    //seleccionado en la tabla
    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarDatosKardex(int idAlmacen) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProductoAjuste.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaProductoAjuste);
            //realizando la consulta para realizar el listado de los datos
            InventarioDao invDao = new InventarioDao();
            List<Object[]> lista = invDao.kardexInventarioAlmacen(idAlmacen);//obteniendo el listado del Kardex por Almacen
            Object[] fila = new Object[modelo.getColumnCount()];
            if (lista.size() > 0) 
            {
                for (int i = 0; i < lista.size(); i++) 
                {
                    fila[0] = lista.get(i)[5];//id producto
                    fila[1] = lista.get(i)[0];//Codigo producto
                    fila[2] = lista.get(i)[1];//nombre producto
                    fila[3] = lista.get(i)[2];//stock
                    fila[4] = lista.get(i)[6];//precio
                    //fila[4] = lista.get(i)[4];//stock maximo
////                    Double subtotal = Double.parseDouble((String) lista.get(i)[2]) * Double.parseDouble((String) lista.get(i)[5]);//subtotal
////                    fila[6] = Operaciones.redondear(subtotal);
                    modelo.addRow(fila);
                }
                //estiloTabla(tablaProductoAjuste);
            } else {
                JOptionPane.showMessageDialog(null, "El almacen selecconado no tiene porductos..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductoAjuste = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setTitle("Adicionar producto");
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
        jLabel2.setText("Seleccionar producto");

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
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        tablaProductoAjuste.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "CODIGO", "PRODUCTO", "STOCK", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProductoAjuste);
        if (tablaProductoAjuste.getColumnModel().getColumnCount() > 0) {
            tablaProductoAjuste.getColumnModel().getColumn(0).setMinWidth(50);
            tablaProductoAjuste.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaProductoAjuste.getColumnModel().getColumn(0).setMaxWidth(60);
            tablaProductoAjuste.getColumnModel().getColumn(1).setMinWidth(100);
            tablaProductoAjuste.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaProductoAjuste.getColumnModel().getColumn(1).setMaxWidth(120);
            tablaProductoAjuste.getColumnModel().getColumn(2).setMinWidth(200);
            tablaProductoAjuste.getColumnModel().getColumn(2).setPreferredWidth(200);
            tablaProductoAjuste.getColumnModel().getColumn(2).setMaxWidth(210);
        }

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 11, Short.MAX_VALUE))
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
            //Ingresa cuando se llama desde la ventana ajustes
            if (bandera == 0) 
            {
                if (tablaProductoAjuste.getSelectedRows().length != 0) 
                {
                   // JOptionPane.showMessageDialog(this, "entro");
                    vtnAjustes.txtAjusteProducto.setText(String.valueOf(tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 2)));
                    int stock = Integer.parseInt((String) tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 3));
                    vtnAjustes.txtCantidad.setValue(stock);
                    vtnAjustes.txtPUnitario.setText(String.valueOf(tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 4)));
                    Double subTotal = Double.parseDouble((String) tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 4)) * stock;
                    vtnAjustes.txtSubtotal.setText(String.valueOf(Operaciones.redondear(subTotal)));
                    this.dispose();
                    validaVentana = null;
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
                }
            }
            //Ingresa cuando se llama desde la ventana de kardex
            if (bandera == 1) {
                //JOptionPane.showMessageDialog(this, "entro..");
                vtnKardex.txtNomProd.setText(String.valueOf(tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 2)));
                vtnKardex.labelCodigo.setText(String.valueOf(tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 1)));
                int tipo = new InventarioDao().buscarTipoInventario((int) tablaProductoAjuste.getValueAt(tablaProductoAjuste.getSelectedRow(), 0));
                if (tipo == 1) 
                {
                    vtnKardex.labelTipoInv.setText("PEPS");
                } 
                else 
                {
                    vtnKardex.labelTipoInv.setText("PP");
                }
                this.dispose();
                validaVentana = null;
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductoAjuste;
    // End of variables declaration//GEN-END:variables
}

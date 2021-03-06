/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.OC;


import view.OC.vtnOD;
import dao.CategoriaDao;
import dao.ProductoDao;
import java.util.List;
import javassist.compiler.TokenId;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import mapeos.Categoria;
import util.Numero_a_Letra;
import util.Operaciones;
import view.vtnPrincipal;
import static view.OC.vtnOD.tablaOD;

/**
 *
 * @author Reynaldo
 */
public class vtnListaProOD extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnListaProOD
     */
    public static String validaVentana;// variable que valida que la ventana no se habra mas de una vez

    public vtnListaProOD(String prov) {
        initComponents();
        validaVentana = "x";//inicializando la variable de validacion con un valor cualquiera

        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/
        listarProductos(prov);
        txtPrecioCompra.setEnabled(false);
        
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

        vtnOD.LBLiteral.setText(numero.Convertir(literal, true));//insertando el literal de la suma

        //muestra en el componente
        vtnOD.txtTotal.setText(String.valueOf(total));
    }

    public void listarProductos(String prov) {
        try 
        {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaAdicionarPro.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaAdicionarPro);
            //realizando la consulta para realizar el listado de los datos
            ProductoDao proDao = new ProductoDao();
            List<Object[]> lista = proDao.listarProductosProv(prov);
            Object[] fila = new Object[modelo.getColumnCount()];

            if (lista.size() != 0) {
                for (int i = 0; i < lista.size(); i++) {
                    fila[0] = lista.get(i)[0];//id
                    fila[1] = lista.get(i)[1];//id
                    //fila[2] = lista.get(i)[2];//id
                    modelo.addRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El proveedor seleccionado no tiene productos asociados", null, JOptionPane.ERROR_MESSAGE);

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

    //Metodo que verifica si el item seleccionado esta repetido en la tabla
    public boolean buscarRepetidos(int id) 
    {
        DefaultTableModel modelo = (DefaultTableModel) vtnOD.tablaOD.getModel();//recuperando la tabla OC
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Integer.parseInt((String) tablaOD.getValueAt(i, 0)) == id) {
                return true;
            }
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

        panelPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAdicionarPro = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        SpCantidad = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();

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

        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaAdicionarPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "NOMBRE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAdicionarPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAdicionarProMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAdicionarPro);
        if (tablaAdicionarPro.getColumnModel().getColumnCount() > 0) {
            tablaAdicionarPro.getColumnModel().getColumn(0).setMinWidth(50);
            tablaAdicionarPro.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaAdicionarPro.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Cantidad:");

        SpCantidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        SpCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5000, 1));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Precio:");

        txtPrecioCompra.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPrecioCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioCompra.setText("0.0");
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(SpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:        
        try 
        {
            if (tablaAdicionarPro.getSelectedRows().length != 0) 
            {
                DefaultTableModel modelo = (DefaultTableModel) vtnOD.tablaOD.getModel();//creando el modelo pára llenar los datos al JTableje
                //realizando la consulta para realizar el listado de los datos
                Object[] fila = new Object[modelo.getColumnCount()];
                int cant = Integer.parseInt(SpCantidad.getValue().toString());//recuperando la cantidad

                //String valor = String.valueOf(tablaAdicionarPro.getValueAt(tablaAdicionarPro.getSelectedRow(), 2));//precio
                String valor = String.valueOf(txtPrecioCompra.getText());//precio
                if (!valor.equals("0.0")) 
                {
                    double subtotal = Double.parseDouble(valor) * cant;//calculando el subtotal

                    int idbuscar = Integer.parseInt(String.valueOf(tablaAdicionarPro.getValueAt(tablaAdicionarPro.getSelectedRow(), 0)));

                    if (!buscarRepetidos(idbuscar)) 
                    {
                        fila[0] = String.valueOf(tablaAdicionarPro.getValueAt(tablaAdicionarPro.getSelectedRow(), 0));//ID PRODUCTO
                        fila[1] = cant;//cantidad
                        fila[2] = String.valueOf(tablaAdicionarPro.getValueAt(tablaAdicionarPro.getSelectedRow(), 1));//producto
                        //fila[3] = Double.parseDouble(String.valueOf(tablaAdicionarPro.getValueAt(tablaAdicionarPro.getSelectedRow(), 2)));//precio unitario
                        fila[3] = Double.parseDouble(String.valueOf(txtPrecioCompra.getText()));//precio unitario
                        fila[4] = Operaciones.redondear(subtotal);//subtotal

                        modelo.addRow(fila);//adicionando la fila a la tabla
                        suma(modelo);//sumando el total de los productos y poniendo el total literal
                        this.dispose();
                        validaVentana = null;
                        vtnOD.comboProvOD.setEnabled(false);//desbloqueando el comboBox de proveedores
                    } else {
                        JOptionPane.showMessageDialog(null, "El producto ya fue añadido..!!", "Mensaje...", JOptionPane.ERROR_MESSAGE);
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "El precio no puede ser 0 ", "Mensaje..", JOptionPane.WARNING_MESSAGE);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Seleccione un producto ", "Mensaje..", JOptionPane.WARNING_MESSAGE);
                vtnOD.comboProvOD.setEnabled(true);//desbloqueando el comboBox de proveedores
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        this.validaVentana = null;//asignando el valor de nulo a la variable de validacion de la ventana 
    }//GEN-LAST:event_formInternalFrameClosing

    private void tablaAdicionarProMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAdicionarProMousePressed
        // TODO add your handling code here:
        txtPrecioCompra.setEnabled(true);
    }//GEN-LAST:event_tablaAdicionarProMousePressed

    private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
//        if (c < '0' || c > '9') {
//            evt.consume();
//        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecioCompra.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioCompraKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SpCantidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JPanel panelPrincipal;
    public static javax.swing.JTable tablaAdicionarPro;
    private javax.swing.JTextField txtPrecioCompra;
    // End of variables declaration//GEN-END:variables
}

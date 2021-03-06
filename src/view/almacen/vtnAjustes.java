/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.almacen;

import dao.AjusteDao;
import dao.AlmacenDao;
import dao.InventarioDao;
import dao.ProductoDao;
import java.util.List;
import javax.swing.JOptionPane;
import mapeos.Ajustes;
import mapeos.Almacen;
import mapeos.Fifo;
import mapeos.Inventario;
import util.Operaciones;
import view.OC.vtnListaProOD;
import static view.OC.vtnOD.comboProvOD;
import view.vtnPrincipal;
import static view.vtnPrincipal.sysMDI;

/**
 *
 * @author Reynaldo
 */
public class vtnAjustes extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnAjustes
     */
    public static String validaVentana;

    public vtnAjustes() {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*-----------FIN-----------------*/
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboAlmacen = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtAjusteProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdetalleajuste = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPUnitario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtresponsable = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCanAj = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        txtPrecioAj = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotalAj = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        jToolBar1.setRollover(true);

        setTitle("Ajustes Inventario");
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AJUSTE INVENTARIO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ajustes.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Seleccione almacen:");

        comboAlmacen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "********" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Buscar Producto:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/busqueda.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de ajuste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Producto:");

        txtAjusteProducto.setEditable(false);
        txtAjusteProducto.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Detalle:");

        txtdetalleajuste.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtdetalleajuste.setToolTipText("Ingrese el detalle del ajuste de inentario..");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Precio:");

        txtPUnitario.setEditable(false);
        txtPUnitario.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtPUnitario.setText("0.0");
        txtPUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPUnitarioFocusLost(evt);
            }
        });
        txtPUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPUnitarioKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Cantidad:");

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, 500000, 1));
        txtCantidad.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Subtotal:");

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtSubtotal.setText("0.0");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        jButton2.setText("Realizar Ajuste");
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

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Responsable:");

        txtresponsable.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Cantidad ajuste:");

        txtCanAj.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtCanAj.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100000, 1));
        txtCanAj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCanAjFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Precio ajuste:");

        txtPrecioAj.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtPrecioAj.setText("0.0");
        txtPrecioAj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioAjFocusLost(evt);
            }
        });
        txtPrecioAj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioAjKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Subtotal ajuste:");

        txtTotalAj.setEditable(false);
        txtTotalAj.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtTotalAj.setText("0.0");

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/buscar.png"))); // NOI18N
        jButton4.setText("Listar ajustes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtresponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSubtotal, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPUnitario, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel6))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAjusteProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTotalAj))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCanAj)
                                        .addComponent(txtPrecioAj)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtdetalleajuste))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtresponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAjusteProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtCanAj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtPrecioAj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtTotalAj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdetalleajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        this.validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try 
        {
            if (comboAlmacen.getSelectedIndex() > 0) 
            {
                String varValicadion = vtnProductoAjuste.validaVentana;//recuperando el valor de la variable de validacion de la ventana
                if (varValicadion == null) 
                {
                    AlmacenDao almDao = new AlmacenDao();
                    int idAlm = almDao.buscarAlmacenId(comboAlmacen.getSelectedItem().toString());

                    vtnProductoAjuste listaPro = new vtnProductoAjuste(idAlm);

                    vtnPrincipal.sysMDI.add(listaPro);
                    listaPro.setClosable(true);//si se puede cerra la ventana
                    listaPro.setTitle("Seleccione un producto");
                    //listaPro.setLocationRelativeTo(null);
                    listaPro.setVisible(true);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "La ventana de adicionar productos ya esta activa..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
                }        // TODO add your handling code here:
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Almacen..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado.." + ex.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPUnitarioKeyTyped
        // TODO add your handling code here:
        double subtotal = Double.parseDouble((String) txtCantidad.getValue()) * Double.parseDouble(txtPUnitario.getText());

    }//GEN-LAST:event_txtPUnitarioKeyTyped

    private void txtPUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPUnitarioFocusLost
        // TODO add your handling code here:
        txtSubtotal.setText(String.valueOf(61545));
    }//GEN-LAST:event_txtPUnitarioFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        validaVentana = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtPrecioAjKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioAjKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
//        if (c < '0' || c > '9') {
//            evt.consume();
//        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecioAj.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioAjKeyTyped

    private void txtCanAjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCanAjFocusLost
        // TODO add your handling code here:
        Double total = Integer.parseInt(txtCanAj.getValue().toString()) * Double.parseDouble(txtPrecioAj.getText());
        txtTotalAj.setText(Operaciones.redondear(total).toString());
    }//GEN-LAST:event_txtCanAjFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try 
        {
            if (!txtdetalleajuste.getText().equals("") && !txtPrecioAj.equals("")) {
                Ajustes ajuste = new Ajustes();
                ajuste.setDetalle(txtdetalleajuste.getText());
                ajuste.setResponsable(txtresponsable.getText());

                ajuste.setCantidad((int) txtCantidad.getValue());
                ajuste.setCantidadA((int) txtCanAj.getValue());
                ajuste.setPrecio(txtPUnitario.getText());
                ajuste.setPrecioA(txtPrecioAj.getText());
                //ingresando el id del producto
                ProductoDao pr = new ProductoDao();
                ajuste.setIdproducto(pr.buscarProductoByNombre(txtAjusteProducto.getText()));
                //insertando el id del almacen del producto
                AlmacenDao alm = new AlmacenDao();
                ajuste.setIdalmacen(alm.buscarAlmacenId(comboAlmacen.getSelectedItem().toString()));
                //Registrando el ajuste
                AjusteDao ajDao = new AjusteDao();
                ajDao.registarAjuste(ajuste);
                //fin del registro del ajuste

                //actualizando el inventario de acuerdo al ajuste
                InventarioDao invDao = new InventarioDao();
                Inventario inv = invDao.existeProducto(alm.buscarAlmacenId(comboAlmacen.getSelectedItem().toString()), pr.buscarProductoByNombre(txtAjusteProducto.getText()));
                //JOptionPane.showMessageDialog(this, "-->"+inv.getIdinventario());
                Operaciones.actualizaStockInventario(inv.getIdinventario(), String.valueOf(txtCanAj.getValue()));
                Operaciones.actualizaPrecioProducto(pr.buscarProductoByNombre(txtAjusteProducto.getText()), txtPrecioAj.getText());
                //fin de la actualizacion del inventario

                //realizando la insercion el el kardex fifo
                Fifo fifo = new Fifo();
                fifo.setDocumento("INVENTARIO INICIAL");
                fifo.setInventario(inv);
                //JOptionPane.showMessageDialog(this, inv.getIdinventario());
                fifo.setNdocumento(0);
                fifo.setDetalle(txtdetalleajuste.getText());
                fifo.setCant_entrada((int) txtCanAj.getValue());
                fifo.setPrecio_entrada(txtPrecioAj.getText());
                fifo.setP_total_entrada(txtTotalAj.getText());//precio total

                //------------saldos---------
                fifo.setCant_saldo((int) txtCanAj.getValue());
                fifo.setPrecio_saldo(txtPrecioAj.getText());
                fifo.setP_total_saldo(txtTotalAj.getText());//precio total
                //----------------------------
                Operaciones.ingresoFifoAjuste(fifo);//ingresando el registro del ajuste al Kardex del Fifo
                //fin de la insercion del kardex fifo
                JOptionPane.showMessageDialog(this, "Ajuste realizado correctamente..!!");
                this.dispose();
                validaVentana = null;
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado.." + ex.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPrecioAjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioAjFocusLost
        // TODO add your handling code here:
        Double total = Integer.parseInt(txtCanAj.getValue().toString()) * Double.parseDouble(txtPrecioAj.getText());
        txtTotalAj.setText(Operaciones.redondear(total).toString());
    }//GEN-LAST:event_txtPrecioAjFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String varValidacion = vtnListarAjustes.validaVentana;
        if (varValidacion == null) {
            vtnListarAjustes almigr = new vtnListarAjustes();
            almigr.setResizable(false);//no es redimencionable
            almigr.setMaximizable(false);//no se puede maximizar
            almigr.setClosable(true);//si se puede cerra la ventana
            almigr.setIconifiable(true);
            sysMDI.add(almigr);
            almigr.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "La ventana listado de ajustes\nya esta activa..!!", null, JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboAlmacen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JTextField txtAjusteProducto;
    private javax.swing.JSpinner txtCanAj;
    public static javax.swing.JSpinner txtCantidad;
    public static javax.swing.JTextField txtPUnitario;
    private javax.swing.JTextField txtPrecioAj;
    public static javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotalAj;
    private javax.swing.JTextField txtdetalleajuste;
    private javax.swing.JTextField txtresponsable;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.OC;

import dao.ComprasDao;
import dao.PagoDao;
import dao.ProveedorDao;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mapeos.Compra;
import mapeos.Detallecompra;
import mapeos.Pago;
import mapeos.Proveedor;
import util.CodigoControlOC;
import util.ContadorPanelActividad;
import util.Operaciones;
import util.QR.MyQRCreator;
import util.Validaciones;
import view.vtnPrincipal;
import static view.OC.vtnListaProOD.validaVentana;
/**
 *
 * @author Reynaldo
 */
public class vtnOD extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnOD
     */
    public static String validaVentana;
    BufferedImage bufferedImage;//imagen QR

    public vtnOD() 
    {
        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*fin-----*/

        cargarProveedores();
        cargarFpago();
        txtNuOD.setText(String.valueOf(new ComprasDao().numeroUltimoReg()));
        codigoControl();

        Operaciones.parametrosCalendario(txtfechaE);
    }

    //Metodo  que parametriza el calendario de OC
    public void parametrosCalendario() 
    {
        Calendar calendario = new GregorianCalendar(2016, 5, 10);
        //Estableciendo la fecha minima de seleccion
        txtfechaE.setMinSelectableDate(calendario.getTime());
    }

    //Metodo que inserta el codigo de control 
    public void codigoControl() {
        txtCodControl.setText(CodigoControlOC.generarCodigoControl("OC-"));
        MyQRCreator qr = new MyQRCreator();
        setImageQR(qr.createQR(txtCodControl.getText(), 100));
    }

    public void setImageQR(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            this.bufferedImage = bufferedImage;
            ImageIcon icon = new ImageIcon(bufferedImage);
            lbmQR.setIcon(icon);
        }
    }

    //metodo que carga la forma de pago el un comboBox
    public void cargarFpago() {
        try {
            PagoDao pgDao = new PagoDao();
            List<Pago> listaP = pgDao.listarFpago();
            if (listaP.size() != 0) {
                for (int i = 0; i < listaP.size(); i++) {
                    comboFpago.addItem(listaP.get(i).getCodigo());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe tener por lo menos una forma de pago registrada ", null, JOptionPane.ERROR_MESSAGE);
                comboProvOD.setEnabled(false);
                btnAddProOD.setEnabled(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Date OptenerDate(String dateString) {
        Date date = null;
        Date formatteddate = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(dateString);
            formatteddate = df.parse(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return formatteddate;
    }

    //Metodo que carga la lista de proveedores
    public void cargarProveedores() 
    {
        try 
        {
            ProveedorDao provDao = new ProveedorDao();
            List<Proveedor> listaP = provDao.listarProveedoresActivos();
            if (listaP.size() != 0) {
                for (int i = 0; i < listaP.size(); i++) {
                    comboProvOD.addItem(listaP.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe tener por lo menos un proveedor registrado ", null, JOptionPane.ERROR_MESSAGE);
                comboProvOD.setEnabled(false);
                btnAddProOD.setEnabled(false);
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
        contenedorOD = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNuOD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCotProv = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaOD = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LBLiteral = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboProvOD = new javax.swing.JComboBox();
        btnAddProOD = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtResOD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtfechaE = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtCodControl = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboFpago = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        lbmQR = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setTitle("Orden de compra");
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

        contenedorOD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Número de OC:");

        txtNuOD.setEditable(false);
        txtNuOD.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtNuOD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNuOD.setToolTipText("Número de compra ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Referencia proveedor:");

        txtCotProv.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtCotProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCotProv.setToolTipText("Número de referencia de cotización del proveedor");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaOD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaOD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID PRODUCTO", "CANTIDAD", "PRODUCTO", "PRECIO UNITARIO", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaOD);
        if (tablaOD.getColumnModel().getColumnCount() > 0) {
            tablaOD.getColumnModel().getColumn(1).setPreferredWidth(25);
            tablaOD.getColumnModel().getColumn(3).setPreferredWidth(30);
            tablaOD.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

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

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/borrar.png"))); // NOI18N
        jButton4.setText("Eliminar producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Total:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("SON:");

        LBLiteral.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotal.setText("0.0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7)
                                .addGap(3, 3, 3)
                                .addComponent(LBLiteral, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(27, 27, 27)
                        .addComponent(txtTotal)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LBLiteral, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Proveedor:");

        comboProvOD.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N

        btnAddProOD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnAddProOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        btnAddProOD.setText("Adicionar");
        btnAddProOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProODActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboProvOD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddProOD)
                .addContainerGap(495, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboProvOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProOD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Responsable:");

        txtResOD.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtResOD.setToolTipText("Persona responsable de la compra");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Fecha entrega:");

        txtfechaE.setToolTipText("Fecha de entrega de la compra");
        txtfechaE.setDateFormatString("yyyy-MM-dd");
        txtfechaE.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Codigo control:");

        txtCodControl.setEditable(false);
        txtCodControl.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        txtCodControl.setToolTipText("Codigo de control del sistema");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Forma de pago");

        comboFpago.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        comboFpago.setToolTipText("Modalidad de pago (Ver Configuración formas de pago)");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmQR, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbmQR, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contenedorODLayout = new javax.swing.GroupLayout(contenedorOD);
        contenedorOD.setLayout(contenedorODLayout);
        contenedorODLayout.setHorizontalGroup(
            contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorODLayout.createSequentialGroup()
                .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorODLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenedorODLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contenedorODLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNuOD, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contenedorODLayout.createSequentialGroup()
                                .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtResOD, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfechaE, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32)
                        .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCotProv, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodControl)
                            .addComponent(comboFpago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenedorODLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contenedorODLayout.setVerticalGroup(
            contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorODLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contenedorODLayout.createSequentialGroup()
                        .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNuOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtCotProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(comboFpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtfechaE, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtResOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorODLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtCodControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GENERAR ORDEN DE COMPRA");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/OC.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorOD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProODActionPerformed
        String varValicadion = vtnListaProOD.validaVentana;//recuperando el valor de la variable de validacion de la ventana
        if (varValicadion == null) 
        {
            vtnListaProOD listaPro = new vtnListaProOD(comboProvOD.getSelectedItem().toString());

            vtnPrincipal.sysMDI.add(listaPro);
            listaPro.setClosable(true);//si se puede cerra la ventana
            listaPro.setTitle("Seleccione un producto");
            //listaPro.setLocationRelativeTo(null);
           
            listaPro.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "La ventana de adicionar productos ya esta activa..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddProODActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        this.validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tablaOD.getModel();

        if (tablaOD.getRowCount() > 0) {
            if (tablaOD.getSelectedRows().length != 0) {
                modelo.removeRow(tablaOD.getSelectedRow());
                if (tablaOD.getRowCount() == 0) {
                    comboProvOD.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione el producto a eliminar ", "Mensaje..", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene productos a eliminar..!! ", "Mensaje..", JOptionPane.ERROR_MESSAGE);
            comboProvOD.setEnabled(true);//Habilitando el combo de proveedores
        }
        vtnListaProOD.suma(modelo);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        validaVentana = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try 
        {
            List<String> validar = new ArrayList<>();
            validar.add(txtNuOD.getText());
            validar.add(txtCotProv.getText());
            validar.add(txtResOD.getText());
            validar.add(txtCodControl.getText());

            if (Validaciones.validarCampos(validar) && txtfechaE.getDate() != null) 
            {
                Compra cm = new Compra();
                cm.setIdcompra(Integer.parseInt(txtNuOD.getText()));
                cm.setNcotizacion(txtCotProv.getText());//Numero de cotizacion del proveedor
                //cm.setFecha(OptenerDate((txtfechaC.getDateFormatString())));//fecha compra

                int año = txtfechaE.getCalendar().get(Calendar.YEAR);
                int mes = txtfechaE.getCalendar().get(Calendar.MONTH) + 1;
                int dia = txtfechaE.getCalendar().get(Calendar.DAY_OF_MONTH);
                String fecha = año + "-" + mes + "-" + dia;
                //JOptionPane.showMessageDialog(this, "fecha--->" + fecha);
                cm.setFechaemtrega(fecha);

                //cm.setFechaemtrega(String.valueOf(OptenerDate((txtfechaE.getDateFormatString()))));//fecha entrega
                cm.setResponsable(txtResOD.getText());//responsable
                cm.setCodigo(txtCodControl.getText());//codigo de control
                cm.setPreciototal(txtTotal.getText());//precio total
                cm.setEstado("En espera");//estado

                //RECUPERANDO LA FORMA DE PAGO Y SETEANDO//
                PagoDao pgDao = new PagoDao();
                Pago pg = pgDao.buscarPago(comboFpago.getSelectedItem().toString());
                cm.setPago(pg);
                //FIN DE LA RECUPERACION DE LA FORMA DE PAGO..///

                //RECUPERANDO EL DETALLE DE LOS ITEMS SELECCIONADOS EN LA TABLA
                Set<Detallecompra> items = new HashSet<>();
                for (int i = 0; i < tablaOD.getRowCount(); i++) 
                {

                    Detallecompra det = new Detallecompra();
                    det.setCantidad(String.valueOf(tablaOD.getValueAt(i, 1)));
                    det.setProduto_idproducto(Integer.parseInt((String) tablaOD.getValueAt(i, 0)));
                    det.setPrecioCompra(String.valueOf(tablaOD.getValueAt(i, 3)));
                    det.setCompra(cm);
                    det.setEstado("0");
                    items.add(det);
                    //items.add(new Detallecompra(String.valueOf(tablaOD.getValueAt(i, 1)), Integer.parseInt((String) tablaOD.getValueAt(i, 0)), cm, "0"));

                    //                    Detallecompra det = new Detallecompra();
                    //                    det.setCantidad(String.valueOf(tablaOD.getValueAt(i, 0)));
                    //                    det.setProducto(new ProductoDao().buscarProducto2(String.valueOf(tablaOD.getValueAt(i, 1))));
                    //                    det.setCompra(cm);
                    //                    items.add(det);
                }
                //det.setCompra(cm);
                cm.setDetalleCompra(items);
                //FIN DE LA RECUPERACION DE LOS ITEMS
                if (tablaOD.getRowCount() > 0) 
                {
                    ComprasDao cmDao = new ComprasDao();
                    if (cmDao.registarCompra(cm)) {
                        JOptionPane.showMessageDialog(this, "Registro de OC correcto.", "Mensaje..", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        validaVentana = null;
                        ContadorPanelActividad.contadorOC();//actualizando el contador de actividad
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Adicione un producto(s) a comprar..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje..", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error--->" + e.getMessage(), "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel LBLiteral;
    private javax.swing.JButton btnAddProOD;
    private javax.swing.JComboBox comboFpago;
    public static javax.swing.JComboBox comboProvOD;
    public static javax.swing.JPanel contenedorOD;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbmQR;
    public static javax.swing.JTable tablaOD;
    private javax.swing.JTextField txtCodControl;
    private javax.swing.JTextField txtCotProv;
    public static javax.swing.JTextField txtNuOD;
    private javax.swing.JTextField txtResOD;
    public static javax.swing.JTextField txtTotal;
    private com.toedter.calendar.JDateChooser txtfechaE;
    // End of variables declaration//GEN-END:variables
}

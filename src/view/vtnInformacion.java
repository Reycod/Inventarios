/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.InformacionDao;
import dao.PagoDao;
import dao.UnidadMedidaDao;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import mapeos.Informacion;
import mapeos.Pago;
import mapeos.UnidadMedida;
import mapeos.Usuario;
import util.BackUpSample;
import util.Conexion;
import util.Validaciones;

/**
 *
 * @author Reynaldo
 */
public class vtnInformacion extends javax.swing.JInternalFrame {

    private Pago ObjPago = new Pago();
    private UnidadMedida ObjMedida = new UnidadMedida();

    //----------Objeto Informacion
    private Informacion objInfo;
    //-----------------------------

    public UnidadMedida getObjMedida() {
        return ObjMedida;
    }

    public void setObjMedida(UnidadMedida ObjMedida) {
        this.ObjMedida = ObjMedida;
    }
    

    public vtnInformacion() 
    {
        initComponents();
        listarDatosFPago();
        listarDatosUMedida();
        bloquearCamposFPago();
        bloquearCamposUnidades();
        InformacionDao infoDao = new InformacionDao();
        /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        llenarComboBD();
        //FIN DE LA ALINEACION DE LA VENTANA
        
        //---------------------------------------------
        //Validando los campos de ingreso de numero 
        Validaciones.validaNumeros(txtNit);
        Validaciones.validaNumeros(txtFonoInformacion);
        Validaciones.validaNumeros(txtFaxInformacion);
        //---------------------------------------------
        try 
        {
            List<Informacion> datosInfo = infoDao.listarInformacion();

            for (int i = 0; i < datosInfo.size(); ++i) 
            {
                txtRazonS.setText(datosInfo.get(i).getRazonsocial());
                txtNit.setText(datosInfo.get(i).getNit());
                txtDirInformacion.setText(datosInfo.get(i).getDireccion());
                txtFonoInformacion.setText(datosInfo.get(i).getTelefono());
                txtFaxInformacion.setText(datosInfo.get(i).getFax());
                txtMailInfo.setText(datosInfo.get(i).getEmail());
                txtWebInfo.setText(datosInfo.get(i).getWeb());

                //recuperando la imagen y convirtiendo en ImageIco
                InputStream is = new ByteArrayInputStream(datosInfo.get(i).getLogo());
                BufferedImage image = ImageIO.read(is);
                ImageIcon ico = new ImageIcon(image);
              
                //ImageIcon iconoF = new ImageIcon(ico.getImage().getScaledInstance(lbmInformacion.getWidth(), lbmInformacion.getHeight(), Image.SCALE_DEFAULT));
                lbmInformacion.setIcon(ico);
                //fin de la recuperacion y muetra de la imagen

                //generando el objeto informacion
                Informacion infoBD = new Informacion();
                infoBD.setIdinformacion(datosInfo.get(i).getIdinformacion());
                infoBD.setRazonsocial(datosInfo.get(i).getRazonsocial());
                infoBD.setNit(datosInfo.get(i).getNit());
                infoBD.setDireccion(datosInfo.get(i).getDireccion());
                infoBD.setTelefono(datosInfo.get(i).getTelefono());
                infoBD.setFax(datosInfo.get(i).getFax());
                infoBD.setEmail(datosInfo.get(i).getEmail());
                infoBD.setWeb(datosInfo.get(i).getWeb());
                infoBD.setLogo(datosInfo.get(i).getLogo());
                this.setObjInfo(infoBD);//insertando el objeto a actualizar
                //--------------------------------------------
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(this, "Error al recuperar la infomación del sistema..!!" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que llena el comboBox con el listado de las BD
    public void llenarComboBD() {
        Connection conex = Conexion.getConectar();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conex.prepareStatement("show databases");
            rs = ps.executeQuery();
            while (rs.next()) {
                comboBD.addItem(rs.getObject(1));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos...!!");
        }
    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarDatosFPago() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaFPago.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaFPago);
            //realizando la consulta para realizar el listado de los datos
            PagoDao pgDao = new PagoDao();
            List<Pago> lista = pgDao.listarFpago();
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getId();
                fila[1] = lista.get(i).getTipo();
                fila[2] = lista.get(i).getCodigo();

                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listarDatosUMedida() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaUmedida.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaUmedida);
            //realizando la consulta para realizar el listado de los datos
            UnidadMedidaDao pgDao = new UnidadMedidaDao();
            List<UnidadMedida> lista = pgDao.listarUnidades();
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getIdunidadmedida();
                fila[1] = lista.get(i).getNombre();
                fila[2] = lista.get(i).getAbreviacion();
                modelo.addRow(fila);
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

    //Metodo que bloquea los campos de texto
    public void bloquearCamposFPago() {
        //txtCodigoFpago.setEnabled(false);
        // txtFormaPago.setEnabled(false);
        btnAceptarPago.setEnabled(false);

    }

    //Metodo que bloquea los campos de texto
    public void bloquearCamposUnidades() {
        //txtCodigoFpago.setEnabled(false);
        // txtFormaPago.setEnabled(false);
        btnGuardarU.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRazonS = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        txtDirInformacion = new javax.swing.JTextField();
        txtFonoInformacion = new javax.swing.JTextField();
        txtFaxInformacion = new javax.swing.JTextField();
        txtMailInfo = new javax.swing.JTextField();
        txtWebInfo = new javax.swing.JTextField();
        ciudadInfo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtImgInformacion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lbmInformacion = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtFormaPago = new javax.swing.JTextField();
        btnAceptarPago = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFPago = new javax.swing.JTable();
        btnEliminarFpago = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoFpago = new javax.swing.JTextField();
        btnActuFpago = new javax.swing.JToggleButton();
        btnNFpago = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtUnidadM = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAbreviacion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUmedida = new javax.swing.JTable();
        btnGuardarU = new javax.swing.JButton();
        btnActU = new javax.swing.JButton();
        btnEliminarU = new javax.swing.JButton();
        btnNuevoU = new javax.swing.JToggleButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtRespaldo = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        lbmBD = new javax.swing.JLabel();
        comboBD = new javax.swing.JComboBox();

        setTitle("Parametros ");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        txtRazonS.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtRazonS.setToolTipText("Ingrese el nombre de su empresa");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nit:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Dirección:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Teléfono:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Fax:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Email:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Web:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Ciudad:");

        txtNit.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtNit.setToolTipText("Ingrese el número de Nit/CI");

        txtDirInformacion.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtDirInformacion.setToolTipText("Ingrese la dirección de su empresa");

        txtFonoInformacion.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtFonoInformacion.setToolTipText("Ingrese el teléfono de su empresa");

        txtFaxInformacion.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtFaxInformacion.setToolTipText("Ingrese el número de fax");

        txtMailInfo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtMailInfo.setToolTipText("Ingrese el E-mail de su empresa");

        txtWebInfo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtWebInfo.setToolTipText("Ingrese la dirección de la pagina de su empresa");

        ciudadInfo.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        ciudadInfo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "La Paz", "Oruro", "Potosi", "Beni", "Pando", "Tarija", "Chuquisaca", "Cochabamba", "Santa Cruz" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("Click para seleccionar una imagen");

        txtImgInformacion.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/cancelar.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setToolTipText("Click para seleccionar una imagen..");
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbmInformacion.setBackground(new java.awt.Color(153, 0, 102));
        lbmInformacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbmInformacionMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbmInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbmInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 0, 51));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_64.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("INFORMACIÓN");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFaxInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(40, 40, 40)
                                    .addComponent(txtMailInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7))
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ciudadInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtWebInfo))))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtRazonS, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDirInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFonoInformacion))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(38, 38, 38))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtImgInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtImgInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtRazonS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDirInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFonoInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtFaxInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMailInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtWebInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ciudadInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jLabel9))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Información general", jPanel4);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar formas de pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Forma de pago:");

        txtFormaPago.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFormaPago.setToolTipText("Descripción de la forma de pago de una compra.");

        btnAceptarPago.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnAceptarPago.setText("Aceptar");
        btnAceptarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarPagoActionPerformed(evt);
            }
        });

        tablaFPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "FORMA DE PAGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaFPagoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFPago);
        if (tablaFPago.getColumnModel().getColumnCount() > 0) {
            tablaFPago.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        btnEliminarFpago.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnEliminarFpago.setText("Eliminar");
        btnEliminarFpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFpagoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Codigo:");

        txtCodigoFpago.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtCodigoFpago.setToolTipText("Codigo asociado a la forma de pago.");

        btnActuFpago.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnActuFpago.setText("Actualizar");
        btnActuFpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuFpagoActionPerformed(evt);
            }
        });

        btnNFpago.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNFpago.setText("Nuevo");
        btnNFpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNFpagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFormaPago)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(txtCodigoFpago, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNFpago, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAceptarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActuFpago)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarFpago, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoFpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActuFpago, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarFpago, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNFpago, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Formas de pago", jPanel5);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Unidad:");

        txtUnidadM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Abreviación:");

        txtAbreviacion.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaUmedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "ABREVIACIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUmedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaUmedidaMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaUmedida);
        if (tablaUmedida.getColumnModel().getColumnCount() > 0) {
            tablaUmedida.getColumnModel().getColumn(0).setMinWidth(50);
            tablaUmedida.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaUmedida.getColumnModel().getColumn(0).setMaxWidth(50);
            tablaUmedida.getColumnModel().getColumn(2).setMinWidth(120);
            tablaUmedida.getColumnModel().getColumn(2).setPreferredWidth(120);
            tablaUmedida.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        btnGuardarU.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnGuardarU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        btnGuardarU.setText("Guardar");
        btnGuardarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUActionPerformed(evt);
            }
        });

        btnActU.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnActU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/modificar.png"))); // NOI18N
        btnActU.setText("Actualizar");
        btnActU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActUActionPerformed(evt);
            }
        });

        btnEliminarU.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnEliminarU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/borrar.png"))); // NOI18N
        btnEliminarU.setText("Eliminar");
        btnEliminarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUActionPerformed(evt);
            }
        });

        btnNuevoU.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNuevoU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        btnNuevoU.setText("Nuevo");
        btnNuevoU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(btnNuevoU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarU)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoU, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarU)
                    .addComponent(btnActU)
                    .addComponent(btnEliminarU))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(204, 0, 51));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/regla.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("DEFINICIÓN DE UNIDAD DE MEDIDA");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(29, 29, 29)
                        .addComponent(txtUnidadM, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtAbreviacion)
                        .addGap(180, 180, 180))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtUnidadM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbreviacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Unidades de Medida", jPanel2);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBackground(new java.awt.Color(204, 0, 51));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Administracion Base de Datos");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database_4_64.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addContainerGap(263, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(31, 31, 31))))
        );

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setText("Backup BD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setText("Restaurar BD");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Ruta respaldo BD");

        txtRespaldo.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("....");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Ruta respaldar BD:");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("...");

        lbmBD.setText("jLabel20");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBD, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtRespaldo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(lbmBD)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(comboBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRespaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton3))
                .addGap(22, 22, 22)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton4))
                .addGap(67, 67, 67)
                .addComponent(lbmBD)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Administracion BD", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {

            Informacion info = this.getObjInfo();

            InformacionDao infoDao = new InformacionDao();

            info.setRazonsocial(txtRazonS.getText());
            info.setNit(txtNit.getText());
            info.setDireccion(txtDirInformacion.getText());
            info.setTelefono(txtFonoInformacion.getText());
            info.setFax(txtFaxInformacion.getText());
            info.setCiudad(ciudadInfo.getSelectedItem().toString());
            info.setEmail(txtMailInfo.getText());
            info.setWeb(txtWebInfo.getText());

            //JOptionPane.showMessageDialog(this, info.getDireccion());

            if (!txtImgInformacion.getText().equals("")) 
            {
                //Tratando la imagen
                File archivoImagen = new File(txtImgInformacion.getText());//recuperando la url de la imagen
                byte[] bytefile = new byte[(int) archivoImagen.length()];
                FileInputStream fs = new FileInputStream(archivoImagen);
                fs.read(bytefile);
                fs.close();

                info.setLogo(bytefile);
            }
            
            if (infoDao.actualizarInformacion(info)) 
            {
                JOptionPane.showMessageDialog(this, "Información guardada", "mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAceptarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarPagoActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtFormaPago.getText());
            validar.add(txtCodigoFpago.getText());

            if (Validaciones.validarCampos(validar)) {
                Pago pg = new Pago();
                pg.setTipo(txtFormaPago.getText());
                pg.setCodigo(txtCodigoFpago.getText());

                PagoDao pgDao = new PagoDao();
                if (pgDao.registarFormaPago(pg)) {
                    JOptionPane.showMessageDialog(this, "Registro correcto.", null, JOptionPane.INFORMATION_MESSAGE);
                    txtFormaPago.setText("");
                    txtCodigoFpago.setText("");
                    btnAceptarPago.setEnabled(false);
                    btnActuFpago.setEnabled(true);
                    btnEliminarFpago.setEnabled(true);
                    btnNFpago.setText(" Nuevo ");

                    listarDatosFPago();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarPagoActionPerformed

    private void tablaFPagoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFPagoMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaFPago.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaFPago.getSelectedRow(), 0));
        PagoDao pgDao = new PagoDao();
        try {
            Pago pg = new Pago(Integer.parseInt(dato), String.valueOf(tm.getValueAt(tablaFPago.getSelectedRow(), 1)), String.valueOf(tm.getValueAt(tablaFPago.getSelectedRow(), 2)));
            this.setObjPago(pg);//insertando el
            txtFormaPago.setText(pg.getTipo());
            txtCodigoFpago.setText(pg.getCodigo());

            //JOptionPane.showMessageDialog(null, "Precionado" + cat.getNombre());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaFPagoMousePressed

    private void btnEliminarFpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFpagoActionPerformed
        // TODO add your handling code here:
        int y = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?");
        try {
            if (y == JOptionPane.YES_OPTION) {
                PagoDao pgDao = new PagoDao();
                if (pgDao.eliminarFpago(this.getObjPago())) {
                    JOptionPane.showMessageDialog(this, "Eliminacion correcta", null, JOptionPane.INFORMATION_MESSAGE);
                    txtFormaPago.setText("");
                    txtCodigoFpago.setText("");

                    bloquearCamposFPago();
                    listarDatosFPago();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hay productos asociados a esta categoria\npor lo que no se puede eliminar..!!", null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarFpagoActionPerformed

    private void btnActuFpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuFpagoActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtFormaPago.getText());
            validar.add(txtCodigoFpago.getText());
            if (Validaciones.validarCampos(validar)) {
                Pago pg = this.getObjPago();
                pg.setTipo(txtFormaPago.getText());
                pg.setCodigo(txtCodigoFpago.getText());

                PagoDao pgDao = new PagoDao();
                if (pgDao.actualizarFormaPago(pg)) {
                    JOptionPane.showMessageDialog(this, "Actualización correcta.", null, JOptionPane.INFORMATION_MESSAGE);
                    txtFormaPago.setText("");
                    txtCodigoFpago.setText("");
                    btnAceptarPago.setEnabled(false);
                    btnActuFpago.setEnabled(true);
                    btnEliminarFpago.setEnabled(true);

                    listarDatosFPago();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActuFpagoActionPerformed

    private void btnNFpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNFpagoActionPerformed
        if (btnNFpago.isSelected()) {
            txtCodigoFpago.setText("");
            txtFormaPago.setText("");

            txtCodigoFpago.setEnabled(true);
            txtFormaPago.setEnabled(true);
            btnAceptarPago.setEnabled(true);
            btnEliminarFpago.setEnabled(false);
            btnActuFpago.setEnabled(false);
            btnNFpago.setText("  Cancelar  ");
        } else {
            btnNFpago.setText("  Nuevo  ");
            txtCodigoFpago.setText("");
            txtFormaPago.setText("");
            btnAceptarPago.setEnabled(false);
            btnEliminarFpago.setEnabled(true);
            btnActuFpago.setEnabled(true);
        }
    }//GEN-LAST:event_btnNFpagoActionPerformed

    private void btnGuardarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtUnidadM.getText());
            validar.add(txtAbreviacion.getText());

            if (Validaciones.validarCampos(validar)) {
                UnidadMedida pg = new UnidadMedida(txtUnidadM.getText(), txtAbreviacion.getText());
                UnidadMedidaDao uniDao = new UnidadMedidaDao();
                if (uniDao.registarUnidadMedida(pg)) {
                    JOptionPane.showMessageDialog(this, "Registro de Unidad de Medida correcto..", null, JOptionPane.INFORMATION_MESSAGE);
                    txtUnidadM.setText("");
                    txtAbreviacion.setText("");
                    btnGuardarU.setEnabled(false);
                    btnActU.setEnabled(true);
                    btnEliminarU.setEnabled(true);
                    btnNuevoU.setText("Nuevo");

                    listarDatosUMedida();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarUActionPerformed

    private void btnNuevoUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUActionPerformed
        // TODO add your handling code here:
        if (btnNuevoU.isSelected()) {
            txtUnidadM.setText("");
            txtAbreviacion.setText("");

            txtUnidadM.setEnabled(true);
            txtAbreviacion.setEnabled(true);

            btnEliminarU.setEnabled(false);
            btnActU.setEnabled(false);
            btnNuevoU.setText("Cancelar");
            btnGuardarU.setEnabled(true);
        } else {
            btnNuevoU.setText("Nuevo");

            txtAbreviacion.setText("");
            btnGuardarU.setEnabled(false);
            btnEliminarU.setEnabled(true);
            btnActU.setEnabled(true);
        }
    }//GEN-LAST:event_btnNuevoUActionPerformed

    private void tablaUmedidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUmedidaMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaUmedida.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaUmedida.getSelectedRow(), 0));
        UnidadMedidaDao pgDao = new UnidadMedidaDao();
        try {
            UnidadMedida pg = new UnidadMedida(Integer.parseInt(dato), String.valueOf(tm.getValueAt(tablaUmedida.getSelectedRow(), 1)), String.valueOf(tm.getValueAt(tablaUmedida.getSelectedRow(), 2)));
            this.setObjMedida(pg);//insertando el
            txtUnidadM.setText(pg.getNombre());
            txtAbreviacion.setText(pg.getAbreviacion());

            //JOptionPane.showMessageDialog(null, "Precionado" + cat.getNombre());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaUmedidaMousePressed

    private void btnActUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActUActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtUnidadM.getText());
            validar.add(txtAbreviacion.getText());
            if (Validaciones.validarCampos(validar)) {

                UnidadMedida pg = this.getObjMedida();
                pg.setNombre(txtUnidadM.getText());
                pg.setAbreviacion(txtAbreviacion.getText());

                UnidadMedidaDao uniDao = new UnidadMedidaDao();

                if (uniDao.actualizarUnidadMedida(pg)) {
                    JOptionPane.showMessageDialog(this, "Actualizacion correcta", null, JOptionPane.INFORMATION_MESSAGE);
                    txtUnidadM.setText("");
                    txtAbreviacion.setText("");
                    btnGuardarU.setEnabled(false);
                    btnActU.setEnabled(true);
                    btnEliminarU.setEnabled(true);
                    btnNuevoU.setText("Nuevo");

                    listarDatosUMedida();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActUActionPerformed

    private void btnEliminarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUActionPerformed
        // TODO add your handling code here:
        if (tablaUmedida.getSelectedRows().length != 0) {
            int y = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?");
            try {
                if (y == JOptionPane.YES_OPTION) {
                    UnidadMedidaDao pgDao = new UnidadMedidaDao();
                    if (pgDao.eliminarUnidadMedida(this.getObjMedida())) {
                        JOptionPane.showMessageDialog(this, "Eliminacion correcta", null, JOptionPane.INFORMATION_MESSAGE);
                        txtUnidadM.setText("");
                        txtAbreviacion.setText("");

                        bloquearCamposUnidades();
                        listarDatosUMedida();
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hay productos asociados a esta Unidad de Medida\npor lo que no se puede eliminar..!!", null, JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una unidad de medida\na eliminar..!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEliminarUActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        JFileChooser fileBackup = new JFileChooser();
        //FIN DE DECLARACION DE DATOS DE RESPALDO
        try {
            fileBackup.setDialogTitle("Respaldar BD");
            if (fileBackup.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                txtRespaldo.setText(fileBackup.getSelectedFile().getAbsolutePath());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al establecer la direccion de respaldo..");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //DATOS PARA RESPALDO DE BD

        String user = "root";
        String password = "6036336";
        String bd = "sysinventory";

        BackUpSample backup = new BackUpSample();
        JOptionPane.showMessageDialog(this, "---->" + txtRespaldo.getText());

        if (backup.CrearBackup("localhost", "3306", user, password, bd, txtRespaldo.getText())) {
            JOptionPane.showMessageDialog(this, "Respaldo de la BD realizado con exito..!!");
        } else {
            JOptionPane.showMessageDialog(this, "Error al respaldar la BD..!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void lbmInformacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmInformacionMousePressed
        // TODO add your handling code here:
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        JFileChooser abrir = new JFileChooser();
        abrir.setFileFilter(filtroImagen);
        abrir.setDialogTitle("Seleccionar imagen..");
        int ventana = abrir.showOpenDialog(null);
        /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //FIN DE LA ALINEACION DE LA VENTANA
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = abrir.getSelectedFile();
            txtImgInformacion.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtImgInformacion.getText());//recuperando la URL de la imagen y convirtiendola en un objeto de tipo imagen
            foto = foto.getScaledInstance(lbmInformacion.getWidth(), lbmInformacion.getHeight(), Image.SCALE_DEFAULT);
            lbmInformacion.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_lbmInformacionMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarPago;
    private javax.swing.JButton btnActU;
    private javax.swing.JToggleButton btnActuFpago;
    private javax.swing.JButton btnEliminarFpago;
    private javax.swing.JButton btnEliminarU;
    private javax.swing.JButton btnGuardarU;
    private javax.swing.JToggleButton btnNFpago;
    private javax.swing.JToggleButton btnNuevoU;
    private javax.swing.JComboBox ciudadInfo;
    private javax.swing.JComboBox comboBD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbmBD;
    private javax.swing.JLabel lbmInformacion;
    private javax.swing.JTable tablaFPago;
    private javax.swing.JTable tablaUmedida;
    private javax.swing.JTextField txtAbreviacion;
    private javax.swing.JTextField txtCodigoFpago;
    private javax.swing.JTextField txtDirInformacion;
    private javax.swing.JTextField txtFaxInformacion;
    private javax.swing.JTextField txtFonoInformacion;
    private javax.swing.JTextField txtFormaPago;
    private javax.swing.JTextField txtImgInformacion;
    private javax.swing.JTextField txtMailInfo;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtRazonS;
    private javax.swing.JTextField txtRespaldo;
    private javax.swing.JTextField txtUnidadM;
    private javax.swing.JTextField txtWebInfo;
    // End of variables declaration//GEN-END:variables

    public Pago getObjPago() {
        return ObjPago;
    }

    public void setObjPago(Pago ObjPago) {
        this.ObjPago = ObjPago;
    }

    public Informacion getObjInfo() {
        return objInfo;
    }

    public void setObjInfo(Informacion objInfo) {
        this.objInfo = objInfo;
    }
}

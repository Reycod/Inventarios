/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CategoriaDao;
import dao.ProductoDao;
import dao.ProveedorDao;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mapeos.Categoria;
import mapeos.Producto;
import mapeos.Proveedor;
import util.Validaciones;
import static view.OC.vtnListarCompras.validaVentana;

/**
 *
 * @author Reynaldo
 */
public class vtnProveedor extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnProveedor
     */
    private Proveedor ObjProveedor = new Proveedor();
    public static String validaVentana;

    public vtnProveedor() {

        initComponents();
        validaVentana = "x";//inicializando la variable de validacion con un valor cualquiera
        /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //FIN DE LA ALINEACION DE LA VENTANA

        bloquearCamposProv();
        listarDatos();

        //Validando el ingreso de datos
        Validaciones.validaNumeros(txtFonoProv);
        Validaciones.validaNumeros(txtNitProveedor);
        Validaciones.validaNumeros(txtFaxProv);
        Validaciones.validaTexto(txtContacProv);
        Validaciones.validaTexto(txtContacProv);
        Validaciones.validaNumeros(txtFonoContacto);

        //fin e la validacion
    }

//Metodo que limpia los campos de texto
    public void limpiarCamposProv() {
        txtNitProveedor.setText("");
        txtRsocialProv.setText("");
        txtDirProv.setText("");
        txtFaxProv.setText("");
        txtFonoProv.setText("");
        txtContacProv.setText("");
        txtWebProv.setText("");
        txtEmailProv.setText("");
        txtObsProv.setText("");
        RadioAcProv.setSelected(true);
        txtFonoContacto.setText("");
    }

    //Metodo que bloquea los campos de texto
    public void bloquearCamposProv() {
        txtNitProveedor.setEnabled(false);
        txtRsocialProv.setEnabled(false);
        txtDirProv.setEnabled(false);
        txtFaxProv.setEnabled(false);
        txtFonoProv.setEnabled(false);
        txtContacProv.setEnabled(false);
        txtWebProv.setEnabled(false);
        txtEmailProv.setEnabled(false);
        txtObsProv.setEnabled(false);
        RInacProv.setEnabled(false);
        RadioAcProv.setEnabled(false);
        btnAcProv.setEnabled(false);
        btnCanProv.setEnabled(false);
        btnActualizarProv.setEnabled(false);
        btnCancelarProv.setEnabled(false);
        txtFonoContacto.setEnabled(false);
    }

    //Metodo que habilita los campos de texto
    public void habilitarCamposCategoria() {
        txtNitProveedor.setEnabled(true);
        txtRsocialProv.setEnabled(true);
        txtDirProv.setEnabled(true);
        txtFaxProv.setEnabled(true);
        txtFonoProv.setEnabled(true);
        txtContacProv.setEnabled(true);
        txtWebProv.setEnabled(true);
        txtEmailProv.setEnabled(true);
        txtObsProv.setEnabled(true);
        RInacProv.setEnabled(true);
        RadioAcProv.setEnabled(true);
        RadioAcProv.setSelected(true);
        btnAcProv.setEnabled(true);
        btnCanProv.setEnabled(true);
        txtFonoContacto.setEnabled(true);

        limpiarCamposProv();
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

    public void listarDatos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProveedor.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaProveedor);
            //realizando la consulta para realizar el listado de los datos
            ProveedorDao proDao = new ProveedorDao();
            List<Proveedor> lista = proDao.listarProveedores();
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getIdproveedor();
                fila[1] = lista.get(i).getNit();
                fila[2] = lista.get(i).getNombre();
                fila[3] = lista.get(i).getDireccion();
                fila[4] = lista.get(i).getTelefono();
                fila[5] = lista.get(i).getNrofax();
                fila[6] = lista.get(i).getContacto();
                fila[7] = lista.get(i).getFonoContacto();
                fila[8] = lista.get(i).getEstado();
                fila[9] = lista.get(i).getWeb();
                fila[10] = lista.get(i).getEmail();
                fila[11] = lista.get(i).getObservacion();

                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, null, JOptionPane.ERROR_MESSAGE);
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

        Grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNitProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRsocialProv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDirProv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFonoProv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFaxProv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtContacProv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        RadioAcProv = new javax.swing.JRadioButton();
        RInacProv = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtWebProv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmailProv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObsProv = new javax.swing.JTextArea();
        btnAcProv = new javax.swing.JButton();
        btnCanProv = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtFonoContacto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedor = new javax.swing.JTable();
        btnActualizarProv = new javax.swing.JButton();
        btnCancelarProv = new javax.swing.JButton();
        btnHabilitar = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("*Nit:");

        txtNitProveedor.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("*Razon social:");

        txtRsocialProv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("*Dirección:");

        txtDirProv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Teléfono:");

        txtFonoProv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtFonoProv.setToolTipText("Solo ingrese números  ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Fax:");

        txtFaxProv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("*Contacto:");

        txtContacProv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Estado");

        Grupo.add(RadioAcProv);
        RadioAcProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioAcProv.setText("Activo");

        Grupo.add(RInacProv);
        RInacProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RInacProv.setText("Inactivo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Web:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Observaciones:");

        txtObsProv.setColumns(20);
        txtObsProv.setRows(5);
        jScrollPane1.setViewportView(txtObsProv);

        btnAcProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAcProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        btnAcProv.setText("Guardar");
        btnAcProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcProvActionPerformed(evt);
            }
        });

        btnCanProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCanProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/cancelar.png"))); // NOI18N
        btnCanProv.setText("Cancelar");
        btnCanProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanProvActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("*Tel. contacto:");

        txtFonoContacto.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAcProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCanProv))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNitProveedor)
                            .addComponent(txtFaxProv)
                            .addComponent(txtContacProv)
                            .addComponent(txtFonoProv)
                            .addComponent(txtDirProv)
                            .addComponent(txtRsocialProv)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailProv, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel13))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFonoContacto)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RadioAcProv)
                                .addGap(18, 18, 18)
                                .addComponent(RInacProv)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtWebProv))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNitProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRsocialProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFonoProv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFaxProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtContacProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtFonoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RadioAcProv)
                        .addComponent(RInacProv)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtWebProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmailProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel10)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCanProv)
                    .addComponent(btnAcProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NIT", "RAZÓN SOCIAL", "DIRECCIÓN", "TELEFONO", "FAX", "CONTACTO", "TEL. CONTACTO", "ESTADO", "WEB", "EMAIL", "OBS."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProveedorMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProveedor);
        if (tablaProveedor.getColumnModel().getColumnCount() > 0) {
            tablaProveedor.getColumnModel().getColumn(0).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        btnActualizarProv.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnActualizarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/modificar.png"))); // NOI18N
        btnActualizarProv.setText("Actualizar");
        btnActualizarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProvActionPerformed(evt);
            }
        });

        btnCancelarProv.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnCancelarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/borrar.png"))); // NOI18N
        btnCancelarProv.setText("Eliminar");
        btnCancelarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProvActionPerformed(evt);
            }
        });

        btnHabilitar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnHabilitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/habilitar.png"))); // NOI18N
        btnHabilitar.setText("Habilitar edición");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(btnHabilitar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizarProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 51));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("GESTIÓN DE PROVEEDORES");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/listaCompras.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedorMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaProveedor.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 0));
        ProveedorDao proDao = new ProveedorDao();
        try 
        {
            Proveedor pro = new Proveedor();
            pro.setIdproveedor(Integer.parseInt(dato));
            pro.setNit(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 1)));
            pro.setNombre(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 2)));
            pro.setDireccion(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 3)));
            pro.setTelefono(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 4)));
            pro.setNrofax(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 5)));
            pro.setContacto(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 6)));
            pro.setFonoContacto(Integer.parseInt(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(),7))));
            //String telCon=
            pro.setEstado(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 8)));
            pro.setWeb(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 9)));
            pro.setEmail(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 10)));
            pro.setObservacion(String.valueOf(tm.getValueAt(tablaProveedor.getSelectedRow(), 11)));
            
            this.setObjProveedor(pro);//insertando el
            txtNitProveedor.setText(pro.getNit());
            txtRsocialProv.setText(pro.getNombre());
            txtDirProv.setText(pro.getDireccion());
            txtFonoProv.setText(pro.getTelefono());
            txtFaxProv.setText(pro.getNrofax());
            txtContacProv.setText(pro.getContacto());
            txtWebProv.setText(pro.getWeb());
            txtEmailProv.setText(pro.getEmail());
            txtObsProv.setText(pro.getObservacion());
            txtFonoContacto.setText(String.valueOf(pro.getFonoContacto()));
            
            if (pro.getEstado().equals("Activo")) {
                RadioAcProv.setSelected(true);
            } else if (pro.getEstado().equals("Inactivo")) {
                RInacProv.setSelected(true);
            }
            //JOptionPane.showMessageDialog(null, "Precionado" + cat.getNombre());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaProveedorMousePressed

    private void btnCancelarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProvActionPerformed
        // TODO add your handling code here:
        int y = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el proveedor?");
        try {
            if (y == JOptionPane.YES_OPTION) {
                ProveedorDao pro = new ProveedorDao();
                if (pro.eliminarProveedor(this.getObjProveedor())) {
                    JOptionPane.showMessageDialog(this, "Eliminacion correcta", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposProv();
                    bloquearCamposProv();
                    listarDatos();
                    btnHabilitar.setEnabled(true);
                    btnNuevo.setEnabled(true);
                    btnHabilitar.setText("Habilitar Edición");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hay productos asociados a esta categoria\npor lo que no se puede eliminar..!!", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelarProvActionPerformed

    private void btnActualizarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProvActionPerformed
        // TODO add your handling code here:
        String estado = "";
        if (RadioAcProv.isSelected()) {
            estado = "Activo";
        } else if (RInacProv.isSelected()) {
            estado = "Inactivo";
        }

        try {

            List<String> validar = new ArrayList<>();
            validar.add(txtNitProveedor.getText());
            validar.add(txtRsocialProv.getText());
            validar.add(txtDirProv.getText());
            //validar.add(txtFaxProv.getText());
           // validar.add(txtFonoProv.getText());
            validar.add(txtContacProv.getText());
            //validar.add(txtWebProv.getText());
            //validar.add(txtObsProv.getText());
            validar.add(txtFonoContacto.getText());
            
            if (Validaciones.validarCampos(validar)) 
            {
                Proveedor pro = this.getObjProveedor();//recuperando el objeto recuperado desde la tabla
                pro.setNit(txtNitProveedor.getText());
                pro.setNombre(txtRsocialProv.getText());
                pro.setDireccion(txtDirProv.getText());
                pro.setTelefono(txtFonoProv.getText());
                pro.setNrofax(txtFaxProv.getText());
                pro.setContacto(txtContacProv.getText());
                pro.setFonoContacto(Integer.parseInt(txtFonoContacto.getText()));
                pro.setWeb(txtWebProv.getText());
                pro.setEmail(txtEmailProv.getText());
                pro.setObservacion(txtObsProv.getText());
                pro.setEstado(estado);

                ProveedorDao proDao = new ProveedorDao();
                if (proDao.actualizarProveedor(pro)) 
                {
                    JOptionPane.showMessageDialog(this, "Actualización correcta", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposProv();
                    bloquearCamposProv();
                    listarDatos();
                    btnNuevo.setEnabled(true);
                    btnHabilitar.setEnabled(true);
                    btnHabilitar.setText("Habilitar Edición");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnActualizarProvActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed
        // TODO add your handling code here:
        if (btnHabilitar.isSelected()) {
            habilitarCamposCategoria();
            btnNuevo.setEnabled(false);
            btnAcProv.setEnabled(false);
            btnCanProv.setEnabled(false);
            btnAcProv.setEnabled(false);
            btnActualizarProv.setEnabled(true);
            btnCancelarProv.setEnabled(true);
            btnHabilitar.setText("Cancelar");
        } else {
            limpiarCamposProv();
            bloquearCamposProv();
            btnHabilitar.setEnabled(true);
            btnNuevo.setEnabled(true);
            // tablaAlamacen.setEnabled(true);
            btnHabilitar.setText("Habilitar Edición");
        }
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        habilitarCamposCategoria();
        btnHabilitar.setEnabled(false);
        btnNuevo.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCanProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanProvActionPerformed
        // TODO add your handling code here:
        limpiarCamposProv();
        bloquearCamposProv();
        btnNuevo.setEnabled(true);
        btnHabilitar.setEnabled(true);
    }//GEN-LAST:event_btnCanProvActionPerformed

    private void btnAcProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcProvActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtNitProveedor.getText());
            validar.add(txtRsocialProv.getText());
            validar.add(txtDirProv.getText());
            //validar.add(txtFaxProv.getText());
            //validar.add(txtFonoProv.getText());
            validar.add(txtContacProv.getText());
           // validar.add(txtWebProv.getText());
           // validar.add(txtObsProv.getText());
            validar.add(txtFonoContacto.getText());

            if (Validaciones.validarCampos(validar)) {
                //recuperando la categoria
                ProveedorDao proDao = new ProveedorDao();
                Proveedor pro = new Proveedor();
                if (RadioAcProv.isSelected()) {
                    pro.setEstado("Activo");
                } else if (RInacProv.isSelected()) {
                    pro.setEstado("Inactivo");
                }
                pro.setNit(txtNitProveedor.getText());
                pro.setNombre(txtRsocialProv.getText());
                pro.setDireccion(txtDirProv.getText());
                pro.setTelefono(txtFonoProv.getText());
                pro.setNrofax(txtFaxProv.getText());
                pro.setContacto(txtContacProv.getText());
                pro.setWeb(txtWebProv.getText());
                pro.setEmail(txtEmailProv.getText());
                pro.setObservacion(txtObsProv.getText());
                pro.setFonoContacto(Integer.parseInt(txtFonoContacto.getText()));

                if (proDao.registarProveedor(pro)) {
                    JOptionPane.showMessageDialog(this, "Registro de proveedor correcto..!!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposProv();
                    listarDatos();
                    bloquearCamposProv();
                    btnHabilitar.setEnabled(true);
                    btnNuevo.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAcProvActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo;
    private javax.swing.JRadioButton RInacProv;
    private javax.swing.JRadioButton RadioAcProv;
    private javax.swing.JButton btnAcProv;
    private javax.swing.JButton btnActualizarProv;
    private javax.swing.JButton btnCanProv;
    private javax.swing.JButton btnCancelarProv;
    private javax.swing.JToggleButton btnHabilitar;
    private javax.swing.JButton btnNuevo;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaProveedor;
    private javax.swing.JTextField txtContacProv;
    private javax.swing.JTextField txtDirProv;
    private javax.swing.JTextField txtEmailProv;
    private javax.swing.JTextField txtFaxProv;
    private javax.swing.JTextField txtFonoContacto;
    private javax.swing.JTextField txtFonoProv;
    private javax.swing.JTextField txtNitProveedor;
    private javax.swing.JTextArea txtObsProv;
    private javax.swing.JTextField txtRsocialProv;
    private javax.swing.JTextField txtWebProv;
    // End of variables declaration//GEN-END:variables

    public Proveedor getObjProveedor() {
        return ObjProveedor;
    }

    public void setObjProveedor(Proveedor ObjProveedor) {
        this.ObjProveedor = ObjProveedor;
    }
}

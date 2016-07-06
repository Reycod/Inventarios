/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.usuarios;

import dao.AlmacenDao;
import dao.CategoriaDao;
import dao.ProductoDao;
import dao.ProveedorDao;
import dao.UsuarioDao;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import mapeos.Categoria;
import mapeos.Producto;
import mapeos.Proveedor;
import mapeos.Usuario;
import util.Validaciones;
import static view.almacen.vtnAjustes.validaVentana;
import view.OC.vtnListarCompras;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class vtnUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form vtnUsuarios
     */
    private Usuario UsuarioObj;

    public static String validaVentana;

    DefaultTreeModel modelo = null;//modelo del Jtree

    public vtnUsuarios() {

        initComponents();
        validaVentana = "x";//insertando un valor a la variable que valida a la ventana
        /*Poniendo el JinternalFrame al centro de la ventana*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        /*-----------FIN-----------------*/
        listarDatos();//listando a los usuarios
        bloquearCamposUsuario();
        setIcono();

        //Validando los campos de ingreso
        Validaciones.validaNumeros(txtCi);
        Validaciones.validaNumeros(txtFono);
        cargarArbolCheck();
    }

    void cargarArbolCheck() 
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Plantilla("1", "PRIVILEGIOS SISTEMA", false, ""));
        //creando el primer hijo
        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(new Plantilla("2", "Modulo Productos", false, " Modulo productos"));
        hijo.add(new DefaultMutableTreeNode(new Plantilla("3", "Crear", false, "")));
        hijo.add(new DefaultMutableTreeNode(new Plantilla("4", "Modificar", false, "")));
        hijo.add(new DefaultMutableTreeNode(new Plantilla("5", "Eliminar", false, "")));
        root.add(hijo);
        //creando el segundo hijo
        DefaultMutableTreeNode hijo2 = new DefaultMutableTreeNode(new Plantilla("6", "Modulo Categorias", false, " Modulo Categorias"));
        hijo2.add(new DefaultMutableTreeNode(new Plantilla("7", "Crear", false, "")));
        hijo2.add(new DefaultMutableTreeNode(new Plantilla("8", "Modificar", false, "")));
        hijo2.add(new DefaultMutableTreeNode(new Plantilla("9", "Eliminar", false, "")));
        root.add(hijo2);

        modelo = new DefaultTreeModel(root);
        arbolPrivigelios.setModel(modelo);
        arbolPrivigelios.setEditable(true);
        arbolPrivigelios.setCellRenderer(new Render());
        arbolPrivigelios.setCellEditor(new Editor());
    }

    //Metodo que realiza una impresion de los checkbox seleccionados
    public void imprimirRecorridoProfundidad(JTree tree) {
        String output = "";
        TreePath selected ;
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) (tree.getModel().getRoot());
        Enumeration<?> enumeration = root.depthFirstEnumeration();
        System.out.println("Recorrido por Profundudidad es: ");
        int i=0;
        while (enumeration.hasMoreElements()) {
            output = enumeration.nextElement().toString() + " ";
            selected = tree.getSelectionPath();
            System.out.println(i + " . " + output);
            i++;
        }
    }  
    
    
    public void determinar_seleccionado(){
        try {
            DefaultMutableTreeNode nseleccionado = (DefaultMutableTreeNode) arbolPrivigelios.getLastSelectedPathComponent();
            JOptionPane.showMessageDialog(this, nseleccionado.getPath(), "Atencion", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    public void limpiarCampos() {
        txtCi.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtPass.setText("");
        txtCargo.setText("");
        txtNick.setText("");
        txtFono.setText("");
        ComboEstado.setSelectedIndex(0);
    }

    //metodo que inserta el icono de usuario por defecto
    public void setIcono() {
        ImageIcon imagenUser = new ImageIcon(getClass().getResource("/images/users2.png"));
        lbmImagen.setIcon(imagenUser);
    }

    //Metodo que bloquea los campos de texto
    public void bloquearCamposUsuario() {
        txtCi.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtPass.setEnabled(false);
        txtCargo.setEnabled(false);
        txtNick.setEnabled(false);
        txtFono.setEnabled(false);
        ComboEstado.setEnabled(false);

        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);

        //--------botones de accion-----
        btnNuevo.setEnabled(true);
        btnOpciones.setText("Habilitar Edición");
        btnOpciones.setEnabled(true);
        //--------fin botones de accion---

    }

    public void habilitarCamposUsuario() {
        txtCi.setEnabled(true);
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        txtPass.setEnabled(true);
        txtCargo.setEnabled(true);
        txtNick.setEnabled(true);
        txtFono.setEnabled(true);
        ComboEstado.setEnabled(true);

        //--------botones de accion-----
//        btnNuevo.setEnabled(true);
//        btnOpciones.setText("Habilitar Edición");
//        btnOpciones.setEnabled(true);
//        //--------fin botones de accion---
        //validando los campos de ingreso
    }

    //Metodo que realiza el listado de los usuarios
    public void listarDatos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaUsuarios.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaUsuarios);
            //realizando la consulta para realizar el listado de los datos
            UsuarioDao userDao = new UsuarioDao();
            List<Usuario> lista = userDao.listarUsuarios();
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getIdusuario();//id
                fila[1] = lista.get(i).getCi();//nombre
                fila[2] = lista.get(i).getNombre() + " " + lista.get(i).getApellido();
                fila[3] = lista.get(i).getAlias();
                fila[4] = lista.get(i).getEstado();
                fila[5] = lista.get(i).getCargo();
                fila[6] = lista.get(i).getTelefono();//categoria
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNick = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ComboEstado = new javax.swing.JComboBox();
        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        lbmImagen = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnOpciones = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        arbolPrivigelios = new javax.swing.JTree();
        txtURLImagen = new javax.swing.JTextField();

        setTitle("Usuarios de sistema");
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

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuarios.png"))); // NOI18N

        labelMetric1.setText("REGISTRO DE  USUARIOS ");
        labelMetric1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("C.I:");

        txtCi.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Nombres:");

        txtNombre.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Apellidos:");

        txtApellido.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Contraseña:");

        txtPass.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Cargo:");

        txtCargo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Teléfono:");

        txtFono.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Nick:");

        txtNick.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Estado:");

        ComboEstado.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        ComboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        lbmImagen.setForeground(new java.awt.Color(255, 255, 255));
        lbmImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbmImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbmImagenMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelRect1Layout = new javax.swing.GroupLayout(panelRect1);
        panelRect1.setLayout(panelRect1Layout);
        panelRect1Layout.setHorizontalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbmImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRect1Layout.setVerticalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbmImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 51));
        jLabel11.setText("Click para seleccionar una imagen");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CI", "USUARIO", "ALIAS", "ESTADO", "CARGO", "TELEFONO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaUsuariosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);
        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(35);
            tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(35);
            tablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(45);
            tablaUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/agregar.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/modificar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
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

        btnOpciones.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysInventory/iconos/habilitar.png"))); // NOI18N
        btnOpciones.setText("Habilitar opciones");
        btnOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionesActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Privilegios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        arbolPrivigelios.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("SISTEMA");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("colors");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("sports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockey");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("food");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        arbolPrivigelios.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolPrivigelios.setEditable(true);
        jScrollPane2.setViewportView(arbolPrivigelios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFono)
                    .addComponent(txtNick)
                    .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRect1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnOpciones)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRect1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnOpciones))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        txtURLImagen.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtURLImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 177, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtURLImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        validaVentana = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void lbmImagenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmImagenMousePressed
        // TODO add your handling code here:
        JFileChooser abrir = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        abrir.setFileFilter(filtroImagen);
        abrir.setDialogTitle("Seleccionar imagen..");
        int ventana = abrir.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = abrir.getSelectedFile();
            txtURLImagen.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtURLImagen.getText());//recuperando la URL de la imagen y convirtiendola en un objeto de tipo imagen
            foto = foto.getScaledInstance(lbmImagen.getWidth(), lbmImagen.getHeight(), Image.SCALE_DEFAULT);
            lbmImagen.setText("");
            lbmImagen.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_lbmImagenMousePressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try 
        {
            //Validando los campos
            List<String> validar = new ArrayList<>();
            validar.add(txtCi.getText());
            validar.add(txtNombre.getText());
            validar.add(txtApellido.getText());
            validar.add(txtPass.getText());
            validar.add(txtCargo.getText());
            validar.add(txtNick.getText());
            validar.add(txtFono.getText());
            //fin de la carga de los campos para validacion
            if (Validaciones.validarCampos(validar)) 
            {
                if (!txtURLImagen.getText().equals(""))
                {
                    //convirtiendo la imagen a archivo de Bytes para almacenar en la BD 
                    File archivoImagen = new File(txtURLImagen.getText());//recuperando la url de la imagen
                    byte[] bytefile = new byte[(int) archivoImagen.length()];
                    FileInputStream fs = new FileInputStream(archivoImagen);
                    fs.read(bytefile);
                    fs.close();

                    UsuarioDao userDao = new UsuarioDao();
                    Usuario user = new Usuario();
                    user.setCi(Integer.parseInt(txtCi.getText()));
                    user.setNombre(txtNombre.getText());
                    user.setApellido(txtApellido.getText());
                    user.setPassword(txtPass.getText());
                    user.setCargo(txtCargo.getText());
                    user.setAlias(txtNick.getText());
                    user.setTelefono(Integer.parseInt(txtFono.getText()));
                    user.setImagen(bytefile);
                    user.setEstado(ComboEstado.getSelectedItem().toString());

                    //recuperando los privilegios
                    DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbolPrivigelios.getLastSelectedPathComponent();
                    if (nodo == null) 
                    {
                        return;
                    }
                    Object nodoInfo = nodo.getUserObject();
                    Plantilla clsPlantilla = (Plantilla) nodoInfo;
                    
                    imprimirRecorridoProfundidad(arbolPrivigelios);
                    determinar_seleccionado();
                    if (clsPlantilla.getValor()) 
                    {
                        JOptionPane.showMessageDialog(this, "nodo seleccionado");
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "NO SELECCIONADO");
                    }
                    //fin de la recuperaciond de los privilegios

                    
                    //realizando la insercion del usuario en la BD
//                    if (userDao.registarUsuario(user)) 
//                    {
//                        JOptionPane.showMessageDialog(this, "Registro de Usuario correcto..!!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
//                        limpiarCampos();
//                        listarDatos();
//                        bloquearCamposUsuario();
//                        btnNuevo.setEnabled(true);
//                        btnOpciones.setEnabled(true);
//                        setIcono();//mostrando nuevamente la imagen de insercion
//                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Selecciones una imagen..", "mensaje", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtCi.getText());
            validar.add(txtNombre.getText());
            validar.add(txtApellido.getText());
            validar.add(txtPass.getText());
            validar.add(txtCargo.getText());
            validar.add(txtNick.getText());
            validar.add(txtFono.getText());

            if (Validaciones.validarCampos(validar)) {

                Usuario user = this.getUsuarioObj();//recuperando el objeto recuperado desde la tabla
                UsuarioDao userDao = new UsuarioDao();

                user.setCi(Integer.parseInt(txtCi.getText()));
                user.setNombre(txtNombre.getText());
                user.setApellido(txtApellido.getText());
                user.setPassword(txtPass.getText());
                user.setCargo(txtCargo.getText());
                user.setAlias(txtNick.getText());
                user.setTelefono(Integer.parseInt(txtFono.getText()));
                user.setEstado(ComboEstado.getSelectedItem().toString());

                //bloque que verifica que si se escogio otra imagen a actualizar
                if (!txtURLImagen.getText().equals("")) {
                    //recueprando la imagen
                    File archivoImagen = new File(txtURLImagen.getText());//recuperando la url de la imagen
                    byte[] bytefile = new byte[(int) archivoImagen.length()];
                    FileInputStream fs = new FileInputStream(archivoImagen);
                    fs.read(bytefile);
                    fs.close();
                    //fin de la creacion de la imagen

                    user.setImagen(bytefile);
                }
                //Fin bloque imagen

                if (userDao.actualizarUsuario(user)) {
                    JOptionPane.showMessageDialog(this, "Actualización correcta", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    bloquearCamposUsuario();
                    listarDatos();
                    setIcono();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnNuevo.setEnabled(false);
        habilitarCamposUsuario();
        limpiarCampos();
        setIcono();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        bloquearCamposUsuario();
        limpiarCampos();
        setIcono();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaUsuarios.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaUsuarios.getSelectedRow(), 0));//recuperando el id
        UsuarioDao userDao = new UsuarioDao();
        try {
            Usuario user = userDao.buscarUsuario(Integer.parseInt(dato));
            this.setUsuarioObj(user);//insertando el objeto recuperado por la consulta
            txtCi.setText(String.valueOf(user.getCi()));
            txtNombre.setText(user.getNombre());
            txtApellido.setText(user.getApellido());
            txtCargo.setText(user.getCargo());
            txtPass.setText(user.getPassword());
            txtNick.setText(user.getAlias());
            txtFono.setText(String.valueOf(user.getTelefono()));

            //recuperando la imagen y convirtiendo en ImageIco
            InputStream is = new ByteArrayInputStream(user.getImagen());
            BufferedImage image = ImageIO.read(is);
            ImageIcon ico = new ImageIcon(image);
            ImageIcon icono = new ImageIcon(ico.getImage().getScaledInstance(lbmImagen.getWidth(), lbmImagen.getHeight(), Image.SCALE_DEFAULT));
            lbmImagen.setIcon(icono);
            //fin de la recuperacion y muetra de la imagen

            if (user.getEstado().equals("Activo")) {
                ComboEstado.setSelectedIndex(0);
            } else if (user.getEstado().equals("Inactivo")) {
                ComboEstado.setSelectedIndex(1);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaUsuariosMousePressed

    private void btnOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionesActionPerformed
        if (btnOpciones.isSelected()) {
            habilitarCamposUsuario();
            btnNuevo.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnOpciones.setText("Cancelar");
        } else {
            limpiarCampos();
            bloquearCamposUsuario();
            btnOpciones.setEnabled(true);
            btnOpciones.setText("Habilitar Edición");
        }

    }//GEN-LAST:event_btnOpcionesActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            if (!txtNombre.getText().equals("")) {
                int y = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el registro?");
                if (y == JOptionPane.YES_OPTION) {
                    UsuarioDao userDao = new UsuarioDao();
                    if (userDao.eliminarUsuario(this.getUsuarioObj())) {
                        JOptionPane.showMessageDialog(this, "Eliminacion correcta", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                        bloquearCamposUsuario();
                        listarDatos();
                        setIcono();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Almacen a eliminar..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hay productos asociados a esta categoria\npor lo que no se puede eliminar..!!", "Mensaje..", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboEstado;
    private javax.swing.JTree arbolPrivigelios;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JToggleButton btnOpciones;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private javax.swing.JLabel lbmImagen;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCi;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtNick;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtURLImagen;
    // End of variables declaration//GEN-END:variables

    public Usuario getUsuarioObj() {
        return UsuarioObj;
    }

    public void setUsuarioObj(Usuario UsuarioObj) {
        this.UsuarioObj = UsuarioObj;
    }
}

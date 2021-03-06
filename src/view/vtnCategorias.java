/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import dao.CategoriaDao;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mapeos.Categoria;
import util.Validaciones;

/**
 *
 * @author Reynaldo
 */
public class vtnCategorias extends javax.swing.JInternalFrame {

    //Decalaracion de atributos 
    private Categoria ObjCategoria = new Categoria();

    public vtnCategorias() {
        initComponents();
        /*ALINEANDO LA VENTANA AL CENTRO*/
        int a = vtnPrincipal.sysMDI.getWidth() - this.getWidth();
        int b = vtnPrincipal.sysMDI.getHeight() - this.getHeight();
        setLocation(a / 2, b / 2);
        //FIN DE LA ALINEACION DE LA VENTANA

        bloquearCamposCategoria();
        listarDatos();
    }

    //Metodo que limpia los campos de texto
    public void limpiarCamposCategoria() {
        txtCateNombre.setText("");
        txtDesCate.setText("");
        radioActivo.setSelected(true);
    }

    //Metodo que bloquea los campos de texto
    public void bloquearCamposCategoria() {
        txtCateNombre.setEnabled(false);
        txtDesCate.setEnabled(false);
        radioActivo.setEnabled(false);
        RadioInactivo.setEnabled(false);
        btnAceptarCategoria.setEnabled(false);
        btnCancelarCategoria.setEnabled(false);
    }

    //Metodo que habilita los campos de texto
    public void habilitarCamposCategoria() {
        txtCateNombre.setEnabled(true);
        txtDesCate.setEnabled(true);
        radioActivo.setEnabled(true);
        RadioInactivo.setEnabled(true);
        btnAceptarCategoria.setEnabled(true);
        btnCancelarCategoria.setEnabled(true);
        limpiarCamposCategoria();
    }

    //Metodo que realiza el listado de los datos en un JTable de java
    public void listarDatos() {
        try 
        {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaCategoria.getModel();//creando el modela ára llenar los datos al JTableje
            limpiarTabla(tablaCategoria);
            //realizando la consulta para realizar el listado de los datos
            CategoriaDao catDao = new CategoriaDao();
            List<Categoria> lista = catDao.listarCategoria();
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                fila[0] = lista.get(i).getIdcategoria();
                fila[1] = lista.get(i).getNombre();
                fila[2] = lista.get(i).getDescripcion();
                fila[3] = lista.get(i).getEstado();
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

    //metodo que limpia los datos del Jtable 
    public void limpiarTabla() {
        DefaultTableModel m = new DefaultTableModel();
        tablaCategoria.setModel(m);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCateNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesCate = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        radioActivo = new javax.swing.JRadioButton();
        RadioInactivo = new javax.swing.JRadioButton();
        btnAceptarCategoria = new javax.swing.JButton();
        btnCancelarCategoria = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelScroll = new javax.swing.JScrollPane();
        tablaCategoria = new javax.swing.JTable();
        btnNuevoCategoria = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestión de categorias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Descripción:");

        txtDesCate.setColumns(20);
        txtDesCate.setRows(5);
        jScrollPane1.setViewportView(txtDesCate);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Estado:");

        grupo.add(radioActivo);
        radioActivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        radioActivo.setText("activo");

        grupo.add(RadioInactivo);
        RadioInactivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RadioInactivo.setText("inactivo");

        btnAceptarCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAceptarCategoria.setText("Aceptar");
        btnAceptarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCategoriaActionPerformed(evt);
            }
        });

        btnCancelarCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancelarCategoria.setText("Cancelar");
        btnCancelarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCateNombre)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radioActivo)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioInactivo)
                            .addComponent(btnCancelarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 54, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnAceptarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCateNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(radioActivo)
                    .addComponent(RadioInactivo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCIÓN", "ESTADO"
            }
        ));
        tablaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaCategoriaMousePressed(evt);
            }
        });
        panelScroll.setViewportView(tablaCategoria);

        btnNuevoCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevoCategoria.setText("Nuevo");
        btnNuevoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCategoriaActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Actualizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnNuevoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCategoriaActionPerformed
        // TODO add your handling code here:
        limpiarCamposCategoria();
        bloquearCamposCategoria();
    }//GEN-LAST:event_btnCancelarCategoriaActionPerformed

    private void btnAceptarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCategoriaActionPerformed
        // TODO add your handling code here:
        String estado = "";
        if (radioActivo.isSelected()) 
        {
            estado = "Activo";
        } 
        else if (RadioInactivo.isSelected()) {
            estado = "Inactivo";
        }

        try 
        {
            List<String> validar = new ArrayList<>();
            validar.add(txtCateNombre.getText());
            validar.add(txtDesCate.getText());
            validar.add(estado);
            if (Validaciones.validarCampos(validar)) 
            {
                Categoria cate = new Categoria(txtCateNombre.getText(), txtDesCate.getText(), estado);
                CategoriaDao catDao = new CategoriaDao();
                if (catDao.registarCategoria(cate)) 
                {
                    JOptionPane.showMessageDialog(this, "Registro de categoria correcto", null, JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCategoria();
                    bloquearCamposCategoria();
                    listarDatos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarCategoriaActionPerformed

    private void btnNuevoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCategoriaActionPerformed
        // TODO add your handling code here:
        habilitarCamposCategoria();
    }//GEN-LAST:event_btnNuevoCategoriaActionPerformed

    private void tablaCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCategoriaMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) tablaCategoria.getModel();
        String dato = String.valueOf(tm.getValueAt(tablaCategoria.getSelectedRow(), 0));
        CategoriaDao catDao = new CategoriaDao();
        try 
        {
            Categoria cat = catDao.buscarCategoria(Integer.parseInt(dato));
            this.setObjCategoria(cat);//insertando el
            txtCateNombre.setText(cat.getNombre());
            txtDesCate.setText(cat.getDescripcion());
            if (cat.getEstado().equals("Activo")) {
                radioActivo.setSelected(true);
            } else if (cat.getEstado().equals("Inactivo")) {
                RadioInactivo.setSelected(true);
            }
            //JOptionPane.showMessageDialog(null, "Precionado" + cat.getNombre());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablaCategoriaMousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int y = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?");
        try {
            if (y == JOptionPane.YES_OPTION) {
                CategoriaDao cate = new CategoriaDao();
                if (cate.eliminarusuario(this.getObjCategoria())) {
                    JOptionPane.showMessageDialog(this, "Eliminacion correcta", null, JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCategoria();
                    bloquearCamposCategoria();
                    listarDatos();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hay productos asociados a esta categoria\npor lo que no se puede eliminar..!!", null, JOptionPane.ERROR_MESSAGE);
        }
        //JOptionPane.showMessageDialog(null, "Precionado" + this.getObjCategoria().getNombre());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        String estado = "";
        if (radioActivo.isSelected()) {
            estado = "Activo";
        } else if (RadioInactivo.isSelected()) {
            estado = "Inactivo";
        }

        try {
            List<String> validar = new ArrayList<>();
            validar.add(txtCateNombre.getText());
            validar.add(txtDesCate.getText());
            validar.add(estado);
            if (Validaciones.validarCampos(validar)) {
                Categoria cate = this.getObjCategoria();//recuperando el objeto recuperado desde la tabla
                cate.setNombre(txtCateNombre.getText());
                cate.setDescripcion(txtDesCate.getText());
                cate.setEstado(estado);

                CategoriaDao catDao = new CategoriaDao();
                if (catDao.actualizarCategoria(cate)) {
                    JOptionPane.showMessageDialog(this, "Actualización correcta", null, JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCategoria();
                    bloquearCamposCategoria();
                    listarDatos();
                }
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar..!!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioInactivo;
    private javax.swing.JButton btnAceptarCategoria;
    private javax.swing.JButton btnCancelarCategoria;
    private javax.swing.JButton btnNuevoCategoria;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JRadioButton radioActivo;
    private javax.swing.JTable tablaCategoria;
    private javax.swing.JTextField txtCateNombre;
    private javax.swing.JTextArea txtDesCate;
    // End of variables declaration//GEN-END:variables

    public Categoria getObjCategoria() {
        return ObjCategoria;
    }

    public void setObjCategoria(Categoria ObjCategoria) {
        this.ObjCategoria = ObjCategoria;
    }
}

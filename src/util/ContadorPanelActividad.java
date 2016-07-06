/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import view.vtnPrincipal;

/**
 *
 * @author Reynaldo
 */
public class ContadorPanelActividad {

    public int contadorOC = 0;//contador de ordenes de compra a procesar
    public int contadorIngresoAlmacen;

    public ContadorPanelActividad() {
    }

    //metodo que recupera el numero de ordenes de compra
    //a procesar
    public static void contadorOC()
    {
        try {
            Connection miConexion = Conexion.getConectar();
            Statement statement = miConexion.createStatement();
            
             ResultSet rs = statement.executeQuery("SELECT COUNT(estado) FROM compra where estado=\"En espera\" ");
            int contador;
            if (rs.next()) 
            {
                contador=rs.getInt("count(estado)");
                vtnPrincipal.labelContadorOC.setText(String.valueOf(contador));
            }
            else
            {
                    vtnPrincipal.labelContadorOC.setText("0");
            }
            statement.close();
            miConexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado en la tabla\nDetalleCompra" + ex.getMessage());
        }
    }
    
    //metodo que actualiza el contador de ingreso a almacen
    public static void contadorIA()
    {
        try 
        {
            Connection miConexion = Conexion.getConectar();
            Statement statement = miConexion.createStatement();
            
             ResultSet rs = statement.executeQuery("Select count(estado) from Detallecompra where estado='0'");
            int contador;
            if (rs.next()) 
            {
                contador=rs.getInt("count(estado)");
                vtnPrincipal.labelContadorIA.setText(String.valueOf(contador));
            }
            else
            {
                    vtnPrincipal.labelContadorIA.setText("0");
            }
            statement.close();
            miConexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado en la tabla\nDetalleCompra" + ex.getMessage());
        }
    }
    
}

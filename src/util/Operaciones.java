/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import dao.ProductoDao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mapeos.Fifo;
import mapeos.Producto;

/**
 *
 * @author Reynaldo
 */
public class Operaciones {

    public int contadorOC;

    //Metodo que realiza la actualizacion del estado de los productos
    //del detalle de la compra al ingreso al almacen
    //NO INGRESADO A ALMACEN ESTADO=0
    //INGRESADO A ALMACEN ESTADO=1
    public static void actualizaEstadoDetalleCompra(int id) {
        try {
            Connection miConexion = Conexion.getConectar();
            PreparedStatement statement = miConexion.prepareStatement("UPDATE detallecompra SET estado=1  WHERE iddetallecompra=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            miConexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado en la tabla\nDetalleCompra" + ex.getMessage());
        }
    }

    //Metodo que realiza la actualizacion del precio en la tabla producto
    //Este campo se actualiza al precio de la ultima compra
    public static void actualizaPrecioProducto(int id, String precio) {
        try {
            Connection miConexion = Conexion.getConectar();
            PreparedStatement statement = miConexion.prepareStatement("UPDATE producto SET precio=?  WHERE idproducto=?");
            statement.setString(1, precio);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            miConexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el precio del producto..!!" + ex.getMessage());
        }
    }

    //Metodo que realiza la actualizacion del precio en la tabla producto
    //Este campo se actualiza al precio de la ultima compra
    public static void actualizaStockInventario(int id, String stock) {
        try {
            Connection miConexion = Conexion.getConectar();
            PreparedStatement statement = miConexion.prepareStatement("UPDATE inventario SET stock=?  WHERE idinventario=?");
            statement.setString(1, stock);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            miConexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el precio del producto..!!" + ex.getMessage());
        }
    }

    //METODO QUE VERIFICA SI TODOS LOS PRODUCTOS DEL DETALLE DE UNA COMPRA YS INGRESO 
    //AL ALMACEN VERIFICA SI EL CAMPO ESTADO DE LA TABLA detallecompra = 1
    // estado=1-->ya ingresado al almacen
    public static boolean verEstadoIngresoDetalleCompra(int id) {
        try {
            Connection con = Conexion.getConectar();//creando una instancia de la clase conexion
            String sql = "Select * from detallecompra where compra_idcompra=" + id;
            Statement stm = con.createStatement();
            ResultSet consulta = stm.executeQuery(sql);//ejecutando la consulta
            boolean valor = false;//variable que contiene la verificacion del estado de los productos
            while (consulta.next()) {
                if (consulta.getString("estado").equals("0")) {
                    valor = true;
                    break;
                }
            }
            stm.close();
            con.close();
            return valor;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado en la tabla\nDetalleCompra" + ex.getMessage());
            return true;
        }
    }

    //Metodo que realiza la insercion del registro del ajuste en el Kardex del Fifo
    public static void ingresoFifoAjuste(Fifo fifo) {
        try {
            Connection miConexion = Conexion.getConectar();
            PreparedStatement statement = miConexion.prepareStatement("INSERT INTO fifo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, 0);//id 
            statement.setInt(2, fifo.getInventario().getIdinventario());
            statement.setString(3, fifo.getDocumento());
            statement.setInt(4, fifo.getNdocumento());
            statement.setString(5, fifo.getFecha());
            statement.setString(6, fifo.getDetalle());
            statement.setInt(7, fifo.getCant_entrada());
            statement.setString(8, fifo.getPrecio_entrada());
            statement.setString(9, fifo.getP_total_entrada());

            statement.setInt(10, fifo.getCant_salida());
            statement.setString(11, fifo.getPrecio_salida());
            statement.setString(12, fifo.getPrecio_salida());

            statement.setInt(13, fifo.getCant_saldo());
            statement.setString(14, fifo.getPrecio_saldo());
            statement.setString(15, fifo.getP_total_saldo());

            statement.executeUpdate();
            statement.close();
            miConexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el precio del producto..!!" + ex.getMessage());
        }
    }

    public static BigDecimal redondear(double valor) {
        //double valor = 1254.625;
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(1, RoundingMode.HALF_UP);
        //System.out.println("Número : " + big);
        return big;
    }

    //Metodo que crea el campo de autocompletar para la busqueda
    public static void crearAutocompletar(JTextField campo) {
        try {
            TextAutoCompleter autocompletar = new TextAutoCompleter(campo);

            ProductoDao prDao = new ProductoDao();
            List<Producto> lista = prDao.listarProductos();

            for (int i = 0; i < lista.size(); i++) {
                autocompletar.addItem(lista.get(i).getNombre());
                //      autocompletar.addItem(lista.get(i).getCodigoproducto());
            }
        } catch (Exception ex) {
        }
    }

    //Metodo  que parametriza el calendario de OC
    public static void parametrosCalendario(JDateChooser campo) {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        //JOptionPane.showMessageDialog(null, "" + año+"-"+mes+"-"+dia);
        Calendar calendario = new GregorianCalendar(año, mes, dia);
        //Estableciendo la fecha minima de seleccion
        campo.setMinSelectableDate(calendario.getTime());
    }

    //Metodo que elimina un producto de los items combo producto
    public static void eliminarItemComboProducto(int idprod, int idcombo) {
        try 
        {
            Connection miConexion = Conexion.getConectar();
            PreparedStatement statement = miConexion.prepareStatement("DELETE FROM ItemsCombo WHERE idProducto=? and combo.idcomboProducto=?");
            statement.setInt(1, idprod);
            statement.setInt(2, idcombo);
            statement.executeUpdate();
            statement.close();
            miConexion.close();
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado en la tabla\nDetalleCompra" + ex.getMessage());
        }
    }

//    
//    public static void main(String args[]) {
//        + mes + dia + hora + minuto + segundo;
//
//        redondear(312.354);
//    }
}

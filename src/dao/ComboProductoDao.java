/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapeos.ComboProducto;
import mapeos.Producto;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class ComboProductoDao {

    Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaexception(HibernateException he) {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos");
    }

    public boolean registarComboProducto(ComboProducto cmb) throws Exception {
        iniciarOperacion();
        sesion.save(cmb);
        tx.commit();
        sesion.close();
        return true;
    }

    public boolean actualizarComboProducto(ComboProducto cmb) throws Exception {
        iniciarOperacion();
        sesion.update(cmb);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Object[]> listarCombosArmados() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("Select c.idcomboProducto,c.codigo,c.nombre,c.precioMercado,c.precioCliente,c.precioMayorista,c.estado from ComboProducto As c");
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

    //Metodo que recupera el detalle del comboArmado
    public List<Object[]> listarItemsComboArmado(int idcombo) throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT i.iditemsCombo,p.nombre,i.cantidad  FROM ItemsCombo i, Producto p WHERE i.combo.idcomboProducto=? and  i. idProducto=p.idproducto");
        query.setInteger(0, idcombo);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

    //metodo que realiza la busqueda del pryecto
    public ComboProducto buscarCombo(int id) throws Exception 
    {
        ComboProducto cm = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From ComboProducto Where idcomboProducto=?");
        query.setInteger(0, id);
        cm = (ComboProducto) query.uniqueResult();
        sesion.close();
        return cm;
    }
   
    //Metodo que verifica si el codigo ingresado ya esta registrado 
    public  boolean buscarCodigo(String codigo) 
    {
        ComboProducto cm=null;
        Producto pro=null;
        iniciarOperacion();
        Query query=sesion.createQuery("From ComboProducto Where codigo=?");
        query.setString(0, codigo);
        cm=(ComboProducto) query.uniqueResult();
        if(cm!=null)
        {
            return true;//TRUE= codigo ya regitrado
        }
        
        Query query2=sesion.createQuery("From Producto Where codigoproducto=?");
        query2.setString(0, codigo);
        pro= (Producto) query2.uniqueResult();
        sesion.close();
        if(pro!=null)
        {
            return true;//TRUE= codigo ya regitrado
        }
        return false;//FALSE= codigo no registrado y listo para regitrarse
    }
}

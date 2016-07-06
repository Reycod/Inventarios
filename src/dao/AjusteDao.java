/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import mapeos.Ajustes;
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
public class AjusteDao 
{
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


    public boolean registarAjuste(Ajustes aj) throws Exception {
        iniciarOperacion();
        sesion.save(aj);
        tx.commit();
        sesion.close();
        return true;
    }
    
    //Metodo que realiza el listado del kardex de inventario por almacen
    public List<Object[]> listarAjustesCompleto(int id) throws Exception 
    {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT a.idajuste,a.fecha,a.detalle,a.responsable,p.nombre,a.cantidad,a.cantidadA,a.precio,a.precioA,alm.nombre  FROM Ajustes a , Producto p, Almacen alm WHERE a.idproducto=p.idproducto and alm.idalmacen=a.idalmacen and a.idajuste=?");
        query.setInteger(0, id);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }
    
    //Metodo que realiza el listado del kardex de inventario por almacen
    public List<Object[]> listarAjustes() throws Exception 
    {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT a.idajuste,a.fecha,a.detalle,a.responsable,p.nombre,alm.nombre  FROM Ajustes a , Producto p, Almacen alm WHERE a.idproducto=p.idproducto and alm.idalmacen=a.idalmacen order by a.fecha desc");
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }
}

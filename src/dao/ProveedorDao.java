/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapeos.Categoria;
import mapeos.Producto;
import mapeos.Proveedor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class ProveedorDao {

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

    public boolean registarProveedor(Proveedor pro) throws Exception {
        iniciarOperacion();
        sesion.save(pro);
        tx.commit();
        sesion.close();
        return true;
    }

    public boolean actualizarProveedor(Proveedor pro) throws Exception {
        iniciarOperacion();
        sesion.update(pro);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Proveedor> listarProveedores() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("From Proveedor");
        List<Proveedor> lista = query.list();
        sesion.close();
        return lista;
    }
    //Metodo que lista a los proveedores activos
    public List<Proveedor> listarProveedoresActivos() throws Exception 
    {
        iniciarOperacion();
        Query query = sesion.createQuery("From Proveedor Where estado='Activo'");
        List<Proveedor> lista = query.list();
        sesion.close();
        return lista;
    }
    
    public boolean eliminarProveedor(Proveedor pro) throws Exception {
        iniciarOperacion();
        sesion.delete(pro);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public Proveedor buscarProveedor(String nombre) throws Exception 
    {
        Proveedor pro = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Proveedor Where nombre=?");
        query.setString(0, nombre);
        pro = (Proveedor) query.uniqueResult();
        sesion.close();
        return pro ;
    }
}

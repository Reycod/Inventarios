/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapeos.Almacen;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class AlmacenDao {

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

    public boolean registarAlmacen(Almacen alm) throws Exception {
        iniciarOperacion();
        sesion.save(alm);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Almacen> listarAlmacen() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("From Almacen");
        List<Almacen> lista = query.list();
        sesion.close();
        return lista;
    }

    public int buscarAlmacenId(String nombre) throws Exception
    {
        iniciarOperacion();
        Query query = sesion.createQuery("Select  A.idalmacen From Almacen A where nombre=?");
        query.setString(0, nombre);
        int id =(int) query.uniqueResult();
        tx.commit();
        sesion.close();
        return  id;
    }
    public boolean eliminarAlmacen(Almacen alm) throws Exception {
        iniciarOperacion();
        sesion.delete(alm);
        tx.commit();
        sesion.close();
        return true;
    }

    public boolean actualizarAlmacen(Almacen alm) throws Exception {
        iniciarOperacion();
        sesion.update(alm);
        tx.commit();
        sesion.close();
        return true;
    }
    
     
}

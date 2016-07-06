/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import mapeos.Pago;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class PagoDao 
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


    public boolean registarFormaPago(Pago p) throws Exception {
        iniciarOperacion();
        sesion.save(p);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public boolean actualizarFormaPago(Pago p) throws Exception {
        iniciarOperacion();
        sesion.update(p);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Pago> listarFpago() throws Exception 
    {
        iniciarOperacion();
        Query query = sesion.createQuery("From Pago");
        List<Pago> lista = query.list();
        sesion.close();
        return lista;
    }
    public boolean eliminarFpago(Pago p) throws Exception {
        iniciarOperacion();
        sesion.delete(p);
        tx.commit();
        sesion.close();
        return true;
    }
    public Pago buscarPago(String codigo) throws Exception 
    {
        Pago pg = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Pago Where codigo=?");
        query.setString(0, codigo);
        pg = (Pago) query.uniqueResult();
        sesion.close();
        return pg;
    }
    
    public Pago buscarPago(int id) throws Exception 
    {
        Pago pg = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Pago Where id=?");
        query.setInteger(0, id);
        pg = (Pago) query.uniqueResult();
        sesion.close();
        return pg;
    }
}

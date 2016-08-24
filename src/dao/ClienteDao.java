/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapeos.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


/**
 *
 * @author Reynaldo
 */
public class ClienteDao 
{

    Session sesion;
    Transaction tx;
    //Metodo que inicia la sesion

    public void iniciarOperacion() 
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaexception(HibernateException he) {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos");
    }

    public boolean registarCliente(Cliente client) throws Exception {
        iniciarOperacion();
        sesion.save(client);
        tx.commit();
        sesion.close();
        return true;
    }
    
    //Metodo que realiza la busqueda de un usuario por el id
    public Cliente buscarCliente(int ci) throws Exception 
    {
        Cliente user = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Cliente Where ci=?");
        query.setInteger(0, ci);
        user = (Cliente) query.uniqueResult();
        sesion.close();
        return user;
    }
}

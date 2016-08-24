/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import mapeos.Compra;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * RWebDesigners 
 * @author Ing. Reynaldo
 * Copyright 2016
 * Todos los derechos reservador de autor
 * Propiedad intelectual
 */
public class ListaPreciosDao {
     Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() 
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaexception(HibernateException he)
    {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos");
    }

    public boolean registarListaPrecios(Compra cm) throws Exception 
    {
        iniciarOperacion();
        sesion.save(cm);
        tx.commit();
        sesion.close();
        return true;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import mapeos.Compra;
import mapeos.ListaPrecios;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

    public boolean registarListaPrecios(ListaPrecios lp) throws Exception 
    {
        iniciarOperacion();
        sesion.save(lp);
        tx.commit();
        sesion.close();
        return true;
    }
    
    //Metodo que realiza la busqueda de un NOMBRE duplicado para realizar la insercion en la BD
    public ListaPrecios BuscarNombre(String nombre)
    {
        ListaPrecios lp=null;
        iniciarOperacion();
        Query query = sesion.createQuery(" From ListaPrecios Where nombre=?");
        query.setString(0, nombre);
        lp=(ListaPrecios) query.uniqueResult();
        tx.commit();
        sesion.close();
        return lp;
    }
    
    //Metodo que realiza la busqueda de un CODIGO duplicado para realizar la insercion en la BD
    public ListaPrecios BuscarCodigo(String codigo)
    {
        ListaPrecios lp=null;
        iniciarOperacion();
        Query query = sesion.createQuery("From ListaPrecios Where codigo=?");
        query.setString(0, codigo);
        lp=(ListaPrecios) query.uniqueResult();
        tx.commit();
        sesion.close();
        return lp;
    }
    
    //Metodo que realiza el listado de los precios creados
    public List<Object[]> listarPrecios() throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("Select l.idlista_precios,l.fecha,l.nombre,l.codigo,l.estado,a.nombre from ListaPrecios As l, Almacen As a where a.idalmacen=l.idAlmacen");
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }
    
    //Metodo que recupera el detalle de la lista de precios creada
    public List<Object[]> listarItemsListaPrecios(int idListaPrecios) throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT d.idproductoslista,p.nombre,d.precio,d.precioCliente,d.precioMayor FROM Productos_lista_precios As d, Producto As p WHERE d.idProducto=p.idproducto and d.listaPrecios.idlista_precios=?");
        query.setInteger(0, idListaPrecios);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import mapeos.Categoria;
import mapeos.UnidadMedida;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class UnidadMedidaDao 
{
     Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public boolean registarUnidadMedida(UnidadMedida unidad) throws Exception {
        iniciarOperacion();
        sesion.save(unidad);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public boolean actualizarUnidadMedida(UnidadMedida unidad) throws Exception {
        iniciarOperacion();
        sesion.update(unidad);
        tx.commit();
        sesion.close();
        return true;
    }
    public boolean eliminarUnidadMedida(UnidadMedida unidad) throws Exception {
        iniciarOperacion();
        sesion.delete(unidad);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public List<UnidadMedida> listarUnidades() throws Exception
    {
        iniciarOperacion();
        Query query = sesion.createQuery("From UnidadMedida");
        List<UnidadMedida> lista = query.list();
        sesion.close();
        return lista;
    }
    
    public UnidadMedida buscarCategoria(int id) throws Exception {
        UnidadMedida cate = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From UnidadMedida Where idunidadmedida=?");
        query.setInteger(0, id);
        cate = (UnidadMedida) query.uniqueResult();
        sesion.close();
        return cate;
    }
    
    //metodo que realiza la busqueda de una
    public UnidadMedida buscarUnidadMedida(String nombre) throws Exception 
    {
        UnidadMedida cate = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From UnidadMedida Where nombre=?");
        query.setString(0,nombre);
        cate = (UnidadMedida) query.uniqueResult();
        sesion.close();
        return cate;
    } 
}

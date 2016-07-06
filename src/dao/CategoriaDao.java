/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapeos.Categoria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class CategoriaDao {

    Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public boolean registarCategoria(Categoria cate) throws Exception {
        iniciarOperacion();
        sesion.save(cate);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public List<Categoria> listarCategoria() throws Exception
    {
        iniciarOperacion();
        Query query = sesion.createQuery("From Categoria");
        List<Categoria> lista = query.list();
        sesion.close();
        return lista;
    }
    
    public Categoria buscarCategoria(int id) throws Exception {
        Categoria cate = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Categoria Where idcategoria=?");
        query.setInteger(0, id);
        cate = (Categoria) query.uniqueResult();
        sesion.close();
        return cate;
    }
    
    public Categoria buscarCategoriaLista(String nombre) throws Exception 
    {
        Categoria cate = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Categoria Where nombre=?");
        query.setString(0, nombre);
        cate = (Categoria) query.uniqueResult();
        sesion.close();
        return cate;
    }
    public boolean eliminarusuario(Categoria cate) throws Exception {
        iniciarOperacion();
        sesion.delete(cate);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public boolean actualizarCategoria(Categoria cate) throws Exception {
        iniciarOperacion();
        sesion.update(cate);
        tx.commit();
        sesion.close();
        return true;
    }
}
